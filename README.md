# forest-fire

# Forest Fire Simulation - Setup and Run

This project simulates the propagation of a forest fire using a backend (Spring Boot) and a frontend (Reactjs). Docker Compose is used to run both the frontend and backend. You need to generate the backend JAR file manually.

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

