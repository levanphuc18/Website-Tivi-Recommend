package models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//thực tập md5
public class Md5Encryption {
	public String convertHashToString(String mk) throws NoSuchAlgorithmException {
		MessageDigest md =MessageDigest.getInstance("MD5");
		byte[] hashInBytes = md.digest(mk.getBytes(StandardCharsets.UTF_8));
		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
        Md5Encryption md5 = new Md5Encryption();
        System.out.println(md5.convertHashToString("1"));
        System.out.println(md5.convertHashToString("1231231923213129318239218"));
    }
}
