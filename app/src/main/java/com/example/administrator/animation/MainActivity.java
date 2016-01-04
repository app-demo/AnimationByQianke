package com.example.administrator.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import animation.AnimatorSetActivity;
import animation.ObjectAnimActivity;
import animation.ValueAnimatorActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_object_anim;
    private Button btn_value_anim;
    private Button btn_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_object_anim = (Button) findViewById(R.id.btn_object_anim);
        btn_object_anim.setOnClickListener(this);
        btn_value_anim = (Button) findViewById(R.id.btn_value_anim);
        btn_value_anim.setOnClickListener(this);
        btn_set = (Button) findViewById(R.id.btn_set);
        btn_set.setOnClickListener(this);
    }

    Animation scaleAnimation;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_object_anim:
                startActivity(new Intent(this, ObjectAnimActivity.class));
                break;

            case R.id.btn_value_anim:
                startActivity(new Intent(this, ValueAnimatorActivity.class));
                break;

            case R.id.btn_set:
                startActivity(new Intent(this, AnimatorSetActivity.class));
                break;
        }

    }
}
