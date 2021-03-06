package view;

import javax.swing.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import common.*;
import controller.*;


import java.awt.*;
import java.util.*;
/*
 * this class is for the main interface after user login Dussenger
 */
public class MainInterface extends JFrame implements ActionListener{
	private JTextField tf_search;
	private HashMap friend;
	private JLabel img;
	public HashMap getFriend() {
		return friend;
	}
	public void setFriend(HashMap friend) {
		this.friend = friend;
	}

	public JScrollPane getFriendlist() {
		return friendlist;
	}
	public void setFriendlist(JScrollPane friendlist) {
		this.friendlist = friendlist;
	}
	public JPanel getFriendPanel() {
		return friendPanel;
	}
	public void setFriendPanel(JPanel friendPanel) {
		this.friendPanel = friendPanel;
	}
	private JScrollPane friendlist;
	private JPanel friendPanel;
	
	
	private JTextField tf_NoChat;
	private JPanel search;
	
	private JPanel chatBox;
	public JPanel getChatBox() {
		return chatBox;
	}
	public void setChatBox(JPanel chatBox) {
		this.chatBox = chatBox;
	}
	private boolean hasChat = false;
	private JTextField tf_friendinfo;
	private JTextArea oldTalk;
	private JTextArea currTalk;
	private JButton searchbtn;
	private JLabel tf_Name;
	private JLabel tf_ID;
	private JButton btnSend;
	
	private JMenuItem add_to_group;
	private JMenuItem delete_friend;
	
	
	private JButton friend_btn;
	
	private JButton message_btn;
	
	
	private JButton file_btn;
	
	public JButton getFile_btn() {
		return file_btn;
	}
	public void setFile_btn(JButton file_btn) {
		this.file_btn = file_btn;
	}
	public JButton getMessage_btn() {
		return message_btn;
	}
	public void setMessage_btn(JButton message_btn) {
		this.message_btn = message_btn;
	}
	private JButton change_passwd;
	public JButton getFriend_btn() {
		return friend_btn;
	}
	public void setFriend_btn(JButton friend_btn) {
		this.friend_btn = friend_btn;
	}
	public JButton getGroup_btn() {
		return group_btn;
	}
	public void setGroup_btn(JButton group_btn) {
		this.group_btn = group_btn;
	}
	private JButton group_btn;
	private JButton create_group;
	
	private ArrayList<String> relation;
	public void setRelation(ArrayList<String> relation) {
		this.relation = relation;
	}
	//0 -> now chat with friend
	//1 -> now chat with group
	private int now_chat;
	
	public int getNow_chat() {
		return now_chat;
	}
	public void setNow_chat(int now_chat) {
		this.now_chat = now_chat;
	}
	private ArrayList<String> OnlineFriend;
	/**
	 * Create the frame.
	 */
	private String usr;
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	private String target = "%%";
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
//	public static void main(String[] args) {
//		MainInterface main = new MainInterface("c");
//
//	}
	public MainInterface(String usr) {
		this.now_chat = 0;
		this.usr = usr;
		setTitle("Dussenger");
		setResizable(false);
		getContentPane().setBackground(UIManager.getColor("ToolBar.light"));
		getContentPane().setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 539);
		getContentPane().setLayout(null);
		//arrange search panel
		search = new JPanel();
		search.setBounds(6, 6, 288, 155);
		getContentPane().add(search);
		search.setLayout(null);
		
		
		
		
		
		tf_search = new JTextField();
		tf_search.setBounds(6, 104, 90, 38);
		search.add(tf_search);
		tf_search.setColumns(5);
		
		searchbtn = new JButton("Find Friend");
		searchbtn.setBounds(100, 104, 90, 38);
		//searchbtn.setHorizontalAlignment(SwingConstants.LEADING);
		search.add(searchbtn);
		
		message_btn = new JButton("Message");
		message_btn.setBounds(190,104,90,38);
		message_btn.addActionListener(this);
		search.add(message_btn);
		
		tf_Name = new JLabel();
		tf_Name.setBounds(80, 6, 100, 38);
		search.add(tf_Name);
		tf_Name.setText(usr);
		
		change_passwd = new JButton("Passwd");
		change_passwd.setBounds(190,6,90,38);
		search.add(change_passwd);
		change_passwd.addActionListener(this);
		
		friend_btn = new JButton("Friend List");
		friend_btn.setBounds(6, 54, 90, 38);
		search.add(friend_btn);
		friend_btn.addActionListener(this);
		
		group_btn = new JButton("Group List");
		group_btn.setBounds(100, 54, 90, 38);
		group_btn.addActionListener(this);
		search.add(group_btn);
		
		create_group = new JButton("More Group");
		create_group.setBounds(190,54,90,38);
		create_group.addActionListener(this);
		search.add(create_group);
		
		
		/*
		tf_ID = new JLabel();
		tf_ID.setBounds(80, 54, 196, 38);
		search.add(tf_ID);
		tf_ID.setText("TBD");
		*/
		
		JLabel lbl_Name = new JLabel("Name:");
		lbl_Name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Name.setBounds(7, 6, 61, 38);
		search.add(lbl_Name);
		
		file_btn = new JButton("File Transfer");
		file_btn.setForeground(UIManager.getColor("Button.light"));
		file_btn.setBounds(200,358,130,29);
		file_btn.addActionListener(this);
		
		
		btnSend = new JButton("send");
		btnSend.setForeground(UIManager.getColor("Button.light"));
		btnSend.setBounds(336, 358, 75, 29);
		btnSend.addActionListener(this);
		/*
		JLabel lbl_ID = new JLabel("ID:");
		lbl_ID.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ID.setBounds(6, 54, 61, 38);
		search.add(lbl_ID);
		*/
		searchbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String friendID =tf_search.getText();
				tf_search.setText("");
				if(!search(friendID)){
					System.out.println("not found");
					new NoFriend(usr);
				}
			}		
		});
		relation = RelationManage.getRelation();
		if(relation == null)
			relation = new ArrayList();
		int total_relation = relation.size() < 7? 7:relation.size();
		OnlineFriend = RelationManage.getOnlineFriend();
		int cnt_onlineFriend = OnlineFriend.size();
		friendPanel = new JPanel();		
		friendPanel.setLayout(new GridLayout(total_relation, 1, 4, 4));
		friendlist = new JScrollPane(friendPanel);
		
		friend = RelationManage.getFriendList();
		
		
		for(int i = 0; i < relation.size(); i++){
			String friend_i = relation.get(i);

			friend.put(friend_i, new JLabel(friend_i));

			
			((Component) friend.get(friend_i)).addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2)
					{
						String friendID = ((JLabel)e.getSource()).getText();
						System.out.println("wish to talk to friend " + friendID);
						newChatBox(friendID);
						
					}
					if(e.getButton() == MouseEvent.BUTTON3)
					{
						JPopupMenu textMenu = new JPopupMenu();
						add_to_group = new JMenuItem("Add to Group");
						add_to_group .addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new AddToGroup(usr,friend_i);
							}
						});
						delete_friend = new JMenuItem("Delete Friend");
						delete_friend .addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								Message reply = new Message();
								reply.setMesType(MessageType.delete_friend);
								reply.setSender(usr);
								reply.setGetter(friend_i);
								ObjectOutputStream oos;
								try {
									oos = new ObjectOutputStream(ManageThread.getClientConServerThread(usr).getS().getOutputStream());
									oos.writeObject(reply);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						});
						textMenu.add(add_to_group);
						textMenu.add(delete_friend);
	                    textMenu.show(e.getComponent(), e.getX(),  
	                            e.getY()); 
						
					}
				}

			});
			friendPanel.add((Component) friend.get(friend_i));
		}
		
		
		for(int i = 0; i < cnt_onlineFriend; i++) {
			((Component) friend.get(OnlineFriend.get(i))).setForeground(Color.GREEN);
		}
		
		
		friendlist.setBounds(6, 166, 288, 345);
		getContentPane().add(friendlist);
		
		chatBox = new JPanel();
		chatBox.setBounds(300, 6, 417, 505);
		getContentPane().add(chatBox);
		chatBox.setLayout(null);
		
		//before any talk, chat box is blank
		
//		tf_NoChat = new JTextField();
//		tf_NoChat.setEditable(false);
//		tf_NoChat.setBackground(SystemColor.window);
//		tf_NoChat.setHorizontalAlignment(SwingConstants.CENTER);
//		tf_NoChat.setText("No Current Chat");
//		tf_NoChat.setBounds(105, 214, 220, 65);
		
		img=new JLabel(getImageIcon("image_material/duke_bluedevil.png",417,505));
		//img=new JLabel(new ImageIcon("image_material/duke_bluedevil.png"));
		img.setBounds(0, 0, 417, 505);
		chatBox.add(img);
		//tf_NoChat.setColumns(10);
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
		if(this.now_chat == 0)
			tf_friendinfo = new JTextField("Friend: " + friendID);
		else
			tf_friendinfo = new JTextField("Group: " + friendID);
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
		if(this.now_chat == 0)
			chatBox.add(file_btn);
		chatBox.revalidate();
		chatBox.repaint();
		if(ManageChat.getCon(friendID) != null &&this.now_chat == 0){
			oldTalk.append(ManageChat.getCon(friendID));
			ManageChat.remove(friendID);
			
		}
		if(ManageChat.getGPCon(friendID) != null && this.now_chat ==1)
		{
			oldTalk.append(ManageChat.getGPCon(friendID));
			ManageChat.removeGPCon(friendID);
		}
		
	}
	
	public boolean search(String friendID){
		if(friend.containsKey(friendID))
		{
			
			newChatBox(friendID);
			return true;
		}
		return false;
	}

	public void showMessage(Message m)
	{
		if(this.now_chat == 0)
		{
			String info=m.getSender()+" to "+m.getGetter()+" : "+m.getCon()+"\r\n";
			this.oldTalk.append(info);
			ManageChat.remove(m.getSender());
		}
		else if(this.now_chat == 1)
		{
			String info = m.getStarter() + " to Group : " + m.getCon() + "\r\n";
			this.oldTalk.append(info);
			ManageChat.removeGPCon(m.getSender());
		}
	}
	 public static ImageIcon getImageIcon(String path, int width, int height) {
		  if (width == 0 || height == 0) {
		   return new ImageIcon(path);
		  }
		  ImageIcon icon = new ImageIcon(path);
		  icon.setImage(icon.getImage().getScaledInstance(width, height,
		    Image.SCALE_DEFAULT));
		  return icon;
		 }
	public void updateFriendList(){
		
		Message m = new Message();
		m.setGetter(this.usr);
		m.setSender(this.usr);
		m.setMesType(MessageType.getRelation);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream
			(ManageThread.getClientConServerThread(this.usr).getS().getOutputStream());
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateGroup(){
		Message m = new Message();
		m.setGetter(this.usr);
		m.setSender(this.usr);
		m.setMesType(MessageType.getGroup);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream
			(ManageThread.getClientConServerThread(this.usr).getS().getOutputStream());
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateOnlineFriendList(String newUser) {
		if (relation.contains(newUser)) {
			//System.out.println("new user is: " + newUser);
			Object m = friend.get(newUser);
			((Component) m).setForeground(Color.GREEN);
			
		}
	}
	
	public void updateOfflineFriendList(String newUser) {
		if (relation.contains(newUser)) {
			System.out.println("logoff user is: " + newUser);
			Object m = friend.get(newUser);
			((Component) m).setForeground(Color.BLACK);
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSend){
			try {
				
				Message m = new Message();
				if(this.getNow_chat() == 1)
				{
					m.setMesType(MessageType.groupForward);
				}
				else
				{
					m.setMesType(MessageType.default_type);
				}
				m.setGetter(this.target);
				
				m.setSender(this.usr);
				
				m.setCon(currTalk.getText());
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageThread.getClientConServerThread(this.usr).getS().getOutputStream());
				oos.writeObject(m);
				currTalk.setText("");
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}
		}
		if(e.getSource() == friend_btn)
		{

			updateFriendList();
		}
		if(e.getSource() == create_group)
		{
			new CreateGroup(usr);	
		}
		if(e.getSource() == group_btn)
		{
			System.out.println("hello");
			updateGroup();
		}
		if(e.getSource() == change_passwd)
		{
			new ChangePasswd(this.usr);
		}
		if(e.getSource() == message_btn)
		{
			message_btn.setForeground(Color.black);
			new MessageTable(this.usr);
		}
		if(e.getSource() == file_btn)
		{
			new FileFolder(this.usr,this.target);
		}
		
	}
	
	
	
	
	
}

