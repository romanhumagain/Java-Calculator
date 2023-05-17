import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class New_Calculator {
    private JFrame frame;
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton equalsButton, decimalButton, clearButton;
    private JPanel panel;

    private String currentInput;
    private double result;

    public New_Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 450);
        frame.setLayout(null);

        displayField = new JTextField();
        displayField.setBounds(50, 25, 250, 50);
        displayField.setEditable(false);
        frame.add(displayField);

        panel = new JPanel();
        panel.setBounds(50, 100, 250, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        frame.add(panel);

        currentInput = "";
        result = 0.0;

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            panel.add(numberButtons[i]);
            numberButtons[i].addActionListener(new NumberButtonAction());
        }

        operationButtons = new JButton[4];
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        operationButtons[0] = addButton;
        operationButtons[1] = subtractButton;
        operationButtons[2] = multiplyButton;
        operationButtons[3] = divideButton;

        for (JButton operationButton : operationButtons) {
            panel.add(operationButton);
            operationButton.addActionListener(new OperationButtonAction());
        }

        equalsButton = new JButton("=");
        decimalButton = new JButton(".");
        clearButton = new JButton("C");

        panel.add(decimalButton);
        panel.add(equalsButton);
        panel.add(clearButton);

        decimalButton.addActionListener(new NumberButtonAction());
        equalsButton.addActionListener(new EqualsButtonAction());
        clearButton.addActionListener(new ClearButtonAction());

        frame.setVisible(true);
    }

    private class NumberButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String buttonText = ((JButton) event.getSource()).getText();
            currentInput += buttonText;
            displayField.setText(currentInput);
        }
    }

    private class OperationButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String operation = ((JButton) event.getSource()).getText();
            double input = Double.parseDouble(currentInput);

            if (operation.equals("+")) {
                result += input;
            } else if (operation.equals("-")) {
                result -= input;
            } else if (operation.equals("*")) {
                result *= input;
            } else if (operation.equals("/")) {
                result /= input;
            }

            currentInput = "";
            displayField.setText(String.valueOf(result));
        }
    }

    private class EqualsButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            double input = Double.parseDouble(currentInput);

            if (addButton.isEnabled()) {
                result += input;
            } else if (subtractButton.isEnabled()) {
                result -= input;
            } else if (multiplyButton.isEnabled()) {
                result *= input;
            } else if (divideButton.isEnabled()) {
                result /= input;
            }

            currentInput = "";
            displayField.setText(String.valueOf(result));
        }
    }

    private class ClearButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            currentInput = "";
            result = 0.0;
            displayField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new New_Calculator();
            }
        });
    }
}
