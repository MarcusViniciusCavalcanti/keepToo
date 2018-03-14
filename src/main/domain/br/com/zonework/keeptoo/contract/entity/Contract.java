package br.com.zonework.keeptoo.contract.entity;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractEntity;
import br.com.zonework.keeptoo.contract.valueObject.BalanceContract;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "contract")
public class Contract extends AbstractEntity {

    private @Column StringProperty number;
    private @Column StringProperty numberTrading;
    private @Column StringProperty description;

    private @Column LocalDate initialDate;
    private @Column LocalDate endDate;

    private BalanceContract balance;

    public Contract() { }
}
