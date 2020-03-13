package br.com.mensagem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DataUtil {

	private static final Logger logger = LogManager.getLogger(DataUtil.class);
	
	private static TimeZone tz = TimeZone.getTimeZone("GMT-3");
	
	private static int GMT = -3;
	
	//2020-01-07 14:29:15
	private static SimpleDateFormat sdfBanco = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat dateFormatDDMMMHHmm = new SimpleDateFormat("dd MMM HH:mm");  
	
	
	public static Date getDataAtual() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	public static String converterDataGMT3(String data) {
		if(data == null)return null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdfBanco.parse(data));
			cal.add(Calendar.HOUR_OF_DAY, GMT );
			return dateFormatDDMMMHHmm.format(cal.getTime()); 
		} catch (ParseException e) {
			logger.error("Erro ao converter data " + e);
			return null;
		}
		
	}
	
	
	public static String converteData(Date date) {
		if(date == null)return null;
		return dateFormatDDMMMHHmm.format(date); 
	}
	

	/**
	 * emtrada yyyy-MM-dd HH:mm:ss.SSS
	 * @param date
	 * @return
	 */
	public static String formataData(String date) {
		try {
			String[] dt = date.split(" ");
			String[] d = dt[0].split("-");
			String[] t = dt[1].split("[.]");
			return d[2] +"/"+ d[1] +"/"+ d[0] +" "+t[0];
		}catch (Exception e) {
			logger.error("Erro ao formatar data " + e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println( converterDataGMT3("2020-01-11 19:24:46.512733") );
	}
}
