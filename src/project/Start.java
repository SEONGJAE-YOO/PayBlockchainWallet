package project;

public class Start {

	public static void main(String[] args) {               
		new Login();
	}
}
/*
 DB이름 JavaWallet
테이블
 - 회원 -
  	id primary key not null
  	pw not null
  	name not null
  	(나중에 잡다한 개인정보 추가)
  	wallet1
  	wallet2
  	wallet3

 - 지갑 -
  	이름(계좌번호)
  	돈
  	생성날짜
            
 - 기록 -   
  	출금(보낸) 계좌(지갑)
  	입금(받는) 계좌(지갑)     
  	금액
  	날짜
  	
  	
  	거래내역 테이블 만들기
  	조회 부분 만들기
  	지갑 조회 만들기
 */


