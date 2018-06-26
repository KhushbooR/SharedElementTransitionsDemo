package com.khushbooraheja.sharedelementtransitions_demo.method_b_for_grid_frag_to_activity.util;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.khushbooraheja.sharedelementtransitions_demo.R;
import com.khushbooraheja.sharedelementtransitions_demo.databinding.GridItemForGridToActivityBinding;
import com.khushbooraheja.sharedelementtransitions_demo.utils.Constants;

import static com.khushbooraheja.sharedelementtransitions_demo.utils.Constants.IMAGE_URL;

public class FragmentGridAdapter extends RecyclerView.Adapter<FragmentGridAdapter.ViewHolder>{

  private GridItemForGridToActivityBinding itemBinding;
  private Context context;
  private LayoutInflater mInflater;
  private FragmentCallBack fragmentCallBack;

  public FragmentGridAdapter(Context context, FragmentCallBack fragmentCallBack) {
    mInflater = LayoutInflater.from(context);
    this.context = context;
    this.fragmentCallBack = fragmentCallBack;
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
    Glide.with(context)
        .load(IMAGE_URL)
        .listener(new RequestListener<Drawable>() {
          @Override
          public boolean onLoadFailed(
              @Nullable
                  GlideException e, Object model, Target<Drawable> target,
              boolean isFirstResource) {
            dLog("onLoadFailed");
            return false;
          }

          @Override
          public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
              DataSource dataSource, boolean isFirstResource) {
            dLog("onResourceReady");
            return false;
          }
        })
        .into(holder.imageView);

    holder.imageView.setOnClickListener(v ->
        fragmentCallBack.onGridItemClick(holder.imageView));
  }

  @Override
  public int getItemCount() {
    return 30;
  }

  private void dLog(String message) {
    Log.d("FragmentGridAdapter", message);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    ViewHolder(View itemView) {
      super(itemView);
      imageView = itemBinding.imageGridItem;
    }

  }}
