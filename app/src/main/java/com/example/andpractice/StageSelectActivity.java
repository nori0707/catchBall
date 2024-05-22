package com.example.andpractice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StageSelectActivity extends AppCompatActivity {

    //BGM
    private SoundPlayer stageSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_select);

        //BGMを再生するインスタンスを生成してオブジェクトを作る
        stageSelect = new SoundPlayer(this);
        stageSelect.setStageSelect();

        Button stageButton1 = findViewById(R.id.button_stage1);
        stageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StageSelectActivity.this
                        , Stage1Activity.class);
                startActivity(intent);
                stageSelect.stopStageSelect();
            }
        });

        Button stageButton2 = findViewById(R.id.button_stage2);
        stageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StageSelectActivity.this
                        , Stage2Activity.class);
                startActivity(intent);
                stageSelect.stopStageSelect();
            }
        });

        Button stageButton3 = findViewById(R.id.button_stage3);
        stageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StageSelectActivity.this
                        , Stage3Activity.class);
                startActivity(intent);
                stageSelect.stopStageSelect();
            }
        });

    }
}

    /*

    public void goToStage1(View view){
        Intent intent = new Intent(this , Stage1Activity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.d("stage1" , "ステージ1に移動しました");
    }

    public void goToStage2(View view){
        Intent intent = new Intent(this , Stage2Activity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public void goToStage3(View view){
        Intent intent = new Intent(this , Stage3Activity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
    */