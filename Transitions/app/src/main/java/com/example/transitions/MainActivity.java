package com.example.transitions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private Scene scene1, scene2;
    private Transition transition;
    private boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup mSceneRoot = (ViewGroup) findViewById(R.id.scene_root);

        scene1 = Scene.getSceneForLayout(mSceneRoot, R.layout.initial_layout, this);
        scene2 = Scene.getSceneForLayout(mSceneRoot, R.layout.final_layout, this);

        transition = new AutoTransition();
        transition.setDuration(1000);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        start = true;

    }

        void ChangeScene(View view) {
            if(start) {
                TransitionManager.go(scene2, transition);
                start = false;
            }else{
                TransitionManager.go(scene1, transition);
                start = true;
            }
        }

}
