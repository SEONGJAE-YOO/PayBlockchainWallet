package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainView extends JFrame{
	JLabel sideLa = new JLabel(new ImageIcon("image/leftside.png"));
	JLabel borderLa1 = new JLabel(new ImageIcon("image/main_border1.png"));
	JLabel borderLa2 = new JLabel(new ImageIcon("image/main_border2.png"));
	
	JLabel pageName = new JLabel("메인화면");
	JLabel La1 = new JLabel("내 계좌");
	JLabel La2 = new JLabel("메뉴");
	
	JButton inquiry = new JButton(new ImageIcon("image/main_inquiry.png"));
	JButton send = new JButton(new ImageIcon("image/main_send.png"));
	JButton mypage = new JButton(new ImageIcon("image/main_mypage.png"));
	JButton lock = new JButton(new ImageIcon("image/main_lock.png"));

	JLabel down_BG = new JLabel(new ImageIcon("image/down_bg.png"));				//백그라운드
	JButton down_home = new JButton(new ImageIcon("image/down_home.png"));		//홈버튼
	JButton down_send = new JButton(new ImageIcon("image/down_send.png"));		//송금버튼
	JButton down_inquiry = new JButton(new ImageIcon("image/down_inquiry.png"));//조회버튼
	JButton down_mypage = new JButton(new ImageIcon("image/down_mypage.png"));	//마이페이지버튼
	JButton down_service = new JButton(new ImageIcon("image/down_service.png"));//서비스버튼
	String user ="";
	
	Vector<JPanel> panel1 = new Vector<JPanel>();
	
	Container container = getContentPane();
	
	MainView(String user)
	{
		this.user = user;
		
		showPanel();
		//----- 로고 및 Border -----
		sideLa.setBounds(10,10,250,480);
		borderLa1.setBounds(275,35,411,145);
		borderLa2.setBounds(275,215,411,110);
		
		pageName.setBounds(43,45,190,57);
		pageName.setFont(new Font("함초롬돋움",Font.BOLD,45));
		pageName.setForeground(Color.white);
		
		//----- 메뉴 구성 -----
		inquiry.setBounds(285,224,90,90);
		inquiry.setPressedIcon(new ImageIcon("image/main_inquiry1.png"));
		inquiry.setBorderPainted(false);
		inquiry.setContentAreaFilled(false);
		inquiry.addActionListener(new MainButtonAction());
		
		send.setBounds(385,224,90,90);
		send.setPressedIcon(new ImageIcon("image/main_send1.png"));
		send.setBorderPainted(false);
		send.setContentAreaFilled(false);
		send.addActionListener(new MainButtonAction());

		mypage.setBounds(485,224,90,90);
		mypage.setPressedIcon(new ImageIcon("image/main_mypage1.png"));
		mypage.setBorderPainted(false);
		mypage.setContentAreaFilled(false);
		mypage.addActionListener(new MainButtonAction());

		lock.setBounds(585,224,90,90);
		lock.setPressedIcon(new ImageIcon("image/main_lock1.png"));
		lock.setBorderPainted(false);
		lock.setContentAreaFilled(false);
		
		La1.setBounds(275,10,70,18);
		La1.setFont(new Font("함초롬돋움",Font.BOLD,20));
		La1.setForeground(new Color(97,183,183));
		
		La2.setBounds(275,188,40,20);
		La2.setFont(new Font("함초롬돋움",Font.BOLD,20));
		La2.setForeground(new Color(97,183,183));
		//----- 아래 버튼 -----
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
		
		down_service.setBounds(597,440,40,43);
		down_service.setBorderPainted(false);
		down_service.setContentAreaFilled(false);

		down_home.setPressedIcon(new ImageIcon("image/down_home2.png"));
		down_send.setPressedIcon(new ImageIcon("image/down_send2.png"));
		down_inquiry.setPressedIcon(new ImageIcon("image/down_inquiry2.png"));
		down_mypage.setPressedIcon(new ImageIcon("image/down_mypage2.png"));
		down_service.setPressedIcon(new ImageIcon("image/down_service2.png"));
		
		down_home.addActionListener(new DownButtonAction1());
		down_send.addActionListener(new DownButtonAction1());
		down_inquiry.addActionListener(new DownButtonAction1());
		down_mypage.addActionListener(new DownButtonAction1());
		down_service.addActionListener(new DownButtonAction1());
		
		//----- 추가 -----
		add(pageName);
		add(sideLa);
		add(borderLa1);
		add(borderLa2);
		add(inquiry);
		add(send);
		add(mypage);
		add(lock);
		add(La1);
		add(La2);
		add(down_home);
		add(down_send);
		add(down_inquiry);
		add(down_mypage);
		add(down_service);
		add(down_BG);
		add(new JLabel());
		setMid();
		display();
	}
	//----- 프레임 중앙 배치-----
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
	
	//-----------지갑생성 함수-----------
	void showPanel()
	{
		int waNum = 0;
		String wNames[] = new String[3];
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 연결
			//                                         ↓DB이름  나머지는 복붙
			String url = "jdbc:mysql://localhost:3306/javawallet?serverTimezone=Asia/Seoul"; // DB연결
			con = DriverManager.getConnection(url, "root", "jsppass"); //url, ID, PW
			Statement stmt = con.createStatement();
			//실행할 문장
			String sql = "SELECT * FROM userInfo where user_id = '" + user + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			//지갑 개수 확인
			try {
			if(!rs.getString("wallet1").equals("")) {wNames[waNum] = rs.getString("wallet1");waNum++;}
			}catch(NullPointerException ex) {}
			try {
			if(!rs.getString("wallet2").equals("")) {wNames[waNum] = rs.getString("wallet2");waNum++;}
			}catch(NullPointerException ex) {}
			try {
			if(!rs.getString("wallet3").equals("")) {wNames[waNum] = rs.getString("wallet3");waNum++;}
			}catch(NullPointerException ex) {}
			for(int i = 0; i<waNum; i++)
			{
				JPanel panel = new JPanel(){
					public void paintComponent(Graphics g)
					{
						g.drawImage(new ImageIcon("image/panel.png").getImage(),0,0,null);
						setOpaque(false);
						super.paintComponent(g);
					}
				};
				panel.setBounds(289+(i*130),46,124,124);
				panel.setLayout(null);
				JLabel jl = new JLabel(wNames[i]);
				jl.setBounds(18,43,96,25);
				jl.setFont(new Font("",Font.BOLD,11));
				jl.setForeground(Color.white);
				
				panel.add(jl);
				JButton bt = new JButton("조회하기");
				bt.setBounds(20,90,84,21);
				bt.setBackground(Color.white);
				bt.setFont(new Font("HY견고딕",Font.PLAIN,10));
				int iss = i;
				bt.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new Inquiry(user,iss);
						dispose();
//						JButton b = (JButton)e.getSource();
//						JOptionPane.showMessageDialog(null, b.getText());
					}
				});
				bt.setBorderPainted(false);
				panel.add(bt);
//				bt.setBorderPainted(false);
//				bt.setContentAreaFilled(false);
//				bt.setFont(new Font("돋움",Font.BOLD,13));
//				bt.setForeground(Color.white);
//				bt.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
				
				panel1.add(panel);
			}
			for(int i = 0; i<waNum; i++) 
			{
				container.add(panel1.elementAt(i));
				System.out.println("컨테이너에 지갑 추가함");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
	}
	//페이지 이동 버튼
	class MainButtonAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == inquiry)
			{
				new Inquiry(user);
				dispose();
			}
			if(e.getSource() == send)
			{
				new Send(user);
				dispose();
			}
			if(e.getSource() == mypage)
			{
				new Mypage(user);
				dispose();
			}
//			if(e.getSource() == lock)
//			{
//				new Lock();
//				dispose();
//			}
		}
		
	}
	class DownButtonAction1 implements ActionListener{
		JButton bt;
		@Override
		public void actionPerformed(ActionEvent e) {
			bt = (JButton)e.getSource();
			if(bt.getIcon().toString().equals("image/down_home.png"))
			{
//				new Main();
//				dispose();
			}
			if(bt.getIcon().toString().equals("image/down_send.png"))
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
			else if(bt.getIcon().toString().equals("image/down_service.png"))
			{
				new Service(user);
				dispose();
			}
		}
	}
}
