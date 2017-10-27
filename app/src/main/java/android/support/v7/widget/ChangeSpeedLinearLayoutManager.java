package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by clvc on 2017/9/11.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class ChangeSpeedLinearLayoutManager extends LinearLayoutManager {
    private static final String TAG = "ChangeSpeedManager";

    private double speedRatio;
    public void setSpeedRatio(double speedRatio) {
        this.speedRatio = speedRatio;
    }

    public ChangeSpeedLinearLayoutManager(Context context) {
        super(context);
    }

    public ChangeSpeedLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ChangeSpeedLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                          int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State
            state) {
        if (getOrientation() == HORIZONTAL) {
            return 0;
        }
        if (getChildCount() == 0 || dy == 0) {
            return 0;
        }
        Class<LinearLayoutManager> aClass = LinearLayoutManager.class;
        LayoutState mLayoutState = null;
        try {
            Field mLayoutStateField = aClass.getDeclaredField("mLayoutState");
            mLayoutStateField.setAccessible(true);
            mLayoutState = (LayoutState) mLayoutStateField.get(this);
            mLayoutState.mRecycle = true;
            ensureLayoutState();
            final int layoutDirection = dy > 0 ? LayoutState.LAYOUT_END : LayoutState.LAYOUT_START;
            final int absDy = Math.abs(dy);
            Method layoutForPredictiveAnimations = aClass.getDeclaredMethod
                    ("layoutForPredictiveAnimations", RecyclerView.Recycler.class, RecyclerView
                            .State.class, Integer.class, Integer.class);
            Log.i("ChangeSpeedL", "---scrollVerticallyBy()--->" + layoutForPredictiveAnimations);

            Method updateLayoutStateMethod = aClass.getDeclaredMethod("updateLayoutState",
                    Integer.class, Integer.class, Boolean.class, RecyclerView.State.class);
            updateLayoutStateMethod.setAccessible(true);
            updateLayoutStateMethod.invoke(this, layoutDirection, absDy, true, state);
            final int consumed = mLayoutState.mScrollingOffset + fill(recycler, mLayoutState,
                    state, false);
            if (consumed < 0) {
                if (DEBUG) {
                    Log.d(TAG, "Don't have any more elements to scroll");
                }
                return 0;
            }
            int scrolled = absDy > consumed ? layoutDirection * consumed : dy;
            mOrientationHelper.offsetChildren(-scrolled);
            if (DEBUG) {
                Log.d(TAG, "scroll req: " + dy + " scrolled: " + scrolled);
            }
            scrolled *= speedRatio;
            mLayoutState.mLastScrollDelta = scrolled;
            return scrolled;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
