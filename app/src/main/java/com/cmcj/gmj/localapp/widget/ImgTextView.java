package com.cmcj.gmj.localapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.databinding.ViewImageTextLayoutBinding;

/**
 * Created by guomaojian on 16/10/9.
 */

public class ImgTextView extends LinearLayout {
    private int mSelectedImgRes;
    private int mUnselectedImgRes;
    private boolean mChecked;
    private String mTxt;

    private ViewImageTextLayoutBinding mBinding;

    public ImgTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ImgTextView);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = array.getIndex(i);
            if (attr == R.styleable.ImgTextView_selectedImgRes)
                mSelectedImgRes = array.getResourceId(attr, R.drawable.ic_main_home_checked);
            else if (attr == R.styleable.ImgTextView_unSelectedImgRes)
                mUnselectedImgRes = array.getResourceId(attr, R.drawable.ic_main_home_unchecked);
            else if (attr == R.styleable.ImgTextView_checked)
                mChecked = array.getBoolean(R.styleable.ImgTextView_checked, false);
            else if (attr == R.styleable.ImgTextView_text)
                mTxt = array.getString(attr);
        }
        array.recycle();

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_image_text_layout, this, true);
        mBinding.textImageText.setText(mTxt);
        setChecked(mChecked);
    }


    public void setChecked(boolean checked) {
        if (mBinding != null) {
            mBinding.setChecked(checked);
            mBinding.imageImageText.setImageResource(mChecked ? mSelectedImgRes : mUnselectedImgRes);
        }
    }

}
