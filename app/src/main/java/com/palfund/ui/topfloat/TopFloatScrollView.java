package com.palfund.ui.topfloat;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by clvc on 2017/8/14.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */


/*
 * ScrollView并没有实现滚动监听，所以我们必须自行实现对ScrollView的监听，
 * 我们很自然的想到在onTouchEvent()方法中实现对滚动Y轴进行监听
 * ScrollView的滚动Y值进行监听
 */
public class TopFloatScrollView extends ScrollView {
    private OnScrollListener onScrollListener;
    /**
     * 主要是用在用户手指离开TopFloatScrollView，TopFloatScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public TopFloatScrollView(Context context) {
        super(context, null);
    }

    public TopFloatScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TopFloatScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 用于用户手指离开TopFloatScrollView的时候获取TopFloatScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            // 获取到的滑动距离是相对于初始状态的开始位置而言的
            int scrollY = TopFloatScrollView.this.getScrollY();

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendEmptyMessageDelayed(0, 5);
            }
            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollY);
            }

        }
    };

    /**
     * 重写onTouchEvent， 当用户的手在TopFloatScrollView上面的时候，
     * 直接将TopFloatScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * TopFloatScrollView可能还在滑动，所以当用户抬起手我们隔20毫秒给handler发送消息，在handler处理
     * TopFloatScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (onScrollListener != null) {
            Log.i("TopFloatScrollView", "---onTouchEvent()--->" + getScrollY());
            onScrollListener.onScroll(lastScrollY = getScrollY());
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                handler.sendEmptyMessageDelayed(0, 20);
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        void onScroll(int scrollY);
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}
