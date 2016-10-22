package tao.test.weixindemo.view;

import android.media.MediaRecorder;

/**
 * Created by 李松涛 on 2016/10/14.
 */

public class AudioManager {

    /**
     * 用于播放音频视频
     */
    private MediaRecorder mMediaRecoder;
    /**
     * 文件夹名称
     */
    private String mDir;
    /**
     * 文件存储路径
     */
    private String mCurrentFilePath;

    //单例设计模式，构造方法私有化
    private AudioManager() {
    }
    //
    private static AudioManager mInstance;

}
