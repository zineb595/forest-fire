package com.example.FireSimulation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Configuration class for fire simulation.
 * It loads properties from the application configuration file (e.g., application.yml)
 */
@Component
@ConfigurationProperties(prefix = "simulation")
public class SimulationConfig {

    private int Width;  // Width of the simulation grid
    private int Height; // Height of the simulation grid
    private List<FirePosition> initialFirePositions; // List of initial fire positions in the grid
    private double firePropagationProbability; // Probability of fire spreading to adjacent cells

    // Getters and setters for width
    public int getWidth() { return Width; }
    public void setWidth(int Width) { this.Width = Width; }

    // Getters and setters for height
    public int getHeight() { return Height; }
    public void setHeight(int height) { this.Height = height; }

    // Getters and setters for the list of initial fire positions
    public List<FirePosition> getInitialFirePositions() { return initialFirePositions; }
    public void setInitialFirePositions(List<FirePosition> initialFirePositions) { this.initialFirePositions = initialFirePositions; }

    // Getters and setters for fire propagation probability
    public double getFirePropagationProbability() { return firePropagationProbability; }
    public void setFirePropagationProbability(double firePropagationProbability) { this.firePropagationProbability = firePropagationProbability; }

    /**
     * Inner class representing a fire position in the grid.
     * Each fire position consists of a row and a column.
     */
    public static class FirePosition {
        private int row; // Row index of the fire position
        private int col; // Column index of the fire position

        // Getters and setters for row
        public int getRow() { return row; }
        public void setRow(int row) { this.row = row; }

        // Getters and setters for column
        public int getCol() { return col; }
        public void setCol(int col) { this.col = col; }
    }
}
