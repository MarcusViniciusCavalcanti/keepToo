package br.com.zonework.keeptoo.builder;

import br.com.zonework.keeptoo.contract.valueObject.BalanceContract;
import br.com.zonework.keeptoo.contract.valueObject.Type;

import java.time.LocalDateTime;

public final class BalanceContractBuilder {
    private BalanceContract balanceContract;

    private BalanceContractBuilder() {
        balanceContract = new BalanceContract();
        balanceContract.setValue("10");
        balanceContract.setType(TypeBuilder.aType().build());
    }

    public static BalanceContractBuilder aBalanceContract() {
        return new BalanceContractBuilder();
    }

    public BalanceContractBuilder withValue(String value) {
        balanceContract.setValue(value);
        return this;
    }

    public BalanceContractBuilder withId(Long id) {
        balanceContract.setId(id);
        return this;
    }

    public BalanceContractBuilder withType(Type type) {
        balanceContract.setType(type);
        return this;
    }

    public BalanceContractBuilder withCreatedAt(LocalDateTime createdAt) {
        balanceContract.setCreatedAt(createdAt);
        return this;
    }

    public BalanceContractBuilder withUpdatedAt(LocalDateTime updatedAt) {
        balanceContract.setUpdatedAt(updatedAt);
        return this;
    }

    public BalanceContract build() {
        return balanceContract;
    }
}
