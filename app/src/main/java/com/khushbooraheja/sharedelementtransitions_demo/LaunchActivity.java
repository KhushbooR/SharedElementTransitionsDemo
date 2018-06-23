package com.khushbooraheja.sharedelementtransitions_demo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.ActivityLaunchBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.IntentUtils;

public class LaunchActivity extends AppCompatActivity {

  ActivityLaunchBinding launchBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    launchBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
    setClickableOnButtons();
  }

  private void setClickableOnButtons() {
    launchBinding.btnGridToActivity
        .setOnClickListener(v ->
            startActivity(IntentUtils.getSourceGridActivityIntent(this)));
    launchBinding.btnGridFragToActivity
        .setOnClickListener(v ->
            startActivity(IntentUtils.getSourceGridFragmentActivityIntent(this)));
    launchBinding.btnViewPagerToActivity
        .setOnClickListener(v ->
            startActivity(IntentUtils.getSourceViewPagerActivityIntent(this)));
  }
}
