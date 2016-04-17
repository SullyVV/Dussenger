package view;

import javax.swing.*;

import common.Message;
import common.UserInfo;
import controller.ManageChat;
import controller.ManageThread;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.util.Arrays;  
import controller.*;
public class ClientRegister extends JFrame implements ActionListener{
	//error message frame
	private JFrame errFrame;
	//should not contain %:%
	//set the components for the interface
	JPanel jpm1,jpm2,jpm3,jps;
	JLabel jbln, jpm1_jbl,jpm2_jbl,jpm3_jbl;
	JTextField usr_txt;
    JPasswordField passwd_txt,passwd_confirm;
    JButton register,cancel;
    
    char[] password1;
    char[] password2;
	
	public ClientRegister()
	{
		errFrame=new JFrame();
		//north
		jbln=new JLabel(new ImageIcon("image_material/Register.jpg"));

		//center
        jpm1=new JPanel();
        jpm1_jbl=new JLabel("   Set username     ");
        jpm1_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm1_jbl.setForeground(Color.blue);
        usr_txt=new JTextField(15);
        usr_txt.setFont(new Font("Serif",Font.BOLD,20));
        jpm1.add(jpm1_jbl);
        jpm1.add(usr_txt);

        jpm2=new JPanel();
        jpm2_jbl=new JLabel("   Set password     ");
        jpm2_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm2_jbl.setForeground(Color.blue);
        passwd_txt=new JPasswordField(15);
        passwd_txt.setFont(new Font("Serif",Font.BOLD,20));
        jpm2.add(jpm2_jbl);
        jpm2.add(passwd_txt);
        
        jpm3=new JPanel();
        jpm3_jbl=new JLabel("Confirm password ");
        jpm3_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm3_jbl.setForeground(Color.blue);
        passwd_confirm=new JPasswordField(15);
        passwd_confirm.setFont(new Font("Serif",Font.BOLD,20));
        jpm3.add(jpm3_jbl);
        jpm3.add(passwd_confirm);
        
		//south
		jps=new JPanel();
		register=new JButton("Confirm");
		register.setFont(new Font("Serif", Font.PLAIN, 30));
		register.addActionListener(this);
		cancel=new JButton("  Cancel  ");
		cancel.setFont(new Font("Serif", Font.PLAIN, 30));
		cancel.addActionListener(this);
		jps.add(register);
		jps.add(cancel);
		
		//add the components to the interface
		this.setLayout(new GridLayout(5,1));
		this.add(jbln,"North");
		this.add(jpm1);
		this.add(jpm2);
		this.add(jpm3);
		this.add(jps,"South");
		
		//set  the size, title, location and way to exit of the interface
		this.setSize(510, 500);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int x = (int)screensize.getWidth()/2 - (int)framesize.getWidth()/2;
		int y = (int)screensize.getHeight()/2 - (int)framesize.getHeight()/2;
		this.setLocation(x,y); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Dussenger");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//set the response for JButton jps_jb1 and jps_jb2 under different condition based on the content in JTextField jtf and JPasswordField jpf1,jpf2;
		if(e.getSource() == register){

		}
		if (e.getSource()==register)
		{   
            if(usr_txt.getText().length()==0||passwd_txt.getPassword().length==0)
            {
            	char[] password1 = passwd_txt.getPassword();
            	JOptionPane.showMessageDialog(errFrame,  
                        "The username or password can not be empty, please enter again.",  
                        "Error",  
                        JOptionPane.ERROR_MESSAGE); 
            	//erase the content of the password for security
            	Arrays.fill(password1, '0');  
            }
            else
            {
				char[] password2 = passwd_confirm.getPassword();  
	            if (isPasswordMatched(password2)) 
	            {
	    			try {
	    				
	    				UserInfo info = new UserInfo();
	    				info.setMsg_type(1);
	    				info.setUserId(usr_txt.getText());
	    				info.setPasswd(new String(passwd_txt.getPassword()));
	    				Connection conn = new Connection();
	    				if(conn.register(info))
	    				{
	    					JOptionPane.showMessageDialog(null, "register succeed!");
	    					this.setVisible(false);
	    	            	dispose();
	    	            	new ClientLogin();
	    				}
	    				else
	    				{
	    					//JOptionPane.showMessageDialog(panel_south, "Authentication fail");
	    					JOptionPane.showMessageDialog(errFrame, "register fail, user exist");
	    					System.out.println("register fail");
	    					usr_txt.setText("");
	    					passwd_txt.setText("");
	    					passwd_confirm.setText("");
	    				}
	    			} catch (Exception e1) {
	    				e1.printStackTrace();
	    				// TODO: handle exception
	    			}

	            }
	            else
	            {
	                JOptionPane.showMessageDialog(errFrame,  
	                        "The passwords entered are not the same, please enter again.",  
	                        "Password Error",  
	                        JOptionPane.ERROR_MESSAGE);  
	            }  
	            //erase the content of the password for security
	            Arrays.fill(password2, '0');  
            }
        }

		if (e.getSource()==cancel)
		{
			dispose();
			new ClientLogin();
			
		}
	}

	private boolean isPasswordMatched(char[] password) {
        boolean isMatched = true;  
        char[] correctPassword = passwd_txt.getPassword();  
        //check if the content in JPasswordField jpf1 and jpf2 are matched
        if (password.length != correctPassword.length) {  
            isMatched = false;  
        } else {  
            isMatched = Arrays.equals(password, correctPassword);  
        }  
  
        //erase the content of the password for security
        Arrays.fill(correctPassword, '0');  
  
        return isMatched;  
    } 



}
