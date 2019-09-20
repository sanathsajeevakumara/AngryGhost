package com.example.angryghost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    int highScore;
    ImageView medal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView scoreLabel = findViewById(R.id.Results_scoreLabel);
        TextView highscoreLabel = findViewById(R.id.Results_topScoreLabel);
//        TextView gamePlayLabel = findViewById(R.id.Results_gamePlayedLabel);
        medal = findViewById(R.id.medal);

        int score = getIntent().getIntExtra("Score", 0);
        scoreLabel.setText("Your Score : "+score);

        if (score < 50)
        {
            medal.setImageResource(R.drawable.bronze);
        } else if (score >= 150)
        {
            medal.setImageResource(R.drawable.gold);
        } else
        {
            medal.setImageResource(R.drawable.silver);
        }

        SharedPreferences preferencesScore = getSharedPreferences("HIGHSCORE", Context.MODE_PRIVATE);
        highScore = preferencesScore.getInt("HIGHSCORE", 0);

        if (score > highScore)
        {
            highscoreLabel.setText("High Score : "+ score);

            SharedPreferences.Editor editor = preferencesScore.edit();
            editor.putInt("HIGHSCORE", score);
            editor.commit();
        } else
        {
            highscoreLabel.setText("High Score : "+ highScore);
        }

//        SharedPreferences preferencesGame = getSharedPreferences("GAMES", Context.MODE_PRIVATE);
//        int game = preferencesGame.getInt("GAMES", 0);
//
//        gamePlayLabel.setText("Game Played : "+(game + 1));
//
//        SharedPreferences.Editor editor = preferencesGame.edit();
//        editor.putInt("GAMES", (game + 1));
//        editor.commit();
    }

    public void tryAgain(View view)
    {
        startActivity(new Intent(getApplicationContext(), GameActivity.class));
        finish();
    }

    public void backToHome(View view)
    {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }

    //Disable return button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch (event.getKeyCode())
            {
                case KeyEvent.KEYCODE_BACK :
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
