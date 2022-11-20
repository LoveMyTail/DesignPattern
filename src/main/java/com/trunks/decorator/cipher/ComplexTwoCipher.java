package com.trunks.decorator.cipher;

public class ComplexTwoCipher extends CipherDecorator {
    public ComplexTwoCipher(Cipher cipher) {
        super(cipher);
    }

    @Override
    public String encrypt(String text) {
        String encrypt = super.encrypt(text);
        return "<complexTwo>" + encrypt + "</complexTwo>";
    }
}
