package com.hatsunemiku.data.util;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{

	static final Logger logger = LogManager.getLogger(Utils.class);

	public static Date getAddDate(int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getAddDate(Date time, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date getDate(String time, String format)
	{
		try
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			Date date = simpleDateFormat.parse(time);
			return date;
		} catch (Exception e)
		{
		}
		return null;
	}

	public static String getDateString(Date time, String format)
	{
		try
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.format(time);
		} catch (Exception e)
		{
		}
		return null;
	}

	public static String uuid()
	{
		UUID uuid = UUID.randomUUID();
		long mostSigBits = uuid.getMostSignificantBits();
		long leastSigBits = uuid.getLeastSignificantBits();

		return (digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4)
				+ digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12));
	}

	public static boolean isInt(String number)
	{
		String regex = "^-?[0-9]*$";
		return pattern(number, regex);
	}

	public static boolean isDouble(String number)
	{
		String regex = "^-?[0-9]*.?[0-9]*$";
		return pattern(number, regex);
	}

	public static boolean isPhone(String phone)
	{
		String regex = "^[1][3-9]+\\d{9}$";
		return pattern(phone, regex);
	}

	public static boolean isCurrency(String charge)
	{
		String regex = "^[0-9]\\d*\\.?\\d*$|^0\\.\\d*[1-9]\\d*$";
		return pattern(charge, regex);
	}

	public static boolean isMail(String mail)
	{
		String regex = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		return pattern(mail, regex);
	}

	public static boolean isPermitUrl(String url, String str)
	{
		String regex = ".*?/" + str + "/.*?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	public static String getJsonResult(Object data)
	{
		return JSON.toJSONString(data);
	}

	public static String getJsonResult(Object res, Object msg)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", res);
		map.put("msg", msg);
		return JSON.toJSONString(map);
	}

	public static String getJsonResult(Object res, Object code, Object msg)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", res);
		map.put("code", code);
		map.put("msg", msg);
		return JSON.toJSONString(map);
	}

	public static String getJsonResult(Object res, Object code, Object msg, Object url)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", res);
		map.put("code", code);
		map.put("msg", msg);
		map.put("url", url);
		return JSON.toJSONString(map);
	}

	public static String verifyCode()
	{
		int i = (int) (Math.random() * 10000);
		String verifyCode = i + "";
		while (verifyCode.length() < 4)
		{
			verifyCode = "0" + verifyCode;
		}
		return verifyCode;
	}

	private static String digits(long val, int digits)
	{
		long hi = 1L << (digits * 4);
		return Long.toHexString(hi | (val & (hi - 1))).substring(1);
	}

	private static boolean pattern(String str, String regex)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// 封装统一的Session中获取端口号
	public static String[] portArray(String serviceId)
	{
		String regex = "[0-9]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(serviceId);
		ArrayList portArrayList = new ArrayList();
		while (matcher.find())
		{
			portArrayList.add(matcher.group(0));
		}
		String portStr = null;
		for (int i = 0; i < portArrayList.size(); i++)
		{
			if (i >= 1)
				portStr = portStr + "" + portArrayList.get(i).toString();
			else
				portStr = portArrayList.get(i).toString();

		}
		// 端口号四个一组存入数组中便于使用
		int len = portStr.length() / 4;
		ArrayList<String> port = new ArrayList<String>();
		for (int i = 0; i < len; i++)
		{
			port.add(portStr.substring(0 + (i * 4), 4 + (i * 4)));
		}
		return port.toArray(new String[len]);
	}

	public static String getRamdonKey() // 获取随机唯一UUID-key(含大小写字母，数字)
	{
		String uuid = UUID.randomUUID().toString().replace("-", "");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < uuid.length(); i++) // 循环遍历字符串
		{
			if (Character.isDigit(uuid.charAt(i))) // 用char包装类中的判断数字的方法判断每一个字符
				sb.append(uuid.charAt(i));

			if (Character.isLetter(uuid.charAt(i))) // 用char包装类中的判断字母的方法判断每一个字符
			{
				if ((int) (Math.random() * 100) % 2 == 0)
					sb.append(String.valueOf(uuid.charAt(i)).toLowerCase());
				else
					sb.append(String.valueOf(uuid.charAt(i)).toUpperCase());
			}
		}
		return sb.toString();
	}

	public static void main(String[] args)
	{
		// System.out.println(getRamdonKey());
		// double l = 0.24;
		// for (int i = 0; i < 20; i++) {

		// }
		// System.out.println(isPhone("13910253932"));
		// System.out.println(isPermitUrl("mang/", "mang"));
		// System.out.println(isMail("13910253932@139.com"));
		// String num = "-0123";
		// System.out.println(isInt(num));
		// System.out.println(Integer.valueOf(num));
		// String d = "-.012307";
		// System.out.println(isDouble(d));
		// System.out.println(Double.valueOf(d));
		// Double cost=0.12455;
		// System.out.println(new BigDecimal(cost*100).setScale(0,
		// BigDecimal.ROUND_HALF_UP).intValue());
	}
}
