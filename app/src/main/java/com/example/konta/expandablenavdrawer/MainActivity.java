package com.example.konta.expandablenavdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> menuList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> submenuList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView = findViewById(R.id.expandable_list_view);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void prepareMenuData() {

        // First menuItem with no submenuItem
        MenuModel menuModel = new MenuModel("Menu 1", true, false, R.drawable.ic_menu_home);
        menuList.add(menuModel);

        if (!menuModel.hasChildren) {
            submenuList.put(menuModel, null);
        }

        // Second menuItem with 3 submenuItem
        menuModel = new MenuModel("Menu 2", true, true, R.drawable.ic_menu_cube);
        menuList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Submenu 2-1", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("Submenu 2-2", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("Submenu 2-3", false, false);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            submenuList.put(menuModel, childModelsList);
        }

        // Third menuItem with 2 submenuItem
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Menu 3", true, true, R.drawable.ic_menu_info);
        menuList.add(menuModel);
        childModel = new MenuModel("Submenu 3-1", false, false);
        childModelsList.add(childModel);


        childModel = new MenuModel("Submenu 3-2", false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            submenuList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListAdapter(this, menuList, submenuList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (menuList.get(groupPosition).isGroup) {
                    if (!menuList.get(groupPosition).hasChildren) {
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (submenuList.get(menuList.get(groupPosition)) != null) {
                    onBackPressed();
                }

                return false;
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
