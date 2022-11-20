package com.trunks.decorator.cipher;

public class ComplexOneCipher extends CipherDecorator {


    public ComplexOneCipher(Cipher cipher) {
        super(cipher);
    }

    @Override
    public String encrypt(String text) {
        String encrypt = super.encrypt(text);
        return "<complexOne>" + encrypt + "</complexOne>";
    }
}
