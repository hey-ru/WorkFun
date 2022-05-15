package com.util;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.groupbuy.model.GroupBuyService;
import com.groupbuy.model.GroupBuyVO;
import com.groupbuylist.model.GroupBuyListService;
import com.secondHand.model.SecondHandService;
import com.secondHand.model.SecondHandVO;

public class SecondHandRun implements Runnable {
	

	public SecondHandRun() {
	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SecondHandService secondHandService=new SecondHandService();
		List<SecondHandVO> list=secondHandService.getAllDate();
		
		Iterator<SecondHandVO> iterator=list.iterator();
		while (iterator.hasNext()) {
			SecondHandVO secondHandVO = (SecondHandVO) iterator.next();
			
			Integer second_hand_id= secondHandVO.getsecond_hand_id();
//			Long targetTime=new GregorianCalendar().getTime().getTime();
		//System.out.println(secondHandVO.getStart_time());
			if(secondHandVO.getIs_deal()==0) {
				
				
		if(secondHandVO.getStart_time().before(new Timestamp(new GregorianCalendar().getTime().getTime()))) {
			//System.out.println(secondHandVO.getStart_time().before(new Timestamp(new GregorianCalendar().getTime().getTime())));
			secondHandVO.setIs_deal(1);
			secondHandService.updateSecondHand(secondHandVO);
		}
			}
			
			if(secondHandVO.getIs_deal()==1) {
				if(secondHandVO.getEnd_time().before(new Timestamp(new GregorianCalendar().getTime().getTime()))) {
				//	System.out.println(secondHandVO.getStart_time().before(new Timestamp(new GregorianCalendar().getTime().getTime())));
					
					if(secondHandService.getOneById(second_hand_id).getBidVO().getBidder()==0) {
					
					System.out.println("設定成3");
					
					secondHandVO.setIs_deal(3);
					secondHandService.updateSecondHand(secondHandVO);
					
					
					}
					else {
						secondHandVO.setIs_deal(2);
						secondHandService.updateSecondHand(secondHandVO);
						System.out.println("設定成2");
						secondHandVO.setBid_winner(secondHandService.getOneById(second_hand_id).getBidVO().getBidder());
						secondHandVO.setDeal_price(secondHandService.getOneById(second_hand_id).getBidVO().getPrice());
					
					}
				
					
					
					
				
				
			}
		
		
			}
		//System.out.println(secondHandVO.getStart_time().after(new Timestamp(new GregorianCalendar().getTime().getTime())));
		//secondHandVO.getStart_time().after(new Timestamp(new GregorianCalendar().getTime().getTime()));
	
					
		}
		
		
		
	//	System.out.println(new GregorianCalendar().getTime().getTime());
		
//	if(目標秒數<現在秒數)	
//	{
 
	}
//		TIMERSERVICE.shutdown();
		
//	}
	
//	public EmpVO run(EmpVO empVO) {
//		// TODO Auto-generated method stub
//		
//		
//		
//		
//		
//		
//		
//		
//		System.out.println(job);
//		return empVO;
//		
//	}
//	
//	public static void main(String[] args) {
//		System.out.println(now());
//	System.out.println(now().toLocalDate());
//	java.util.Date date=new java.util.Date();
//	
//	System.out.println(date.getTime());
//	//Date date2=new Date(94,5,19);
//	Date date2=new Date(date.getTime());
//	
//	
//	
//	Timestamp timestamp=new Timestamp(System.currentTimeMillis());
//	System.out.println("timestamp"+timestamp);
//	System.out.println(timestamp.getHours()+"     "+timestamp.getMinutes());
//	
//	//date2.	System.out.println(date2);
//	System.out.println(date2);
//	
//	
//	System.out.println(new GregorianCalendar().getTime().getTime());
//	
//	
//	System.out.println(System.currentTimeMillis());
//		
//	}
	
	public static void main(String[] args) {
		GroupBuyService gbSvc = new GroupBuyService();
	    GroupBuyVO groupBuyVO = gbSvc.getOneGB(1002);
	Timestamp aTimestamp= groupBuyVO.getEnd_time();
	System.out.println(aTimestamp);
	Long aLong=aTimestamp.getTime();
	Long b=new GregorianCalendar().getTime().getTime();
	Long cLong=System.currentTimeMillis();
	
   Timestamp bTimestamp=new Timestamp((aLong-b));
   System.out.println(aLong);
   System.out.println(b);
   
   
  
	
	
	
	}
	
	
}
