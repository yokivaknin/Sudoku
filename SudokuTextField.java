package sample;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

// SudokuTextField extends TextField and adds the attributes
// _isValCommitted which remembers if a valid number has already been to the SudokuTextField
// and _currentNum which saves the last valid number or 0 in case it is empty.
public class SudokuTextField extends TextField {
    private final int MAX_VAL = 9;
    private final int MIN_VAL = 1;
    private boolean _isValCommitted = false;
    private int _currentNum = 0;


    public void setCurrentNumberZero (){
        _currentNum = 0;
        _isValCommitted = false;
        this.clear();
    }

    //The function decides if it is necessary to initialize the cell or re-enter previous information.
    public void clearOrLeave (){
        if(_currentNum != 0){
            this.setText(Integer.toString(_currentNum));
            _isValCommitted = true;
        }else {
            this.clear();
            _isValCommitted = false;
        }
    }

    public int getCurrentNum(){
        return _currentNum;
    }

    //The constructor registers for the enter click event and checks accordingly for input validity.
    public SudokuTextField() {
        this.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if(this.getText().equals("")){//if the user just want to clear the SudokuTextField.
                    this.setCurrentNumberZero();
                }else {
                    try {//convert the text to integer and set the attributes accordingly.
                        int temp = Integer.parseInt(this.getText());
                        if(temp >= MIN_VAL && temp <= MAX_VAL) {
                            _isValCommitted = true;
                            _currentNum = temp;
                        }else {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("game rules");
                            alert.setHeaderText("Rules are rules.");
                            alert.setContentText("The rules shall not be violated! " + "invalid number: " + this.getText());
                            alert.showAndWait().get();
                            this.clearOrLeave();
                        }
                    }catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("game rules");
                        alert.setHeaderText("Rules are rules.");
                        alert.setContentText("The rules shall not be violated! " + "Invalid expression entered: " + this.getText());
                        alert.showAndWait().get();
                        this.clearOrLeave();
                    }
                }
            }
        });

        this.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {//lost focus without pressing enter.
                if(_isValCommitted == false){
                    this.clear();
                }else {
                    this.setText(Integer.toString(_currentNum));
                }


                // Deselect on focus loss
                this.deselect();
            }
        });


    }
}
