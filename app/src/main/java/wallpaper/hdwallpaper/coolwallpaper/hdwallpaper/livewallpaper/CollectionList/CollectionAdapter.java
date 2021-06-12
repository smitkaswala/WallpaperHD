package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionText;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionViewer;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.R;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    public Context context;
    private List<ResultsItem> collectionResponseList;
    private List<CollectionText> collectionTexts;
    private RequestManager requestManager;
    private OnImageClick imageClick;

    public CollectionAdapter (Context context, List<ResultsItem> collectionResponseList, RequestManager requestManager, OnImageClick imageClick ,List<CollectionText>  collectionTexts) {

        this.context = context;
        this.collectionResponseList = collectionResponseList;
        this.requestManager = requestManager;
        this.imageClick = imageClick;
        this.collectionTexts = collectionTexts;
    }



    @NonNull
    @Override

    public CollectionAdapter.CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collation_wallpaper_id,parent,false);
        return new CollectionViewHolder(view,imageClick,requestManager);

    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.CollectionViewHolder holder, int position) {
//        ResultsItem resultsItem = collectionResponseList.get(position);

        Picasso.get().load(collectionResponseList.get(position).getCoverPhoto().getUrls().getRegular()).into(holder.iv_imageview);
        holder.text1.setText(collectionResponseList.get(position).getTitle());
        holder.text2.setText(String.valueOf(collectionResponseList.get(position).getTotalPhotos()+" "+"Photos"));

        holder.onBind(collectionResponseList.get(position));

//        Log.d("TAG", "onBindViewHolder: "+collectionResponseList.get(position).getCoverPhoto().getUrls().getSmall());
//        holder.onBind(resultsItem,resultsItem.getCoverPhoto().urls);

    }

    @Override
    public int getItemCount() {
        return collectionResponseList.size();

    }

    public static class CollectionViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_imageview;
        OnImageClick onImageClick;
        RequestManager requestManager;
        TextView text1,text2;

        public CollectionViewHolder(@NonNull View itemView, OnImageClick onImageClick,RequestManager requestManager) {
            super(itemView);
            this.onImageClick = onImageClick;
            this.requestManager = requestManager;

            iv_imageview = itemView.findViewById(R.id.iv_collection);
            text1 = itemView.findViewById(R.id.image_name);
            text2 = itemView.findViewById(R.id.photos_name);
//
        }

        public void onBind(ResultsItem resultsItem) {
//            Picasso.get().load(resultsItem.getCoverPhoto().getUrls().getThumb()).into(iv_imageview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    onImageClick.setOnImageClick(resultsItem.getId(),resultsItem.getCoverPhoto().getUrls().getSmall(),resultsItem.getCoverPhoto().getUrls().getFull());
//                    v.getContext().startActivity(new Intent(v.getContext(),CollectionViewer.class));
                    Intent intent = new Intent(v.getContext(),CollectionViewer.class);
                    intent.putExtra("id",resultsItem.getId());
                    v.getContext().startActivity(intent);
                }

            });
        }
    }

    public void addImages(List<ResultsItem> images)

    {

        for (ResultsItem im : images)

        {
            collectionResponseList.add(im);
        }

        notifyDataSetChanged();

    }

}
