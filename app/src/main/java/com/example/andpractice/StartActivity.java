package com.example.andpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    //android:onClick="startGame"/>のstartGameがメソッド名、今は非推奨みたい。でも楽だから使う
    public void startGame(View view){
        startActivity(new Intent(getApplicationContext() , StageSelectActivity.class));
    }



}