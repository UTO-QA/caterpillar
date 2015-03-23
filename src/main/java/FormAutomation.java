import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

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
	public static final Map<String, String> STATE_MAP;
	// list of state codes
	static {
		STATE_MAP = new HashMap<String, String>();
		STATE_MAP.put("AL", "Alabama");
		STATE_MAP.put("AK", "Alaska");
		STATE_MAP.put("AB", "Alberta");
		STATE_MAP.put("AZ", "Arizona");
		STATE_MAP.put("AR", "Arkansas");
		STATE_MAP.put("BC", "British Columbia");
		STATE_MAP.put("CA", "California");
		STATE_MAP.put("CO", "Colorado");
		STATE_MAP.put("CT", "Connecticut");
		STATE_MAP.put("DE", "Delaware");
		STATE_MAP.put("DC", "District Of Columbia");
		STATE_MAP.put("FL", "Florida");
		STATE_MAP.put("GA", "Georgia");
		STATE_MAP.put("GU", "Guam");
		STATE_MAP.put("HI", "Hawaii");
		STATE_MAP.put("ID", "Idaho");
		STATE_MAP.put("IL", "Illinois");
		STATE_MAP.put("IN", "Indiana");
		STATE_MAP.put("IA", "Iowa");
		STATE_MAP.put("KS", "Kansas");
		STATE_MAP.put("KY", "Kentucky");
		STATE_MAP.put("LA", "Louisiana");
		STATE_MAP.put("ME", "Maine");
		STATE_MAP.put("MB", "Manitoba");
		STATE_MAP.put("MD", "Maryland");
		STATE_MAP.put("MA", "Massachusetts");
		STATE_MAP.put("MI", "Michigan");
		STATE_MAP.put("MN", "Minnesota");
		STATE_MAP.put("MS", "Mississippi");
		STATE_MAP.put("MO", "Missouri");
		STATE_MAP.put("MT", "Montana");
		STATE_MAP.put("NE", "Nebraska");
		STATE_MAP.put("NV", "Nevada");
		STATE_MAP.put("NB", "New Brunswick");
		STATE_MAP.put("NH", "New Hampshire");
		STATE_MAP.put("NJ", "New Jersey");
		STATE_MAP.put("NM", "New Mexico");
		STATE_MAP.put("NY", "New York");
		STATE_MAP.put("NF", "Newfoundland");
		STATE_MAP.put("NC", "North Carolina");
		STATE_MAP.put("ND", "North Dakota");
		STATE_MAP.put("NT", "Northwest Territories");
		STATE_MAP.put("NS", "Nova Scotia");
		STATE_MAP.put("NU", "Nunavut");
		STATE_MAP.put("OH", "Ohio");
		STATE_MAP.put("OK", "Oklahoma");
		STATE_MAP.put("ON", "Ontario");
		STATE_MAP.put("OR", "Oregon");
		STATE_MAP.put("PA", "Pennsylvania");
		STATE_MAP.put("PE", "Prince Edward Island");
		STATE_MAP.put("PR", "Puerto Rico");
		STATE_MAP.put("QC", "Quebec");
		STATE_MAP.put("RI", "Rhode Island");
		STATE_MAP.put("SK", "Saskatchewan");
		STATE_MAP.put("SC", "South Carolina");
		STATE_MAP.put("SD", "South Dakota");
		STATE_MAP.put("TN", "Tennessee");
		STATE_MAP.put("TX", "Texas");
		STATE_MAP.put("UT", "Utah");
		STATE_MAP.put("VT", "Vermont");
		STATE_MAP.put("VI", "Virgin Islands");
		STATE_MAP.put("VA", "Virginia");
		STATE_MAP.put("WA", "Washington");
		STATE_MAP.put("WV", "West Virginia");
		STATE_MAP.put("WI", "Wisconsin");
		STATE_MAP.put("WY", "Wyoming");
		STATE_MAP.put("YT", "Yukon Territory");
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		try {
			FormAutomation dialog = new FormAutomation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (formFlag) {
		case 0:
			automation0(recordNumber);
			break;
		case 1:
			automation1(recordNumber);
			break;
		case 2:
			automation2(recordNumber);
			break;
		default:
			System.out.println("Error handling here!");
		}
	}

	private static void automation0(int recordNumber) throws IOException,
			InterruptedException {
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
		row = (XSSFRow) rowIterator.next();

		// Initialize the cell variable
		Iterator<Cell> cells = row.cellIterator();

		cell = (XSSFCell) cells.next();
		for (int i = 0; i < recordNumber; i++) {

			// Check if record already used
			while (rowIterator.hasNext()) {
				if (cell.getStringCellValue().equals("True")) {
					row = (XSSFRow) rowIterator.next();
					cells = row.cellIterator();
					cell = (XSSFCell) cells.next();
				} else {
					break;
				}
			}

			// Mark done
			cell.setCellValue("True");

			// My Program Interest
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/a/span"))
					.click();
			Random rand = new Random();
			int random = rand.nextInt(8) + 2;
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/div/ul/li["
							+ random + "]")).click();
			String programInterest = driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/a/span"))
					.getText();
			cell = (XSSFCell) cells.next();
			cell.setCellValue(programInterest);

			// My Start Date
			driver.findElement(
					By.xpath(".//*[@id='edit_start_date_chosen']/a/span"))
					.click();
			random = rand.nextInt(4) + 2;
			driver.findElement(
					By.xpath(".//*[@id='edit_start_date_chosen']/div/ul/li["
							+ random + "]")).click();
			String startDate = driver.findElement(
					By.xpath(".//*[@id='edit_start_date_chosen']/a/span"))
					.getText();
			cell = (XSSFCell) cells.next();
			cell.setCellValue(startDate);

			// First Name
			cell = (XSSFCell) cells.next();
			String fname = cell.getStringCellValue();
			driver.findElement(By.id("edit-first-name")).sendKeys(fname);

			// Last name
			cell = (XSSFCell) cells.next();
			String lname = cell.getStringCellValue();
			driver.findElement(By.id("edit-last-name")).sendKeys(lname);

			// Email
			driver.findElement(By.id("edit-email")).sendKeys(
					fname + "." + lname + "@email.com");

			// Phone
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-phone")).sendKeys(
					cell.getStringCellValue());

			// Country
			cell = (XSSFCell) cells.next();
			driver.findElement(
					By.xpath(".//*[@id='rfi_country_chosen']/a/span")).click();
			driver.findElement(
					By.xpath(".//*[@id='rfi_country_chosen']/div/div/input"))
					.sendKeys(cell.getStringCellValue() + "\n");

			// Postal code
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-zipcode")).sendKeys(
					cell.getStringCellValue());

			// Date of Birth
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-birthdate")).sendKeys("08/25/1991");

			// Military Status
			cell = (XSSFCell) cells.next();
			driver.findElement(
					By.xpath(".//*[@id='edit-military']/div[3]/label/span/i"))
					.click();
			cell.setCellValue(driver.findElement(
					By.xpath(".//*[@id='edit-military']/div[3]/label/span/i"))
					.getText());

			// Address
			cell = (XSSFCell) cells.next();
			driver.findElement(By.xpath(".//*[@id='edit-address']")).sendKeys(
					cell.getStringCellValue());

			// City
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-city")).sendKeys(
					cell.getStringCellValue());

			// State
			cell = (XSSFCell) cells.next();
			driver.findElement(By.xpath(".//*[@id='rfi_state_chosen']/a"))
					.click();
			String state = STATE_MAP.get(cell.getStringCellValue());
			driver.findElement(
					By.xpath(".//*[@id='rfi_state_chosen']/div/div/input"))
					.sendKeys(state + "\n");

			// Comment
			driver.findElement(By.id("edit-questions")).sendKeys(
					"May the force be with you!?.");

			// request info
			driver.findElement(By.id("edit-submit")).click();

			// confirmation
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath((".//*[@id='asu_footer']/ul/li[6]/a"))));
			if (driver
					.getPageSource()
					.contains(
							"Thank you for your interest in ASU. An ASU representative will contact you soon!")) {
				cell = (XSSFCell) cells.next();
				cell.setCellValue("True");
			}

			FileOutputStream fileOut = new FileOutputStream(
					"US_Names_Addresses.xlsx");
			workBook.write(fileOut);
			fileOut.close();

			// Open Page, Log in, Start the form
			driver.get("https://aarontest-qa.asu.edu/content/graduate-long-form");

			// Wait for the page to load up!
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.id("edit-questions")));

		}

		driver.close();

	}

	private static void automation1(int recordNumber) throws IOException,
			InterruptedException {
		// Read in the Excel file
		InputStream ExcelFileToRead = new FileInputStream(
				"International_Names_Addresses.xlsx");

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
		row = (XSSFRow) rowIterator.next();

		// Initialize the cell variable
		Iterator<Cell> cells = row.cellIterator();

		cell = (XSSFCell) cells.next();
		for (int i = 0; i < recordNumber; i++) {

			// Check if record already used
			while (rowIterator.hasNext()) {
				if (cell.getStringCellValue().equals("True")) {
					row = (XSSFRow) rowIterator.next();
					cells = row.cellIterator();
					cell = (XSSFCell) cells.next();
				} else {
					break;
				}
			}

			// Mark done
			cell.setCellValue("True");

			// My Program Interest
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/a/span"))
					.click();
			Random rand = new Random();
			int random = rand.nextInt(8) + 2;
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/div/ul/li["
							+ random + "]")).click();

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
			driver.findElement(By.id("edit-email")).sendKeys(
					fname + "." + lname + "@email.com");

			// Phone
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-phone")).sendKeys(
					cell.getStringCellValue());

			// Country
			cell = (XSSFCell) cells.next();
			driver.findElement(
					By.xpath(".//*[@id='rfi_country_chosen']/a/span")).click();
			driver.findElement(
					By.xpath(".//*[@id='rfi_country_chosen']/div/div/input"))
					.sendKeys(cell.getStringCellValue() + "\n");

			// Postal code
			cell = (XSSFCell) cells.next();
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				driver.findElement(By.id("edit-zipcode")).sendKeys(
						cell.getStringCellValue());
			} else {
				String zip = String.valueOf((int) cell.getNumericCellValue());
				driver.findElement(By.id("edit-zipcode")).sendKeys(zip);
			}

			// Date of Birth
			driver.findElement(By.id("edit-birthdate")).sendKeys("08/25/1991");

			// Military Status
			driver.findElement(
					By.xpath(".//*[@id='req_info_form']/div[10]/label/span/i"))
					.click();

			// Address
			cell = (XSSFCell) cells.next();
			driver.findElement(By.xpath(".//*[@id='edit-address']")).sendKeys(
					cell.getStringCellValue());

			// City
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-city")).sendKeys(
					cell.getStringCellValue());

			// Comment
			driver.findElement(By.id("edit-questions")).sendKeys(
					"May the force be with you!?.");

			// request info
			driver.findElement(By.id("edit-submit")).click();

			// confirmation
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath((".//*[@id='asu_footer']/ul/li[6]/a"))));
			if (driver
					.getPageSource()
					.contains(
							"Thank you for your interest in ASU. An ASU representative will contact you soon!")) {
				cell = (XSSFCell) cells.next();
				cell.setCellValue("True");
			}

			FileOutputStream fileOut = new FileOutputStream(
					"International_Names_Addresses.xlsx");
			workBook.write(fileOut);
			fileOut.close();

			// Open Page, Log in, Start the form
			driver.get("https://aarontest-qa.asu.edu/content/graduate-long-form");

			// Wait for the page to load up!
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.id("edit-questions")));
		}

		driver.close();

	}

	private static void automation2(int recordNumber) throws IOException,
			InterruptedException {
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
		row = (XSSFRow) rowIterator.next();

		// Initialize the cell variable
		Iterator<Cell> cells = row.cellIterator();

		cell = (XSSFCell) cells.next();
		for (int i = 0; i < recordNumber; i++) {

			// Check if record already used
			while (rowIterator.hasNext()) {
				if (cell.getStringCellValue().equals("True")) {
					row = (XSSFRow) rowIterator.next();
					cells = row.cellIterator();
					cell = (XSSFCell) cells.next();
				} else {
					break;
				}
			}

			// Mark done
			cell.setCellValue("True");

			// My Program Interest
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/a/span"))
					.click();
			Random rand = new Random();
			int random = rand.nextInt(8) + 2;
			driver.findElement(
					By.xpath(".//*[@id='edit_program_code_chosen']/div/ul/li["
							+ random + "]")).click();

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
			driver.findElement(By.id("edit-email")).sendKeys(
					fname + "." + lname + "@email.com");

			// Phone
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-phone")).sendKeys(
					cell.getStringCellValue());

			// Country
			cell = (XSSFCell) cells.next();
			driver.findElement(
					By.xpath(".//*[@id='rfi_country_chosen']/a/span")).click();
			driver.findElement(
					By.xpath(".//*[@id='rfi_country_chosen']/div/div/input"))
					.sendKeys(cell.getStringCellValue() + "\n");

			// Postal code
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-zipcode")).sendKeys(
					cell.getStringCellValue());

			// Date of Birth
			driver.findElement(By.id("edit-birthdate")).sendKeys("08/25/1991");

			// Military Status
			driver.findElement(
					By.xpath(".//*[@id='edit-military']/div[3]/label/span/i"))
					.click();

			// Address
			cell = (XSSFCell) cells.next();
			driver.findElement(By.xpath(".//*[@id='edit-address']")).sendKeys(
					cell.getStringCellValue());

			// City
			cell = (XSSFCell) cells.next();
			driver.findElement(By.id("edit-city")).sendKeys(
					cell.getStringCellValue());

			// State
			cell = (XSSFCell) cells.next();
			driver.findElement(By.xpath(".//*[@id='rfi_state_chosen']/a"))
					.click();
			String state = STATE_MAP.get(cell.getStringCellValue());
			driver.findElement(
					By.xpath(".//*[@id='rfi_state_chosen']/div/div/input"))
					.sendKeys(state + "\n");

			// Comment
			driver.findElement(By.id("edit-questions")).sendKeys(
					"May the force be with you!?.");

			// request info
			driver.findElement(By.id("edit-submit")).click();

			// confirmation
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath((".//*[@id='asu_footer']/ul/li[6]/a"))));
			if (driver
					.getPageSource()
					.contains(
							"Thank you for your interest in ASU. An ASU representative will contact you soon!")) {
				cell = (XSSFCell) cells.next();
				cell.setCellValue("True");
			}

			FileOutputStream fileOut = new FileOutputStream(
					"US_Names_Addresses.xlsx");
			workBook.write(fileOut);
			fileOut.close();

			// Open Page, Log in, Start the form
			driver.get("https://aarontest-qa.asu.edu/content/graduate-long-form");

			// Wait for the page to load up!
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.id("edit-questions")));

		}

		driver.close();

	}

	public FormAutomation() {
		setModal(true);
		setBounds(100, 100, 752, 283);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 40, 98, 33);
		contentPanel.add(panel);

		JLabel label = new JLabel("Select Form: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(173, 40, 573, 33);
		contentPanel.add(panel_1);

		String[] items = {
				"Grad Contact Transfer No Opportunity Veteran With Comment USA",
				"Grad Contact First Time Grad No Opportunity comment added International",
				"Grad Contact First Time Grad No Opportunity USA" };

		// Windows builder does not support JRE 7 yet -> hence the warning
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JComboBox comboBox = new JComboBox(items);
		comboBox.setPreferredSize(new Dimension(525, 25));
		panel_1.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 113, 153, 33);
		contentPanel.add(panel_2);

		JLabel label_1 = new JLabel("Select # of records:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(label_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(173, 113, 185, 33);
		contentPanel.add(panel_3);

		textField = new JTextField();
		textField.setColumns(10);
		panel_3.add(textField);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(306, 183, 226, 37);
		contentPanel.add(panel_4);

		JButton button = new JButton("Fire up the automation!");
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
