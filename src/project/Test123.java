package project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import java.util.Vector;


public class Test123 extends JFrame{
	ImageIcon img = new ImageIcon("image/panel1.png");
	JButton bt = new JButton() {
		public void paintComponent(Graphics g)
		{
			g.drawImage(img.getImage(),0,0,null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	Test123()
	{
		setTitle("ㅁㄴㅇㄹ");
		setVisible(true);
		setSize(500,500);
		setLayout(null);
		bt.setBounds(20,20,200,200);
		             
		JButton bt1 = new JButton("11414086801011");
		bt1.setBounds(10,90,104,25);
		bt1.setBorderPainted(false);
		bt1.setContentAreaFilled(false);
		bt1.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, bt1.getText() + "와!");
			}
		});
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g)
			{
				g.drawImage(img.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setBounds(220,20,124,124);
		panel.setLayout(null);
		panel.add(bt1);
		add(panel);
		add(bt);
	}
	public static void main(String[] args) {
		new Test123();
//		try {
//		GregorianCalendar gc = new GregorianCalendar();
//		//년
//		String st = "'" + gc.get(gc.YEAR);
//		//월
//		if(gc.get(gc.MONTH) <10) st+= "0" + gc.get(gc.MONTH);
//		else st +=gc.get(gc.MONTH);
//		//일
//		if(gc.get(gc.DATE)<10)st+= "0" + gc.get(gc.DATE);
//		else st +=gc.get(gc.DATE);
//		st+= "'";
//		
//		System.out.println(st);
//		}catch(Exception ex) {System.out.println(ex.toString());}
	}

}

/* 
 		JLabel L = new JLabel("11414086801012 에게 1000원 받음");
		L.setBounds(285,120,385,17);
		L.setFont(new Font("",Font.PLAIN,18));
		
		JLabel LL = new JLabel("201906022");
		LL.setBounds(610,139,72,10);
		LL.setFont(new Font("",Font.PLAIN,12));
		LL.setForeground(Color.GRAY);
		LL.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel LLL = new JLabel("11414086801012 에게 1000원 보냄");
		LLL.setBounds(285,150,385,17);
		LLL.setFont(new Font("",Font.PLAIN,18));
*/
