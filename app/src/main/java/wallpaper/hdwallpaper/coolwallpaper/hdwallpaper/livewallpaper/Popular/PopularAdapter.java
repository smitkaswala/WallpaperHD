package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.MainActivity;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.MainActivity2;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.R;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class    PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private Context context;
    public List<ResponseClass> popularModelList;
    private RequestManager requestManager;
    private OnImageClick imageClick;
//    private List list;

    public PopularAdapter (Context context,List<ResponseClass> popularModelList, RequestManager requestManager,OnImageClick imageClick){

        this.context = context;
        this.popularModelList = popularModelList;
        this.requestManager = requestManager;
        this.imageClick = imageClick;
//        this.list = list;
    }

    @NonNull
    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
        return new PopularViewHolder(view, imageClick);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.PopularViewHolder holder, int position) {

//        holder.progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Picasso.get().load(popularModelList.get(position).urls.small).into(holder.image_1);
                holder.onBind(popularModelList.get(position));

//                holder.progressBar.setVisibility(View.GONE);

            }

        },2500);


    }

    @Override
    public int getItemCount() {
        return popularModelList.size();

    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView image_1;
        OnImageClick onImageClick;
        ProgressBar progressBar;

        public PopularViewHolder(@NonNull View itemView, OnImageClick onImageClick) {
            super(itemView);
            this.onImageClick = onImageClick;

            image_1 = itemView.findViewById(R.id.iv_imageview);
            progressBar = itemView.findViewById(R.id.sProgressBar);

        }


        public void onBind(ResponseClass responseClass) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClick.setOnImageClick(responseClass.id,responseClass.urls.small, responseClass.urls.full, responseClass.urls.regular);
                }
            });
        }
    }
    public void addImages(List<ResponseClass> images)
    {

        for (ResponseClass im : images)

        {
            popularModelList.add(im);
        }

        notifyDataSetChanged();

    }

}
