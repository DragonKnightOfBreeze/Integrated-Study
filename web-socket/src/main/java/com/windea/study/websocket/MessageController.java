package com.windea.study.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MessageController {
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/sendMessage")
    public String sendMessage() throws Exception {
        //这里不能直接new
        var webSocket = WebSocket.getClients().values().iterator().next();
        var message = Map.of("name", "windea", "message", "hello WebSocket!", "to", "windea");
        webSocket.onMessage(mapper.writeValueAsString(message));
        return "index.html";
    }
}
