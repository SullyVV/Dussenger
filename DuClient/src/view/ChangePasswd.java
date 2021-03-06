package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import common.Message;
import common.MessageType;
import controller.ManageThread;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChangePasswd extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField first;
	private JTextField second;
	private JButton submit_btn;
	private JButton cancel_btn;
	private String usr;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ChangePasswd(String usr) {
		this.usr = usr;
		setResizable(false);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Type in New Passwd");
		lblNewLabel.setBounds(87, 18, 133, 16);
		contentPane.add(lblNewLabel);
		
		JLabel New_title = new JLabel("New Passwd");
		New_title.setBounds(18, 46, 77, 16);
		contentPane.add(New_title);
		
		JLabel confirm_title = new JLabel("Confirm Passwd");
		confirm_title.setBounds(18, 91, 109, 16);
		contentPane.add(confirm_title);
		
		first = new JPasswordField(15);
		first.setBounds(139, 46, 130, 26);
		contentPane.add(first);
		first.setColumns(10);
		
		second =new JPasswordField(15);
		second.setBounds(139, 86, 130, 26);
		contentPane.add(second);
		second.setColumns(10);
		
		submit_btn = new JButton("Submit");
		submit_btn.setBounds(139, 124, 117, 29);
		submit_btn.addActionListener(this);
		contentPane.add(submit_btn);
		
		cancel_btn = new JButton("Cancel");
		cancel_btn.setBounds(10, 124, 117, 29);
		contentPane.add(cancel_btn);
		cancel_btn.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cancel_btn)
		{
			dispose();
		}
		if(e.getSource() == submit_btn)
		{
			if(first.getText().equals(second.getText()) && first.getText().length() != 0)
			{
				System.out.println("hello");
				Message m = new Message();
				m.setSender(this.usr);
				m.setCon(first.getText());
				m.setMesType(MessageType.change_passwd);
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream
					(ManageThread.getClientConServerThread(usr).getS().getOutputStream());
					oos.writeObject(m);
					dispose();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(contentPane, "Make sure the two password are the same!");
				first.setText("");
				second.setText("");
			}
		}
		
	}
}
