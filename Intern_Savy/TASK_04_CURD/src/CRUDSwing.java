import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CRUDSwing extends JFrame {
    private DefaultListModel<String> model;
    private JList<String> itemList;
    private JTextField textField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    private ArrayList<String> items;

    public CRUDSwing() {
        items = new ArrayList<>();
        model = new DefaultListModel<>();
        itemList = new JList<>(model);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        textField = new JTextField(20);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(itemList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(textField);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newItem = textField.getText();
                if (!newItem.isEmpty()) {
                    items.add(newItem);
                    updateItemList();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String updatedItem = textField.getText();
                    if (!updatedItem.isEmpty()) {
                        items.set(selectedIndex, updatedItem);
                        updateItemList();
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    items.remove(selectedIndex);
                    updateItemList();
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CRUD Swing Application");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateItemList() {
        model.clear();
        for (String item : items) {
            model.addElement(item);
        }
        textField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CRUDSwing();
            }
        });
    }
}
