package com.khushbooraheja.sharedelementtransitions_demo.grid__frag_to_activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.ActivitySourceGridFragmentBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.Constants;
import com.khushbooraheja.sharedelementtransitions_demo.utils.IntentUtils;

public class SourceGridFragmentActivity extends AppCompatActivity
    implements SourceGridFragActivityView {

  ActivitySourceGridFragmentBinding gridFragmentBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    dLog("onCreate==");
    super.onCreate(savedInstanceState);
    gridFragmentBinding = DataBindingUtil
        .setContentView(this, R.layout.activity_source_grid_fragment);
    initializeView();
  }

  private void initializeView() {
    loadFragment(FragmentClassForGrid.newInstance(this), "fragmentClassForGrid");
  }

  private void loadFragment (Fragment selectFragment, String tag) {
    FragmentManager manager = getSupportFragmentManager();
    boolean fragmentPopped = manager.popBackStackImmediate(tag, 0);
    if (!fragmentPopped) replaceFragment(selectFragment, R.id.grid_frame_container, tag);
  }

  public void replaceFragment(Fragment fragment, int layout_ID, String tag) {
    FragmentTransaction transaction =
        getSupportFragmentManager().beginTransaction();
    transaction.replace(layout_ID, fragment, tag);
    transaction.addToBackStack(tag);
    transaction.commitAllowingStateLoss();
  }

  @Override
  public void onBackPressed() {
    dLog("onBackPressed==");
    super.onBackPressed();
    finish();
  }

  @Override
  public void gridItemOnFragmentClicked(ImageView sharedImage) {
    Intent intent = IntentUtils.getDestinationActivityIntent(this);
    intent.putExtra(Constants.KEY_INTENT_TRANSITION_NAME,
        ViewCompat.getTransitionName(sharedImage));

    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,
        sharedImage,
        ViewCompat.getTransitionName(sharedImage));
    dLog("options=="+options.getLaunchBounds());

    startActivity(intent, options.toBundle());
  }

  private void dLog(String message) {
    Log.d("SourceGridFragActivity", message);
  }

}
