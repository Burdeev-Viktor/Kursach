package com.example.organizer.SecondTherd;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class Encode implements PasswordEncoder {
    private MessageDigest md5;

    {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Encode()  {
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return Arrays.toString(md5.digest(rawPassword.toString().getBytes()));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String encodePas = Arrays.toString(md5.digest(rawPassword.toString().getBytes()));
        return Objects.equals(encodePas,encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
}
