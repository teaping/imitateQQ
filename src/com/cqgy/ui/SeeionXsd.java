package com.cqgy.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import org.bson.Document;

import com.cqgy.method.CteataDao;
import com.mongodb.client.MongoCursor;

public class SeeionXsd implements Runnable{
	JTextPane txtMainInfo=null;
	String cteraNames=null;
	 
	public SeeionXsd(JTextPane txtMainInfo, String cteraNames) {
		super();
		this.txtMainInfo = txtMainInfo;
		this.cteraNames = cteraNames;
	}

	public void run(){
		
	
		while (true) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			MongoCursor<Document> mongoCursor = new CteataDao().SelectCtreata(cteraNames);
			String dateseeion = null;
			String nameseeion = null;
			String meesageseeion = null;
			StyledDocument doc = (StyledDocument) txtMainInfo.getDocument();
			Style style = doc.addStyle("StyleName", null);
			String zonghe = null;
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			while (mongoCursor.hasNext()) {
				Document studentDocument = mongoCursor.next();
				dateseeion = format1.format(studentDocument.getDate("date"));
				nameseeion = studentDocument.getString("username");
				meesageseeion = studentDocument.getString("meesage");
				zonghe += dateseeion + nameseeion + "หตฃบ" + meesageseeion + "\n";
//				try {
//					
//					//doc.insertString(doc.getLength(), zonghe, style);
//				} catch (BadLocationException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
			}
			txtMainInfo.setText(zonghe);
		}
	}
	
}
