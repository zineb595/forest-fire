<script setup lang="ts">
import { ref } from "vue";
import ForestGrid from "../components/ForestGrid.vue";

import {
  startSimulation,
  restartSimulation,
  stopSimulation,
} from "../socket/socket"; // Importing socket functions

// Track if the start button should be disabled
const isStartDisabled = ref(false);

// Handle the click event for the start button
const handleStartClick = () => {
  isStartDisabled.value = true; // Disable button after click
  startSimulation(); // Start the simulation
};
</script>

<template>
  <div class="simulation-container">
    <h1>🔥 Forest Fire Simulation</h1>

    <!-- Forest Grid Section -->
    <div class="forest-grid-container">
      <ForestGrid />
      <!-- Embed the ForestGrid component -->
    </div>

    <!-- Button Container -->
    <div class="button-container">
      <button
        class="button start-btn"
        @click="handleStartClick"
        :disabled="isStartDisabled"
      >
        Start
      </button>
      <button class="button restart-btn" @click="restartSimulation">
        Restart
      </button>
      <button class="button stop-btn" @click="stopSimulation">Stop</button>
    </div>
  </div>
</template>

<style scoped>
.simulation-container {
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #f4f4f9;
  font-family: "Arial", sans-serif;
}

h1 {
  font-size: 2.5rem;
  color: #2f4f4f;
  margin-bottom: 5px;
  margin-top: 5px;
}

.forest-grid-container {
  width: 80%;
  max-width: 550px;
  height: 500px;
  margin: 10px auto;
  background-color: #e0e0e0; /* Light gray background */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

.button-container {
  display: flex;
  gap: 20px;
  justify-content: center;
  margin-top: 30px;
}

/* General button style */
.button {
  padding: 12px 28px;
  font-size: 16px;
  font-weight: 600;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  color: white;
  text-transform: uppercase;
  letter-spacing: 0.8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  background: linear-gradient(135deg, #3a3f47, #2a2f36); /* Elegant gray */
}

.button:disabled {
  background: gray;
  cursor: not-allowed;
  opacity: 0.6;
}

/* Start Button - Deep green */
.start-btn {
  background: linear-gradient(135deg, #2e7d32, #1b5e20);
}

.start-btn:hover {
  background: linear-gradient(135deg, #388e3c, #1e4620);
  transform: translateY(-2px);
}

/* Restart Button - Blue-gray */
.restart-btn {
  background: linear-gradient(135deg, #546e7a, #37474f);
}

.restart-btn:hover {
  background: linear-gradient(135deg, #607d8b, #455a64);
  transform: translateY(-2px);
}

/* Stop Button - Bordeaux red */
.stop-btn {
  background: linear-gradient(135deg, #8e2323, #5e1616);
}

.stop-btn:hover {
  background: linear-gradient(135deg, #a32d2d, #701a1a);
  transform: translateY(-2px);
}

/* Click effect */
.button:active {
  transform: scale(0.95);
}

@media (max-width: 600px) {
  .forest-grid-container {
    width: 95%;
    height: 300px;
  }
  .button-container {
    flex-direction: column;
  }
  .button {
    width: 100%;
    margin-bottom: 10px;
  }
}
</style>
