# Sudoku Game

## Description
This JavaFX application implements a Sudoku game with features to validate user input according to Sudoku rules. It provides a graphical interface for playing Sudoku and includes functionalities such as setting the initial board configuration, clearing the board, and validating user input.

## Components
The project consists of the following components:
- **Main**: The main class that launches the JavaFX application and sets up the primary stage.
- **Controller**: The controller class responsible for managing user interactions, input validation, and game logic.
- **SudokuTextField**: An extension of TextField class tailored for handling Sudoku input. It includes validation logic for user input.
- **sample.fxml**: The FXML file defining the UI layout of the Sudoku game.

## Features
- **Input Validation**: Validates user input according to Sudoku rules, ensuring that numbers are placed correctly within rows, columns, and subgrids.
- **Setting Initial Board**: Allows the user to set the initial configuration of the Sudoku board by entering predefined numbers.
- **Clearing Board**: Clears the board, allowing the user to start over or change the initial configuration.
- **Visual Feedback**: Provides visual feedback to the user by highlighting invalid input and enforcing correct placement of numbers.

## Usage
1. Launch the application.
2. Set the initial board configuration by entering numbers in the desired cells and pressing enter after changing eatch cell.
3. Press the "Set Board" button to finalize the initial configuration.
4. Fill in the remaining empty cells following Sudoku rules.
5. Press Enter after entering a number to validate the input.
6. Use the "Clear Board" button to reset the board and start over if needed.

## Dependencies
- JavaFX: Java library for building graphical user interfaces.

## Instructions for Building and Running
1. Ensure Java Development Kit (JDK) is installed on your system.
2. Set up a Java development environment (e.g., IntelliJ IDEA, Eclipse).
3. Import the project into your IDE.
4. Resolve any dependencies if needed.
5. Run the Main class to launch the Sudoku game.

## Contributors
- yoki vaknin
