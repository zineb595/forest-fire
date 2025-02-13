import { reactive } from "vue";

export const state = reactive({
  connected: false,
  grid: [] as number[][],
});

export let socket: WebSocket | null = null;

export const connectWebSocket = () => {
  if (socket) return; // Prevent multiple connections

  socket = new WebSocket("ws://localhost:9090/fire-simulation");

  socket.onopen = () => {
    state.connected = true;
    console.log(" Connected to WebSocket");
  };

  socket.onmessage = (event) => {
    try {
      state.grid = JSON.parse(event.data); // Update grid from server
    } catch (error) {
      console.error("Error parsing WebSocket message:", error);
    }
  };

  socket.onclose = () => {
    state.connected = false;
    socket = null;
    console.log(" WebSocket Disconnected");
  };
};

// 🟢 Send "start" command to server
export const startSimulation = () => {
  if (socket && socket.readyState === WebSocket.OPEN) {
    socket.send("start");
  } else {
    console.error("WebSocket is not connected.");
  }
};

// 🔄 Send "restart" command to server
export const restartSimulation = () => {
  if (socket && socket.readyState === WebSocket.OPEN) {
    socket.send("restart");
  } else {
    console.error("WebSocket is not connected.");
  }
};

// 🔄 Send "restart" command to server
export const stopSimulation = () => {
  if (socket && socket.readyState === WebSocket.OPEN) {
    socket.send("stop");
  } else {
    console.error("WebSocket is not connected.");
  }
};
