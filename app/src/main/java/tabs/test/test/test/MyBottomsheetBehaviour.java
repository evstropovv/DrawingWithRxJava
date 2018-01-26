package tabs.test.test.test;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by user on 26-Jan-18.
 */

public class MyBottomsheetBehaviour extends BottomSheetBehavior {

    int lastY = 0;

    MyBottomsheetBehaviour() {
        super();
    }

    @Keep
    public MyBottomsheetBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View view, MotionEvent event) {

        int dy = 0;
        boolean shouldWeConsumeIt = false;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lastY = (int) event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) {
            lastY = 0;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            dy = (int) event.getY() - lastY;
        }
         if (view instanceof FrameLayout) {

            View child = ((ViewGroup) ((FrameLayout) view).getChildAt(0)).getChildAt(0);
            LinearLayoutManager manager = (LinearLayoutManager) ((RecyclerView) child).getLayoutManager();

            if (manager.findFirstCompletelyVisibleItemPosition() == 0 && dy > 0) {

                shouldWeConsumeIt = true;
                int parentHeight = view.getHeight();

                ViewCompat.offsetTopAndBottom(view, dy);
            } else if (manager.findFirstCompletelyVisibleItemPosition() == 0) {
                int height = ((View) view.getParent()).getHeight();
                int top = view.getTop();
                if ((event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) && top < (height >> 2)) {
                    view.setTop(0);
                    setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if ((event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP)) {
                    setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            lastY = (int) event.getY();
        }
        if (!shouldWeConsumeIt)
            return super.onInterceptTouchEvent(parent, view, event);

        return true;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
        return super.onTouchEvent(parent, child, event);
    }

    private String getActionType(int eventType) {

        StringBuilder builder = new StringBuilder();
        if (eventType == MotionEvent.ACTION_DOWN)
            builder.append(" DOWN");
        else if ((eventType & MotionEvent.ACTION_UP) == MotionEvent.ACTION_UP)
            builder.append(" UP");
        else if ((eventType & MotionEvent.ACTION_CANCEL) == MotionEvent.ACTION_CANCEL)
            builder.append(" CANCEL");
        else if ((eventType & MotionEvent.ACTION_MOVE) == MotionEvent.ACTION_MOVE)
            builder.append(" MOVE");

        return builder.toString();
    }
}