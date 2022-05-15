package com.util;
import static com.util.TimerThreadPool.*;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.secondHand.model.SecondHandVO;
import com.util.*;

public class TimerService {
private	long initialDelay;
private long delay;
public long getInitialDelay() {
	return initialDelay;
}
public void setInitialDelay(long initialDelay) {
	this.initialDelay = initialDelay;
}
public long getDelay() {
	return delay;
}
public void setDelay(long delay) {
	this.delay = delay;
}

public void SecondHand(long initialDelay, long delay) {
//public Future<?> timerTest2(long initialDelay, long delay) {
	

// TIMERSERVICE.schedule(new CommandTest(empVO,job,empstatus.byteValue()),delay, TimeUnit.SECONDS);
	//Future<?> a=(Future<?>)TIMERSERVICE.schedule(new CommandTest(),delay,TimeUnit.SECONDS);
	
	
	TIMERSERVICE.scheduleAtFixedRate(new SecondHandRun(), initialDelay, delay,TimeUnit.SECONDS);
}

//EmpVO empVO=new EmpService().getOneEmp(1001);
//String aString="改變員工狀態";
//	
//TimerActionMedthod timerActionMedthod=new TimerActionMedthod();	
//	
//Future<?> a=timerActionMedthod.timerTest2(empVO,aString,2,10);
////	a.cancel(true);
//	
//	System.out.println(a.isDone());
//	
//	
//	a.cancel(false);

public void Booking(long initialDelay, long delay) {	
		
		TIMERSERVICE.scheduleAtFixedRate(new BookingRun(), initialDelay, delay,TimeUnit.SECONDS);
	}

}



	

