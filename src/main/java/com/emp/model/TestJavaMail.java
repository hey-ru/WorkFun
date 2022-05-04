package com.emp.model;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import java.util.*;

public class TestJavaMail {
	
	
	// 1.step 資料 1.寄件者 2.寄件者電子信箱密碼()  3.收件者 4.信件標題 5.內容
 // GMail user name (just the part before "@gmail.com")
 private static String USER_NAME = "s80532s@gmail.com"; 
 private static String PASSWORD = "yoaqwrnddbgnyger"; // GMail password
 private static String RECIPIENT = "s80532ss@gmail.com";
 private static String SUBJECT = "test";
 private static String TXT = "<h1>testjavamail";
 
 

 public static void main(String[] args) {
	 TestJavaMail mail=new TestJavaMail();
	 mail.sendMail();
	 
	 
 // TODO Auto-generated method stub 
//        Scanner scanner = new Scanner(System.in);
//        
//        System.out.println("請輸入USER_NAME：");
//        USER_NAME = scanner.nextLine();
//        System.out.println("請輸入PASSWORD：");
//        PASSWORD = scanner.nextLine();
//        //System.out.println("您輸入的字串一為：\n" + USER_NAME+"\n"+PASSWORD );
//        
//        
// String from = USER_NAME;
// String pass = PASSWORD;
// String[] to = { RECIPIENT }; // list of recipient email addresses
// String subject = "Java send mail example";
// String body = "Welcome to JavaMail!";
//
// sendMail(from, pass, to, subject, body);
 }

 public void sendMail() {
	 
 Properties prop = System.getProperties();
// 外寄郵件 (SMTP) 伺服器	
// smtp.gmail.com
//
// 需要安全資料傳輸層 (SSL)：是
//
// 需要傳輸層安全性 (TLS)：是 (如果可用)
//
// 需要驗證：是
//
// 安全資料傳輸層 (SSL) 通訊埠：465
 //設定連線方式為stmp
 prop.setProperty("mail.transport.protocol", "smtp");
 //設定為gmail
 prop.setProperty("mail.host", "smtp.gmail.com");
 //gmail port為465
 prop.setProperty("mail.smtp.port","465");
 //連線是否需要驗證
 prop.setProperty("mail.smtp.auth","true");
 //設定安全資料傳輸
 prop.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 //設定安全資料傳輸port
 prop.setProperty("mail.smtp.socketFactory.port","465");
 
 prop.setProperty("mail.debug", "true");
 
 Session session=Session.getDefaultInstance(prop, new Authenticator() {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication(USER_NAME, PASSWORD);
	}
	 
 });
 
 MimeMessage message=new MimeMessage(session);
 
 try {
	message.setSender(new InternetAddress(USER_NAME));
	message.setRecipient(RecipientType.TO, new InternetAddress(RECIPIENT));
	message.setSubject(SUBJECT);
	message.setContent(TXT, "text/html;charset=utf-8");
	
	Transport transport=session.getTransport();
	transport.send(message);
	transport.close();
	
	
	
	
} catch (AddressException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (MessagingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

 
 

}
}
