package forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import restaurant.Meal;
import java.awt.Color;

public class FormMeal extends JFrame {

	/********************************************
	 * Variables declaration
	 *********************************************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDateAndTime;
	private JTextField txtName, txtPrice;
	private Meal newMeal = new Meal();
	private String cbCategorySelected, cbMenuSelected;
	private String[] categories = { "Regular", "Kids", "Veggie", "Spicy", "Gluten-free" };
	private JComboBox<?> cbCategory = new JComboBox<Object>(categories);
	private String[] menu = { "All year", "Winter", "Spring", "Summer", "Fall" };
	private JComboBox<?> cbMenu = new JComboBox<Object>(menu);
	private DefaultTableModel model = new DefaultTableModel();
	private JTable tbMeals;
	private JScrollPane scrollPane = new JScrollPane();
	private int index, idMeal;

	/********************************************
	 * Launch the application
	 *********************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMeal frame = new FormMeal();
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
	public FormMeal() {

		setTitle("Meals");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/********************************************
		 * Panel's content
		 *********************************************/

		lblDateAndTime = new JLabel("");
		lblDateAndTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateAndTime.setFont(new Font("Calibri Light", Font.ITALIC, 12));
		lblDateAndTime.setBounds(411, 0, 160, 23);
		contentPane.add(lblDateAndTime);

		JLabel imageMealForm = new JLabel("");
		imageMealForm.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
		imageMealForm.setIcon(new ImageIcon(img));
		imageMealForm.setBounds(10, 21, 73, 124);
		contentPane.add(imageMealForm);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblName.setBounds(90, 48, 67, 23);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtName.setBounds(167, 48, 137, 23);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblValidate = new JLabel("");
		lblValidate.setForeground(Color.RED);
		lblValidate.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblValidate.setBounds(167, 110, 137, 23);
		contentPane.add(lblValidate);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblPrice.setBounds(90, 87, 67, 23);
		contentPane.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtPrice.setColumns(10);
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validate = e.getKeyChar();

				if (Character.isLetter((validate))) {
					getToolkit().beep();
					e.consume();
					lblValidate.setText("Only digits allowed");
				} else
					lblValidate.setText("");
			}
		});
		txtPrice.setBounds(167, 87, 137, 23);
		contentPane.add(txtPrice);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblCategory.setBounds(328, 48, 67, 23);
		contentPane.add(lblCategory);

		cbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbCategory.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		cbCategory.setBounds(408, 48, 137, 22);
		contentPane.add(cbCategory);

		JLabel lblMenu = new JLabel("Menu:");
		lblMenu.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblMenu.setBounds(328, 87, 67, 23);
		contentPane.add(lblMenu);
		int interval = 100;

		cbMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbMenu.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		cbMenu.setBounds(408, 87, 137, 22);
		contentPane.add(cbMenu);

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
		btnHome.setBounds(472, 419, 99, 29);
		contentPane.add(btnHome);

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkEmpty())
					newMeal();
			}
		});

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(472, 459, 99, 29);
		contentPane.add(btnExit);
		btnNew.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnNew.setBounds(20, 440, 99, 29);
		contentPane.add(btnNew);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkEmpty())
					delete();
			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkEmpty())
					update();
			}
		});
		btnUpdate.setBounds(129, 440, 99, 29);
		contentPane.add(btnUpdate);
		btnUpdate.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnDelete.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnDelete.setBounds(238, 440, 99, 29);
		contentPane.add(btnDelete);

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
	 * Go to Home
	 *********************************************/

	protected void goHome() {
		dispose();
		FormHome home = new FormHome();
		home.setLocationRelativeTo(null);
		home.setVisible(true);
	}

	/********************************************
	 * Check if the textBox is empty
	 *********************************************/
	protected boolean checkEmpty() {
		boolean isDone = true;
		if (txtName.getText().isEmpty() || txtPrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Name or price of meal cannot be empty");
		} else {
			if (Double.parseDouble(txtPrice.getText()) < 100)
				isDone = false;
			else {
				JOptionPane.showMessageDialog(contentPane, "Price of meal cannot be more than 100");
			}

		}

		return isDone;

	}

	/********************************************
	 * New Meal
	 *********************************************/
	protected void newMeal() {
		cbCategorySelected = cbCategory.getSelectedItem().toString();
		cbMenuSelected = cbMenu.getSelectedItem().toString();

		double dPrice = Double.valueOf(txtPrice.getText());

		if (newMeal.insertNewMeal(txtName.getText(), dPrice, cbCategorySelected, cbMenuSelected)) {
			clearData();
			populateTable();
		} else {
			JOptionPane.showMessageDialog(contentPane, "That Meal already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/********************************************
	 * Update Meal
	 *********************************************/
	protected void update() {
		cbCategorySelected = cbCategory.getSelectedItem().toString();
		cbMenuSelected = cbMenu.getSelectedItem().toString();

		double dPrice = Double.valueOf(txtPrice.getText());

		if (newMeal.updateMeal(idMeal, txtName.getText(), dPrice, cbCategorySelected, cbMenuSelected)) {
			populateTable();
			clearData();
		} else {
			JOptionPane.showMessageDialog(contentPane, "That Meal doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/********************************************
	 * Delete Meal
	 *********************************************/
	protected void delete() {

		int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {
			if (newMeal.deleteMeal(newMeal.getIdMeal(txtName.getText()))) {
				populateTable();

				clearData();
			} else {
				JOptionPane.showMessageDialog(contentPane, "That Meal doesn't exist", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/********************************************
	 * Populate Table
	 *********************************************/
	protected void populateTable() {

		model.setRowCount(0);

		Object[] columnsName = new Object[4];

		columnsName[0] = "Name";
		columnsName[1] = "Price";
		columnsName[2] = "Category";
		columnsName[3] = "menu";

		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[5];

		for (int i = 0; i < newMeal.getMealList().size(); i++) {

			rowData[0] = newMeal.getMealList().get(i).getMealName();
			rowData[1] = newMeal.getMealList().get(i).getPrice();
			rowData[2] = newMeal.getMealList().get(i).getCategory();
			rowData[3] = newMeal.getMealList().get(i).getMenu();

			model.addRow(rowData);
		}

		rowData[0] = "";
		rowData[1] = "";
		rowData[2] = "";
		rowData[3] = "";
		;

		model.addRow(rowData);

		scrollPane.setBounds(10, 160, 559, 231);
		contentPane.add(scrollPane);

		tbMeals = new JTable();
		tbMeals.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showItem();
			}
		});

		tbMeals.setBounds(10, 153, 563, 239);
		contentPane.add(tbMeals);

		tbMeals.setModel(model);

		JScrollPane pane = new JScrollPane(tbMeals);

		scrollPane.setViewportView(pane);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelHeader.setBounds(10, 21, 561, 124);
		contentPane.add(panelHeader);

		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBtn.setBounds(10, 419, 344, 69);
		contentPane.add(panelBtn);

	}

	/********************************************
	 * Show items on the labels
	 *********************************************/
	protected void showItem() {

		index = tbMeals.getSelectedRow();
		try {

			txtName.setText(model.getValueAt(index, 0).toString());
			txtPrice.setText(model.getValueAt(index, 1).toString());
			cbCategory.setSelectedItem(model.getValueAt(index, 2).toString());
			cbMenu.setSelectedItem(model.getValueAt(index, 3).toString());
			idMeal = newMeal.getIdMeal(model.getValueAt(index, 0).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/********************************************
	 * Clear all the labels
	 *********************************************/
	void clearData() {
		cbCategory.setSelectedIndex(0);
		cbMenu.setSelectedIndex(0);
		txtName.setText(null);
		txtPrice.setText(null);
		idMeal = 0;
	}
}