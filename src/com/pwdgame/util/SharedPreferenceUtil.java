package com.pwdgame.util;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.pwdgame.secure.Des;

/**
 * 配置文件工具类，方便存取
 * 
 * @author 谢源
 * 
 */
public final class SharedPreferenceUtil {
	
	
	/**
	 * 配置文件名称
	 */
    private static String RECORD_PREFERENCE_NAME="record";

    /**
     * 缓存文件
     */
    private static String CACHE_PREFERENCE_NAME="cache";
    
    private static Context mContext;
	
	private SharedPreferenceUtil(){
		
	}
	
	public static void init(Context context){
		mContext=context;
	}
	
	public static SharedPreferences  getSharedPreferences(){
		return mContext.getSharedPreferences( RECORD_PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	public static SharedPreferences  getCachePreferences(){
		return mContext.getSharedPreferences( CACHE_PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 获取记录文件的value为boolean的值
	 * 
	 * @param context
	 * @param key
	 * @return 该设置的结果，默认为false
	 */
	public static boolean getRecordBoolean(String key) {
		return getSharedPreferences().getBoolean(key, false);
	}
	/**
	 * 获取记录文件的value为string的值
	 * 
	 * @param context
	 * @param key
	 * @return 该设置的结果，默认为null
	 */
	public static String getRecordString(String key) {
		return getSharedPreferences().getString(key,null);
	}

	public static String getRecordStringEx(String key){
		String oldValue=getSharedPreferences().getString(key,null);
		if(oldValue!=null){
			try {
				String decrypt=Des.decrypt(oldValue);
				return decrypt;
			} catch (Exception e) {
				Logger.debug(e);
			}
				
		}		
		return oldValue;
	}
	
	/**
	 * 获取记录文件的value为boolean的值
	 * 
	 * @param context
	 * @param key
	 * @return 该设置的结果，默认为false
	 */
	public static int getRecordInteger(String key) {
		return getSharedPreferences().getInt(key, 0);
	}
	
	/**
	 * 获取记录文件的value为boolean的值
	 * 
	 * @param context
	 * @param key
	 * @return 该设置的结果，默认为false
	 */
	public static int getRecordInteger(String key,int def) {
		return getSharedPreferences().getInt(key, def);
	}
	
	public static Long getRecordLong(String key){
		return getSharedPreferences().getLong(key, 0L);		
	}
	
	/**
	 * 获取加密存储的integer
	 * @param key
	 * @param def
	 * @return
	 */
	public static int getRecordIntegerEx(String key,int def) {
		int resultValue=def;
		String enptyValue=getRecordStringEx(key);
		if(!TextUtils.isEmpty(enptyValue)){
			try {
				resultValue=Integer.parseInt(enptyValue);	
			} catch (NumberFormatException e) {
				resultValue=def;
			}				
		}		
		return resultValue;
	}
	/**
	 * 设置记录文件的value为boolean的值
	 * 
	 * @param context
	 * @param key
	 *            要设置的KEY
	 * @param value
	 *            要设置的value
	 */
	public static void setRecordBoolean(String key, Boolean value) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	public static void setRecordInteger(String key, int value) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putInt(key, value);
		editor.commit();
	}
	/**
	 * 加密存储int
	 * @param key
	 * @param value
	 */
	public static void setRecordIntegerEx(String key, int value) {
		setRecordStringEx(key,String.valueOf(value));
	}

	public static void setRecordLong(String key,Long value){
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
		
	public static void setRecordString(String... value){
		SharedPreferences.Editor editor = getSharedPreferences().edit();		
		int len=value.length;
		for(int i=0;i<len;i+=2){
			editor.putString(value[i], value[i+1]);			
		}
		editor.commit();
	}
	
	/**
	 * 使用加密的方式存储Record
	 * @param value
	 */
	public static void setRecordStringEx(String... value){
		SharedPreferences.Editor editor = getSharedPreferences().edit();		
		int len=value.length;
		for(int i=0;i<len;i+=2){
			try {
				String encrypt=Des.encrypt(value[i+1]);
				editor.putString(value[i], encrypt);
			} catch (UnsupportedEncodingException e) {
				Logger.debug("SharePreferenceUtil", "enpty error");				 
			}
			
		}
		editor.commit();
	}
	/**
	 * 保存任意类型键值到RECORD 配置文件，适配性强，效率稍低 
	 * 我这里只写了Boolean Integer Long String 的支持
	 * @param key
	 * @param value
	 *//*
	public static void setRecordObject(String key,Object value){
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		if(value instanceof Boolean)
			editor.putBoolean(key, (Boolean)value);
		else if(value instanceof Integer)
			editor.putInt(key, (Integer)value);
		else if(value instanceof Long)
			editor.putLong(key, (Long)value);
		else if(value instanceof String)
			editor.putString(key, (String)value);
		editor.commit();
	}*/
	
	public static void remove(String key){
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.remove(key);
		editor.commit();	
	}
	
/*	////////////////////////////////////////////cache
	public static void setCacheString(String key,String value){
		SharedPreferences.Editor editor = getCachePreferences().edit();						
		editor.putString(key,value);		
		editor.commit();
	}
	
	public static String getCacheString(String key){
		return getCachePreferences().getString(key, null);
	}*/
}
