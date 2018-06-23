package com.khushbooraheja.sharedelementtransitions_demo.utils;

import android.content.Context;
import android.content.Intent;
import com.khushbooraheja.sharedelementtransitions_demo.grid__frag_to_activity.SourceGridFragmentActivity;
import com.khushbooraheja.sharedelementtransitions_demo.grid_to_activity.DestinationActivity;
import com.khushbooraheja.sharedelementtransitions_demo.grid_to_activity.SourceGridActivity;
import com.khushbooraheja.sharedelementtransitions_demo.viewpager_to_activity.SourceViewPagerActivity;

public class IntentUtils {

  public static Intent getSourceGridActivityIntent(Context context) {
    return new Intent(context, SourceGridActivity.class);
  }

  public static Intent getSourceGridFragmentActivityIntent(Context context) {
    return new Intent(context, SourceGridFragmentActivity.class);
  }

  public static Intent getDestinationActivityIntent(Context context) {
    return new Intent(context, DestinationActivity.class);
  }

  public static Intent getSourceViewPagerActivityIntent(Context context) {
    return new Intent(context, SourceViewPagerActivity.class);
  }
}
