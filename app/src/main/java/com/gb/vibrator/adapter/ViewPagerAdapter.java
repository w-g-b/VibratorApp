package com.gb.vibrator.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

  private List<Fragment> list;

  public void setList(List<Fragment> list) {
    this.list = list;
    notifyDataSetChanged();
  }

  public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
    super( fm );
    this.list = list;
  }

  @Override public Fragment getItem(int position) {
    return list.get( position );
  }

  @Override public int getCount() {
    return list != null ? list.size() : 0;
  }
}