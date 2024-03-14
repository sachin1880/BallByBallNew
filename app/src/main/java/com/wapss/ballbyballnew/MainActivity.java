package com.wapss.ballbyballnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.wapss.ballbyballnew.fragment.HomeFragment;
import com.wapss.ballbyballnew.fragment.MatcheFragment;
import com.wapss.ballbyballnew.fragment.Profile_Fragment;
import com.wapss.ballbyballnew.fragment.WalletFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation nav_view1;
    ImageView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));

        notification=findViewById(R.id.notification);

        //navigation
        nav_view1 = findViewById(R.id.nav_view1);
        nav_view1.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        nav_view1.add(new MeowBottomNavigation.Model(2, R.drawable.ball1));
        nav_view1.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_wallet_24));
        nav_view1.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_account_circle_24));

        nav_view1.show(1,true);
        replace(new HomeFragment());
        nav_view1.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new HomeFragment());
                        break;
                    case 2:
                        replace(new MatcheFragment());
                        break;
                    case 3:
                        replace(new WalletFragment());
                        break;
                    case 4:
                        replace(new Profile_Fragment());
                        break;
                }
                return null;
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void replace(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }
}