package com.langfeng.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.langfeng.view.utils.ExEditText;
import com.langfeng.view.utils.TextAndEditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ImageActivity extends AppCompatActivity {

    private static final String TAG = "ImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        final drawArcView dr = (drawArcView) findViewById(R.id.sweepArc);

        dr.setSweepAngle(0);
        dr.setText("0");
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
                float sweepAngle = dr.getSweepAngle();
                dr.setText(numberFormat.format((float) progress1));




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        TextAndEditText viewText = (TextAndEditText) findViewById(R.id.testC);
        viewText.setOnClickText(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ImageActivity.this, "点击事件发生", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

}
