package com.naxesa.a0903;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.naxesa.a0903.MainFragment.FragmentPagerAdapter;
import com.naxesa.a0903.MainFragment.MessageFragment;
import com.naxesa.a0903.MainFragment.MyPageFragment;
import com.naxesa.a0903.MainFragment.ProjectFragment;
import com.naxesa.a0903.MainFragment.TeamFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // View
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Reference
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // View Set
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ArrayList<String> tabTitles = new ArrayList<String>();
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        tabTitles.add("Team");
        tabTitles.add("Project");
        tabTitles.add("MyPage");
        tabTitles.add("Message");

        fragments.add(new TeamFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new MyPageFragment());
        fragments.add(new MessageFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext(), tabTitles, fragments);
        viewPager.setAdapter(adapter);
    }
}
