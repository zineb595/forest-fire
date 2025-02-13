package com.example.FireSimulation.websocket;

import com.example.FireSimulation.service.FireSimulationService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket handler for managing connections and communication between
 * the fire simulation service and clients.
 */
@Component
public class FireSimulationWebSocketHandler extends TextWebSocketHandler implements FireSimulationService.GridUpdateListener {

    private final FireSimulationService fireSimulationService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON serializer
    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>(); // Thread-safe session set

    /**
     * Constructor to inject the FireSimulationService and set this handler as a grid update listener.
     */
    public FireSimulationWebSocketHandler(FireSimulationService fireSimulationService) {
        this.fireSimulationService = fireSimulationService;
        this.fireSimulationService.setGridUpdateListener(this); // Set THIS WebSocket handler as the listener
    }

    /**
     * Called when a new WebSocket connection is established.
     * Adds the session to the active sessions and initializes the grid.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);  // Add new session to the list
        System.out.println("New WebSocket connection established with client: " + session.getId());
        fireSimulationService.initializeGrid(); // Initialize the grid when a client connects
    }

    /**
     * Handles incoming WebSocket messages from clients.
     * Processes commands such as "start", "stop", and "restart".
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Extract command from the received message
        String command = message.getPayload().trim();

        // Process the command
        switch (command) {
            case "start" -> {
                fireSimulationService.startSimulation(); // Start the fire simulation
            }
            case "stop" -> {
                fireSimulationService.stopSimulation(); // Stop the fire simulation
            }
            case "restart" -> {
                fireSimulationService.initializeGrid(); // Reinitialize the grid
                try {
                    Thread.sleep(500); // Pause to allow sending the reinitialized grid before restarting
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                fireSimulationService.restartSimulation(); // Restart the simulation
            }
            default -> System.out.println("Unknown command received: " + command); // Log unrecognized commands
        }
    }

    /**
     * Called whenever the grid is updated in the simulation service.
     * Sends the updated grid state to all connected WebSocket clients.
     */
    @Override
    public void onGridUpdate(int[][] grid) {
        String gridJson;
        try {
            gridJson = objectMapper.writeValueAsString(grid); // Convert grid to JSON
        } catch (IOException e) {
            throw new RuntimeException("Error converting grid to JSON", e);
        }

        // Send the updated grid to all active WebSocket sessions
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(gridJson)); // Send JSON message to the client
                } catch (IOException e) {
                    e.printStackTrace(); // Log any errors during message sending
                }
            }
        }
    }

    /**
     * Called when a WebSocket connection is closed.
     * Removes the session from the active session list.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // Remove session when closed
    }
}
