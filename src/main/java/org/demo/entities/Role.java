package org.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.demo.constants.EntitiesConst;
import org.demo.constants.RolesEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность - Роль
 *
 * @author Selutin_AV
 * @since 14.08.2015 12:39
 */
@Entity
@Table(name = EntitiesConst.SQL_ROLES)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @JsonIgnore
    @ManyToMany(mappedBy = EntitiesConst.SQL_ROLES)
    private Set<User> users;

    public Role() {
    }

    public Role(RolesEnum role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getRole().name();
    }
}
