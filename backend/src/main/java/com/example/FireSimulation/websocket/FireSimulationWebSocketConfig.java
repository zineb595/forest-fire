package com.example.FireSimulation.websocket;


import com.example.FireSimulation.service.FireSimulationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class FireSimulationWebSocketConfig implements WebSocketConfigurer {

    private final FireSimulationWebSocketHandler fireSimulationWebSocketHandler;

    public FireSimulationWebSocketConfig(FireSimulationWebSocketHandler fireSimulationWebSocketHandler) {
        this.fireSimulationWebSocketHandler = fireSimulationWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(fireSimulationWebSocketHandler, "/fire-simulation")
                .setAllowedOrigins("*"); // Allow connections from any frontend

    }
}

