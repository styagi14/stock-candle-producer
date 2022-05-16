package com.shailendra.stock.websocket;

import java.net.URI;

public class StockDataWebSocket {
	
	public static void main(String[] args) {
		try {
            final WebsocketClientEndpoint clientEndPoint = 
            		new WebsocketClientEndpoint(new URI("ws://b-mocks.dev.app.getbaraka.com:9989"));
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });
            Thread.sleep(5000);
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
	}

}
