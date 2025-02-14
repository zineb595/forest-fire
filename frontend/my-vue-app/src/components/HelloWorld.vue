<template>
  <div :class="['home-container', { 'dark-mode': isDarkMode }]">
    <div class="overlay">
      <h1>Welcome to the Forest Fire Simulation</h1>
      <button @click="startSimulation" class="start-btn">Here you go!👈</button>
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
import { connectWebSocket } from "../socket/socket";

export default {
  setup() {
    onMounted(() => {
      connectWebSocket(); // Start WebSocket connection when the app is loaded
    });
    const isDarkMode = ref(false); // Dark mode toggle state

    // Get the Vue Router instance to handle routing
    const router = useRouter();

    // Method to navigate to the simulation page
    const startSimulation = () => {
      router.push("/simulation");
    };

    // Toggle between night mode and day mode
    const toggleNightMode = () => {
      isDarkMode.value = !isDarkMode.value; // Change dark mode state
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
/* Basic styling for body */
body {
  height: 100%;
  margin: 0;
  padding: 0;
}

/* Main container styling for the home screen */
.home-container {
  height: 100vh; /* Full screen height */
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url("@/assets/fire-images/fire-night.jpg") no-repeat center center
    fixed; /* Night mode background */
  background-size: cover;
  transition: background 0.5s ease; /* Smooth background transition */
  color: white;
  position: relative;
  text-align: center;
}

/* Dark mode styling for the home screen */
.dark-mode {
  background: url("@/assets/fire-images/fire-day.jpg") no-repeat center center
    fixed; /* Day mode background */
  background-size: cover;
}

/* Overlay styling for content positioning */
.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1; /* Ensures the overlay is above other content */
}

/* Heading styling */
h1 {
  font-size: 2.5rem;
  font-weight: bold;
  position: absolute;
  margin-bottom: 250px; /* Adjusted positioning */
}

/* Start button styling */
.start-btn {
  padding: 20px 40px;
  font-size: 1.5rem;
  background-color: rgba(0, 0, 0, 0.7); /* Semi-transparent background */
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 10px;
  margin-top: 50px;
  transition: background-color 0.3s ease; /* Smooth hover effect */
}

/* Start button hover effect */
.start-btn:hover {
  background-color: rgba(0, 0, 0, 0.9); /* Darker background on hover */
}

/* Night mode toggle button styling */
.night-mode-btn {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%); /* Center button horizontally */
  background: rgba(0, 0, 0, 0.6); /* Semi-transparent background */
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 10px;
}

/* Night mode toggle button hover effect */
.night-mode-btn:hover {
  background: rgba(0, 0, 0, 0.8); /* Darker background on hover */
}
</style>
