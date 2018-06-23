package com.khushbooraheja.sharedelementtransitions_demo.viewpager_to_activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.ActivitySourceViewPagerBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.Constants;
import com.khushbooraheja.sharedelementtransitions_demo.utils.IntentUtils;

public class SourceViewPagerActivity extends AppCompatActivity implements SourcePagerActivityView {

  ActivitySourceViewPagerBinding pagerBinding;
  ViewPagerAdapter pagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pagerBinding = DataBindingUtil.setContentView(this,
        R.layout.activity_source_view_pager);
    initializeView();
  }

  private void initializeView() {
    pagerAdapter = new ViewPagerAdapter(this, this);
    pagerBinding.pager.setAdapter(pagerAdapter);
  }

  @Override
  public void onImageItemClicked(ImageView sharedImageView) {
    Intent intent = IntentUtils.getDestinationActivityIntent(this);
    intent.putExtra(Constants.KEY_INTENT_TRANSITION_NAME,
        ViewCompat.getTransitionName(sharedImageView));

    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,
        sharedImageView,
        ViewCompat.getTransitionName(sharedImageView));

    startActivity(intent, options.toBundle());
  }
}
