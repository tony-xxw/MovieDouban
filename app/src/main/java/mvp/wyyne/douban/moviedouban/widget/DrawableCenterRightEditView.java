package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author XXW
 * @date 2017/6/3
 */

public class DrawableCenterRightEditView extends AppCompatTextView {


    public DrawableCenterRightEditView(Context context) {
        super(context);
    }

    public DrawableCenterRightEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableRight = drawables[2];
        if (drawableRight != null) {
            int textWidth = (int) getPaint().measureText(getText().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = 0;
            drawableWidth = drawableRight.getIntrinsicWidth();
            int bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
        super.onDraw(canvas);
    }
}
