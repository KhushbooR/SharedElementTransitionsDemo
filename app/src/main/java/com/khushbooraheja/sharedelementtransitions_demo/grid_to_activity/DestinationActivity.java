package com.khushbooraheja.sharedelementtransitions_demo.grid_to_activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.ActivityDestinationBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.Constants;

public class DestinationActivity extends AppCompatActivity {

  ActivityDestinationBinding destBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    destBinding = DataBindingUtil.setContentView(this, R.layout.activity_destination);
    supportPostponeEnterTransition();
    initializeZoomView();
  }

  private void initializeZoomView() {
    Bundle extras = getIntent().getExtras();
    destBinding.destinationImageView
        .setTransitionName(extras.getString(Constants.KEY_INTENT_TRANSITION_NAME));
    supportStartPostponedEnterTransition();
    destBinding.destinationImageView
        .setImageDrawable(getResources().getDrawable(R.drawable.model));
  }
}
