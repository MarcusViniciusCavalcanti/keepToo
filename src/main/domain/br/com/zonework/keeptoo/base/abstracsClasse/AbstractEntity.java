package br.com.zonework.keeptoo.base.abstracsClasse;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Vinicius Cavalcanti
 * @version 1.0
 * @since 1.0 12/03/18 project keeptoo
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(name = "created_at") LocalDateTime createdAt;
    private @Column(name = "updated_at") LocalDateTime updatedAt;

    public AbstractEntity() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) return true;
        if (anotherObject == null || getClass() != anotherObject.getClass()) return false;
        AbstractEntity AbstractEntity = (AbstractEntity) anotherObject;
        return Objects.equals(id, AbstractEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
