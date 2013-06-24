package org.vivek.j2ee.websockets.server;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebSocketServerWrapper {

	public static void main(String args[]) {
		runServer();
	}

	private static void runServer() {

		Server server = new Server(EchoWebSocketEndpoint.class);
		try {
			server.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please press a key to stop the server.");
			reader.readLine();
		} catch (DeploymentException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} finally {
			server.stop();
		}

	}
}
