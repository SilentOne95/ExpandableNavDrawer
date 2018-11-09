package com.example.konta.expandablenavdrawer;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    MyExpandableListAdapter myExpandableListAdapter;
    ExpandableListView expandableListView;
    List<ExpandedMenuModel> listGroupData;
    HashMap<ExpandedMenuModel, List<String>> listChildData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        expandableListView = findViewById(R.id.expandable_list_view);

        prepareListData();

        expandableListView.setAdapter(myExpandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
    }

    private void prepareListData() {
        listGroupData = new ArrayList<>();
        listChildData = new HashMap<>();

        ExpandedMenuModel itemOne = new ExpandedMenuModel();
        itemOne.setIconName("IconOne");
        itemOne.setIconResourceId(R.drawable.ic_menu_home);
        listGroupData.add(itemOne);

        ExpandedMenuModel itemTwo = new ExpandedMenuModel();
        itemTwo.setIconName("IconTwo");
        itemTwo.setIconResourceId(R.drawable.ic_menu_cube);
        listGroupData.add(itemTwo);

        ExpandedMenuModel itemThree = new ExpandedMenuModel();
        itemThree.setIconName("IconThree");
        itemThree.setIconResourceId(R.drawable.ic_menu_info);
        listGroupData.add(itemThree);

        List<String> childOne = new ArrayList<>();
        childOne.add("Submenu of item one");

        List<String> childTwo = new ArrayList<>();
        childTwo.add("Submenu of item two");
        childTwo.add("Submenu of item two");
        childTwo.add("Submenu of item two");

        listChildData.put(listGroupData.get(0), childOne);
        listChildData.put(listGroupData.get(1), childTwo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
