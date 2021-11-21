package com.trunks.adapter.playerCase;

/**
 * 我们有一个 MediaPlayer 接口和一个实现了 MediaPlayer 接口的实体类 AudioPlayer。
 * 默认情况下，AudioPlayer 可以播放 mp3 格式的音频文件。
 * 我们还有另一个接口 AdvancedMediaPlayer 和实现了 AdvancedMediaPlayer 接口的实体类。
 * 该类可以播放 vlc 和 mp4 格式的文件。
 * 我们想要让 AudioPlayer 播放其他格式的音频文件。为了实现这个功能，我们需要创建一个实现了 MediaPlayer 接口
 * 的适配器类 MediaAdapter，并使用 AdvancedMediaPlayer 对象来播放所需的格式。
 * AudioPlayer 使用适配器类 MediaAdapter 传递所需的音频类型，不需要知道能播放所需格式音频的实际类。
 * Client 类使用 AudioPlayer 类来播放各种格式。
 */
public class Client {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
