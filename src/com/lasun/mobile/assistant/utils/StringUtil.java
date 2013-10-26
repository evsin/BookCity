/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-10-24 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.utils;

import java.util.regex.Pattern;

/**
 * <pre>
 * 业务名:
 * 功能说明: 
 * 编写日期:	2011-10-24
 * 作者:	 刘斌
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class StringUtil {

	/**
	 * 
	 * 方法说明：清除传入字符串中的空格
	 * 
	 * @return
	 */
	public static String clearBlank(String input) {

		StringBuffer sb = new StringBuffer(input);
		while (sb.indexOf(" ") != -1) {
			sb.deleteCharAt(sb.indexOf(" "));
		}
		return sb.toString();
	}

	/**
	 * * * 方法说明：清除input字符串中的指定字符 * * @param input 要操作的字符串 * @param
	 * specialCharacter 要清除的字符 * @return 处理后的字符串
	 */
	public static String clearSpecialCharacter(String input,
			char specialCharacter) {
		StringBuffer buffer = new StringBuffer(input);
		for (int i = buffer.length() - 1; i >= 0; i--) {
			if (buffer.charAt(i) == specialCharacter) {
				buffer.deleteCharAt(i);
				i++;
			}
		}
		return buffer.toString().trim();
	}
	/**
	 * 
	 * 方法说明：用来格式化后台传过来的地址信息
	 *
	 * @param input要格式的数据  removeBars 去除横杠后的数据 areainfo省市县数据address详细地址formatArray要返回的数组
	 * @return
	 */
	public static String[] formatStringToDisplay(String input){
		String removeBars,areainfo,address;
		String[] formatArray;
		String[] shuchu=new String[2];
		if(input!=null&&!input.equals("")){
		removeBars=clearSpecialCharacter(input,'-');
		formatArray=removeBars.split(",");
		areainfo=formatArray[0];
		if(formatArray.length>1&&formatArray[1]!=null&&!formatArray[1].equals("")){
		address=formatArray[1];}else{
			address="";
		}
		int size=areainfo.indexOf("总部");
		if(size==0){
			areainfo=areainfo.substring(2);
		}
		if(areainfo.length()<=address.length()){
			int number=address.indexOf(areainfo);
			if(number==0){
				address=address.substring(areainfo.length());
			}
		}
		shuchu[0]=areainfo;
		shuchu[1]=address;
		return shuchu;
		}else{
	   return null;
		}
	}
/**
 * 
 * 方法说明：用来格式化用户输入的信息
 *
 * @param sheng 省
 * @param shi 市
 * @param xian  县 
 * @param address  详细地址
 * @return 输出加上"-"后的文字信息
 */
	public static String inputFormatString(String sheng,String shi,String xian,String address){
		String realaddress;
		if(xian.equals("")){
			realaddress="总部-"+sheng+"-"+shi+","+address;
		}else{
			realaddress=sheng+"-"+shi+"-"+xian+","+address;
		}
		return realaddress.trim();
	}
	
	/**
	 * 检测account若为手机号将第4位到第8位以*号表示，否则返回原值
	 * @param account 用户帐号
	 * @return result
	 * NOTE:凡是满足手机正则表达示的帐号均视为手机号，如果用户注册的帐号满足手机号规则，一样会被以*号表示，会有误差
	 */
	public static String coverPhoneNum(String account){
		if(account!=null){
			if(Pattern.matches("^(1(([35][0-9])|(47)|[8][0126789]))\\d{8}$", account)){
				StringBuffer sb=new StringBuffer(account);
				return sb.replace(3, 7, "****").toString();
			}
			return account;
		}
		return "unknown";
	}
	
	
	/**
	 * 传入省市区和详细地址，将其组合成  河北-石家庄-长安区,幸福北路8号
	 * @return
	 */
	public static String encodeAddress(String pro,String city,String country,String detail){
		
		if(country.equals("")){
			country=" ";
		}
		
		return pro+"-"+city+"-"+country+","+detail;
	}
	
	
	
	/**
	 * 将 河北-石家庄-长安区,幸福北路8号类型的地址解析为一个数据，分别包含省、市、区、详细
	 * @param address
	 * @return
	 */
	public static String[] decodeAddress2Ary(String address){
		String[] resultAry = new String[4];
		int splitPoint = address.indexOf(",");
		String areas[] = address.substring(0, splitPoint).split("-");
		if (areas[0].equals("总部")) {
			resultAry[0] = areas[1];
			resultAry[1] = areas[2];
			resultAry[2] = " ";
		} else {
			resultAry[0] = areas[0];
			resultAry[1] = areas[1];
			resultAry[2] = areas[2];
		}
		resultAry[3] = address.substring(splitPoint+1);
		return resultAry;
	}
	
	/**
	 * 将 河北-石家庄-长安区,幸福北路8号类型的地址解析为一个字符串
	 * @param address
	 * @return
	 */
	public static String decodeAddress2Str(String address){
		String[] resultAry = decodeAddress2Ary(address);
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<resultAry.length;i++){
			buffer.append(resultAry[i]);
		}
		return buffer.toString();
	}
}
