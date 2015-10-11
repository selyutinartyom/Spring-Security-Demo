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

    public AuthEntity() {
    }

    public AuthEntity(LocalDateTime expired) {
        this.expired = expired;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }
}
