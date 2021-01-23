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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField radius;
	private JTextField dateField;
	private JTextField timeField;
	private JTextField docIdField;
	private JTable appointmentsTable;
	private JTable searchResultTable;
	
	private String doctorType;
	private String appointmentTime;
	private String appointmentDate;
	
	private User userUsed;

	private DoctorDB docDB = new DoctorDB();
	private AppointmentsDB appDB = new AppointmentsDB();

	
	public MainWindow(String username) {
		
		userUsed = new User(username);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1177, 463);
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"no reminder", "1 week before", "3 days before", "1 hour before", "10 minutes before"}));
		comboBox.setBounds(105, 275, 205, 34);
		contentPane.add(comboBox);
		
		JLabel lblUpcoming = new JLabel("Upcoming appointments:");
		lblUpcoming.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUpcoming.setBounds(798, 1, 354, 34);
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
		
		JComboBox<String> comboBoxProblem = new JComboBox<String>();
		comboBoxProblem.setModel(new DefaultComboBoxModel<String>(new String[] {"---", "eye pain", "weak vision", "watery eyes", "cough", "sniff", "fever", "headache", "itchy skin", "acne", "tootache", "gingivitis", "jaw pain"}));
		comboBoxProblem.setBounds(6, 126, 304, 38);
		contentPane.add(comboBoxProblem);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String radiusString = radius.getText();
				int radiusInt = 0;
				appointmentDate = dateField.getText();
				appointmentTime = timeField.getText();
				
				switch(comboBoxProblem.getSelectedIndex()) {
				case 0:
					//Warning
					showMessageDialog(null,"Please select your health problem!", "Warning", WARNING_MESSAGE);
					return;
				case 1:
				case 2:
				case 3:
					//Augenarzt
					System.out.println("AU");
					doctorType = "Oculist";
					break;
				case 4:
				case 5: 
				case 6:
				case 7:
					//Hausarzt
					System.out.println("HA");
					doctorType = "FamilyDoctor";
					break;
				case 8:
				case 9:
					//Hautarzt
					System.out.println("HAT");
					doctorType = "Dermatologist";
					break;
				case 10:
				case 11:
				case 12:
					// Zahnarzt
					System.out.println("ZA");
					doctorType = "Dentist";
					break;
					
				default:
					showMessageDialog(null,"Please select a Health Problem!", "Warning", WARNING_MESSAGE);
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
				
				if (appointmentDate.equals("")) {
                	dateField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid date!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	dateField.setBorder(new LineBorder(Color.GREEN, 1));
                }
				
				if (appointmentTime.equals("")) {
                	timeField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid date!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	timeField.setBorder(new LineBorder(Color.GREEN, 1));
                }
				// distance calc
				String queryWhereCondition = "WHERE ";
				try {
					docDB.resultSetToTableModel(searchResultTable, doctorType, " ", userUsed, radiusInt);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
		});
		searchButton.setBounds(11, 374, 299, 29);
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
		logoutBtn.setBounds(906, 387, 117, 29);
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
		quitBtn.setBounds(1035, 387, 117, 29);
		contentPane.add(quitBtn);
		
		JScrollPane scrollPaneSearchResult = new JScrollPane();
		scrollPaneSearchResult.setBounds(431, 65, 330, 263);
		contentPane.add(scrollPaneSearchResult);
		
		searchResultTable = new JTable();
		scrollPaneSearchResult.setViewportView(searchResultTable);
		
		JLabel lblSearchResults = new JLabel("Search results");
		lblSearchResults.setBounds(429, 0, 239, 30);
		contentPane.add(lblSearchResults);
		
		JButton makeAppointmentBtn = new JButton("Make Appointment");
		makeAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String docIdString = docIdField.getText();
				int docId = 0;
                try {
                	docId = Integer.parseInt(docIdString);
                }catch(NumberFormatException exc) {
                	showMessageDialog(null, "ERROR", "Warning", WARNING_MESSAGE);
                	return;
                } 
				String queryWhere = " id = " + docId; 
				String appointmentDocFirstName = docDB.getStringColomnFromDB("firstname", doctorType, queryWhere);
				String appointmentDocLastName = docDB.getStringColomnFromDB("lastname", doctorType, queryWhere);
				String appointmentDocAddress = docDB.getStringColomnFromDB("address", doctorType, queryWhere);
				
				appDB.insertIntoAppointmentsDBTable(username, appointmentDocFirstName, appointmentDocLastName, appointmentDocAddress, appointmentDate, appointmentTime);
			}
		});
		makeAppointmentBtn.setBounds(594, 376, 167, 25);
		contentPane.add(makeAppointmentBtn);
		
		docIdField = new JTextField();
		docIdField.setBounds(430, 379, 114, 19);
		contentPane.add(docIdField);
		docIdField.setColumns(10);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID of the wanted Doctor");
		lblEnterTheId.setBounds(441, 335, 299, 15);
		contentPane.add(lblEnterTheId);
		
		JScrollPane scrollPanAppointments = new JScrollPane();
		scrollPanAppointments.setBounds(843, 65, 287, 164);
		contentPane.add(scrollPanAppointments);
		
		appointmentsTable = new JTable();
		scrollPanAppointments.setViewportView(appointmentsTable);
		
		JButton refreshAppointmentTableBtn = new JButton("Refresh");
		refreshAppointmentTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String queryWhere = "WHERE username = '" + username+"'";
				try {
					appDB.resultSetToTableModel(appointmentsTable, "Appointments", queryWhere);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshAppointmentTableBtn.setBounds(1012, 244, 117, 25);
		contentPane.add(refreshAppointmentTableBtn);
		
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
