package GUI;

import javax.swing.JPanel;

public class WelcomePanel extends JPanel{

	public WelcomePanel() {
	setPreferredSize(new Dimension(1200, 700));
		setLayout(new BorderLayout());
		
		title_panel = new JPanel();
		
		add(title_panel);
		
		title_label = new JLabel("Dropquote");
		title_label.setFont(title_label.getFont().deriveFont(40.0f));
		title_panel.add(title_label);
		
		button_panel = new JPanel();
		add(button_panel, BorderLayout.SOUTH);
		
		user_help = new JButton("User Help");
		user_help.setFont(user_help.getFont().deriveFont(30.0f));
		button_panel.add(user_help);
		
		admin_help = new JButton("Admin Help");
		admin_help.setFont(admin_help.getFont().deriveFont(30.0f));
		admin_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog (null, "The user mode will allow user to play the dropquote game.", "User Mode Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button_panel.add(admin_help);
		user_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog (null, "The admin mode will allow user to input a customize dropquote game.", "Admin Mode Help", JOptionPane.INFORMATION_MESSAGE);

				
			}
		});	
	}

}
