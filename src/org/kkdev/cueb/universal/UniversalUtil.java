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
}
