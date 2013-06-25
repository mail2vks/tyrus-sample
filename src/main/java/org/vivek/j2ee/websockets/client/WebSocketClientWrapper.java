package org.vivek.j2ee.websockets.client;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class WebSocketClientWrapper {

	public static void main(String args[]) throws IOException {
		runClient();
	}

	private static void runClient() throws IOException {

		ClientManager client = ClientManager.createClient();
		Session session = null;
		final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
		try {
			session = client.connectToServer(ClientWebSocketEndpoint.class, cec, new URI("ws://localhost:8025/websocket/echo"));

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter text to send or type exit");
			while (scanner.hasNext()) {
				String input = scanner.nextLine();
				if (!"exit".equals(input)) {
					session.getAsyncRemote().sendText(input);
				} else {
					break;
				}
			}

		} catch (DeploymentException | URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close(new CloseReason(CloseReason.CloseCodes.GOING_AWAY, "Bye"));
		}
	}
}
