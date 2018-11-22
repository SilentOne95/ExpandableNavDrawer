package com.example.konta.expandablenavdrawer;

public class MenuModel {

    public String menuName;
    public boolean hasChildren, isGroup;
    public int imageResourceId;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren, int imageResourceId) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.imageResourceId = imageResourceId;
    }
}
