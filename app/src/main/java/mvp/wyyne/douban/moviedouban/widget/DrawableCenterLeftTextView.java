package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author Wynne
 * @date 2018/6/22
 */

public class DrawableCenterLeftTextView extends AppCompatTextView {

    public DrawableCenterLeftTextView(Context context) {
        super(context);
    }

    public DrawableCenterLeftTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        if (drawableLeft != null) {
            int textWidth = (int) getPaint().measureText(getText().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = 0;
            drawableWidth = drawableLeft.getIntrinsicWidth();
            int bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
        super.onDraw(canvas);
    }
}
