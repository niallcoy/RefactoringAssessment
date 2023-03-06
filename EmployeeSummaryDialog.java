/*
 * 
 * This is the summary dialog for displaying all Employee details
 * 
 * */

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class EmployeeSummaryDialog extends JDialog implements ActionListener {
	// vector with all Employees details
	Vector<Object> allEmployees;
	JButton back;
	
	public EmployeeSummaryDialog(Vector<Object> allEmployees) {
		setTitle("Employee Summary");
		setModal(true);
		this.allEmployees = allEmployees;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(summaryPane());
		setContentPane(scrollPane);

		setSize(850, 500);
		setLocation(350, 250);
		setVisible(true);

	}
	// initialise container
	public Container summaryPane() {
	    JPanel summaryDialog = new JPanel(new MigLayout());
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JTable employeeTable = createEmployeeTable();
	    JScrollPane scrollPane = new JScrollPane(employeeTable);
	    buttonPanel.add(createBackButton());
	    summaryDialog.add(buttonPanel, "growx, pushx, wrap");
	    summaryDialog.add(scrollPane, "growx, pushx, wrap");
	    scrollPane.setBorder(BorderFactory.createTitledBorder("Employee Details"));
	    return summaryDialog;
	}

	private JTable createEmployeeTable() {
	    Vector<String> header = new Vector<>(Arrays.asList(
	        "ID", "PPS Number", "Surname", "First Name", "Gender", "Department", "Salary", "Full Time"
	    ));
	    int[] colWidth = { 15, 100, 120, 120, 50, 120, 80, 80 };
	    DefaultTableModel tableModel = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    JTable employeeTable = new JTable(tableModel);
	    for (int i = 0; i < employeeTable.getColumnCount(); i++) {
	        employeeTable.getColumn(header.get(i)).setMinWidth(colWidth[i]);
	        if (i == 4) {
	            employeeTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
	                @Override
	                public void setValue(Object value) {
	                    setText(Character.toString((char) value));
	                }
	            });
	        } else if (i == 6) {
	            employeeTable.getColumnModel().getColumn(i).setCellRenderer(new DecimalFormatRenderer());
	        } else {
	            employeeTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer());
	        }
	    }
	    employeeTable.setPreferredScrollableViewportSize(new Dimension(800, (15 * employeeTable.getRowCount() + 15)));
	    employeeTable.setAutoCreateRowSorter(true);
	    return employeeTable;
	}
	private JButton createBackButton() {
	    JButton back = new JButton("Back");
	    back.addActionListener(this::actionPerformed);
	    return back;
	}

	private Class<?>[] getColumnClasses() {
	    return new Class<?>[] { Integer.class, String.class, String.class, String.class,
	        Character.class, String.class, Double.class, Boolean.class
	    };
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back){
			dispose();
		}

	}
	// format for salary column
	static class DecimalFormatRenderer extends DefaultTableCellRenderer {
		 private static final DecimalFormat format = new DecimalFormat(
		 "\u20ac ###,###,##0.00" );

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			 JLabel label = (JLabel) c;
			 label.setHorizontalAlignment(JLabel.RIGHT);
			 // format salary column
			value = format.format((Number) value);

			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}// end getTableCellRendererComponent
	}// DefaultTableCellRenderer
}// end class EmployeeSummaryDialog
