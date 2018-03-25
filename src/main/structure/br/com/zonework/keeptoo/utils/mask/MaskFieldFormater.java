/*
https://github.com/gbfragoso/MaskedTextField/blob/master/src/org/fxutils/maskedtextfield/MaskedTextField.java
 */
package br.com.zonework.keeptoo.utils.mask;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MaskFieldFormater extends TextField {
    private StringProperty mask;
    private StringProperty placeholder;
    private StringProperty plainText;

    private static final char MASK_ESCAPE = '\'';
    private static final char MASK_NUMBER = '#';
    private static final char MASK_CHARACTER = '?';
    private static final char MASK_HEXADECIMAL = 'H';
    private static final char MASK_UPPER_CHARACTER = 'U';
    private static final char MASK_LOWER_CHARACTER = 'L';
    private static final char MASK_CHAR_OR_NUM = 'A';
    private static final char MASK_ANYTHING = '*';

    private ArrayList<Mask> semanticMask = new ArrayList<>();
    private int maskLength;
    private int semanticMaskLength;

    public MaskFieldFormater(){
        this.mask = new SimpleStringProperty(this, "mask", "");
        this.placeholder = new SimpleStringProperty(this,"placeholder"," ");
        this.plainText = new SimpleStringProperty(this,"plaintext","");
        start();
    }

    public MaskFieldFormater(String mask){
        this.mask = new SimpleStringProperty(this, "mask", mask);
        this.placeholder = new SimpleStringProperty(this,"placeholder"," ");
        this.plainText = new SimpleStringProperty(this,"plaintext","");
        start();
    }

    public MaskFieldFormater(String mask, char placeHolder){
        this.mask = new SimpleStringProperty(this, "mask", mask);
        this.placeholder = new SimpleStringProperty(this,"placeholder",String.valueOf(placeHolder));
        this.plainText = new SimpleStringProperty(this,"plaintext","");
        start();
    }

    public final String getMask(){
        return mask.get();
    }

    public final String getPlaceholder(){
        return placeholder.get();
    }

    public final String getPlainText(){
        return plainText.get();
    }

    public final void setMask(String m){
        mask.set(m);
        maskLength = m.length();
        buildSemanticMask();
        updateSemanticMask();
    }

    public final void setPlaceholder(String holder){
        placeholder.set(holder);
        resetSemanticMask();
        updateSemanticMask();
    }

    public final void setPlainText(String text){
        if(text == null){
            plainText.set("");
        }else{
            plainText.set(text);
        }
        updateSemanticMask();
    }

    public StringProperty maskProperty(){
        return mask;
    }

    public StringProperty placeholderProperty(){
        return placeholder;
    }

    public StringProperty plainTextProperty(){
        return plainText;
    }

    /**
     * Método de configuração
     */
    private void start(){
        maskLength = getMask().length();
        buildSemanticMask();
        setText(allSemanticValuesToString());

        focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Platform.runLater(() -> {
                int pos = firstPlaceholderPosition();
                selectRange(pos, pos);
                positionCaret(pos);
            });
        });
    }

    private void buildSemanticMask(){
        String newMask = getMask();
        for (int i = 0; i < maskLength; i++){
            char c = newMask.charAt(i);
            if(c == MASK_ESCAPE){
                semanticMask.add(new Mask(newMask.charAt(i+1), newMask.charAt(i+1), true, false));
                i++;
            }else{
                if(isLiteral(c)){
                    semanticMask.add(new Mask(c, c, true, false));
                }else{
                    semanticMask.add(new Mask(getPlaceholder().charAt(0), c, false, true));
                }
            }
        }
        semanticMaskLength = semanticMask.size();
    }

    private void resetSemanticMask(){
        char p = getPlaceholder().charAt(0);
        semanticMask.stream()
                .filter(e -> !e.isLiteral())
                .forEach(e -> {
                    e.setValue(p);
                    e.setPlaceholder(true);
                });
    }

    public void updateSemanticMask(){
        resetSemanticMask();

        String newPlainText = getPlainText();

        int plainCharCounter = 0;
        int plainTextSize = newPlainText.length();

        char[] textPlain = newPlainText.toCharArray();

        for(int i = 0; i < semanticMaskLength; i++){
            Mask m = semanticMask.get(i);

            if(m.isPlaceholder() && plainTextSize > plainCharCounter){
                if(isCorrect(m.getMask(), textPlain[plainCharCounter])){

                    m.setValue(textPlain[plainCharCounter]);
                    m.setPlaceholder(false);

                    if(m.getMask() == MASK_LOWER_CHARACTER){
                        m.setValue(Character.toLowerCase(textPlain[plainCharCounter]));
                    }else if(m.getMask() == MASK_UPPER_CHARACTER){
                        m.setValue(Character.toUpperCase(textPlain[plainCharCounter]));
                    }

                }else{
                    newPlainText = newPlainText.substring(0, plainCharCounter) + newPlainText.substring(plainCharCounter+1);
                }
                plainCharCounter++;
            }
        }

        setText(allSemanticValuesToString());

        if (plainTextSize > plainCharCounter)
            newPlainText = newPlainText.substring(0, plainCharCounter);

        if (!newPlainText.equals(getPlainText())){
            setPlainText(newPlainText);
        }
    }

    private String allSemanticValuesToString(){
        return semanticMask.stream().map(e -> String.valueOf(e.getValue())).collect(Collectors.joining());
    }

    private boolean isLiteral(char c){
        return (c != MASK_ANYTHING &&
                c != MASK_CHARACTER &&
                c != MASK_ESCAPE &&
                c != MASK_NUMBER &&
                c != MASK_CHAR_OR_NUM &&
                c != MASK_HEXADECIMAL &&
                c != MASK_LOWER_CHARACTER &&
                c != MASK_UPPER_CHARACTER);
    }

    public boolean isCorrect(char m, char value){
        switch(m){
            case MASK_ANYTHING: return true;
            case MASK_CHARACTER: return Character.isLetter(value);
            case MASK_NUMBER: return Character.isDigit(value);
            case MASK_CHAR_OR_NUM: return Character.isLetterOrDigit(value);
            case MASK_HEXADECIMAL: return Pattern.matches("[0-9a-fA-F]", String.valueOf(value));
            case MASK_LOWER_CHARACTER: return Character.isLetter(value);
            case MASK_UPPER_CHARACTER: return Character.isLetter(value);
        }
        return false;
    }

    public int firstPlaceholderPosition(){
        for(int i = 0; i < semanticMaskLength; i++){
            if(semanticMask.get(i).isPlaceholder()){
                return i;
            }
        }
        return -1;
    }

    private int maskPositionToPlaintextPosition(int pos){
        int count = 0;

        for (int i = 0; i < semanticMaskLength && i < pos; i++){
            Mask m = semanticMask.get(i);
            if (!(m.isPlaceholder() || m.isLiteral())){
                count++;
            }
        }

        return count;
    }

    private int plaintextPositionToMaskPosition(int pos){
        int countLiterals = 0, countNonLiterals = 0;

        for (int i = 0; i < semanticMaskLength && countNonLiterals < pos; i++){
            Mask m = semanticMask.get(i);
            if (m.isLiteral()){
                countLiterals++;
            }else {
                countNonLiterals++;
            }
        }

        return countLiterals + countNonLiterals;
    }

    @Override
    public void replaceText(int start, int end, String newText){

        int plainStart = maskPositionToPlaintextPosition(start);

        String oldPlainText = getPlainText();
        String newString = oldPlainText.substring(0, plainStart) + newText + oldPlainText.substring(plainStart, oldPlainText.length());
        setPlainText(newString);

        int newPos = plaintextPositionToMaskPosition(newString.lastIndexOf(newText) + newText.length());
        selectRange(newPos, newPos);
    }

    @Override
    public void replaceSelection(String string){
        IndexRange range = getSelection();
        if(string.equals("")){
            deleteText(range.getStart(), range.getEnd());
        }else{
            replaceText(range.getStart(), range.getEnd(), string);
        }
    }

    @Override
    public void deleteText(int start, int end){

        int plainStart = maskPositionToPlaintextPosition(start);
        int plainEnd = maskPositionToPlaintextPosition(end);

        StringBuilder newString = new StringBuilder(getPlainText());
        newString.delete(plainStart, plainEnd);
        setPlainText(newString.toString());

        selectRange(start, start);
    }

    @Override
    public void clear(){
        setPlainText("");
    }
}