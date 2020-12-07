package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByLogin(String login) {
        return entityManager.createQuery(
                "select u from User u where u.login = :login",
                User.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User find(Long id) {
        return entityManager.find(User.class, id);
    }
}
