package com.example.andpractice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClearActivity extends AppCompatActivity {

    private SoundPlayer clearBGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);
        //BGMを再生するインスタンスを生成してオブジェクトを作る
        clearBGM = new SoundPlayer(this);
        clearBGM.startClearBGM();

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent ( ClearActivity.this
                        , StageSelectActivity.class);
                clearBGM.stopClearBGM();
                startActivity(intent);
            }
        });

        Button titleButton = findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent ( ClearActivity.this
                        , StartActivity.class);
                clearBGM.stopClearBGM();
                startActivity(intent);
            }
        });


    }
}

    /*
        public void stageSelect () {

            Intent intent = new Intent(this, StageSelectActivity.class);
            startActivity(intent);
            //clearBGM.stopClearBGM();
            Log.d("stageSelectCheck", "ステージセレクト画面に戻ります");
        }

     */