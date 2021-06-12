package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Latest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.R;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.LatestViewHolder> {

    private Context context;
    private List<ResponseModel> responseModelList;
    private RequestManager requestManager;
    private OnImageClick imageClick;


    public LatestAdapter(Context context, List<ResponseModel> responseModelList, RequestManager requestManager, OnImageClick imageClick, Activity activity){

        this.context = context;
        this.responseModelList = responseModelList;
        this.requestManager = requestManager;
        this.imageClick = imageClick;

    }

    @NonNull
    @Override
    public LatestAdapter.LatestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
        return new LatestViewHolder(view, imageClick);



    }

    @Override
    public void onBindViewHolder(@NonNull LatestAdapter.LatestViewHolder holder, int position) {

//        holder.progressBar.setVisibility(View.VISIBLE);

//        this.requestManager.load(responseModelList.get(position).getUrls().getThumb()).into(holder.image_2);
        Picasso.get().load(responseModelList.get(position).getUrls().getSmall()).into(holder.image_2);
        holder.onBind(responseModelList.get(position));

//        holder.progressBar.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return responseModelList.size();

    }

    public static class LatestViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView image_2;
        ImageView iv_image;
        OnImageClick onImageClick;
        ProgressBar progressBar;

        public LatestViewHolder(@NonNull View itemView,OnImageClick onImageClick) {
            super(itemView);
            this.onImageClick = onImageClick;

            image_2 = itemView.findViewById(R.id.iv_imageview);
            iv_image = itemView.findViewById(R.id.iv_image);
            progressBar = itemView.findViewById(R.id.sProgressBar);

        }

        public void onBind(ResponseModel responseModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onImageClick.setOnImageClick(responseModel.getId(),responseModel.getUrls().getSmall(),responseModel.getUrls().getFull(),responseModel.getUrls().getRegular());

                }

            });

        }

    }

    public void addImages1(List<ResponseModel> images)

    {

        for (ResponseModel im : images)
        {
            responseModelList.add(im);
        }
        notifyDataSetChanged();

    }

}
