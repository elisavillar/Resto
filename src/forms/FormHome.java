package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import restaurant.Location;
import restaurant.Order;

public class FormHome extends JFrame {

	/********************************************
	 * Variables declaration
	 *********************************************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDateAndTime;
	Location location = new Location();
	Order order = new Order();
	Image img;
	JLabel imgLoc1, imgLoc2, imgLoc3, imgLoc4, imgLoc5, imgLoc6;

	/********************************************
	 * Launch the application
	 *********************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormHome frame = new FormHome();
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
	public FormHome() {
		setBackground(new Color(192, 192, 192));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/********************************************
		 * Buttons
		 *********************************************/

		JButton btnOrders = new JButton("Orders");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goOrder();
			}
		});
		btnOrders.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnOrders.setEnabled(false);
		btnOrders.setBounds(508, 174, 103, 36);
		contentPane.add(btnOrders);

		JButton btnLocations = new JButton("Locations");
		btnLocations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goLocation();
			}
		});

		JButton btnKitchen = new JButton("Kitchen");
		btnKitchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goKitchen();
			}
		});
		btnKitchen.setEnabled(false);
		btnKitchen.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnKitchen.setBounds(508, 221, 103, 36);
		contentPane.add(btnKitchen);
		btnLocations.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnLocations.setEnabled(false);
		btnLocations.setBounds(508, 268, 103, 36);
		contentPane.add(btnLocations);

		JButton btnMeals = new JButton("Meals");
		btnMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goMeal();
			}
		});
		btnMeals.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnMeals.setEnabled(false);
		btnMeals.setBounds(508, 315, 103, 36);
		contentPane.add(btnMeals);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.BLACK);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		btnExit.setBounds(508, 431, 101, 36);
		contentPane.add(btnExit);

		/********************************************
		 * Panel's content
		 *********************************************/
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBackground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblUsername.setBounds(26, 10, 89, 29);
		contentPane.add(lblUsername);

		JLabel lblResultUsername = new JLabel("");
		lblResultUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultUsername.setForeground(Color.BLACK);
		lblResultUsername.setBackground(new Color(255, 255, 255));
		lblResultUsername.setText(FormLogin.userLogged);
		lblResultUsername.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblResultUsername.setBounds(109, 10, 137, 29);
		contentPane.add(lblResultUsername);

		lblDateAndTime = new JLabel("");
		lblDateAndTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateAndTime.setFont(new Font("Calibri Light", Font.ITALIC, 14));
		lblDateAndTime.setBounds(488, 7, 134, 25);
		contentPane.add(lblDateAndTime);

		JLabel userImage = new JLabel("");
		userImage.setHorizontalAlignment(SwingConstants.CENTER);
		userImage.setBounds(508, 70, 103, 75);
		contentPane.add(userImage);
		Image userImg = new ImageIcon(this.getClass().getResource("")).getImage();

		if (FormLogin.typeLogged == "Admin") {
			btnOrders.setEnabled(true);
			btnKitchen.setEnabled(true);
			btnLocations.setEnabled(true);
			btnMeals.setEnabled(true);
			userImg = new ImageIcon(this.getClass().getResource("/administrator.png")).getImage();
		}

		if (FormLogin.typeLogged == "Server") {
			btnOrders.setEnabled(true);
			userImg = new ImageIcon(this.getClass().getResource("/waitress.png")).getImage();
		}

		if (FormLogin.typeLogged == "Kitchen") {
			btnKitchen.setEnabled(true);
			userImg = new ImageIcon(this.getClass().getResource("/chefUser.png")).getImage();

		}

		userImage.setIcon(new ImageIcon(userImg));

		JLabel lblStatus1 = new JLabel(checkLocationExist("1"));
		lblStatus1.setBounds(26, 217, 133, 18);
		contentPane.add(lblStatus1);
		lblStatus1.setForeground(new Color(0, 100, 0));
		lblStatus1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus1.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 14));

		JLabel lblStatus2 = new JLabel(checkLocationExist("2"));
		lblStatus2.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus2.setForeground(new Color(0, 100, 0));
		lblStatus2.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 14));
		lblStatus2.setBounds(183, 217, 133, 18);
		contentPane.add(lblStatus2);

		JLabel lblStatus3 = new JLabel(checkLocationExist("3"));
		lblStatus3.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus3.setForeground(new Color(0, 100, 0));
		lblStatus3.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 14));
		lblStatus3.setBounds(341, 217, 133, 18);
		contentPane.add(lblStatus3);

		JLabel lblStatus4 = new JLabel(checkLocationExist("4"));
		lblStatus4.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus4.setForeground(new Color(0, 100, 0));
		lblStatus4.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 14));
		lblStatus4.setBounds(27, 441, 133, 25);
		contentPane.add(lblStatus4);

		JLabel lblStatus5 = new JLabel(checkLocationExist("5"));
		lblStatus5.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus5.setForeground(new Color(0, 100, 0));
		lblStatus5.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 14));
		lblStatus5.setBounds(184, 441, 133, 18);
		contentPane.add(lblStatus5);

		JLabel lblStatus6 = new JLabel(checkLocationExist("6"));
		lblStatus6.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus6.setForeground(new Color(0, 100, 0));
		lblStatus6.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 14));
		lblStatus6.setBounds(345, 441, 133, 18);
		contentPane.add(lblStatus6);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(26, 69, 134, 174);
		contentPane.add(panel_1);

		JLabel lblLoc1 = new JLabel("Location 1");
		panel_1.add(lblLoc1);
		lblLoc1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc1.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		Panel panelLoc1 = new Panel();
		panel_1.add(panelLoc1);

		imgLoc1 = new JLabel("");
		imgLoc1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanLocation("1");
			}
		});
		panelLoc1.add(imgLoc1);
		imgLoc1.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(183, 69, 134, 174);
		contentPane.add(panel_2);

		JLabel lblLoc2 = new JLabel("Location 2");
		panel_2.add(lblLoc2);
		lblLoc2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc2.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		Panel panelLoc2 = new Panel();
		panel_2.add(panelLoc2);

		imgLoc2 = new JLabel("");
		imgLoc2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanLocation("2");
			}
		});
		panelLoc2.add(imgLoc2);
		imgLoc2.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(341, 69, 134, 174);
		contentPane.add(panel_3);

		JLabel lblLoc3 = new JLabel("Location 3");
		panel_3.add(lblLoc3);
		lblLoc3.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc3.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		Panel panelLoc3 = new Panel();
		panel_3.add(panelLoc3);

		imgLoc3 = new JLabel("");
		imgLoc3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanLocation("3");
			}
		});
		imgLoc3.setHorizontalAlignment(SwingConstants.CENTER);
		panelLoc3.add(imgLoc3);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(26, 293, 134, 174);
		contentPane.add(panel_4);

		JLabel lblLoc4 = new JLabel("Location 4");
		panel_4.add(lblLoc4);
		lblLoc4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc4.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		Panel panelLoc4 = new Panel();
		panel_4.add(panelLoc4);

		imgLoc4 = new JLabel("");
		imgLoc4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanLocation("4");
			}
		});
		imgLoc4.setHorizontalAlignment(SwingConstants.CENTER);
		panelLoc4.add(imgLoc4);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(183, 293, 134, 174);
		contentPane.add(panel_5);

		JLabel lblLoc5 = new JLabel("Location 5");
		panel_5.add(lblLoc5);
		lblLoc5.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc5.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		Panel panelLoc5 = new Panel();
		panel_5.add(panelLoc5);

		imgLoc5 = new JLabel("");
		imgLoc5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanLocation("5");
			}
		});
		imgLoc5.setHorizontalAlignment(SwingConstants.CENTER);
		panelLoc5.add(imgLoc5);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(341, 292, 134, 174);
		contentPane.add(panel_6);

		JLabel lblLoc6 = new JLabel("Location 6");
		panel_6.add(lblLoc6);
		lblLoc6.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc6.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		Panel panelLoc6 = new Panel();
		panel_6.add(panelLoc6);

		imgLoc6 = new JLabel("");
		imgLoc6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanLocation("6");
			}
		});
		imgLoc6.setHorizontalAlignment(SwingConstants.CENTER);
		panelLoc6.add(imgLoc6);

		/********************************************
		 * Current Date and Time display
		 *********************************************/
		int interval = 1000;
		new Timer(interval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblDateAndTime.setText(DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm:ss").format(LocalDateTime.now()));

			}

		}).start();

		/********************************************
		 * Check state of the Locations
		 *********************************************/
		setState();
		int intervalLoc = 9999;
		new Timer(intervalLoc, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setState();

			}

		}).start();

	}

	/********************************************
	 * Go to Kitchen
	 *********************************************/
	private void goKitchen() {
		dispose();
		FormKitchen frame = new FormKitchen();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/********************************************
	 * Go to Location
	 *********************************************/
	private void goLocation() {
		dispose();
		FormLocation frame = new FormLocation();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/********************************************
	 * Go to Meal
	 *********************************************/
	private void goMeal() {
		dispose();
		FormMeal frame = new FormMeal();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/********************************************
	 * Go to Order
	 *********************************************/
	private void goOrder() {
		dispose();
		FormOrder frame = new FormOrder();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/********************************************
	 * Check state of locations
	 *********************************************/

	private Image checkState(String idLoc) {

		String state = null;

		if (location.getState(idLoc) == null)
			state = "";
		else
			state = location.getState(idLoc);

		switch (state) {
		case "Vacant":
			if (location.checkSize(idLoc).contains("2 persons"))
				img = new ImageIcon(this.getClass().getResource("/2vac.png")).getImage();
			else if (location.checkSize(idLoc).contains("4 persons"))
				img = new ImageIcon(this.getClass().getResource("/4vac.png")).getImage();
			else if (location.checkSize(idLoc).contains("6 persons"))
				img = new ImageIcon(this.getClass().getResource("/6vac.png")).getImage();
			break;
		case "Occupied":
			if (location.checkSize(idLoc).contains("2 persons"))
				img = new ImageIcon(this.getClass().getResource("/2occ.png")).getImage();
			else if (location.checkSize(idLoc).contains("4 persons"))
				img = new ImageIcon(this.getClass().getResource("/4occ.png")).getImage();
			else if (location.checkSize(idLoc).contains("6 persons"))
				img = new ImageIcon(this.getClass().getResource("/6occ.png")).getImage();
			break;
		case "Dirty":
			if (location.checkSize(idLoc).contains("2 persons"))
				img = new ImageIcon(this.getClass().getResource("/2drt.png")).getImage();
			else if (location.checkSize(idLoc).contains("4 persons"))
				img = new ImageIcon(this.getClass().getResource("/4drt.png")).getImage();
			else if (location.checkSize(idLoc).contains("6 persons"))
				img = new ImageIcon(this.getClass().getResource("/6drt.png")).getImage();
			break;
		default:
			img = new ImageIcon(this.getClass().getResource("/closed.png")).getImage();
		}

		return img;

	}

	/********************************************
	 * Set image for the locations
	 *********************************************/
	private void setState() {
		imgLoc1.setIcon(new ImageIcon(checkState("1")));
		imgLoc2.setIcon(new ImageIcon(checkState("2")));
		imgLoc3.setIcon(new ImageIcon(checkState("3")));
		imgLoc4.setIcon(new ImageIcon(checkState("4")));
		imgLoc5.setIcon(new ImageIcon(checkState("5")));
		imgLoc6.setIcon(new ImageIcon(checkState("6")));
	}

	/********************************************
	 * Clean the locations
	 *********************************************/
	private void cleanLocation(String loc) {
		Location location = new Location();
		int response = 0;

		if (location.checkDirty(loc)) {
			response = JOptionPane.showConfirmDialog(null, "Do you want to clean the Location?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				location.setStateLocation(loc, "Vacant");
				setState();
				JOptionPane.showMessageDialog(contentPane, "Cleaned");
			}
		} else {
			JOptionPane.showMessageDialog(contentPane, "This Location is not dirty", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/********************************************
	 * Check if Location exist
	 *********************************************/
	protected String checkLocationExist(String loc) {
		String status = null;
		if (order.getStatusOrder(loc) == null)
			status = "";
		else
			status = order.getStatusOrder(loc);
		return status;

	}

}