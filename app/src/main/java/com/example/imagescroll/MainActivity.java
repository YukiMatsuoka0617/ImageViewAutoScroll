package com.example.imagescroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int drawableWidth;
    int imageWidth;
    int currentX = 0;
    int repeatCount = 0;
    int scrollX;
    int speed = 10;
    boolean isScrollLeftToRight = true;

    ImageView IV;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IV = findViewById(R.id.imageview);
        Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.violet07, null);

        drawableWidth = d.getIntrinsicWidth();

        ViewTreeObserver observer = IV.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout(){
                imageWidth = IV.getWidth();
            }
        });

        handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (drawableWidth < currentX + imageWidth)
                    isScrollLeftToRight = false;

                if (currentX < 0) {
                    repeatCount++;
                    isScrollLeftToRight = true;
                }

                if(isScrollLeftToRight)
                    scrollX = speed;
                else
                    scrollX = -speed;

                currentX += scrollX;

                IV.scrollBy(scrollX,0);
                if (repeatCount == 2)
                    return;
                handler.postDelayed(this, 10);
            }
        };
        handler.post(r);
    }
}