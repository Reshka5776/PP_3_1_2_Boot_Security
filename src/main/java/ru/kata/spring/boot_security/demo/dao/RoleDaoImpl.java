package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }


    @Override
    public Set<Role> getAllRoles(Set<String> roles) {
        return new HashSet<>(entityManager.createQuery("select role from Role role where role.name =:roleName", Role.class)
                .setParameter("roleName", roles)
                .getResultList());
    }

    @Override
    public List<User> getAllUserRoles() {
        return entityManager.createQuery("FROM Role").getResultList();
    }

    //@Transactional
    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }


    @Override
    public void update(Role updatedRole) {
        entityManager.merge(updatedRole);

    }


    @Override
    public void removeRole(long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }


}
