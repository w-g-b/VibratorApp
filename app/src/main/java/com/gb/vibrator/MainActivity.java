package com.gb.vibrator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gb.vibrator.adapter.ViewPagerAdapter;
import com.gb.vibrator.fragment.CreateVibratorFragment;
import com.gb.vibrator.fragment.SingleVibratorFragment;
import com.gb.vibrator.fragment.WaveVibratorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.bottomNavigationView) BottomNavigationView bottomNavigationView;
  @BindView(R.id.viewPager) ViewPager viewPager;
  MenuItem menuItem;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate( savedInstanceState );
    requestWindowFeature( Window.FEATURE_NO_TITLE );
    setContentView( R.layout.activity_main );
    ButterKnife.bind( this );
    init();
  }

  private void init() {
    //ViewPager初始化
    viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        if (menuItem != null) {
          menuItem.setChecked( false );
        } else {
          bottomNavigationView.getMenu().getItem( 0 ).setChecked( false );
        }
        menuItem = bottomNavigationView.getMenu().getItem( position );
        menuItem.setChecked( true );
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    } );
    List<Fragment> list = new ArrayList<>();
    list.add( new SingleVibratorFragment() );
    list.add( new WaveVibratorFragment() );
    list.add( new CreateVibratorFragment() );
    ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager(), list );
    viewPager.setAdapter( adapter );

    //BottomNavigationView初始化
    bottomNavigationView.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            menuItem = item;
            switch (item.getItemId()) {
              case R.id.single_vibrator:
                viewPager.setCurrentItem( 0 );
                return true;
              case R.id.wave_vibrator:
                viewPager.setCurrentItem( 1 );
                return true;
              case R.id.create_vibrator:
                viewPager.setCurrentItem( 2 );
                return true;
            }
            return false;
          }
        } );
  }
}
