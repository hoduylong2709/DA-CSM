package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;

	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				if (clientSocket.isConnected()) {
					ClientConnection connectionThread = new ClientConnection(clientSocket);
					connectionThread.start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server(5321);
	}

}
