package com.pansijing.common.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by guomaojian on 16/12/24.
 */

public abstract class OnRecyclerViewAttachItemClickListener implements RecyclerView.OnChildAttachStateChangeListener {

    @Override
    public void onChildViewAttachedToWindow(final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickEvent(getData(view));
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickEvent(getData(view));
                return false;
            }
        });
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
    }

    private Object getData(View view) {
        return view.getTag();
    }

    public abstract void onItemClickEvent(Object data);

    public abstract void onItemLongClickEvent(Object data);
}
