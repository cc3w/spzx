package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    Integer countByParentId(Long id);

    void removeById(Long id);


    List<SysMenu> selectListByUserId(Long id);

    SysMenu selectById(Long parentId);

    void updateSysRoleMenuIsHalf(Long id);

}
