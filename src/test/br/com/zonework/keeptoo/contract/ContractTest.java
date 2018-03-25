package br.com.zonework.keeptoo.contract;

import br.com.zonework.keeptoo.contract.builder.ContractBuilder;
import br.com.zonework.keeptoo.contract.entity.Contract;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ContractTest {

    @Test
    public void should_have_return_total_balance_include_addend() {
        Contract contract = ContractBuilder.aContract().build();

        Contract addOne = ContractBuilder.aContract().build();
        Contract addTwo = ContractBuilder.aContract().build();
        Contract addThree = ContractBuilder.aContract().build();

        contract.addAddend(addOne);
        contract.addAddend(addTwo);
        contract.addAddend(addThree);

        BigDecimal total = contract.getBalanceWithAddend();
        Assert.assertThat(total.doubleValue(), CoreMatchers.is(40d));
    }
}
