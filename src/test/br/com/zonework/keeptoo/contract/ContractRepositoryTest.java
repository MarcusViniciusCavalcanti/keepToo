package br.com.zonework.keeptoo.contract;

import br.com.zonework.keeptoo.builder.ContractBuilder;
import br.com.zonework.keeptoo.contract.entity.Contract;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ContractRepositoryTest {
    private ContractRepository repository;
    @Before
    public void setUp() throws Exception {
        repository = new ContractRepository();
    }

    @Test
    public void should_have_persist_contract() {
        Contract contract = ContractBuilder.aContract().build();
        repository.save(contract);
    }

    @Test
    public void should_have_return_all_contracts() {
        List<Contract> contracts = repository.findAll();

        contracts.forEach(System.out::println);
    }
}
