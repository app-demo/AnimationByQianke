package animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.administrator.animation.R;

/**
 * AnimationSet提供了一个把多个动画组合成一个组合的机制，并可设置组中动画的时序关系，如同时播放，顺序播放等。
 */
public class AnimatorSetActivity extends Activity {
    private ImageView mBlueBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_set);

        mBlueBall = (ImageView) findViewById(R.id.id_ball);

    }

    public void togetherRun(View view) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX", 1f, 2f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY", 1f, 2f, 1f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall, "translationX", 0f, 300f, 0f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        //同时执行设置的动画
        //animSet.playTogether(anim1, anim2, anim3);
        //顺序播放 根据你所写顺序进行播放
        animSet.playSequentially(anim1, anim2);
        animSet.start();
    }

    public void playWithAfter(View view) {
        float cx = mBlueBall.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX", 1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY", 1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall, "translationX", 0f, 300f, 0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall, "scaleX", 2f, 0.5f);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(mBlueBall, "scaleY", 2f, 0.5f);

        /**
         * before(anim) 指在anim之前执行  after(anim) 指在anim之后执行  with(anim) 同时执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2).before(anim3);
       // animSet.play(anim3).with(anim4).with(anim5);
        animSet.setDuration(1000);
        animSet.start();
    }
}
