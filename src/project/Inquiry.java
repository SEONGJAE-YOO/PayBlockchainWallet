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
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class Inquiry extends JFrame{
   JLabel sideLa = new JLabel(new ImageIcon("image/leftside.png"));
   //계좌표시==
   String walletNameS = "농협은행 123-1234-12345";
   JLabel walletName = new JLabel();
   JComboBox walletNameBox = new JComboBox();
   JLabel walletValue = new JLabel();

   JLabel pageName = new JLabel("계좌조회");
   
   JButton sendBT = new JButton(new ImageIcon("image/inquiry_send.png"));
   
   JLabel board1 = new JLabel(new ImageIcon("image/inquiry_board1.png"));
   JLabel board2 = new JLabel(new ImageIcon("image/inquiry_board2.png"));
   
   JLabel La1 = new JLabel("거래내역");
   
   JLabel down_BG = new JLabel(new ImageIcon("image/down_bg.png"));            //백그라운드
   JButton down_home = new JButton(new ImageIcon("image/down_home.png"));      //홈버튼
   JButton down_send = new JButton(new ImageIcon("image/down_send.png"));      //송금버튼
   JButton down_inquiry = new JButton(new ImageIcon("image/down_inquiry.png"));//조회버튼
   JButton down_mypage = new JButton(new ImageIcon("image/down_mypage.png"));   //마이페이지버튼
   JButton down_service = new JButton(new ImageIcon("image/down_service.png"));//서비스버튼
   
   Vector<JLabel> showL1 = new Vector<JLabel>();
   Vector<JLabel> showL2 = new Vector<JLabel>();
   Vector<String> sendV = new Vector<String>();
   Vector<String> reV = new Vector<String>();
   Vector<String> valueV  = new Vector<String>();
   Vector<String> dateV = new Vector<String>();
   
   String user = "";
   int asdf = 0;
   
   //그냥 생성자들
   public Inquiry(String user,int asdf)
   {
      this.asdf = asdf; 
      this.user = user;
      setView();
   }
   public Inquiry(String user) 
   {
      this.user = user;
      setView();
   }
   
   //화면구성 함수
   void setView()
   {
      //-----좌측 패널-----
            sideLa.setBounds(10,10,250,480);

            pageName.setBounds(43,45,190,57);
            pageName.setFont(new Font("함초롬돋움",Font.BOLD,45));
            pageName.setForeground(Color.white);
            //-----잔액 확인-----
            board1.setBounds(275,10,411,61);
            
            //계좌표시
            walletName.setText(walletNameS);
            walletName.setBounds(293,22,163,13);
            walletName.setFont(new Font("돋움",Font.BOLD,12));
            
            walletNameBox.setBounds(293,18,163,20);
            walletNameBox.setFont(new Font("돋움",Font.BOLD,12));
            walletNameBox.setUI(new BasicComboBoxUI() {
                protected JButton createArrowButton() {
                    return new JButton() {
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
            walletNameBox.setBackground(Color.white);
            walletNameBox.setFocusable(false);
            
            joinDB();
            walletNameBox.setSelectedIndex(asdf);
            walletNameBox.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  
                  for(int i = 0; i< showL1.size(); i++)
                  {
                      System.out.println("지우기 함수 실행");
                     remove(showL1.elementAt(i));
                     remove(showL2.elementAt(i));
                  }
                  showL1.clear();
                  showL2.clear();
                  sendV.clear();
                  reV.clear();
                  valueV.clear();
                  dateV.clear();
                  System.out.println(showL1.size());
                  String se = (String)walletNameBox.getSelectedItem();
                  showRecode(se);

                  showMoney();
               }
            });
            //=====
            
            walletValue.setBounds(293,41,170,22);
            walletValue.setFont(new Font("돋움",Font.BOLD,20));
            
            sendBT.setBounds(605,39,70,24);
            sendBT.setBorderPainted(false);
            sendBT.setContentAreaFilled(false);
            sendBT.setPressedIcon(new ImageIcon("image/inquiry_send2.png"));
            sendBT.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  // TODO Auto-generated method stub
                  new Send(user,walletNameBox.getSelectedIndex());
                  dispose();
               }
            });
            //-----거래내역-----
            La1.setBounds(275,85,90,27);
            La1.setFont(new Font("돋움",Font.BOLD,20));
            La1.setForeground(new Color(97,183,183));
            
            board2.setBounds(275,115,411,301);
            
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
            
            //-----추가-----
            add(pageName);
            add(sideLa);
            add(board1);
            add(La1);
            add(walletNameBox);
            add(walletValue);
            add(sendBT);
            add(board2);
            add(down_home);
            add(down_send);
            add(down_inquiry);
            add(down_mypage);
            add(down_service);
            add(down_BG);
            add(new JLabel());
            display();
            setMid();
            
            String se = (String)walletNameBox.getSelectedItem();
            showRecode(se);
            showMoney();
   }
   //돈 얻어오는 함수
   void showMoney()
   {
      System.out.println("잔액확인 함수");
      Connection con = null;
      try {
         Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 연결
         //                                         ↓DB이름  나머지는 복붙
         String url = "jdbc:mysql://localhost:3306/javawallet?serverTimezone=Asia/Seoul"; // DB연결
         con = DriverManager.getConnection(url, "root", "jsppass"); //url, ID, PW
         Statement stmt = con.createStatement();
         
         String se = (String)walletNameBox.getSelectedItem();
         String sql = "SELECT wallet_value FROM wallet where wallet_name = '" + se + "'";
         ResultSet rs = stmt.executeQuery(sql);
         
         rs.next();
         walletValue.setText(rs.getInt("wallet_value") + " 원");
         
         rs.close();
         con.close();
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버를 찾을 수 없습니다.");
      } catch (SQLException e) {
         System.out.println("연결에 실패하였습니다.");
      }
   }
   
   //거래내역 생성 함수
   void showRecode(String se)
   {
//      System.out.println("거래내역 함수");
//      for(int i = 0; i< showL1.size(); i++)
//      {
//         remove(showL1.elementAt(i));
//         remove(showL2.elementAt(i));
//      }
//      showL1.clear();
//      showL2.clear();
      Connection con = null;
      try {
         Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 연결
         //                                         ↓DB이름  나머지는 복붙
         String url = "jdbc:mysql://localhost:3306/javawallet?serverTimezone=Asia/Seoul"; // DB연결
         con = DriverManager.getConnection(url, "root", "jsppass"); //url, ID, PW
         Statement stmt = con.createStatement();
         
         se = (String)walletNameBox.getSelectedItem();
         String sql = "SELECT * FROM recode WHERE sender = '" + se +"' OR reciepient = '" + se +"';";
         System.out.println(sql);
         ResultSet rs = stmt.executeQuery(sql);
         
         while(rs.next())
         {
            String s = rs.getString("sender");
            String r = rs.getString("reciepient");
            String v = rs.getInt("recode_value") + "";
            String d = rs.getDate("send_date").toString();
            System.out.println(s +"/"+ r+"/"+v+"/"+d);
            sendV.add(s);
            reV.add(r);
            valueV.add(v);
            dateV.add(d);
         }
         for(int i = 0; i< sendV.size(); i++)
         {
            String str = "";
            if(sendV.elementAt(i).equals(se)) str += reV.elementAt(i) + "에게 " + valueV.elementAt(i) + "보냄";
            else if(!sendV.elementAt(i).equals(se))str += sendV.elementAt(i) + "에게 " + valueV.elementAt(i) + "받음";
            
            JLabel L = new JLabel(str);
            L.setBounds(285,123+(i*30),385,17);
            L.setFont(new Font("",Font.PLAIN,18));
            
            JLabel LL = new JLabel(dateV.elementAt(i));
            LL.setBounds(610,142+(i*30),72,10);
            LL.setFont(new Font("",Font.PLAIN,12));
            LL.setForeground(Color.gray);
            LL.setHorizontalAlignment(SwingConstants.RIGHT);
            
            showL1.add(L);
            showL2.add(LL);
         }
         visibleRecode();
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버를 찾을 수 없습니다.");
      } catch (SQLException e) {
         System.out.println("연결에 실패하였습니다.");
      }
      this.repaint();//화면 새로고침
   }
  
   //생성한 거래내역 화면에 추가하는 함수
   void visibleRecode()
   {
      
      for(int i = 0; i< showL1.size(); i++)
      {
         add(showL1.elementAt(i));
         add(showL2.elementAt(i));
      }
   }
                                 
   //체크박스에 계좌번호 넣는 함수
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
         
//         String[] st = new String[] {rs.getString("wallet1"),rs.getString("wallet2"),rs.getString("wallet3")};
         
         walletNameBox.addItem(rs.getString("wallet1"));
         walletNameBox.addItem(rs.getString("wallet2"));
         walletNameBox.addItem(rs.getString("wallet3"));
         
         con.close();
         rs.close();
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버를 찾을 수 없습니다.");
      } catch (SQLException e) {
         System.out.println("연결에 실패하였습니다.");
      }
   }
   
   //화면구성
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
      setTitle("로그인페이지");
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      this.getRootPane().setPreferredSize(new Dimension(700,500));//프레임 크기 정확하게
      this.pack();   //- - - - - - -  - - - - - - - - - - - -- - - 프레임 크기 정확하게
   }

   //이동 버튼
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
//         else if(bt.getIcon().toString().equals("image/down_inquiry.png"))
//         {
//            new Inquiry();
//            dispose();
//         }
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