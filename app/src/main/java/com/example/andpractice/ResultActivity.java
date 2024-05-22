package com.example.andpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = findViewById(R.id.scoreLabel);

        //getIntExtra("キー" , 取得できなかった場合の値)
        int score = getIntent().getIntExtra("SCORE" , 0);
        //文字列として扱うために""を入れてる
        //strings.xmlをつかって入れるときはこういう手間がいるんだと覚えよう
        scoreLabel.setText(getString(R.string.result_score , score));

        //アプリのデータを保存するための物
        //(データの名前→"GAME_DATA" , 　モード→MODE_PRIVATE)
        SharedPreferences sharedPreferences =
                getSharedPreferences("GAME_DATA" , MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE" , 0);

        Button backButton = findViewById(R.id.titleButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent (ResultActivity.this
                        , StartActivity.class);
                startActivity(intent);
            }
        });


    }

    //本当ならもう一度ボタンを押すと前の画面に戻りたい。
    //時間的に難しそうだから、いったんStage1に戻るようにした,ここは課題。
    public void tryAgain(View view){
        //finishで前の画面に戻れるけどゲームオーバーの状態になる
        //finish();
        //引数は(現在のコンテキスト、移行先のclass)
        startActivity(new Intent(getApplicationContext() , Stage1Activity.class));
    }


}

