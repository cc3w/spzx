package com.cc.spzx.manager.service.impl;

import com.cc.spzx.common.exception.ccException;
import com.cc.spzx.manager.mapper.SysMenuMapper;
import com.cc.spzx.manager.service.SysMenuService;
import com.cc.spzx.manager.utils.MenuHelper;
import com.cc.spzx.model.entity.system.SysMenu;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


//    @Override
//    public void deleteByRoleId(Long roleId) {
//        sysMenuMapper.deleteByRoleId(roleId);
//    }

    @Override
    public void removeById(Long id) {
        Integer count = sysMenuMapper.countByParentId(id);

        if(count > 0)
            throw new ccException(ResultCodeEnum.NODE_ERROR);

        sysMenuMapper.removeById(id);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
    }

    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> list = sysMenuMapper.findNodes();

        if(CollectionUtils.isEmpty(list))
            return null;
        //递归实现Element-plus的树形菜单
        List<SysMenu> treeList = MenuHelper.buildTree(list);
        return treeList;
    }
}
