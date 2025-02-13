package com.example.FireSimulation.websocket;

import com.example.FireSimulation.service.FireSimulationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket configuration for the fire simulation.
 * This class enables WebSocket support and registers the WebSocket handler.
 */
@Configuration
@EnableWebSocket
public class FireSimulationWebSocketConfig implements WebSocketConfigurer {

    private final FireSimulationWebSocketHandler fireSimulationWebSocketHandler;

    /**
     * Constructor to inject the WebSocket handler.
     * The handler that processes WebSocket connections.
     */
    public FireSimulationWebSocketConfig(FireSimulationWebSocketHandler fireSimulationWebSocketHandler) {
        this.fireSimulationWebSocketHandler = fireSimulationWebSocketHandler;
    }

    /**
     * Registers the WebSocket handler for handling connections.
     * The WebSocket handler registry.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(fireSimulationWebSocketHandler, "/fire-simulation")
                .setAllowedOrigins("*"); // Allow connections from any frontend
    }
}
