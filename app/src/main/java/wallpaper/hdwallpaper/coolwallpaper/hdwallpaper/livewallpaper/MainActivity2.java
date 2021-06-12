package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.fragments.PageFragment1;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.fragments.PageFragment2;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.fragments.PageFragment3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphFloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private BottomNavigationView bottomNavigationView;
    Dialog dial;

    private AppUpdateManager appUpdateManager;

    private static final int UPDATE_REQUEST_CODE = 101;

    NeumorphFloatingActionButton home,like,download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);

//        bottomNavigationView = findViewById(R.id.bottomNav);
//        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        home = findViewById(R.id.iv_house);
        like = findViewById(R.id.iv_like);
        download = findViewById(R.id.iv_download);

        appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(result -> {
            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){
                try {
                    appUpdateManager.startUpdateFlowForResult(result,MainActivity2.this,
                            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).setAllowAssetPackDeletion(true).build(),
                            UPDATE_REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });

        int type = 0;

        if (type == 0) {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    home.setImageResource(R.drawable.ic_home2);
                    download.setImageResource(R.drawable.ic_download);
                    like.setImageResource(R.drawable.ic_favorite);

                    pager.setCurrentItem(0);

                }
            });
        }

        int count = 0;

        if (count == 0) {

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    like.setImageResource(R.drawable.ic_favorite2);
                    home.setImageResource(R.drawable.ic_home);
                    download.setImageResource(R.drawable.ic_download);

                    pager.setCurrentItem(1);
                }
            });
        }

        int level = 0;

        if (level == 0) {

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    download.setImageResource(R.drawable.ic_download2);
                    like.setImageResource(R.drawable.ic_favorite);
                    home.setImageResource(R.drawable.ic_home);

                    pager.setCurrentItem(2);

                }

            });

        }

        dial = new Dialog(MainActivity2.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dial.requestWindowFeature(1);
        dial.setContentView(R.layout.dialog_exit);
        dial.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dial.setCanceledOnTouchOutside(true);

        dial.findViewById(R.id.delete_yes).setOnClickListener(view -> {
            dial.dismiss();
            finishAffinity();
        });

        dial.findViewById(R.id.rate_us_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName() + "");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                } catch (ActivityNotFoundException e) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() + "");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                }


            }
        });

        dial.findViewById(R.id.delete_no).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dial.dismiss();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(result -> {
            if (result.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                // If an in-app update is already running, resume the update.
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            result,
                            AppUpdateType.IMMEDIATE,
                            MainActivity2.this,
                            UPDATE_REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_REQUEST_CODE){

        }
    }

    @Override
    public void onBackPressed() {

        dial.show();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener()
            {

                @Override

                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment = null;
                    switch (item.getItemId())

                    {

                        case R.id.home:

                            pager.setCurrentItem(0);

                            break;

                        case R.id.favorite:

                            pager.setCurrentItem(1);

                            break;

                        case R.id.download:

                            pager.setCurrentItem(2);

                            break;

                    }

                    return true;

                }

            };

}