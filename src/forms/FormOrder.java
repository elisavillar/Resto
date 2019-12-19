package forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.*;

public class FormOrder extends JFrame {

	/********************************************
	 * Variables declaration
	 *********************************************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDateAndTime, txtIdOrder, lblStatusSelected;
	private Order order = new Order();
	private Location location = new Location();
	private JComboBox<Object> cbLocation = new JComboBox<Object>(new Object[] {});
	private JTable tbOrder;
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane = new JScrollPane();
	private JTextField txtClient;
	private JButton btnPaid, btnServed, btnReceipt, btnAddItem, btnDelete;
	private int index;

	/********************************************
	 * Launch the application
	 *********************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOrder frame = new FormOrder();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static String orderSelected, locationSelected, orderReceipt, clientReceipt, locationReceipt;

	/********************************************
	 * Create the frame
	 *********************************************/
	public FormOrder() {

		setTitle("Orders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/********************************************
		 * Buttons
		 *********************************************/

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goHome();
			}
		});
		btnHome.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnHome.setBounds(472, 402, 99, 29);
		contentPane.add(btnHome);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(472, 459, 99, 29);
		contentPane.add(btnExit);

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOrder();
			}
		});
		btnNew.setBounds(10, 402, 99, 29);
		contentPane.add(btnNew);
		btnNew.setFont(new Font("Calibri Light", Font.PLAIN, 12));

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(10, 459, 99, 29);
		contentPane.add(btnDelete);
		btnDelete.setFont(new Font("Calibri Light", Font.PLAIN, 12));

		btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});

		btnServed = new JButton("Served");
		btnServed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStatus("Served");
			}
		});
		btnServed.setEnabled(false);
		btnServed.setBounds(161, 419, 99, 29);
		contentPane.add(btnServed);
		btnServed.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnAddItem.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnAddItem.setBounds(297, 419, 99, 29);
		contentPane.add(btnAddItem);

		btnPaid = new JButton("Paid");
		btnPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStatus("Paid");
				location.setStateLocation(cbLocation.getSelectedItem().toString(), "Dirty");
			}
		});
		btnPaid.setEnabled(false);
		btnPaid.setBounds(161, 458, 99, 29);
		contentPane.add(btnPaid);
		btnPaid.setFont(new Font("Calibri Light", Font.PLAIN, 12));

		/********************************************
		 * Panel's content
		 *********************************************/

		btnReceipt = new JButton("Receipt");
		btnReceipt.setEnabled(false);
		btnReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goReceipt();
			}
		});
		btnReceipt.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnReceipt.setBounds(297, 459, 99, 29);
		contentPane.add(btnReceipt);

		lblDateAndTime = new JLabel("");
		lblDateAndTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateAndTime.setFont(new Font("Calibri Light", Font.ITALIC, 12));
		lblDateAndTime.setBounds(411, 0, 160, 24);
		contentPane.add(lblDateAndTime);

		JLabel imageMealForm = new JLabel("");
		imageMealForm.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/order2.png")).getImage();
		imageMealForm.setIcon(new ImageIcon(img));
		imageMealForm.setBounds(10, 26, 79, 114);
		contentPane.add(imageMealForm);

		JLabel lblIdOrder = new JLabel("Id Order:");
		lblIdOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdOrder.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblIdOrder.setBounds(99, 56, 67, 23);
		contentPane.add(lblIdOrder);

		txtIdOrder = new JLabel();
		txtIdOrder.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdOrder.setFont(new Font("Calibri Light", Font.BOLD, 15));
		txtIdOrder.setBounds(176, 56, 137, 23);
		contentPane.add(txtIdOrder);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblStatus.setBounds(99, 91, 67, 23);
		contentPane.add(lblStatus);

		lblStatusSelected = new JLabel("");
		lblStatusSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusSelected.setFont(new Font("Calibri Light", Font.BOLD, 15));
		lblStatusSelected.setBounds(176, 91, 137, 23);
		contentPane.add(lblStatusSelected);

		JLabel lblClient = new JLabel("Client:");
		lblClient.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblClient.setBounds(329, 56, 67, 23);
		contentPane.add(lblClient);

		txtClient = new JTextField();
		txtClient.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtClient.setColumns(10);
		txtClient.setBounds(406, 56, 137, 23);
		contentPane.add(txtClient);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblLocation.setBounds(329, 91, 67, 23);
		contentPane.add(lblLocation);

		location.getLocationsCb(cbLocation);
		cbLocation.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		cbLocation.setBounds(406, 91, 137, 22);
		contentPane.add(cbLocation);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelHeader.setBounds(10, 26, 561, 119);
		contentPane.add(panelHeader);

		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBtn.setBounds(138, 402, 294, 97);
		contentPane.add(panelBtn);

		/********************************************
		 * Current Date and Time display
		 *********************************************/
		int interval = 100;
		new Timer(interval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblDateAndTime.setText(DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm:ss").format(LocalDateTime.now()));
				checkButtonsStatus();
			}

		}).start();

		populateTable();

	}

	/****************************************************
	 * Check if the field client is empty - default Guest
	 ****************************************************/
	protected void checkClientEmpty() {

		if (txtClient.getText().isEmpty())
			txtClient.setText("Guest");

	}

	/********************************************
	 * Go to home
	 *********************************************/
	protected void goHome() {
		dispose();
		FormHome home = new FormHome();
		home.setLocationRelativeTo(null);
		home.setVisible(true);
	}

	/********************************************
	 * Add item to Order
	 *********************************************/
	protected void addItem() {
		if (txtIdOrder.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please select an order from the list", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (!lblStatusSelected.getText().contains("New")) {
			JOptionPane.showMessageDialog(contentPane, "You can't add Items to an order that's not New", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			dispose();
			orderSelected = txtIdOrder.getText();
			locationSelected = cbLocation.getSelectedItem().toString();
			FormOrderDetails orderDetails = new FormOrderDetails();
			orderDetails.setLocationRelativeTo(null);
			orderDetails.setVisible(true);
		}
	}

	/********************************************
	 * New Order
	 *********************************************/
	protected void newOrder() {
		checkClientEmpty();

		if (!txtIdOrder.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Select the empty row", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (!location.checkAvailable(cbLocation.getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(contentPane, "That location is not Vacant", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (order.insertNewOrder(txtClient.getText(), cbLocation.getSelectedItem().toString())) {
			location.setStateLocation(cbLocation.getSelectedItem().toString(), "Occupied");
			clearData();
			populateTable();
		} else {
			JOptionPane.showMessageDialog(contentPane, "That Order already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/********************************************
	 * Delete Order
	 *********************************************/
	protected void delete() {

		if (txtIdOrder.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please select a valid order");
		} else {
			int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response == JOptionPane.YES_OPTION) {
				if (order.deleteOrder(txtIdOrder.getText())) {
					location.setStateLocation(cbLocation.getSelectedItem().toString(), "Vacant");
					populateTable();

					clearData();
				} else {
					JOptionPane.showMessageDialog(contentPane, "That Order doesn't exist", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	/********************************************
	 * Change status of the Order
	 *********************************************/
	protected void changeStatus(String option) {

		if (order.updateOrderStatus(txtIdOrder.getText(), option)) {
			populateTable();
		} else {
			JOptionPane.showMessageDialog(contentPane, "Invalid option", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/********************************************
	 * Populate table
	 *********************************************/
	protected void populateTable() {

		model.setRowCount(0);

		Object[] columnsName = new Object[5];

		columnsName[0] = "#Order";
		columnsName[1] = "Date";
		columnsName[2] = "Client Name";
		columnsName[3] = "Status";
		columnsName[4] = "Location";

		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[5];

		for (int i = 0; i < order.getTodaysOrders().size(); i++) {

			rowData[0] = order.getTodaysOrders().get(i).getIdOrder();
			rowData[1] = order.getTodaysOrders().get(i).getDate();
			rowData[2] = order.getTodaysOrders().get(i).getClientName();
			rowData[3] = order.getTodaysOrders().get(i).getStatus();
			rowData[4] = order.getTodaysOrders().get(i).getIdLocation();

			model.addRow(rowData);
		}

		rowData[0] = "";
		rowData[1] = "";
		rowData[2] = "";
		rowData[3] = "";
		rowData[4] = "";

		model.addRow(rowData);

		scrollPane.setBounds(10, 156, 563, 235);
		contentPane.add(scrollPane);

		tbOrder = new JTable();
		tbOrder.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showItem();
			}
		});

		tbOrder.setBounds(10, 153, 563, 239);
		contentPane.add(tbOrder);

		tbOrder.setModel(model);

		JScrollPane pane = new JScrollPane(tbOrder);
		scrollPane.setViewportView(pane);

	}

	/********************************************
	 * Show data in the labels
	 *********************************************/
	protected void showItem() {
		index = tbOrder.getSelectedRow();
		try {

			txtIdOrder.setText(tbOrder.getValueAt(index, 0).toString());
			txtClient.setText(tbOrder.getValueAt(index, 2).toString());
			lblStatusSelected.setText(tbOrder.getValueAt(index, 3).toString());
			cbLocation.setSelectedItem(tbOrder.getValueAt(index, 4).toString());

			if (txtIdOrder.getText() == "") {
				cbLocation.setEnabled(true);
				txtClient.setEnabled(true);
			} else {
				cbLocation.setEnabled(false);
				txtClient.setEnabled(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/********************************************
	 * Go to receipt
	 *********************************************/
	protected void goReceipt() {
		index = tbOrder.getSelectedRow();
		orderReceipt = tbOrder.getValueAt(index, 0).toString();
		clientReceipt = tbOrder.getValueAt(index, 2).toString();
		locationReceipt = tbOrder.getValueAt(index, 4).toString();
		dispose();
		FormReceipt frame = new FormReceipt();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/********************************************
	 * Clear all the data from the labels
	 *********************************************/
	protected void clearData() {
		txtIdOrder.setText(null);
		cbLocation.setSelectedIndex(0);
		txtClient.setText(null);
	}

	/********************************************
	 * Check status to set the buttons
	 *********************************************/
	protected void checkButtonsStatus() {

		if (lblStatusSelected.getText().contains("New"))
			btnAddItem.setEnabled(true);
		else
			btnAddItem.setEnabled(false);

		if (lblStatusSelected.getText().contains("Ready"))
			btnServed.setEnabled(true);
		else
			btnServed.setEnabled(false);

		if (lblStatusSelected.getText().contains("Served")) {
			btnPaid.setEnabled(true);
			btnReceipt.setEnabled(true);
		} else {
			btnPaid.setEnabled(false);
			if (lblStatusSelected.getText().contains("Paid")) {
				btnReceipt.setEnabled(true);
				btnDelete.setEnabled(false);
			} else {
				btnReceipt.setEnabled(false);
				btnDelete.setEnabled(true);
			}
		}

	}
}
