package com.windea.study.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/message/{username}")
public class WebSocket {
    private static int onlineCount = 0;
    private static final Map<String, WebSocket> clients = new ConcurrentHashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");
    }

    @OnClose
    public void onClose() throws IOException {
        removeOnlineCount();
        clients.remove(username);
        System.out.println("已关闭");
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        var json = mapper.readValue(message, Map.class);
        var name = json.get("name").toString();
        var msg = json.get("message").toString();
        var to = json.get("to").toString();
        if(Objects.equals(to, "all")) {
            sendMessageAll(msg);
        } else {
            sendMessageTo(msg, to);
        }
        System.out.println("message： " + json);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public void sendMessageAll(String message) throws IOException {
        for(WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void removeOnlineCount() {
        onlineCount--;
    }

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }
}
