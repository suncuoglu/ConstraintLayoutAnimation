package com.example.suncuoglu.constraintlayoutanimation;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button askSize;
    ImageButton close;
    ConstraintLayout mConstraintLayout; // cache the ConstraintLayout
    ConstraintSet mConstraintSet1, mConstraintSet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupAnimations();

    }

    public void setupAnimations() {
        mConstraintSet1 = new ConstraintSet(); // create a Constraint Set
        mConstraintSet2 = new ConstraintSet(); // create a Constraint Set

        mConstraintSet1.clone(this, R.layout.activity_main); // get constraints from layout
        mConstraintSet2.clone(this, R.layout.activity_two); // get constraints from layout
        askSize = findViewById(R.id.askSize);
        close = findViewById(R.id.close);
        mConstraintLayout = findViewById(R.id.shoppingRoot);
        askSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                askSize.setText("ADD TO CART - 1234 INR");
                upDateConstraints(1);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDateConstraints(2);
                askSize.setText("SELECT SIZE");

            }
        });
    }

    private void upDateConstraints(int page) {


        if (page == 1) {
            mConstraintSet2.applyTo(mConstraintLayout);

        } else if (page == 2) {
            mConstraintSet1.applyTo(mConstraintLayout);
        }

        Transition transition = new ChangeBounds();
        transition.setInterpolator(new OvershootInterpolator());
        TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
    }

}
