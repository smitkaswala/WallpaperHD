package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList.CollectionAdapter;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList.CollectionResponse;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphFloatingActionButton;

public class CollectionView extends AppCompatActivity {

    OnImageClick onImageClick;
    NeumorphFloatingActionButton neumorphFloatingActionButton,neumorphFloatingActionButton2;
//    AppCompatImageView appCompatImageView;
    RecyclerView recyclerView,recyclerView2;
    GridLayoutManager gridLayoutManager;
    CollectionAdapter collectionAdapter;
    List<CollectionText>  collectionTexts = new ArrayList<>();
    Boolean isScrolling = false;
    ProgressBar progressBar;
    AutoCompleteTextView collection_1;
    CollectionResponse collectionResponse;
    public int page = 1;
    String search = "office";
    private int page1 = 1;
    private int page2 = 1;

    private AdView adView;

    private Handler handler;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);

        neumorphFloatingActionButton = findViewById(R.id.back_arrow);
//        neumorphFloatingActionButton2 = findViewById(R.id.popup_bar);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView = findViewById(R.id.rec_2);

        collection_1 = findViewById(R.id.collection_1);

        gridLayoutManager = new GridLayoutManager(CollectionView.this, 1);

        neumorphFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getCollection2();

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

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

                if (isScrolling && (currentItems + scrollOutItems == (totalItems))) {

                    isScrolling = false;
                    page1++;
                    Log.d("TAG+++++++++", "onScrolled: "+page1);

                    PerPagination(page1);
//
                }

            }

        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    isScrolling = true;
//                }
//
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int currentItems = gridLayoutManager.getChildCount();
//                int totalItems = gridLayoutManager.getItemCount();
//                int scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();
//
//                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
//
//                    isScrolling = false;
//                    page2++;
//                    Log.d("TAG+++++++++", "onScrolled: "+page2);
//
//                    PerPagination2(page2);
//
//
////
//                }
//
//            }
//
//        });

//        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    isScrolling = true;
//                }
//
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int currentItems = gridLayoutManager.getChildCount();
//                int totalItems = gridLayoutManager.getItemCount();
//                int scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();
//
//                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
//
//                    isScrolling = false;
//                    page++;
//                    Log.d("TAG+++++++++", "onScrolled: " + page);
//
//                    performPagaination(page);
//
//                }
//
//            }
//
//        });

//        neumorphFloatingActionButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                PopupMenu popupMenu = new PopupMenu(CollectionView.this,neumorphFloatingActionButton2);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_2,popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//
//                        switch (item.getItemId()){
//                            case R.id.privacy_policy:
//
//                                break;
//
//                            case R.id.rate_us:
//
//                                break;
//
//                            case R.id.share_app:
//
//                                break;
//
//                        }
//
//                        Toast.makeText(CollectionView.this, "" +item.getTitle(), Toast.LENGTH_SHORT).show();
//
//                        return true;
//
//                    }
//                });
//
//                popupMenu.show();
//
//            }
//        });

        onImageClick = new OnImageClick() {
            @Override
            public void setOnImageClick(String id,String small,String full,String regular) {
                Log.d("TAG---------", "setOnImageClick: "+ small);

                Intent intent = new Intent(CollectionView.this,FullImageView.class);
                intent.putExtra("small",small);
                intent.putExtra("full",full);
                intent.putExtra("id",id);
                intent.putExtra("regular",regular);
                startActivity(intent);

            }

        };

        collection_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() > 2){

                    handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                    handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,AUTO_COMPLETE_DELAY);

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                if (msg.what == TRIGGER_AUTO_COMPLETE){

                    if (!TextUtils.isEmpty(collection_1.getText())){

//                        getCollection(collection_1.getText().toString());
                        search = collection_1.getText().toString();
                        if (search.length() != 0 && search != null){
                            getCollection(search);
                        }else {
                            search  = "office";
                            getCollection(search);
                        }

                    }

                }

                return false;

            }

        });

//        appCompatImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = new Intent(CollectionView.this,FullImageView.class);
//                startActivity(intent);
//
//
//            }
//
//        });

    }

    private void getCollection2(){

        recyclerView.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",search,
                "1","30");

        call.enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                Log.d("TAG","/////////"+ response.code());

                CollectionResponse posts = response.body();

                if (posts.getResults() != null) {
                    
                    CollectionText collectionText = new CollectionText();

                    collectionAdapter = new CollectionAdapter(CollectionView.this, posts.getResults(), initGlide(), onImageClick , collectionTexts);
                    recyclerView.setAdapter(collectionAdapter);
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {

                Log.e("TAG", "onResponse:&&&&&&"+t.getMessage());

            }

        });

    }

    private void PerPagination(int page1){

        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",search,
                String.valueOf(page1),"30");

        call.enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                Log.d("TAG","/////////"+ response.code());

                CollectionResponse collectionResponse = response.body();
                if (collectionResponse.getResults() != null) {
                    collectionAdapter.addImages(collectionResponse.getResults());
                }

            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {

                Log.e("TAG", "onResponse:&&&&&&"+t.getMessage());

            }

        });

    }

    private void getCollection(String text){

        recyclerView.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",text,
                "1","30");

        call.enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                Log.d("TAG","/////////"+ response.code());

                CollectionResponse posts = response.body();
//                CollectionText collectionText = new CollectionText();
//                collectionText.setText1(posts.getResults().get().getTitle());

                if (posts.getResults() != null) {
//                    collectionText.setText2(posts.getResults().get().getTotalPhotos());
                    collectionAdapter = new CollectionAdapter(CollectionView.this, posts.getResults(), initGlide(), onImageClick,collectionTexts);
                    recyclerView.setAdapter(collectionAdapter);
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {

                Log.e("TAG", "onResponse:&&&&&&"+t.getMessage());

            }

        });

    }

    private void PerPagination2(int page2){

        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1","office",
                String.valueOf(page2),"24");

        call.enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                Log.d("TAG","/////////"+ response.code());

                CollectionResponse collectionResponse = response.body();
                collectionAdapter.addImages(collectionResponse.getResults());

            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {

                Log.e("TAG", "onResponse:&&&&&&"+t.getMessage());

            }

        });

    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(CollectionView.this).setDefaultRequestOptions(options);

    }

}

//    private void getCollection(String text){
//
//        recyclerView1.setLayoutManager(gridLayoutManager);
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
//                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",text,
//                "5","24");
//
//        call.enqueue(new Callback<CollectionResponse>() {
//            @Override
//            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
//                Log.d("TAG","/////////"+ response.code());
//
//                CollectionResponse posts = response.body();
//
////                List<User> post = response.body();
//
//                /*for (int i = 0; i < posts.size() ; i++) {
//
//                    Log.d("TAG", "onResponse:&&&&&&"+posts.get(i).getResults().listIterator().next().getCoverPhoto().getUrls().getFull());
//
//                    CollectionModel collectionModel = new CollectionModel();
//                    collectionModel.setIv_imageview(posts.get(i).getResults().listIterator().next().getCoverPhoto().getUrls().getThumb());
////                    collectionModel.setIv_textview(posts.get(i).getResults().listIterator().next().getCoverPhoto().getDescription());
//
//                }*/
//
//
//                collectionAdapter = new CollectionAdapter(CollectionView.this,posts.getResults(), initGlide(),onImageClick);
//                recyclerView1.setAdapter(collectionAdapter);
//                progressBar.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onFailure(Call<CollectionResponse> call, Throwable t) {
//
//                Log.e("TAG", "onResponse:&&&&&&"+t.getMessage());
//
//            }
//
//        });
//
//    }
//
//    private void performPagaination(int page) {
//        progressBar.setVisibility(View.VISIBLE);
//        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
//                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1","backdrops",
//                String.valueOf(page),"18");
//
//        call.enqueue(new Callback<CollectionResponse>() {
//            @Override
//            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
//                Log.d("TAG","/////////"+ response.code());
//
//                List<ResultsItem>  list= new ArrayList<>();
//
//                if (response.code() == 200) {
//
//
//                    CollectionResponse collectionResponseList = response.body();
//                    Log.d("TAG********", "onResponse: "+ response.body());
//
//                    for (int i = 0; i <collectionResponseList.getResults().size();i++){
//
//                        ResultsItem resultsItem = new ResultsItem();
//                        resultsItem.setCoverPhoto(collectionResponseList.getResults().get(i).getCoverPhoto());
//                        list.add(resultsItem);
//
//                    }
//
//                   collectionAdapter.addImages(list);
//
//                }
//
//                else {
//
//                }
//
//                progressBar.setVisibility(View.GONE);
//
//                /*for (int i = 0; i < posts.size() ; i++) {
//
//                    Log.d("TAG", "onResponse:&&&&&&"+posts.get(i).getResults().listIterator().next().getCoverPhoto().getUrls().getFull());
//
//                    CollectionModel collectionModel = new CollectionModel();
//                    collectionModel.setIv_imageview(posts.get(i).getResults().listIterator().next().getCoverPhoto().getUrls().getThumb());
////                    collectionModel.setIv_textview(posts.get(i).getResults().listIterator().next().getCoverPhoto().getDescription());
//
//                }*/
//
//            }
//            @Override
//            public void onFailure(Call<CollectionResponse> call, Throwable t) {
//
//                Log.e("TAG", "onResponse:&&&&&&"+t.getMessage());
//
//            }
//
//        });
//
//    }



