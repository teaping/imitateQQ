package com.cqgy.ui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import org.json.JSONObject;
import com.cqgy.bean.*;
import com.cqgy.qq.Client;
import com.cqgy.common.ReaderFileString;
import com.cqgy.common.SaveFileString;
import com.cqgy.common.WriteErrorLog;
import com.cqgy.method.UserDao;



public class LoginFrame  extends JFrame{
	    //������������ϵ�ҵ���߼���
	   UserDao objuserDao;
	   //����ʵ�����
	   User objuser;
	   int tage=0;
		// ��������Ҫ�����
		JLabel jlbTop, jlbImg, jlbReg, jlbPsw;
		JPanel jp; // ���ڽ���ײ�����
		JTextField jtf;//�˺�
		JPasswordField jpf;//����
		JCheckBox box1, box2;
		JButton jbLogin;// ��½��ť
		//������ʱ��
        Timer timer;
	public LoginFrame() {
		// ������
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		createFrame();
	}
	
	public LoginFrame(int tage) {
		// ������
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		this.tage=tage;
		createFrame();
	}

	private void  createFrame() {
		//����
		jlbTop=new JLabel(new ImageIcon("image/Loginhader1.gif"));
		//�в�
		jp=new JPanel();
		jp.setLayout(null);//����layout����
		jlbImg=new JLabel(new ImageIcon("image/qqHead.jpg"));
		jlbImg.setBounds(25,10,70,70);
		jtf=new JTextField(15);
		jtf.setBounds(110,10,180,30);
		jpf=new JPasswordField(15);
		jpf.setBounds(110,35,180,30);
		box1=new JCheckBox("��ס����");
		box1.setBounds(110,75,90,15);
		box2=new JCheckBox("�Զ���½");
		box2.setBounds(210,75,90,15);

		jlbReg=new JLabel("ע���˺�");
		jlbReg.setBounds(300,15,60,15);
		//jlbReg.setFont(MyFont.myFont);
		jlbReg.setForeground(Color.blue);
		jlbPsw=new JLabel("�һ�����");
		jlbPsw.setBounds(300,50,60,15);
		//jlbPsw.setFont(MyFont.myFont);
		jlbPsw.setForeground(Color.blue);

		//�ϲ�
		jbLogin=new JButton(new ImageIcon("image/login2.png"));
		jbLogin.setBounds(110,100,180,32);

		//������
		jp.add(jlbImg);
		jp.add(jtf);
		jp.add(jpf);
		jp.add(box1);
		jp.add(box2);
		jp.add(jlbReg);
		jp.add(jlbPsw);
		jp.add(jbLogin);

		//��������JFrame
		this.add(jlbTop,"North");
		this.add(jp,"Center");
		//���ô�������
		this.setTitle("QQ�������");
		this.setIconImage(new ImageIcon("Image/qqImage.jpg").getImage());
		this.setSize(400,350);
		// ������ʾ
		this.setLocationRelativeTo(null);
		// �����С���ܸı�
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//this.setVisible(true);
		// ע���¼��ť�¼�������
		jbLogin.addActionListener(e -> {
			if (objuserDao.Loginstatus(jtf.getText())) {
				JOptionPane.showMessageDialog(null, "���û�������!"); 
				return;
			}else{
				//���е���
				login();	
			}
				
		});

		//Ϊע����ӵ���¼�
			// ע���˺�����¼�
			jlbReg.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseExited(MouseEvent e) {
							jlbReg.setText("\u6ce8\u518c\u8d26\u53f7");
						}
						@Override
						public void mouseEntered(MouseEvent e) {
							jlbReg.setCursor(new Cursor(Cursor.HAND_CURSOR));
							jlbReg.setText("<html><u>\u6ce8\u518c\u8d26\u53f7</u><html>");
						}
						@Override
						public void mouseClicked(MouseEvent e) {
						    //ʵ����ע��ҳ��
							RegistrationFrame register=new RegistrationFrame();
							//�رյ�ǰ����
							 close();
							 //��ʾע��ҳ��
							 register.setVisible(true);	
						}
	        
					});
			
			
			//qq�һ���������¼�
			jlbPsw.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mouseExited(MouseEvent e) {
					jlbPsw.setText("\u627E\u56DE\u5BC6\u7801");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					jlbPsw.setCursor(new Cursor(Cursor.HAND_CURSOR));
					jlbPsw.setText("<html><u>\u627E\u56DE\u5BC6\u7801</u><html>");
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				    //ʵ�����һ��������
					RetrievePasswordFrame  retrievePasswordFrame=new RetrievePasswordFrame();
					//�رյ�ǰ����
					 close();
					 //��ʾ�һ��������
					retrievePasswordFrame.setVisible(true);
				}
				
				
				
			});
			

		    //����û�ѡ���Զ����룬����ס���뵥ѡ��ѡ
			box2.addActionListener(e -> {
				if (box2.isSelected()) {
					box1.setSelected(true);	
				}
			});
			
			//��ס����
			box1.addActionListener(e->{
				
				if (!box1.isSelected()) {
					
					box1.setSelected(false);
					if (box2.isSelected()) {
						box2.setSelected(false);	
					}
				}
				
				
			});
			
				
			
		
		
			
			
			
				//ʵ�ֽ�������¼�
				this.addWindowListener(new WindowAdapter(){
					//�����ʱ����
					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						System.out.println("��ý���");
						//��ȡ״̬
						if (objuserDao==null) {
							objuserDao=new UserDao();
						}
						ArrayList<String> readerState=objuserDao.readerAutoLoginState();
						if (readerState!=null) {
							if (readerState.get(0).trim().equals("1")) {
								box1.setSelected(true);
							}else{
								box1.setSelected(false);
							}
							
							if (readerState.get(1).trim().equals("1")) {
								box2.setSelected(true);
							}else {
								box2.setSelected(false);
							}
						}
					
						
							autoLoginFrame();	
						
			
					}
					
					private void autoLoginFrame() {
						// TODO Auto-generated method stub
						//���Ϊ�Զ�����״̬���е���
						if (box2.isSelected()) {
							System.out.println(Client.SERVER_PORT+";"+Client.SERVER_IP);
							//��ȡ�˺ź�����
							ArrayList<String> readerUserInfo=objuserDao.readerUserInfo();
							if (readerUserInfo.size()>0) {
								jtf.setText(readerUserInfo.get(0));
								jpf.setText(readerUserInfo.get(1));	
								//�ж��û��Ƿ����
							if (objuserDao.Loginstatus(jtf.getText())) {
								JOptionPane.showMessageDialog(null, "���û�������!"); 
								return;
							}else{
								//�����Զ�����
								if (tage==0) {
									if (timer==null) {
										 timer=new Timer();	
									}
									if (objuser==null) {
										objuser=new User();	
										objuser.setUser_id(readerUserInfo.get(0));
										objuser.setUser_pwd(readerUserInfo.get(1));
									}
									timer.schedule(task, 100, 1000000);
									}	
								}
							}
						}
					}

					//����ͼ���¼�
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

						
					}
					
					
					//���廹ԭ����
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					//����ͣ���¼�
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					//���ڹر�
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						int n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�رյ�ǰ����?", "����",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
						if (n==JOptionPane.YES_OPTION) {
							System.exit(0);
						}
					}
					
					//�Ѿ��ر�
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					//���弤���¼�
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
	}
	
	
	public void close(){
		this.setVisible(false);
	}
	
	//ִ�ж�ʱ���ϵ�ҵ��
	TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (objuser!=null) {
				LogginginFrame  logginginFrame=new LogginginFrame(objuser);
				//�����û�����״̬
				SaveState();
				logginginFrame.setVisible(true);
				close();	
			}
		}
	};
	
	private void dataValidation(String userId,String password){
		        //������֤����
				if (userId.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��!");
					return;
				}
				if (password.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��!");
					return;
				}
	}
	

	// �ͻ�������������͵�¼����
	public void login() {
		    if (objuser==null) {
			      objuser=new User();	
		     }
		    if (objuserDao==null) {
				objuserDao=new UserDao();	
			}
		          // �Ƚ����û�������֤����֤ͨ���ٵ�¼
				dataValidation(jtf.getText(),new String(jpf.getPassword()));
					objuser.setUser_id(jtf.getText());
					objuser.setUser_pwd(new String(jpf.getPassword()));
				Map usermap=objuserDao.login(objuser);
				if (usermap != null) {
					// ��¼�ɹ���ת����
					System.out.println("��¼�ɹ���ת����");
					//�����û�״̬
					objuser.setState("1");//��Ϊ����
					objuserDao.Changestatu(objuser);
					//�����û������ϵ�ѡ���״̬
					SaveState();
					// ���õ�¼���ڲ��ɼ�
					this.setVisible(false);
					//FriendsFrame frame = new FriendsFrame(usermap);
					MainFrame frame=new MainFrame(usermap);
					frame.setVisible(true);
					
				}else{
					
					 JOptionPane.showMessageDialog(null, "�˺Ż����벻��ȷ!");
				}
		
		}
	
	
	//����״̬
	private void  SaveState() {
		boolean saveUserInfoState=false,autoLogin=false;
		//�ж��û��Ƿ����˱�������
		if (box1.isSelected()) {
			//��������
			objuserDao.SaveUserInfo(objuser.getUser_id(), objuser.getUser_pwd());
			saveUserInfoState=true;
		}
		
		//�ж��û��Ƿ����˵�ѡ���ϵ�״̬
		if (box2.isSelected()) {
			autoLogin=true;
		}
		//�����û������ϵ�״̬
		objuserDao.Savestate(saveUserInfoState,autoLogin);
	}
	
	

	
	
	
	
	
}
