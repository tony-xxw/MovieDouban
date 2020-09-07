package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import javax.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Nullable;

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
    private final int RECT_LEFT = 80;

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
        drawPercentWithLength(canvas);
    }

    /**
     * 画百分文字比与长度
     *
     * @param canvas
     */
    private void drawPercentWithLength(Canvas canvas) {
        Rect fiveRect = new Rect(RECT_LEFT, textCoordinateY - textHeight, getRectLength(fiveStar), textCoordinateY);
        canvas.drawRect(fiveRect, mRectPaint);
        canvas.drawText(getPercentText(fiveStar), getRectLength(fiveStar) + 30, textCoordinateY, mTextPaint);

        Rect fourRect = new Rect(RECT_LEFT, textCoordinateY * 2 - textHeight, getRectLength(fourStar), textCoordinateY * 2);
        canvas.drawRect(fourRect, mRectPaint);
        canvas.drawText(getPercentText(fourStar), getRectLength(fourStar) + 30, textCoordinateY * 2, mTextPaint);

        Rect threeRect = new Rect(RECT_LEFT, textCoordinateY * 3 - textHeight, getRectLength(threeStar), textCoordinateY * 3);
        canvas.drawRect(threeRect, mRectPaint);
        canvas.drawText(getPercentText(threeStar), getRectLength(threeStar) + 30, textCoordinateY * 3, mTextPaint);

        Rect twoRect = new Rect(RECT_LEFT, textCoordinateY * 4 - textHeight, getRectLength(twoStar), textCoordinateY * 4);
        canvas.drawRect(twoRect, mRectPaint);
        canvas.drawText(getPercentText(twoStar), getRectLength(twoStar) + 30, textCoordinateY * 4, mTextPaint);


        Rect oneRect = new Rect(RECT_LEFT, textCoordinateY * 5 - textHeight, getRectLength(oneStar), textCoordinateY * 5);
        canvas.drawRect(oneRect, mRectPaint);
        canvas.drawText(getPercentText(oneStar), getRectLength(oneStar) + 30, textCoordinateY * 5, mTextPaint);

    }


    /**
     * 画星级
     *
     * @param canvas
     */
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


    public int getRectLength(String xstar) {
        int result = StringUtils.getDoubleToInt(Double.parseDouble(xstar)) * 5;
        if (xstar.length() <= 1) {
            return RECT_LEFT + result;
        } else if (result < 1) {
            //精确到0.1时
            return RECT_LEFT + result + 10;
        } else if (xstar.indexOf(".") == 1) {
            //精确到1.1时
            return RECT_LEFT + result;
        } else {
            return RECT_LEFT + result;
        }
    }

    public String getPercentText(String percent) {
        Log.d("XXW", "percent :" + percent.indexOf("."));
        double num = Double.parseDouble(percent);
        BigDecimal bigDecimal = new BigDecimal(num).setScale(1, RoundingMode.UP);
        return bigDecimal + "%";
    }


}
