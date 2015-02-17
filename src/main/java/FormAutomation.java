import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormAutomation extends JDialog {

	private static WebDriver driver = new FirefoxDriver();
	public static int formFlag;
	public static int recordNumber;
	private static final long serialVersionUID = 3190083073737509914L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public static void main(String[] args) throws IOException {
		try {
			FormAutomation dialog = new FormAutomation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(formFlag);
		System.out.println(recordNumber);
		automation();
	}

	private static void automation() throws IOException {
		// Read in the Excel file
		InputStream ExcelFileToRead = new FileInputStream(
				"US_Names_Addresses.xlsx");

		// Make a .xlsx workbook from .xlsx file
		XSSFWorkbook workBook = new XSSFWorkbook(ExcelFileToRead);

		// Get the sheet from the .xlsx workbook
		XSSFSheet sheet = workBook.getSheetAt(0);

		// Declare variables for manipulation of .xlsx workbook
		XSSFRow row;
		XSSFCell cell;

		// Define Iterators for the different sheets
		Iterator<Row> rowIterator = sheet.rowIterator();

		// Open Page, Log in, Start the form
		driver.get("https://aarontest-qa.asu.edu/content/graduate-long-form");

		// Wait for the page to load up!
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("edit-questions")));

		// Skip the column headings and already executed rows
		row = (XSSFRow) rowIterator.next();
		for (int i = 0; i < 1; i++) {
			row = (XSSFRow) rowIterator.next();
		}

		// Initialize the cell variable
		Iterator<Cell> cells = row.cellIterator();

		// Check if record already used
		cell = (XSSFCell) cells.next();
		for (int i = 1; i < 2; i++) {
			while (cell.getStringCellValue().equals("True")) {
				row = (XSSFRow) rowIterator.next();
			}

			// Mark done
			cell.setCellValue("True");

			// My Program Interest
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/a/span"))
					.click();
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/div/ul/li[5]"))
					.click();

			// My Start Date
			driver.findElement(
					By.xpath(".//*[@id='edit_start_date_chosen']/a/span"))
					.click();
			driver.findElement(
					By.xpath(".//*[@id='edit_start_date_chosen']/div/ul/li[3]"))
					.click();

			// First Name
			cell = (XSSFCell) cells.next();
			String fname = cell.getStringCellValue();
			driver.findElement(By.id("edit-first-name")).sendKeys(fname);

			// Last name
			cell = (XSSFCell) cells.next();
			String lname = cell.getStringCellValue();
			driver.findElement(By.id("edit-last-name")).sendKeys(lname);

			// Email
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-email")).sendKeys(
					fname + "." + lname + "@email.com");

			// Phone
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-phone")).sendKeys(
					cell.getStringCellValue());

			FileOutputStream fileOut = new FileOutputStream(
					"US_Names_Addresses.xlsx");
			workBook.write(fileOut);
			fileOut.close();

			// // Zip
			// cell = (XSSFCell) cells.next();
			// double zipd = cell.getNumericCellValue();
			// int zipn = (int) zipd;
			// String zip = Integer.toString(zipn);
			// driver.findElement(By.id("edit-zipcode")).sendKeys(zip);

			// // Street
			// cell = (XSSFCell) cells.next();
			// driver.findElement(By.id("edit-asu-rfi-dedupe-street-und-0-value"))
			// .sendKeys(cell.getStringCellValue());
			//
			// // City
			// cell = (XSSFCell) cells.next();
			// driver.findElement(By.id("edit-asu-rfi-dedupe-city-und-0-value"))
			// .sendKeys(cell.getStringCellValue());
			//
			// // State
			// cell = (XSSFCell) cells.next();
			// driver.findElement(By.id("edit-asu-rfi-dedupe-state-und-0-value"))
			// .sendKeys(cell.getStringCellValue());
			//

			//

			//
			// // Birth Date
			// driver.findElement(
			// By.id("edit-asu-rfi-dedupe-birth-date-und-0-value-datepicker-popup-0"))
			// .clear();
			// driver.findElement(
			// By.id("edit-asu-rfi-dedupe-birth-date-und-0-value-datepicker-popup-0"))
			// .sendKeys("01/01/2014");
			//
			// // Country
			// driver.findElement(By.id("edit-asu-rfi-dedupe-country-und-0-value"))
			// .sendKeys("United States");
			//
			// // This student is an international student checkbox
			//
			// // Veteran Status
			//
			// // SMS
			//
			// // This submission matches multiple items checkbox
			//
			// // Status drop down
			//
			// // Comments
			// //
			// driver.findElement(By.id("edit-asu-rfi-dedupe-comments-und-0-value"))
			// // .sendKeys("May the force be with you!");
			//
			// // Requesting Site dropdown
			// new Select(driver.findElement(By
			// .id("edit-asu-rfi-dedupe-client-reference-und")))
			// .selectByVisibleText("TEST");
			//
			// // Prod or test dropdown
			// new Select(driver.findElement(By
			// .id("edit-asu-rfi-dedupe-prod-test-flag-und")))
			// .selectByVisibleText("Prod");
			//
			// // Nid
			// cell = (XSSFCell) cells.next();
			// double nidd = cell.getNumericCellValue();
			// int nidn = (int) nidd;
			// String nid = Integer.toString(nidn);
			// driver.findElement(
			// By.id("edit-asu-rfi-dedupe-remote-nid-und-0-value"))
			// .sendKeys(nid);
			//
			// // Hash
			// cell = (XSSFCell) cells.next();
			// driver.findElement(
			// By.id("edit-asu-rfi-dedupe-request-hash-und-0-value"))
			// .sendKeys(cell.getStringCellValue());
			//
			// // Publish button
			// driver.findElement(By.id("edit-title")).clear();
			// driver.findElement(By.id("edit-title")).sendKeys("test" + i);
			// driver.findElement(By.id("edit-submit")).click();
			//
			// // Wait
			// WebDriverWait wait = new WebDriverWait(driver, 300);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By
			// .id("edit-subject")));
		}

	}

	public FormAutomation() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 40, 122, 33);
		contentPanel.add(panel);

		JLabel label = new JLabel("Select Form: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(189, 40, 217, 33);
		contentPanel.add(panel_1);

		String[] items = { "form1", "form2" };
		// Windows builder does not support JRE 7 yet -> hence the warning
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JComboBox comboBox = new JComboBox(items);
		comboBox.setPreferredSize(new Dimension(200, 25));
		panel_1.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 113, 153, 33);
		contentPanel.add(panel_2);

		JLabel label_1 = new JLabel("Select # of records:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(label_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(235, 113, 122, 33);
		contentPanel.add(panel_3);

		textField = new JTextField();
		textField.setColumns(10);
		panel_3.add(textField);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(98, 191, 226, 37);
		contentPanel.add(panel_4);

		JButton button = new JButton("Let the automation begin!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formFlag = comboBox.getSelectedIndex();
				recordNumber = Integer.parseInt(textField.getText());
				dispose();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(button);
	}

}
