package com.pwdgame.secure;
import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;

/*     */ public class Des {
/*     */ 
           private static String strDefaultKey = "@ddgbg./";
/*     */  public static byte[] encrypt(byte[] src, byte[] key)
/*     */   {
/*  12 */     if ((src == null) || (key == null)) return null;
/*     */     try
/*     */     {
/*  15 */       Cipher cipher = Cipher.getInstance("DES");
/*  16 */       cipher.init(1, getKey(key));
/*  17 */       return cipher.doFinal(src);
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*  21 */     return null;
/*     */   }
/*     */ public static byte[] encrypt(byte[] src) throws UnsupportedEncodingException
/*     */   {
	          byte[] key=strDefaultKey.getBytes();
/*  12 */     if ((src == null) || (key == null)) return null;
/*     */     try
/*     */     {
/*  15 */       Cipher cipher = Cipher.getInstance("DES");
/*  16 */       cipher.init(1, getKey(key));
/*  17 */       return cipher.doFinal(src);
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*  21 */     return null;
/*     */   }
/*     */   public static byte[] decrypt(byte[] src, byte[] key)
/*     */   {
/*  27 */     if ((src == null) || (key == null)) return null;
/*     */     try
/*     */     {
/*  30 */       Cipher cipher = Cipher.getInstance("DES");
/*  31 */       cipher.init(2, getKey(key));
/*  32 */       return cipher.doFinal(src);
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*  36 */     return null;
/*     */   }
/*     */   public static byte[] decrypt(byte[] src) throws UnsupportedEncodingException
/*     */   {
	           byte[] key=strDefaultKey.getBytes();
/*  27 */     if ((src == null) || (key == null)) return null;
/*     */     try
/*     */     {
/*  30 */       Cipher cipher = Cipher.getInstance("DES");
/*  31 */       cipher.init(2, getKey(key));
/*  32 */       return cipher.doFinal(src);
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*  36 */     return null;
/*     */   }
			public static String byte2hex(byte[] b) { // 二进制转字符串
				String hs = "";
				String stmp = "";
				for (int n = 0; n < b.length; n++) {
					stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
					if (stmp.length() == 1)
						hs = hs + "0" + stmp;
					else
						hs = hs + stmp;
				}
				return hs;
			}
			
			public static byte[] hex2byte(String str) { // 字符串转二进制
				if (str == null)
					return null;
				str = str.trim();
				int len = str.length();
				if (len == 0 || len % 2 == 1)
					return null;
			
				byte[] b = new byte[len / 2];
				try {
					for (int i = 0; i < str.length(); i += 2) {
						b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
					}
					return b;
				} catch (Exception e) {
					return null;
				}
			}
			
		    /**
		     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
		     * 
		     * @param arrBTmp
		     *            构成该字符串的字节数组
		     * @return 生成的密钥
		     * @throws java.lang.Exception
		     */
		    public static Key getKey(byte[] arrBTmp) {
		        // 创建一个空的8位字节数组（默认值为0）
		        byte[] arrB = new byte[8];

		        // 将原始字节数组转换为8位
		        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
		            arrB[i] = arrBTmp[i];
		        }

		        // 生成密钥
		        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		        return key;
		    }
		    /**
		     * 解密字符串
		     * 
		     * @param strIn
		     *            需解密的字符串
		     * @return 解密后的字符串
		     * @throws Exception
		     */
		    public static String decrypt(String strIn) throws Exception {
		        try {
		            return new String(decrypt(hex2byte(strIn)));
		        } catch (Exception e) {
		            return "";
		        }
		    }
		    
		    /**
		     * 加密字符串
		     * 
		     * @param strIn
		     *            需加密的字符串
		     * @return 加密后的字符串
		     * @throws UnsupportedEncodingException 
		     * @throws Exception
		     */
		    public static String encrypt(String strIn) throws UnsupportedEncodingException {
		        return byte2hex(encrypt(strIn.getBytes()));
		    }
		    
/*		    public static void main(String[] args) throws Exception{
		    	String ddd=encrypt("啊我大打我的哇洼地121312");
		    	System.out.println(ddd);
		    	String old=decrypt(ddd);
		    	System.out.println(old);
		    	
		    }*/
}