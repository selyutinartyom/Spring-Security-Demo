package org.demo.entities;

import org.demo.constants.AbstractConst;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Абстрактна сущность
 *
 * @author Secundus
 * @since 09.10.2015 00:09
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = AbstractConst.SQL_ID, nullable = false, updatable = false)
    protected long id;

    @Column(name = AbstractConst.SQL_ENABLE, nullable = false)
    protected boolean enable = false;

    @Column(name = AbstractConst.SQL_DESC)
    protected String desc;

    public AbstractEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
