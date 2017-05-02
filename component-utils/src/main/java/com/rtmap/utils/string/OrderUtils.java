package com.rtmap.utils.string;

import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: OrderUtils 
 * @Description: 订单相关工具类
 * @author maoyun0903(maoyun0903@163.com)
 * @date 2015-4-18 上午11:41:27 
 * @version V1.0
 */
public class OrderUtils {

	private static final DateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 添加商品-生成指定字符串
	 */
	public static String generatorProduct(String productCode, String productNum, String productSale) {
		StringBuffer sb = new StringBuffer();
		sb.append(productCode).append("-").append(productNum).append("-").append(productSale);
		return sb.toString();
	}

	/**
	 * 生成商户订单号
	 * 	支付类型：1个字母（微信支付：T，支付宝：A）
	 *	商户ID：3位数字（商场：002）
	 *	时间戳：14位 年月日时分秒	20160914 143207
	 *	随机数：14位
	 */
	public static String genOutTradeNo(Long id) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("T")
				.append(_genStringById(id, 3))
				.append(FORMAT.format(new Date()))
				.append(randomInt(14));

		return buffer.toString();
	}

	public static String randomInt(int length) {
		String base = "0123456789";
		Random random = new Random();
		final StringBuffer buffer = new StringBuffer();

		for(int i = 0; i < length; ++i) {
			int number = random.nextInt(base.length());
			buffer.append(base.charAt(number));
		}

		return buffer.toString();
	}

	/**
	 * 根据ID生成指定长度的字符串
	 * 如果id值为null或者小于1,直接用0来补全
	* @Description:
	* @return String
	 */
	private static String _genStringById(Long id , Integer num){
		StringBuffer sb = new StringBuffer();
		if(null == id || id < 1){
			for (int i = 0; i < num; i++) {
				sb.append("0");
			}
		}else{
			String d = id.toString();
			int l = d.length();
			for (int i = 0; i < num - l; i++) {
				sb.append("0");
			}
			sb.append(d);
		}

		return sb.toString();
	}

	/**
	 * @Description: 截取后六位作为提货码
	 * @param outTradeNo
	 * @return String
	 */
	public static String subOutTradeNo(String outTradeNo) {
		return outTradeNo.substring(outTradeNo.length() - 6, outTradeNo.length());
	}

	/**
	 * @Description: 根据时间来生成财务流水号
	 * @return
	 * @return String
	 */
	public static String generatorTimeSerialNumber() {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
		return format.format(new Date());
	}

	/**
	 * 获取一定长度的随机字符串
	 * @param length 指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String randomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			buffer.append(base.charAt(number));
		}
		return buffer.toString();
	}

	/**
	 * @Description: 解析形如：20150416220021 成 2015-04-16 22:00:21
	 * @return
	 * @return String
	 */
	public static Date parseStringToDate(String string){
		return DateTimeFormat.forPattern("yyyyMMddHHmmss").parseDateTime(string).toDate();
	}
}
