package com.example.angryghost;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundEffects
{
    private AudioAttributes audio;
    final int MAX = 2;

    private static int collectSound;
    private static int loseSound;
    private static int diamondSound;
    private static int starterSound;
    private static SoundPool sound;

    public SoundEffects(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            audio = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            sound = new SoundPool.Builder()
                    .setAudioAttributes(audio)
                    .setMaxStreams(MAX)
                    .build();
        } else
        {
            sound = new SoundPool(MAX, AudioManager.STREAM_MUSIC, 0);
        }

        collectSound = sound.load(context, R.raw.foodcollected, 1);
        loseSound = sound.load(context, R.raw.lose, 1);
        diamondSound = sound.load(context, R.raw.diamond, 1);
        starterSound = sound.load(context, R.raw.starter, 1);
    }

    public void collectSound()
    {
        sound.play(collectSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void loseSound()
    {
        sound.play(loseSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void diamondSound()
    {
        sound.play(diamondSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void starterSound()
    {
        sound.play(starterSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
