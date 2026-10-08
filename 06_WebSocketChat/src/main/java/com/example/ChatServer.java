package com.example;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatServer {

    private static final Set<Session> clients =
            new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        System.out.println("Client connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {

        System.out.println("Message from "
                + session.getId() + ": " + message);

        for (Session client : clients) {
            try {
                client.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Client disconnected: "
                + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("WebSocket error: "
                + throwable.getMessage());
    }
}