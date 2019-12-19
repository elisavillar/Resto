package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.Order;
import restaurant.OrderDetails;

public class FormReceipt extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Order order = new Order();
	DefaultTableModel model = new DefaultTableModel();
	OrderDetails orderD = new OrderDetails();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormReceipt frame = new FormReceipt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormReceipt() {
		setTitle("Receipt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("/receipt.png")).getImage();

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackOrder();
			}
		});
		btnNewButton.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnNewButton.setBounds(57, 418, 89, 34);
		contentPane.add(btnNewButton);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		btnExit.setBounds(265, 418, 89, 34);
		contentPane.add(btnExit);

		JLabel imageMealForm = new JLabel("");
		imageMealForm.setHorizontalAlignment(SwingConstants.CENTER);
		imageMealForm.setIcon(new ImageIcon(img));
		imageMealForm.setBounds(291, 22, 107, 91);
		contentPane.add(imageMealForm);

		JLabel lblOrder = new JLabel("Id Order:");
		lblOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrder.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblOrder.setBounds(21, 22, 67, 23);
		contentPane.add(lblOrder);

		JLabel lblNumber = new JLabel(FormOrder.orderReceipt);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumber.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblNumber.setBounds(123, 22, 158, 23);
		contentPane.add(lblNumber);

		JLabel lblClient = new JLabel("Client:");
		lblClient.setHorizontalAlignment(SwingConstants.LEFT);
		lblClient.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblClient.setBounds(21, 56, 67, 23);
		contentPane.add(lblClient);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblLocation.setBounds(21, 90, 67, 23);
		contentPane.add(lblLocation);

		JLabel locationNumber = new JLabel(FormOrder.locationReceipt);
		locationNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		locationNumber.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		locationNumber.setBounds(123, 90, 158, 23);
		contentPane.add(locationNumber);

		JLabel lblName = new JLabel(FormOrder.clientReceipt);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblName.setBounds(123, 56, 158, 23);
		contentPane.add(lblName);

		JLabel receiptDetails = new JLabel(orderD.setLabelReceipt(FormOrder.orderReceipt));
		receiptDetails.setHorizontalAlignment(SwingConstants.CENTER);
		receiptDetails.setVerticalAlignment(SwingConstants.TOP);
		receiptDetails.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		receiptDetails.setBounds(21, 133, 361, 190);
		contentPane.add(receiptDetails);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(139, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblTotal.setBounds(76, 359, 67, 31);
		contentPane.add(lblTotal);

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		JLabel lblPrice = new JLabel(String.valueOf(formatter.format(order.calculateTotal(FormOrder.orderReceipt))));
		lblPrice.setForeground(new Color(139, 0, 0));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblPrice.setBounds(196, 359, 136, 31);
		contentPane.add(lblPrice);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelHeader.setBounds(10, 11, 384, 102);
		contentPane.add(panelHeader);

		JPanel panelTotal = new JPanel();
		panelTotal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTotal.setBounds(21, 338, 373, 69);
		contentPane.add(panelTotal);

	}

	protected void btnBackOrder() {
		dispose();
		FormOrder order = new FormOrder();
		order.setVisible(true);
		order.setLocationRelativeTo(null);
	}
}
