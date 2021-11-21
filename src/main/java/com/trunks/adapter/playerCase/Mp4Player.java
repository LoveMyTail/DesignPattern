package com.trunks.adapter.playerCase;

/**
 * 高级媒体播放器类的实现类Mp4Player
 */
public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }

    @Override
    public void playVlc(String fileName) {
        // 什么也不做
    }
}
