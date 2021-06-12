package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.UserDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.R;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.UserDetailsViewHolder> {

    private Context context;
    public List<ResponseUser> userDetailAdapterList;
    private RequestManager requestManager;
    private OnImageClick imageClick;

    public UserDetailAdapter(Context context,List<ResponseUser> userDetailAdapterList,RequestManager requestManager,OnImageClick imageClick){

        this.context = context;
        this.userDetailAdapterList = userDetailAdapterList;
        this.requestManager = requestManager;
        this.imageClick = imageClick;

    }

    @NonNull
    @Override
    public UserDetailAdapter.UserDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
        return new UserDetailsViewHolder(view , imageClick);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailAdapter.UserDetailsViewHolder holder, int position) {

        Picasso.get().load(userDetailAdapterList.get(position).getUrls().getSmall()).into(holder.image_3);
        holder.onBind(userDetailAdapterList.get(position));
    }

    @Override
    public int getItemCount() {
        return userDetailAdapterList.size();

    }

    public static class UserDetailsViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView image_3;
        OnImageClick onImageClick;

        public UserDetailsViewHolder(@NonNull View itemView,OnImageClick onImageClick) {
            super(itemView);
            this.onImageClick = onImageClick;

            image_3 = itemView.findViewById(R.id.iv_imageview);

        }

        public void onBind(ResponseUser responseUser) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClick.setOnImageClick(responseUser.getId(),responseUser.getUrls().getSmall(),responseUser.getUrls().getFull(),responseUser.getUrls().getRegular());
                }
            });
        }
    }

    public void addImages(List<ResponseUser> images)

    {
        for (ResponseUser im : images)
        {
            userDetailAdapterList.add(im);
        }

        notifyDataSetChanged();
    }
}
