package org.kkdev.cueb.universal;

import java.util.Random;

public class UniversalUtil {
	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	public static String generateId(int length) {
		return generateString(new Random(),"ABCDEFGHIJKLMNOPQRSTUVWXYZqazwsxedcrfvtgbyhnujmikolp0987654321",length);
	}
	public static Class<?> GetCaller(int deepth) {
		class WhoCalledSecurityManager extends SecurityManager {
			private static final int OFFSET = 2;
			public Class<?> getCallingClass(int depth) {
				return getClassContext()[OFFSET + depth];
			}
		}
		return new WhoCalledSecurityManager().getCallingClass(deepth);
	}
	
	final protected static char[] hexArray = "0123456789abcdef".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
