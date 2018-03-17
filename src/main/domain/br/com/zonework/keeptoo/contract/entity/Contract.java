package br.com.zonework.keeptoo.contract.entity;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractEntity;
import br.com.zonework.keeptoo.contract.valueObject.BalanceContract;
import javafx.beans.property.StringProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "contracts")
public class Contract extends AbstractEntity {
    private @Column String number;
    private @Column String numberTrading;
    private @Column String description;

    private @Column LocalDate initialDate;
    private @Column LocalDate endDate;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private BalanceContract balance;

    @ManyToMany
    @JoinTable(name = "contract_addenda",
            joinColumns = @JoinColumn(name = "contract", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "addenda", referencedColumnName = "id"))
    private List<Contract> contractAddenda;

    public Contract() {
        this.contractAddenda = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberTrading() {
        return numberTrading;
    }

    public void setNumberTrading(String numberTrading) {
        this.numberTrading = numberTrading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BalanceContract getBalance() {
        return balance;
    }

    public void setBalance(BalanceContract balance) {
        this.balance = balance;
    }

    public List<Contract> getListAddent() {
        return contractAddenda;
    }

    public void setContractAddenda(List<Contract> contractAddenda) {
        this.contractAddenda = contractAddenda;
    }

    public void addAdend(Contract contract) {
        contractAddenda.add(contract);
    }

    public Boolean isExpired() {
        return this.endDate.isAfter(LocalDate.now());
    }

    public Boolean isAlertExpire() {
        return false;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Contract contract = (Contract) o;
        return Objects.equals(number, contract.number) &&
                Objects.equals(numberTrading, contract.numberTrading);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, numberTrading);
    }


}
