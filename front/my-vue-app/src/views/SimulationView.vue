<script setup lang="ts">
import { computed, onMounted } from "vue";
import { useStore } from "vuex";
import ForestGrid from "../components/ForestGrid.vue";

import { startSimulation, restartSimulation, stopSimulation } from "../socket";
</script>

<template>
  <div>
    <h1>🔥 Forest Fire Simulation</h1>
    <ForestGrid :grid="grid" />
    <div class="button-container">
      <button class="button start-btn" @click="startSimulation">Start</button>
      <button class="button restart-btn" @click="restartSimulation">
        Restart
      </button>
      <button class="button stop-btn" @click="stopSimulation">Stop</button>
    </div>
  </div>
</template>

<style scoped>
/* Container des boutons */
.button-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 30px;
}

/* Style général des boutons */
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
  background: linear-gradient(135deg, #3a3f47, #2a2f36); /* Gris élégant */
}

/* Start Button - Vert profond */
.start-btn {
  background: linear-gradient(135deg, #2e7d32, #1b5e20);
}

.start-btn:hover {
  background: linear-gradient(135deg, #388e3c, #1e4620);
  transform: translateY(-2px);
}

/* Restart Button - Bleu-gris */
.restart-btn {
  background: linear-gradient(135deg, #546e7a, #37474f);
}

.restart-btn:hover {
  background: linear-gradient(135deg, #607d8b, #455a64);
  transform: translateY(-2px);
}

/* Stop Button - Rouge bordeaux */
.stop-btn {
  background: linear-gradient(135deg, #8e2323, #5e1616);
}

.stop-btn:hover {
  background: linear-gradient(135deg, #a32d2d, #701a1a);
  transform: translateY(-2px);
}

/* Effet de clic */
.button:active {
  transform: scale(0.95);
}
</style>
