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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context context;
    public List<FavoriteItem> userDetailAdapterList;
    private RequestManager requestManager;
    private OnImageClick imageClick;

    public FavoriteAdapter(Context context,List<FavoriteItem> userDetailAdapterList,RequestManager requestManager,OnImageClick imageClick){

        this.context = context;
        this.userDetailAdapterList = userDetailAdapterList;
        this.requestManager = requestManager;
        this.imageClick = imageClick;
        Log.d("TAG", "Favorite2Adapter: "+ userDetailAdapterList.size());

    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteAdapter.FavoriteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false),imageClick);

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
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

        public void onBind(FavoriteItem responseUser) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClick.setOnImageClick(responseUser.getKey_id(),responseUser.getSmall_image(),responseUser.getFull_image(),responseUser.getRegular_image());
                }

            });

        }

    }

}
//    private void readCursorData(FavoriteItem favoriteItem, ViewHolder viewHolder) {
//        Cursor cursor = sqliteDatabase.read_all_data(favoriteItem.getKey_id());
//        SQLiteDatabase db = sqliteDatabase.getReadableDatabase();
//        try{
//            while (cursor.moveToNext()){
//                String item_fav_status = cursor.getString(cursor.getColumnIndex(SqliteDatabase.THUMB_IMAGE));
//                favoriteItem.setFavStatus(item_fav_status);
//
//                if (item_fav_status != null && item_fav_status.equals("1")){
//                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite2);
//                }else if (item_fav_status != null && item_fav_status.equals("0")){
//                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite);
//                }
//            }
//        }finally {
//            if (cursor != null && cursor.isClosed())
//                cursor.close();
//            db.close();
//        }
//
//    }


