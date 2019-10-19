package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class Send extends JFrame{
	JLabel sideLa = new JLabel(new ImageIcon("image/leftside.png"));
	JLabel pageName = new JLabel("계좌이체");
	JLabel myMoney = new JLabel("100000원");
	JLabel La1 = new JLabel("출금 계좌번호");
	JLabel La2 = new JLabel("입금 계좌번호");
	JLabel La3 = new JLabel("보낼 돈");
//	String [] myWS = {"농협 123-4567-8901-23","기업 987-654321-09-876","우리 123-4567-8901-23"};
	JComboBox myWC = new JComboBox();
	
	String [] stst = new String[] {"농협","기업","우리"};
	JComboBox sendWC = new JComboBox(stst);
	JTextField sendWF = new JTextField();
	
	JTextField valueF = new JTextField();
	  
	JLabel border1 = new JLabel(new ImageIcon("image/send_border1.png"));
	JLabel border2 = new JLabel(new ImageIcon("image/send_border2.png"));
	JLabel border3 = new JLabel(new ImageIcon("image/send_border3.png"));

	JButton sendBT = new JButton(new ImageIcon("image/send_send.png"));
	
	JLabel down_BG = new JLabel(new ImageIcon("image/down_bg.png"));				//백그라운드
	JButton down_home = new JButton(new ImageIcon("image/down_home.png"));		//홈버튼
	JButton down_send = new JButton(new ImageIcon("image/down_send.png"));		//송금버튼
	JButton down_inquiry = new JButton(new ImageIcon("image/down_inquiry.png"));//조회버튼
	JButton down_mypage = new JButton(new ImageIcon("image/down_mypage.png"));	//마이페이지버튼
	JButton down_service = new JButton(new ImageIcon("image/down_service.png"));//서비스버튼
	
	String user = "";
	int asdf = 0;
	//지갑 지정해서 송금페이지 만듬
	Send(String user, int asdf)
	{
		this.user= user;
		this.asdf = asdf;
		viewCreate();
	}
	Send(String user)
	{
		this.user = user;
		viewCreate();
	}
	
	//화면만드는 애
	void viewCreate()
	{
		joinDB();
		//-----왼쪽 화면 구성-----
				sideLa.setBounds(10,10,250,480);
				pageName.setBounds(43,45,190,57);
				pageName.setFont(new Font("함초롬돋움",Font.BOLD,45));
				pageName.setForeground(Color.white);
				
				//-----출금 계좌 구성-----
				border1.setBounds(275,10,411,61);
				
				myWC.setBounds(287,17,270,33);
				myWC.setFont(new Font("함초롬돋움",Font.BOLD,20));
				myWC.setSelectedIndex(asdf);
				
				myMoney.setBounds(580,55,100,15);
				myMoney.setFont(new Font("돋움",Font.BOLD,15));
				myMoney.setForeground(Color.gray);
				myMoney.setHorizontalAlignment(SwingConstants.RIGHT);
				
				La1.setBounds(275,73,100,14);
				La1.setFont(new Font("돋움",Font.BOLD,14));
				La1.setForeground(Color.GRAY);
				
				//-----입금 계좌 구성-----
				sendWC.setBounds(287,100,61,33);
				sendWC.setFont(new Font("함초롬돋움",Font.BOLD,20));
				sendWC.setBackground(Color.LIGHT_GRAY);
				sendWC.setUI(new BasicComboBoxUI() {
				    protected JButton createArrowButton() {
				        return new JButton() {
				            public int getWidth() {
				                return 0;
				            }
				        };
				    }
				});
				
				sendWF.setBounds(357,100,205,33);
				sendWF.setFont(new Font("함초롬돋움",Font.BOLD,20));
				
				border2.setBounds(275,93,411,48);
				La2.setBounds(275,143,100,14);
				La2.setFont(new Font("돋움",Font.BOLD,14));
				La2.setForeground(Color.GRAY);
				//-----보낼 돈-----
				valueF.setBounds(287,180,330,40);
				valueF.setFont(new Font("함초롬돋움",Font.BOLD,30));
				JLabel w = new JLabel("원");
				w.setBounds(620,180,33,33);
				w.setFont(new Font("함초롬돋움",Font.BOLD,30));
				border3.setBounds(275,173,411,55);

				La3.setBounds(275,230,100,14);
				La3.setFont(new Font("돋움",Font.BOLD,14));
				La3.setForeground(Color.GRAY);
				
				//-----송금 버튼-----
				sendBT.setBounds(275,381,410,41);
				sendBT.setPressedIcon(new ImageIcon("image/send_send1.png"));
				sendBT.setBorderPainted(false);
				sendBT.setContentAreaFilled(false);
				sendBT.addActionListener(new sendButton());
				
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
				
				down_service.setBounds(597,440,40,43);
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
				//-----컨테이너에 붙이기-----
				add(pageName);
				add(sideLa);
				add(myWC);
				add(myMoney);
				add(sendWC);
				add(sendWF);
				add(valueF);
				add(border1);
				add(border2);
				add(border3);
				add(w);
				add(La2);
				add(La1);
				add(La3);
				add(sendBT);
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
	//체크박스에 계좌번호 목록 만드는 함수
	void joinDB()
	{
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
			
//			String[] st = new String[] {rs.getString("wallet1"),rs.getString("wallet2"),rs.getString("wallet3")};
			
			myWC.addItem(rs.getString("wallet1"));
			myWC.addItem(rs.getString("wallet2"));
			myWC.addItem(rs.getString("wallet3"));
			
			con.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
	}
	//화면 구성
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
		setTitle("송금페이지");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		this.getRootPane().setPreferredSize(new Dimension(700,500));//프레임 크기 정확하게
		this.pack();	//- - - - - - -  - - - - - - - - - - - -- - - 프레임 크기 정확하게
	}
	boolean showM(String str)
	{
		if(str.equals("")) 
		{
			JOptionPane.showMessageDialog(this, "올바른 계좌번호가 아닙니다.");
			return false;
		}
		return true;
	}
	
	//송금 버튼 이벤트(Main함수 호출로 블록체인 사용해서 안전거래)
	class sendButton implements ActionListener{
		@Override   
		public void actionPerformed(ActionEvent e) {
//			setTitle(myWC.getSelectedItem().toString());
			Connection con = null;
			try {            
				Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 연결
				//                                         ↓DB이름  나머지는 복붙
				String url = "jdbc:mysql://localhost:3306/javawallet?serverTimezone=Asia/Seoul"; // DB연결
				con = DriverManager.getConnection(url, "root", "jsppass"); //url, ID, PW
				Statement stmt = con.createStatement();
				//실행할 문장
				String sql = "SELECT * FROM wallet where wallet_name = '" + sendWF.getText()  +"'";
				ResultSet rs = stmt.executeQuery(sql);
				
				rs.next();
				
				String str = rs.getString("wallet_name");
				
				//계좌가 없으면 끊음
				if(!showM(str)) return;
				
				//있으면 송금
				
				new Main(myWC.getSelectedItem().toString(), sendWF.getText(),Integer.parseInt(valueF.getText()));
				
				resetSend();
				new MainView(user);
				
				con.close();
				rs.close();
			} catch (ClassNotFoundException ex) {
				System.out.println("드라이버를 찾을 수 없습니다.");
			} catch (SQLException ex) {
				System.out.println("연결에 실패하였습니다.");
			}
		}
	}
	void resetSend()
	{
		JOptionPane.showMessageDialog(this, "송금이 완료되었습니다.");
		this.dispose();
	}
	//화면 이동 버튼
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
//			else if(bt.getIcon().toString().equals("image/down_send.png"))
//			{
//				new Send();
//				dispose();
//			}
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
