package com.example.andpractice;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundPlayer {

    //サウンドエフェクト用の変数たち
    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int saikoSound;
    private static int clearSound;
    
    //BGM用の変数たち
    private static MediaPlayer mediaPlayer;
    private static MediaPlayer clearBGM;
    private static MediaPlayer stageSelect;


    public SoundPlayer(Context context) {

        //引数は（同時再生数、音声ファイルの種類、再生品質（０はデフォルト）)
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        //(R.raw.hit)は音声ファイルのID、1は再生品質、デフォルト
        hitSound = soundPool.load(context, R.raw.hit, 1);
        overSound = soundPool.load(context, R.raw.over, 1);
        saikoSound = soundPool.load(context , R.raw.yeah , 1);
        clearSound = soundPool.load(context , R.raw.lvup , 1);
        
        //BGMの初期化
        mediaPlayer = MediaPlayer.create(context , R.raw.ringo);
        mediaPlayer.setLooping(true);

        clearBGM= MediaPlayer.create(context , R.raw.clearbgm);
        clearBGM.setLooping(true);

        stageSelect= MediaPlayer.create(context , R.raw.stageselect);
        stageSelect.setLooping(true);

    }

    //オレンジ、ピンクボールにあたったときに音を再生するメソッド
    //.play（左のボリューム（0.0~1.0）、右のボリューム、優先度(0が最小値)、
    // ループするかどうか（する：-1、しない：0）、再生速度（0.5(遅い~1(標準)~2.0(速い)）
    public void playHitSound() {
        soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playOverSound() {
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playYeahSound(){
        soundPool.play(saikoSound ,1.0f, 1.0f, 1,0,1.0f);
    }

    public void playClearSound(){
        soundPool.play(clearSound , 1.0f , 1.0f , 1 ,0 ,1);
    }

    public void playStopSe(){
        soundPool.stop(hitSound);
        soundPool.stop(overSound);
        soundPool.stop(saikoSound);
        soundPool.stop(clearSound);
    }

    //BGM再生開始
    public void startBGM(){

        mediaPlayer.start();
    }

    //BGM再生停止
    public void stopBGM(){
        mediaPlayer.pause();
    }

    //BGM再生再開
    public void resumeBGM(){
        mediaPlayer.start();
    }

    public void releaseMediaPlayer(){
        mediaPlayer.release();
    }

    //BGM再生開始
    public void startClearBGM(){

        clearBGM.start();
    }

    //BGM再生停止
    public void stopClearBGM()
    {
        clearBGM.pause();
    }


    //BGM再生開始
    public void setStageSelect(){

        stageSelect.start();
    }

    //BGM再生停止
    public void stopStageSelect()
    {
        stageSelect.pause();
    }

}
