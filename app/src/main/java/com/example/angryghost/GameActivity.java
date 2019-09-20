package com.example.angryghost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private TextView scoreLable, startLable;
    private ImageView player, food, diamond, enemy1, enemy2;
    ImageButton pauseLb;
    private ImageButton pause, startLb;
    private FrameLayout frameLb;
    TextView tv_coins;
    private int frameHeight, playerSize, screenHeight, screenWidth;
    private int playerY, foodY, foodX, diamondY, diamondX, enemy1Y, enemy1X, enemy2Y, enemy2X;
    private SoundEffects soundEffects;
    private int playerSpeed, foodSpeed, diamondSpeed, enemy1Speed, enemy2Speed;
    private int score = 0;
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private boolean action_flag = false, start_flag = false, pause_flag = false;
    int coins = 0;
    int actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final SharedPreferences preferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        coins = preferences.getInt("COINS", 0);
        actions = preferences.getInt("ACTION", 1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        soundEffects = new SoundEffects(this);

        scoreLable = findViewById(R.id.Game_score_lb);
        startLable = findViewById(R.id.Game_tapToStartLb);
        pauseLb = findViewById(R.id.Game_pause_lb);
        player = findViewById(R.id.Game_player);
        food = findViewById(R.id.Game_food);
        diamond = findViewById(R.id.Game_diamond);
        enemy1 = findViewById(R.id.Game_enemy1);
        enemy2 = findViewById(R.id.Game_enemy2);
        tv_coins = findViewById(R.id.tv_coins);

        startLb = findViewById(R.id.Game_start_lb);
        frameLb = findViewById(R.id.Game_frame_lb);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        pauseLb.setEnabled(false);
        frameLb.setVisibility(View.GONE);

        screenWidth = size.x;
        screenHeight = size.y;

        playerSpeed = Math.round(screenWidth / 59);
        foodSpeed = Math.round(screenWidth / 59);
        diamondSpeed = Math.round(screenWidth / 35);
        enemy1Speed = Math.round(screenWidth / 44);
        enemy2Speed = Math.round(screenWidth / 39);

        food.setX(-80f);
        food.setY(-80f);
        diamond.setX(-80f);
        diamond.setY(-80f);
        enemy1.setX(-80f);
        enemy1.setY(-80f);
        enemy2.setX(-80f);
        enemy2.setY(-80f);

        scoreLable.setText("Score : 0");
        tv_coins.setText(""+coins);
    }

    public void position()
    {

        hit();

        //Food Position
        foodX -= foodSpeed;
        if (foodX < 0)
        {
            foodX = screenWidth + 20;
            foodY = (int) Math.floor(Math.random() * (frameHeight - food.getHeight()));
        }
        food.setX(foodX);
        food.setY(foodY);

        //Enemy1 Position
        enemy1X -= enemy1Speed;
        if (enemy1X < 0)
        {
            enemy1X = screenWidth + 10;
            enemy1Y = (int) Math.floor(Math.random() * (frameHeight - enemy1.getHeight()));
        }
        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);

        //Enemy2 Position
        enemy2X -= enemy2Speed;
        if (enemy2X < 0)
        {
            enemy2X = screenWidth + 4000;
            enemy2Y = (int) Math.floor(Math.random() * (frameHeight - enemy2.getHeight()));
        }
        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);

        //diamond Position
        diamondX -= diamondSpeed;
        if (diamondX < 0)
        {
            diamondX = screenWidth + 5000;
            diamondY = (int) Math.floor(Math.random() * (frameHeight - diamond.getHeight()));
        }
        diamond.setX(diamondX);
        diamond.setY(diamondY);

        if (action_flag == true)
        {
            playerY -= playerSpeed;
            if (actions == 1)
            {
                player.setImageResource(R.drawable.ghost1);
            } else if (actions == 2)
            {
                player.setImageResource(R.drawable.ghost1a);
            } else if (actions == 3)
            {
                player.setImageResource(R.drawable.devil1);
            } else if (actions == 4)
            {
                player.setImageResource(R.drawable.frankenstein1);
            } else if (actions == 5)
            {
                player.setImageResource(R.drawable.pumpkin1);
            } else
            {
                player.setImageResource(R.drawable.clown1);
            }
        } else
        {
            playerY += playerSpeed;

            if (actions == 1)
            {
                player.setImageResource(R.drawable.ghost2);
            } else if (actions == 2)
            {
                player.setImageResource(R.drawable.ghost1b);
            } else if (actions == 3)
            {
                player.setImageResource(R.drawable.devil2);
            } else if (actions == 4)
            {
                player.setImageResource(R.drawable.frankenstein2);
            } else if (actions == 5)
            {
                player.setImageResource(R.drawable.pumpkin2);
            } else
            {
                player.setImageResource(R.drawable.clown2);
            }
        }

        if (playerY < 0)
        {
            playerY = 0;
        }

        if (playerY > frameHeight - playerSize)
        {
            playerY = frameHeight - playerSize;
        }

        player.setY(playerY);
        scoreLable.setText("Score : "+score);
        tv_coins.setText(""+coins);
    }

    public boolean onTouchEvent(MotionEvent motionEvent)
    {

        if (start_flag == false)
        {
            start_flag = true;

            FrameLayout frameLayout = findViewById(R.id.Game_frame);
            frameHeight = frameLayout.getHeight();

            playerY = (int) player.getY();

            playerSize = player.getHeight();

            startLable.setVisibility(View.GONE);

            pauseLb.setEnabled(true);

            timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            position();
                        }
                    });
                }
            }, 0 , 20);
        }else
            {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            {
                action_flag = true;
            } else  if (motionEvent.getAction() == MotionEvent.ACTION_UP)
            {
                action_flag = false;
            }
        }

        return true;
    }

    public void hit()
    {
        //Food hit
        int foodCenterX = foodX + food.getWidth() / 2;
        int foodCenterY = foodY + food.getHeight() / 2;

        if (0 <= foodCenterX && foodCenterX <= playerSize
            && playerY <= foodCenterY && foodCenterY <= playerY + playerSize)
        {
            score += 1;
            soundEffects.collectSound();
            foodX = -10;
        }


        //Diamond hit
        int diamondCenterX = diamondX + diamond.getWidth() / 2;
        int diamondCenterY = diamondY + diamond.getHeight() / 2;

        if (0 <= diamondCenterX && diamondCenterX <= playerSize
                && playerY <= diamondCenterY && diamondCenterY <= playerY + playerSize)
        {
            coins++;
            score += 1;
            soundEffects.diamondSound();
            foodX = -10;
        }


        //enemy1 hit
        int enemy1CenterX = enemy1X + enemy1.getWidth() / 2;
        int enemy1CenterY = enemy1Y + enemy1.getHeight() / 2;

        if (0 <= enemy1CenterX && enemy1CenterX <= playerSize
                && playerY <= enemy1CenterY && enemy1CenterY <= playerY + playerSize)
        {
            timer.cancel();
            soundEffects.loseSound();
            timer = null;

            SharedPreferences preferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("COINS", coins);
            editor.commit();

            startActivity(new Intent(getApplicationContext(), ResultsActivity.class)
            .putExtra("Score", score));
        }

        //enemy2 hit
        int enemy2CenterX = enemy2X + enemy2.getWidth() / 2;
        int enemy2CenterY = enemy2Y + enemy2.getHeight() / 2;

        if (0 <= enemy2CenterX && enemy2CenterX <= playerSize
                && playerY <= enemy2CenterY && enemy2CenterY <= playerY + playerSize)
        {
            timer.cancel();
            soundEffects.loseSound();
            timer = null;

            SharedPreferences preferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("COINS", coins);
            editor.commit();

            startActivity(new Intent(getApplicationContext(), ResultsActivity.class)
                    .putExtra("Score", score));
        }
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

    public void pasueGame(View view)
    {
        if (pause_flag == false)
        {
            pause_flag = true;

            timer.cancel();
            timer = null;

            Drawable drawable = getResources().getDrawable(R.drawable.play);
            pauseLb.setBackgroundDrawable(drawable);

            frameLb.setVisibility(View.VISIBLE);
        }else
        {
            pause_flag = false;
            Drawable drawable = getResources().getDrawable(R.drawable.ic_pause);
            pauseLb.setBackgroundDrawable(drawable);

            frameLb.setVisibility(View.GONE);

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            position();
                        }
                    });
                }
            }, 0, 20);
        }
    }
}
