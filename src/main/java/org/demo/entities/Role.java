package org.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.demo.constants.RoleConst;
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
@Table(name = RoleConst.SQL_ROLES)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = RoleConst.SQL_ID, nullable = false, updatable = false)
    private long id;

    @Column(name = RoleConst.SQL_ROLE, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @JsonIgnore
    @ManyToMany(mappedBy = RoleConst.SQL_ROLES)
    private Set<User> users;

    public Role() {
    }

    public Role(RoleEnum role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
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
