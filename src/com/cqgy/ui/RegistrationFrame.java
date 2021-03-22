package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cqgy.bean.User;
import com.cqgy.common.VerificationCode;
import com.cqgy.common.WriteErrorLog;
import com.cqgy.method.UserDao;;



public class RegistrationFrame  extends JFrame{
	public RegistrationFrame() {
		CreateFrame();
	}

    
    private void CreateFrame() {
    	VerificationCode vcode = new  VerificationCode();
        this.setSize(400,350);//设计窗体的大小
        this.setTitle("注册页面");
        this.setBackground(Color.RED);//设置背景颜色
         JLabel name=new JLabel("昵        称:"); 
         JLabel a=new JLabel("账      号:"); //实例化JLabel对象
         JLabel b=new JLabel("密      码:");
         //JLabel g=new JLabel("忘记用户名/密码？");
         JLabel Confirmationcode=new JLabel("确认密码:");
         JLabel h=new JLabel("验证码:  ");
         JTextField c=new JTextField(15);//实例化用户名文本框
         JTextField inputName=new JTextField(15);
         JPasswordField d=new JPasswordField(15);//实例化密码框
         JPasswordField inputConfirmationcode=new JPasswordField(15);//实例化用户名文本框
         JTextField k=new JTextField(4);//实例化验证码框
         c.setOpaque(false);
         d.setOpaque(false);
         inputConfirmationcode.setOpaque(false);
         k.setOpaque(false);
         inputName.setOpaque(false);
         
         d.setEchoChar('*');//将输入密码框中的密码以*显示出来
         JButton btnregister=new JButton(new ImageIcon("image/logon.png"));
        // btnregister.setBackground(Color.YELLOW);//设置登录按钮字体颜色
         this.setVisible(true);//使窗体可视化
         Container m=getContentPane();//获取一个容器
         getContentPane().setBackground(Color.WHITE);//设置窗体填充色
//         将用户名、密码的Jlabel和用户名JTextField文本框、密码JPasswordField密码框以及确定JButton、快速注册JButton添加到container容器里面                         //
         m.add(a);
         m.add(name);
         m.add(b);
         m.add(Confirmationcode);
         m.add(c);
         m.add(d);
         m.add(btnregister);
         m.add(inputConfirmationcode);
         m.add(h);
         m.add(k);
         m.add(vcode);
         m.add(inputName);
         m.setLayout(null);
//         a、b、c、d、e、f显示在container容器中的位置坐标
         a.setBounds(50,60,60,18);
         name.setBounds(50,20,60,18);
         b.setBounds(50,100,60,18);
         Confirmationcode.setBounds(50, 140,60,18);
         c.setBounds(110,60,180,30);
         inputName.setBounds(110,20,180,30);
         d.setBounds(110,100,180,30);
         h.setBounds(50,180,60,18);
         inputConfirmationcode.setBounds(110, 140, 180, 30);
         k.setBounds(110,180,180,30);
         btnregister.setBounds(110,250,180,32);//110,100,180,32
         //vcode.setBounds(280,160,180,30);
         vcode.setBounds(295,175,160,18);
         this.setIconImage(new ImageIcon("Image/qqImage.jpg").getImage());
         this.setSize(400,350);
         // 居中显示
         this.setLocationRelativeTo(null);
         // 窗体大小不能改变
         this.setResizable(false);
         //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         
        //设置背景
         ImageIcon img = new ImageIcon("Image/registerherader1_gaitubao_467x350_gaitubao_400x350.gif");//这是背景图片
         JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
         this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
         imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
         Container cp=this.getContentPane();
         cp.setLayout(new BorderLayout());
         ((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
             btnregister.addActionListener(e->{
             	// 先进行用户输入验证，验证通过再登录
             	String inname=inputName.getText();
         		String userId = c.getText();
         		String password = new String(d.getPassword());
         		String confirmpwd=new String(inputConfirmationcode.getPassword());
         		String ico="qqHead";
         		//数据验证部分
         		if (inname.trim().isEmpty()) {
         			JOptionPane.showMessageDialog(null, "昵称不能为空!");
         			return;
 				}
         		if (userId.trim().isEmpty()) {
         			JOptionPane.showMessageDialog(null, "账号不能为空!");
         			return;
         		}
         		if (password.trim().isEmpty()) {
         			JOptionPane.showMessageDialog(null, "密码不能为空!");
         			return;
         		}
         		if (confirmpwd.trim().isEmpty()) {
         			JOptionPane.showMessageDialog(null, "确认密码不能为空!");
         			return;
         		}
         		if (!password.equals(confirmpwd)) {
         			JOptionPane.showMessageDialog(null, "二次输入的密码不一致!");
         			return;
 				}
         		
         		//验证码
         		 //判断输入验证码是否正确
                 if(k.getText()== null) {
                 	JOptionPane.showMessageDialog(null, "请输入验证码!");
         			return;
                 }else if(vcode.getCode() == null) {
                 	 JOptionPane.showMessageDialog(null, "生成验证码失败!");
                 	 //重新生成验证码
                 	 vcode.nextCode();
          			return;
                 }else if(!vcode.getCode() .equals(k.getText())) {
                     JOptionPane.showMessageDialog(null, "输入的验证码有误!");
                     //更新验证码
                   //重新生成验证码
                	 vcode.nextCode();
         			return;
                 }
                 

         		//封装对象
         		User objuser=new User(userId,password,inname,ico,"0");
         		UserDao userDAO=new UserDao();
         		try{
         			//进行注册
             		if (userDAO.Regisration(objuser)) {
             			JOptionPane.showMessageDialog(null, "注册成功!");
             			this.setVisible(false);
             			LoginFrame loginNewFrame=new LoginFrame(1);
             			//重新显示
             			loginNewFrame.setVisible(true);
     				}
         		}catch(Exception ex){
         			WriteErrorLog.SaveError("在class RegistrationFrame下的public RegistrationFrame()中出现异常,异常信息:"+ex.toString());
         			JOptionPane.showMessageDialog(null, "注册失败出现异常!\n异常信息:"+ex.toString());
         		}
         		
         		
             });
             
 			//实现界面监听事件
 			this.addWindowListener(new WindowListener() {
 				//窗体打开时激活
 				@Override
 				public void windowOpened(WindowEvent e) {
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
 					int n = JOptionPane.showConfirmDialog(null, "用户未注册,你确定要关闭当前窗体?", "警告",JOptionPane.YES_NO_OPTION); //返回值为0或1
 					if (n==JOptionPane.YES_OPTION) {
 						LoginFrame frame=new LoginFrame(1);
 						frame.setVisible(true);
 						//System.exit(0);
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
	}


	public void close() {
		this.setVisible(false);
	}
}
