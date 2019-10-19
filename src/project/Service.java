package project;

import java.awt.*;
import javax.swing.*;

import project.Send.DownButtonAction1;

import java.awt.event.*;

public class Service extends JFrame{
	JLabel sideLa = new JLabel(new ImageIcon("image/leftside.png"));
	JLabel pageName = new JLabel("고객센터");
	
	JLabel logo = new JLabel(new ImageIcon("image/service_logo.png"));
	
	JLabel board1 = new JLabel(new ImageIcon("image/service_board.png"));
	
	JButton call = new JButton(new ImageIcon("image/service_call.png"));
	JButton email = new JButton(new ImageIcon("image/service_email.png"));
	JButton message = new JButton(new ImageIcon("image/service_message.png"));
	JButton service = new JButton(new ImageIcon("image/service_service.png"));
	JButton time = new JButton(new ImageIcon("image/service_time.png"));
	JButton faq = new JButton(new ImageIcon("image/service_FAQ.png"));
	
	JLabel down_BG = new JLabel(new ImageIcon("image/down_bg.png"));			//백그라운드
	JButton down_home = new JButton(new ImageIcon("image/down_home.png"));		//홈버튼
	JButton down_send = new JButton(new ImageIcon("image/down_send.png"));		//송금버튼
	JButton down_inquiry = new JButton(new ImageIcon("image/down_inquiry.png"));//조회버튼
	JButton down_mypage = new JButton(new ImageIcon("image/down_mypage.png"));	//마이페이지버튼
	JButton down_service = new JButton(new ImageIcon("image/down_service.png"));//서비스버튼
	
	String user = "";
	Service(String user)
	{
		this.user = user;
		sideLa.setBounds(10,10,250,480);
		pageName.setBounds(43,45,190,57);
		pageName.setFont(new Font("함초롬돋움",Font.BOLD,45));
		pageName.setForeground(Color.white);
		
		logo.setBounds(378,16,220,220); 
		
		board1.setBounds(275,228,411,191);
		
		//-----중앙 버튼-----
		call.setBounds(337,238,80,80);
		call.setBorderPainted(false);
		call.setContentAreaFilled(false);
		call.setPressedIcon(new ImageIcon("image/service_call2.png"));
		
		email.setBounds(439,238,80,80);
		email.setBorderPainted(false);
		email.setContentAreaFilled(false);
		email.setPressedIcon(new ImageIcon("image/service_email2.png"));

		message.setBounds(542,238,80,80);
		message.setBorderPainted(false);
		message.setContentAreaFilled(false);
		message.setPressedIcon(new ImageIcon("image/service_message2.png"));

		service.setBounds(337,328,80,80);
		service.setBorderPainted(false);
		service.setContentAreaFilled(false);
		service.setPressedIcon(new ImageIcon("image/service_service2.png"));

		time.setBounds(439,328,80,80);
		time.setBorderPainted(false);
		time.setContentAreaFilled(false);
		time.setPressedIcon(new ImageIcon("image/service_time2.png"));

		faq.setBounds(542,328,80,80);
		faq.setBorderPainted(false);
		faq.setContentAreaFilled(false);
		faq.setPressedIcon(new ImageIcon("image/service_FAQ2.png"));
		
		//-----아래 버튼-----
		down_BG.setBounds(275,432,410,58);
						 
		down_home.setBounds(327,440,32,43);
		down_home.setBorderPainted(false);
		down_home.setContentAreaFilled(false);
						
		down_send.setBounds(394,440,37,45);
		down_send.setBorderPainted(false);
		down_send.setContentAreaFilled(false);
						
		down_inquiry.setBounds(465,440,36,44);
		down_inquiry.setBorderPainted(false);
		down_inquiry.setContentAreaFilled(false);
						
		down_mypage.setBounds(528,440,39,45);
		down_mypage.setBorderPainted(false);
		down_mypage.setContentAreaFilled(false);
						
		down_service.setBounds(597,440,38,43);
		down_service.setBorderPainted(false);
		down_service.setContentAreaFilled(false);

		down_home.addActionListener(new DownButtonAction1());
		down_send.addActionListener(new DownButtonAction1());
		down_inquiry.addActionListener(new DownButtonAction1());
		down_mypage.addActionListener(new DownButtonAction1());
		down_service.addActionListener(new DownButtonAction1());

		down_home.setPressedIcon(new ImageIcon("image/down_home2.png"));
		down_send.setPressedIcon(new ImageIcon("image/down_send2.png"));
		down_inquiry.setPressedIcon(new ImageIcon("image/down_inquiry2.png"));
		down_mypage.setPressedIcon(new ImageIcon("image/down_mypage2.png"));
		down_service.setPressedIcon(new ImageIcon("image/down_service2.png"));
		//-----추가-----
		add(pageName);
		add(sideLa);
		add(logo);
		add(board1);
		add(call);
		add(email);
		add(message);
		add(service);
		add(time);
		add(faq);
		add(down_home);
		add(down_send);
		add(down_inquiry);
		add(down_mypage);
		add(down_service);
		add(down_BG);
		
		add(new JLabel());
		display();
		setMid();
	}
	void setMid()
	{
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터화면의 해상도 얻기
		
		// 프레임이 화면 중앙에 위치하도록 left, top 계산
		int left = (screen.width / 2) - (700 / 2);
		int top = (screen.height / 2) - (500 / 2);

		setLocation(left,top);
	}
	void display()
	{
		this.getContentPane().setBackground(new Color(255,255,255));
		setTitle("메인페이지");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		this.getRootPane().setPreferredSize(new Dimension(700,500));//프레임 크기 정확하게
		this.pack();	//- - - - - - -  - - - - - - - - - - - -- - - 프레임 크기 정확하게
	}
	class DownButtonAction1 implements ActionListener{
		JButton bt;
		@Override
		public void actionPerformed(ActionEvent e) {
			bt = (JButton)e.getSource();
			if(bt.getIcon().toString().equals("image/down_home.png"))
			{
				new MainView(user);
				dispose();
			}
			else if(bt.getIcon().toString().equals("image/down_send.png"))
			{
				new Send(user);
				dispose();
			}
			else if(bt.getIcon().toString().equals("image/down_inquiry.png"))
			{
				new Inquiry(user);
				dispose();
			}
			else if(bt.getIcon().toString().equals("image/down_mypage.png"))
			{
				new Mypage(user);
				dispose();
			}             
//			else if(bt.getIcon().toString().equals("image/down_service.png"))
//			{
//				new Service();
//				dispose();
//			}
		}
	}
}
