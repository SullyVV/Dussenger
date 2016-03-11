package com.server;
import com.database.*;
import java.net.*;
import java.io.*;
import java.util.*;
import com.test.*;

import common.*;
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("server");
		//initialize();
		//relation rl = new relation();
		
		try {
			String ss=InetAddress.getLocalHost().getHostAddress();
			System.out.println(ss);
			ServerSocket socket = new ServerSocket(6541);
			while(true)
			{
				Socket s = socket.accept();
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				UserInfo u=(UserInfo)ois.readObject();
				System.out.println(u.getUserId()+ " : " + u.getPasswd());
				ServerThread thread=new ServerThread(s);
				ManageThread.addThread(u.getUserId(), thread);
				thread.start();
					
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}
	
	
	
	
	public static void initialize()
	{
		init  initial = new init();
		initial.initDatabase();
		InfoAdd info = new InfoAdd();
	}
}
