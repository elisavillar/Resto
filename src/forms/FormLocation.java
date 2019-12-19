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
import restaurant.Location;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormLocation extends JFrame {

	/********************************************
	 * Variables declaration
	 *********************************************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDateAndTime;
	private JTextField txtNumber;
	private Location location = new Location();
	private String cbSizeSelected, cbPositionSelected;
	private String[] sizes = { "2 persons", "4 persons", "6 persons" };
	private JComboBox<?> cbSize = new JComboBox<Object>(sizes);
	private String[] positions = { "Patio", "In diner" };
	private JComboBox<?> cbPosition = new JComboBox<Object>(positions);
	private JTable tbLocation;
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane = new JScrollPane();

	/********************************************
	 * Launch the application
	 *********************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLocation frame = new FormLocation();
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
	public FormLocation() {
		setTitle("Location");
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
		lblDateAndTime.setBounds(413, 11, 160, 14);
		contentPane.add(lblDateAndTime);

		JLabel imageMealForm = new JLabel("");
		imageMealForm.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/dining-table.png")).getImage();
		imageMealForm.setIcon(new ImageIcon(img));
		imageMealForm.setBounds(10, 22, 79, 108);
		contentPane.add(imageMealForm);

		JLabel lblValidate = new JLabel("");
		lblValidate.setFont(new Font("Calibri Light", Font.ITALIC, 12));
		lblValidate.setForeground(Color.RED);
		lblValidate.setBounds(406, 48, 137, 23);
		contentPane.add(lblValidate);

		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumber.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblNumber.setBounds(99, 48, 67, 23);
		contentPane.add(lblNumber);

		txtNumber = new JTextField();
		txtNumber.addKeyListener(new KeyAdapter() {
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
		txtNumber.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtNumber.setBounds(176, 48, 137, 23);
		contentPane.add(txtNumber);
		txtNumber.setColumns(10);

		JLabel lblDescription = new JLabel("Size:");
		lblDescription.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblDescription.setBounds(99, 87, 67, 23);
		contentPane.add(lblDescription);

		cbSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbSize.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		cbSize.setBounds(176, 87, 137, 22);
		contentPane.add(cbSize);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblPosition.setBounds(329, 87, 67, 23);
		contentPane.add(lblPosition);

		cbPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbPosition.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		cbPosition.setBounds(406, 87, 137, 22);
		contentPane.add(cbPosition);

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
				if (!checkNumberEmpty())
					newLocation();
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
		btnNew.setBounds(22, 440, 99, 29);
		contentPane.add(btnNew);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkNumberEmpty())
					delete();
			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkNumberEmpty())
					update();
			}
		});
		btnUpdate.setBounds(131, 440, 99, 29);
		contentPane.add(btnUpdate);
		btnUpdate.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnDelete.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnDelete.setBounds(240, 440, 99, 29);
		contentPane.add(btnDelete);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelHeader.setBounds(10, 22, 563, 108);
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
	 * Go to Home
	 *********************************************/

	protected void goHome() {
		dispose();
		FormHome home = new FormHome();
		home.setLocationRelativeTo(null);
		home.setVisible(true);
	}

	/********************************************
	 * New Location
	 *********************************************/

	protected void newLocation() {
		cbSizeSelected = cbSize.getSelectedItem().toString();
		cbPositionSelected = cbPosition.getSelectedItem().toString();

		if (location.insertNewLocation(txtNumber.getText(), cbSizeSelected, cbPositionSelected)) {
			clearData();
			populateTable();
		} else {
			JOptionPane.showMessageDialog(contentPane, "That Location already exist", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/********************************************
	 * Update Location
	 *********************************************/
	protected void update() {
		cbSizeSelected = cbSize.getSelectedItem().toString();
		cbPositionSelected = cbPosition.getSelectedItem().toString();

		if (location.updateLocation(txtNumber.getText(), cbSizeSelected, cbPositionSelected)) {
			populateTable();
			clearData();
		} else {
			JOptionPane.showMessageDialog(contentPane, "That Location doesn't exist", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/********************************************
	 * Delete location
	 *********************************************/

	protected void delete() {

		int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {
			if (location.deleteLocation(txtNumber.getText())) {
				populateTable();

				clearData();
			} else {
				JOptionPane.showMessageDialog(contentPane, "That Location doesn't exist", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/********************************************
	 * Check if number of location is empty
	 *********************************************/

	protected boolean checkNumberEmpty() {
		boolean isDone = true;
		if (txtNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Number of Location cannot be empty");
		} else {
			isDone = false;
		}
		return isDone;
	}

	/********************************************
	 * Populate table
	 *********************************************/
	protected void populateTable() {

		model.setRowCount(0);

		Object[] columnsName = new Object[4];

		columnsName[0] = "#Location";
		columnsName[1] = "Size";
		columnsName[2] = "Availability";
		columnsName[3] = "Position";

		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[4];

		for (int i = 0; i < location.getLocations().size(); i++) {

			rowData[0] = location.getLocations().get(i).getIdLocation();
			rowData[1] = location.getLocations().get(i).getSize();
			rowData[2] = location.getLocations().get(i).getState();
			rowData[3] = location.getLocations().get(i).getPosition();

			model.addRow(rowData);
		}

		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBtn.setBounds(10, 419, 347, 69);
		contentPane.add(panelBtn);

		scrollPane.setBounds(14, 156, 559, 235);
		contentPane.add(scrollPane);

		tbLocation = new JTable();
		tbLocation.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showItem();
			}
		});

		tbLocation.setBounds(10, 153, 563, 239);
		contentPane.add(tbLocation);

		tbLocation.setModel(model);

		JScrollPane pane = new JScrollPane(tbLocation);

		scrollPane.setViewportView(pane);

	}

	/********************************************
	 * Show data from the row selected
	 *********************************************/

	protected void showItem() {
		int index = tbLocation.getSelectedRow();
		try {

			txtNumber.setText(tbLocation.getValueAt(index, 0).toString());
			cbSize.setSelectedItem(tbLocation.getValueAt(index, 1).toString());
			cbPosition.setSelectedItem(tbLocation.getValueAt(index, 3).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/********************************************
	 * Clear text field and comboBox
	 *********************************************/

	protected void clearData() {
		cbSize.setSelectedIndex(0);
		cbPosition.setSelectedIndex(0);
		txtNumber.setText(null);
	}

}
