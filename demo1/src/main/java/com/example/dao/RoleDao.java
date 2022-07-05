package com.example.dao;

import com.example.model.Role;
import com.example.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoleDao {
    Collection<Role> getRolesByIds(int[] idRoles);

}
