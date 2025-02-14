# forest-fire

# Forest Fire Simulation - Setup and Run

This project simulates the propagation of a forest fire using a backend (Spring Boot) and a frontend (Vue.js). Docker Compose is used to run both the frontend and backend. You need to generate the backend JAR file manually.

## Prerequisites

Make sure you have the following installed:

- **Docker**: To build and run the Docker containers.
- **Docker Compose**: To manage the containers.
- **Maven**: To build the backend project.

### Steps to Run the Project

1. Clone the Repository to your local machine:

2. Build the Backend JAR

Before running the project, you need to generate the backend JAR file manually.
Make sure Maven is installed on your machine.
Build the backend using Maven:

bash
Copier
mvn clean install >>>> This will generate the FireSimulation-0.0.1-SNAPSHOT.jar file in the target directory.

3. Run Docker Compose
   Now, run Docker Compose to start both the backend and frontend containers:

bash
Copier
docker-compose up --build >>>> This command will:

. Build the Docker images.
. Start both the frontend and backend containers.

4. Access the Application
   Once the containers are up and running, you can access the application:

The frontend will be available at http://localhost:8080.
The backend will be accessible at http://localhost:9090.

5. Stopping the Application
   To stop the Docker containers, run:

bash
Copier
docker-compose down

### Notes

### Every time you make changes to the backend, remember to run mvn clean install again to rebuild the JAR before running Docker Compose.

### Docker will use the JAR file you manually built, so if you forget to rebuild it, Docker might use an outdated version.

🔥 Fire Simulation Backend

This is a Spring Boot backend, which uses WebSockets to communicate with a frontend in real-time.

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

🔥 Fire Simulation Frontend

The Forest Fire Simulation Front-End is a Vue.js application that interfaces with a WebSocket back-end. It visualizes the spread of fire in a forest grid, with the following states:

Empty: Ground (no trees or fire)
Tree: Represents a tree
Fire: Fire burning a tree
Ashes: Ashes left after the fire burns the tree

🛠️ Tech Stack
Vue 3: Framework for building the user interface.
Vuex: State management for handling the grid data and simulation state.
CSS: For styling the grid and buttons.
WebSockets: To receive real-time updates from the back-end service.

🔧 How it Works
When you click "Start", the button becomes disabled.
When you click "Restart", the button is enabled again so you can start another simulation.
The "Stop" button does not re-enable the "Start" button because stopping doesn’t necessarily mean a new simulation is needed.

🚀 Result
Users can interact with the simulation by clicking the Start, Stop, and Restart buttons. The simulation updates every 0.5 seconds, showing how fire spreads and affects the grid.
Start button can only be clicked once per simulation.
Restart button resets it, allowing another simulation.
Smooth and user-friendly experience! 😃
