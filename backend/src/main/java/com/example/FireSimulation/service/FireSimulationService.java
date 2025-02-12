package com.example.FireSimulation.service;

import com.example.FireSimulation.config.SimulationConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FireSimulationService {

    private int[][] grid;
    private final SimulationConfig simulationConfig;
    private volatile boolean simulationRunning = false;
    private final Random random = new Random();
    private GridUpdateListener gridUpdateListener;

    public FireSimulationService(SimulationConfig simulationConfig) {
        this.simulationConfig = simulationConfig;
        initializeGrid();  // Initialize grid when the service starts
    }

    public void setGridUpdateListener(GridUpdateListener listener) {
        this.gridUpdateListener = listener;
    }

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

    public void initializeGrid() {
        int width = simulationConfig.getWidth();
        int height = simulationConfig.getHeight();
        grid = new int[height][width];

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
            grid[row][col] = 2;  // Set fire
        }
        notifyGridUpdate();  // Notify that the grid has been updated
    }

    // Starts the fire propagation simulation
    public void startSimulation() {
        simulationRunning = true;
        new Thread(this::simulateFirePropagation).start();
    }

    public void stopSimulation() {
        simulationRunning = false;
    }

    public void restartSimulation() {
        stopSimulation(); // Stop the current simulation first
        initializeGrid(); // Reset grid
        startSimulation();
    }

    private void simulateFirePropagation() {
        while (simulationRunning && isFirePresent()) {
            int[][] newGrid = new int[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 2) { // Fire
                        System.out.println("HOOO ");
                        newGrid[i][j] = 3; // Turn into ashes

                        // Simulate fire spread with a probability
                        if (random.nextDouble() < simulationConfig.getFirePropagationProbability()) {
                            if (i > 0 && grid[i - 1][j] == 1) newGrid[i - 1][j] = 2; // Up
                            if (i < grid.length - 1 && grid[i + 1][j] == 1) newGrid[i + 1][j] = 2; // Down
                            if (j > 0 && grid[i][j - 1] == 1) newGrid[i][j - 1] = 2; // Left
                            if (j < grid[i].length - 1 && grid[i][j + 1] == 1) newGrid[i][j + 1] = 2; // Right
                        }
                    } else {
                        newGrid[i][j] = grid[i][j];
                    }
                }
            }
            System.out.println("1 ");
            grid = newGrid;  // Update the grid
            notifyGridUpdate();  // Notify that the grid has been updated

            try {
                Thread.sleep(2000); // Simulation speed control
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    // Notify the listener about the grid update (this is called after every grid change)
    private void notifyGridUpdate() {
        if (gridUpdateListener != null) {
            gridUpdateListener.onGridUpdate(grid);
        }
    }

    // Interface for the listener callback
    public interface GridUpdateListener {
        void onGridUpdate(int[][] grid);
    }
}
