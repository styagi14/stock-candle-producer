package com.shailendra.stock.websocket;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebsocketClientEndpoint {
	
	Session userSession= null;
	private MessageHandler messageHandler;
	
	public WebsocketClientEndpoint(URI endpointURI) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("Opening WebSocket");
		this.userSession = userSession;
	}
	
	@OnClose
	public void onClose(Session userSession) {
		System.out.println("Closing Session"); 
		this.userSession = userSession;
	}
	
    @OnMessage	
	public void onMessage(String message) {
		System.out.println("Inside OnMessage");
		this.messageHandler.handleMessage(message); 
	}
    
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public static interface MessageHandler {

        public void handleMessage(String message);
    }
}
