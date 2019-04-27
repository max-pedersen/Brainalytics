package org.maxpedersen.maquiz;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private int i;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.section_1, R.string.section_2, R.string.section_3, R.string.section_4, R.string.section_5};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // This method determines how many slides there should be. When it finds an attribute with "" it knows that there isn't any content will limit the slides respectively
        i = ContentDetailedSlide.getI();
        List<Content> list = new ArrayList<Content>();
        list = (ArrayList<Content>) DatabaseService.getDbInstance(mContext).getAppDatabase()
                .contentDAO().getContents();
        Log.d(" from list", list.get(i).toString() + list.get(0).toString() + " ok");
        if(list.get(i).getContent_page2().equalsIgnoreCase("")){
            return 1;
        }
        if(list.get(i).getContent_page3().equalsIgnoreCase("")){
            return 2;
        }
        if(list.get(i).getContent_page4().equalsIgnoreCase("")){
            return 3;
        }
        else{
            return 4;
        }
    }
}