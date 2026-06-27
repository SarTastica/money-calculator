package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.ui.CurrencyDialog;
import software.ulpgc.moneycalculator.ui.MoneyDialog;
import software.ulpgc.moneycalculator.ui.MoneyDisplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final SwingMoneyDisplay moneyDisplay;

    public MainFrame() {
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(240, 242, 245));

        this.moneyDialog = new SwingMoneyDialog();
        this.currencyDialog = new SwingCurrencyDialog();
        this.moneyDisplay = new SwingMoneyDisplay();

        Dimension componentSize = new Dimension(400, 60);
        moneyDialog.setPreferredSize(componentSize);
        moneyDialog.setMaximumSize(componentSize);
        currencyDialog.setPreferredSize(componentSize);
        currencyDialog.setMaximumSize(componentSize);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(40, 40, 40, 40));
        card.putClientProperty("FlatLaf.style", "arc: 30");

        card.setPreferredSize(new Dimension(500, 650));

        card.add(createLabel("FROM"));
        card.add(moneyDialog);

        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(createSwapButton());
        card.add(Box.createRigidArea(new Dimension(0, 20)));

        card.add(createLabel("TO"));
        card.add(currencyDialog);

        card.add(Box.createRigidArea(new Dimension(0, 40)));
        card.add(createCalculateButton());

        card.add(Box.createRigidArea(new Dimension(0, 30)));
        card.add(moneyDisplay);

        // 3. Wrapper
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.add(card);

        this.add(wrapper, BorderLayout.CENTER);

        this.pack();
        this.setMinimumSize(new Dimension(500, 650));
        this.setLocationRelativeTo(null);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(Color.GRAY);
        return label;
    }

    private JButton createSwapButton() {
        JButton btn = new JButton("⇅");
        btn.setFont(new Font("SansSerif", Font.BOLD, 22));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(60, 60));
        btn.setMaximumSize(new Dimension(60, 60));
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.addActionListener(e -> {
            if (commands.containsKey("swap")) commands.get("swap").execute();
        });
        return btn;
    }

    private JButton createCalculateButton() {
        JButton btn = new JButton("Calculate");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(350, 50)); // Botón más ancho
        btn.setMaximumSize(new Dimension(400, 50));
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.addActionListener(e -> {
            try {
                if (commands.containsKey("exchange")) commands.get("exchange").execute();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        return btn;
    }

    public void add(String name, Command command) { commands.put(name, command); }
    public MoneyDialog getMoneyDialog() { return moneyDialog; }
    public CurrencyDialog getCurrencyDialog() { return currencyDialog; }
    public MoneyDisplay getMoneyDisplay() { return moneyDisplay; }
    public SwingMoneyDialog getSwingMoneyDialog() { return moneyDialog; }
    public SwingCurrencyDialog getSwingCurrencyDialog() { return currencyDialog; }
}