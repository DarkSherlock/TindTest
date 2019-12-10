package com.liang.tind.www.tindtest.activty.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @author 梁天德
 * @date 2019/10/30 10:00
 * desc
 */
public class TestTabLayout extends BaseActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void init() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        List<TestFragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestFragment testFragment = new TestFragment();
            testFragment.title = "title" + i;
            fragmentList.add(testFragment);
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setText(testFragment.title);
            mTabLayout.addTab(tab);
        }
        mViewPager.setAdapter(new Adapter(getSupportFragmentManager(), fragmentList));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public static   class TestFragment extends Fragment {
        public String title;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            TextView textView = new TextView(getContext());
            textView.setText("测试Fragment:" + title);
            return textView;
        }
    }

    public static class Adapter extends FragmentStatePagerAdapter {
        private static final String TAG = "WorkOrderVpAdapter";
        private List<TestFragment> mList;

        public Adapter(FragmentManager fm, @NonNull List<TestFragment> list) {
            super(fm);
            mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).title;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
