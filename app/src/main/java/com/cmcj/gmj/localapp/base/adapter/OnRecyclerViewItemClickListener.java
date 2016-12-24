package com.cmcj.gmj.localapp.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by guomaojian on 16/12/24.
 */

public abstract class OnRecyclerViewItemClickListener<V extends RecyclerView.ViewHolder> extends RecyclerView.SimpleOnItemTouchListener {

    private RecyclerView mView;
    private GestureDetector mGestureDetector;

    public OnRecyclerViewItemClickListener(RecyclerView recyclerView) {
        mView = recyclerView;
        mGestureDetector = new GestureDetector(mView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = mView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    V viewHolder = (V) mView.getChildViewHolder(childView);
                    int position = viewHolder.getLayoutPosition();
                    onItemClickEvent(mView, viewHolder);
                }
                return super.onSingleTapUp(e);
//                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = mView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    onItemLongClickEvent(mView, (V) mView.getChildViewHolder(childView));
                }
                super.onLongPress(e);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return super.onInterceptTouchEvent(rv, e);
    }

    public abstract void onItemClickEvent(RecyclerView recyclerView, V viewHolder);

    public abstract void onItemLongClickEvent(RecyclerView recyclerView, V viewHolder);
}
