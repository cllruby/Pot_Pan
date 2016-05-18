package com.example.ziyang.potpan;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wxx on 2016/4/25.
 */
public class wxx_example extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wxx_vp2);
        cll_exit.getInstance().addActivity(this);

        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new wxx_help1());
        fragments.add(new wxx_help2());
        fragments.add(new wxx_help3());
        fragments.add(new wxx_help4());
        fragments.add(new wxx_finalhelp());
        wxx_AboutUsPagerAdapter adapter = new wxx_AboutUsPagerAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager2);
        vp.setAdapter(adapter);






    }
}
