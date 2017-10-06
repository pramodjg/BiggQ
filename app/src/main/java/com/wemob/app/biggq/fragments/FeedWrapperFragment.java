package com.wemob.app.biggq.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.wemob.app.biggq.HomePageWrapper;
import com.wemob.app.biggq.R;

/**
 * Created by admin on 8/22/2017.
 */

public class FeedWrapperFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LayoutInflater layinflator;
    TextView tabOne,tabTwo,tabThree;
    private HomePageWrapper parentcontext;
    Button btnslide;
    FeedCallbackInterface mcallback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View content=inflater.inflate(R.layout.feed_wrapper_layout,null);
        layinflator=inflater;
        return content;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mcallback = (FeedWrapperFragment.FeedCallbackInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FeedCallbackInterface");
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void showSlider() {

        mcallback.showBottomPanel();
    }

    private void setupTabIcons() {
        tabOne = (TextView) layinflator.inflate(R.layout.custom_tab, null);
        tabOne.setText("Feed");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.feed, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        tabTwo = (TextView)layinflator.inflate(R.layout.custom_tab, null);
        tabTwo.setText("Trending");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.trend, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        tabThree = (TextView) layinflator.inflate(R.layout.custom_tab, null);
        tabThree.setText("Featured");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.featured_tab, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);



    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FeedFragment(),"Feed");
        TrendFragment trendFragment=new TrendFragment();
        //trendFragment.setParentcontext((HomePageWrapper) getActivity());
        adapter.addFragment(trendFragment,"Trending");

        FeaturedFragment featuredFragment=new FeaturedFragment();
        featuredFragment.setParentcontext((HomePageWrapper) getActivity());
        adapter.addFragment(featuredFragment,"Featured");

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    parentcontext.showFabMenu();
                } else {
                    parentcontext.hideFabMenu();
                }
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public HomePageWrapper getParentcontext() {
        return parentcontext;
    }

    public void setParentcontext(HomePageWrapper parentcontext) {
        this.parentcontext = parentcontext;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
    public interface FeedCallbackInterface
    {
        public void showBottomPanel();
    }
}
