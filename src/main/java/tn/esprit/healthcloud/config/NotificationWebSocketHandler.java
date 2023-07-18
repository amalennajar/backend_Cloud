package tn.esprit.healthcloud.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    private final Map<WebSocketSession, Set<Long>> sessions = new ConcurrentHashMap<>();

    public void notifyUser(Long logistiqueId) throws IOException {
        for (WebSocketSession session : sessions.keySet()) {
            Set<Long> logistiqueIds = sessions.get(session);
            if (logistiqueIds.contains(logistiqueId)) {
                session.sendMessage(new TextMessage("Logistique with id " + logistiqueId + " is out of stock!"));
                System.out.println("Logistique with id " + logistiqueId + " is out of stock!");

            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session, new HashSet<>());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Do nothing
    }

    public void addLogistiqueSubscription(WebSocketSession session, Long logistiqueId) {
        sessions.get(session).add(logistiqueId);
    }

    public void removeLogistiqueSubscription(WebSocketSession session, Long logistiqueId) {
        sessions.get(session).remove(logistiqueId);
    }

}
