package com.trunks.adapter.playerCase;

/**
 * 定义一个相比于MediaPlayer更高级的媒体播放器接口
 */
public interface AdvancedMediaPlayer {
    void playMp4(String fileName);

    void playVlc(String fileName);
}
