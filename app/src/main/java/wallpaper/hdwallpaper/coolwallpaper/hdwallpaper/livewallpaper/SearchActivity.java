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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList.CollectionAdapter;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.SearchWallpaper.ResponseWallpaper;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.SearchWallpaper.SearchWallpaperAdapter;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphFloatingActionButton;

public class SearchActivity extends AppCompatActivity {

    OnImageClick onImageClick;
    AutoCompleteTextView edit_1;
    CollectionAdapter collectionAdapter;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView3;
    SearchWallpaperAdapter searchWallpaperAdapter;
    NeumorphFloatingActionButton back_arrow;
    Boolean isScrolling = false;
    private Handler handler;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    String search = "nature";
    private int page3 = 1;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edit_1 = findViewById(R.id.edit_1);
        recyclerView3 = findViewById(R.id.rec_1);
        progressBar = findViewById(R.id.progress_bar);
        back_arrow = findViewById(R.id.back_arrow);

        gridLayoutManager = new GridLayoutManager(SearchActivity.this, 3);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        recyclerView3.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                    page3++;
                    Log.d("TAG+++++++++", "onScrolled: " + page3);

//                    getCollection(search, page3);
                    performPagaination4(page3);

                }

            }

        });

        SearchWallpaper(search);
//        SearchWallpaper(search);

        onImageClick = new OnImageClick() {
            @Override
            public void setOnImageClick(String id, String small, String full ,String regular) {

                Intent intent = new Intent(SearchActivity.this, FullImageView.class);

                intent.putExtra("id", id);
                intent.putExtra("small", small);
                intent.putExtra("full", full);
                intent.putExtra("regular", regular);
                startActivity(intent);

            }

        };

        edit_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 2) {

                    handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                    handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE, AUTO_COMPLETE_DELAY);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                if (msg.what == TRIGGER_AUTO_COMPLETE) {

                    if (!TextUtils.isEmpty(edit_1.getText())) {

//                        getCollection(edit_1.getText().toString(), page3++);
                        SearchWallpaper(edit_1.getText().toString());
                        search = edit_1.getText().toString();

                    }

                }

                return false;

            }

        });

    }

    private void SearchWallpaper(String text) {

        recyclerView3.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        Call<ResponseWallpaper> call = RetrofitClient.getInstance().getModel().getpost4(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                text, "1", "18");

        call.enqueue(new Callback<ResponseWallpaper>() {
            @Override
            public void onResponse(Call<ResponseWallpaper> call, Response<ResponseWallpaper> response) {

                Log.d("TAG", "Responcelite+-+-+-" + response.toString());

                ResponseWallpaper post = response.body();

                searchWallpaperAdapter = new SearchWallpaperAdapter(SearchActivity.this, post.getResults(), initGlide(), onImageClick);
                recyclerView3.setAdapter(searchWallpaperAdapter);

                progressBar.setVisibility(View.GONE);
//                Toast.makeText(SearchActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseWallpaper> call, Throwable t) {
                Log.d("TAG", "+-+-+-+" + t.toString());
//                Toast.makeText(SearchActivity.this, "onFail", Toast.LENGTH_SHORT).show();
            }
        });

    }
//    private void SearchWallpaper(String text) {
//
//        recyclerView3.setLayoutManager(gridLayoutManager);
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        Call<List<ResponseWallpaper>> call = RetrofitClient.getInstance().getModel().getpost4(
//                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
//                text, "1", "15");
//
//        call.enqueue(new Callback<List<ResponseWallpaper>>() {
//            @Override
//            public void onResponse(Call<List<ResponseWallpaper>> call, Response<List<ResponseWallpaper>> response) {
//
//                Log.d("TAG", "Responcelite" + response.toString());
//
//                List<ResponseWallpaper> post = response.body();
//                searchWallpaperAdapter = new SearchWallpaperAdapter(SearchActivity.this, post, initGlide(), onImageClick);
//                recyclerView3.setAdapter(searchWallpaperAdapter);
//
//                progressBar.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<ResponseWallpaper>> call, Throwable t) {
//
//            }
//        });
//
//    }

    private void performPagaination4(int page3) {

        progressBar.setVisibility(View.VISIBLE);


        Call<ResponseWallpaper> call = RetrofitClient.getInstance().getModel().getpost4(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                search, String.valueOf(page3), "18");

        call.enqueue(new Callback<ResponseWallpaper>() {
            @Override
            public void onResponse(Call<ResponseWallpaper> call, Response<ResponseWallpaper> response) {

                Log.d("TAG", "Responcelite+-+-+-" + response.toString());

//                searchWallpaperAdapter = new SearchWallpaperAdapter(SearchActivity.this, post.getResults(), initGlide(), onImageClick);
//                recyclerView3.setAdapter(searchWallpaperAdapter);
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(SearchActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();

                ResponseWallpaper responseWallpaper = response.body();
                searchWallpaperAdapter.addImages(responseWallpaper.getResults());

            }

            @Override
            public void onFailure(Call<ResponseWallpaper> call, Throwable t) {
                Log.d("TAG", "+-+-+-+" + t.toString());
                Toast.makeText(SearchActivity.this, "onFail", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void getCollection(String text, int page3) {
//
//        recyclerView3.setLayoutManager(gridLayoutManager);
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
//                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1", text,
//                String.valueOf(page3), "24");
//
//        call.enqueue(new Callback<CollectionResponse>() {
//            @Override
//            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
//                Log.d("TAG", "/////////" + response.code());
//
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
////
////                if (page3 == 1) {
////                    collectionAdapter = new CollectionAdapter(SearchActivity.this, posts.getResults(), initGlide(), onImageClick);
////                    recyclerView3.setAdapter(collectionAdapter);
////                }else {
////                    Log.d("TAG********", "onResponse: "+ response.body());
////                    collectionAdapter.addImages(posts.getResults());
////                }
//
//                CollectionResponse posts = response.body();
//                collectionAdapter = new CollectionAdapter(SearchActivity.this, posts.getResults(), initGlide(), onImageClick);
//                recyclerView3.setAdapter(collectionAdapter);
//                Log.d("TAG********", "onResponse: " + response.body());
//
//                progressBar.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onFailure(Call<CollectionResponse> call, Throwable t) {
//
//                Log.e("TAG", "onResponse:&&&&&&" + t.getMessage());
//
//            }
//
//        });
//
//    }
//
//    private void performPagaination3(int page3) {
//
//        progressBar.setVisibility(View.VISIBLE);
//        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
//                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1", "backdrops",
//                String.valueOf(page3), "24");
//
//        call.enqueue(new Callback<CollectionResponse>() {
//            @Override
//            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
//                Log.d("TAG", "/////////" + response.code());
//
//
//                if (response.code() == 200) {
//
//
//                    CollectionResponse collectionResponseList = response.body();
//                    Log.d("TAG********", "onResponse: " + response.body());
//                    collectionAdapter.addImages(collectionResponseList.getResults());
//
//                } else {
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
//
//            @Override
//            public void onFailure(Call<CollectionResponse> call, Throwable t) {
//
//                Log.e("TAG", "onResponse:&&&&&&" + t.getMessage());
//
//            }
//
//        });
//
//    }


    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(SearchActivity.this).setDefaultRequestOptions(options);

    }

}