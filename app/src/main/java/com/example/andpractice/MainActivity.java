package com.example.andpractice;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private TextView stageLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;
    private ImageView bird;

    // サイズ
    private int frameHeight;
    private int boxSize;
    private int screenWidth;

    // 位置
    private float boxY;
    private float orangeX , orangeY;
    private float pinkX , pinkY;
    private float blackX , blackY;
    private float birdX , birdY;
    private float radius;
    private float birdAngle =0;

    //スピード
    private int boxSpeed;
    private int orangeSpeed;
    private int pinkSpeed;
    private int blackSpeed;
    private int birdSpeed;



    //Score
    private int score = 0;

    // Handler & Timer
    final private Handler handler = new Handler();
    private Timer timer = new Timer();

    // Status
    private boolean action_flg = false;
    private boolean start_flg = false;

    //エフェクト
    private SoundPlayer soundPlayer;

    //BGM
    private SoundPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //エフェクトを再生するインスタンスを生成してオブジェクトを作る
        soundPlayer = new SoundPlayer(this);

        //BGMを再生するインスタンスを生成してオブジェクトを作る
        mediaPlayer = new SoundPlayer(this);

        scoreLabel = findViewById(R.id.scoreLabel);
        stageLabel = findViewById(R.id.stageLabel);
        startLabel = findViewById(R.id.startLabel);
        box = findViewById(R.id.box);
        orange = findViewById(R.id.orange);
        pink = findViewById(R.id.pink);
        black = findViewById(R.id.black);
        bird = findViewById(R.id.bird);

        //Screen Size
        WindowManager wm = getWindowManager();

        Display display = wm.getDefaultDisplay();
        //2次元平面上の座標を表すためのクラス(x軸の値と、y軸の値が入ることになる)
        Point size = new Point();
        //スクリーンのサイズをディスプレイに格納
        display.getSize(size);

        //スクリーンのなかの値をそれぞれの幅と高さに入れた
        screenWidth = size.x;
        int screenHeight = size.y;

        boxSpeed = Math.round(screenHeight / 80f);
        orangeSpeed = Math.round(screenWidth / 100f);
        pinkSpeed = Math.round(screenWidth / 60f);
        blackSpeed = Math.round(screenWidth / 55f);
        birdSpeed = Math.round(screenWidth / 30f);


        orange.setX(-80.0f);
        orange.setY(-80.0f);
        pink.setX(-80.0f);
        pink.setY(-80.0f);
        black.setX(-80.0f);
        black.setY(-80.0f);
        bird.setX(-80f);
        bird.setY(-80f);

        //引数１つ目はscoreを受け取る、第二引数は%dの部分に入れる数値を指定。
        //strings.xmlをつかって入れるときはこういう手間がいるんだと覚えよう
        scoreLabel.setText(getString(R.string.score , 0));


        //戻るボタンが押されても戻らないようにする
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });
    }


    @Override
    protected void onPause(){
        super.onPause();
        mediaPlayer.stopBGM();
    }


    //runの中で20ms間隔で動いてる、
    public void changePos() {
        hitCheck();

        //orange
        orangeX -= orangeSpeed;
        if(orangeX < 0){
            orangeX = screenWidth + 20;
            //小数点以下の部分が切り捨てられ、整数部分のみが残されます
            orangeY = (float)Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);


        //Black
        blackX -= blackSpeed;
        if(blackX < 0){
            blackX = screenWidth + 10;
            blackY = (float)Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);

        // Pink
        pinkX -= pinkSpeed;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (float)Math.floor(Math.random() * (frameHeight - pink.getHeight()));
            // pinkY の値をログ出力
            Log.d("PinkY" , "New pinkY:"  + pinkY);
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        /*ここから鳥の動きの制御*/
        // bird
        birdX -= birdSpeed;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                birdY -= (float)Math.floor(Math.random() * 101) -50;
            }
        },500);

        if (birdX < 0) {
            birdX = screenWidth + 10000;
            birdY = (float)Math.floor(Math.random() * (frameHeight - bird.getHeight()));
        }
        bird.setX(birdX);
        bird.setY(birdY);




        //Box
        if (action_flg) {
            boxY -= boxSpeed;

        } else {
            boxY += boxSpeed;
        }

        //上に突き抜けないように
        if (boxY < 0){
            boxY = 0;
        }

        //下に突き抜けないように
        if (boxY > frameHeight - boxSize) {
            boxY = frameHeight - boxSize;
        }

        box.setY(boxY);

        scoreLabel.setText(getString(R.string.score , score));
    }

    public void hitCheck(){
        //orange
        float orangeCenterX = orangeX + orange.getWidth() / 2.0f;
        float orangeCenterY = orangeY + orange.getHeight() / 2.0f;

        if(0 <= orangeCenterX && orangeCenterX <= boxSize &&
            boxY <=orangeCenterY && orangeCenterY <= boxY + boxSize){
            orangeX = -10.0f;
            score += 10;
            soundPlayer.playHitSound();
        }

        //Pink
        float pinkCenterX = pinkX + pink.getWidth() / 2.0f;
        //=になっていたせいで常に上から来るようになっていた。気づけた
        float pinkCenterY = pinkY + pink.getHeight() / 2.0f;

        if(0 <= pinkCenterX && pinkCenterX <= boxSize &&
            boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize){
            pinkX = -10.0f;
            score += 30;
            soundPlayer.playHitSound();
        }

        //bird
        float birdCenterX = birdX + bird.getWidth() / 2.0f;

        float birdCenterY = birdY + bird.getHeight() / 2.0f;

        if(0 <= birdCenterX && birdCenterX <= boxSize &&
                boxY <= birdCenterY && birdCenterY <= boxY + boxSize){
            birdX = -10.0f;
            score += 100;
            soundPlayer.playYeahSound();
        }


        //Black
        float blackCenterX = blackX + black.getWidth() / 2.0f;
        float blackCenterY = blackY + black.getWidth() / 2.0f;

        if (0 <= blackCenterX && blackCenterX <= boxSize &&
                boxY <= blackCenterY && blackCenterY <= boxY + boxSize){
            //Game Over!
            if(timer != null){
                timer.cancel();
                timer = null;
                soundPlayer.playOverSound();
                mediaPlayer.stopBGM();
            }
            //結果画面へ
            Intent intent = new Intent(getApplicationContext() , ResultActivity.class);
            intent.putExtra("SCORE" , score);
            startActivity(intent);
        }
    }

    //ボタンが押された時のイベントハンドラ
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!start_flg) {

            start_flg = true;

            //タッチしたらBGMが流れ始める
            mediaPlayer.startBGM();

            //フレームレイアウトから高さをもらっている
            FrameLayout frame = findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            //ここに書く理由はonCreateメソッド内ではビューの描画が完了していないから
            boxY = box.getY();
            boxSize = box.getHeight();

            //タッチイベントと同時にstartラベルを完全に消す
            startLabel.setVisibility(View.GONE);

            //タッチイベントと同時にstartラベルを非表示
            //startLabel.setVisibility(View.INVISIBLE);

            /*Androidはメインスレッド以外でUIを変更できない
            * つまり、TimerTaskからはスコアラベルを更新できない
            * ↓
            * そこで使うのがHandler
            * */

            timer.schedule(new TimerTask() {
                @Override
                //繰り返し実行する処理をrunの中に記述
                public void run() {
                    handler.post(new Runnable() {
                        //UIスレッドで行う処理
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;

            }
        }
        return true;
    }


}