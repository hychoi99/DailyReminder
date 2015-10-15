package com.choimeetsworld.reminder.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.choimeetsworld.reminder.enums.NotificationsType;
import com.choimeetsworld.reminder.ui.fragments.TabFragment;
import com.choimeetsworld.reminder.R;

public class ViewPageAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

    private Context mContext;
    private final int[] ICONS = {
            R.drawable.selector_icon_active
            //R.drawable.selector_icon_inactive
    };

    public ViewPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public void tabUnselected(View view) {
        view.setSelected(false);
    }

    @Override
    public void tabSelected(View view) {
        view.setSelected(true);
    }

    @Override
    public View getCustomTabView(ViewGroup parent, int position) {
        FrameLayout customLayout = (FrameLayout) LayoutInflater.from(mContext).inflate(R.layout.custom_tab, parent, false);
        ((ImageView) customLayout.findViewById(R.id.image)).setImageResource(ICONS[position]);
        TextView title = (TextView) customLayout.findViewById(R.id.title);
        title.setText(mContext.getResources().getString(R.string.header_title));
        return customLayout;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return ICONS.length;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
            default:
                bundle.putSerializable("TYPE", NotificationsType.ACTIVE);
                break;
            case 1:
                bundle.putSerializable("TYPE", NotificationsType.INACTIVE);
                break;
        }
        Fragment fragment = new TabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}