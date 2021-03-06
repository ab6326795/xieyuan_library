package com.pwdgame.util;

import java.util.Locale;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class DeviceInfo {

	private DeviceInfo() {
	}

	/**
	 * 初始化设备信息
	 * 
	 * @param context
	 */
	public static void init(final Context context) {
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager
					.getActiveNetworkInfo();

			WindowManager windowManager = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);

			packName = context.getPackageName();
			sign = Utility.getSignature(context,
					context.getPackageName());
			MAC = wifiInfo.getMacAddress();
			/**
			 * 这里需要READ_PHONE_STATE的权限，
			 */
			try {
				SIMState = telephonyManager.getSimState();
				IMEI = telephonyManager.getDeviceId();				
				IMSI = telephonyManager.getSubscriberId();
				phoneNumber = telephonyManager.getLine1Number();
			} catch (Exception e) {
				// TODO: handle exception
			}
			phoneModel = Build.MODEL.replaceAll(" ", "_"); // 替换所有空格，因为空格在HTTP中是不允许的
			brad = Build.BOARD;
			Object obj = Utility.getManiMetaData(context,
					"UMENG_CHANNEL");
			if (obj != null)
				Channel = String.valueOf(obj);

			if (networkInfo == null)
				APN = "null";
			else
				APN = networkInfo.getExtraInfo();
			language = Locale.getDefault().getLanguage();
			netwrokType = telephonyManager.getNetworkType();
			SDKVersion = Build.VERSION.SDK;
			SDKReleaseVersion = Build.VERSION.RELEASE;
			// bHasInstallPermission=MyPackageManager.hasInstallPermission(context);
			// channelCode = Utility.getManiMetaData(context,
			// "CHANNEL_CODE").toString();
			appVersionCode = Utility.getPackageVersionCode(context,
					context.getPackageName());
			appVersionName = Utility.getPackageVersionName(context,
					context.getPackageName());
			screenW = windowManager.getDefaultDisplay().getWidth();
			screenH = windowManager.getDefaultDisplay().getHeight();
			// memory=Utility.getTotalMemory();
			// CPU=Utility.getCPUName();
			DisplayMetrics metric = new DisplayMetrics();
			windowManager.getDefaultDisplay().getMetrics(metric);
			densityDpi = metric.densityDpi;
			density = metric.density;
		} catch (Exception e) {
			e.printStackTrace();
		}
	

	}

	/*
	 * private static String getProvidersName(String IMSI) { if (IMSI != null) {
	 * // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。 if
	 * (IMSI.startsWith("46000") || IMSI.startsWith("46002")) { providersName =
	 * "中国移动"; } else if (IMSI.startsWith("46001")) { providersName = "中国联通"; }
	 * else if (IMSI.startsWith("46003")) { providersName = "中国电信"; } else {
	 * providersName = "未知"; } } return providersName; }
	 */

	public static String ToString() {
		return "DeviceInfo [MAC=" + MAC + ", IMEI=" + IMEI + ", IMSI=" + IMSI
				+ ", phoneModel=" + phoneModel + ", phoneNumber=" + phoneNumber
				+ ", Channel=" + Channel + ", APN=" + APN + ",screenW="
				+ screenW + ",screenH=" + screenH + ",Memory=" + memory
				+ ",CPU=" + CPU + ", netwrokType=" + netwrokType
				+ " , SDKVersion=" + SDKVersion + ", SDKReleaseVersion="
				+ SDKReleaseVersion + ", bHasInstallPermission="
				+ bHasInstallPermission + ",sign=" + sign + ",packname="
				+ packName + ", appVersionCode=" + appVersionCode
				+ ", appVersionName=" + appVersionName + "]";
	}

	public static String getMAC() {
		return MAC;
	}

	/**
	 * IMEI由15位数字组成
	 * 
	 * @return
	 */
	public static String getIMEI() {
		/*
		 * if(IMEI==null) return "000000000000000";
		 */
		return IMEI;
	}

	public static String getIMSI() {
		return IMSI;
	}

	public static String getPhoneModel() {
		return phoneModel;
	}

	public static String getBradModel() {
		return brad;
	}

	public static String getPhoneNumber() {
		return phoneNumber;
	}

	public static String getLanguage() {
		return language;
	}

	public static int getNetwrokType() {
		return netwrokType;
	}

	public static String getSDKVersion() {
		return SDKVersion;
	}

	public static String getSDKReleaseVersion() {
		return SDKReleaseVersion;
	}

	public static boolean isbHasInstallPermission() {
		return bHasInstallPermission;
	}

	public static int getAppVersionCode() {
		return appVersionCode;
	}

	public static String getAppVersionName() {
		return appVersionName;
	}

	public static String getChannel() {
		return Channel;
	}

	public static String getAPN() {
		return APN;
	}

	public static int getScreenW() {
		return screenW;
	}

	public static int getScreenH() {
		return screenH;
	}

	public static String getMemory() {
		return memory;
	}

	public static String getCPU() {
		return CPU;
	}

	public static String getPackName() {
		return packName;
	}

	public static int getSign() {
		return sign;
	}

	/**
	 * 获取SIM卡状态，1不可用
	 * 
	 * @return
	 */
	public static int getSIMState() {
		return SIMState;
	}

	/**
	 * 获取屏幕DPI 如，240
	 * 
	 * @return
	 */
	public static int getDensityDpi() {
		return densityDpi;
	}

	public static float getDensity() {
		return density;
	}

	private static String MAC;
	private static String IMEI;
	private static String IMSI;
	private static int SIMState; // sim卡状态，1未插入

	private static String phoneModel;
	private static String brad;
	private static String phoneNumber;
	private static String Channel;
	private static String APN;

	private static String language;
	private static int netwrokType;
	private static String SDKVersion;
	private static String SDKReleaseVersion;
	private static boolean bHasInstallPermission;

	// 渠道和APP版本号本来不该放在这，但是为了方便就加在后面了
	private static int appVersionCode;
	private static String appVersionName;

	private static int screenW;
	private static int screenH;
	private static String memory;
	private static String CPU;
	private static String packName;
	private static int sign;
	private static int densityDpi;
	private static float density;

}
