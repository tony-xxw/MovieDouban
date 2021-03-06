package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * @author XXW
 * @date 2017/6/20
 */

public class BitmapUtils {
    private static BitmapUtils mUtils;
    private Context mContext;

    /**
     * 图片缩放比例
     */
    private static final float BITMAP_SCALE = 0.4f;
    /**
     * 最大模糊度(在0.0到25.0之间)
     */
    private static final float BLUR_RADIUS = 25f;


    private BitmapUtils(Context context) {
        mContext = context;
    }

    public static BitmapUtils getInstace(Context context) {
        if (mUtils == null) {
            mUtils = new BitmapUtils(context);
        }
        return mUtils;
    }

    /**
     * 获取bitmap
     *
     * @param id 资源id
     * @return bitmap
     */
    public Bitmap getBitmap(int id) {
        return BitmapFactory.decodeResource(mContext.getResources(), id);
    }

    /**
     * 模糊图片的具体方法
     *
     * @param context 上下文对象
     * @param image   需要模糊的图片
     * @return 模糊处理后的图片
     */
    public static Bitmap blur(Context context, Bitmap image) {
//        // 计算图片缩小后的长宽
//        int width = Math.round(image.getWidth() * BITMAP_SCALE);
//        int height = Math.round(image.getHeight() * BITMAP_SCALE);
//
//        // 将缩小后的图片做为预渲染的图片。
//        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
//        // 创建一张渲染后的输出图片。
//        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
//
//        // 创建RenderScript内核对象
//        RenderScript rs = RenderScript.create(context);
//        // 创建一个模糊效果的RenderScript的工具对象
//        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//
//        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
//        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
//        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
//        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
//
//        // 设置渲染的模糊程度, 25f是最大模糊度
//        blurScript.setRadius(BLUR_RADIUS);
//        // 设置blurScript对象的输入内存
//        blurScript.setInput(tmpIn);
//        // 将输出数据保存到输出内存中
//        blurScript.forEach(tmpOut);
//
//        // 将数据填充到Allocation中
//        tmpOut.copyTo(outputBitmap);

        Bitmap output = Bitmap.createBitmap(image); // 创建输出图片
        RenderScript rs = RenderScript.create(context); // 构建一个RenderScript对象
        ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)); // 创建高斯模糊脚本
        Allocation allIn = Allocation.createFromBitmap(rs, image); // 创建用于输入的脚本类型
        Allocation allOut = Allocation.createFromBitmap(rs, output); // 创建用于输出的脚本类型
        gaussianBlue.setRadius(BLUR_RADIUS); // 设置模糊半径，范围0f<radius<=25f
        gaussianBlue.setInput(allIn); // 设置输入脚本类型
        gaussianBlue.forEach(allOut); // 执行高斯模糊算法，并将结果填入输出脚本类型中
        allOut.copyTo(output); // 将输出内存编码为Bitmap，图片大小必须注意
        rs.destroy(); // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()

        return output;
    }


}
