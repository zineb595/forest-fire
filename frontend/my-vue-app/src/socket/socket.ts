import { reactive } from "vue";

// Define the reactive state object
export const state = reactive({
  connected: false, // Boolean to track WebSocket connection status
  grid: [] as number[][], // Grid to store the simulation state (2D array of numbers)
});

// Declare a WebSocket variable that can be null initially
export let socket: WebSocket | null = null;

// Function to connect to the WebSocket server
export const connectWebSocket = () => {
  if (socket) return; // Prevent multiple connections if socket already exists

  // Initialize the WebSocket connection to the server
  socket = new WebSocket("ws://localhost:9090/fire-simulation");

  // Handle successful WebSocket connection
  socket.onopen = () => {
    state.connected = true;
    console.log(" Connected to WebSocket");
  };

  // Handle incoming messages from the WebSocket server
  socket.onmessage = (event) => {
    try {
      state.grid = JSON.parse(event.data); // Parse and update the grid from server message
    } catch (error) {
      console.error("Error parsing WebSocket message:", error);
    }
  };

  // Handle WebSocket disconnection
  socket.onclose = () => {
    state.connected = false; // Set connected state to false when disconnected
    socket = null;
    console.log(" WebSocket Disconnected");
  };
};

// Send "start" command to the server
export const startSimulation = () => {
  if (socket && socket.readyState === WebSocket.OPEN) {
    // if WebSocket is open
    socket.send("start");
  } else {
    console.error("WebSocket is not connected.");
  }
};

// Send "restart" command to the server
export const restartSimulation = () => {
  if (socket && socket.readyState === WebSocket.OPEN) {
    socket.send("restart");
  } else {
    console.error("WebSocket is not connected.");
  }
};

// Send "stop" command to the server
export const stopSimulation = () => {
  if (socket && socket.readyState === WebSocket.OPEN) {
    socket.send("stop");
  } else {
    console.error("WebSocket is not connected.");
  }
};
