package com.example.FireSimulation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "simulation")
public class SimulationConfig {

    private int Width;
    private int Height;
    private List<FirePosition> initialFirePositions;
    private double firePropagationProbability;

    // Getters et setters
    public int getWidth() { return Width; }
    public void setWidth(int Width) { this.Width = Width; }

    public int getHeight() { return Height; }
    public void setHeight(int height) { this.Height = height; }

    public List<FirePosition> getInitialFirePositions() { return initialFirePositions; }
    public void setInitialFirePositions(List<FirePosition> initialFirePositions) { this.initialFirePositions = initialFirePositions; }

    public double getFirePropagationProbability() { return firePropagationProbability; }
    public void setFirePropagationProbability(double firePropagationProbability) { this.firePropagationProbability = firePropagationProbability; }

    // Classe interne pour FirePosition
    public static class FirePosition {
        private int row;
        private int col;

        public int getRow() { return row; }
        public void setRow(int row) { this.row = row; }

        public int getCol() { return col; }
        public void setCol(int col) { this.col = col; }
    }
}
