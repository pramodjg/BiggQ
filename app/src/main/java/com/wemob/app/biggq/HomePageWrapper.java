package com.wemob.app.biggq;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.nineoldandroids.animation.AnimatorSet;

import com.mindorks.placeholderview.PlaceHolderView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import com.wemob.app.biggq.apiHandler.ApiLinks;
import com.wemob.app.biggq.fragments.AboutFragment;
import com.wemob.app.biggq.fragments.AddMoneyPage;
import com.wemob.app.biggq.fragments.AskQuestion;
import com.wemob.app.biggq.fragments.ConfirmationDialog;
import com.wemob.app.biggq.fragments.FeedWrapperFragment;
import com.wemob.app.biggq.fragments.NotificationView;
import com.wemob.app.biggq.fragments.PopupFragment;
import com.wemob.app.biggq.fragments.ProfilePage;
import com.wemob.app.biggq.fragments.SearchFragment;
import com.wemob.app.biggq.fragments.WalletFragment;
import com.wemob.app.biggq.utils.Utils;

public class HomePageWrapper extends AppCompatActivity implements ProfilePage.ProfileCallbackListener,WalletFragment.WalletInterface,DrawerMenuItem.DrawerCallBack,FeedWrapperFragment.FeedCallbackInterface,AskQuestion.AskCallbackListener{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private PlaceHolderView mDrawerView;

    public DrawerLayout getmDrawer() {
        return mDrawer;
    }

    private DrawerLayout mDrawer;
    private PlaceHolderView mGalleryView;
    FeedWrapperFragment feedwrapper;
    private FloatingActionButton rightLowerButton;
    SubActionButton rrLSub1,rrLSub2,rrLSub3,rrLSub4;
    FloatingActionMenu rightLowerMenu;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private View searchAppBarLayout;
    private Toolbar searchToolBar;
    BiggQ applicationobj;
   private EditText searchEditText;
    private float positionFromRight = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_wrapper);
        this.mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        this.mDrawerView = (PlaceHolderView) findViewById(R.id.drawerView);

        applicationobj=(BiggQ)getApplication();
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        searchAppBarLayout = findViewById(R.id.layout_appbar_search);
        toolbar = (Toolbar) findViewById(R.id.apptoolbar);
        searchEditText = (EditText) findViewById(R.id.editText_search);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              showSearchFragment();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        setSupportActionBar(toolbar);

        this.mGalleryView = (PlaceHolderView) findViewById(R.id.galleryView);
        final AppCompatImageView fabIconNew = new AppCompatImageView(this);
        fabIconNew.setImageResource(R.drawable.ic_menu);

        this.rightLowerButton =  new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .setBackgroundDrawable(R.drawable.fab_back)
                .build();
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_monitor));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_filter_outline));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_drawable));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_vertical));
        this.rrLSub1 = rLSubBuilder.setContentView(rlIcon1).build();
        this.rrLSub1.setBackground(getResources().getDrawable(R.drawable.fab_sub_back));
        this.rrLSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        this.rrLSub2 = rLSubBuilder.setContentView(rlIcon2).build();
        this.rrLSub2.setBackground(getResources().getDrawable(R.drawable.fab_sub_back));
        this.rrLSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        this.rrLSub3 = rLSubBuilder.setContentView(rlIcon3).build();
        this.rrLSub3.setBackground(getResources().getDrawable(R.drawable.fab_sub_back));
        this.rrLSub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        this.rrLSub4 = rLSubBuilder.setContentView(rlIcon4).build();
        this.rrLSub4.setBackground(getResources().getDrawable(R.drawable.fab_sub_back));
        this.rrLSub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        this.rightLowerMenu = new FloatingActionMenu.Builder(this).addSubActionView(this.rrLSub1).addSubActionView(this.rrLSub2).addSubActionView(this.rrLSub3).addSubActionView(this.rrLSub4).attachTo(this.rightLowerButton).build();
        this.rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            public void onMenuOpened(FloatingActionMenu menu) {
                fabIconNew.setRotation(0.0f);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, new float[]{45.0f});
                ObjectAnimator.ofPropertyValuesHolder(fabIconNew, new PropertyValuesHolder[]{pvhR}).start();
            }

            public void onMenuClosed(FloatingActionMenu menu) {
                fabIconNew.setRotation(45.0f);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, new float[]{0.0f});
                ObjectAnimator.ofPropertyValuesHolder(fabIconNew, new PropertyValuesHolder[]{pvhR}).start();
            }
        });
        setupDrawer();
        initSearchBar();


    }

    private void showSearchFragment() {
        SearchFragment searchFragment = new SearchFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, searchFragment)
                .addToBackStack("search")
                .commit();

    }


    private void setupDrawer(){

        DrawerHeader drawerHeader=null;
        if(applicationobj.getCurrentUser().getLogin_method()==1) {
            drawerHeader = new DrawerHeader(this, applicationobj.getCurrentUser().getName(), "NA", applicationobj.getCurrentUser().getProfilePic());
        }
        else
        {
            drawerHeader = new DrawerHeader(this, applicationobj.getCurrentUser().getName(), "NA", ApiLinks.baseURL + applicationobj.getCurrentUser().getProfilePic());
        }

        mDrawerView
                .addView(drawerHeader)
                .addView(new DrawerMenuItem(this,DrawerMenuItem.DRAWER_MENU_ITEM_HOME))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_WALLET))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_PRIVACY))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_TERMS))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_INVITE))
                .addView(new DrawerMenuItem(this,DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));



        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawer.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
        initScreen();
    }
    private void initScreen() {
        // Creating the ViewPager container fragment once
        feedwrapper = new FeedWrapperFragment();
        feedwrapper.setParentcontext(this);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, feedwrapper)
                .commit();
    }

    /**
     * Initialize searchBar.
     */
    private void initSearchBar() {
        searchToolBar = (Toolbar) findViewById(R.id.toolbar_search);
        if (searchToolBar != null) {
            searchToolBar.setNavigationIcon(R.drawable.ic_back);
            searchAppBarLayout.setVisibility(View.GONE);
            searchToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSearchBar(positionFromRight);
                    if (getFragmentManager().getBackStackEntryCount() > 0) {
                        getFragmentManager().popBackStack();
                    } else {
                        onBackPressed();
                    }

                }
            });
        }
    }

    /**
     * Initialize viewPager.
     */

    /**
     * Initialize tabLayout.
     * create and add tab.
     */


    /**
     * to show the searchAppBarLayout and hide the mainAppBar with animation.
     *
     * @param positionFromRight
     */
    private void showSearchBar(float positionFromRight) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                com.nineoldandroids.animation.ObjectAnimator.ofFloat(appBar, "translationY", toolbar.getHeight()),
                com.nineoldandroids.animation.ObjectAnimator.ofFloat(appBar, "alpha", 0)
        );
        set.setDuration(100).addListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                appBar.setVisibility(View.GONE);
                searchEditText.requestFocus();
                Utils.showKeyBoard(searchEditText);
            }

            @Override
            public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

            }
        });
        set.start();


        // start x-index for circular animation
        int cx = toolbar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48)* (0.5f + positionFromRight));
        // start y-index for circular animation
        int cy = (toolbar.getTop() + toolbar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, toolbar.getWidth() - cx);
        int dy = Math.max(cy, toolbar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        final Animator animator;
        animator = com.wemob.app.biggq.animation.ViewAnimationUtils
                .createCircularReveal(searchAppBarLayout, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        searchAppBarLayout.setVisibility(View.VISIBLE);
        animator.start();
        // Circular animation declaration end
    }


    /**
     * to hide the searchAppBarLayout and show the mainAppBar with animation.
     *
     * @param positionFromRight
     */
    private void hideSearchBar(float positionFromRight) {

        // start x-index for circular animation
        int cx = toolbar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48) * (0.5f + positionFromRight));
        // start  y-index for circular animation
        int cy = (toolbar.getTop() + toolbar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, toolbar.getWidth() - cx);
        int dy = Math.max(cy, toolbar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        Animator animator;
        animator = com.wemob.app.biggq.animation.ViewAnimationUtils
                .createCircularReveal(searchAppBarLayout, cx, cy, finalRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                searchAppBarLayout.setVisibility(View.GONE);
                Utils.hideKeyBoard(searchEditText);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        animator.start();
        // Circular animation declaration end

        appBar.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
               com.nineoldandroids.animation.ObjectAnimator.ofFloat(appBar, "translationY", 0),
                com.nineoldandroids.animation.ObjectAnimator.ofFloat(appBar, "alpha", 1)

        );
        set.setDuration(100).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            showSearchBar(positionFromRight);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // if the searchToolBar is visible, hide it
        // otherwise, do parent onBackPressed method
        if (searchAppBarLayout.getVisibility() == View.VISIBLE) {
            hideSearchBar(positionFromRight);
        }
        else
        {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }


    }

    @Override
    public void addMoneySelected() {
        AddMoneyPage addMoneyFragment = new AddMoneyPage();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, addMoneyFragment)
                .addToBackStack("Add Money")
                .commit();
    }

    @Override
    public void onProfileMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
        showProfilePage();
    }

    @Override
    public void onWalletMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
        showWalletPage();
    }

    @Override
    public void onPrivacyMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onTermsMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onInviteMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onLogoutMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onAboutMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
        showAboutPage();
    }

    @Override
    public void onHomeMenuSelected() {
        if(mDrawer.isDrawerOpen(Gravity.LEFT))mDrawer.closeDrawer(Gravity.LEFT);
        initScreen();
    }

    private void showAboutPage() {

        AboutFragment aboutFragment = new AboutFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, aboutFragment)
                .addToBackStack("About")
                .commit();
    }


    @Override
    public void showBottomPanel() {
        BottomSlidingPanel bottomSheetDialogFragment = new BottomSlidingPanel();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }


    public void showProfilePage() {
        ProfilePage profilefragment = new ProfilePage();
        Bundle params=new Bundle();
        params.putSerializable("CurrentUser",applicationobj.getCurrentUser());
        profilefragment.setArguments(params);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, profilefragment)
                .addToBackStack("Profile")
                .commit();
    }
    private void showWalletPage() {
        WalletFragment walletfragment = new WalletFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, walletfragment)
                .addToBackStack("Wallet")
                .commit();
    }






    @Override
    public void showAskQuestionFragment() {
        BiggQ applicationobj=(BiggQ)getApplication();
        AskQuestion askQuestion = new AskQuestion();
        Bundle askparams=new Bundle();
        askparams.putString("id",applicationobj.getCurrentUser().getId());

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, askQuestion)
                .addToBackStack("Ask Question")
                .commit();
    }

    @Override
    public void showConfirmationDialog(String offerAmount) {
        showConfirmationPopup(offerAmount);
    }

    @Override
    public void showCelebrityFragment(String key) {

    }

    public void hideFabMenu()
    {
        this.rightLowerButton.setVisibility(View.GONE);
        rrLSub1.setVisibility(View.GONE);
        rrLSub2.setVisibility(View.GONE);
        rrLSub3.setVisibility(View.GONE);
        rrLSub4.setVisibility(View.GONE);
    }
    public void showFabMenu()
    {
        this.rightLowerButton.setVisibility(View.VISIBLE);
        rrLSub1.setVisibility(View.VISIBLE);
        rrLSub2.setVisibility(View.VISIBLE);
        rrLSub3.setVisibility(View.VISIBLE);
        rrLSub4.setVisibility(View.VISIBLE);
    }
    public FloatingActionButton getRightLowerButton() {
        return rightLowerButton;
    }

    public void setRightLowerButton(FloatingActionButton rightLowerButton) {
        this.rightLowerButton = rightLowerButton;
    }
    private void showConfirmationPopup(String offerAmount) {
        FragmentManager fm = getSupportFragmentManager();
        ConfirmationDialog optionfragment = ConfirmationDialog.newInstance("Confirm Payment",offerAmount);
        optionfragment.show(fm, "confirmfragment");

    }

    private void showPopupup() {
        FragmentManager fm = getSupportFragmentManager();
        PopupFragment optionfragment = PopupFragment.newInstance("Some Title");
        optionfragment.show(fm, "fragment_edit_name");
        rightLowerMenu.close(true);
    }

}