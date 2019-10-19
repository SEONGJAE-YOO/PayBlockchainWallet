package project;

import java.awt.*;
import javax.swing.*;

import project.Send.DownButtonAction1;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;

public class Mypage extends JFrame{
   JLabel sideLa = new JLabel(new ImageIcon("image/leftside.png"));
   JLabel pageName = new JLabel("마이페이지");
   
   JLabel board = new JLabel(new ImageIcon("image/mypage_board.png"));
   JLabel profile = new JLabel(new ImageIcon("image/mypage_profile.png"));
   JLabel mypage_name = new JLabel("");
   
   JButton mypage_BT1 = new JButton(new ImageIcon("image/mypage_b1.png"));
   JButton mypage_BT2 = new JButton(new ImageIcon("image/mypage_b2.png"));
   JButton mypage_BT3 = new JButton(new ImageIcon("image/mypage_b3.png"));
   JButton mypage_BT4 = new JButton(new ImageIcon("image/mypage_b4.png"));
   
   JLabel down_BG = new JLabel(new ImageIcon("image/down_bg.png"));            //백그라운드
   JButton down_home = new JButton(new ImageIcon("image/down_home.png"));      //홈버튼
   JButton down_send = new JButton(new ImageIcon("image/down_send.png"));      //송금버튼
   JButton down_inquiry = new JButton(new ImageIcon("image/down_inquiry.png"));//조회버튼
   JButton down_mypage = new JButton(new ImageIcon("image/down_mypage.png"));   //마이페이지버튼
   JButton down_service = new JButton(new ImageIcon("image/down_service.png"));//서비스버튼
   
   String user = "";
   Mypage(String user)
   {
      this.user = user;
      
      //-----소개-----
      sideLa.setBounds(10,10,250,480);
      pageName.setBounds(35,35,210,70);
      pageName.setFont(new Font("굴림",Font.BOLD,40));
      pageName.setForeground(Color.white);
      
      board.setBounds(275,10,411,141);
      profile.setBounds(300,30,100,100);
      mypage_name.setBounds(410,30,110,50);
      mypage_name.setFont(new Font("굴림",Font.BOLD,37));
      //-----메인버튼-----
      mypage_BT1.setBounds(275,170,410,45);
      mypage_BT1.setBorderPainted(false);
      mypage_BT1.setContentAreaFilled(false);
      
      mypage_BT2.setBounds(275,225,410,45);
      mypage_BT2.setBorderPainted(false);
      mypage_BT2.setContentAreaFilled(false);
      
      mypage_BT3.setBounds(275,280,410,45);
      mypage_BT3.setBorderPainted(false);
      mypage_BT3.setContentAreaFilled(false);
      
      mypage_BT4.setBounds(275,335,410,45);
      mypage_BT4.setBorderPainted(false);
      mypage_BT4.setContentAreaFilled(false);
      
      mypage_BT1.setPressedIcon(new ImageIcon("image/mypage_b11.png"));
      mypage_BT2.setPressedIcon(new ImageIcon("image/mypage_b21.png"));
      mypage_BT3.setPressedIcon(new ImageIcon("image/mypage_b31.png"));
      mypage_BT4.setPressedIcon(new ImageIcon("image/mypage_b41.png"));
      
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
      
      add(pageName);
      add(sideLa);
      add(board);
      add(profile);
      add(mypage_name);
      add(mypage_BT1);
      add(mypage_BT2);
      add(mypage_BT3);
      add(mypage_BT4);
      add(down_home);
      add(down_send);
      add(down_inquiry);
      add(down_mypage);
      add(down_service);
      add(down_BG);
      add(new JLabel());
      setMid();
      display();
      setName();
   }
   void setName()
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
			
			mypage_name.setText(rs.getString("user_name"));
			
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
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
      setTitle("마이페이지");
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      this.getRootPane().setPreferredSize(new Dimension(700,500));//프레임 크기 정확하게
      this.pack();   //- - - - - - -  - - - - - - - - - - - -- - - 프레임 크기 정확하게
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
//         else if(bt.getIcon().toString().equals("image/down_mypage.png"))
//         {
//            new Mypage();
//            dispose();
//         }
         else if(bt.getIcon().toString().equals("image/down_service.png"))
         {
            new Service(user);
            dispose();
         }
      }
   }
}