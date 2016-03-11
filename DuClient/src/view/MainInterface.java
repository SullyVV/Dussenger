package view;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.util.ArrayList;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.Color;

import javax.swing.UIManager;

import common.Message;
import common.MessageType;
import common.UserInfo;
import controller.Connection;
import controller.ManageChat;
import controller.ManageThread;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;
/*
 * this class is for the main interface after user login Dussenger
 */
public class MainInterface extends JFrame implements ActionListener{
	private JTextField tf_search;
	private JLabel[] friend;
	private int[] friendArray;
	private JScrollPane friendlist;
	private JTextField tf_NoChat;
	private JPanel search;
	private JPanel friendPanel;
	private JPanel chatBox;
	private boolean hasChat = false;
	private JTextField tf_friendinfo;
	private JTextArea oldTalk;
	private JTextArea currTalk;
	private JButton searchbtn;
	private JTextField tf_Name;
	private JTextField tf_ID;
	private JButton btnSend;
	/**
	 * Create the frame.
	 */
	private String usr;
	private String target;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public MainInterface(String usr) {
		this.usr = usr;
		setTitle("Dussenger");
		setResizable(false);
		getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		getContentPane().setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 539);
		getContentPane().setLayout(null);
		//arrange search panel
		search = new JPanel();
		search.setBounds(6, 6, 282, 155);
		getContentPane().add(search);
		search.setLayout(null);
		
		tf_search = new JTextField();
		tf_search.setBounds(6, 104, 166, 45);
		search.add(tf_search);
		tf_search.setColumns(5);
		
		searchbtn = new JButton("Search Friend");
		searchbtn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		searchbtn.setBounds(184, 105, 92, 45);
		searchbtn.setHorizontalAlignment(SwingConstants.LEADING);
		search.add(searchbtn);
		
		tf_Name = new JTextField();
		tf_Name.setBounds(80, 6, 196, 38);
		search.add(tf_Name);
		tf_Name.setColumns(10);
		
		tf_ID = new JTextField();
		tf_ID.setBounds(80, 54, 196, 38);
		search.add(tf_ID);
		tf_ID.setColumns(10);
		
		JLabel lbl_Name = new JLabel("Name:");
		lbl_Name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Name.setBounds(7, 6, 61, 38);
		search.add(lbl_Name);
		
		btnSend = new JButton("send");
		btnSend.setForeground(UIManager.getColor("Button.light"));
		btnSend.setBounds(336, 358, 75, 29);
		btnSend.addActionListener(this);
		JLabel lbl_ID = new JLabel("ID:");
		lbl_ID.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ID.setBounds(6, 54, 61, 38);
		search.add(lbl_ID);
		searchbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String friendID =tf_search.getText();
				if(!search(friendID)){
					System.out.println("not found");
					new friendNotFound();
				}
			}		
		});
		friendPanel = new JPanel();		
		friendPanel.setLayout(new GridLayout(50, 1, 4, 4));
		friendlist = new JScrollPane(friendPanel);
		friend = new JLabel[50];
		friendArray = new int[50];
		for(int i = 0; i < 50; i++){
			friendArray[i] = i;
			friend[i] = new JLabel(""+i);
			friend[i].addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2){
						String friendID = ((JLabel)e.getSource()).getText();
						System.out.println("wish to talk to friend " + friendID);
						newChatBox(friendID);
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel curr = (JLabel) e.getSource();
					curr.setForeground(Color.BLUE);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					JLabel curr = (JLabel) e.getSource();
					curr.setForeground(Color.BLACK);
				}
			});
			friendPanel.add(friend[i]);
		}
		
		friendlist.setBounds(6, 166, 282, 345);
		getContentPane().add(friendlist);
		
		chatBox = new JPanel();
		chatBox.setBounds(300, 6, 417, 505);
		getContentPane().add(chatBox);
		chatBox.setLayout(null);
		
		//before any talk, chat box is blank
		tf_NoChat = new JTextField();
		tf_NoChat.setEditable(false);
		tf_NoChat.setBackground(SystemColor.window);
		tf_NoChat.setHorizontalAlignment(SwingConstants.CENTER);
		tf_NoChat.setText("No Current Chat");
		tf_NoChat.setBounds(105, 214, 220, 65);
		chatBox.add(tf_NoChat);
		tf_NoChat.setColumns(10);
		//ManageChat.addView("1", this);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
				public void windowClosing(java.awt.event.WindowEvent e) { 
						System.out.println("window close");
						Socket s = ManageThread.getClientConServerThread(usr).getS();
						try {
							ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
							Message end = new Message();
							end.setMesType(MessageType.logout);
							end.setSender(usr);
							oos.writeObject(end);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						System.exit(0); 
					} 
				}); 
		this.setVisible(true);
	}
	//when a friend is selected, chat box of that friend is opened
	public void newChatBox(String friendID){
		this.target = friendID;
		
		chatBox.removeAll();
		hasChat = true;
		tf_friendinfo = new JTextField("friend: " + friendID);
		tf_friendinfo.setHorizontalAlignment(SwingConstants.CENTER);
		tf_friendinfo.setBounds(6, -2, 411, 45);
		tf_friendinfo.setEditable(false);
		chatBox.add(tf_friendinfo);
		tf_friendinfo.setColumns(10);
		
		oldTalk = new JTextArea();
		oldTalk.setBounds(6, 51, 405, 307);
		oldTalk.setEditable(false);
		chatBox.add(oldTalk);
		
		currTalk = new JTextArea();
		currTalk.setBounds(6, 387, 405, 112);
		chatBox.add(currTalk);
		
		chatBox.add(btnSend);
		chatBox.revalidate();
		chatBox.repaint();
		if(ManageChat.getCon(friendID) != null){
			oldTalk.append(ManageChat.getCon(friendID));
			ManageChat.remove(friendID);
			
		}
		
	}
	public boolean search(String friendID){
		for(int i = 0; i < friendArray.length; i++){
			if(friendID.equals(Integer.toString(friendArray[i]))){
				newChatBox(friendID);
				return true;
			}
		}
		return false;
	}
	public void showMessage(Message m)
	{
		String info=m.getSender()+" to "+m.getGetter()+" :"+m.getCon()+"\r\n";
		this.oldTalk.append(info);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSend){
			try {
				Message m = new Message();
				m.setGetter(this.target);
				m.setSender(this.usr);
				m.setCon(currTalk.getText());
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageThread.getClientConServerThread(this.usr).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}
		}
		
	}
	
	
	
	
	
}

