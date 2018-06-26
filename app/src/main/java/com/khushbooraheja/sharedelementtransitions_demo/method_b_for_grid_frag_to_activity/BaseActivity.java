package com.khushbooraheja.sharedelementtransitions_demo.method_b_for_grid_frag_to_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.method_b_for_grid_frag_to_activity.util.BaseActivityView;
import com.khushbooraheja.sharedelementtransitions_demo.method_b_for_grid_frag_to_activity.util.FragmentBaseClassForGrid;
import com.khushbooraheja.sharedelementtransitions_demo.utils.IntentUtils;

import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.IMAGE_URL;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.IMAGE_URL_EXTRA;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_HEIGHT;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_SCREENLOCATION_LEFT;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_SCREENLOCATION_TOP;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.PROPNAME_WIDTH;
import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.VIEW_INFO_EXTRA;

public class BaseActivity extends AppCompatActivity implements BaseActivityView {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base_grid);
    initializeView();
  }

  private void initializeView() {
    loadFragment(FragmentBaseClassForGrid.newInstance(this), "fragmentClassForGrid");
  }

  private void loadFragment (Fragment selectFragment, String tag) {
    FragmentManager manager = getSupportFragmentManager();
    boolean fragmentPopped = manager.popBackStackImmediate(tag, 0);
    if (!fragmentPopped) replaceFragment(selectFragment, R.id.grid_frame, tag);
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
  public void onGridItemClick(ImageView sharedImage) {
    Intent intent = IntentUtils.getTargetActivityIntentForMethodB(this);
    intent.putExtra(IMAGE_URL_EXTRA, IMAGE_URL);
    intent.putExtra(VIEW_INFO_EXTRA, captureValues(sharedImage));

    startActivity(intent);
    overridePendingTransition(0, 0);
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
  
  private void dLog(String message) {
    Log.d("SourceGridFragActivity", message);
  }
}
