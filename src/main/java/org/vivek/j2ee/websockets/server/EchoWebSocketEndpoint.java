package org.vivek.j2ee.websockets.server;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@ServerEndpoint(value = "/echo")
public class EchoWebSocketEndPoint {

	private static List<Session> connectedSessions = new LinkedList<>();

	@OnMessage
	public void getMessage(final String message, final Session session) {
		if (!connectedSessions.contains(session)) {
			connectedSessions.add(session);
			broadcastToAll("user" + session.getId() + " joined");
		} else {
			broadcastToAll("user" + session.getId() + " says > " + message);
		}

	}

	private void broadcastToAll(final String s) {
		for (Session session : connectedSessions) {
			try {
				session.getBasicRemote().sendText(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnClose
	public void closeConnectionHandler(Session session, CloseReason closeReason) {
		connectedSessions.remove(session);
		broadcastToAll("user" + session.getId() + " quit");
	}

}
