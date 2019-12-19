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

import restaurant.Meal;
import restaurant.Order;
import restaurant.OrderDetails;

public class FormClientHome extends JFrame {

	/********************************************
	 * Variables declaration
	 *********************************************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDateAndTime, txtIdOrder;
	private OrderDetails orderD = new OrderDetails();
	private Order order = new Order();
	private Meal meal = new Meal();
	private JComboBox<Object> cbMeal = new JComboBox<Object>(new Object[] {});
	private JTable tbOrder;
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane = new JScrollPane();
	private String idOrderDetails, idOrderClient, locationClient;
	private JSpinner quantity;

	/********************************************
	 * Launch the application
	 *********************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormClientHome frame = new FormClientHome();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/********************************************
	 * Create the frame
	 *********************************************/
	public FormClientHome() {

		setTitle("Client area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		idOrderClient = order.getIdOrderClient(FormLogin.userLogged);
		locationClient = order.getLocationClient(FormLogin.userLogged);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(472, 435, 99, 29);
		contentPane.add(btnExit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		JButton btnAddItem = new JButton("Add");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});
		btnAddItem.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnAddItem.setBounds(21, 435, 99, 29);
		contentPane.add(btnAddItem);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnUpdate.setBounds(131, 435, 99, 29);
		contentPane.add(btnUpdate);
		btnDelete.setBounds(240, 435, 99, 29);
		contentPane.add(btnDelete);
		btnDelete.setFont(new Font("Calibri Light", Font.PLAIN, 12));

		/********************************************
		 * Panel's content
		 *********************************************/

		lblDateAndTime = new JLabel("");
		lblDateAndTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateAndTime.setFont(new Font("Calibri Light", Font.ITALIC, 12));
		lblDateAndTime.setBounds(411, 8, 160, 16);
		contentPane.add(lblDateAndTime);

		JLabel imageMealForm = new JLabel("");
		imageMealForm.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/client.png")).getImage();
		imageMealForm.setIcon(new ImageIcon(img));
		imageMealForm.setBounds(10, 26, 79, 107);
		contentPane.add(imageMealForm);

		JLabel lblIdOrder = new JLabel("Id Order:");
		lblIdOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdOrder.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblIdOrder.setBounds(99, 37, 67, 23);
		contentPane.add(lblIdOrder);

		txtIdOrder = new JLabel(idOrderClient);
		txtIdOrder.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdOrder.setFont(new Font("Calibri Light", Font.BOLD, 15));
		txtIdOrder.setBounds(176, 37, 137, 23);
		contentPane.add(txtIdOrder);

		JLabel lblMeal;

		JLabel lblClient = new JLabel("Quantity:");
		lblClient.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblClient.setBounds(99, 71, 67, 23);
		contentPane.add(lblClient);

		quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(1, 1, null, 1));
		quantity.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		quantity.setBounds(176, 71, 137, 23);
		contentPane.add(quantity);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblLocation.setBounds(323, 37, 67, 23);
		contentPane.add(lblLocation);

		JLabel txtLocation = new JLabel(locationClient);
		txtLocation.setHorizontalAlignment(SwingConstants.CENTER);
		txtLocation.setFont(new Font("Calibri Light", Font.BOLD, 15));
		txtLocation.setBounds(400, 37, 137, 23);
		contentPane.add(txtLocation);
		lblMeal = new JLabel("Meal:");
		lblMeal.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblMeal.setBounds(323, 71, 67, 23);
		contentPane.add(lblMeal);

		meal.getMealCb(cbMeal);
		cbMeal.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		cbMeal.setBounds(400, 69, 137, 22);
		contentPane.add(cbMeal);

		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBtn.setBounds(10, 419, 348, 69);
		contentPane.add(panelBtn);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelHeader.setBounds(10, 21, 561, 124);
		contentPane.add(panelHeader);

		/********************************************
		 * Current Date and Time display
		 *********************************************/
		int interval = 100;
		new Timer(interval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblDateAndTime.setText(DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm:ss").format(LocalDateTime.now()));
			}
		}).start();

		populateTable();

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
	 * Add Item
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
	 * Delete
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
	 * Populate Table
	 *********************************************/
	protected void populateTable() {

		model.setRowCount(0);

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		Object[] columnsName = new Object[4];

		columnsName[0] = "#OrdDetails";
		columnsName[1] = "Meal";
		columnsName[2] = "Quantity";
		columnsName[3] = "Price";

		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[4];

		for (int i = 0; i < orderD.getOrdersDetails(idOrderClient).size(); i++) {

			rowData[0] = orderD.getOrdersDetails(idOrderClient).get(i).getIdOrderDetails();
			rowData[1] = orderD.getOrdersDetails(idOrderClient).get(i).getMealName();
			rowData[2] = orderD.getOrdersDetails(idOrderClient).get(i).getQuantity();
			rowData[3] = formatter.format(orderD.getOrdersDetails(idOrderClient).get(i).getPrice());

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
	 * Show Item on the labels
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
	 * Clear all the labels
	 *********************************************/
	protected void clearData() {
		idOrderDetails = null;
		cbMeal.setSelectedIndex(0);
		quantity.setValue(Integer.valueOf(1));
	}

}
