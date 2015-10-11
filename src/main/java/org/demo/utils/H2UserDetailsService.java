package org.demo.utils;

import org.demo.entities.User;
import org.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Сервис, реализующий интерфейс UserDetailsService для базы данных H2
 * <p>
 * Created by Selutin_AV on 05.08.2015.
 */
@Service
public class H2UserDetailsService implements UserDetailsService {

    /*
    Autowired для того, чтобы Spring подставил в users
    реализованный интерфейс репозитория юзеров из базы данных
     */
    @Autowired
    IUserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails loadedUser;

        try {
            // Получаем пользователя
            User client = users.findByUsername(username);

            // Проверка всех аттрибутов
            if (client == null)
                throw new UsernameNotFoundException("Invalid username and password");

            Boolean accountNonExpired = client.getExpired() == null ||
                    !client.getExpired().isBefore(LocalDateTime.now());

            Boolean credentialsNonExpired = client.getCredentialsExpired() == null ||
                    !client.getCredentialsExpired().isBefore(LocalDateTime.now());

            // Возвращаем сконвертированного из сущности базы данных в сущность Spring Security пользователя.
            // Spring для этого пользователя проверит соответствие введенного пароля и пароля объекта в базе данных
            loadedUser = new org.springframework.security.core.userdetails.User(
                    client.getUsername(), client.getPassword(),
                    client.isEnable(), accountNonExpired, credentialsNonExpired, !client.isLocked(),
                    client.getRoles());

            // Заглушка для наделения пользователя ролью ROLE_USER в DummyAuthority
//            loadedUser = new org.springframework.security.core.userdetails.User(
//                    client.getUsername(), client.getPassword(),
//                    DummyAuthority.getAuth());

        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        return loadedUser;
    }

    // inner-class для заглушки DummyAuthority
//    static class DummyAuthority implements GrantedAuthority {
//
//        static Collection<GrantedAuthority> getAuth() {
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>(1);
//            grantedAuthorities.add(new DummyAuthority());
//            return grantedAuthorities;
//        }
//
//        @Override
//        public String getAuthority() {
//            return "ROLE_USER";
//        }
//    }
}
