package com.cc.spzx.manager.utils;

import com.cc.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    private static List<SysMenu> list;

    public static List<SysMenu> buildTree(List<SysMenu> list) {

        List<SysMenu> trees = new ArrayList<>();

        for(SysMenu sysMenu : list) {
            if(sysMenu.getParentId().longValue() == 0) {
                trees.add(dfs(sysMenu, list));
            }
        }
        return trees;
    }

    public static SysMenu dfs(SysMenu sysMenu, List<SysMenu> list) {

        sysMenu.setChildren(new ArrayList<SysMenu>());

        for(SysMenu it : list) {
            if(it.getParentId().longValue() == sysMenu.getId()) {
                sysMenu.getChildren().add(dfs(it, list));
            }
        }
        return sysMenu;
    }
}
