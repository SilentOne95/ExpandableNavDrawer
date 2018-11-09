package com.example.konta.expandablenavdrawer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<ExpandedMenuModel> mListGroupData;
    private HashMap<ExpandedMenuModel, List<String>> mListChildData;
    private ExpandableListView mExpandableListView;

    public MyExpandableListAdapter(Context context, List<ExpandedMenuModel> listGroupData,
                                   HashMap<ExpandedMenuModel, List<String>> listChildData,
                                   ExpandableListView expandableListView) {
        mContext = context;
        mListGroupData = listGroupData;
        mListChildData = listChildData;
        mExpandableListView = expandableListView;
    }

    @Override
    public int getGroupCount() {
        return mListGroupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childCount = 0;
        if (groupPosition != 2)
            childCount = mListChildData.get(mListGroupData.get(groupPosition)).size();

        return childCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListGroupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListChildData.get(mListGroupData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
