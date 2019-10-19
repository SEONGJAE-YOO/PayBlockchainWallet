package project;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Main {
	public static ArrayList<Block> blockChain = new ArrayList<>();//[1,2,3,...]
	public static int difficulty =2;
	public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();//보내야할 코인에 트랜잭션
	
	public static Wallet walletA;
	public static Wallet walletB;
	
	public static Vector<Wallet> walletL = new Vector<Wallet>(); //지갑-계좌번호
	public static Vector<Integer> walletV = new Vector<Integer>();//지갑-계좌금액
	public static Vector<String> walletN = new Vector<String>();//지갑-계좌금액
	public Vector<Block> blockL = new Vector<Block>();
	public int blockNum = 0;

	int send =0, res=0;
	String user = "";
	public static Transaction genesisTransaction;
	public static float minimumTransaction =0.1f; //최소한으로 할 이체할 금액
						
	Main(String Sender)
	{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Wallet coinbase = new Wallet("center"); //채굴이 일어났을때 코인을 주는 월렛
		
		//------------------------------------------------------
		//------------------데이터베이스에서 지갑 뽑아옴------------------
		//------------------------------------------------------
		Connection con = null;
		blockL.add(new Block("0"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 연결
			
			//                                         ↓DB이름  나머지는 복붙
			String url = "jdbc:mysql://localhost:3306/javawallet?serverTimezone=Asia/Seoul"; // DB연결
			con = DriverManager.getConnection(url, "root", "jsppass"); //url, ID, PW
			
			
			Statement stmt = con.createStatement();
			//실행할 문장
			String sql = "SELECT * FROM wallet";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) 
			{
				String wName = rs.getString("wallet_name");
				int wValue = rs.getInt("wallet_value");
				
				walletL.add(new Wallet(wName));
				walletV.add(wValue);
				System.out.println("추가!");
			}
			for(int i = 0; i< walletL.size(); i++) System.out.println(walletL.elementAt(i).publicKey);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		//---------------------DB끝!----------------------------
		//------------------------------------------------------
		
		
		//-------------------------------------------------------
		//---------------지갑 초기값 설정을 암호화 처리로 함------------------
		//-------------------------------------------------------
		for(int i = 0; i<walletL.size(); i++)
		{
			try {
			genesisTransaction = new Transaction(coinbase.publicKey,walletL.elementAt(i).publicKey,(float)walletV.elementAt(i), null);
			genesisTransaction.generateSignature(coinbase.privateKey);
			genesisTransaction.transactionId="0";
			genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId));
			UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
			
			blockL.add(new Block(blockL.elementAt(blockNum++).hash));
			blockL.elementAt(blockNum).addTransaction(genesisTransaction);
			addBlock(blockL.elementAt(blockNum));
			
			}catch(Exception ex) { System.out.println(ex.toString());
			}
		}
		//------------------초기화 끝!-----------------------------

		try {
		send = walletN.indexOf(Sender);
		}catch(Exception ex) {System.out.println(ex.toString());}
		
		System.out.println(getMoney(walletL.elementAt(send)));
	}
	float getMoney(Wallet wallet)
	{
		return wallet.getBalance();
	}
	//송금 생성자
	Main(String Sender, String Res, float money)
	{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Wallet coinbase = new Wallet("center"); //채굴이 일어났을때 코인을 주는 월렛
		
		//------------------------------------------------------
		//------------------데이터베이스에서 지갑 뽑아옴------------------
		//------------------------------------------------------
		Connection con = null;
		blockL.add(new Block("0"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 연결
			
			//                                         ↓DB이름  나머지는 복붙
			String url = "jdbc:mysql://localhost:3306/javawallet?serverTimezone=Asia/Seoul"; // DB연결
			con = DriverManager.getConnection(url, "root", "jsppass"); //url, ID, PW
			
			
			Statement stmt = con.createStatement();
			//실행할 문장
			String sql = "SELECT * FROM wallet";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) 
			{
				String wName = rs.getString("wallet_name");
				int wValue = rs.getInt("wallet_value");
				
				walletN.add(wName);
				walletL.add(new Wallet(wName));
				walletV.add(wValue);
				System.out.println("추가!");
			}
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		//---------------------DB끝!----------------------------
		//------------------------------------------------------
		
		
		//-------------------------------------------------------
		//---------------지갑 초기값 설정을 암호화 처리로 함------------------
		//-------------------------------------------------------
		for(int i = 0; i<walletL.size(); i++)
		{
			try {
			genesisTransaction = new Transaction(coinbase.publicKey,walletL.elementAt(i).publicKey,(float)walletV.elementAt(i), null);
			genesisTransaction.generateSignature(coinbase.privateKey);
			genesisTransaction.transactionId="0";
			genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId));
			UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
			
			blockL.add(new Block(blockL.elementAt(blockNum++).hash));
			blockL.elementAt(blockNum).addTransaction(genesisTransaction);
			addBlock(blockL.elementAt(blockNum));
			
			}catch(Exception ex) {}
		}

		for(int i = 0; i<walletL.size(); i++)
			System.out.println(i +"번째 지갑의 금액 :" + walletL.elementAt(i).getBalance());
		
		try {
		send = walletN.indexOf(Sender);
		res = walletN.indexOf(Res);
		}catch(Exception ex) {System.out.println(ex.toString());}
		sendF(walletL.elementAt(send),walletL.elementAt(res),money);
	}
	// 보내는 지갑, 받는 지갑, 보낼 금액을 받아서 송금해주는 함수
	void sendF(Wallet Sender, Wallet Res, float money)
	{
		blockL.add(new Block(blockL.elementAt(blockNum++).hash));
		blockL.elementAt(blockNum).addTransaction(Sender.sendFunds(Res.publicKey, money));
		addBlock(blockL.elementAt(blockNum));

		//------------ 데이터베이스 Insert --------------------
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 연결
			//                                         ↓DB이름  나머지는 복붙
			String url = "jdbc:mysql://localhost:3306/javawallet?serverTimezone=Asia/Seoul"; // DB연결
			con = DriverManager.getConnection(url, "root", "jsppass"); //url, ID, PW
			Statement stmt = con.createStatement();
//			update books set wallet_value = 1000 where wallet_name = 'send' 
			String sql = "update wallet set wallet_value =" + walletL.elementAt(send).getBalance() +
						 " where wallet_name = '" + walletN.elementAt(send) + "'";
			int i = stmt.executeUpdate(sql);
			
			sql = "update wallet set wallet_value =" + walletL.elementAt(res).getBalance() +
					 " where wallet_name = '" + walletN.elementAt(res) + "'";
			
			stmt.executeUpdate(sql);
			System.out.println("기록완료!");
			//------------ 기록저장 Insert --------------------
//			sql = "INSERT INTO recode(sender,reciepient,recode_value,send_date) value('" +
//				   walletN.elementAt(send) + "', '" + walletN.elementAt(res) +"'," + money + 
//				   ",'" + nowDate + "'";
			
			sql = "insert into recode(sender,reciepient,recode_value,send_date) value('" +
			       walletN.elementAt(send)+"','" + walletN.elementAt(res)+"',"+ money +",now())";
			
			stmt.executeUpdate(sql);
			
			
			con.close();
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.toString());
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		for(int i = 0; i< walletL.size(); i++)
		{
			System.out.println(i+"번 지갑의 돈 :"+walletL.elementAt(i).getBalance());
		}
	}
	
	public static void main(String[] args) {
//		new Main("11414086801012","11414086801013",1000f);
		new Main("11414086801011");
	}
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockChain.add(newBlock);
	}
}
