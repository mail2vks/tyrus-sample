package org.vivek.j2ee.websockets.client;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketClientWrapper {

	public static void main(String args[]) {
		runClient();
	}

	private static void runClient() {

		ClientManager client = ClientManager.createClient();
		final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
		try {
			client.connectToServer(ClientWebSocketEndpoint.class, cec, new URI("ws://127.0.0.1:8025/websockets/tests"));
		} catch (DeploymentException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (URISyntaxException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

	}
}
