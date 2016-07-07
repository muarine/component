/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.utils.time;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.junit.Test;
/**
 * 
 * JodaTimeUtil. Joda时间操作工具类常用的api
 * 
 * @author muarine
 * @since 0.1
 */
public class JodaTimeUtil {
//	private static final Logger log = LoggerFactory.getLogger(JodaTimeUtil.class);
//	
//	private static DateTime dateTime = new DateTime();
	/**
	 * long -> Date 
	 * @param time
	 * @return
	 */
	public static Date parseLong(Long time){
		DateTime dateTime = new DateTime(time);
		return dateTime.toDate();
	}
	/**
	 * 
	 * 解析Long -》 String（pattern : yyyy-MM-dd） 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String parseLong(Long time , String pattern){
		return DateTimeFormat.forPattern(pattern).print(time);
	}
	/**
	 * 解析String -》 Date 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parseString(String date , String pattern){
		return DateTime.parse(date,DateTimeFormat.forPattern(pattern)).toDate();
	}
	/**
	 * Date -> String (pattern:yyyy-MM-dd)
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String parseDate(Date date , String pattern){
		return new DateTime(date.getTime()).toString(pattern);
	}
	@Test
	public void testStart(){
		// long -> Date / String
		System.out.println(JodaTimeUtil.parseLong(new Date().getTime(), "yyyy-MM-dd HH:mm:ssS"));
		System.out.println(JodaTimeUtil.parseLong(new Date().getTime()));
		// String -> Date
		System.out.println(JodaTimeUtil.parseDate(new Date(), "yyyy-MM-dd"));
		System.out.println(JodaTimeUtil.parseString("2015-10-11 13:44:22","yyyy-MM-dd HH:mm:ss"));
		
		// Date -> String
		
		long time = System.currentTimeMillis();
		DateTime dt = new DateTime(1436284800000L);
		Date date = new Date(1436284800000L);
		System.out.println(date);
		DateTime dt4 = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("2015-06-22");
		
		System.out.println(dt4.toString("yyyy-MM-dd"));
//		Date date = dt.toDate();
		System.out.println(dt.toString("yyyy-MM-dd HH:mm:ss"));
		// 当前时间往后+2小时
		DateTime dt1 = dt.plusHours(2);
		// 当前时间往前-2小时
		DateTime dt2 = dt.minusHours(2);
		System.out.println(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		System.out.println(dt1.getMillis());
		System.out.println(dt1.getMillis() - time);
		DateTime dt3 = dt.plus(dt1.getMillis());
		System.out.println(dt3.getMillis());
		System.out.println(dt3.getMillisOfDay());
		System.out.println(dt2.toString("yyyy-MM-dd HH:mm:ss"));
		// long -> String
		Period period = new Period(System.currentTimeMillis());
		PeriodFormatter formatter = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours()
	    .appendLiteral(":").appendMinutes().appendLiteral(":").appendSeconds().toFormatter();
		System.out.println(period.toString(formatter));
		 String countDate = new DateTime().plusDays(-1).toString("yyyy-MM-dd");
		 System.out.println(countDate);
		 LocalDate localDate = new LocalDate();
		 String start = localDate.toDateTimeAtStartOfDay().toString("yyyy-MM-dd HH:mm:ss");
		 System.out.println(start + "..." + localDate.toDateTimeAtStartOfDay().plusDays(1).minusSeconds(1).toString("yyyy-MM-dd HH:mm:ss"));
		 
	}
	
	
}
