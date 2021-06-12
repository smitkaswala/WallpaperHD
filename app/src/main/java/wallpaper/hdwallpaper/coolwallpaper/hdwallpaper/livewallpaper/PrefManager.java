package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public PrefManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Mypref",Context.MODE_PRIVATE);
    }

    public void getpage(int t){
        editor = sharedPreferences.edit();
        editor.putInt("page", 1);
        editor.apply();
    }

    public int setpage() {

        return sharedPreferences.getInt("page",10);

    }
}
