package com.test;

/**
 * 
 * @author Mao
 *
 */
import java.net.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyServer extends JFrame implements ActionListener{
	JScrollPane jsp = null;
	JTextArea jta = null;
	JPanel jp1 = null;
	JTextField jtf = null;
	JButton jb = null;
	
	PrintWriter pw = null; //sent msg to client
	 
	public static void main(String[] args) {
		MyServer ms = new MyServer();

	}
	public MyServer(){
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		
		jtf = new JTextField(10);
		jb = new JButton("Send");
		jb.addActionListener(this);
		jp1 = new JPanel();	
		jp1.add(jtf);
		jp1.add(jb);
		
		this.add(jsp, "Center");
		this.add(jp1, "South");
		this.setSize(300, 200);
		this.setTitle("Let'sChat--Server");
		this.setVisible(true);
		
		
		try {
			//Server listener
			ServerSocket ss = new ServerSocket(9988);
			// waiting client
			Socket s = ss.accept(); //ready to accept
			InputStreamReader isr= new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			//send message to client
//java.io.PrintWriter.PrintWriter(OutputStream out, boolean autoFlush)
			pw = new PrintWriter(s.getOutputStream(), true);
			
			while(true){
				// read from the client
				String msg= br.readLine();
				jta.append("Client: "+msg+"\n");
						
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/*
	 * event handler of "Send" button
	 */
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource()==jb){
			String msgOut = jtf.getText();
			//also shows in the jta as chat history
			jta.append("Server: "+ msgOut+"\n");
			//send jtf's text to client
			pw.println(msgOut);
			//clear the jtf
			jtf.setText("");
			
		}
	}
	
}
