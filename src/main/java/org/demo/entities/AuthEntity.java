package org.demo.entities;

import org.demo.constants.AbstractConst;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Абстрактная сущность аутентификации
 *
 * @author Secundus
 * @since 11.10.2015 15:14
 */
@MappedSuperclass
public abstract class AuthEntity extends AbstractEntity {

    /**
     * Дата истечения действия аккаунта
     */
    @Column(name = AbstractConst.SQL_EXPIRED)
    protected LocalDateTime expired; // @see http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/

    /**
     * Дата истечения действия полномочий (пароля)
     */
    @Column(name = AbstractConst.SQL_CREDENTIALS_EXPIRED)
    protected LocalDateTime credentialsExpired;

    /**
     * Признак блокировки аккаунта
     */
    @Column(name = AbstractConst.SQL_LOCKED, nullable = false)
    protected boolean locked;

    public AuthEntity() {
    }

    public AuthEntity(LocalDateTime expired, LocalDateTime credentialsExpired, boolean locked) {
        this.expired = expired;
        this.credentialsExpired = credentialsExpired;
        this.locked = locked;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(LocalDateTime credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
}
