package com.khushbooraheja.sharedelementtransitions_demo.grid_to_activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.ActivitySourceGridBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.Constants;
import com.khushbooraheja.sharedelementtransitions_demo.utils.IntentUtils;

public class SourceGridActivity extends AppCompatActivity implements SourceGridAcitvityView {

  ActivitySourceGridBinding gridBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gridBinding = DataBindingUtil.setContentView(this, R.layout.activity_source_grid);
    initializeGridView();
  }

  private void initializeGridView() {
    gridBinding.gridView.setLayoutManager(new GridLayoutManager(this, 3));
    gridBinding.gridView.setAdapter(new GridAdapter(this, this));
  }

  @Override
  public void onGridItemClick(ImageView sharedImageView) {
    Intent intent = IntentUtils.getDestinationActivityIntent(this);
    intent.putExtra(Constants.KEY_INTENT_TRANSITION_NAME,
        ViewCompat.getTransitionName(sharedImageView));

    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,
        sharedImageView,
        ViewCompat.getTransitionName(sharedImageView));
    dLog("options=="+options.getLaunchBounds());

    startActivity(intent, options.toBundle());

  }

  private void dLog(String message) {
    Log.d("SourceGridActivity", message);
  }

}
