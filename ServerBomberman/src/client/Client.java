package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import server.Msg;

public class Client {
	private Socket clientSocket;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    
    public void connect(String ip, int port) {
    	try {
        clientSocket = new Socket(ip, port);
        System.out.println("Client connected to the server");
        
       /*os = new ObjectOutputStream(clientSocket.getOutputStream());
        is = new ObjectInputStream(clientSocket.getInputStream());
        
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter name");
        String name = myObj.nextLine();
        myObj.close();
        Msg message = new Msg();
        message.text = name;
        os.writeObject(message);
        */
        
    	} catch(Exception e) {
    		System.out.println("Couldnt connect to the server.");
    	}
    }
    	
    public void disconnect() {
    	try {
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Couldnt disconnect.");
		}
    } 

}
