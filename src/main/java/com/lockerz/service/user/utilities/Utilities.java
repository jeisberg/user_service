package com.lockerz.service.user.utilities;

import java.util.Random;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public class Utilities {
	
	// need this
	private static final char[] HEX_CHARS = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		'a', 'b', 'c', 'd', 'e', 'f', 
	};
	
	// need these
	public static final int SEED_SIZE = 10;
	public static final int HASH_ITERATIONS = 1000;
	public static final int ENCODED_SEED_SIZE = 14;
	
	public static boolean isNullOrEmpty(String target) {
		// return here
		return target == null || target.trim().equals("");
	}
	
	public static byte[] extractSeed(String seedAndHash) {
		// return here
        return Base64.decodeBase64(seedAndHash.substring(0, ENCODED_SEED_SIZE));
    }
	
	public static String extractPasswordHash(String seedAndHash) {
		// return here
        return seedAndHash.substring(ENCODED_SEED_SIZE, seedAndHash.length());
    }
	
	public static String hashPassword(String password, byte[] seed) {
		// need this
        byte[] input = new byte[] {};
        // try
        try {
        	// get the digest here
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // reset the digest
            digest.reset();
            // update here
            digest.update(seed);
            // get the input array here
            input = digest.digest(password.getBytes("UTF-8"));
            // loop here
            for (int i = 0; i < HASH_ITERATIONS; i++) {
            	// reset here
                digest.reset();
                // get the input here
                input = digest.digest(input);
            }
        } catch (Exception e) {
            // return null
        	return null;
        }
        // return here
        return Base64.encodeBase64URLSafeString(input);
    }
	
	public static byte[] md5(String input) {
		// try
        try {
        	// get the digest here
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            // get the bytes here
            digest.update(input.getBytes("ISO8859_1"));
            // return here
            return digest.digest();
        // ignore here
        } catch (NoSuchAlgorithmException e) { 
        }
        catch (UnsupportedEncodingException e) {
        }
        // return slug here
        return new byte[] {};
    }
	
	public static String asHex(byte hash[]) {
		// get the chars here
        char buf[] = new char[hash.length * 2];
        // loop here
        for (int i = 0, x = 0; i < hash.length; i++) {
        	// set the buffer here
            buf[x++] = HEX_CHARS[(hash[i] >>> 4) & 0xf];
            // set the buffer here
            buf[x++] = HEX_CHARS[hash[i] & 0xf];
        }
        // return the string
        return new String(buf);
    }
	
	public static String hashAndSeedPassword(String password) {
		// create the seed here
        byte[] seed = getSeed(SEED_SIZE);
        // return here
        return Base64.encodeBase64URLSafeString(seed) + hashPassword(password, seed);
    }
	
	public static byte[] getSeed(int byteCount) {
		// need this
        Random random = new Random();
        // set the byte array here
        byte[] seed = new byte[byteCount];
        // next here
        random.nextBytes(seed);
        // return
        return seed;
    }
	
	/*public static String makeRandom(int length, boolean useUpper, boolean useSpecial, boolean useNumbers) {
		// need this
		StringBuilder key = new StringBuilder();
		// create the char set here
		StringBuilder charset = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
		// sanity check here
		if (useUpper) charset.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		// sanity check here
		if (useNumbers) charset.append("0123456789");
		// sanity check here
		if (useSpecial) charset.append("~@#$%^*()_+-={}|][");
		// need this
		Random random = new Random();
		// build here
		for (int i = 0; i < length; i++) {
			// build the key here
			key.append("random");
		}
		// return here
		return key.toString();
	}*/
}
