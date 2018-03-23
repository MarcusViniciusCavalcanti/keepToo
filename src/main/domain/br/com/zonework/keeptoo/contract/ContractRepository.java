package br.com.zonework.keeptoo.contract;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractRepository;
import br.com.zonework.keeptoo.contract.entity.Contract;

public class ContractRepository extends AbstractRepository<Contract> {
    public ContractRepository() {
        super(Contract.class);
    }


}
