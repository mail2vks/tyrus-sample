package org.vivek.j2ee.websockets.server;

import javax.websocket.*;
import java.io.IOException;

public class EchoWebSocketEndpoint extends Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		final RemoteEndpoint.Basic endPoint = session.getBasicRemote();

		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				try {
					endPoint.sendText(System.currentTimeMillis() + " " + message);
				} catch (IOException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}
			}
		});


	}
}
