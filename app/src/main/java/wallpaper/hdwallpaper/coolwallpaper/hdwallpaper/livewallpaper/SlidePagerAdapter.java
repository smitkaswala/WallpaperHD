package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class SlidePagerAdapter extends FragmentStatePagerAdapter {

    Context context;
    private List<Fragment> fragmentList;

    public SlidePagerAdapter(FragmentManager fm,List<Fragment> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
        this.context = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new HomeFragment();
        } else if (position == 1) {
            fragment = new FavoriteFragment();
        } else if (position == 2) {
            fragment = new DownloadFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
