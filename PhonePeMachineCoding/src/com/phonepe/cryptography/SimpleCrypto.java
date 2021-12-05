package com.phonepe.cryptography;

public class SimpleCrypto implements CryptAlgorithm {
    @Override
    public String encrypt(String content) {
        return "*" + content;
    }

    @Override
    public String decrypt(String encContent) {
        return encContent.substring(1);
    }
}
