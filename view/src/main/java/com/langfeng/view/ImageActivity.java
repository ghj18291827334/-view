package com.langfeng.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ImageActivity extends AppCompatActivity {

    private static final String TAG = "ImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        final drawArcView dr = (drawArcView) findViewById(R.id.sweepArc);
        dr.setSweepAngle(30);
        dr.setText("0%");
        /**
         * 设置自定义的弧形的弧度
         */

        SeekBar see = (SeekBar) findViewById(R.id.seekBar);
        see.setMax(100);
        see.setProgress(0);
        see.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progress1 = seekBar.getProgress();

                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);

                dr.setSweepAngle(Float.parseFloat(numberFormat.format(((float) 360 * ((float) progress1 / 100)))));
                dr.setText(numberFormat.format((float) progress1));

                /**
                 * 重新绘制方法
                 */
                dr.postInvalidate();
                dr.invalidate();


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
