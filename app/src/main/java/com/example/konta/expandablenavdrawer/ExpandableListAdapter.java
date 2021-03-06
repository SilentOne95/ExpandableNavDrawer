package com.example.konta.expandablenavdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<MenuModel> listMenuItem;
    private HashMap<MenuModel, List<MenuModel>> listSubmenuItem;

    public ExpandableListAdapter(Context context, List<MenuModel> listDataHeader,
                                 HashMap<MenuModel, List<MenuModel>> listChildData) {
        this.context = context;
        this.listMenuItem = listDataHeader;
        this.listSubmenuItem = listChildData;
    }

    @Override
    public MenuModel getChild(int groupPosition, int childPosition) {
        return this.listSubmenuItem.get(this.listMenuItem.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String submenuListTitle = getChild(groupPosition, childPosition).menuName;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.submenu_list_item, null);
        }

        TextView submenuListTextView = convertView
                .findViewById(R.id.submenu_item_text_view);
        submenuListTextView.setText(submenuListTitle);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listSubmenuItem.get(this.listMenuItem.get(groupPosition)) == null)
            return 0;
        else
            return this.listSubmenuItem.get(this.listMenuItem.get(groupPosition))
                    .size();
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.listMenuItem.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listMenuItem.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String menuListTitle = getGroup(groupPosition).menuName;
        int menuListIcon = getGroup(groupPosition).imageResourceId;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_list_item, null);
        }

        TextView menuListTextView = convertView.findViewById(R.id.menu_item_text_view);
        menuListTextView.setTypeface(null, Typeface.BOLD);
        menuListTextView.setText(menuListTitle);
        menuListTextView.setCompoundDrawablesWithIntrinsicBounds(menuListIcon, 0, 0,0);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
