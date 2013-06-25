package org.vivek.j2ee.websockets.client;

import javax.websocket.*;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ClientWebSocketEndpoint extends Endpoint {
	@Override
	public void onOpen(final Session session, EndpointConfig config) {

		try {
			session.getBasicRemote().sendText("Joining");
		} catch (IOException e) {
			e.printStackTrace();
		}

		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				System.out.println(message);
			}

			public void onMessage(PongMessage pongMessage) {
				System.out.println(pongMessage);
			}
		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				ByteBuffer buffer = ByteBuffer.allocate(1);
				buffer.put((byte) 0xFF);
				try {
					session.getBasicRemote().sendPing(buffer);
					Thread.sleep(2000);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
