package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;


public class DownloadFragment extends Fragment {

    OnImageClick onImageClick;


    private RecyclerView recyclerView1;
    private SqliteDatabase2 sqliteDatabase2;
    private FavoriteAdapter favoriteAdapter;

    private AdView adView;

    GridLayoutManager gridLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download, container, false);

        recyclerView1 = view.findViewById(R.id.rec_4);

        sqliteDatabase2 = new SqliteDatabase2(getActivity());
        gridLayoutManager = new GridLayoutManager(getContext(), 2);

        onImageClick = new OnImageClick() {
            @Override
            public void setOnImageClick(String id, String small, String full, String regular) {

                Intent intent = new Intent(getActivity(), FullImageView.class);

                intent.putExtra("id", id);
                intent.putExtra("small", small);
                intent.putExtra("full", full);
                intent.putExtra("regular", regular);
                startActivity(intent);

            }

        };


//        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        adView = view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);




        return view;

    }

    @Override
    public void onStart() {

        if (sqliteDatabase2.favoriteItemList() != null && sqliteDatabase2.favoriteItemList().size() >=1)

        {
            recyclerView1.setLayoutManager(gridLayoutManager);
            favoriteAdapter = new FavoriteAdapter(getContext(), sqliteDatabase2.favoriteItemList(), initGlide(), onImageClick);
            recyclerView1.setAdapter(favoriteAdapter);
        }

        super.onStart();
    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(getContext()).setDefaultRequestOptions(options);

    }

}