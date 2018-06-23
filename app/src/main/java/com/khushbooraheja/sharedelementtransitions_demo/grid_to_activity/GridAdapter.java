package com.khushbooraheja.sharedelementtransitions_demo.grid_to_activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.GridItemForGridToActivityBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.Constants;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{

  private GridItemForGridToActivityBinding itemBinding;
  private Context context;
  private LayoutInflater mInflater;
  private SourceGridAcitvityView gridAcitvityView;

  public GridAdapter(Context context, SourceGridAcitvityView gridAcitvityView) {
    mInflater = LayoutInflater.from(context);
    this.context = context;
    this.gridAcitvityView = gridAcitvityView;
  }

  @Override
  public ViewHolder onCreateViewHolder(
          ViewGroup parent, int viewType) {
    itemBinding = DataBindingUtil
        .inflate(mInflater, R.layout.grid_item_for_grid_to_activity, parent, false);
    return new ViewHolder(itemBinding.getRoot());
  }

  @Override
  public void onBindViewHolder(
          ViewHolder holder, int position) {
    ViewCompat.setTransitionName(holder.imageView, Constants.TRANSITION_NAME_IMAGE_TO_ACTIVITY);
    holder.imageView.setOnClickListener(v ->
        gridAcitvityView.onGridItemClick(holder.imageView));
  }

  @Override
  public int getItemCount() {
    return 30;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    ViewHolder(View itemView) {
      super(itemView);
      imageView = itemBinding.imageGridItem;
    }

  }}
