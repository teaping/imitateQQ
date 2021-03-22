package com.cqgy.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cqgy.bean.User;
import com.cqgy.method.UserDao;

public class LogginginFrame  extends JFrame{
	Timer timer;
	private User objuser;
	public LogginginFrame(User objuser) {
	  this.objuser=objuser;
	  showFrome();
	}
public LogginginFrame() {
	showFrome();
}


//��ʾ����
private void showFrome() {
	JButton btnregister=new JButton(new ImageIcon("image/Longingin_gaitubao_103x36.png"));
	this.add(btnregister);
	btnregister.setBounds(152,270,100,30);//150,300,100,30
	//���ô�������
	this.setTitle("���ڵ���....");
	this.setIconImage(new ImageIcon("Image/qqImage.jpg").getImage());
	//this.setSize(330,300);
	this.setSize(400,340);
	this.setBackground(new Color(255,255,255));//#FFFFFF
	//���ñ���
	ImageIcon img = new ImageIcon("Image/20200608221757_gaitubao_400x270.gif");//���Ǳ���ͼƬ
	JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�
	//this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
	this.getLayeredPane().add(imgLabel,CENTER_ALIGNMENT);
	imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//���ñ�����ǩ��λ��
	Container cp=this.getContentPane();
	cp.setLayout(new BorderLayout());
	((JPanel)cp).setOpaque(false); //ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
	// ������ʾ
	this.setLocationRelativeTo(null);
	// �����С���ܸı�
	 this.setResizable(false);
	 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 
	 
		//ʵ�ֽ�������¼�
		this.addWindowListener(new WindowListener() {
			//�����ʱ����
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("��ȡ����");
				if (timer==null) {
					 timer=new Timer();	
				}
				timer.schedule(task, 2000, 1000000);
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
				int n = JOptionPane.showConfirmDialog(null, "���ڵ���,��ȷ��Ҫ�ر�?", "����",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
				if (n==JOptionPane.YES_OPTION) {
					LoginFrame frame=new LoginFrame(1);
					frame.setVisible(true);
					//System.exit(0);
					if (timer!=null) {
						timer.cancel();
						timer=null;
					}
					 close();
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
		
		
		//ȡ����ť����¼�
		btnregister.addActionListener(e->
		{
			int n = JOptionPane.showConfirmDialog(null, "���ڵ���,��ȷ��Ҫȡ��?", "����",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
			if (n==JOptionPane.YES_OPTION) {
				LoginFrame frame=new LoginFrame(1);
				frame.setVisible(true);
				//System.exit(0);
				if (timer!=null) {
					timer.cancel();
					timer=null;
				}
				 close();
			}
				
		});

		

}




//ִ�ж�ʱ��ҵ��
TimerTask task=new TimerTask() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (objuser!=null) {
			login(objuser);	
		}
	}
};


//�رմ���
public void close() {
this.setVisible(false);
}

//�ͻ�������������͵�¼����
	public void login(User objusre) {
				Map   usermap=new UserDao().login(objusre);
				if (usermap != null) {
					// ��¼�ɹ���ת����
					System.out.println("��¼�ɹ���ת����");
					//�ı�״̬
					objuser.setState("1");
					new UserDao().Changestatu(objuser);
					// ���õ�¼���ڲ��ɼ�
					this.setVisible(false);
					// ���õ�¼���ڿɼ�
					//FriendsFrame frame = new FriendsFrame(usermap);
					//frame.setVisible(true);
					MainFrame frame=new MainFrame(usermap);
					frame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "����ʧ��!");
					close();
					LoginFrame loginFrame=new LoginFrame(1);
					loginFrame.setVisible(true);
				}
		
		}


}
