<<<<<<< HEAD
"Frontend directory" 
=======
# forest-fire

🔥 Fire Simulation Backend
This is a Spring Boot backend for a forest fire simulation, which uses WebSockets to communicate with a frontend in real-time.

🚀 Features
Generates a random forest grid with trees and empty spaces.
Simulates fire propagation based on a probability factor.
Supports WebSocket connections to send grid updates to the frontend.
Allows start, stop, and restart commands via WebSocket messages.
🛠️ Tech Stack
Spring Boot (Backend Framework)
WebSockets (Real-time communication)
Jackson (JSON serialization)
📂 Project Structure
service/FireSimulationService.java → Handles fire simulation logic.
websocket/FireSimulationWebSocketHandler.java → Manages WebSocket connections.
config/SimulationConfig.java → Holds simulation settings.
🔧 How It Works
The simulation initializes a grid where:
0 = Empty space
1 = Tree
2 = Fire
3 = Ashes
Fire spreads randomly based on the fire propagation probability.
The WebSocket sends grid updates to connected clients.
Users can control the simulation via WebSocket commands:
"start" → Starts the fire propagation.
"stop" → Stops the simulation.
"restart" → Resets the grid and restarts the simulation.
>>>>>>> be405b818b8b63438bfc97d6f36f88cedcc1533d
