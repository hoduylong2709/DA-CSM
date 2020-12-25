package Client;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveEvent extends Thread{
		private Socket socket= null;
		private Robot robot = null;
		private DataInputStream dis = null;
		public ReceiveEvent(Socket socket, Robot robot){
			this.socket = socket;
			this.robot = robot;
			start(); 
		}

		@Override
		public void run(){
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while(true){
					int command = dis.readInt();
					switch(command){
						case-1:
							robot.mousePress(dis.readInt());
							break;
						case-2:
							robot.mouseRelease(dis.readInt());
							break;
						case-3:
							robot.keyPress(dis.readInt());
							break;
						case-4:
							robot.keyRelease(dis.readInt());
							break;
						case-5:
							robot.mouseMove(dis.readInt(),dis.readInt());
							break;
					}
		
				}
			}catch(IOException ex){
					ex.printStackTrace();
			}
	}
			
}




