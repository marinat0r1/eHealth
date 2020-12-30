package eHealth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField street;
	private JTextField number;
	private JTextField zipcode;
	private JTextField city;
	private JTextField dateofbirth;
	private JTextField healthinfo;
	private JTextField insurancename;
	private JTextField newUsernameField;
	private JPasswordField newPasswordField;
	private JPasswordField RepeatPasswordField;

	
	public RegisterWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Create your new Account:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 79, 95, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLastname.setBounds(27, 107, 95, 16);
		contentPane.add(lblLastname);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblStreet.setBounds(27, 135, 95, 16);
		contentPane.add(lblStreet);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNumber.setBounds(27, 163, 95, 16);
		contentPane.add(lblNumber);
		
		JLabel lblZipcode = new JLabel("Zip-Code:");
		lblZipcode.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblZipcode.setBounds(27, 191, 95, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCity.setBounds(27, 219, 95, 16);
		contentPane.add(lblCity);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(27, 247, 102, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblHealthInformation = new JLabel("Health information:");
		lblHealthInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHealthInformation.setBounds(27, 275, 140, 16);
		contentPane.add(lblHealthInformation);
		
		JLabel lblInsurenceType = new JLabel("Insurence type:");
		lblInsurenceType.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsurenceType.setBounds(27, 303, 124, 16);
		contentPane.add(lblInsurenceType);
		
		JLabel lblInsuranceName = new JLabel("Insurance name:");
		lblInsuranceName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsuranceName.setBounds(27, 331, 124, 16);
		contentPane.add(lblInsuranceName);
		
		firstname = new JTextField();
		firstname.setBounds(187, 75, 243, 26);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(187, 103, 243, 26);
		contentPane.add(lastname);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(187, 131, 243, 26);
		contentPane.add(street);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(187, 159, 243, 26);
		contentPane.add(number);
		
		zipcode = new JTextField();
		zipcode.setColumns(10);
		zipcode.setBounds(187, 187, 243, 26);
		contentPane.add(zipcode);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(187, 215, 243, 26);
		contentPane.add(city);
		
		dateofbirth = new JTextField();
		dateofbirth.setColumns(10);
		dateofbirth.setBounds(187, 243, 243, 26);
		contentPane.add(dateofbirth);
		
		healthinfo = new JTextField();
		healthinfo.setColumns(10);
		healthinfo.setBounds(187, 271, 243, 26);
		contentPane.add(healthinfo);
		
		insurancename = new JTextField();
		insurancename.setColumns(10);
		insurancename.setBounds(187, 327, 243, 26);
		contentPane.add(insurancename);
		
		JRadioButton publicbtn = new JRadioButton("public");
		publicbtn.setBounds(187, 300, 95, 23);
		contentPane.add(publicbtn);
		
		JRadioButton privatebtn = new JRadioButton("private");
		privatebtn.setBounds(294, 300, 95, 23);
		contentPane.add(privatebtn);
		
		JButton ConfirmBtn = new JButton("Confirm and Register");
		ConfirmBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		ConfirmBtn.setBounds(263, 467, 167, 29);
		contentPane.add(ConfirmBtn);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//rw.dispose();
			}
		});
		btnNewButton.setBounds(129, 467, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblUsername.setBounds(27, 359, 124, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPassword.setBounds(27, 387, 124, 16);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("repeat password:");
		lblRepeatPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblRepeatPassword.setBounds(27, 415, 124, 16);
		contentPane.add(lblRepeatPassword);
		
		newUsernameField = new JTextField();
		newUsernameField.setColumns(10);
		newUsernameField.setBounds(187, 355, 243, 26);
		contentPane.add(newUsernameField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(187, 383, 243, 26);
		contentPane.add(newPasswordField);
		
		RepeatPasswordField = new JPasswordField();
		RepeatPasswordField.setBounds(187, 411, 243, 26);
		contentPane.add(RepeatPasswordField);
	}
	
	public void createRegisterWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow rw = new RegisterWindow();
					rw.setVisible(true);
					rw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
