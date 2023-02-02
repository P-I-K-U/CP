package com.example.collegeproject;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.collegeproject.Assignment.CreateAssignmentActivity;
import com.example.collegeproject.BottomFragments.AssignmentFragment;
import com.example.collegeproject.BottomFragments.ChatsFragment;
import com.example.collegeproject.BottomFragments.ContactsFragment;
import com.example.collegeproject.BottomFragments.HomeFragment;
import com.example.collegeproject.Progress.ProgressFragment;
import com.example.collegeproject.Remark.RemarkFragment;
import com.example.collegeproject.fee.FeeFragment;
import com.example.collegeproject.attendance.ClassFragment;
import com.example.collegeproject.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    ActionBarDrawerToggle toggle;
    static final float END_SCALE = 0.7f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        animateNavDrawer();

        Menu menu = binding.navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.logout);

        SpannableString logout = new SpannableString(menuItem.getTitle());
        ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.bloodRed));
        logout.setSpan(fcs, 0,logout.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        menuItem.setTitle(logout);


        getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new HomeFragment()).commit();
        binding.navigationView.setCheckedItem(R.id.home);
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new HomeFragment()).commit();
                        break;
                    case R.id.attendence:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new ClassFragment()).commit();
                        break;
                    case R.id.fee:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new FeeFragment()).commit();
                        break;
                    case R.id.remark:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new RemarkFragment()).commit();
                        break;
                    case R.id.progress:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new ProgressFragment()).commit();
                        break;
                    case R.id.logout:
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finishAffinity();
                        break;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);

                return true;

            }
        });




        binding.bottom.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new HomeFragment()).commit();
                    break;
                case R.id.assignment:
                    getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new AssignmentFragment()).commit();
                    break;
                case R.id.chats:
                    getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new ChatsFragment()).commit();
                    break;
                case R.id.contacts:
                    if(ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        // ActivityCompat.requestPermissions(CreateAssignmentActivity.this, new String[]{Manifest.permission.CAMERA},1);  //line 136-151
                        requestLauncher.launch(Manifest.permission.CALL_PHONE);
                    }else{
                        getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new ContactsFragment()).commit();
                    }
                    break;
            }

            return true;
        });

    }

    ActivityResultLauncher<String> requestLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if(result){
                getSupportFragmentManager().beginTransaction().replace(R.id.bReplace, new ContactsFragment()).commit();
            }
            else{
                Toast.makeText(HomeActivity.this, "Call Permission Denied\nTo Allow Permission go to\n Setting < App Manager / App Permission", Toast.LENGTH_SHORT).show();
            }
        }
    });

    private void animateNavDrawer() {
        binding.drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                binding.bReplace.setScaleX(offsetScale);
                binding.bReplace.setScaleY(offsetScale);
                // translate the view accounting of the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = binding.bReplace.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                binding.bReplace.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }


}