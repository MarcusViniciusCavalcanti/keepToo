package br.com.zonework.keeptoo.actions;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractEntity;
import br.com.zonework.keeptoo.contract.entity.Contract;
import com.sun.istack.internal.NotNull;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class AutoCompleteHandler<T> implements EventHandler<KeyEvent> {
    private ComboBox<T> comboBox;
    private boolean moveCaretToPos = false;
    private int caretPos;
    private List<T> entities;

    public AutoCompleteHandler(@NotNull final ComboBox<T> comboBox, @NotNull List<T> entities) {
        this.comboBox = comboBox;
        this.entities = entities;

        this.comboBox.setEditable(true);
        this.comboBox.getEditor().getStyleClass().add("combo-box-editor");

        this.comboBox.setOnKeyPressed(event -> comboBox.hide());
        this.comboBox.setOnKeyReleased(this);
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else {
            if (event.getCode() != KeyCode.DOWN) {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                }
            } else {
                if(!comboBox.isShowing()) {
                    comboBox.show();
                }
                caretPos = -1;
                moveCaret(comboBox.getEditor().getText().length());
                return;
            }
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }

        String search = comboBox.getEditor().getText();

        ObservableList<T> list = FXCollections.observableArrayList();

        if(search.length() > 2)
        {
            list = FXCollections.observableArrayList(this.entities);
        }

        comboBox.setItems(list);
        comboBox.getEditor().setText(search);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(search.length());
        if(!list.isEmpty()) {
            comboBox.show();
        }else{
            comboBox.hide();
        }
    }

    private void moveCaret(int textLength) {
        if(caretPos == -1) {
            comboBox.getEditor().positionCaret(textLength);
        } else {
            comboBox.getEditor().positionCaret(caretPos);
        }
        moveCaretToPos = false;
    }
}
