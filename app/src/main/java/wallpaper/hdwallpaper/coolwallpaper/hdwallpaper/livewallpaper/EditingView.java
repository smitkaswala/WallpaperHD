package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;



import soup.neumorphism.NeumorphFloatingActionButton;

public class EditingView extends AppCompatActivity {

    NeumorphFloatingActionButton back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_view);

        back_btn = findViewById(R.id.back_arrow1);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}