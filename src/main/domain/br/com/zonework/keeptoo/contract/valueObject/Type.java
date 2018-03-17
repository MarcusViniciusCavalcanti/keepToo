package br.com.zonework.keeptoo.contract.valueObject;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "types")
public class Type extends AbstractEntity {
    private @Column String name;
    private @Column String description;

    @OneToMany(orphanRemoval = true, mappedBy = "type")
    private List<BalanceContract> balanceContracts;

    public Type() { }

    public Type(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id='" + getId() + '\'' +
                ", name=" + name +
                ", description='" + description + '\'' +
                '}';
    }
}
