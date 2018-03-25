package br.com.zonework.keeptoo.service;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractRepository;
import br.com.zonework.keeptoo.contract.ContractRepository;
import br.com.zonework.keeptoo.contract.entity.Contract;

public class ContractService {
    private static final AbstractRepository<Contract> REPOSITORY = new ContractRepository();

    public static void saveContract(Contract contract) {
        REPOSITORY.save(contract);
    }
}
