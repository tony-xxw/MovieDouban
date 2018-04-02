package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.StarDetail;
import mvp.wyyne.douban.moviedouban.utils.StringUtils;

/**
 * 图表统计
 *
 * @author Wynne
 * @date 2018/4/2
 */

public class ChartRect extends View {
    private int mSum;
    private StarDetail mStarDetail;
    private String oneStar;
    private String twoStar;
    private String threeStar;
    private String fourStar;
    private String fiveStar;
    private Paint mTextPaint;
    private Paint mRectPaint;
    private int textCoordinateY = 60;
    private Context mContext;
    private Rect bound;
    private int textHeight;

    public ChartRect(Context context) {
        super(context);
    }

    public ChartRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mTextPaint = new Paint();
        mTextPaint.setTextSize(36);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);

        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setColor(getResources().getColor(R.color.colorOrange_light));

        bound = new Rect();
    }

    public void setStarDetail(StarDetail starDetail, int sum) {
        mStarDetail = starDetail;
        mSum = mStarDetail.get_$1() + mStarDetail.get_$2() + mStarDetail.get_$3() + mStarDetail.get_$4() + mStarDetail.get_$5();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);

        Log.d("XXW", "width :" + width);
        Log.d("XXW", "height :" + height);
        if (widthModel == MeasureSpec.AT_MOST && heightModel == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, height);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        oneStar = StringUtils.getNumberFormat(mStarDetail.get_$1(), mSum);
        twoStar = StringUtils.getNumberFormat(mStarDetail.get_$2(), mSum);
        threeStar = StringUtils.getNumberFormat(mStarDetail.get_$3(), mSum);
        fourStar = StringUtils.getNumberFormat(mStarDetail.get_$4(), mSum);
        fiveStar = StringUtils.getNumberFormat(mStarDetail.get_$5(), mSum);


        drawTextStar(canvas);
        drawRectStar(canvas);
    }

    private void drawRectStar(Canvas canvas) {
        Rect rect = new Rect(80, textCoordinateY - textHeight,
                StringUtils.getDoubleToInt(Double.parseDouble(fiveStar)) * 5, textCoordinateY);
        canvas.drawRect(rect, mRectPaint);

        canvas.drawText(fiveStar + "%", StringUtils.getDoubleToInt(Double.parseDouble(fiveStar)) * 5 + 30, textCoordinateY, mRectPaint);
    }

    private void drawTextStar(Canvas canvas) {
        String text = mContext.getResources().getString(R.string.five_star);
        canvas.drawText(text, 0, textCoordinateY, mTextPaint);
        canvas.drawText("4星", 0, textCoordinateY * 2, mTextPaint);
        canvas.drawText("3星", 0, textCoordinateY * 3, mTextPaint);
        canvas.drawText("2星", 0, textCoordinateY * 4, mTextPaint);
        canvas.drawText("1星", 0, textCoordinateY * 5, mTextPaint);
        mTextPaint.getTextBounds(text, 0, text.length(), bound);
        textHeight = Math.abs(bound.top) + bound.bottom;
    }


}
