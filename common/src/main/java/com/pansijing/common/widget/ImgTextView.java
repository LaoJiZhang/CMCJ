package com.pansijing.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pansijing.common.R;
import com.pansijing.common.utils.AppUtils;

/**
 * Created by guomaojian on 16/10/9.
 */

public class ImgTextView extends LinearLayout {
    private int mSelectedImgRes;
    private int mUnselectedImgRes;
    private boolean mChecked;
    private String mTxt;
    private com.pansijing.common.databinding.ViewImageTextLayoutBinding mBinding;

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
            else if (attr == R.styleable.ImgTextView_text)
                mTxt = array.getString(attr);
        }
        array.recycle();

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_image_text_layout, this, true);
        mBinding.textImageText.setText(mTxt);
    }

    @BindingAdapter({"checked"})
    public static void setChecked(ImgTextView imgTextView, boolean checked) {
        if (imgTextView.getSelectedImgRes() == 0 || imgTextView.getUnselectedImgRes() == 0)
            return;
        Resources res = AppUtils.getApplication().getResources();
        ((ImageView) imgTextView.findViewById(R.id.image_image_text)).setImageDrawable(checked ? res.getDrawable(imgTextView.getSelectedImgRes()) : res.getDrawable(imgTextView.getUnselectedImgRes()));
        ((TextView) imgTextView.findViewById(R.id.text_image_text)).setTextColor(checked ? res.getColor(R.color.yellow) : res.getColor(R.color.gray1));
    }

    public int getUnselectedImgRes() {
        return mUnselectedImgRes;
    }

    public int getSelectedImgRes() {
        return mSelectedImgRes;
    }
}
