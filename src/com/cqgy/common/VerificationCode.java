package com.cqgy.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JComponent;
/*
 * ��֤��
 */
public class VerificationCode extends JComponent implements MouseListener{
	  private String codes;  //�Զ����ɵ���֤��

	    private int width, height = 40;  //������֤��߶ȡ����

	    private int codesLength = 4;  //���ô��볤��

	    private Random random = new Random(); //�������ֵķ���

	    public VerificationCode() {
	        width = this.codesLength * 16 + (this.codesLength - 1) * 10; //������֤�볤�����ÿ��
	        setPreferredSize(new Dimension(width, height));  //���ñ�����С
	        setSize(width, height);  //������֤�볤�ȺͿ��
	        this.addMouseListener(this);
	        setToolTipText("����ɸ�����֤��");
	    }
	  //�õ����ɵ���֤��
	    public int getCodesLength() {
	        return codesLength;
	    }


	    //������֤��ĳ���
	    public void setCodesLength(int codeLength) {
	        if(codesLength < 4) {
	            this.codesLength = 4;
	        } else {
	            this.codesLength = codeLength;
	        }

	    }

	    public String getCode() {
	        return codes;
	    }


	       //����֤������������ɫ
	    public Color getRandColor(int min, int max) {

	        if (min > 255)
	            min = 255;
	        if (max > 255)
	            max = 255;
	        int red = random.nextInt(max - min) + min;
	        int green = random.nextInt(max - min) + min;
	        int blue = random.nextInt(max - min) + min;
	        return new Color(red, green, blue);
	    }
	       // ������֤���������ֻ���ĸ��ʲô
	    protected String generateCode() {
	        char[] codes = new char[this.codesLength];
	        for (int i = 0, len = codes.length; i < len; i++) {
	            if (random.nextBoolean()) {
	                codes[i] = (char) (random.nextInt(10) + 48);
	            } else {
	                codes[i] = (char) (random.nextInt(26) + 97);
	            }
	        }
	        this.codes = new String(codes);
	        return this.codes;
	    }


	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if(this.codes == null || this.codes.length() != this.codesLength) {  //�ж����ɵ���֤���Ƿ�Ϊ�ջ򳬳�����
	            this.codes = generateCode();
	        }
	        width = this.codesLength * 16 + (this.codesLength - 1) * 10;
	        super.setSize(width, height);  //�ӿ�ʹ�ã���֤�������С
	        super.setPreferredSize(new Dimension(width, height));//�ӿ�ʹ�ã���֤�뱳����С
	        Font mFont = new Font("Arial", Font.BOLD | Font.ITALIC, 25);  //��������������С
	        g.setFont(mFont);  //���ö���
	        //���Ƴ���֤��ı����ľ�������
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(getRandColor(200, 250));
	        g2d.fillRect(0, 0, width, height);
	        g2d.setColor(getRandColor(180, 200));
	        g2d.drawRect(0, 0, width - 1, height - 1);
	        //���Ƴ���֤�뱳������
	        int i = 0, len = 150;
	        for (; i < len; i++) {
	            int x = random.nextInt(width - 1);
	            int y = random.nextInt(height - 1);
	            int x1 = random.nextInt(width - 10) + 10;
	            int y1 = random.nextInt(height - 4) + 4;
	            g2d.setColor(getRandColor(180, 200));
	            g2d.drawLine(x, y, x1, y1);
	        }



	        //���Ƴ���֤��ľ�����ĸ
	        i = 0; len = this.codesLength;
	        FontMetrics fm = g2d.getFontMetrics();
	        int base = (height - fm.getHeight())/2 + fm.getAscent();
	        for(;i<len;i++) {
	            int b = random.nextBoolean() ? 1 : -1;
	            g2d.rotate(random.nextInt(10)*0.01*b);
	            g2d.setColor(getRandColor(20, 130));
	            g2d.drawString(codes.charAt(i)+"", 16 * i + 10, base);
	        }
	    }

	    //��һ����֤��
	    public void nextCode() {
	        generateCode();
	        repaint();
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {

	        nextCode();
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
	        // TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	        // TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	        // TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	        // TODO Auto-generated method stub

	    }
}
