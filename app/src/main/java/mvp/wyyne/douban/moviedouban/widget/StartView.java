package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import mvp.wyyne.douban.moviedouban.R;

/**
 * Created by XXW on 2017/6/12.
 */

public class StartView extends View {
    private Paint mPaint;
    private int startSpace = 0;
    private int startSize;
    private int startCount;
    private float startMark = 0.0F;
    private Drawable startDark;
    private Bitmap startLight;
    private OnStartChangeLister mChangeLister;
    private boolean mInteggerMark = false;
    private int maxSpace;


    public StartView(Context context) {
        super(context);
    }

    public StartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setClickable(false);
        TypedArray mArry = context.obtainStyledAttributes(attrs, R.styleable.StartView);
        startSpace = (int) mArry.getDimension(R.styleable.StartView_startSpace, 0);
        startSize = (int) mArry.getDimension(R.styleable.StartView_startSize, 2);
        startCount = mArry.getInteger(R.styleable.StartView_startCount, 5);
        startDark = mArry.getDrawable(R.styleable.StartView_startEmpty);
        startLight = drawableToBitmap(mArry.getDrawable(R.styleable.StartView_startFill));
        mArry.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(startLight, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));

    }


    //是否需要整数评分
    public void setInteggerMark(boolean integgerMark) {
        mInteggerMark = integgerMark;
    }

    public void setStartMark(int mark) {
        if (mInteggerMark) {
            startMark = (int) Math.ceil(mark);
        } else {
            startMark = Math.round(mark * 10) * 1.0f / 10;
        }
        if (this.mChangeLister != null) {
            this.mChangeLister.onStartChange(startMark);  //调用监听接口
        }
        invalidate();
    }

    public float getStartMark() {
        return startMark;
    }


    public interface OnStartChangeLister {
        void onStartChange(float mark);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(startSize * startCount + startSpace * (startCount - 1), startSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (startDark == null || startLight == null) {
            return;
        }


        for (int i = 0; i < startCount; i++) {
            startDark.setBounds((startSpace + startSize) * i, 0, (startSpace + startSize) * i + startSize, startSize);
            startDark.draw(canvas);
        }

        if (startMark > 1) {
            maxSpace = ((startSpace + startSize) * startCount);
//            Log.d("XXW", "maxspace---" + maxSpace);
//            Log.d("XXW", "startMark" + startMark);
            canvas.drawRect(0, 0, startSize, startSize, mPaint);
            if (startMark - (int) (startMark) == 0) {
//                Log.d("XXW", "0");
                for (int i = 1; i < startMark / 2; i++) {
                    canvas.translate(startSpace + startSize, 0);
                    canvas.drawRect(0, 0, startSize, startSize, mPaint);
                }
//            } else {
//                Log.d("XXW", "1");
//                for (int i = 1; i < startMark - 1; i++) {
//                    canvas.translate(startSpace + startSize, 0);
//                    canvas.drawRect(0, 0, startSize, startSize, mPaint);
//                }
//                canvas.translate(startSpace + startSize, 0);
//                canvas.drawRect(0, 0, startSize * (Math.round((startMark - (int) (startMark)) * 10) * 1.0f / 10), startSize, mPaint);
//            }
            } else {
            canvas.drawRect(0, 0, startSize * startMark, startSize, mPaint);
            }

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        if (x < 0) x = 0;
        if (x > getMeasuredWidth()) x = getMeasuredWidth();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                setStartMark(x / (getMeasuredWidth() / startCount));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                setStartMark(x / (getMeasuredWidth() / startCount));
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) return null;
        Bitmap bitmap = Bitmap.createBitmap(startSize, startSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, startSize, startSize);
        drawable.draw(canvas);
        return bitmap;
    }
}
