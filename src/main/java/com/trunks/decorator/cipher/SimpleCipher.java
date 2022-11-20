package com.trunks.decorator.cipher;


public class SimpleCipher implements Cipher {
    @Override
    public String encrypt(String text) {
        return "<simple>" + text + "</simple>";
    }
}
