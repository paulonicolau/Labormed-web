package br.com.labormed.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Util {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

	static {
		nf.setMaximumFractionDigits(2); // O default e 3.
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);
	}

	public static boolean dataValida(String umaData) {
		try {
			if (umaData.length() != 10)
				return false;

			Integer.parseInt(umaData.substring(0, 2));
			if (!umaData.substring(2, 3).equals("/"))
				return false;
			Integer.parseInt(umaData.substring(3, 5));
			if (!umaData.substring(5, 6).equals("/"))
				return false;
			Integer.parseInt(umaData.substring(6, 10));

			sdf.setLenient(false);
			sdf.parse(umaData);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Timestamp strToDateTimesTamp(String umaData) {
		int dia = Integer.parseInt(umaData.substring(0, 2));
		int mes = Integer.parseInt(umaData.substring(3, 5));
		int ano = Integer.parseInt(umaData.substring(6, 10));

		return new Timestamp(java.sql.Date.valueOf(ano + "-" + mes + "-" + dia).getTime());
	}

	public static Date strToDateSQL(String umaData) {
		int dia = Integer.parseInt(umaData.substring(0, 2));
		int mes = Integer.parseInt(umaData.substring(3, 5));
		int ano = Integer.parseInt(umaData.substring(6, 10));

		return java.sql.Date.valueOf(ano + "-" + mes + "-" + dia);
	}

	public static java.util.Date strToDateUTIL(String umaData) {
		java.util.Date data = null;

		try {
			data = sdf.parse(umaData);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static String dateSQLToStr(Date umaData) {
		return sdf.format(umaData);
	}

	public static String TimeStampToStr(Timestamp umaData) {
		return sdtf.format(umaData);
	}

	public static String dateUtilToStr(java.util.Date umaData){
		if (umaData == null)
			return "";
		else
		{	return sdf.format(umaData);
		}
	}

	public static double strToDouble(String valor) throws NumberFormatException {
		if (valor == null || "".equals(valor)) {
			return 0;
		} else {
			return Double.parseDouble(valor);
		}
	}

	public static String doubleToStr(double valor) {
		return nf.format(valor);
	}

	public static Integer converterSenhaUsuario(String pSenha){
	  Integer lResult = 0;
	  String senha = pSenha.toUpperCase();
	  int tam = senha.length();
	  
	  for(int i = 0; i< tam; i++){
		  int charInt = (int)senha.charAt(i);
		  lResult = lResult * 128 + (charInt ^ 85);
	  }
	  return lResult;//admin -> 13027329 | cesario -> 1652674074
	  		  
	}
}