package forms;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import restaurant.*;
import java.awt.Color;

public class FormOrderDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDateAndTime;
	private JLabel txtIdOrder;
	private OrderDetails orderD = new OrderDetails();
	private Meal meal = new Meal();
	JComboBox<Object> cbMeal = new JComboBox<Object>(new Object[] {});
	private JTable tbOrder;
	DefaultTableModel model = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	JLabel txtLocation;
	private String idOrderDetails;
	JSpinner quantity;
	JLabel imageOrderDetailForm;
	JButton btnBackToOrder, btnHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOrderDetails frame = new FormOrderDetails();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormOrderDetails() {

		setTitle("Order Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/********************************************
		 * Buttons
		 *********************************************/

		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goHome();
			}
		});
		btnHome.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnHome.setBounds(472, 420, 99, 29);
		contentPane.add(btnHome);

		quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(1, 1, null, 1));
		quantity.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		quantity.setBounds(176, 71, 137, 23);
		contentPane.add(quantity);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(472, 459, 99, 29);
		contentPane.add(btnExit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(240, 435, 99, 29);
		contentPane.add(btnDelete);
		btnDelete.setFont(new Font("Calibri Light", Font.PLAIN, 12));

		JButton btnAddItem = new JButton("Add");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});
		btnAddItem.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnAddItem.setBounds(21, 435, 99, 29);
		contentPane.add(btnAddItem);

		btnBackToOrder = new JButton("Back");
		btnBackToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackOrder();
			}
		});
		btnBackToOrder.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnBackToOrder.setBounds(368, 420, 99, 29);
		contentPane.add(btnBackToOrder);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnUpdate.setBounds(131, 435, 99, 29);
		contentPane.add(btnUpdate);

		/********************************************
		 * Panel's content
		 *********************************************/

		JLabel lblIdOrder = new JLabel("Id Order:");
		lblIdOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdOrder.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblIdOrder.setBounds(99, 37, 67, 23);
		contentPane.add(lblIdOrder);

		imageOrderDetailForm = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/additem.png")).getImage();
		imageOrderDetailForm.setIcon(new ImageIcon(img));
		imageOrderDetailForm.setHorizontalAlignment(SwingConstants.CENTER);
		imageOrderDetailForm.setBounds(10, 26, 79, 107);
		contentPane.add(imageOrderDetailForm);

		JLabel lblMeal;
		lblMeal = new JLabel("Meal:");
		lblMeal.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblMeal.setBounds(323, 71, 67, 23);
		contentPane.add(lblMeal);

		JLabel lblValidate = new JLabel("");
		lblValidate.setForeground(Color.RED);
		lblValidate.setFont(new Font("Calibri Light", Font.ITALIC, 12));
		lblValidate.setBounds(176, 105, 137, 23);
		contentPane.add(lblValidate);

		lblDateAndTime = new JLabel("");
		lblDateAndTime.setFont(new Font("Calibri Light", Font.ITALIC, 12));
		lblDateAndTime.setBounds(411, 8, 160, 16);
		contentPane.add(lblDateAndTime);

		JLabel lblClient = new JLabel("Quantity:");
		lblClient.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblClient.setBounds(99, 71, 67, 23);
		contentPane.add(lblClient);

		meal.getMealCb(cbMeal);
		cbMeal.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		cbMeal.setBounds(400, 69, 137, 22);
		contentPane.add(cbMeal);

		txtIdOrder = new JLabel(FormOrder.orderSelected);
		txtIdOrder.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdOrder.setFont(new Font("Calibri Light", Font.BOLD, 15));
		txtIdOrder.setBounds(176, 37, 137, 23);
		contentPane.add(txtIdOrder);

		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBtn.setBounds(10, 419, 348, 69);
		contentPane.add(panelBtn);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblLocation.setBounds(323, 37, 67, 23);
		contentPane.add(lblLocation);

		txtLocation = new JLabel(FormOrder.locationSelected);
		txtLocation.setHorizontalAlignment(SwingConstants.CENTER);
		txtLocation.setFont(new Font("Calibri Light", Font.BOLD, 15));
		txtLocation.setBounds(400, 37, 137, 23);
		contentPane.add(txtLocation);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 21, 561, 124);
		contentPane.add(panel);

		int interval = 100;

		/********************************************
		 * Current Date and Time display
		 *********************************************/

		new Timer(interval, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblDateAndTime.setText(DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm:ss").format(LocalDateTime.now()));
			}

		}).start();

		populateTable();

	}

	/********************************************
	 * Go Back to Order
	 *********************************************/
	protected void btnBackOrder() {
		dispose();
		FormOrder order = new FormOrder();
		order.setVisible(true);
		order.setLocationRelativeTo(null);
	}

	/********************************************
	 * Update
	 *********************************************/

	protected void update() {

		int index = tbOrder.getSelectedRow();

		if (orderD.updateOrderDetail(tbOrder.getValueAt(index, 0).toString(), quantity.getValue().toString(),
				cbMeal.getSelectedItem().toString())) {
			populateTable();
			clearData();
		} else {
			JOptionPane.showMessageDialog(contentPane, "That option is not valid", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/********************************************
	 * Add item
	 *********************************************/

	protected void addItem() {

		if (orderD.insertNewOrderDetail(txtIdOrder.getText(), quantity.getValue().toString(),
				cbMeal.getSelectedItem().toString())) {
			clearData();
			populateTable();
		} else {
			JOptionPane.showMessageDialog(contentPane, "That meal already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/********************************************
	 * Go to Home
	 *********************************************/
	protected void goHome() {
		dispose();
		FormHome home = new FormHome();
		home.setVisible(true);
		home.setLocationRelativeTo(null);
	}

	/********************************************
	 * Delete order details
	 *********************************************/
	protected void delete() {

		if (txtIdOrder.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please select a valid order");
		} else {
			int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response == JOptionPane.YES_OPTION) {
				if (orderD.deleteOrderDetails(idOrderDetails)) {
					populateTable();

					clearData();
				} else {
					JOptionPane.showMessageDialog(contentPane, "That option is not valid", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	/********************************************
	 * Populate table
	 *********************************************/
	protected void populateTable() {

		model.setRowCount(0);

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		Object[] columnsName = new Object[4];

		columnsName[0] = "#OrdDetails";
		columnsName[1] = "Meal";
		columnsName[2] = "Quantity";
		columnsName[3] = "Price (EA)";

		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[4];

		for (int i = 0; i < orderD.getOrdersDetails(FormOrder.orderSelected).size(); i++) {

			rowData[0] = orderD.getOrdersDetails(FormOrder.orderSelected).get(i).getIdOrderDetails();
			rowData[1] = orderD.getOrdersDetails(FormOrder.orderSelected).get(i).getMealName();
			rowData[2] = orderD.getOrdersDetails(FormOrder.orderSelected).get(i).getQuantity();
			rowData[3] = formatter.format(orderD.getOrdersDetails(FormOrder.orderSelected).get(i).getPrice());

			model.addRow(rowData);
		}

		rowData[0] = "";
		rowData[1] = "";
		rowData[2] = "";
		rowData[3] = "";

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

		tbOrder.getColumnModel().getColumn(0).setMinWidth(0);
		tbOrder.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

		JScrollPane pane = new JScrollPane(tbOrder);

		scrollPane.setViewportView(pane);

	}

	/********************************************
	 * Show the items on the labels
	 *********************************************/
	protected void showItem() {
		int index = tbOrder.getSelectedRow();
		try {

			idOrderDetails = tbOrder.getValueAt(index, 0).toString();
			cbMeal.setSelectedItem(tbOrder.getValueAt(index, 1).toString());
			quantity.setValue(Integer.valueOf(tbOrder.getValueAt(index, 2).toString()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/********************************************
	 * Clear all the data from the labels
	 *********************************************/
	protected void clearData() {
		idOrderDetails = null;
		cbMeal.setSelectedIndex(0);
		quantity.setValue(Integer.valueOf(1));
	}
}
