package br.com.zonework.keeptoo.contract.builder;

import br.com.zonework.keeptoo.contract.entity.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class ContractBuilder {
    private Contract contract;

    private ContractBuilder() {
        contract = new Contract();

        contract.setNumber("0000");
        contract.setDescription("default");
        contract.setNumberTrading("default");
        contract.setBalance(new BigDecimal(10.00));
    }

    public static ContractBuilder aContract() {
        return new ContractBuilder();
    }

    public ContractBuilder withId(Long id) {
        contract.setId(id);
        return this;
    }



    public ContractBuilder withNumber(String number) {
        contract.setNumber(number);
        return this;
    }

    public ContractBuilder withNumberTrading(String numberTrading) {
        contract.setNumberTrading(numberTrading);
        return this;
    }

    public ContractBuilder withDescription(String description) {
        contract.setDescription(description);
        return this;
    }

    public ContractBuilder withInitialDate(LocalDate initialDate) {
        contract.setInitialDate(initialDate);
        return this;
    }

    public ContractBuilder withEndDate(LocalDate endDate) {
        contract.setEndDate(endDate);
        return this;
    }

    public ContractBuilder withBalance(BigDecimal balance) {
        contract.setBalance(balance);
        return this;
    }

    public ContractBuilder withContractAddenda(List<Contract> contractAddenda) {
        contract.setContractAddenda(contractAddenda);
        return this;
    }

    public Contract build() {
        return contract;
    }

    public ContractBuilder withParent(Contract parent) {
        contract.setParent(parent);
        return this;
    }
}
