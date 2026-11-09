import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BankBalanceGUI extends JFrame implements ActionListener {

    private JPanel panel;

    private JLabel balanceLabel;
    private JLabel startingBalanceLabel;
    private JLabel transactionLabel;

    private JTextField startingBalanceField;
    private JTextField transactionField;

    private JButton setBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton showBalanceButton;
    private JButton exitButton;

    private double balance = 0.0;

    public BankBalanceGUI() {

        // Create the frame
        setTitle("Bank Balance Application");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Create the JPanel
        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        // Starting balance
        startingBalanceLabel = new JLabel("Starting Balance:");
        startingBalanceField = new JTextField();

        setBalanceButton = new JButton("Set Balance");
        setBalanceButton.addActionListener(this);

        // Transaction amount
        transactionLabel = new JLabel("Transaction Amount:");
        transactionField = new JTextField();

        // Buttons
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);

        showBalanceButton = new JButton("Show Balance");
        showBalanceButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        // Balance display
        balanceLabel = new JLabel("Current Balance: $0.00");

        // Add components to JPanel
        panel.add(startingBalanceLabel);
        panel.add(startingBalanceField);

        panel.add(setBalanceButton);
        panel.add(new JLabel(""));

        panel.add(transactionLabel);
        panel.add(transactionField);

        panel.add(depositButton);
        panel.add(withdrawButton);

        panel.add(showBalanceButton);
        panel.add(exitButton);

        panel.add(balanceLabel);
        panel.add(new JLabel(""));

        // Add panel to frame
        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            // Set starting balance
            if (e.getSource() == setBalanceButton) {

                balance = Double.parseDouble(startingBalanceField.getText());

                if (balance < 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Starting balance cannot be negative."
                    );

                    balance = 0.0;
                    return;
                }

                updateBalance();
            }

            // Deposit
            else if (e.getSource() == depositButton) {

                double amount =
                    Double.parseDouble(transactionField.getText());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Deposit amount must be greater than zero."
                    );
                    return;
                }

                balance += amount;

                updateBalance();

                transactionField.setText("");
            }

            // Withdrawal
            else if (e.getSource() == withdrawButton) {

                double amount =
                    Double.parseDouble(transactionField.getText());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Withdrawal amount must be greater than zero."
                    );
                    return;
                }

                if (amount > balance) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Insufficient funds."
                    );
                    return;
                }

                balance -= amount;

                updateBalance();

                transactionField.setText("");
            }

            // Show current balance
            else if (e.getSource() == showBalanceButton) {

                updateBalance();
            }

            // Exit
            else if (e.getSource() == exitButton) {

                JOptionPane.showMessageDialog(
                    this,
                    String.format(
                        "Remaining Balance: $%.2f",
                        balance
                    )
                );

                System.exit(0);
            }

        } catch (NumberFormatException error) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric amount."
            );
        }
    }

    private void updateBalance() {

        balanceLabel.setText(
            String.format(
                "Current Balance: $%.2f",
                balance
            )
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new BankBalanceGUI();
        });
    }
}