package com.example.angryghost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends AppCompatActivity {

    RelativeLayout free_relative, ghost1b_relative, devil_relative, frank_relative, pumpking_relative, clown_relative;
    RelativeLayout lock1, lock2, lock3, lock4, lock5;
    ImageButton home;
    TextView tv_coins;
    boolean shop1, shop2, shop3, shop4, shop5;
    int coins, action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        free_relative = findViewById(R.id.free_relative);
        ghost1b_relative = findViewById(R.id.ghost1b_relative);
        devil_relative = findViewById(R.id.devil_relative);
        frank_relative = findViewById(R.id.frank_relative);
        pumpking_relative = findViewById(R.id.pumpking_relative);
        clown_relative = findViewById(R.id.clown_relative);

        lock1 = findViewById(R.id.lock1);
        lock2 = findViewById(R.id.lock2);
        lock3 = findViewById(R.id.lock3);
        lock4 = findViewById(R.id.lock4);
        lock5 = findViewById(R.id.lock5);

        home = findViewById(R.id.home);
        tv_coins = findViewById(R.id.coins);


        final SharedPreferences preferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        coins = preferences.getInt("COINS", 0);
        action = preferences.getInt("ACTION", 1);
        shop1 = preferences.getBoolean("SHOP1", false);
        shop2 = preferences.getBoolean("SHOP2", false);
        shop3 = preferences.getBoolean("SHOP3", false);
        shop4 = preferences.getBoolean("SHOP4", false);
        shop5 = preferences.getBoolean("SHOP5", false);

        tv_coins.setText(""+coins);

        if (shop1 == true)
        {
            lock1.setVisibility(View.GONE);
        }

        if (shop2 == true)
        {
            lock2.setVisibility(View.GONE);
        }

        if (shop3 == true)
        {
            lock3.setVisibility(View.GONE);
        }

        if (shop4 == true)
        {
            lock4.setVisibility(View.GONE);
        }

        if (shop5 == true)
        {
            lock5.setVisibility(View.GONE);
        }

        free_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 1;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("ACTION", action);
                editor.commit();
                startActivity(new Intent(ShopActivity.this, GameActivity.class));
            }
        });

        ghost1b_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shop1 == true)
                {
                    action = 2;

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                }else if (coins >= 30)
                {
                    shop1 = true;
                    action = 2;
                    coins -= 30;

                    tv_coins.setText(""+coins);
                    lock1.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.putInt("COINS", coins);
                    editor.putBoolean("SHOP1", shop1);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                } else
                {
                    Toast.makeText(ShopActivity.this, "Not enough coins", Toast.LENGTH_SHORT).show();
                }
            }
        });

        devil_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shop2 == true)
                {
                    action = 3;

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                }else if (coins >= 50)
                {
                    shop2 = true;
                    action = 3;
                    coins -= 50;

                    tv_coins.setText(""+coins);
                    lock2.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.putInt("COINS", coins);
                    editor.putBoolean("SHOP2", shop2);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                } else
                {
                    Toast.makeText(ShopActivity.this, "Not enough coins", Toast.LENGTH_SHORT).show();
                }
            }
        });

        frank_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shop3 == true)
                {
                    action = 4;

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                }else if (coins >= 70)
                {
                    shop3 = true;
                    action = 4;
                    coins -= 70;

                    tv_coins.setText(""+coins);
                    lock3.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.putInt("COINS", coins);
                    editor.putBoolean("SHOP3", shop3);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                } else
                {
                    Toast.makeText(ShopActivity.this, "Not enough coins", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pumpking_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shop4 == true)
                {
                    action = 5;

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                }else if (coins >= 100)
                {
                    shop4 = true;
                    action = 5;
                    coins -= 100;

                    tv_coins.setText(""+coins);
                    lock4.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.putInt("COINS", coins);
                    editor.putBoolean("SHOP4", shop4);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                } else
                {
                    Toast.makeText(ShopActivity.this, "Not enough coins", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clown_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shop5 == true)
                {
                    action = 6;

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                }else if (coins >= 100)
                {
                    shop5 = true;
                    action = 5;
                    coins -= 100;

                    tv_coins.setText(""+coins);
                    lock5.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("ACTION", action);
                    editor.putInt("COINS", coins);
                    editor.putBoolean("SHOP5", shop5);
                    editor.commit();
                    startActivity(new Intent(ShopActivity.this, GameActivity.class));
                } else
                {
                    Toast.makeText(ShopActivity.this, "Not enough coins", Toast.LENGTH_SHORT).show();
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }
}
