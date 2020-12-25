package Server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ReceiveAndSendEvent extends Thread{
	InputStream in = null;
	OutputStream out = null;
	DataOutputStream dos;
	private Scanner scanner;
	public ReceiveAndSendEvent(InputStream in, OutputStream out){
		this.in = in;
		this.out = out;
		start(); 
	}

	public void run(){
		scanner = new Scanner(in);
		dos = new DataOutputStream(out);
		while(true){
			int command = scanner.nextInt();
			//System.out.println(command);
			switch(command){
				case -1:
				case -2:
				case -3:
				case -4:{
					try {
						dos.writeInt(command);
						dos.writeInt(scanner.nextInt());
						dos.flush();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
				}	
				case -5:{
					
					try {
		
						dos.writeInt(command);
						dos.writeInt(scanner.nextInt());
						dos.writeInt(scanner.nextInt());
						dos.flush();
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
}




