package com.example.FireSimulation.websocket;

import com.example.FireSimulation.service.FireSimulationService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class FireSimulationWebSocketHandler extends TextWebSocketHandler implements FireSimulationService.GridUpdateListener {
    private final FireSimulationService fireSimulationService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    public FireSimulationWebSocketHandler(FireSimulationService fireSimulationService) {
        this.fireSimulationService = fireSimulationService;
        this.fireSimulationService.setGridUpdateListener(this); // Set THIS WebSocket handler as the listener
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);  // Add new session to the list
        System.out.println("New WebSocket connection established with client: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle WebSocket commands like "start", "stop", "restart"
        String command = message.getPayload().trim();

        switch (command) {
            case "start" -> {
                System.out.println("start" );
                fireSimulationService.startSimulation(); // Start simulation
            }
            case "stop" -> {
                fireSimulationService.stopSimulation(); // Stop simulation
            }
            case "restart" -> {
                fireSimulationService.restartSimulation(); // Restart simulation
            }
            default -> System.out.println("Unknown command received: " + command);
        }
    }

    // This method is called whenever the grid is updated in the service
    @Override
    public void onGridUpdate(int[][] grid) {
        // Send the updated grid to all connected WebSocket clients
        String gridJson;
        try {
            gridJson = objectMapper.writeValueAsString(grid); // Convert grid to JSON
        } catch (IOException e) {
            throw new RuntimeException("Error converting grid to JSON", e);
        }

        // Loop through all connected sessions and send the grid update
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(gridJson)); // Send grid to each client
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // Remove session when closed
    }
}
