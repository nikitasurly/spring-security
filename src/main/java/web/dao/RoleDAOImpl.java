package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getListRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery(
                "select r from Role r where r.name = :name",
                Role.class)
                .setParameter("name", name).getSingleResult();
    }
}
