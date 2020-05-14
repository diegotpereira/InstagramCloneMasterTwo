package br.com.myapplication.instagramclonemastertwo.Activity;

import android.os.Bundle;

import android.view.MenuItem;

import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.myapplication.instagramclonemastertwo.Fragment.AddImageFragment;
import br.com.myapplication.instagramclonemastertwo.Fragment.FeedFragment;
import br.com.myapplication.instagramclonemastertwo.Fragment.LikeFragment;
import br.com.myapplication.instagramclonemastertwo.Fragment.ProfileFragment;
import br.com.myapplication.instagramclonemastertwo.R;


public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new FeedFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_add:
                    fragment=new AddImageFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_like:
                    fragment=new LikeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    fragment=new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new FeedFragment());
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
