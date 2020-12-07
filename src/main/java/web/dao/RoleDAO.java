package web.dao;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    List<Role> getListRoles();
    Role getRoleByName(String name);
}
