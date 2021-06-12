package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;


import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Latest.LatestAdapter;


import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.interfaces.OnImageClick;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphFloatingActionButton;

import static android.os.FileUtils.copy;

public class FullImageView extends AppCompatActivity {

    private static final int IO_BUFFER_SIZE = 1;
    private BottomNavigationView bottomNavigationView2;

    NeumorphFloatingActionButton imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7;
    ImageView iv_image, iv_image1, iv_image2, iv_image3, iv_image4, iv_image5, iv_image6, iv_download;
    Bitmap bitmap = null;

    LinearLayout linear, linear1, linear2;
    public final Context context = this;
    ProgressDialog progressDialog;
    NeumorphCardView neumorphCardView;

    OnImageClick onImageClick;
    LatestAdapter latestAdapter;
    OutputStream outputStream;

    String small;
    String regular;
    String full;
    String id;

    Bitmap image, image2;
    SeekBar seekBar;
    NeumorphCardView cardView;

    LottieAnimationView progressBar1, progressBar2, progressBar3;
    int radius = 100;

    private List<FavoriteItem> favoriteItems;
    FavoriteAdapter favoriteAdapter;
    SqliteDatabase sqliteDatabase;
    SqliteDatabase2 sqliteDatabase2;

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        small = getIntent().getStringExtra("small");
        full = getIntent().getStringExtra("full");
        id = getIntent().getStringExtra("id");
        regular = getIntent().getStringExtra("regular");


        progressDialog = new ProgressDialog(context);

        imageView = findViewById(R.id.back_arrow);
        iv_image1 = findViewById(R.id.iv_hue);
        imageView2 = findViewById(R.id.set_image);
        iv_image2 = findViewById(R.id.iv_contrast);
        imageView3 = findViewById(R.id.iv_edit);
        iv_image3 = findViewById(R.id.iv_brightness);
        imageView4 = findViewById(R.id.share_app);
        iv_image4 = findViewById(R.id.iv_saturation);
        imageView5 = findViewById(R.id.goto_home);
        iv_image5 = findViewById(R.id.iv_fade);
        imageView6 = findViewById(R.id.iv_download);
        iv_image6 = findViewById(R.id.iv_structure);
        imageView7 = findViewById(R.id.favorite_btn);
        neumorphCardView = findViewById(R.id.editing_container1);
        cardView = findViewById(R.id.setwallpaper_container);


        linear = findViewById(R.id.set_1);
        linear1 = findViewById(R.id.set_2);
        linear2 = findViewById(R.id.set_3);

        progressBar1 = findViewById(R.id.progress1);
        progressBar2 = findViewById(R.id.progress2);
        progressBar3 = findViewById(R.id.progress3);
//        progressBar4 = findViewById(R.id.progress);

        iv_image = findViewById(R.id.iv_image);
        iv_download = findViewById(R.id.iv_image);

        seekBar = findViewById(R.id.seekbar);

        sqliteDatabase = new SqliteDatabase(this);
        sqliteDatabase2 = new SqliteDatabase2(this);


        if (sqliteDatabase.checkIfUserExit(id)) {
            imageView7.setImageResource(R.drawable.ic_favorite2);
        } else {
            imageView7.setImageResource(R.drawable.ic_favorite);
        }

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    //Your code goes here

                    try {

                        URL url = new URL(full);
                        image = BitmapFactory.decodeStream(url.openConnection().getInputStream());

//                        wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_LOCK);
//                        BitmapDrawable drawable = (BitmapDrawable) iv_image.getDrawable();
//                        Bitmap bitmap = drawable.getBitmap();
//                        iv_image.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        System.out.println(e);
//                        Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Glide.with(this)
                .load(full)
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(Glide.with(this)
                        .load(regular)
                        .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()
                        .thumbnail(Glide.with(this)
                                .load(small)
                                .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()))
                .centerCrop()
                .into(iv_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView5.setImageResource(R.drawable.ic_home);
                imageView6.setImageResource(R.drawable.ic_download);
                imageView2.setImageResource(R.drawable.ic_group_crop2);
                imageView3.setImageResource(R.drawable.ic_color_lens);

//                AlertDialog.Builder builder = new AlertDialog.Builder(FullImageView.this);
//                View view = getLayoutInflater().inflate(R.layout.fragment_crop, null);
//
//                LinearLayout homescreen = view.findViewById(R.id.set_1);
//                LinearLayout lockscreen = view.findViewById(R.id.set_2);
//                LinearLayout bothscreen = view.findViewById(R.id.set_3);
//
//                progressBar1 =  view.findViewById(R.id.progress1);
//                progressBar2 =  view.findViewById(R.id.progress2);
//                progressBar3 =  view.findViewById(R.id.progress3);
//
//
//                builder.setView(view);
//
//                AlertDialog dialog = builder.create();
//
//                if (dialog.getWindow() != null)
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//                dialog.show();
//
////                homescreen.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////                        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(url));
////
//////                        getBitmapFromURL(url);
////
//////                        File bitmapFile = new File(getIntent().getStringExtra(url));
//////                        Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(bitmapFile));
////
////                        try {
////                            URL ur = new URL(small);
////                            URL urr = new URL(full);
////                            images = BitmapFactory.decodeStream(ur.openConnection().getInputStream());
////                            images = BitmapFactory.decodeStream(urr.openConnection().getInputStream());
////                        } catch(IOException e) {
////                            System.out.println(e);
////                        }
////
////                        WallpaperManager wallpaperManage = WallpaperManager.getInstance(getApplicationContext());
////
////                        try {
////
////                            wallpaperManage.setBitmap(images);
////
////                        }catch (IOException e){
////
////                        }
////                        dialog.dismiss();
////                    }
////
////
////                });
//
//
//                homescreen.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
////                        progressBar1.setVisibility(View.VISIBLE);
//
//                        onWallpaperChanged(image, true, false);
//
//                        dialog.dismiss();
//                    }
//                });
//
//                lockscreen.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
////                        onWallpaperChanged(image, false, true);
//
//
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
//                            try {
//                                wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_LOCK);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//
//                        dialog.dismiss();
//
//                    }
//                });
//
//                bothscreen.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//
//                        onWallpaperChanged(image, true, true);
////
//                        dialog.dismiss();
//
//                    }
//                });
//                if (homescreen.callOnClick()){
//                    dialog.dismiss();
//                 }

                NeumorphCardView cardView1 = findViewById(R.id.editing_container);

                if (cardView.getVisibility() == View.VISIBLE) {
                    cardView.setVisibility(View.GONE);

                } else {
                    cardView.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.GONE);

                }

                linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        progressBar1.setVisibility(View.VISIBLE);

//                        showSpinner1(true);
//                        if (progressBar1.getVisibility() != View.VISIBLE){
//
//
//                        }else {
//                            progressBar1.setVisibility(View.GONE);
//                        }
                        Bitmap bitmap = viewToBitmap(iv_image,iv_image.getWidth(),iv_image.getHeight());

                        onWallpaperChanged(bitmap, true, false);


                    }
                });

                linear1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        progressBar2.setVisibility(View.VISIBLE);

                        Bitmap bitmap = viewToBitmap(iv_image,iv_image.getWidth(),iv_image.getHeight());

                        onWallpaperChanged(bitmap, false, true);


                    }
                });

                linear2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        progressBar3.setVisibility(View.VISIBLE);

                        Bitmap bitmap = viewToBitmap(iv_image,iv_image.getWidth(),iv_image.getHeight());

                        onWallpaperChanged(bitmap, true, true);


                    }
                });

            }

        });

        neumorphCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NeumorphCardView cardView = findViewById(R.id.editing_container);
                NeumorphCardView cardView1 = findViewById(R.id.setwallpaper_container);

                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                AlertDialog.Builder builder1 = new AlertDialog.Builder(FullImageView.this);
//                View view = getLayoutInflater().inflate(R.layout.fragment_color,null);
////
//////                LinearLayout homescreen = view.findViewById(R.id.set_1);
//////                LinearLayout lockscreen = view.findViewById(R.id.set_2);
//////                LinearLayout bothscreen = view.findViewById(R.id.set_3);
////
//                builder1.setView(view);
//                AlertDialog dialog1 = builder1.create();
//
//                if(dialog1.getWindow() != null)
//                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//                dialog1.show();
                NeumorphCardView cardView = findViewById(R.id.editing_container);
                NeumorphCardView cardView1 = findViewById(R.id.setwallpaper_container);
                if (cardView.getVisibility() == View.VISIBLE) {
                    cardView.setVisibility(View.GONE);

                } else {
                    cardView.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.GONE);
                }
//                if (iv_image.getVisibility() == View.VISIBLE){
//                    cardView.setVisibility(View.GONE);
//                }else {
//                    cardView.setVisibility(View.VISIBLE);
//                }
                iv_image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iv_image1.setImageResource(R.drawable.ic_dots1);
                        iv_image2.setImageResource(R.drawable.ic_sun);
                        iv_image3.setImageResource(R.drawable.ic_half_brite);
                        iv_image4.setImageResource(R.drawable.ic_circul);
                        iv_image5.setImageResource(R.drawable.ic_lite);
                        iv_image6.setImageResource(R.drawable.ic_lightmode);

                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                ColorFilter colorFilter = ColorFilterGenerator.adjustColor(0, progress, 0, 0, 0, 0);
                                iv_image.setColorFilter(colorFilter);

                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }

                        });


                    }

                });

                iv_image2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iv_image1.setImageResource(R.drawable.ic_dots);
                        iv_image2.setImageResource(R.drawable.ic_sun1);
                        iv_image3.setImageResource(R.drawable.ic_half_brite);
                        iv_image4.setImageResource(R.drawable.ic_circul);
                        iv_image5.setImageResource(R.drawable.ic_lite);
                        iv_image6.setImageResource(R.drawable.ic_lightmode);

                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                ColorFilter colorFilter = ColorFilterGenerator.adjustColor(progress, 0, 0, 0, 0, 0);
                                iv_download.setColorFilter(colorFilter);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }

                        });

                    }

                });

                iv_image3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iv_image1.setImageResource(R.drawable.ic_dots);
                        iv_image2.setImageResource(R.drawable.ic_sun);
                        iv_image3.setImageResource(R.drawable.ic_half_brite1);
                        iv_image4.setImageResource(R.drawable.ic_circul);
                        iv_image5.setImageResource(R.drawable.ic_lite);
                        iv_image6.setImageResource(R.drawable.ic_lightmode);

                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                ColorFilter colorFilter = ColorFilterGenerator.adjustColor(0, 0, 0, 0, 0, progress);
                                iv_download.setColorFilter(colorFilter);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }

                        });

                    }

                });

                iv_image4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iv_image1.setImageResource(R.drawable.ic_dots);
                        iv_image2.setImageResource(R.drawable.ic_sun);
                        iv_image3.setImageResource(R.drawable.ic_half_brite);
                        iv_image4.setImageResource(R.drawable.ic_circul1);
                        iv_image5.setImageResource(R.drawable.ic_lite);
                        iv_image6.setImageResource(R.drawable.ic_lightmode);

                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                ColorFilter colorFilter = ColorFilterGenerator.adjustColor(0, 0, 0, 0, progress, 0);
                                iv_download.setColorFilter(colorFilter);

//                                Bitmap blurred = blurRenderScript(FullImageView.this, image, 25);
//                                //second parametre is radius
//                                iv_image.setImageBitmap(blurred);

//                                iv_image.setImageBitmap(fastblur(FullImageView.this,image,progress,true));

                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }

                        });

                    }

                });

                iv_image5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iv_image1.setImageResource(R.drawable.ic_dots);
                        iv_image2.setImageResource(R.drawable.ic_sun);
                        iv_image3.setImageResource(R.drawable.ic_half_brite);
                        iv_image4.setImageResource(R.drawable.ic_circul);
                        iv_image5.setImageResource(R.drawable.ic_lite1);
                        iv_image6.setImageResource(R.drawable.ic_lightmode);

                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                ColorFilter colorFilter = ColorFilterGenerator.adjustColor(0, 0, progress, 0, 0, 0);
                                iv_download.setColorFilter(colorFilter);

                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }

                        });

                    }

                });

                iv_image6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iv_image1.setImageResource(R.drawable.ic_dots);
                        iv_image2.setImageResource(R.drawable.ic_sun);
                        iv_image3.setImageResource(R.drawable.ic_half_brite);
                        iv_image4.setImageResource(R.drawable.ic_circul);
                        iv_image5.setImageResource(R.drawable.ic_lite);
                        iv_image6.setImageResource(R.drawable.ic_lightmode1);

                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                ColorFilter colorFilter = ColorFilterGenerator.adjustColor(0, 0, 0, progress, 0, 0);
                                iv_download.setColorFilter(colorFilter);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }

                        });

                    }

                });

                imageView5.setImageResource(R.drawable.ic_home);
                imageView2.setImageResource(R.drawable.ic_group_crop);
                imageView6.setImageResource(R.drawable.ic_download);
                imageView3.setImageResource(R.drawable.ic_color_lens1);
//                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                        FullImageView.this,R.style.Theme_Design_BottomSheetDialog);
//
//                View view = LayoutInflater.from(getApplicationContext())
//                        .inflate(
//                                R.layout.fragment_color,(FrameLayout)findViewById(R.id.frame)
//                        );
//              bottomSheetDialog.setContentView(view);
//              bottomSheetDialog.show();
//                Intent intent = new Intent(FullImageView.this, EditingView.class);
//                startActivity(intent);
            }

        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(FullImageView.this, imageView4);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_2, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.privacy_policy:

                                startActivity(new Intent(FullImageView.this, PrivacyPolicyActivity.class));
                                return true;
                            default:
                                return FullImageView.super.onOptionsItemSelected(item);

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

//                        Toast.makeText(FullImageView.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });


                popupMenu.show();
            }

        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView5.setImageResource(R.drawable.ic_home2);
                imageView6.setImageResource(R.drawable.ic_download);
                imageView2.setImageResource(R.drawable.ic_group_crop);
                imageView3.setImageResource(R.drawable.ic_color_lens);

                Intent intent = new Intent(FullImageView.this, MainActivity2.class);
                startActivity(intent);

            }

        });

        imageView7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                imageView5.setImageResource(R.drawable.ic_home);
                imageView2.setImageResource(R.drawable.ic_group_crop);
                imageView6.setImageResource(R.drawable.ic_download);
                imageView3.setImageResource(R.drawable.ic_color_lens);

                if (imageView7.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_favorite).getConstantState()) {
                    imageView7.setImageResource(R.drawable.ic_favorite2);
                    sqliteDatabase.insertInfoTheDatabase(id, full, small);
                    //remove

                } else {
                    imageView7.setImageResource(R.drawable.ic_favorite);
                    //like
                    sqliteDatabase.deletedataa(id);

                }
            }
        });

        int type = 0;

        if (type == 0) {
            imageView6.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    imageView5.setImageResource(R.drawable.ic_home);
                    imageView2.setImageResource(R.drawable.ic_group_crop);
                    imageView6.setImageResource(R.drawable.ic_download);
                    imageView3.setImageResource(R.drawable.ic_color_lens);

//                    saveImages();
                    saveimage2();
                    imageView6.setImageResource(R.drawable.ic_download2);
                    sqliteDatabase2.insertInfoTheDatabase(id, full, small);


                }

            });
        }

    }

    boolean toShow = false;

    Thread spinner1Thread = new Thread("Show/Hide Spinner Thread") {

        @Override
        public void run() {

            setProgressBarIndeterminateVisibility(toShow);

        }

    };

    public void showSpinner1(boolean pShow) {
        toShow = pShow;
        runOnUiThread(spinner1Thread);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    public void onWallpaperChanged(Bitmap bitmap, boolean onHomeScreen, boolean onLockScreen) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());

                NeumorphCardView cardView3 = findViewById(R.id.setwallpaper_container);

                try {
                    if (onHomeScreen) {
                        myWallpaperManager.setBitmap(bitmap);// For Home screen
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                cardView3.setVisibility(View.GONE);
                                progressBar1.setVisibility(View.GONE);
                                progressBar2.setVisibility(View.GONE);
                                progressBar3.setVisibility(View.GONE);

                            }
                        });

                    }

                    if (onLockScreen) {
                        myWallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);//For Lock screen
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                cardView3.setVisibility(View.GONE);
                                progressBar1.setVisibility(View.GONE);
                                progressBar2.setVisibility(View.GONE);
                                progressBar3.setVisibility(View.GONE);

                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();


    }

    private void saveImages() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        File file = getDisc();

        if (!file.exists() && !file.mkdirs()) {

            file.mkdirs();

        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyysshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "IMG" + date + ".jpg";
        File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), name);
        String file_name = file.getAbsolutePath();
        File new_file = new File(file_name);

        PRDownloader.download(full, new_file.getPath(), name)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        Toast.makeText(context, "download Start", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {
//                        Toast.makeText(context, "onPause", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
//                        Toast.makeText(context, "onCancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
//                        Toast.makeText(context, "onProgress", Toast.LENGTH_SHORT).show();
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Toast.makeText(context, "save images success", Toast.LENGTH_SHORT).show();
                        refreshGallery(file1);
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(context, "try again", Toast.LENGTH_SHORT).show();
                    }


                });

//        PRDownloader.download(full, new_file.getPath(), name)
//                .build()
//                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
//                    @Override
//                    public void onStartOrResume() {
//                        Toast.makeText(context, "download Start", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setOnPauseListener(new OnPauseListener() {
//                    @Override
//                    public void onPause() {
////                        Toast.makeText(context, "onPause", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setOnCancelListener(new OnCancelListener() {
//                    @Override
//                    public void onCancel() {
////                        Toast.makeText(context, "onCancel", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setOnProgressListener(new OnProgressListener() {
//                    @Override
//                    public void onProgress(Progress progress) {
////                        Toast.makeText(context, "onProgress", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .start(new OnDownloadListener() {
//                    @Override
//                    public void onDownloadComplete() {
//                        Toast.makeText(context, "save images success", Toast.LENGTH_SHORT).show();
//                        refreshGallery(file1);
//                    }
//
//                    @Override
//                    public void onError(Error error) {
//                        Toast.makeText(context, "try again", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                });

//        //        imageView6.setImageResource(R.drawable.ic_download2);
//           /* Log.d("TAG", "saveImage: downloading selected images");

    }

    private File getDisc() {

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(file, "Wallpaper");

    }

    private void refreshGallery(File file) {

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);

    }

    private static Bitmap viewToBitmap(View view, int widh, int hight)
    {
        Bitmap bitmap=Bitmap.createBitmap(widh,hight, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap); view.draw(canvas);
        return bitmap;
    }

    private void saveimage2() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        FileOutputStream fileOutputStream;

        File file2 = getDisc();

//        if (!file2.exists() && !file2.mkdirs()) {

        if (!file2.exists()) {
            file2.mkdirs();
        }
//
//            }

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyysshhmmss");
        String date2 = simpleDateFormat2.format(new Date());
        String name2 = "IMG" + date2 + ".jpg";
        String file_name2 = file2.getAbsolutePath() + "/" + name2;
        File new_file2 = new File(file_name2);
//        Toast.makeText(context, "" + file_name2, Toast.LENGTH_SHORT).show();
//
        try {

//            BitmapDrawable drawable2 = (BitmapDrawable) iv_download.getDrawable();
//            Bitmap bitmap = drawable2.getBitmap();

            Bitmap bitmap = viewToBitmap(iv_image,iv_image.getWidth(),iv_image.getHeight());
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);


            fileOutputStream = new FileOutputStream(new_file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            Toast.makeText(context, "save images success", Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
//            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "try again", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        refreshGallery(new_file2);

    }

}
