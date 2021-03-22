package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Window.Type;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;


public class SendFileFrame extends JFrame {

	private JPanel contentPane;
	  private int port = 65;


	boolean run=false;
	//��ȡ�ļ��ı�
	JTextArea textField;
	//�ļ�·��
	private  String mulu="";
	JLabel connecttionState;
	JButton sendFile;
	/**
	 * Create the frame.
	 */
	public SendFileFrame() {
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6587\u4EF6\u8DEF\u5F84: (\u628A\u6587\u4EF6\u62D6\u5230\u8FD9\u91CC)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 159, 245, 24);
		contentPane.add(lblNewLabel);
		
		textField = new JTextArea();
		textField.setEditable(false);
		textField.setBounds(10, 196, 414, 24);
		textField.setColumns(10);
		contentPane.add(textField);
		
        new DropTarget(textField,new DropTargetAdapter() {
			
			public void drop(DropTargetDropEvent arg0) {
				SendFileFrame.this.drop(arg0);
			}
		});
		
        sendFile = new JButton("\u53D1\u9001");
        sendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������֤
				if (mulu.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�뽫Ҫ������ļ��Ϸŵ�ָ��������");
				
				}else
				{
					sendFile.setEnabled(false);
					
					run=true;
					//��ʼ��������
					start(port,mulu);//����˿���ʹ�õ��ǹ̶�ֵ
					//��֤�ͷ����Ƿ����豸������
					//System.out.println("�����˸İ�ť");
				}
			}
		});
        sendFile.setBounds(331, 228, 93, 23);
		contentPane.add(sendFile);
		
		JLabel lblNewLabel_1 = new JLabel("\u5F53\u524D\u8FDE\u63A5\u72B6\u6001:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(58, 73, 149, 28);
		contentPane.add(lblNewLabel_1);
		
		 connecttionState = new JLabel("\u65E0\u8FDE\u63A5...");
		 connecttionState.setForeground(Color.WHITE);
		connecttionState.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		connecttionState.setBounds(202, 73, 86, 24);
		contentPane.add(connecttionState);
	}

	//��ȥ�û���ק���ļ�·��
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
	
	 //���ļ�
    void start(int port,String filePath) {
        Socket s = null;
        try {
            ServerSocket ss = new ServerSocket(port);
            while (run) {
                // ѡ����д�����ļ�
                File fi = new File(filePath);

                System.out.println("�ļ�����:" + (int) fi.length());

                // public Socket accept() throws
                // IOException���������ܵ����׽��ֵ����ӡ��˷����ڽ�������֮ǰһֱ������
                //�ȴ��Է�����
                connecttionState.setText("�ȴ��ͷ�����������...");
                System.out.println("�ȴ��ͷ�����������...");
                s = ss.accept();
                //���ӳɹ�
                //System.out.println("����socket����");
                connecttionState.setText("����socket����");
                DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                dis.readByte();

                DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
                DataOutputStream ps = new DataOutputStream(s.getOutputStream());
                //���ļ��������ȴ����ͻ��ˡ�����Ҫ������������ƽ̨�������������Ĵ�������Ҫ�ӹ���������Բμ�Think In Java 4th�����ֳɵĴ��롣
                ps.writeUTF(fi.getName());
                ps.flush();
                ps.writeLong((long) fi.length());
                ps.flush();

                int bufferSize = 8192;
                byte[] buf = new byte[bufferSize];

                while (true) {
                    int read = 0;
                    if (fis != null) {
                        read = fis.read(buf);
                    }

                    if (read == -1) {
                        break;
                    }
                    ps.write(buf, 0, read);
                }
                ps.flush();
                // ע��ر�socket����Ŷ����Ȼ�ͻ��˻�ȴ�server�����ݹ�����
                // ֱ��socket��ʱ���������ݲ�������                
                fis.close();
                s.close();  
                connecttionState.setText("�ļ��������");
                System.out.println("�ļ��������");
                JOptionPane.showMessageDialog(null, "�ļ��������");
                textField.setText("");
                CharFrameTest.count=1;
                sendFile.setEnabled(true);
                break;
            }
            
            close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void close() {
    	FiletransferMainFraem.sendFileFrame=null;
    	run=false;
    	this.setVisible(false);
	}
	
  
    

	
}
