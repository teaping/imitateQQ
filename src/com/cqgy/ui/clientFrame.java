package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import com.cqgy.qq.Client;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Window.Type;

public class clientFrame extends JFrame {

	private JPanel contentPane;
	//�ļ�·��
	private JTextField textField;
	//mulu:�û���ק��·��
	String mulu;
	JButton connection;
	//������
	JProgressBar progressBar;

	
	    private ClientSocket cs = null;

	    boolean run=true;
	    private String ip = "localhost";// ���óɷ�����IP
	    private int port = 65;

	    private String sendMessage = "Windwos";



	    private boolean createConnection() {
	    	/*
	    	ip=Client.SERVER_IP;
	    	port=Client.SERVER_PORT;
	    	*/
	        cs = new ClientSocket(ip, port);
	        try {
	            cs.CreateConnection();
	            System.out.print("���ӷ������ɹ�!" + "\n");
	            return true;
	        } catch (Exception e) {
	            System.out.print("���ӷ�����ʧ��!" + "\n");
	            return false;
	        }

	    }

	    private void sendMessage() {
	        if (cs == null)
	            return;
	        try {
	            cs.sendMessage(sendMessage);
	        } catch (Exception e) {
	            System.out.print("������Ϣʧ��!" + "\n");
	        }
	    }

	   

	//�رմ���
	private void close() {
		FiletransferMainFraem.client=null;
		 run=false;
		this.setVisible(false);
	}

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public clientFrame() {
		setBackground(Color.BLACK);
		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 connection = new JButton("\u8FDE\u63A5");
		 connection.setFont(new Font("΢���ź�", Font.BOLD, 15));
		connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������֤
				if (textField.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ָ������");
				}else
				{
					try {
						    if(createConnection())
						    {
						    	//������Ϣ
				                sendMessage();
				                connection.setEnabled(false);
				                run=true;
				                //��ȡ��Ϣ
				                getMessage(textField.getText().trim()+"\\");
						    }
			       
			        	

			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				}
			}
		});
		connection.setBounds(159, 197, 93, 23);
		contentPane.add(connection);
		
		JLabel lblNewLabel = new JLabel("\u53E6\u5B58\u4E3A\u76EE\u5F55:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 79, 149, 28);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(26, 117, 374, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		//��ק�¼�
		 new DropTarget(textField,new DropTargetAdapter() {
				
				public void drop(DropTargetDropEvent arg0) {
					clientFrame.this.drop(arg0);
				}
			});
		 
		  progressBar = new JProgressBar();
		  progressBar.setForeground(Color.CYAN);
		  progressBar.setBackground(Color.GREEN);
		 progressBar.setBounds(26, 159, 374, 14);
		 progressBar.setValue(0);
		 progressBar.setStringPainted(true);
		 contentPane.add(progressBar);
	}
	
	
	//�û���ק���ļ�·��
	protected void drop(DropTargetDropEvent arg0) {
		arg0.acceptDrop(3);//���ܵ� û���ͻᱨ�� 3��ʾ�ƶ�or����
		List<?> list=null;
		try {
			list = (List<?>) arg0.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
								//get��ת�õ�		get���ݴ���		���ݵ�ζ����java�ļ��б��ζ��
			File fi=(File)list.get(0);
			mulu=fi.getPath();
			textField.setText(mulu);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
	}
	//��ȡ��Ϣ
	 private void getMessage(String path) {
	        if (cs == null)
	            return;
	        DataInputStream inputStream = null;
	        try {
	            inputStream = cs.getMessageStream();
	        } catch (Exception e) {
	            System.out.print("������Ϣ�������\n");
	            return;
	        }

	        try {
	            //���ر���·�����ļ������Զ��ӷ������˼̳ж�����
	        	String savePath;
	            int bufferSize = 8192;
	            byte[] buf = new byte[bufferSize];
	            int passedlen = 0;
	            long len=0;
	            
	            path+= inputStream.readUTF();
	            savePath=path;
	            System.out.println(savePath);
	            int count=0;
	            //�����ļ��ظ����
		        while (new File(savePath).exists()) {
		        	savePath=path;
		        	++count;
		        	  int index=savePath.indexOf(".");
			        	String frontStrPath=savePath.substring(0,index)+"("+count+")";
			        	String lastStrPath=savePath.substring(index);
			        	savePath=frontStrPath+lastStrPath;
				}
		           
	            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(savePath))));
	            len = inputStream.readLong();
	            
	            System.out.println("�ļ��ĳ���Ϊ:" + len + "\n");
	            System.out.println("��ʼ�����ļ�!" + "\n");
	                    
	            while (run) {
	                int read = 0;
	                if (inputStream != null) {
	                    read = inputStream.read(buf);
	                }
	                passedlen += read;
	                if (read == -1) {
	                    break;
	                }
	                //�����������Ϊͼ�ν����prograssBar���ģ���������Ǵ��ļ������ܻ��ظ���ӡ��һЩ��ͬ�İٷֱ�
	                System.out.println("�ļ�������" +  (passedlen * 100/ len) + "%\n");
	                //��ʾ����
	                progressBar.setValue( (int) (passedlen * 100/ len));
	                fileOut.write(buf, 0, read);
	            }
	            System.out.println("������ɣ��ļ���Ϊ" + savePath + "\n");
	            fileOut.close();
	            JOptionPane.showMessageDialog(null, "�ļ��������");
	            //��ʾ�Ѿ�ʹ��
	            CharFrameTest.count=1;
	            connection.setEnabled(true);
	            close();
	        } catch (Exception e) {
	            System.out.println("������Ϣ����" + "\n");
	            return;
	        }
	    }
	 
	
}
