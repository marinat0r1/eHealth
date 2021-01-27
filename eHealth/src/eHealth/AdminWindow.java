package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.h2.engine.User;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class AdminWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField userSelectionTextField;
	
	private UserDB userTable = new UserDB();

	
	public AdminWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1333, 570);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Account Management");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
		
		JButton viewUsersButton = new JButton("View Users");
		viewUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userTable.resultSetToTableModel(table, "user", "", 13);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		viewUsersButton.setBounds(34, 454, 169, 23);
		contentPane.add(viewUsersButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 60, 1291, 382);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton editUserButton = new JButton("Edit this User\r\n");
		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userTable.checkIfUsernameExistsInDB(userSelectionTextField.getText())) {
					EditUserWindow edtu = new EditUserWindow();
					edtu.createEditUserWindow(userSelectionTextField.getText());
				}
				else {
					showMessageDialog(null, "User not found", "Message",WARNING_MESSAGE);
					userSelectionTextField.setText("");
				}
				
			}
		});
		editUserButton.setBounds(954, 501, 173, 23);
		contentPane.add(editUserButton);
		
		JButton deleteButton = new JButton("Delete this User");
		deleteButton.setForeground(Color.RED);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userToDelete = userSelectionTextField.getText();
				
				if(userTable.checkIfUsernameExistsInDB(userToDelete)) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this User?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					if (confirm == 0) {
							userTable.deleteUserFromDB(userToDelete);
							String message = "User " + userToDelete + " succesfully deleted";
							showMessageDialog(null, message, "Message",WARNING_MESSAGE);
							userSelectionTextField.setText("");
					}
					else return;
				}
				else {
					showMessageDialog(null, "User not found", "Message",WARNING_MESSAGE);
					userSelectionTextField.setText("");
				}
			}
		});
		deleteButton.setBounds(1138, 501, 169, 23);
		contentPane.add(deleteButton);
		
		JButton refreshButton = new JButton("Refresh\r\n");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userTable.resultSetToTableModel(table, "user", "", 13);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshButton.setBounds(215, 454, 169, 23);
		contentPane.add(refreshButton);
		
		JLabel lblNewLabel = new JLabel("Please enter the username of the user you like to edit or delete:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(149, 488, 564, 42);
		contentPane.add(lblNewLabel);
		
		userSelectionTextField = new JTextField();
		userSelectionTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		userSelectionTextField.setBounds(723, 498, 221, 26);
		contentPane.add(userSelectionTextField);
		userSelectionTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 477, 1307, 12);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Leave Admin View");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave Admin view?\nAny unsaved Changes won't be saved!", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					dispose();
					LoginDialog lw = new LoginDialog();
			        lw.createLoginDialog();
				}
				else return;
			}
		});
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		btnNewButton.setBounds(1109, 6, 198, 29);
		contentPane.add(btnNewButton);
	}

	public void createAdminWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow aw = new AdminWindow();
					aw.setVisible(true);
					aw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}