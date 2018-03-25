package br.com.zonework.keeptoo.controller;

import br.com.zonework.keeptoo.contract.builder.ContractBuilder;
import br.com.zonework.keeptoo.contract.entity.Contract;
import br.com.zonework.keeptoo.service.ContractService;
import br.com.zonework.keeptoo.ui.ComboAutoComplete;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ContractAddController implements Initializable {
    @FXML private ImageView close;
    @FXML private TextField contractNumber, tradingNumber, value;
    @FXML private TextArea description;
    @FXML private DatePicker dateInitial, dateEnd;
    @FXML private Label lbDateFinalAlert, lbValueAlert;
    @FXML private Tooltip errorDate;
    @FXML private ToggleGroup groupAddend;
    @FXML private RadioButton addendYes;
    @FXML private GridPane form;
    private ComboBox<Contract> box;
    private boolean withAddend;
    private Label label;
    private boolean isValidForm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.label = new Label("Selecione o contrato");
        this.box = ComboAutoComplete.getComboBoxContract();

        this.label.setVisible(false);
        this.box.setVisible(false);

        this.form.add(label, 0, 7);
        this.form.add(box, 1, 7);

        this.errorDate.showingProperty().addListener((observable, oldValue, newValue) -> isValidDate());
        this.dateEnd.focusedProperty().addListener((observable, oldValue, newValue) -> isValidDate());
        this.groupAddend.selectedToggleProperty().addListener(this::isAddend);
        this.value.focusedProperty().addListener((observable, oldValue, newValue) -> isValidValue());
    }

    @FXML
    public void onClose() {
        Window stage = close.getScene().getWindow();
        stage.hide();
    }

    @FXML
    public void onSave() {
        Contract contract = ContractBuilder.aContract()
                .withNumber(this.contractNumber.getText())
                .withNumberTrading(this.tradingNumber.getText())
                .withBalance(new BigDecimal(this.value.getText().replace(".", "").replace(",", "")))
                .withDescription(this.description.getText())
                .withInitialDate(this.dateInitial.getValue())
                .withEndDate(this.dateEnd.getValue())
                .build();

        if(withAddend)  {
            ObservableList<Contract> items = this.box.getItems();
            Contract parent = items.get(this.box.getSelectionModel().getSelectedIndex());
            contract.setParent(parent);
        }

        ContractService.saveContract(contract);

        this.clearFields();
    }

    private void clearFields() {
        this.contractNumber.setText("");
        this.tradingNumber.setText("");
        this.value.setText("");
        this.description.setText(" ");

        this.dateInitial.setValue(null);
        this.dateEnd.setValue(null);
    }

    @FXML
    public void isValidDate() {
        if(this.dateInitial.getValue() != null && this.dateEnd.getValue() != null) {
            LocalDate end = this.dateEnd.getValue();
            boolean after = this.dateInitial.getValue().isAfter(end);

            if (after) {
                this.lbDateFinalAlert.setStyle("-fx-text-fill: red");
                this.lbDateFinalAlert.getGraphic().setVisible(true);

                this.errorDate.setText("a data final do contrato deve ser posterior a data inicial.");
            } else {
                this.lbDateFinalAlert.getGraphic().setVisible(false);
                this.lbDateFinalAlert.setStyle("-fx-text-fill: #2962ff");
                this.errorDate.setText("Digite a data final do contrato");
            }
        }
    }

    private void isAddend(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
        if(observableValue.getValue().equals(addendYes)) {
            box.setVisible(true);
            label.setVisible(true);
            this.withAddend = true;
        } else {
            label.setVisible(false);
            box.setVisible(false);
            this.withAddend = false;
        }
    }

    private void isValidValue() {
        if(this.value.getText().isEmpty()) {
            this.lbDateFinalAlert.setStyle("-fx-text-fill: red");
            this.lbDateFinalAlert.getGraphic().setVisible(true);

            this.errorDate.setText("a data final do contrato deve ser posterior a data inicial.");
            this.isValidForm = false;
        } else {
            this.isValidForm = true;
        }


    }

}
