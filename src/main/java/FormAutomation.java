import javax.swing.SwingUtilities;

public class FormAutomation {
	public static void main(String[] args) {
		// Start GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FormSelection();
			}
		});
		int formFlag = FormSelection.formFlag;
		int recordNumber = FormSelection.recordNumber;
		int i = 0;
		while (i == 0) {
			if (FormSelection.flag == 1) {
				i = 1;
			} else {

			}
		}
		System.out.println(formFlag + " " + recordNumber);
	}

}
