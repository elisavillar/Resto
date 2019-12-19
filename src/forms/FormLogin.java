package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import restaurant.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JComboBox;

public class FormLogin extends JFrame implements ActionListener {

	/********************************************
	 * Variables declaration
	 *********************************************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private String[] userType = { "Server", "Kitchen", "Admin", "Client" };
	private JComboBox<?> comboBoxType = new JComboBox<Object>(userType);

	/********************************************
	 * Launch the application
	 *********************************************/
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin frame = new FormLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static String userLogged = "", typeLogged = "";

	/********************************************
	 * Create the frame
	 *********************************************/
	public FormLogin() {
		setTitle("Resto v1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/********************************************
		 * Panel's content
		 *********************************************/

		JLabel lblWelcome = new JLabel(
				"<html>Welcome to Restaurant Managment Resto v1.<br> Enter a valid Username and password</html>");
		lblWelcome.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblWelcome.setBounds(23, 11, 332, 55);
		contentPane.add(lblWelcome);

		JLabel imageLogin = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login4.png")).getImage();
		imageLogin.setIcon(new ImageIcon(img));
		imageLogin.setBounds(277, 49, 128, 142);
		contentPane.add(imageLogin);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblUsername.setBounds(23, 80, 86, 14);
		contentPane.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(118, 77, 123, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblPassword.setBounds(23, 117, 86, 14);
		contentPane.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(119, 114, 122, 20);
		contentPane.add(txtPassword);

		JLabel lblTypeOfUser = new JLabel("Type of user:");
		lblTypeOfUser.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblTypeOfUser.setBounds(23, 149, 86, 24);
		contentPane.add(lblTypeOfUser);

		comboBoxType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		comboBoxType.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		comboBoxType.setBounds(118, 148, 123, 22);
		contentPane.add(comboBoxType);

		/********************************************
		 * Buttons
		 *********************************************/

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkClientGuest())
					checkNotNullInput();
			}
		});
		btnLogin.setBounds(23, 213, 89, 29);
		contentPane.add(btnLogin);
		btnExit.setBounds(122, 213, 89, 29);
		contentPane.add(btnExit);
	}

	/********************************************
	 * Login
	 *********************************************/
	protected void login(String type) {

		String username = txtUsername.getText();
		String pass = String.valueOf(txtPassword.getPassword());

		User userGestion = new User();

		User user2 = new User();
		user2.setUsername(username);
		user2.setPassword(pass);
		user2.setType(type);

		User user = userGestion.getUser(user2);

		if (user != null) {
			dispose();

			if (type != "Client") {
				FormHome home = new FormHome();
				home.setVisible(true);
				home.setLocationRelativeTo(null);
			} else
				checkOrderClient();

		} else {
			JOptionPane.showMessageDialog(contentPane, "Invalid Login", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/*************************************************
	 * Check that username and password are not empty
	 *************************************************/

	protected void checkNotNullInput() {
		userLogged = txtUsername.getText();
		String typeSelected = comboBoxType.getSelectedItem().toString();
		typeLogged = typeSelected;
		if (txtUsername.getText().isEmpty())
			JOptionPane.showMessageDialog(contentPane, "Please enter username", "Error", JOptionPane.ERROR_MESSAGE);
		else if (String.valueOf(txtPassword.getPassword()).length() == 0)
			JOptionPane.showMessageDialog(contentPane, "Please enter password", "Error", JOptionPane.ERROR_MESSAGE);
		else
			login(typeSelected);
	}

	/********************************************
	 * Check if the client is type Guest
	 *********************************************/
	protected Boolean checkClientGuest() {

		if (txtUsername.getText().contains("Guest")) {
			JOptionPane.showMessageDialog(contentPane,
					"<html>You need to have an username to login<br>"
							+ "Please contact the server for more information</html>",
					"Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}

		return false;
	}

	/********************************************
	 * Check if the client has a current order
	 *********************************************/
	protected void checkOrderClient() {
		Order order = new Order();
		FormClientHome frame = new FormClientHome();

		if (order.getIdOrderClient(FormLogin.userLogged) == null) {
			JOptionPane.showMessageDialog(contentPane,
					"<html>You don't have any current order<br>"
							+ "Please contact the server for more information</html>",
					"Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
