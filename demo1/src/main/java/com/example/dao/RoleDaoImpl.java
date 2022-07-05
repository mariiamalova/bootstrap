package com.example.dao;

import com.example.model.Role;
import com.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Role> getRolesByIds(int[] idRoles) {
        Collection<Role> roles = new HashSet<>();

        for(int role: idRoles) {
         roles.addAll(entityManager.createQuery("select r from Role r where r.id =: role").setParameter("role", role).getResultList());
        }
        return roles;
    }


}
