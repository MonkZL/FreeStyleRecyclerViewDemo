package zl.com.freestylerecyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zl on 2017/7/18.
 *
 */

public class FreeStyleRecyclerView extends RecyclerView {

    //内部默认高度
    private int DEFAULTHEIGHT;
    //用户设置的默认高度
    private int defaultHeight;
    //内部目标高度
    private int TARGETHEIGHT;
    //用户设置的目标高度
    private int targetHeight;
    private DisplayMetrics metrics;


    public FreeStyleRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public FreeStyleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FreeStyleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        DEFAULTHEIGHT = metrics.heightPixels / 6;
        TARGETHEIGHT = DEFAULTHEIGHT * 2;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FreeStyleRecyclerView);
            defaultHeight = (int) typedArray.getDimension(R.styleable.FreeStyleRecyclerView_defaultHeight, DEFAULTHEIGHT);
            targetHeight = (int) typedArray.getDimension(R.styleable.FreeStyleRecyclerView_targetHeight, TARGETHEIGHT);
        } else {
            defaultHeight = DEFAULTHEIGHT;
            targetHeight = TARGETHEIGHT;
        }


        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!(getLayoutManager() instanceof GridLayoutManager)) {
                    for (int i = 0; i < getChildCount(); i++) {
                        if (i == 0) {
                            View childAt0 = getChildAt(0);
                            ViewGroup.LayoutParams layoutParams0 = childAt0.getLayoutParams();
                            int top = childAt0.getTop();
                            top = top <= -defaultHeight ? -defaultHeight : top;
                            layoutParams0.height = (int) (targetHeight + (top * 1f / defaultHeight) * (targetHeight - defaultHeight));
                            childAt0.requestLayout();
                        } else if (i == 1) {
                            View childAt1 = getChildAt(1);
                            ViewGroup.LayoutParams layoutParams1 = childAt1.getLayoutParams();
                            layoutParams1.height = defaultHeight + targetHeight - getChildAt(0).getLayoutParams().height;
                            childAt1.requestLayout();
                        } else {
                            View childAt = getChildAt(i);
                            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                            layoutParams.height = defaultHeight;
                            childAt.requestLayout();
                        }
                    }
                } else {
                    for (int i = 0; i < getChildCount(); i++) {
                        if (i <= 1) {
                            View childAt0 = getChildAt(i);
                            ViewGroup.LayoutParams layoutParams0 = childAt0.getLayoutParams();
                            int top = childAt0.getTop();
                            top = top <= -defaultHeight ? -defaultHeight : top;
                            layoutParams0.height = (int) (targetHeight + (top * 1f / defaultHeight) * (targetHeight - defaultHeight));
                            childAt0.requestLayout();
                        } else if (i <= 3) {
                            View childAt1 = getChildAt(i);
                            ViewGroup.LayoutParams layoutParams1 = childAt1.getLayoutParams();
                            int height = getChildAt(0).getLayoutParams().height;
                            layoutParams1.height = defaultHeight + targetHeight - height;
                            childAt1.setLayoutParams(layoutParams1);
                        } else {
                            View childAt = getChildAt(i);
                            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                            layoutParams.height = defaultHeight;
                            childAt.requestLayout();
                        }
                    }
                }
            }
        });

    }


    public int getDefaultHeight() {
        return defaultHeight;
    }

    public void setDefaultHeight(int defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    public void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
    }
}
