package com.trunks.adapter.playerCase;

/**
 * 高级媒体播放器类的实现类VlcPlayer
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String fileName) {
        // 什么也不做
    }

    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }
}
