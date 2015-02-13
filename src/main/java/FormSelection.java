import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FormSelection extends JFrame {

	public static int formFlag;
	public static int recordNumber;
	public static int flag = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1028709970893522753L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public FormSelection() {
		// Window properties
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Form Selection");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(27, 47, 122, 33);
		contentPane.add(panel);

		JLabel lblSelectForm = new JLabel("Select Form: ");
		lblSelectForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblSelectForm);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(196, 47, 217, 33);
		contentPane.add(panel_1);

		String[] items = { "form1", "form2" };
		// Windows builder does not support JRE 7 yet -> hence the warning
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JComboBox<String> comboBox = new JComboBox(items);
		comboBox.setPreferredSize(new Dimension(200, 25));

		panel_1.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 117, 153, 33);
		contentPane.add(panel_2);

		JLabel lblSelectOf = new JLabel("Select # of records:");
		lblSelectOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblSelectOf);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(247, 117, 122, 33);
		contentPane.add(panel_3);

		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(100, 192, 226, 33);
		contentPane.add(panel_4);

		JButton btnLetTheAutomation = new JButton("Let the automation begin!");
		btnLetTheAutomation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formFlag = comboBox.getSelectedIndex();
				recordNumber = Integer.parseInt(textField.getText());
				flag = 1;
			}
		});
		btnLetTheAutomation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(btnLetTheAutomation);
	}
}
