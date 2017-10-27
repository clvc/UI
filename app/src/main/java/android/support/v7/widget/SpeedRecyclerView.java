package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by clvc on 2017/9/11.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class SpeedRecyclerView extends RecyclerView {
    private static final String TAG = "SpeedRecyclerView";
    private double speedRatio=1;

    public SpeedRecyclerView(Context context) {
        super(context);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSpeedRatio(double speedRatio) {
        this.speedRatio = speedRatio;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        LayoutManager layout = getLayoutManager();
        if (layout == null) {
            Log.e(TAG, "Cannot fling without a LayoutManager set. " + "Call setLayoutManager " +
                    "with" + " a non-null argument.");
            return false;
        }
        Class<RecyclerView> aClass = RecyclerView.class;
        boolean mLayoutFrozen = false;
        int mMinFlingVelocity = 0;
        OnFlingListener mOnFlingListener = null;
        int mMaxFlingVelocity = 0;
        ViewFlinger mViewFlinger = null;
        try {
            Field mLayoutFrozenField = aClass.getDeclaredField("mLayoutFrozen");
            mLayoutFrozenField.setAccessible(true);
            mLayoutFrozen = (boolean) mLayoutFrozenField.get(this);
            Field mMinFlingVelocityField = aClass.getDeclaredField("mMinFlingVelocity");
            mMinFlingVelocityField.setAccessible(true);
            mMinFlingVelocity = (int) mMinFlingVelocityField.get(this);

            Field mOnFlingListenerField = aClass.getDeclaredField("mOnFlingListener");
            mOnFlingListenerField.setAccessible(true);
            mOnFlingListener = (OnFlingListener) mOnFlingListenerField.get(this);

            Field mMaxFlingVelocityField = aClass.getDeclaredField("mMaxFlingVelocity");
            mMaxFlingVelocityField.setAccessible(true);
            mMaxFlingVelocity = (int) mMaxFlingVelocityField.get(this);

            Field mViewFlingerField = aClass.getDeclaredField("mViewFlinger");
            mViewFlingerField.setAccessible(true);
            mViewFlinger = (ViewFlinger) mViewFlingerField.get(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (mLayoutFrozen) {
            return false;
        }

        final boolean canScrollHorizontal = layout.canScrollHorizontally();
        final boolean canScrollVertical = layout.canScrollVertically();

        if (!canScrollHorizontal || Math.abs(velocityX) < mMinFlingVelocity) {
            velocityX = 0;
        }
        if (!canScrollVertical || Math.abs(velocityY) < mMinFlingVelocity) {
            velocityY = 0;
        }
        if (velocityX == 0 && velocityY == 0) {
            // If we don't have any velocity, return false
            return false;
        }

        if (!dispatchNestedPreFling(velocityX, velocityY)) {
            final boolean canScroll = canScrollHorizontal || canScrollVertical;
            dispatchNestedFling(velocityX, velocityY, canScroll);

            if (mOnFlingListener != null && mOnFlingListener.onFling(velocityX, velocityY)) {
                return true;
            }

            if (canScroll) {
                velocityX = (int) (Math.max(-mMaxFlingVelocity, Math.min(velocityX,
                        mMaxFlingVelocity)) * speedRatio);
                velocityY = (int) (Math.max(-mMaxFlingVelocity, Math.min(velocityY,
                        mMaxFlingVelocity)) * speedRatio);
                mViewFlinger.fling(velocityX, velocityY);
                return true;
            }
        }
        return false;
    }
}
