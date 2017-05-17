package eservice;

import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InquiryService {
    private Set<InquiryWebSocket> webSockets;

    public void sendMessage(String data, InquiryWebSocket webSocket) {
        webSocket.sendString(data);
    }

    public void add(InquiryWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(InquiryWebSocket webSocket) {
        webSockets.remove(webSocket);
    }

}
