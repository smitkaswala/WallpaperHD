package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;

import java.util.List;

public class Favorite2Adapter extends RecyclerView.Adapter<Favorite2Adapter.FavoriteViewHolder> {

    private Context context;
    public List<FavoriteImage> userDetailAdapterList;
    private RequestManager requestManager;
    private OnImageClick imageClick;

    public Favorite2Adapter(Context context,List<FavoriteImage> userDetailAdapterList,RequestManager requestManager,OnImageClick imageClick){

        this.context = context;
        this.userDetailAdapterList = userDetailAdapterList;
        this.requestManager = requestManager;
        this.imageClick = imageClick;

        Log.d("TAG", "Favorite2Adapter: "+ userDetailAdapterList.size());

    }

    @NonNull
    @Override
    public Favorite2Adapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Favorite2Adapter.FavoriteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false),imageClick);

    }

    @Override
    public void onBindViewHolder(@NonNull Favorite2Adapter.FavoriteViewHolder holder, int position) {
        this.requestManager.load(userDetailAdapterList.get(position).getFull_image()).into(holder.image_3);
        holder.onBind(userDetailAdapterList.get(position));
        Log.d("TAG", "Favorite2Adapter: "+position);

    }

    @Override
    public int getItemCount() {
        return userDetailAdapterList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder{

        ImageView image_3;
        OnImageClick onImageClick;

        public FavoriteViewHolder(@NonNull View itemView,OnImageClick onImageClick) {
            super(itemView);
            this.onImageClick = onImageClick;

            image_3 = itemView.findViewById(R.id.iv_imageview);

        }

        public void onBind(FavoriteImage responseUser) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClick.setOnImageClick(responseUser.getKey_id(),responseUser.getSmall_image(),responseUser.getFull_image(),responseUser.getRegular_image());
                }

            });

        }

    }

}
   /* View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
 this.requestManager.load(userDetailAdapterList.get(position).getSmall_image()).into(holder.image_3);
         Log.d("TAG", "Favorite2Adapter: "+position);

         holder.onBind(userDetailAdapterList.get(position));*/