package com.liang.tind.www.tindtest.activty.widget;

import android.util.Log;
import android.widget.RatingBar;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * @author lonnie.liang
 * @date 2020/03/19 11:59
 */
public class TestRatingBarActivity extends BaseActivity {
  @Override
  protected int getLayoutId() {
    return R.layout.activity_rating_bar;
  }

  @Override
  protected void init() {
    RatingBar ratingBar = findViewById(R.id.rating_bar);
    ratingBar.setMax(5);
    ratingBar.setNumStars(5);
    ratingBar.setOnRatingBarChangeListener(
        (ratingBar1, rating, fromUser) ->
            Log.i(TAG, "onRatingChanged: rating:" + rating + ",fromUser=" + fromUser));
  }
}
