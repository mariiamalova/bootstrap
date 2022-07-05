package com.example.services;

import com.example.dao.RoleDaoImpl;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@Service
public class RoleServiceImpl {
    private final RoleDaoImpl roleDao;

    @Autowired
    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }



    public Collection<Role> getRolesByIds(int[] idRoles){
      return  roleDao.getRolesByIds(idRoles);
    }

}
