package br.com.zonework.keeptoo.ui;

import br.com.zonework.keeptoo.actions.AutoCompleteHandler;
import br.com.zonework.keeptoo.contract.ContractRepository;
import br.com.zonework.keeptoo.contract.entity.Contract;
import javafx.scene.control.ComboBox;

import java.util.List;

public class ComboAutoComplete {

    public static ComboBox<Contract> getComboBoxContract() {
        ComboBox<Contract> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("combo-box");
        ContractRepository repository = new ContractRepository();

        List<Contract> contracts = repository.findContractWithoutParent();
        AutoCompleteHandler<Contract> autoCompleteHandler = new AutoCompleteHandler<>(comboBox, contracts);

        return comboBox;
    }
}
