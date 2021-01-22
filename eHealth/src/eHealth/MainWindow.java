package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField radius;
	private JTextField dateField;
	private JTextField timeField;
	
	private User userUsed;

	
	public MainWindow(String username) {
		
		userUsed = new User(username);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("eHealth");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		lblNewLabel.setBounds(6, 0, 155, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please select your health problem:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(6, 92, 299, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Search radius:");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(6, 176, 136, 22);
		contentPane.add(lblNewLabel_1_1);
		
		radius = new JTextField();
		radius.setHorizontalAlignment(SwingConstants.RIGHT);
		radius.setColumns(10);
		radius.setBounds(195, 172, 62, 34);
		contentPane.add(radius);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("km");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(269, 176, 36, 22);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Reminder:");
		lblNewLabel_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(6, 278, 136, 22);
		contentPane.add(lblNewLabel_1_1_2);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"no reminder", "1 week before", "3 days before", "1 hour before", "10 minutes before"}));
		comboBox.setBounds(105, 275, 205, 34);
		contentPane.add(comboBox);
		
		JLabel lblUpcoming = new JLabel("Upcoming appointments:");
		lblUpcoming.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUpcoming.setBounds(390, 6, 354, 34);
		contentPane.add(lblUpcoming);
		
		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblWelcome.setBounds(6, 34, 123, 34);
		contentPane.add(lblWelcome);
		
		JLabel loggedInUser = new JLabel();
		loggedInUser.setText(userUsed.getUsername());
		loggedInUser.setForeground(new Color(0, 0, 0));
		loggedInUser.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		loggedInUser.setBounds(128, 34, 183, 34);
		contentPane.add(loggedInUser);
		
		JComboBox comboBoxProblem = new JComboBox();
		comboBoxProblem.setModel(new DefaultComboBoxModel(new String[] {"---", "eye pain", "weak vision", "watery eyes", "cough", "sniff", "fever", "headache", "itchy skin", "acne", "tootache", "gingivitis", "jaw pain"}));
		comboBoxProblem.setBounds(6, 126, 304, 38);
		contentPane.add(comboBoxProblem);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String doctorType;
				String radiusString = radius.getText();
				int radiusInt = 0;
				String date = dateField.getText();
				String time = timeField.getText();
				
				switch(comboBoxProblem.getSelectedIndex()) {
				case 0:
					//Warning
					showMessageDialog(null,"Please select your health problem!", "Warning", WARNING_MESSAGE);
					return;
				case 1,2,3:
					//Augenarzt
					doctorType = "ophthalmologist";
					break;
				case 4,5,6,7:
					//Hausarzt
					doctorType = "generaldoctor";
					break;
				case 8,9:
					//Hautarzt
					doctorType = "dermatologist";
				case 10,11,12:
					// Zahnarzt
					doctorType = "dentist";
					
				default:
					showMessageDialog(null,"Something went wrong.\nPlease try again!", "Warning", WARNING_MESSAGE);
					return;
				}
				
				if (radiusString.equals("") | isInteger(radiusString) == false) {
					radius.setBorder(new LineBorder(Color.RED, 1));
                	radius.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null, "Please enter an integer value!", "Warning", WARNING_MESSAGE);
                	return;
				}
				else {
					radius.setBorder(new LineBorder(Color.GREEN, 1));
                	radius.setBorder(new LineBorder(Color.GREEN, 1));
                	
                    try {
                    	radiusInt = Integer.parseInt(radiusString);
                    }catch(NumberFormatException exc) {
                    	showMessageDialog(null, "ERROR", "Warning", WARNING_MESSAGE);
                    	return;
                    } 
				}
				
				if (date.equals("")) {
                	dateField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid date!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	dateField.setBorder(new LineBorder(Color.GREEN, 1));
                }
				
				if (time.equals("")) {
                	timeField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid date!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	timeField.setBorder(new LineBorder(Color.GREEN, 1));
                }
			}
		});
		searchButton.setBounds(6, 358, 299, 29);
		contentPane.add(searchButton);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Date (YYYY-MM-DD):");
		lblNewLabel_1_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1.setBounds(6, 210, 193, 22);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Time (HH:MM):");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1_1.setBounds(6, 244, 187, 22);
		contentPane.add(lblNewLabel_1_1_2_1_1);
		
		dateField = new JTextField();
		dateField.setBounds(205, 210, 100, 26);
		contentPane.add(dateField);
		dateField.setColumns(10);
		
		timeField = new JTextField();
		timeField.setColumns(10);
		timeField.setBounds(205, 244, 100, 26);
		contentPane.add(timeField);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(Color.RED);
		logoutBtn.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out from eHealth?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					dispose();
					LoginDialog lw = new LoginDialog();
			        lw.createLoginDialog();
				}
				else return;
			}
		});
		logoutBtn.setBounds(516, 387, 117, 29);
		contentPane.add(logoutBtn);
		
		JButton quitBtn = new JButton("Quit");
		quitBtn.setForeground(Color.RED);
		quitBtn.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit eHealth?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					System.exit(0);
				}
				else return;
				
				
			}
		});
		quitBtn.setBounds(627, 387, 117, 29);
		contentPane.add(quitBtn);
		
	}
	
	public boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
	
	public static void createMainWindow(String usernameInput) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mw = new MainWindow(usernameInput);
					mw.setVisible(true);
					mw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
