package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.SearchWallpaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.R;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchWallpaperAdapter extends RecyclerView.Adapter<SearchWallpaperAdapter.SearchWallpaperViewHolder> {

    private Context context;
    private List<ResultsItem> responseWallpapersList;
    private RequestManager requestManager;
    private OnImageClick imageClick;

    public SearchWallpaperAdapter(Context context, List<ResultsItem> responseWallpapersList, RequestManager requestManager, OnImageClick imageClick){
        this.context = context;
        this.responseWallpapersList = responseWallpapersList;
        this.requestManager =requestManager;
        this.imageClick = imageClick;
    }

    @NonNull
    @Override
    public SearchWallpaperAdapter.SearchWallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collation_wallpaper,parent,false);
        return new SearchWallpaperViewHolder(view,imageClick);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchWallpaperAdapter.SearchWallpaperViewHolder holder, int position) {

       Picasso.get().load(responseWallpapersList.get(position).getUrls().getSmall()).into(holder.iv_imageview);
        holder.onBind(responseWallpapersList.get(position));
    }

    @Override
    public int getItemCount() {
        return responseWallpapersList.size();
    }

    public class SearchWallpaperViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_imageview;

        OnImageClick onImageClick;

        public SearchWallpaperViewHolder(@NonNull View itemView,OnImageClick onImageClick) {
            super(itemView);

            this.onImageClick = onImageClick;

            iv_imageview = itemView.findViewById(R.id.iv_collection);

        }

        public void onBind(ResultsItem responseWallpaper) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onImageClick.setOnImageClick(responseWallpaper.getId(),responseWallpaper.getUrls().getSmall(),responseWallpaper.getUrls().getFull(),responseWallpaper.getUrls().getRegular());

                }

            });
        }

    }

    public void addImages(List<ResultsItem> images)
    {

        for (ResultsItem im : images)

        {
            responseWallpapersList.add(im);
        }

        notifyDataSetChanged();

    }

}
