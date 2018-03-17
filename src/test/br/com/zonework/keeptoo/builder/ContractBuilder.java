package br.com.zonework.keeptoo.builder;

import br.com.zonework.keeptoo.contract.entity.Contract;
import br.com.zonework.keeptoo.contract.valueObject.BalanceContract;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class ContractBuilder {
    private Contract contract;

    private ContractBuilder() {
        contract = new Contract();

        contract.setNumber("0000");
        contract.setDescription("description");
        contract.setNumberTrading("pregão");
        contract.setBalance(BalanceContractBuilder.aBalanceContract().build());
        contract.setInitialDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusMonths(12));
    }

    public static ContractBuilder aContract() {
        return new ContractBuilder();
    }

    public ContractBuilder withId(Long id) {
        contract.setId(id);
        return this;
    }

    public ContractBuilder withCreatedAt(LocalDateTime createdAt) {
        contract.setCreatedAt(createdAt);
        return this;
    }

    public ContractBuilder withNumber(String number) {
        contract.setNumber(number);
        return this;
    }

    public ContractBuilder withUpdatedAt(LocalDateTime updatedAt) {
        contract.setUpdatedAt(updatedAt);
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

    public ContractBuilder withBalance(BalanceContract balance) {
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

}
