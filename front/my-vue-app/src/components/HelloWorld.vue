<template>
  <div :class="['home-container', { 'dark-mode': isDarkMode }]">
    <div class="overlay">
      <h1>Welcome to the Forest Fire Simulation</h1>
      <button @click="startSimulation" class="start-btn">
        Start Your Simulation
      </button>
      <button @click="toggleNightMode" class="night-mode-btn">
        {{ isDarkMode ? "Day Mode" : "Night Mode" }}
      </button>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { computed, onMounted } from "vue";
import { useStore } from "vuex";
import { connectWebSocket } from "../socket";

export default {
  setup() {
    onMounted(() => {
      console.log("hhhhhh");

      connectWebSocket(); // Start WebSocket connection on app load
    });
    const isDarkMode = ref(false);

    // Get the Vue Router instance
    const router = useRouter();

    // Method to navigate to the simulation page
    const startSimulation = () => {
      router.push("/simulation");
    };

    // Toggle night mode
    const toggleNightMode = () => {
      isDarkMode.value = !isDarkMode.value;
    };

    // Return data and methods to the template
    return {
      isDarkMode,
      startSimulation,
      toggleNightMode,
    };
  },
};
</script>

<style scoped>
html {
  height: 100%;
}

body {
  height: 100%;
  margin: 0;
  padding: 0;
}

.home-container {
  height: 100vh;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url("@/assets/fire-images/fire-night.jpg") no-repeat center center
    fixed;
  background-size: cover;
  transition: background 0.5s ease;
  color: white;
  position: relative;
  text-align: center;
}

.dark-mode {
  background: url("@/assets/fire-images/fire-day.jpg") no-repeat center center
    fixed;
  background-size: cover;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
}

h1 {
  font-size: 2.5rem;
  font-weight: bold;
  position: absolute;
  margin-bottom: 250px;
}

.start-btn {
  padding: 20px 40px;
  font-size: 1.5rem;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 10px;
  margin-top: 50px;
  transition: background-color 0.3s ease;
}

.start-btn:hover {
  background-color: rgba(0, 0, 0, 0.9);
}

.night-mode-btn {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 10px;
}

.night-mode-btn:hover {
  background: rgba(0, 0, 0, 0.8);
}
</style>
