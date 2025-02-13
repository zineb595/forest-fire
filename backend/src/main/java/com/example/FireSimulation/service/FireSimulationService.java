package com.example.FireSimulation.service;

import com.example.FireSimulation.config.SimulationConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Service responsible for managing the fire simulation.
 */
@Service
public class FireSimulationService {

    private int[][] grid; // The simulation grid
    private final SimulationConfig simulationConfig; // Configuration for the simulation
    private volatile boolean simulationRunning = false; // Flag to control simulation execution
    private final Random random = new Random(); // Random generator for fire propagation
    private GridUpdateListener gridUpdateListener; // Listener to notify grid updates

    /**
     * Constructor for the FireSimulationService.
     * The configuration settings for the simulation.
     */
    public FireSimulationService(SimulationConfig simulationConfig) {
        this.simulationConfig = simulationConfig;
    }

    /**
     * Sets the listener for grid updates.
     * The listener to receive grid updates.
     */
    public void setGridUpdateListener(GridUpdateListener listener) {
        this.gridUpdateListener = listener;
    }

    /**
     * Checks if there is still fire present in the grid.
     * True if there is at least one fire cell, false otherwise.
     */
    private boolean isFirePresent() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) { // Fire still exists
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Initializes the simulation grid with random values (forest/empty)
     * and sets the initial fire positions from the configuration.
     */
    public void initializeGrid() {
        int width = simulationConfig.getWidth();
        int height = simulationConfig.getHeight();
        grid = new int[height][width];

        // Populate the grid with random forest (1) or empty (0) cells
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = random.nextInt(2); // Random 0 or 1 (forest/empty)
            }
        }

        // Set initial fire positions from configuration
        List<SimulationConfig.FirePosition> firePositions = simulationConfig.getInitialFirePositions();
        for (SimulationConfig.FirePosition firePosition : firePositions) {
            int row = firePosition.getRow();
            int col = firePosition.getCol();
            grid[row][col] = 2;  // Set fire at specified positions
        }
        notifyGridUpdate(); // Notify that the grid has been updated
    }

    /**
     * Starts the fire propagation simulation in a separate thread.
     */
    public void startSimulation() {
        simulationRunning = true;
        new Thread(this::simulateFirePropagation).start();
    }

    /**
     * Stops the fire simulation.
     */
    public void stopSimulation() {
        simulationRunning = false;
    }

    /**
     * Restarts the fire simulation by stopping it and starting again.
     */
    public void restartSimulation() {
        stopSimulation(); // Stop the current simulation first
        startSimulation();
    }

    /**
     * Simulates the propagation of fire in the grid.
     * The fire spreads to adjacent forest cells based on a probability.
     */
    private void simulateFirePropagation() {
        while (simulationRunning && isFirePresent()) {
            int[][] newGrid = new int[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 2) { // Fire cell
                        newGrid[i][j] = 3; // Turn into ashes

                        // Simulate fire spread with a probability
                        if (random.nextDouble() < simulationConfig.getFirePropagationProbability()) {
                            if (i > 0 && grid[i - 1][j] == 1) newGrid[i - 1][j] = 2; // Spread up
                            if (i < grid.length - 1 && grid[i + 1][j] == 1) newGrid[i + 1][j] = 2; // Spread down
                            if (j > 0 && grid[i][j - 1] == 1) newGrid[i][j - 1] = 2; // Spread left
                            if (j < grid[i].length - 1 && grid[i][j + 1] == 1) newGrid[i][j + 1] = 2; // Spread right
                        }
                    } else {
                        newGrid[i][j] = grid[i][j]; // Keep the same state if no fire
                    }
                }
            }

            grid = newGrid; // Update the grid
            notifyGridUpdate(); // Notify that the grid has been updated

            try {
                Thread.sleep(500); // Control the speed of simulation updates
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Notifies the listener about grid updates after every change.
     */
    private void notifyGridUpdate() {
        if (gridUpdateListener != null) {
            gridUpdateListener.onGridUpdate(grid);
        }
    }

    /**
     * Interface for grid update listener, allowing external components
     * to receive updates when the grid changes.
     */
    public interface GridUpdateListener {
        void onGridUpdate(int[][] grid);
    }
}
