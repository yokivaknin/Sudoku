package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

//The controller controls the legality of the input (according to the rules of the game) by registering to the enter event
// and by registering to the gridPan change event.
// In addition controls the set and clear buttons.
public class Controller {
    private final int GRID_SIZE = 9;
    private SudokuTextField[][] text = new SudokuTextField[GRID_SIZE][GRID_SIZE];
    private boolean _isSetPressed = false;

    @FXML
    private GridPane Grid;

    public void initialize() {
        //setting the game.
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                text[x][y] = new SudokuTextField();
                Grid.add(text[x][y], x, y);
                text[x][y].setMaxWidth(Double.MAX_VALUE);
                text[x][y].setMaxHeight(Double.MAX_VALUE);
                text[x][y].setStyle("-fx-text-inner-color: black; -fx-alignment: center;");
                text[x][y].setFont(Font.font("Arial", 30));

                //coloring gray part of the boxes.
                int boxNumber = getBoxNum(x,y);
                if(boxNumber == 1 || boxNumber == 3 || boxNumber == 5 || boxNumber == 7 || boxNumber == 9)
                    text[x][y].setStyle("-fx-background-color: lightgray; -fx-alignment: center;");

                //enter event
                Grid.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        Node focusedNode = Grid.getScene().getFocusOwner(); // Get the focused node
                        if (focusedNode instanceof SudokuTextField) {
                            // Get the column and row indexes of the focused node in the GridPane
                            int columnIndex = GridPane.getColumnIndex(focusedNode);
                            int rowIndex = GridPane.getRowIndex(focusedNode);
                            int num =  ((SudokuTextField)focusedNode).getCurrentNum();

                            //Checks legality only if an empty string was not received for resetting the cell.
                            if(!((SudokuTextField)focusedNode).getText().equals("")){
                                if(!(this.rowValidate(num,columnIndex,rowIndex) &&
                                        this.columnValidate(num,columnIndex,rowIndex) &&
                                        this.boxValidate(num,columnIndex,rowIndex))){

                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("game rules");
                                    alert.setHeaderText("Rules are rules.");
                                    alert.setContentText("The rules shall not be violated!");
                                    alert.showAndWait().get();

                                    ((SudokuTextField)focusedNode).clear();
                                    ((SudokuTextField)focusedNode).setCurrentNumberZero();
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public boolean rowValidate(int num, int columnIndex, int rowIndex){
        boolean toReturn = true;
        for(int i = 0; i< GRID_SIZE; i++){
            if(text[columnIndex][i].getCurrentNum() == num && i!= rowIndex)
                toReturn = false;
        }
        return toReturn;
    }

    public boolean columnValidate(int num, int columnIndex, int rowIndex){
        boolean toReturn = true;
        for(int i = 0; i< GRID_SIZE; i++){
            if(text[i][rowIndex].getCurrentNum() == num && i!= columnIndex)
                toReturn = false;
        }
        return toReturn;
    }

    public boolean boxValidate(int num, int columnIndex, int rowIndex){
        int boxNum = this.getBoxNum(columnIndex, rowIndex);
        switch (boxNum){
            case 1:
                if(!this.validateSpecificBox(0,0,columnIndex,rowIndex,num))
                    return false;
                break;
            case 2:
                if(!this.validateSpecificBox((GRID_SIZE/3),0,columnIndex,rowIndex,num))
                    return false;
                break;
            case 3:
                if(!this.validateSpecificBox(2*(GRID_SIZE/3),0,columnIndex,rowIndex,num))
                    return false;
                break;
            case 4:
                if(!this.validateSpecificBox(0,(GRID_SIZE/3),columnIndex,rowIndex,num))
                    return false;
                break;
            case 5:
                if(!this.validateSpecificBox((GRID_SIZE/3),(GRID_SIZE/3),columnIndex,rowIndex,num))
                    return false;
                break;
            case 6:
                if(!this.validateSpecificBox(2*(GRID_SIZE/3),(GRID_SIZE/3),columnIndex,rowIndex,num))
                    return false;
                break;
            case 7:
                if(!this.validateSpecificBox(0,2*(GRID_SIZE/3),columnIndex,rowIndex,num))
                    return false;
                break;
            case 8:
                if(!this.validateSpecificBox((GRID_SIZE/3),2*(GRID_SIZE/3),columnIndex,rowIndex,num))
                    return false;
                break;
            case 9:
                if(!this.validateSpecificBox(2*(GRID_SIZE/3),2*(GRID_SIZE/3),columnIndex,rowIndex,num))
                    return false;
                break;
        }

        return true;
    }

    //Receives specific indexes of a box and checks legality.
    public boolean validateSpecificBox(int startX, int startY, int columnIndex, int rowIndex, int num){
        boolean toReturn = true;
        for (int x = startX; x< startX+3; x++){
            for (int y = startY; y< startY+3; y++){
                if(x != columnIndex || y != rowIndex){
                    if(text[x][y].getCurrentNum() == num)
                        toReturn = false;
                }
            }
        }
        return toReturn;
    }

    public int getBoxNum(int columnIndex, int rowIndex){
        if(columnIndex >=0 && columnIndex<=2){
            if(rowIndex>=0 && rowIndex<=2)
                return 1;
            else if(rowIndex>=3 && rowIndex<=5)
                return 4;
            else
                return 7;
        }else if(columnIndex >=3 && columnIndex<=5){
            if(rowIndex>=0 && rowIndex<=2)
                return 2;
            else if(rowIndex>=3 && rowIndex<=5)
                return 5;
            else
                return 8;
        }else {
            if(rowIndex>=0 && rowIndex<=2)
                return 3;
            else if(rowIndex>=3 && rowIndex<=5)
                return 6;
            else
                return 9;
        }
    }

    @FXML
    void setBord(ActionEvent event) {
        if(_isSetPressed == false){
            _isSetPressed = true;
            for(int i = 0; i < GRID_SIZE; i++){
                for(int j = 0; j < GRID_SIZE; j++){
                    if(text[i][j].getCurrentNum() != 0){

                        int boxNumber = getBoxNum(i,j);
                        if(boxNumber == 1 || boxNumber == 3 || boxNumber == 5 || boxNumber == 7 || boxNumber == 9)
                            text[i][j].setStyle("-fx-background-color: lightgray;-fx-text-inner-color: blue;-fx-alignment: center;");
                        else
                            text[i][j].setStyle("-fx-text-inner-color: blue;-fx-alignment: center;");
                        text[i][j].setDisable(true);
                    }
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("game structure.");
            alert.setHeaderText("Notice!");
            alert.setContentText("You have already set the game. To change the bord game press clear.");
            alert.showAndWait().get();
        }
    }

    @FXML
    void clearBord(ActionEvent event) {
        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                text[i][j].setDisable(false);
                text[i][j].clear();
                text[i][j].setCurrentNumberZero();
                _isSetPressed = false;

                int boxNumber = getBoxNum(i,j);
                if(boxNumber == 1 || boxNumber == 3 || boxNumber == 5 || boxNumber == 7 || boxNumber == 9)
                    text[i][j].setStyle("-fx-background-color: lightgray;");
            }
        }
    }

}









