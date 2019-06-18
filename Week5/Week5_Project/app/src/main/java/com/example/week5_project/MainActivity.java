package com.example.week5_project;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.week5_project.fragment.ActivityCommunicateFragment;
import com.example.week5_project.fragment.RestApiFragment;
import com.example.week5_project.fragment.ViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ncapdevi.fragnav.FragNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, FragNavController.RootFragmentListener {

    public static final int INDEX_VIEW = FragNavController.TAB1;
    public static final int INDEX_ACTIVITY_COMMUNICATE = FragNavController.TAB2;
    public static final int INDEX_REST_API = FragNavController.TAB3;


    protected BottomNavigationView bottomNavigationView;
    private FragNavController fragNavController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState); // 3)
        fragNavController.setRootFragmentListener(this); // 2)

    }

    private void initView() {

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.inflateMenu(R.menu.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // 1)
        fragNavController = new FragNavController(fragmentManager, R.id.main_content_linear_layout); // 1)
        ArrayList<Fragment> fragments = createFragmentNewInstance(); // 1)
        fragNavController.setRootFragments(fragments); // 1)

        fragNavController.setFragmentHideStrategy(FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH);
        fragNavController.setCreateEager(true);
    }


    ArrayList<Fragment> createFragmentNewInstance() { //1) 역할 명시적, getRoot on...콜백....private...

        int numberOfRootFragments = 3;
        ArrayList<Fragment> arrayList = new ArrayList<>(numberOfRootFragments);

        arrayList.add(ViewFragment.newInstance());
        arrayList.add(ActivityCommunicateFragment.newInstance());
        arrayList.add(RestApiFragment.newInstance());

        return arrayList;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_view:
                if (fragNavController.getCurrentStackIndex() == INDEX_VIEW) {
                    fragNavController.clearStack(INDEX_VIEW);
                }

                fragNavController.switchTab(INDEX_VIEW);
                break;
            case R.id.navigation_communicate_activity:
                if (fragNavController.getCurrentStackIndex() == INDEX_ACTIVITY_COMMUNICATE) {
                    fragNavController.clearStack(INDEX_ACTIVITY_COMMUNICATE);
                }

                fragNavController.switchTab(INDEX_ACTIVITY_COMMUNICATE);
                break;
            case R.id.navigation_rest_api:
                if (fragNavController.getCurrentStackIndex() == INDEX_REST_API) {
                    fragNavController.clearStack(INDEX_REST_API);
                }

                fragNavController.switchTab(INDEX_REST_API);
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
    public Fragment getRootFragment(int i) {
        switch (i) {
            case INDEX_VIEW:
                return ViewFragment.newInstance();
            case INDEX_ACTIVITY_COMMUNICATE:
                return ActivityCommunicateFragment.newInstance();
            case INDEX_REST_API:
                return RestApiFragment.newInstance();

        }
        throw new IllegalStateException("Need to send an index that we know");

    }

    @Override
    public void onBackPressed() {
        if (!fragNavController.isRootFragment()) {
            fragNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }
}
