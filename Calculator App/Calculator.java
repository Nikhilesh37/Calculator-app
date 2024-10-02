import javax.swing.*; // Import Swing components
import java.awt.*; // Import AWT components
import java.awt.event.ActionEvent; // Import AWT event class for action events
import java.awt.event.ActionListener; // Import AWT event listener for handling action events

public class Calculator {
    private JFrame frame; // Declare JFrame for the main window
    private JTextField display; // Declare JTextField for the display
    private JPanel panel; // Declare JPanel for holding buttons
    private JButton[] buttons; // Declare an array of JButtons for the calculator buttons
    private String[] buttonLabels = { // Define labels for the buttons
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
    };

    private double num1 = 0, num2 = 0, result = 0; // Variables to hold the numbers and the result
    private char operator; // Variable to hold the operator

    public Calculator() {
        // Initialize frame
        frame = new JFrame("Calculator"); // Create a new JFrame with the title "Calculator"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        frame.setSize(400, 500); // Set the size of the frame
        frame.setLayout(new BorderLayout()); // Set the layout of the frame to BorderLayout

        // Initialize display
        display = new JTextField(); // Create a new JTextField for the display
        display.setEditable(false); // Set the display to be non-editable
        display.setFont(new Font("Arial", Font.PLAIN, 24)); // Set the font of the display
        frame.add(display, BorderLayout.NORTH); // Add the display to the top of the frame

        // Initialize panel
        panel = new JPanel(); // Create a new JPanel for the buttons
        panel.setLayout(new GridLayout(4, 4, 10, 10)); // Set the layout of the panel to a 4x4 grid with gaps
        frame.add(panel, BorderLayout.CENTER); // Add the panel to the center of the frame

        // Initialize buttons
        buttons = new JButton[16]; // Create an array of 16 JButtons
        for (int i = 0; i < 16; i++) { // Loop through the button labels
            buttons[i] = new JButton(buttonLabels[i]); // Create a new JButton with the label
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 24)); // Set the font of the button
            buttons[i].addActionListener(new ButtonClickListener()); // Add an ActionListener to the button
            panel.add(buttons[i]); // Add the button to the panel
        }

        // Initialize and add clear button
        JButton clearButton = new JButton("Clear"); // Create a new JButton for the clear button
        clearButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Set the font of the clear button
        clearButton.addActionListener(e -> display.setText("")); // Add an ActionListener to clear the display
        frame.add(clearButton, BorderLayout.SOUTH); // Add the clear button to the bottom of the frame

        // Set frame visibility
        frame.setVisible(true); // Set the frame to be visible
    }

    // Inner class for handling button clicks
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // Get the command from the button

            // Handle number and decimal point inputs
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                display.setText(display.getText() + command); // Append the number or decimal point to the display
            }
            // Handle operator inputs
            else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                num1 = Double.parseDouble(display.getText()); // Parse the first number from the display
                operator = command.charAt(0); // Set the operator
                display.setText(""); // Clear the display
            }
            // Handle equals button
            else if (command.equals("=")) {
                num2 = Double.parseDouble(display.getText()); // Parse the second number from the display
                switch (operator) { // Perform the operation based on the operator
                    case '+':
                        result = num1 + num2; // Addition
                        break;
                    case '-':
                        result = num1 - num2; // Subtraction
                        break;
                    case '*':
                        result = num1 * num2; // Multiplication
                        break;
                    case '/':
                        result = num1 / num2; // Division
                        break;
                }
                display.setText(String.valueOf(result)); // Display the result
                num1 = result; // Store the result for further calculations
            }
        }
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        new Calculator(); // Create a new instance of the Calculator class
    }
}
