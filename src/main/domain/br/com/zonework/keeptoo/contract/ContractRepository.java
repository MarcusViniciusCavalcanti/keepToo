package br.com.zonework.keeptoo.contract;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractRepository;
import br.com.zonework.keeptoo.contract.entity.Contract;
import org.hibernate.Session;
import org.hibernate.query.criteria.internal.OrderImpl;

import javax.persistence.criteria.*;
import java.util.List;

public class ContractRepository extends AbstractRepository<Contract> {
    public ContractRepository() {
        super(Contract.class);
    }


    public List<Contract> findContractWithoutParent() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Contract> criteria = builder.createQuery(Contract.class);
        Root<Contract> contractRoot = criteria.from(Contract.class);

        criteria.select(contractRoot).where(builder.isNull(contractRoot.get("parent")));
        List<Contract> entities = session.createQuery(criteria).getResultList();

        session.close();

        return entities;
    }
}
