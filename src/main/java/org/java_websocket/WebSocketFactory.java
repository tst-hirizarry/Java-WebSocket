package org.java_websocket;

import java.net.Socket;
import java.util.List;

import org.java_websocket.drafts.IDraft;

public interface WebSocketFactory {
	public WebSocket createWebSocket( WebSocketAdapter a, IDraft d, Socket s );
	public WebSocket createWebSocket( WebSocketAdapter a, List<IDraft> drafts, Socket s );

}
