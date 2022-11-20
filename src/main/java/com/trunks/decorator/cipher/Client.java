package com.trunks.decorator.cipher;

public class Client {
    /**
     * 某系统提供了一个数据加密功能，可以对字符串进行加密。
     * 有最简单的加密，也有复杂的加密，用户可以使用一个或多个加密方法对于原文本进行加密
     */
    public static void main(String[] args) {
        String textOne = "password";
        Cipher cipher;
        cipher = new SimpleCipher();
        System.out.println(cipher.encrypt(textOne));

        String textTwo = "password";
        System.out.println(new ComplexOneCipher(cipher).encrypt(textTwo));

        String textThree = "password";
        System.out.println(new ComplexTwoCipher(cipher).encrypt(textThree));

        String textFour = "password";
        cipher = new ComplexOneCipher(cipher);
        cipher = new ComplexTwoCipher(cipher);
        System.out.println(cipher.encrypt(textFour));

    }
}
