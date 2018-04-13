package test.test.test.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by pc on 4/13/2018.
 */

public class DrawView extends View {

    private final PublishSubject<MotionEvent> mTouchSubject = PublishSubject.create();
    private final Observable<MotionEvent> mTouches = mTouchSubject;
    private final Observable<MotionEvent> mDownObservable = mTouches.filter(ev -> ev.getActionMasked() == MotionEvent.ACTION_DOWN);
    private final Observable<MotionEvent> mUpObservable = mTouches.filter(ev -> ev.getActionMasked() == MotionEvent.ACTION_UP);
    private final Observable<MotionEvent> mMovesObservable = mTouches.filter(ev -> ev.getActionMasked() == MotionEvent.ACTION_MOVE);

    private Bitmap customBitmap;
    private Canvas customCanvas;
    private Paint customPaint;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOnTouchListener((View v, MotionEvent event) -> {
            mTouchSubject.onNext(event);
            return true;
        });

        mDownObservable.subscribe(downEvent -> {
            final Path path = new Path();
            path.moveTo(downEvent.getX(), downEvent.getY());
            Log.i(downEvent.toString(), "Touch down");

            mMovesObservable
                    .takeUntil(mUpObservable
                            .doOnNext(upEvent -> {
                                draw(path);
                                path.close();
                                Log.i(upEvent.toString(), "Touch up");
                            }))
                    .subscribe(motionEvent -> {
                        path.lineTo(motionEvent.getX(), motionEvent.getY());
                        draw(path);
                        Log.i(motionEvent.toString(), "Touch move");
                    });

        });
    }

    private void draw(Path path) {
        if (customBitmap == null) {
            customBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            customCanvas = new Canvas(customBitmap);
            customPaint = new Paint();
            customPaint.setColor(Color.GREEN);
            customPaint.setStyle(Paint.Style.STROKE);
        }

        customCanvas.drawPath(path, customPaint);
        invalidate();
    }


    public void onDraw(Canvas canvas) {
        if (customBitmap != null)
            canvas.drawBitmap(customBitmap, 0, 0, customPaint);
    }

}
