package models;


import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BcryptEncryption {
    private final PasswordEncoder passwordEncoder;

    public BcryptEncryption() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        BcryptEncryption br = new BcryptEncryption();
        System.out.println(" phuc "+ br.verifyPassword("1","$2a$12$jb01LBv1Qg9ANHoSufUpWu3R88c2o7xqNnC1SowMRbhPNjuAzxnxW"));
     // mã hóa pass Bcrypt
		String passwword = br.hashPassword("12345");
		System.out.println(" phuc2 "+ br.verifyPassword("12345",passwword));
    }
}