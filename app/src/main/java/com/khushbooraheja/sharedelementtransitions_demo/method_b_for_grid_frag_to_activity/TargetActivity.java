package com.khushbooraheja.sharedelementtransitions_demo.method_b_for_grid_frag_to_activity;

import android.animation.TimeInterpolator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.ActivityTargetBinding;

import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.IMAGE_URL_EXTRA;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_HEIGHT;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_SCREENLOCATION_LEFT;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_SCREENLOCATION_TOP;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_WIDTH;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.VIEW_INFO_EXTRA;

public class TargetActivity extends AppCompatActivity {

  private static final long DEFAULT_DURATION = 1500;
  private static final TimeInterpolator DEFAULT_INTERPOLATOR = input -> 5;

  ActivityTargetBinding targetBinding;
  String imageUrl;
  private Bundle mStartValues, mEndValues;
  private float scaleX, scaleY;
  private int deltaX, deltaY;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    targetBinding = DataBindingUtil.setContentView(this, R.layout.activity_target);

    extractViewInfoFromBundle(getIntent());

    Glide.with(this)
        .load(imageUrl)
        .listener(new RequestListener<Drawable>() {
          @Override
          public boolean onLoadFailed(
              @Nullable
                  GlideException e, Object model, Target<Drawable> target,
              boolean isFirstResource) {
            return false;
          }

          @Override
          public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
              DataSource dataSource, boolean isFirstResource) {
            onUIReady();
            return false;
          }
        })
        .into(targetBinding.targetImageView);
  }

  private void onUIReady() {
    targetBinding.targetImageView
        .getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override
      public boolean onPreDraw() {
        // remove previous listener
        targetBinding.targetImageView.getViewTreeObserver().removeOnPreDrawListener(this);
        // prep the scene
        prepareScene();
        // run the animation
        runEnterAnimation();
        return true;
      }
    });
  }

  private void prepareScene() {
    // capture the end values in the destination view
    mEndValues = captureValues(targetBinding.targetImageView);

    // calculate the scale and position deltas
    scaleX = scaleDeltaX(mStartValues, mEndValues);
    scaleY = scaleDeltaY(mStartValues, mEndValues);
    deltaX = translationDeltaX(mStartValues, mEndValues);
    deltaY = translationDeltaY(mStartValues, mEndValues);

    // scale and reposition the image
    targetBinding.targetImageView.setScaleX(scaleX);
    targetBinding.targetImageView.setScaleY(scaleY);
    targetBinding.targetImageView.setTranslationX(deltaX);
    targetBinding.targetImageView.setTranslationY(deltaY);
  }

  private int translationDeltaX(Bundle mStartValues, Bundle mEndValues) {
    int startTranslationX = mStartValues.getInt(PROPNAME_SCREENLOCATION_LEFT);
    int endTranslationX = mEndValues.getInt(PROPNAME_SCREENLOCATION_LEFT);
    if (endTranslationX > startTranslationX) {
      return endTranslationX - startTranslationX;
    }
    return startTranslationX - endTranslationX;
  }

  private int translationDeltaY(Bundle mStartValues, Bundle mEndValues) {
    int startTranslationY = mStartValues.getInt(PROPNAME_SCREENLOCATION_TOP);
    int endTranslationY = mEndValues.getInt(PROPNAME_SCREENLOCATION_TOP);
    if (endTranslationY > startTranslationY) {
      return endTranslationY - startTranslationY;
    }
    return startTranslationY - endTranslationY;
  }

  private float scaleDeltaX(Bundle mStartValues, Bundle mEndValues) {
    float startScaleX = mStartValues.getInt(PROPNAME_WIDTH);
    float endScaleX = mEndValues.getInt(PROPNAME_WIDTH);
    if (startScaleX > endScaleX) {
      return endScaleX / startScaleX;
    }
    return startScaleX / endScaleX;
  }

  private float scaleDeltaY(Bundle mStartValues, Bundle mEndValues) {
    float startScaleY = mStartValues.getInt(PROPNAME_HEIGHT);
    float endScaleY = mEndValues.getInt(PROPNAME_HEIGHT);
    if (startScaleY > endScaleY) {
      return endScaleY / startScaleY;
    }
    return startScaleY / endScaleY;
  }

  private void runEnterAnimation() {
    // We can now make it visible
    targetBinding.targetImageView.setVisibility(View.VISIBLE);
    // finally, run the animation
    targetBinding.targetImageView.animate()
        .setDuration(DEFAULT_DURATION)
        .setInterpolator(DEFAULT_INTERPOLATOR)
        //.scaleX(1f)
        //.scaleY(1f)
        //.translationX(0)
        //.translationY(0)
        .start();
  }

  private void runExitAnimation() {
    targetBinding.targetImageView.animate()
        .setDuration(DEFAULT_DURATION)
        .setInterpolator(DEFAULT_INTERPOLATOR)
        .scaleX(scaleX)
        .scaleY(scaleY)
        .translationX(deltaX)
        .translationY(deltaY)
        .withEndAction(() -> {
          finish();
          overridePendingTransition(0, 0);
        }).start();
  }

  private void extractViewInfoFromBundle(Intent intent) {
    imageUrl = intent.getStringExtra(IMAGE_URL_EXTRA);
    mStartValues = intent.getBundleExtra(VIEW_INFO_EXTRA);
  }

  private Bundle captureValues(@NonNull View view) {
    Bundle b = new Bundle();
    int[] screenLocation = new int[2];
    view.getLocationOnScreen(screenLocation);
    b.putInt(PROPNAME_SCREENLOCATION_LEFT, screenLocation[0]);
    b.putInt(PROPNAME_SCREENLOCATION_TOP, screenLocation[1]);
    b.putInt(PROPNAME_WIDTH, view.getWidth());
    b.putInt(PROPNAME_HEIGHT, view.getHeight());
    return b;
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    runExitAnimation();
  }
}
