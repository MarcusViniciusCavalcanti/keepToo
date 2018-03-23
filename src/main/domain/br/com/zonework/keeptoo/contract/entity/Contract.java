package br.com.zonework.keeptoo.contract.entity;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;
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

    private @Column BigDecimal balance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private Contract parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Contract> getListAddent() {
        return contractAddenda;
    }

    public void setContractAddenda(List<Contract> contractAddenda) {
        this.contractAddenda = contractAddenda;
    }

    public void addAddend(Contract contract) {
        contractAddenda.add(contract);
    }

    public Contract getParent() {
        return parent;
    }

    public void setParent(Contract parent) {
        this.parent = parent;
    }

    public BigDecimal getBalanceWithAddend() {
        if(this.contractAddenda.isEmpty())
            return this.getBalance();

        return this.contractAddenda.stream()
                .map(Contract::getBalance)
                .reduce(this.getBalance(), BigDecimal::add);
    }

    @PrePersist
    @PreUpdate
    private void parent() {
        if(!this.contractAddenda.isEmpty()) {
            contractAddenda.forEach(contract -> contract.setParent(this));
        }
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
