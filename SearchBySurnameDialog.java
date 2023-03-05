import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public abstract class SearchBySurnameDialog extends JDialog implements ActionListener {
    protected JTextField searchField;
    protected JButton searchButton;
    protected JButton cancelButton;

    public SearchBySurnameDialog(EmployeeDetails parent, String title) {
        super(parent, title, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(createTitlePanel());
        contentPane.add(createSearchPanel());
        contentPane.add(createButtonPanel());

        pack();
        setLocationRelativeTo(parent);
    }

     JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel(getTitle()));
        return panel;
    }

     JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        panel.add(new JLabel(getSearchLabel()));
        panel.add(searchField = new JTextField(20));
        searchField.setDocument(new JTextFieldLimit(20));
        return panel;
    }

     abstract String getSearchLabel();

    protected JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(searchButton = new JButton(getSearchButtonLabel()));
        searchButton.addActionListener(this);
        searchButton.requestFocus();
        panel.add(cancelButton = new JButton(getCancelButtonLabel()));
        cancelButton.addActionListener(this);
        return panel;
    }

     abstract String getSearchButtonLabel();

     abstract String getCancelButtonLabel();

     abstract void performSearch();

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            performSearch();
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
