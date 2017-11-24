package codingchallenge.manu.com.testgifanimation;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

/**
 * Created by emmanuelchagnas on 24/11/2017.
 */

public class JustOnceGifAnimationView extends View{

    private Movie mMovie = null;
    private InputStream mStream = null;
    private long mMoviestart = 0;
    private int duration = 0;

    public JustOnceGifAnimationView(Context context) {
        super(context);
    }

    public JustOnceGifAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JustOnceGifAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public JustOnceGifAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setStream(InputStream stream){
        mStream = stream;
        mMovie = Movie.decodeStream(mStream);
        duration = mMovie.duration();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);

        final long now = SystemClock.uptimeMillis();

        if(mMovie != null && mStream != null) {

            if (mMoviestart == 0) {
                mMoviestart = now;
            }

            if((now - mMoviestart) < duration) {
                final int relTime = (int) ((now - mMoviestart) % duration);
                mMovie.setTime(relTime);
            }
            mMovie.draw(canvas, 10, 10);
            this.invalidate();
        }
    }
}
