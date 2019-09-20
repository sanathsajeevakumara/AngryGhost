package com.example.angryghost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class HomeActivity extends AppCompatActivity {

    ImageButton play, feedback, cart;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        player = MediaPlayer.create(this, R.raw.starter);
        player.setLooping(true);
        player.start();

        play = findViewById(R.id.play);
        feedback = findViewById(R.id.feedback);
        cart = findViewById(R.id.cart);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
                player.stop();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), feedback);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Intent intent, chooser;
                        int id = menuItem.getItemId();

                        if (id == R.id.feedBack_menu)
                        {
                            player.stop();
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setData(Uri.parse("mailto:"));
                            String[] to = {"sanathsajeevakumara@gmail.com"};
                            intent.putExtra(Intent.EXTRA_EMAIL, to);
                            intent.setType("message/rfc822");
                            chooser = Intent.createChooser(intent, "Send Feedback");
                            startActivity(chooser);
                        }

                        if (id == R.id.share_menu)
                        {
                            player.stop();
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Hungry Ghost");
                            String sAUX = "\n Let me recommend this game \n\n";
                            sAUX = sAUX + "";
                            intent.putExtra(Intent.EXTRA_TEXT, sAUX);
                            startActivity(Intent.createChooser(intent, "share"));
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                finish();
            }
        });
    }
}
