package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.cqgy.method.InfoDao;

public class Avatarchange extends JFrame {
	private String id;
	int nums = 0;
	String toux = null;
	String toux1=null;

	public Avatarchange(String id) throws HeadlessException {
		super();
		List<JLabel> lab = new ArrayList<JLabel>();
		this.id = id;
		
		setSize(500, 300);
		JPanel panlea = new JPanel();
		panlea.setLayout(new GridLayout(14, 3, 10, 5));// 3行2列
		JScrollPane pane = new JScrollPane(panlea);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		for (int i = 1; i <= 105; i++) {
			Icon icon = new ImageIcon("./src/resource/img/" + i + ".jpg");
			JLabel lb = new JLabel(icon);
			lb.setText("" + i);
			lab.add(lb);
			lb.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					nums++;
//					if (nums % 2 != 0) {
						if (toux == null) {
							lb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							toux = lb.getText();
							toux1=toux;
						} else {
							lb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							toux = lb.getText();
							for (int j = 0; j < lab.size(); j++) {
								if (toux1.equals(lab.get(j).getText())) {
									lab.get(j).setBorder(null);
									toux1=null;
									break;
								}
							}
							toux1=toux;
//						}

						// ctreateList.add(lblFriend.getToolTipText());
						// friendUserNames.add(friendUserName);
					}
//					else {
//						lb.setBorder(null);
//						toux=null;
//					}

					
				}
			});
			lb.setVerticalTextPosition(JLabel.BOTTOM);// 靠上
			lb.setHorizontalTextPosition(JLabel.CENTER);// 文字水平对齐方式居中
			panlea.add(lb);
		}
		getContentPane().add(pane,BorderLayout.CENTER);
		JPanel panle=new JPanel();
		JButton qx=new JButton("取消");
		qx.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				
			}
		});

		JButton qr=new JButton("确认");
		qr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean sd=new InfoDao().UpdateAvatarch(toux,id);
				if(sd){
					String iconFile = String.format("/resource/img/toux.jpg");
					MainFrame.HeaderImg.setIcon(new ImageIcon("./src/resource/img/" + toux + ".jpg"));
					dispose();
				}
			}
		});
		
		panle.add(qx);
		panle.add(qr);
		getContentPane().add(panle,BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public Avatarchange() throws HeadlessException {
		super();
	}
	
	private void cons(){
		setDefaultCloseOperation(Avatarchange.DISPOSE_ON_CLOSE);
	}

//	public static void main(String[] args) {
//		new Avatarchange("12312");
//	}

}
