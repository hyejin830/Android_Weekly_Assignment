package com.example.week3_project_v2.assignment_2_frag_nav;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.week3_project_v2.Constant;
import com.example.week3_project_v2.R;
import com.example.week3_project_v2.assignment_2_frag_nav.fragment.HomeFragment;
import com.example.week3_project_v2.assignment_2_frag_nav.fragment.MyPageFragment;
import com.example.week3_project_v2.assignment_2_frag_nav.fragment.OrderListFragment;
import com.example.week3_project_v2.assignment_2_frag_nav.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ncapdevi.fragnav.FragNavController;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import kotlin.jvm.Throws;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, FragNavController.RootFragmentListener, HomeFragment.OnFragmentPushListener {


    private static int HOME_INDEX = 0;

    private int numberOfRootFragments = 4;

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragNavController fragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        initView();

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState); // 3)
        fragNavController.setRootFragmentListener(this); // 2)

    }

    void initView() {

        // bottom
        bottomNavigationView = findViewById(R.id.bottom_navigation); // bottom tabs...
        bottomNavigationView.inflateMenu(R.menu.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        // 1)
        fragNavController = new FragNavController(fragmentManager, R.id.main_content_linear_layout); // 1)
        ArrayList<Fragment> fragments = onCreateFragmentNewInstance(); // 1)
        fragNavController.setRootFragments(fragments); // 1)

        fragNavController.setFragmentHideStrategy(FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH);
        fragNavController.setCreateEager(true);

    }

    ArrayList<Fragment> onCreateFragmentNewInstance() { //1)

        ArrayList<Fragment> arrayList = new ArrayList<>(numberOfRootFragments);

        arrayList.add(HomeFragment.newInstance(0));
        arrayList.add(SearchFragment.newInstance(0));
        arrayList.add(OrderListFragment.newInstance(0));
        arrayList.add(MyPageFragment.newInstance(0));

        return arrayList;

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                if (fragNavController.getCurrentStackIndex() == Constant.INDEX_HOME) {
                    HOME_INDEX = 0;
                    fragNavController.clearStack(Constant.INDEX_HOME);
                }

                fragNavController.switchTab(Constant.INDEX_HOME);
                break;
            case R.id.navigation_search:
                if (fragNavController.getCurrentStackIndex() == Constant.INDEX_SEARCH) {
                    fragNavController.clearStack(Constant.INDEX_SEARCH);
                }

                fragNavController.switchTab(Constant.INDEX_SEARCH);
                break;
            case R.id.navigation_order_list:
                if (fragNavController.getCurrentStackIndex() == Constant.INDEX_OREDER_LIST) {
                    fragNavController.clearStack(Constant.INDEX_OREDER_LIST);
                }

                fragNavController.switchTab(Constant.INDEX_OREDER_LIST);
                break;
            case R.id.navigation_my_page:
                if (fragNavController.getCurrentStackIndex() == Constant.INDEX_MY_PAGE) {
                    fragNavController.clearStack(Constant.INDEX_MY_PAGE);
                }

                fragNavController.switchTab(Constant.INDEX_MY_PAGE);
                break;

        }

        return true;
    }

    @Override
    public int getNumberOfRootFragments() {
        return 0;
    }

    @NotNull
    @Override
    public Fragment getRootFragment(int index) { // 2)

        switch (index) {
            case Constant.INDEX_HOME:
                return HomeFragment.newInstance(0);
            case Constant.INDEX_SEARCH:
                return SearchFragment.newInstance(0);
            case Constant.INDEX_OREDER_LIST:
                return OrderListFragment.newInstance(0);
            case Constant.INDEX_MY_PAGE:
                return MyPageFragment.newInstance(0);

        }
        throw new IllegalStateException("Need to send an index that we know");

    }


    @Override
    public void onFragmentPushed() {
        fragNavController.pushFragment(HomeFragment.newInstance(++HOME_INDEX));
    }

    @Override
    public void onBackPressed() {


        if (!fragNavController.isRootFragment()) {
            fragNavController.popFragment();
            --HOME_INDEX;
        } else {
            super.onBackPressed();
        }
    }
}
