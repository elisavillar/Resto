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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import restaurant.Order;
import restaurant.OrderDetails;

public class FormKitchen extends JFrame {

	/********************************************
	 * Variables declaration
	 *********************************************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDateAndTime, detLoc1, detLoc2, detLoc3, detLoc4, detLoc5, detLoc6, lblStatusSelected,
			lblLocationSelected;
	private JLabel lblIdOrderSelected;
	private Order order = new Order();
	private OrderDetails orderD = new OrderDetails();
	private JButton btnReady, btnInProgress;

	/********************************************
	 * Launch the application
	 *********************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormKitchen frame = new FormKitchen();
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
	public FormKitchen() {

		setTitle("Kitchen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/********************************************
		 * Buttons
		 *********************************************/
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblLocation.setBounds(164, 44, 67, 23);
		contentPane.add(lblLocation);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goHome();
			}
		});
		btnHome.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnHome.setBounds(465, 445, 99, 29);
		contentPane.add(btnHome);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(465, 485, 99, 29);
		contentPane.add(btnExit);

		btnInProgress = new JButton("In Progress");
		btnInProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusChange("In Progress", lblLocationSelected.getText());

			}
		});
		btnInProgress.setBounds(32, 468, 99, 29);
		contentPane.add(btnInProgress);
		btnInProgress.setFont(new Font("Calibri Light", Font.PLAIN, 12));

		btnReady = new JButton("Ready");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusChange("Ready", lblLocationSelected.getText());
				resetLabels();
			}
		});
		btnReady.setBounds(141, 468, 99, 29);
		contentPane.add(btnReady);
		btnReady.setFont(new Font("Calibri Light", Font.PLAIN, 12));

		/********************************************
		 * Panel's content
		 *********************************************/
		JLabel imageMealForm = new JLabel("");
		imageMealForm.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/chef.png")).getImage();
		imageMealForm.setIcon(new ImageIcon(img));
		imageMealForm.setBounds(10, 22, 92, 115);
		contentPane.add(imageMealForm);

		lblDateAndTime = new JLabel("");
		lblDateAndTime.setFont(new Font("Calibri Light", Font.ITALIC, 12));
		lblDateAndTime.setBounds(411, 0, 160, 24);
		contentPane.add(lblDateAndTime);

		lblLocationSelected = new JLabel();
		lblLocationSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocationSelected.setFont(new Font("Calibri Light", Font.BOLD, 18));
		lblLocationSelected.setBounds(278, 44, 221, 23);
		contentPane.add(lblLocationSelected);

		lblIdOrderSelected = new JLabel();
		lblIdOrderSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdOrderSelected.setFont(new Font("Calibri Light", Font.BOLD, 18));
		lblIdOrderSelected.setBounds(245, 44, 249, 23);
		contentPane.add(lblIdOrderSelected);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblStatus.setBounds(164, 78, 67, 23);
		contentPane.add(lblStatus);

		lblStatusSelected = new JLabel();
		lblStatusSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusSelected.setFont(new Font("Calibri Light", Font.BOLD, 18));
		lblStatusSelected.setBounds(278, 78, 221, 23);
		contentPane.add(lblStatusSelected);

		JPanel panelBtn;
		panelBtn = new JPanel();
		panelBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBtn.setBounds(10, 445, 258, 69);
		contentPane.add(panelBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(17, 142, 171, 141);
		contentPane.add(panel_1);

		JLabel lblLoc1 = new JLabel("Location 1");
		lblLoc1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc1.setFont(new Font("Calibri Light", Font.PLAIN, 16));

		detLoc1 = new JLabel(orderD.setLabelKitchen(1));
		detLoc1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showItemLabel("1");
			}
		});
		detLoc1.setVerticalAlignment(SwingConstants.TOP);
		detLoc1.setHorizontalAlignment(SwingConstants.CENTER);
		detLoc1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLoc1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addComponent(detLoc1, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblLoc1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detLoc1, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(205, 142, 171, 141);
		contentPane.add(panel_2);

		JLabel lblLoc2 = new JLabel("Location 2");
		lblLoc2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc2.setFont(new Font("Calibri Light", Font.PLAIN, 16));

		detLoc2 = new JLabel(orderD.setLabelKitchen(2));
		detLoc2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showItemLabel("2");
			}
		});
		detLoc2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		detLoc2.setVerticalAlignment(SwingConstants.TOP);
		detLoc2.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblLoc2, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(detLoc2, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblLoc2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detLoc2, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(393, 142, 171, 141);
		contentPane.add(panel_3);

		JLabel lblLoc3 = new JLabel("Location 3");
		lblLoc3.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc3.setFont(new Font("Calibri Light", Font.PLAIN, 16));

		detLoc3 = new JLabel(orderD.setLabelKitchen(3));
		detLoc3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showItemLabel("3");
			}
		});
		detLoc3.setVerticalAlignment(SwingConstants.TOP);
		detLoc3.setHorizontalAlignment(SwingConstants.CENTER);
		detLoc3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblLoc3, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(detLoc3, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblLoc3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detLoc3, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(17, 294, 171, 141);
		contentPane.add(panel_4);

		JLabel lblLoc4 = new JLabel("Location 4");
		lblLoc4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc4.setFont(new Font("Calibri Light", Font.PLAIN, 16));

		detLoc4 = new JLabel(orderD.setLabelKitchen(4));
		detLoc4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showItemLabel("4");
			}
		});
		detLoc4.setVerticalAlignment(SwingConstants.TOP);
		detLoc4.setHorizontalAlignment(SwingConstants.CENTER);
		detLoc4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(lblLoc4, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(detLoc4, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(lblLoc4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detLoc4, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(205, 294, 171, 141);
		contentPane.add(panel_5);

		JLabel lblLoc5 = new JLabel("Location 5");
		lblLoc5.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc5.setFont(new Font("Calibri Light", Font.PLAIN, 16));

		detLoc5 = new JLabel(orderD.setLabelKitchen(5));
		detLoc5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showItemLabel("5");
			}
		});
		detLoc5.setVerticalAlignment(SwingConstants.TOP);
		detLoc5.setHorizontalAlignment(SwingConstants.CENTER);
		detLoc5.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addComponent(lblLoc5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
				.addComponent(detLoc5, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(lblLoc5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detLoc5, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(393, 293, 171, 141);
		contentPane.add(panel_6);

		JLabel lblLoc6 = new JLabel("Location 6");
		lblLoc6.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc6.setFont(new Font("Calibri Light", Font.PLAIN, 16));

		detLoc6 = new JLabel(orderD.setLabelKitchen(6));
		detLoc6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showItemLabel("6");
			}
		});
		detLoc6.setVerticalAlignment(SwingConstants.TOP);
		detLoc6.setHorizontalAlignment(SwingConstants.CENTER);
		detLoc6.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addComponent(lblLoc6, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(detLoc6, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addComponent(lblLoc6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detLoc6, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 22, 561, 109);
		contentPane.add(panel);

		/********************************************
		 * Current Date and Time display
		 *********************************************/
		int interval = 100;
		new Timer(interval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblDateAndTime.setText(DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm:ss").format(LocalDateTime.now()));
				checkStatus();
			}

		}).start();

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
	 * Change the status of an order
	 *********************************************/

	protected void statusChange(String option, String loc) {

		order.updateOrderStatus(order.getIdOrderLocation(loc), option);

	}
	
	/********************************************
	 * Show items
	 *********************************************/

	protected void showItemLabel(String loc) {

		lblStatusSelected.setText(order.getStatusOrder(loc));
		lblLocationSelected.setText(loc);

	}
	
	/********************************************
	 * Check Status of the orders
	 *********************************************/

	protected void checkStatus() {
		if (lblStatusSelected.getText().contains("New"))
			btnInProgress.setEnabled(true);
		else
			btnInProgress.setEnabled(false);

		if (lblStatusSelected.getText().contains("In progress"))
			btnReady.setEnabled(true);
		else
			btnReady.setEnabled(false);
	}
	
	/********************************************
	 * Refresh all the orders
	 *********************************************/

	protected void resetLabels() {

		detLoc1.setText(orderD.setLabelKitchen(1));
		detLoc2.setText(orderD.setLabelKitchen(2));
		detLoc3.setText(orderD.setLabelKitchen(3));
		detLoc4.setText(orderD.setLabelKitchen(4));
		detLoc5.setText(orderD.setLabelKitchen(5));
		detLoc6.setText(orderD.setLabelKitchen(6));

	}
}
