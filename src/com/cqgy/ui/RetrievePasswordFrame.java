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
import com.cqgy.method.UserDao;

public class RetrievePasswordFrame  extends JFrame{
	
	
	  //��ʼ���û�ҵ����
	  private UserDao  objuserDao;
	 //��ʼ��
	public   RetrievePasswordFrame() {
		// TODO Auto-generated constructor stub
		CreateFrame();
	}

	
	//��������
    private void CreateFrame() {
    	VerificationCode vcode = new  VerificationCode();
        this.setTitle("�һ�����ҳ��");
        this.setBackground(Color.RED);//���ñ�����ɫ
        vcode.setBounds(295,175,160,18);
        this.setIconImage(new ImageIcon("Image/qqImage.jpg").getImage());
        this.setSize(400,350);
        // ������ʾ
        this.setLocationRelativeTo(null);
        // �����С���ܸı�
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
       //���ñ���
        ImageIcon img = new ImageIcon("Image/20200609134353.gif");//���Ǳ���ͼƬ
        JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//���ñ�����ǩ��λ��
        Container cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel)cp).setOpaque(false); //ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
         JLabel a=new JLabel("��      ��:"); //ʵ����JLabel����
         JLabel b=new JLabel("��      ��:");
         JLabel Confirmationcode=new JLabel("ȷ������:");
         JLabel h=new JLabel("��֤��:  ");
         JTextField c=new JTextField(15);//ʵ�����û����ı���
         JPasswordField d=new JPasswordField(15);//ʵ���������
         JPasswordField inputConfirmationcode=new JPasswordField(15);//ʵ�����û����ı���
         JTextField k=new JTextField(4);//ʵ������֤���
         c.setOpaque(false);
         d.setOpaque(false);
         inputConfirmationcode.setOpaque(false);
         k.setOpaque(false);
         
         d.setEchoChar('*');//������������е�������*��ʾ����
         JButton btnregister=new JButton("�� �� �� ��");
         //#05BAFB
         btnregister.setBackground(new Color(05,186,251));//���õ�¼��ť������ɫ
         //����������ɫ
         btnregister.setForeground(Color.white);
         //����������ʽ
         btnregister.setFont(new java.awt.Font("����", 1, 14));
         Container m=getContentPane();//��ȡһ������
         getContentPane().setBackground(Color.WHITE);//���ô������ɫ
//         ���û����������Jlabel���û���JTextField�ı�������JPasswordField������Լ�ȷ��JButton������ע��JButton��ӵ�container��������                         //
         m.add(a);
         m.add(b);
         m.add(Confirmationcode);
         m.add(c);
         m.add(d);
         m.add(btnregister);
         m.add(inputConfirmationcode);
         m.add(h);
         m.add(k);
         m.add(vcode);
         m.setLayout(null);
//         a��b��c��d��e��f��ʾ��container�����е�λ������
         a.setBounds(50,60,60,18);
         b.setBounds(50,100,60,18);
         Confirmationcode.setBounds(50, 140,60,18);
         c.setBounds(110,60,180,30);
         d.setBounds(110,100,180,30);
         h.setBounds(50,180,60,18);
         inputConfirmationcode.setBounds(110, 140, 180, 30);
         k.setBounds(110,180,180,30);
         btnregister.setBounds(110,250,180,32);//110,100,180,32

             btnregister.addActionListener(e->{
             	// �Ƚ����û�������֤����֤ͨ���ٵ�¼
         		String userId = c.getText();
         		String password = new String(d.getPassword());
         		String confirmpwd=new String(inputConfirmationcode.getPassword());
         		//��1��������֤����
         		if (userId.trim().isEmpty()) {
         			JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��!");
         			return;
         		}
         		if (password.trim().isEmpty()) {
         			JOptionPane.showMessageDialog(null, "���벻��Ϊ��!");
         			return;
         		}
         		if (confirmpwd.trim().isEmpty()) {
         			JOptionPane.showMessageDialog(null, "ȷ�����벻��Ϊ��!");
         			return;
         		}
         		if (!password.equals(confirmpwd)) {
         			JOptionPane.showMessageDialog(null, "������������벻һ��!");
         			return;
 				}
         		
         		//��֤��
         		 //�ж�������֤���Ƿ���ȷ
                 if(k.getText()== null) {
                 	JOptionPane.showMessageDialog(null, "��������֤��!");
         			return;
                 }else if(vcode.getCode() == null) {
                 	 JOptionPane.showMessageDialog(null, "������֤��ʧ��!");
                 	 //����������֤��
                 	 vcode.nextCode();
          			return;
                 }else if(!vcode.getCode() .equals(k.getText())) {
                     JOptionPane.showMessageDialog(null, "�������֤������!");
                     //������֤��
                   //����������֤��
                	 vcode.nextCode();
         			return;
                 }
               
                 //��2����ʼ��ʵ����
                 User objuser=new User();
                 objuser.setUser_id(userId);
                 objuser.setUser_pwd(password);
                 
                 //��3��ʵ��ҵ���һ�����
                 if (objuserDao==null) {
					objuserDao=new UserDao();
				 }
                 
                 if (objuserDao.updateuserPwd(objuser)) {
					//�޸ĳɹ�
                	 JOptionPane.showMessageDialog(null, "�޸ĳɹ�!");
                	 LoginFrame loginframe=new LoginFrame(1);
                	 close();
                	 loginframe.setVisible(true);
				 }else{
					 JOptionPane.showMessageDialog(null, "�޸�ʧ��!");
				 }
                  
                 
         	
             });
             
 			//ʵ�ֽ�������¼�
 			this.addWindowListener(new WindowListener() {
 				//�����ʱ����
 				@Override
 				public void windowOpened(WindowEvent e) {
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
 					int n = JOptionPane.showConfirmDialog(null, "�һ�ʧ��,��ȷ��Ҫ�رյ�ǰ����?", "����",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
 					if (n==JOptionPane.YES_OPTION) {
 						LoginFrame frame=new LoginFrame(1);
 						frame.setVisible(true);
 						//System.exit(0);
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
	}
    
    private void close() {
		this.setVisible(false);
	}
}



