package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onesignal.OneSignal;
import com.squareup.picasso.BuildConfig;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList.CollectionAdapter;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList.CollectionResponse;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Latest.LatestAdapter;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Latest.ResponseModel;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular.PopularAdapter;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular.ResponseClass;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.UserDetail.ResponseUser;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.UserDetail.UserDetailAdapter;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphFloatingActionButton;
import soup.neumorphism.ShapeType;


public class HomeFragment extends Fragment {

    private FirebaseAnalytics firebaseAnalytics;

    OnImageClick onImageClick;
    NeumorphButton neumorphButton, neumorphButton1, neumorphButton2, neumorphButton3;
    NeumorphFloatingActionButton  edit_1, popupbar;
    ImageView imageView;
    ImageView imageView1;
    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    PrefManager prefManager;
    PopularAdapter popularAdapter;
    LatestAdapter latestAdapter;
    CollectionAdapter collectionAdapter;
    UserDetailAdapter userDetailAdapter;
    GridLayoutManager gridLayoutManager;
    Boolean isScrolling = false;
    private Handler handler;

    private static final int TRIGGER_AUTO_COMPLETE = 100;

    private static final long AUTO_COMPLETE_DELAY = 300;

    String search;
    String username;

//    int currentItems, totalItems, scrollOutItems;
    //    List list;

    ProgressBar progressBar;

    private int page = 1;
    private int page1 = 1;
    private int page2 = 1;
    private int page3 = 0;

    private int per_page = 20;

    private AdView adView;

    //Variable for pagination

    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemsCounts, previous_total = 0;
    private int view_threshold = 20;

    @SuppressLint("RestrictedApi")
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        prefManager = new PrefManager(getContext());

//        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
//
//        OneSignal.startInit(getContext())
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();

        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());

        OneSignal.startInit(getContext())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();




        neumorphButton = view.findViewById(R.id.collection);
        neumorphButton1 = view.findViewById(R.id.liveWallpaper);
        neumorphButton2 = view.findViewById(R.id.popular);

//        neumorphButton3 = view.findViewById(R.id.random);

        popupbar = view.findViewById(R.id.popup_bar);
        edit_1 = view.findViewById(R.id.search_view);

//        imageView = view.findViewById(R.id.image_1);

        imageView1 = view.findViewById(R.id.iv_imageview);
        recyclerView = view.findViewById(R.id.rec_1);
        recyclerView1 = view.findViewById(R.id.rec_1);
        recyclerView2 = view.findViewById(R.id.rec_1);
        recyclerView3 = view.findViewById(R.id.rec_1);
        progressBar = view.findViewById(R.id.progress_bar);





        gridLayoutManager = new GridLayoutManager(getContext(), 3);

        getUserDetail();

//        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        adView = view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                    page2++;
                    Log.d("TAG+++++++++", "onScrolled: " + page2++);

//                   getUserDetail(page2);
                    performPagination2(page2);

                }

            }

        });

        neumorphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                neumorphButton.setShapeType(ShapeType.PRESSED);
                neumorphButton2.setShapeType(ShapeType.FLAT);
                neumorphButton1.setShapeType(ShapeType.FLAT);

                Intent intent = new Intent(getActivity(), CollectionView.class);
                startActivity(intent);

            }

        });

//        recyclerView3.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
//                    page3++;
//                    Log.d("TAG+++++++++", "onScrolled: " + page3);
//
//                    getCollection(search,page3);
//
//                }
//
//            }
//
//        });

        neumorphButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                neumorphButton1.setShapeType(ShapeType.PRESSED);
                neumorphButton.setShapeType(ShapeType.FLAT);
//                neumorphButton3.setShapeType(ShapeType.FLAT);
                neumorphButton2.setShapeType(ShapeType.FLAT);

                getLatest(page1);
                recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                            page1++;
                            Log.d("TAG+++++++++", "onScrolled: "+page1);

                            performPagination1(page1);
//                            getLatest(page1);

                        }

                    }

                });

            }

        });

        neumorphButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                neumorphButton2.setShapeType(ShapeType.PRESSED);
                neumorphButton1.setShapeType(ShapeType.FLAT);
                neumorphButton.setShapeType(ShapeType.FLAT);
//                neumorphButton3.setShapeType(ShapeType.FLAT);
                getPopular();
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
                            Log.d("TAG+++++++++", "onScrolled: "+page);

                            performPagaination(page);


//
                        }

                    }

                });
//                performPagaination(page);
            }

        });

//        neumorphButton3.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                neumorphButton3.setShapeType(ShapeType.PRESSED);
//                neumorphButton2.setShapeType(ShapeType.FLAT);
//                neumorphButton1.setShapeType(ShapeType.FLAT);
//                neumorphButton.setShapeType(ShapeType.FLAT);
//            }
//
//        });

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(),FullImageView.class);
//                startActivity(intent);
//            }
//        });

        popupbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(getActivity(), popupbar);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_2, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.privacy_policy:

                                startActivity (new Intent(getContext(), PrivacyPolicyActivity.class));
                                return true;
                            default:
                                return HomeFragment.super.onOptionsItemSelected(item);


                            case R.id.rate_us:

                                try {
                                    Uri uri = Uri.parse("market://details?id=" + getTag() + "");
                                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(goMarket);
                                } catch (ActivityNotFoundException e) {
                                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getTag() + "");
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
//                        Toast.makeText(getActivity(), "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;

                    }

                });

                popupMenu.show();

            }

        });

        onImageClick = new OnImageClick() {
            @Override
            public void setOnImageClick(String id,String small ,String full,String regular) {

                Intent intent = new Intent(getActivity(), FullImageView.class);

                intent.putExtra("id",id);
                intent.putExtra("small",small);
                intent.putExtra("full",full);
                intent.putExtra("regular",regular);
                startActivity(intent);

            }

        };

        edit_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

            }
        });

//        edit_1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                if(s.length() > 2){
//
//                    handler.removeMessages(TRIGGER_AUTO_COMPLETE);
//                    handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,AUTO_COMPLETE_DELAY);
//
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

//        handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//
//                if (msg.what == TRIGGER_AUTO_COMPLETE){
//
//                    if (!TextUtils.isEmpty(edit_1.getText())){
//
//                       getCollection(edit_1.getText().toString(),page3++);
//                       search = edit_1.getText().toString();
//
//                    }
//
//                }
//
//                return false;
//
//            }
//
//        });

//        MenuBuilder menuBuilder =new MenuBuilder(getActivity());
//        MenuInflater inflater2 = new MenuInflater(getActivity());
//        inflater2.inflate(R.menu.popup_menu_2, menuBuilder);
//        MenuPopupHelper optionsMenu = new MenuPopupHelper(getActivity(), menuBuilder, view);
//        optionsMenu.setForceShowIcon(true);
//
//        // Set Item Click Listener
//        menuBuilder.setCallback(new MenuBuilder.Callback() {
//            @Override
//            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.privacy_policy: // Handle option1 Click
//                        return true;
//                    case R.id.rate_us: // Handle option2 Click
//                        return true;
//                    case R.id.share_app: // Handle option2 Click
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//
//            @Override
//            public void onMenuModeChange(MenuBuilder menu) {}
//        });
//
//
//        // Display the menu
//        optionsMenu.show();

        return view;

    }


    @Override
    public void onResume() {
        neumorphButton.setShapeType(ShapeType.FLAT);
        super.onResume();

    }



    private void getPopular() {

        recyclerView.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        Call<List<ResponseClass>> call = RetrofitClient.getInstance().getModel().getpost(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                "1", "popular", "18");

        call.enqueue(new Callback<List<ResponseClass>>() {
            @Override
            public void onResponse(Call<List<ResponseClass>> call, Response<List<ResponseClass>> response) {

                Log.d("TAG", "On Response:=====" + response.toString());

                List<ResponseClass> posts = response.body();
                popularAdapter = new PopularAdapter(getContext(), posts, initGlide(), onImageClick);
                recyclerView.setAdapter(popularAdapter);
                progressBar.setVisibility(View.GONE);


                for (int i = 0; i < posts.size(); i++) {
                    Log.d("TAG", "onResponse: ------" + posts.get(i).urls.thumb);
//
//                    list = new ArrayList(Arrays.asList(posts.get(i).urls.full));
//
//                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                        @Override
//                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                            super.onScrollStateChanged(recyclerView, newState);
//                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
//                            {
//                                isScrolling = true;
//                            }
//                        }
//
//                        @Override
//                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                            super.onScrolled(recyclerView, dx, dy);
//                            currentItems = linearLayoutManager.getChildCount();
//                            totalItems = linearLayoutManager.getItemCount();
//                            scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();
//
//                            if (isScrolling && (currentItems + scrollOutItems == totalItems)){
//
//                                isScrolling = false;
//                                fetchData();
//                            }
//                        }
//                    });
                }
                
            }

            @Override
            public void onFailure(Call<List<ResponseClass>> call, Throwable t) {

                Log.e("TAG", "On Response:+++" + t.getMessage());

            }

        });
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                visibleItemCount = linearLayoutManager.getChildCount();
//                totalItemsCounts = linearLayoutManager.getItemCount();
//                pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
//
//                if (dy>0)
//                {
//                    if (isLoading)
//                    {
//                        if (totalItemsCounts>previous_total)
//                        {
//                            isLoading = false;
//                            previous_total = totalItemsCounts;
//                        }
//
//                    }
//                    if (!isLoading&&(totalItemsCounts-visibleItemCount)<=(pastVisibleItems+view_threshold))
//                    {
//                        page++;
//                        performPagaination(page);
//                        isLoading = true;
//                    }
//                }
//            }
//        });
    }

    private void getLatest(int page1)  {

        recyclerView1.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.VISIBLE);
        
        Call<List<ResponseModel>> call = RetrofitClient.getInstance().getModel().getpost1(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                String.valueOf(page1), "latest", "18");

        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {

                Log.d("TAG", "On Response:***" + response.toString());



//                for (int i = 0; i <posts.size() ; i++) {
//
//
//                LatestModel latestModel = new LatestModel();
//                latestModel.setImage2(posts.get(i).getUrls().getFull());
//
//                }

//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                List<ResponseModel> posts = response.body();
                latestAdapter = new LatestAdapter(getContext(), posts, initGlide(), onImageClick , getActivity());
                recyclerView1.setAdapter(latestAdapter);

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {
                Log.e("TAG", "On Response:***" + call.toString());

            }
        });
    }

    private void getUserDetail() {

        recyclerView2.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        Call<List<ResponseUser>> call = RetrofitClient.getInstance().getModel().getpost2(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                "2", "oldest", "18");

        call.enqueue(new Callback<List<ResponseUser>>() {
            @Override
            public void onResponse(Call<List<ResponseUser>> call, Response<List<ResponseUser>> response) {

                Log.d("TAG", "On Response:+++" + response.toString());

//                for (int i = 0; i <posts.size() ; i++) {
//
//                    UserDetailModel userDetailModel = new  UserDetailModel();
//                    userDetailModel.setImage_3(posts.get(i).getUrls().getFull());
//
//                }
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

                List<ResponseUser> posts = response.body();
                userDetailAdapter = new UserDetailAdapter(getContext(), posts, initGlide(), onImageClick);
                recyclerView2.setAdapter(userDetailAdapter);

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<ResponseUser>> call, Throwable t) {

            }

        });

    }

    private void performPagaination(int page) {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<ResponseClass>> call = RetrofitClient.getInstance().getModel().getpost(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                String.valueOf(page), "popular","18" );


        call.enqueue(new Callback<List<ResponseClass>>() {
            @Override
            public void onResponse(Call<List<ResponseClass>> call, Response<List<ResponseClass>> response) {

                if (response.code() == 200) {

                    List<ResponseClass> responseClasses = response.body();
                    Log.d("TAG********", "onResponse: "+ response.body());
                    popularAdapter.addImages(responseClasses);

                } else {

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<ResponseClass>> call, Throwable t) {

                Log.e("TAG-------", "On Response:+-+-+-+" + call.toString());

            }

        });

    }

//    private void fetchData(){
//        progressBar.setVisibility(View.VISIBLE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0 ; i < 5 ; i++){
//                    list.add(Math.floor(Math.random()*100) + "");
//                    popularAdapter.notifyDataSetChanged();
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//        },5000);
//    }

    private void performPagination1(int page1){
        progressBar.setVisibility(View.VISIBLE);
        Call<List<ResponseModel>> call = RetrofitClient.getInstance().getModel().getpost1(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                String.valueOf(page1), "latest", "18");

        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {

                Log.d("TAG", "On Response:***" + response.toString());


//                for (int i = 0; i <posts.size() ; i++) {
//
//
//                LatestModel latestModel = new LatestModel();
//                latestModel.setImage2(posts.get(i).getUrls().getFull());
//
//                }

//

                if (response.code() == 200){

                    List<ResponseModel> responseModels = response.body();
                   latestAdapter.addImages1(responseModels);

                }else {

                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {
                Log.e("TAG", "On Response:***" + call.toString());

            }

        });

    }

    private void performPagination2(int page2){

        progressBar.setVisibility(View.VISIBLE);
        Call<List<ResponseUser>> call = RetrofitClient.getInstance().getModel().getpost2(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1",
                String.valueOf(page2), "oldest", "18");


        call.enqueue(new Callback<List<ResponseUser>>() {
            @Override
            public void onResponse(Call<List<ResponseUser>> call, Response<List<ResponseUser>> response) {

                Log.d("TAG", "On Response:+++" + response.toString());


                if (response.code() == 200){

                    List<ResponseUser> responseUsers = response.body();
                    userDetailAdapter.addImages(responseUsers);


                }

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<ResponseUser>> call, Throwable t) {

            }

        });

    }

    private void performPagaination3(int page3) {

        progressBar.setVisibility(View.VISIBLE);
        Call<CollectionResponse> call = RetrofitClient.getInstance().getModel().getpost3(
                "a82f6bf78409bb9e7f0921a410d9d693d06b98a2d5df9a9cdc8295ab3cb261c1","backdrops",
                String.valueOf(page3),"24");

        call.enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                Log.d("TAG","/////////"+ response.code());


                if (response.code() == 200) {


                    CollectionResponse collectionResponseList = response.body();
                    Log.d("TAG********", "onResponse: "+ response.body());
                    collectionAdapter.addImages(collectionResponseList.getResults());

                }

                else {

                }

                progressBar.setVisibility(View.GONE);

                /*for (int i = 0; i < posts.size() ; i++) {

                    Log.d("TAG", "onResponse:&&&&&&"+posts.get(i).getResults().listIterator().next().getCoverPhoto().getUrls().getFull());

                    CollectionModel collectionModel = new CollectionModel();
                    collectionModel.setIv_imageview(posts.get(i).getResults().listIterator().next().getCoverPhoto().getUrls().getThumb());
//                    collectionModel.setIv_textview(posts.get(i).getResults().listIterator().next().getCoverPhoto().getDescription());

                }*/

            }
            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {

                Log.e("TAG", "onResponse:&&&&&&"+t.getMessage());

            }

        });

    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(getContext()).setDefaultRequestOptions(options);

    }



}