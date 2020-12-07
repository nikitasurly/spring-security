package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDAO;
import web.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public List<Role> getListRoles() {
        return roleDAO.getListRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}
