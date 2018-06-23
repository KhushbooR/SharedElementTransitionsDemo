package com.khushbooraheja.sharedelementtransitions_demo.viewpager_to_activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.PagerItemForActivityBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.Constants;

public class ViewPagerAdapter extends PagerAdapter {
  public static final String TAG = ViewPagerAdapter.class.getCanonicalName();

  private Context mContext;

  LayoutInflater mLayoutInflater;
  private SourcePagerActivityView homeFragmentView;
  private PagerItemForActivityBinding itemBinding;

  public ViewPagerAdapter (Context context, SourcePagerActivityView homeFragmentView) {
    mContext = context;
    this.homeFragmentView = homeFragmentView;
    mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return 10;
  }

  @Override
  public boolean isViewFromObject(
      @NonNull
          View view,
      @NonNull
          Object object) {
    return view == (object);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {

    System.out.println("====object ininitiakodn");
    itemBinding =
        DataBindingUtil.inflate(mLayoutInflater,
            R.layout.pager_item_for_activity, container, false);

    container.addView(itemBinding.getRoot());

    itemBinding.imagePagerItem
        .setImageDrawable(mContext.getResources().getDrawable(R.drawable.model));
    ViewCompat.setTransitionName(itemBinding.imagePagerItem, Constants.TRANSITION_NAME_IMAGE_TO_ACTIVITY);
    itemBinding.imagePagerItem
        .setOnClickListener(v -> homeFragmentView.onImageItemClicked(itemBinding.imagePagerItem));
    return itemBinding.getRoot();
  }

  @Override
  public void destroyItem(ViewGroup collection, int position, Object view) {
    collection.removeView((View) view);
  }

}
