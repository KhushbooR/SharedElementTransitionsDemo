package com.khushbooraheja.sharedelementtransitions_demo.grid__frag_to_activity;

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
import com.khushbooraheja.sharedelementtransitions_demo.grid_to_activity.GridAdapter;
import com.khushbooraheja.sharedelementtransitions_demo.grid_to_activity.SourceGridAcitvityView;

public class FragmentClassForGrid extends Fragment implements SourceGridAcitvityView {

  ActivitySourceGridBinding gridBinding;
  SourceGridFragActivityView activityView;

  public static FragmentClassForGrid newInstance(SourceGridFragActivityView activityView) {
    FragmentClassForGrid frag = new FragmentClassForGrid();
    frag.dLog("=newInstance==");
    frag.setActivityView(activityView);
    return frag;
  }

  public void setActivityView(SourceGridFragActivityView activityView) {
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
    gridBinding.gridView.setAdapter(new GridAdapter(getActivity(), this));
  }

  @Override
  public void onGridItemClick(ImageView sharedImageView) {
    activityView.gridItemOnFragmentClicked(sharedImageView);
  }

  private void dLog(String message) {
    Log.d("FragmentClassForGrid", message);
  }
}
