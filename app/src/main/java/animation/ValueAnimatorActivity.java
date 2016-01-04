package animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.administrator.animation.R;

/**
 * 和ObjectAnimator的区别
 * 没有设置操作的属性
 * 可以自己根据当前动画的计算值，来操作任何属性
 */
public class ValueAnimatorActivity extends Activity {

    private ImageView mBlueBall;

    private ImageView mVerticalBall;

    private ImageView mCircleBall;

    private float mScreenHeight;

    private float mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_animator);

        mBlueBall = (ImageView) findViewById(R.id.id_ball);
        mVerticalBall = (ImageView) findViewById(R.id.id_ball_vertical);
        mCircleBall = (ImageView) findViewById(R.id.id_ball_circle);
        //得到屏幕宽度,高度
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mScreenWidth = outMetrics.widthPixels;
    }

    /**
     * 自由落体
     *
     * @param view
     */
    public void verticalRun(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight
                - 2 * mVerticalBall.getHeight());
        animator.setTarget(mVerticalBall);//设置进行动画的对象
        animator.setDuration(3000).start();
        //Interpolator 被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。具体并未深究
        //animator.setInterpolator(new AccelerateInterpolator(3f));//加速
        animator.setInterpolator(new LinearInterpolator());//弹跳
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mVerticalBall.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    /**
     * 抛物线
     *
     * @param view
     */
    public void paowuxian(View view) {

        final ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setTarget(mBlueBall);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());//匀速

        /**
         *  evaluator全部继承至TypeEvaluator接口，它只有一个evaluate()方法。它用来返回你要进行动画的那个属性在当前时间点所需要的属性值
         *  根据属性的开始、结束值与TimeInterpolation计算出的因子计算出当前时间的属性值
         */
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                /**
                 * fraction 表示时间分值  画面绘制一帧所需要的时间
                 * 平抛运动可正交分解为两个运动：水平方向上的速度为V的匀速直线运动和竖直方向上的自由落体运动.
                 * 水平方向上位移是x = Vt；
                 * 竖直方向上的速度V = gt,位移y = gt²/2  这里设x的速度为200px/s  g为260px/s  时间为3秒
                 */
                Log.e("sssssssss", fraction + "");
                PointF point = new PointF();
                point.x = 200 * 3 * fraction;
                point.y = 260 * (fraction * 3) * (fraction * 3) / 2;
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mBlueBall.setX(point.x);
                mBlueBall.setY(point.y);
            }
        });
        /**
         * 动画监听事件
         * AnimatorListenerAdapter继承了AnimatorListener接口，可以空实现所有的方法
         * 你可以根据你的需要重写你需要的方法
         * 主要的方法有 onAnimationStart 开始 onAnimationRepeat 重复 onAnimationCancel 停止 onAnimationEnd  结束
         */
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup parent = (ViewGroup) mBlueBall.getParent();
                if (mBlueBall != null) {
                    parent.removeView(mBlueBall);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });


    }

    /**
     * 圆周运动
     */
    public void circleRun(View view) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(10000);
        animator.setTarget(mCircleBall);
        animator.setObjectValues(new PointF(mCircleBall.getX(), mCircleBall.getY()));
        animator.setInterpolator(new LinearInterpolator());

        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                /**
                 * 设置圆的半径r为200px   圆点坐标为(x1,y1)
                 * 圆周上的点坐标的公式为 x = x1 + r * cos(wt)  y = y1 + r * sin(wt)
                 */
                PointF point = new PointF();
                point.x = (float) (mScreenWidth / 2 + 200 * Math.cos(Math.toRadians(360 * fraction - 90)));
                point.y = (float) (200 + 200 * Math.sin(Math.toRadians(360 * fraction - 90)));
                return point;
            }
        });
//        //设置重复模式，RESTART为结束后重新开始，REVERSE为按原来的轨迹逆向返回
       // animator.setRepeatMode(Animation.RESTART);
        //设置重复次数，INFINITE为无限
        animator.setRepeatCount(0);
        animator.start();
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mCircleBall.setX(point.x);
                mCircleBall.setY(point.y);
            }
        });
    }
}
