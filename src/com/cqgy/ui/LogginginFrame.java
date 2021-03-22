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


//显示窗体
private void showFrome() {
	JButton btnregister=new JButton(new ImageIcon("image/Longingin_gaitubao_103x36.png"));
	this.add(btnregister);
	btnregister.setBounds(152,270,100,30);//150,300,100,30
	//设置窗体属性
	this.setTitle("正在登入....");
	this.setIconImage(new ImageIcon("Image/qqImage.jpg").getImage());
	//this.setSize(330,300);
	this.setSize(400,340);
	this.setBackground(new Color(255,255,255));//#FFFFFF
	//设置背景
	ImageIcon img = new ImageIcon("Image/20200608221757_gaitubao_400x270.gif");//这是背景图片
	JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
	//this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
	this.getLayeredPane().add(imgLabel,CENTER_ALIGNMENT);
	imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
	Container cp=this.getContentPane();
	cp.setLayout(new BorderLayout());
	((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
	// 居中显示
	this.setLocationRelativeTo(null);
	// 窗体大小不能改变
	 this.setResizable(false);
	 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 
	 
		//实现界面监听事件
		this.addWindowListener(new WindowListener() {
			//窗体打开时激活
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("获取焦点");
				if (timer==null) {
					 timer=new Timer();	
				}
				timer.schedule(task, 2000, 1000000);
			}
			
			//窗体图标事件
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

				
			}
			
			
			//窗体还原触发
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			//窗体停用事件
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			//正在关闭
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "正在登入,你确定要关闭?", "警告",JOptionPane.YES_NO_OPTION); //返回值为0或1
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
			
			//已经关闭
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			//窗体激活事件
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
		
		//取消按钮点击事件
		btnregister.addActionListener(e->
		{
			int n = JOptionPane.showConfirmDialog(null, "正在登入,你确定要取消?", "警告",JOptionPane.YES_NO_OPTION); //返回值为0或1
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




//执行定时器业务
TimerTask task=new TimerTask() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (objuser!=null) {
			login(objuser);	
		}
	}
};


//关闭窗体
public void close() {
this.setVisible(false);
}

//客户端向服务器发送登录请求
	public void login(User objusre) {
				Map   usermap=new UserDao().login(objusre);
				if (usermap != null) {
					// 登录成功调转界面
					System.out.println("登录成功调转界面");
					//改变状态
					objuser.setState("1");
					new UserDao().Changestatu(objuser);
					// 设置登录窗口不可见
					this.setVisible(false);
					// 设置登录窗口可见
					//FriendsFrame frame = new FriendsFrame(usermap);
					//frame.setVisible(true);
					MainFrame frame=new MainFrame(usermap);
					frame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "登入失败!");
					close();
					LoginFrame loginFrame=new LoginFrame(1);
					loginFrame.setVisible(true);
				}
		
		}


}
