package com.example.omari.cis436proj2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class SecondaryActivity extends Activity {

    TextView pointsLeftText;
    RatingBar warriorStrengthBar, warriorIntellectBar, warriorWisdomBar, warriorDexterityBar;
    RatingBar mageStrengthBar, mageIntellectBar, mageWisdomBar, mageDexterityBar;
    RatingBar healerStrengthBar, healerIntellectBar, healerWisdomBar, healerDexterityBar;
    RatingBar hunterStrengthBar, hunterIntellectBar, hunterWisdomBar, hunterDexterityBar;
    RatingBar paladinStrengthBar, paladinIntellectBar, paladinWisdomBar, paladinDexterityBar;
    float warriorPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warrior_fragment);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        String classPicked = intent.getStringExtra("classSelected");
        warriorPoints = 10;
        final float magePoints = 10;
        final float healerPoints = 10;
        final float hunterPoints = 10;
        final float paladinPoints = 10;

        pointsLeftText = findViewById(R.id.warrior_PointsLeftNumber);

        warriorStrengthBar = findViewById(R.id.warrior_strengthBar);
        warriorIntellectBar = findViewById(R.id.warrior_intellectBar);
        warriorWisdomBar = findViewById(R.id.warrior_wisdomBar);
        warriorDexterityBar = findViewById(R.id.warrior_dexterityBar);

        mageStrengthBar = findViewById(R.id.mage_strengthBar);
        mageIntellectBar = findViewById(R.id.mage_intellectBar);
        mageWisdomBar = findViewById(R.id.mage_wisdomBar);
        mageDexterityBar = findViewById(R.id.mage_dexterityBar);

        healerStrengthBar = findViewById(R.id.healer_strengthBar);
        healerIntellectBar = findViewById(R.id.healer_intellectBar);
        healerWisdomBar = findViewById(R.id.healer_wisdomBar);
        healerDexterityBar = findViewById(R.id.healer_dexterityBar);

        hunterStrengthBar = findViewById(R.id.hunter_strengthBar);
        hunterIntellectBar = findViewById(R.id.hunter_intellectBar);
        hunterWisdomBar = findViewById(R.id.hunter_wisdomBar);
        hunterDexterityBar = findViewById(R.id.hunter_dexterityBar);

        paladinStrengthBar = findViewById(R.id.paladin_strengthBar);
        paladinIntellectBar = findViewById(R.id.paladin_intellectBar);
        paladinWisdomBar = findViewById(R.id.paladin_wisdomBar);
        paladinDexterityBar = findViewById(R.id.paladin_dexterityBar);

        if (classPicked.equals("warrior")) {
//            setContentView(R.layout.warrior_fragment);
            save("warriorS", 10);

            warriorStrengthBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(warriorPoints - rating > 0) {
                        warriorPoints = Math.abs(load("warriorS") - rating);
                        save("warriorS", rating);
                        pointsLeftText.setText("" + warriorPoints);
                    }
                    else {
                        float pLeft = (float) Integer.parseInt(pointsLeftText.getText().toString());
                        warriorStrengthBar.setRating(pLeft);
                        warriorPoints = 0;
                        save("warriorS", warriorPoints);
                        pointsLeftText.setText("" + 0);
                    }
                }
            });

            warriorIntellectBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(warriorPoints - rating > 0) {
                        pointsLeftText.setText("" + (Math.abs(load("warriorI") - rating)));
                        save("warriorI", warriorPoints - rating);
                        warriorPoints = Math.abs(load("warriorI") - rating);
                    }
                    else {
                        warriorStrengthBar.setRating(Integer.parseInt(pointsLeftText.getText().toString()));
                        pointsLeftText.setText("" + rating);
                        save("warriorI", warriorPoints);
                        warriorPoints = Math.abs(load("warriorI") - rating);
                    }
                }
            });

            warriorWisdomBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    pointsLeftText.setText("" + rating);
                }
            });

            warriorDexterityBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    pointsLeftText.setText("" + rating);
                }
            });
        }
        else if(classPicked.equals("mage")) {
            setContentView(R.layout.healer_fragment);
        }
        else if(classPicked.equals("healer")) {
            setContentView(R.layout.mage_fragment);
        }
        else if(classPicked.equals("hunter")) {
            setContentView(R.layout.hunter_fragment);
        }
        else if (classPicked.equals("paladin")){
            setContentView(R.layout.paladin_fragment);
        }
    }

    public void save(String folderName, float f) {
        SharedPreferences sharedPreferences = getSharedPreferences(folderName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(folderName, f);
        editor.apply();
    }

    public float load(String folderName) {
        SharedPreferences sharedPreferences = getSharedPreferences(folderName, MODE_PRIVATE);
        return sharedPreferences.getFloat(folderName, 0f);
    }

    public boolean checkMax(int total) {
        return total >= 10;
    }
}

