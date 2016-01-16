package com.madwa.sangraha.Activiteis;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.madwa.sangraha.Fragments.Home.MantraFragment;
import com.madwa.sangraha.Fragments.Home.MusicFragment;
import com.madwa.sangraha.Fragments.Home.PravachanaFragment;
import com.madwa.sangraha.Fragments.Home.NavigationDrawerFragment;
import com.madwa.sangraha.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class HomeActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,View.OnClickListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private DrawerLayout drawerLayout;
    private ImageView imageMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        restoreActionBar();
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,drawerLayout);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_introduction);
                break;
            case 2:
                mTitle = getString(R.string.title_about);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

        imageMenu = (ImageView) mCustomView
                .findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(this);

        ImageView imageSearch = (ImageView) mCustomView
                .findViewById(R.id.imageMenu);
        imageSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        actionBar.setCustomView(mCustomView);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (!mNavigationDrawerFragment.isDrawerOpen())
            drawerLayout.openDrawer(Gravity.LEFT);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private SmartTabLayout smartTabLayout;
        private ViewPager viewPager;
        private Spinner spinnerLang;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
            smartTabLayout = (SmartTabLayout) rootView.findViewById(R.id.viewpagertab);

            spinnerLang= (Spinner) rootView.findViewById(R.id.spinnerLang);

            String[] langArray={"All","Kannada","Sanskrit","English","Telugu","Tamil"};
            ArrayAdapter<String> langAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,langArray);
            spinnerLang.setAdapter(langAdapter);
            spinnerLang.setSelection(0);

            final LayoutInflater inflater1 = LayoutInflater.from(smartTabLayout.getContext());
            final Resources res = smartTabLayout.getContext().getResources();

            smartTabLayout.setCustomTabView(new SmartTabLayout.TabProvider() {
                @Override
                public View createTabView(ViewGroup viewGroup, int i, PagerAdapter pagerAdapter) {
                    ImageView icon = (ImageView) inflater1.inflate(R.layout.custom_tab_icon1, viewGroup, false);
                    switch (i) {
                        case 0:
                            icon.setImageDrawable(res.getDrawable(R.drawable.icn_mantra_unsel));
                            break;
                        case 1:
                            icon.setImageDrawable(res.getDrawable(R.drawable.icn_music_unsel));
                            break;
                        case 2:
                            icon.setImageDrawable(res.getDrawable(R.drawable.icn_pravachana_unsel));
                            break;
                        default:
                            throw new IllegalStateException("Invalid position: " + i);
                    }
                    return icon;
                }
            });


            FragmentPagerItems pages = new FragmentPagerItems(getActivity());

            pages.add(FragmentPagerItem.of("", MantraFragment.class));
            pages.add(FragmentPagerItem.of("", MusicFragment.class));
            pages.add(FragmentPagerItem.of("", PravachanaFragment.class));


            FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                    getChildFragmentManager(), pages);

            viewPager.setAdapter(adapter);
            smartTabLayout.setViewPager(viewPager);

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
