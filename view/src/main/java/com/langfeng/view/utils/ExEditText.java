package com.langfeng.view.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.langfeng.view.R;

@SuppressLint("AppCompatCustomView")
public class ExEditText extends RelativeLayout {

    private Context mContext;
    private EditText mEditText;
    private ImageView mImage;

    // 文本框不为空 图片资源
    private int imgRes;

    // 文本框为空 图片资源
    private int nullImgRes;
    private int measuredWidth;
    private int measuredHeight;

    public ExEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();

        // 得到图片资源
        imgRes = attrs.getAttributeResourceValue(null, "imgRes", 0);
        nullImgRes = attrs.getAttributeResourceValue(null, "nullImgRes", 0);
        if (nullImgRes != 0) {
            setDrawable();
        }

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
    }

    // 初始化布局和控件
    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.drawable_edittext, this);
        mEditText = (EditText) view.findViewById(R.id.edittext);
        mImage = (ImageView) view.findViewById(R.id.image);
    }

    // 根据文本框是否为空设置不同的图片
    private void setDrawable() {
        if (mEditText.getText().toString().equals("")) {
            mImage.setImageResource(nullImgRes);
        } else {
            mImage.setImageResource(imgRes);
        }
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
                setDrawable();
            }
        });
    }

    /**
     * @description 输入框是否有文字
     * @return
     */
    public boolean isEmpty() {
        return getText() == null || getText().length() == 0 ? true : false;
    }

    /**
     * @description 获取输入框内的文字
     * @return
     */
    public String getText() {
        return mEditText.getText().toString();
    }

    /**
     * @description 设置输入框内的文字
     * @return
     */
    public void setText(String s) {
        mEditText.setText(s);
    }
}

