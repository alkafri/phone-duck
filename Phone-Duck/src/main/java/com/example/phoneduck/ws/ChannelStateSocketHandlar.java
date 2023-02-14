package com.example.phoneduck.ws;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.phoneduck.model.Channel;
import com.example.phoneduck.model.ChannelStateDetails;



@Component
public class ChannelStateSocketHandlar extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //broadcast(message.getPayload());
    }

    public void broadcast(String chatChannel, Channel oldState, Channel newState) {
        ChannelStateDetails details = new ChannelStateDetails(oldState, newState);
        broadcastJson(chatChannel, details);
    }

    public void broadcastJson(String chatChannel, Object object) {
        Gson gson = new Gson();
        broadcast(chatChannel, gson.toJson(object));
    }

    public void broadcast(String chatChannel, String message) {
        try {
            for (WebSocketSession webSession : sessions) {
                String state = webSession.getHandshakeHeaders().getFirst("channel-state");
                String channelList = webSession.getHandshakeHeaders().getFirst("channel-list");
                if(chatChannel.equals(state) || chatChannel.equals(channelList)) {
                    webSession.sendMessage(new TextMessage(message));
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("We Opened new Channel");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("This channel is removed");
    }
}

