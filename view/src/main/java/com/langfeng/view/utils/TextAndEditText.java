package com.langfeng.view.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.langfeng.view.R;

@SuppressLint("AppCompatCustomView")
public class TextAndEditText extends RelativeLayout {

    private Context mContext;
    public EditText mEditText;
    public TextView mText;

    public String textEdit;
    public String textText;
    //命名空间
    private final String namespace = "http://com.langfeng.view";


    private int measuredWidth;
    private int measuredHeight;

    public TextAndEditText(Context context) {
        this(context, null);


    }

    public TextAndEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public TextAndEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();

        // 得到俩个赋值的参数值
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.Text2View);
        textEdit= array.getString(R.styleable.Text2View_textEdit);

        textText= array.getString(R.styleable.Text2View_textText);





        //设置俩个文本框中的值
        mEditText.setText(textEdit);
        mText.setText(textText);

        int widthPixels = mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        //设置textview宽度
//        mText.setMinWidth(widthPixels/3);
        // 设置文字大小
        int textSize = attrs.getAttributeResourceValue(null, "textSize", 0);
        if (textSize != 0) {
            mEditText.setTextSize(textSize);
        }



        // 设置edittext的hint提示
        int hint = attrs.getAttributeResourceValue(null, "hint", 0);
        if (hint != 0) {
            mEditText.setHint(hint);
        }

        // 设置文本颜色
        int textColor = attrs.getAttributeResourceValue(null, "textColor", 0);
        if (textColor != 0) {
            mEditText.setTextColor(textColor);
        }

        array.recycle();// 回收mArray
    }

    // 初始化布局和控件
    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.drawable_text, this);
        mEditText = (EditText) view.findViewById(R.id.edittext);
        mText = (TextView) view.findViewById(R.id.text);
    }


    public void setOnClickText(OnClickListener listener){
        mText.setOnClickListener(listener);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 文本框的text改变监听
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}

