package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.util.*;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userDAO.getUserByLogin(login);

        Set<GrantedAuthority> grantedAuth = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuth.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuth);
    }

}
