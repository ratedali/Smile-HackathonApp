package io.divergents.hackathon.smile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.divergents.hackathon.smile.about.TipsActivity;
import io.divergents.hackathon.smile.data.Psychotherapist;
import io.divergents.hackathon.smile.databinding.ActivityMainBinding;
import io.divergents.hackathon.smile.exercises.LayoutContainerActivity;
import io.divergents.hackathon.smile.kids.TestActivity;
import io.divergents.hackathon.smile.therapy.PsychotherapistChooserActivity;
import io.divergents.hackathon.smile.therapy.TherapyActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        binding.drawer.addDrawerListener(
                new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, 0, 0));
        binding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_about: {
                Intent intent = new Intent(this, TipsActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.menu_item_therapy: {
                Intent intent = new Intent(this, TherapyActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.menu_item_exercises: {
                Intent intent = new Intent(this, LayoutContainerActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.menu_item_kids: {
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
            }
            default:
                return false;
        }
        return true;
    }
}
