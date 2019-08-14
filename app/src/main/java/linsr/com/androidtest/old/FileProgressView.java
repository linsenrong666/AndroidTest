package linsr.com.androidtest.old;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Description
 *
 * @author Linsr 2019/1/9 下午4:09
 */
public class FileProgressView extends View {

    private static final String TAG = FileProgressView.class.getSimpleName();
    private static final int MAX_PROGRESS = 100;

    private int mWidth;
    private int mHeight;
    private Paint mForegroundPaint;
    private Paint mBackgroundPaint;
    private RectF rectF;
    private PorterDuffXfermode mXfermode;
    private float mProgress;
    private static final int mOffset = 150;

    public FileProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        if (rectF == null) {
            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            rectF = new RectF(-mOffset, -mOffset, bitmap.getWidth() + mOffset,
                    bitmap.getHeight() + mOffset);
//            int hOffset = mHeight / 5;
//            int wOffset = mWidth / 5;
//            rectF = new RectF(-mOffset, -mOffset, mWidth + mOffset,
//                    mHeight + mOffset);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, mWidth, mHeight, mBackgroundPaint);
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
        Log.e(TAG, "===progress:" + progress);
        invalidate();
    }

    public FileProgressView(Context context) {
        this(context, null, 0);
    }

    public FileProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
}
