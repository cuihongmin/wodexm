package com.cui.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * <p>Tile:订单工具</p>
 * <p>Description:</p>
 * <p>Company:www.itcast.com</p>
 * <p>Email:wuyilei@huaxiafinance.com</p>
 * @author 华夏信财股权投资管理有限公司  吴轶雷
 * @date 2015-10-11 下午8:55:54
 * @version 1.0
 */
public class OrderUtils {
	
	public static String makeOrderNo(){

		int no1=(int) (Math.random()*100);
		int no2=(int) (Math.random()*100);
		int no3=(int) (Math.random()*100);
		while(no1==no2){
			no2=(int) (Math.random()*100);
		}
		while(no2==no3){
			no3=(int) (Math.random()*100);
		}
		while(no1==no3){
			no3=(int) (Math.random()*100);
		}
		String no=no1+""+no2+""+no3;
		if(no.length()==5){
			no = no+"0";
		}else if(no.length()==4){
			no = no+"00";
		}else if(no.length()==3){
			no = no+"000";
		}else if(no.length()==2){
			no = no+"0000";
		}else if(no.length()==1){
			no = no+"00000";
		}else if(no.length()==0){
			no = no+"000000";
		}
		
		long currTime = System.currentTimeMillis();
		String tstringNo=(currTime+"");
		int len = tstringNo.length();
		StringBuilder stringNo=new StringBuilder("");
		stringNo.append(no);
		for(int i =8; i <=len-1; i++) {
		    stringNo.append(tstringNo.charAt(i));
		}
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY");
		String order = "HXXC"+stringNo.toString();
		return order;
	}
	
	//生成合同编号
	public static String makeContractNo(){
		int no1=(int) (Math.random()*100);
		int no2=(int) (Math.random()*100);
		int no3=(int) (Math.random()*100);
		while(no1==no2){
			no2=(int) (Math.random()*100);
		}
		while(no2==no3){
			no3=(int) (Math.random()*100);
		}
		while(no1==no3){
			no3=(int) (Math.random()*100);
		}
		String no=no1+""+no2+""+no3;
		if(no.length()==5){
			no = no+"0";
		}else if(no.length()==4){
			no = no+"00";
		}else if(no.length()==3){
			no = no+"000";
		}else if(no.length()==2){
			no = no+"0000";
		}else if(no.length()==1){
			no = no+"00000";
		}else if(no.length()==0){
			no = no+"000000";
		}
		
		long currTime = System.currentTimeMillis();
		String tstringNo=(currTime+"");
		int len = tstringNo.length();
		StringBuilder stringNo=new StringBuilder("");
		stringNo.append(no);
		for(int i =8; i <=len-1; i++) {
		    stringNo.append(tstringNo.charAt(i));
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String order = "HXXC"+sdf.format(new Date())+"-"+stringNo.toString();
		return order;
	}
	//生成合同编号
	public static String makeNo(){
		int no1=(int) (Math.random()*100);
		int no2=(int) (Math.random()*100);
		int no3=(int) (Math.random()*100);
		while(no1==no2){
			no2=(int) (Math.random()*100);
		}
		while(no2==no3){
			no3=(int) (Math.random()*100);
		}
		while(no1==no3){
			no3=(int) (Math.random()*100);
		}
		String no=no1+""+no2+""+no3;
		if(no.length()==5){
			no = no+"0";
		}else if(no.length()==4){
			no = no+"00";
		}else if(no.length()==3){
			no = no+"000";
		}else if(no.length()==2){
			no = no+"0000";
		}else if(no.length()==1){
			no = no+"00000";
		}else if(no.length()==0){
			no = no+"000000";
		}
		
		long currTime = System.currentTimeMillis();
		String tstringNo=(currTime+"");
		int len = tstringNo.length();
		StringBuilder stringNo=new StringBuilder("");
		stringNo.append(no);
		for(int i =8; i <=len-1; i++) {
		    stringNo.append(tstringNo.charAt(i));
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String order = sdf.format(new Date())+""+stringNo.toString();
		return order;
	}
	
	public static String makeTransId(String mode){
		if(mode==null)
			mode = "_";
		else {
			mode = mode.substring(0,1);
		}
		Date currTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		String TradeDate = formatter.format(currTime); // 订单日期
		String b= (Math.abs(new Random().nextLong()) % 10000)+"";
		for (int i = 0; i <= 5-b.length(); i++) {
			b+="0";
		}
		String transId = TradeDate+mode+b;
		return transId;
	}
	
	public static String getShopOrder(String head,Integer companyId){
		String id = companyId+"";
		int count = id.length();
		for (int i = 0; i < 3-count; i++) {
			id = "0"+id;
		}
		Date currTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss",Locale.CHINA);
		String TradeDate = formatter.format(currTime); // 订单日期
		int number= (int)(Math.random()*100);
		String b = number+"";
		int length = b.length();
		if(b.length()<2){
			for (int i = 0; i < 2-length; i++) {
				b+="0";
			}
		}
		String transId = head+id+TradeDate+b;
		return transId;
	}
	
	
	public static String getLogisticsOrder(Integer companyId){
		String id = companyId+"";
		int count = id.length();
		for (int i = 0; i < 4-count; i++) {
			id = "0"+id;
		}
		System.out.println(id.length());
		int number= (int)(Math.random()*100000);
		String b = number+"";
		int length = b.length();
		if(length<6){
			for (int i = 0; i < 6-length; i++) {
				b+="0";
			}
		}
		String transId = id+b;
		return transId;
	}
	
	
	public static String getBidOrder(Integer scene,int number){
		String id = number+"";
		int count = id.length();
		for (int i = 0; i < 3-count; i++) {
			id = "0"+id;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd",Locale.CHINA);
		String TradeDate = formatter.format(new Date()); // 订单日期
		String transId = TradeDate+scene+id;
		return transId;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			String text = getBidOrder(1, 1);
			System.out.println(text+"======="+text.length());
		}
	}
}
