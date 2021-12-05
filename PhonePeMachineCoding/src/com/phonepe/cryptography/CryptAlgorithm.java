package com.phonepe.cryptography;

public interface CryptAlgorithm {

    String encrypt(String content);

    String decrypt(String content);
}
