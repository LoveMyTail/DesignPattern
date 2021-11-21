package com.trunks.adapter.playerCase;

/**
 * 定义一个媒体播放器的接口
 */
public interface MediaPlayer {
    /**
     * 播放功能
     * @param audioType 类型
     * @param fileName 播放的文件名
     */
    void play(String audioType, String fileName);
}
