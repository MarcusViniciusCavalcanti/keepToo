package br.com.zonework.keeptoo.contract.valueObject;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractEntity;
import br.com.zonework.keeptoo.contract.entity.Contract;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "balances_contract")
public class BalanceContract extends AbstractEntity {
    private @Column String value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Type type;

    public BalanceContract() { }

    public BalanceContract(String value, Type type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceContract that = (BalanceContract) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public String toString() {
        return "BalanceContract{" +
                "id='" + getId() + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
