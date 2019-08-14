package linsr.com.androidtest.old;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Description
 *
 * @author Linsr 2019/1/9 下午4:09
 */
public class FileProgressView2 extends android.support.v7.widget.AppCompatImageView {


    private static final String TAG = FileProgressView.class.getSimpleName();
    private static final int MAX_PROGRESS = 100;

    private int mWidth;
    private int mHeight;
    private Paint mForegroundPaint;
    private Paint mBackgroundPaint;
    private RectF rectF;
    private PorterDuffXfermode mXfermode;
    private float mProgress;
    private int mOffset = 150;

    public FileProgressView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mForegroundPaint = new Paint();
        mForegroundPaint.setAntiAlias(true);
        mForegroundPaint.setStyle(Paint.Style.FILL);
        mForegroundPaint.setDither(true);
        mForegroundPaint.setFilterBitmap(true);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setARGB(150, 0, 0, 0);

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

        int brightness = 80; //RGB偏移量，变暗为负数
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0, brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(matrix);
        setColorFilter(cmcf);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        if (rectF == null) {
            getDrawableSize(getDrawable());

//            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
//            rectF = new RectF(-mOffset, -mOffset, bitmap.getWidth() + mOffset,
//                    bitmap.getHeight() + mOffset);
        }
    }

//    private void getDrawableSize(Drawable drawable) {
//        int width = drawable.getIntrinsicWidth();
//        int height = drawable.getIntrinsicHeight();
//    }

    private void getDrawableSize(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                new Matrix(), true);

        mOffset = width / 2;
        rectF = new RectF(-mOffset, -mOffset, width + mOffset,
                height + mOffset);
        Log.e(TAG, "~~~w:" + width + ",h:" + height);
        Log.e(TAG, "~~~mWidth:" + mWidth + ",mHeight:" + mHeight);
    }

    Canvas mCanvas = new Canvas();

    private Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);


        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(0, 0, mWidth, mHeight, mBackgroundPaint);
        mForegroundPaint.setXfermode(mXfermode);
        float radius = ((mProgress) / 100) * 360;
        canvas.drawArc(rectF, 270, radius, true, mForegroundPaint);
        mForegroundPaint.setXfermode(null);
    }

    public void setProgress(int progress) {
        if (mProgress >= MAX_PROGRESS) {
            return;
        }
        if (mProgress < 0) {
            return;
        }
        mProgress = progress;
        Log.e(TAG, "~~~progress:" + progress);
        invalidate();
    }

    public FileProgressView2(Context context) {
        this(context, null, 0);
    }

    public FileProgressView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
}
