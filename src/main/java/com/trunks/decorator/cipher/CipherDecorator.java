package com.trunks.decorator.cipher;

public class CipherDecorator implements Cipher {

    private Cipher cipher;

    public CipherDecorator(Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public String encrypt(String text) {
        return cipher.encrypt(text);
    }
}
