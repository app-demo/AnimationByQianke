package animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.animation.R;


/**
 * 有关动画的属性值的含义
 * 1）translationX 和 translationY：这两个属性控制了View所处的位置，它们的值是由layout容器设置的，是相对于坐标原点（0，0左上角）的一个偏移量。
 * 2）rotation, rotationX 和 rotationY：控制View绕着轴点（pivotX和pivotY）旋转。
 * 3）scaleX 和 scaleY：控制View基于pivotX和pivotY的缩放。
 * 4）pivotX 和 pivotY：旋转的轴点和缩放的基准点，默认是View的中心点。
 * 5）x 和 y：描述了view在其父容器中的最终位置，是左上角左标和偏移量（translationX，translationY）的和。
 * 6）alpha：透明度，1是完全不透明，0是完全透明。
 * <p/>
 * <p/>
 * ObjectAnimator继承自ValueAnimator
 */

public class ObjectAnimActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_animator);
    }


    /**
     * 最简单的旋转动画
     *
     * @param view
     */
//    public void rotateyAnimRun(View view) {
//        ObjectAnimator
//                /**
//                 *  view  动画作用对象
//                 *  "rotationX" 作用的属性
//                 *  values(0.0F 360.0F) 这是一组值，就是由什么值变动到另外一个什么值，再到什么什么值。。。之类的。
//                 *  values，只设置一个的时候，会认为当前对象该属性的值为开始（getPropName反射获取），然后设置的值为终点。如果设置两个，则一个为开始、一个为结束
//                 */
//                .ofFloat(view, "translationX", 100, 200, 500)
//                .setDuration(500)//执行时间
//                .start();
//    }

    /**
     * 缩小 淡出  简单组合动画
     *
     * @param view
     */
    public void rotateyAnimRun(final View view) {
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(new View(this), "anim", 1.0F, 0.5F)
                .setDuration(500);
        anim.start();

        /**
         * 在动画的帧上调用这个方法。通过监听这个事件，使用在动画期间由ValueAnimator对象产生的计算值,
         * 通过新的动画值来强制屏幕的指定区域进行重绘。在View对象上的所有属性的设置器，如setAlpha()、
         * setTranslationX()等方法都会正确的让View对象失效，因此在调用这些方法设置新值的时候，你不需要让该View对象失效
         */
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();//相当于遍历values 得到每一个value值 进行重新设置  重绘
                Log.e("cccccc", cVal + "");
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });

    }

    /**
     * 使用PropertyValuesHolder 的简单组合动画
     * 1）我们可以通过 PropertyValuesHolder类的工厂方法ofInt, ofFloat等方法，让它能够Hold住对应property的value。
     * 2）在利用ObjectAnimator的ofPropertyValuesHolder方法时，再将我们上面定义的propertyValuesHolder给传进去，定义出一个objectAnimator。
     * <p/>
     * 可以单独的设置每一个属性值 可以实现较复杂的动画
     *
     * @param view
     */
//    public void rotateyAnimRun(View view) {
//
//        PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat("alpha", 1f, 0.2f);
////        PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.2f);
////        PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.2f);
//        PropertyValuesHolder pvhTX = PropertyValuesHolder.ofFloat("translationX", 0f, 500f);
//        PropertyValuesHolder pvhTY = PropertyValuesHolder.ofFloat("translationY", 0f, 800f);
//        ObjectAnimator.ofPropertyValuesHolder(view, pvhA, pvhTX, pvhTY).setDuration(1000).start();
//    }
}
