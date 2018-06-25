package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import mvp.wyyne.douban.moviedouban.R;

/**
 * @author XXW
 * @date 2017/6/12
 */

public class StarView extends View {
    private Paint mPaint;
    private int startSpace = 0;
    private int startSize;
    private int startCount;
    /**
     * 分数
     */
    private float startMark = 0.0F;
    private Drawable startDark;
    private Bitmap startLight;
    private OnStartChangeLister mChangeLister;
    private boolean mInteggerMark = false;


    public StarView(Context context) {
        super(context);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //设置不可点击
        setClickable(false);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StarView);
        startSpace = (int) typedArray.getDimension(R.styleable.StarView_startSpace, 0);
        startSize = (int) typedArray.getDimension(R.styleable.StarView_startSize, 2);
        startCount = typedArray.getInteger(R.styleable.StarView_startCount, 5);
        startDark = typedArray.getDrawable(R.styleable.StarView_startEmpty);
        startLight = drawableToBitmap(typedArray.getDrawable(R.styleable.StarView_startFill));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(startLight, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));

    }


    /**
     * 是否需要整数评分
     */
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
            //调用监听接口
            this.mChangeLister.onStartChange(startMark);
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
            canvas.drawRect(0, 0, startSize, startSize, mPaint);
            if (startMark - (int) (startMark) == 0) {
                for (int i = 1; i < startMark / 2; i++) {
                    canvas.translate(startSpace + startSize, 0);
                    canvas.drawRect(0, 0, startSize, startSize, mPaint);
                }
            } else {
                canvas.drawRect(0, 0, startSize * startMark, startSize, mPaint);
            }

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        if (x < 0) {
            x = 0;
        }
        switch (1) {
            case 1:
                break;
            default:
                break;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                setStartMark(x / (getMeasuredWidth() / startCount));
                break;
            case MotionEvent.ACTION_MOVE:
                setStartMark(x / (getMeasuredWidth() / startCount));
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(startSize, startSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, startSize, startSize);
        drawable.draw(canvas);
        return bitmap;
    }
}
