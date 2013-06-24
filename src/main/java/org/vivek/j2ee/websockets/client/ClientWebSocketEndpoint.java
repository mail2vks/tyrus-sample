package org.vivek.j2ee.websockets.client;

import javax.websocket.*;

public class ClientWebSocketEndpoint extends Endpoint {
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		final RemoteEndpoint.Basic asyncEndPoint = session.getBasicRemote();

		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				System.out.println(message);
			}
		});
	}
}
