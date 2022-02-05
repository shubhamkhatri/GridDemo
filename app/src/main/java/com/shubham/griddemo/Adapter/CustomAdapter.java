package com.shubham.griddemo.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.shubham.griddemo.R;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<Integer> listData;
    private String mSpeed, mHeight, mWidth;

    //init list and dynamic values
    public CustomAdapter(ArrayList<Integer> listData, String mSpeed, String mHeight, String mWidth) {
        this.listData = listData;
        this.mSpeed = mSpeed;
        this.mHeight = mHeight;
        this.mWidth = mWidth;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.single_item, parent, false);

        //Setting dynamic width and height
        RelativeLayout relativeLayout = listItem.findViewById(R.id.relative_layout);
        float factor = listItem.getContext().getResources().getDisplayMetrics().density;
        relativeLayout.getLayoutParams().height = (int) (Integer.parseInt(mHeight) * factor);
        relativeLayout.getLayoutParams().width = (int) (Integer.parseInt(mWidth) * factor);
        relativeLayout.requestLayout();

        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(String.valueOf(listData.get(position)));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //flip animation
                ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", 180f);
                animation.setDuration(Integer.parseInt(mSpeed) * 1000);

                ObjectAnimator animation2 = ObjectAnimator.ofFloat(view, "rotationY", 360f);
                animation2.setStartDelay(0);
                animation2.setDuration(0);

                AnimatorSet set = new AnimatorSet();
                set.playSequentially(animation, animation2);
                set.start();

                //delete after flip is done
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (listData.size() != 0) {
                            listData.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, listData.size());
                        } else
                            Toast.makeText(view.getContext(), "All Blocks Deleted!", Toast.LENGTH_LONG).show();
                    }
                }, Integer.parseInt(mSpeed) * 1000);

            }
        });
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.normal_tv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
        }
    }
}

