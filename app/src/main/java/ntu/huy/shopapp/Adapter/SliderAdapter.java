package ntu.huy.shopapp.Adapter;

import android.content.Context;

import com.bumptech.glide.Glide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import ntu.huy.shopapp.Domain.BannerModel;
import ntu.huy.shopapp.R;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private ArrayList<BannerModel> SliderItems;
    private ViewPager2 viewPager2;
    private Context context;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SliderItems.addAll(SliderItems);
            notifyDataSetChanged();
        }
    };

    public SliderAdapter(ViewPager2 viewPager2, ArrayList<BannerModel> sliderItems) {
        this.viewPager2 = viewPager2;
        SliderItems = sliderItems;
    }

    @NonNull
    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(context).inflate(R.layout.slider_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
        holder.setImage(SliderItems.get(position));
        if(position == SliderItems.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return SliderItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iamgeSlide);
        }

        void setImage(BannerModel bannerModel){
            Glide.with(context)
                    .load(bannerModel.getUrl())
                    .into(imageView);
        }
    }
}
