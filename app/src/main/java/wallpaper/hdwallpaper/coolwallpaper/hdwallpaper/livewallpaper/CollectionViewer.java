package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionPage.CollectionPageAdapter;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionPage.ResponseCollection;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphFloatingActionButton;

public class CollectionViewer extends AppCompatActivity {

    NeumorphFloatingActionButton back_arrow ,popup_bar;
    String id;
    OnImageClick onImageClick;
    CollectionPageAdapter collectionPageAdapter;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ProgressBar progressBar;
    Boolean isScrolling = false;
    private int page = 1;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_viewer);

        back_arrow = findViewById(R.id.back_arrow);
        popup_bar = findViewById(R.id.popup_bar);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView = findViewById(R.id.rec_2);

        gridLayoutManager = new GridLayoutManager(CollectionViewer.this, 3);

        id = getIntent().getStringExtra("id");

        CollectionPage();

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        onImageClick = new OnImageClick() {
            @Override
            public void setOnImageClick(String id,String small ,String full ,String regular) {

                Intent intent = new Intent(CollectionViewer.this, FullImageView.class);

                intent.putExtra("id",id);
                intent.putExtra("small",small);
                intent.putExtra("full",full);
                intent.putExtra("regular",regular);
                startActivity(intent);

            }

        };

        popup_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(CollectionViewer.this, popup_bar);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_2, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.privacy_policy:

                                startActivity(new Intent(CollectionViewer.this, PrivacyPolicyActivity.class));
                                return true;
                            default:
                                return CollectionViewer.super.onOptionsItemSelected(item);

                            case R.id.rate_us:

                                try {
                                    Uri uri = Uri.parse("market://details?id=" + getPackageName() + "");
                                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(goMarket);
                                } catch (ActivityNotFoundException e) {
                                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() + "");
                                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(goMarket);
                                }

                                break;

                            case R.id.share_app:

                                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                                i.setType("text/plain");
                                i.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                                startActivity(Intent.createChooser(i, "Share via"));

                                break;

                        }

//                        Toast.makeText(CollectionViewer.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;

                    }

                });

                popupMenu.show();

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItems = gridLayoutManager.getChildCount();
                int totalItems = gridLayoutManager.getItemCount();
                int scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {

                    isScrolling = false;
                    page++;
                    Log.d("TAG+++++++++", "onScrolled: " + page);

                    performPagaination(page);

                }

            }

        });

    }

    private void CollectionPage(){

        recyclerView.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        Call<List<ResponseCollection>> call = RetrofitClient.getInstance().getModel().getpost5(id,
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                "1","30");

        call.enqueue(new Callback<List<ResponseCollection>>() {
            @Override
            public void onResponse(Call<List<ResponseCollection>> call, Response<List<ResponseCollection>> response) {

                List<ResponseCollection> post = response.body();
                collectionPageAdapter = new CollectionPageAdapter(CollectionViewer.this,post,initGlide(),onImageClick);
                recyclerView.setAdapter(collectionPageAdapter);

                progressBar.setVisibility(View.GONE);

//                Toast.makeText(CollectionViewer.this, "open", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ResponseCollection>> call, Throwable t) {

//                Toast.makeText(CollectionViewer.this, "fail", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void performPagaination(int page) {

        progressBar.setVisibility(View.VISIBLE);

        Call<List<ResponseCollection>> call = RetrofitClient.getInstance().getModel().getpost5(id,
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                String.valueOf(page),"30");

        call.enqueue(new Callback<List<ResponseCollection>>() {
            @Override
            public void onResponse(Call<List<ResponseCollection>> call, Response<List<ResponseCollection>> response) {

                List<ResponseCollection> responseCollections = response.body();
                collectionPageAdapter.addImages(responseCollections);

                progressBar.setVisibility(View.GONE);

//                Toast.makeText(CollectionViewer.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ResponseCollection>> call, Throwable t) {

//                Toast.makeText(CollectionViewer.this, "fail", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(CollectionViewer.this).setDefaultRequestOptions(options);

    }

}