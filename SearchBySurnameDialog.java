/*
 * 
 * This is a dialog for searching Employees by their surname.
 * 
 * */

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

public abstract class SearchBySurnameDialog extends JDialog implements ActionListener{
	EmployeeDetails parent;
	JButton search, cancel;
	JTextField searchField;
	// constructor for search by surname dialog
	public SearchBySurnameDialog(EmployeeDetails parent) {
		setTitle("Search by Surname");
		setModal(true);
		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		setContentPane(scrollPane);

		getRootPane().setDefaultButton(search);
		
		setSize(500, 190);
		setLocation(350, 250);
		setVisible(true);
	}// end SearchBySurnameDialog
	
	// initialize search container
	public void searchPane() {
		
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
	JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(search = new JButton(getSearchButtonLabel()));
        search.addActionListener(this);
        search.requestFocus();
        panel.add(cancel = new JButton(getCancelButtonLabel()));
        cancel.addActionListener(this);
        return panel;
    }
	  abstract String getSearchButtonLabel();
      abstract String getCancelButtonLabel();
      abstract void performSearch();

	// action listener for save and cancel button
	public void actionPerformed(ActionEvent e) {
		// if option search, search for Employee
		if(e.getSource() == search){
			this.parent.searchBySurnameField.setText(searchField.getText());
			// search Employee by surname
			this.parent.searchEmployeeBySurname();
			dispose();// dispose dialog
		}// end if
		// else dispose dialog
		else if(e.getSource() == cancel)
			dispose();// dispose dialog
	}// end actionPerformed
}// end class SearchBySurnameDialog
