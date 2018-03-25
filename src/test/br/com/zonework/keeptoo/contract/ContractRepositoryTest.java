package br.com.zonework.keeptoo.contract;

import br.com.zonework.keeptoo.contract.entity.Contract;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.List;

public class ContractRepositoryTest {


    @Transactional
    @Test
    public void should_return_contract_without_parent() {
        ContractRepository repository = new ContractRepository();
        List<Contract> contracts = repository.findContractWithoutParent();

        contracts.forEach(System.out::println);
    }

}
