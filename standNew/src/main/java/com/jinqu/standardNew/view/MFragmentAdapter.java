package com.jinqu.standardNew.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.BaseFrg;

import java.util.ArrayList;
import java.util.List;

public class MFragmentAdapter extends FragmentPagerAdapter {
    public List<BaseFrg> fragments = new ArrayList();

    public MFragmentAdapter(FragmentManager fm, List<BaseFrg> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // MFragment mf = (MFragment) object;
        // mf.clearView();
        super.destroyItem(container, position, object);
    }
}