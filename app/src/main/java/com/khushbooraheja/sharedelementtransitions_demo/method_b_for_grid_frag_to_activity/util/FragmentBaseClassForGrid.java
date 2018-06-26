package com.khushbooraheja.sharedelementtransitions_demo.method_b_for_grid_frag_to_activity.util;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.ActivitySourceGridBinding;

public class FragmentBaseClassForGrid extends Fragment implements FragmentCallBack {

  ActivitySourceGridBinding gridBinding;
  BaseActivityView activityView;

  public static FragmentBaseClassForGrid newInstance(BaseActivityView activityView) {
    FragmentBaseClassForGrid frag = new FragmentBaseClassForGrid();
    frag.dLog("=newInstance==");
    frag.setActivityView(activityView);
    return frag;
  }

  public void setActivityView(BaseActivityView activityView) {
    this.activityView = activityView;
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull
          LayoutInflater inflater,
      @Nullable
          ViewGroup container,
      @Nullable
          Bundle savedInstanceState) {
    dLog("onCreateView==");
    gridBinding = DataBindingUtil.inflate(inflater,
        R.layout.activity_source_grid, container, false);
    return gridBinding.getRoot();
  }

  @Override
  public void onStart() {
    dLog("onStart==");
    super.onStart();
    initializeGridView();
  }

  private void initializeGridView() {
    gridBinding.gridView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    gridBinding.gridView.setAdapter(new FragmentGridAdapter(getActivity(), this));
  }

  @Override
  public void onGridItemClick(ImageView sharedImageView) {
    activityView.onGridItemClick(sharedImageView);
  }

  private void dLog(String message) {
    Log.d("FragmentClassForGrid", message);
  }
}
