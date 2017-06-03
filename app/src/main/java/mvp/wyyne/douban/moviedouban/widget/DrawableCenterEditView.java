package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by XXW on 2017/6/3.
 */

public class DrawableCenterEditView extends AppCompatEditText {


    public DrawableCenterEditView(Context context) {
        super(context);
    }

    public DrawableCenterEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                int textWidth = (int) getPaint().measureText(getHint().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                int bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}
