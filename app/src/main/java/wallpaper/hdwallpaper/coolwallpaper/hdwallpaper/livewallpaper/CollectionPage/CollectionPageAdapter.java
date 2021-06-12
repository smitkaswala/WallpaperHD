package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.R;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectionPageAdapter extends RecyclerView.Adapter<CollectionPageAdapter.CollectionPageViewHolder> {

    private Context context;
    private List<ResponseCollection> responseCollectionList;
    private RequestManager requestManager;
    private OnImageClick imageClick;

    public CollectionPageAdapter(Context context, List<ResponseCollection> responseCollectionList, RequestManager requestManager, OnImageClick imageClick){
        this.context = context;
        this.responseCollectionList =responseCollectionList;
        this.requestManager = requestManager;
        this.imageClick = imageClick;
    }

    @NonNull
    @Override
    public CollectionPageAdapter.CollectionPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
        return new CollectionPageViewHolder(view,imageClick);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionPageAdapter.CollectionPageViewHolder holder, int position) {

        Picasso.get().load(responseCollectionList.get(position).getUrls().getSmall()).into(holder.image_2);
        holder.onBind(responseCollectionList.get(position));

    }

    @Override
    public int getItemCount() {
        return responseCollectionList.size();
    }

    public class CollectionPageViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView image_2;
        ImageView iv_image;
        OnImageClick onImageClick;

        public CollectionPageViewHolder(@NonNull View itemView, OnImageClick onImageClick) {
            super(itemView);
            this.onImageClick = onImageClick;

            image_2 = itemView.findViewById(R.id.iv_imageview);
            iv_image = itemView.findViewById(R.id.iv_image);

        }

        public void onBind(ResponseCollection responseCollection) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onImageClick.setOnImageClick(responseCollection.getId(),responseCollection.getUrls().getSmall(),responseCollection.getUrls().getFull(),responseCollection.getUrls().getRegular());
                }
            });
        }
    }

    public void addImages(List<ResponseCollection> images){

        for (ResponseCollection im : images){

            responseCollectionList.add(im);

        }

        notifyDataSetChanged();

    }

}
