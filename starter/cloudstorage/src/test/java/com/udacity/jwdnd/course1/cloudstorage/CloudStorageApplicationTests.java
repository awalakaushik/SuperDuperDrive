package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private static final String FIRST_NAME = "Utta";
	private static final String LAST_NAME = "Dev";
	private static final String USERNAME = "adev";
	private static final String PASSWORD = "adev";

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testHomePageIsNotAccessibleBeforeLoggingIn() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testUserLoginAndLogoutFlow() throws InterruptedException {
		SignupUser();
		LoginUser();

		Assertions.assertEquals("Home", driver.getTitle());

		// Logout user
		WebElement element = driver.findElement(By.id("logout"));
		element.click();
		Thread.sleep(1000);

		Assertions.assertEquals("Login", driver.getTitle());

		// test accessing home page
		driver.get(String.format("http://localhost:%d/home", this.port));
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testAddNote() throws InterruptedException {

		AddNote();

		WebElement addedNote = driver.findElement(By.id("note-title-view"));
		Assertions.assertEquals("Test Note", addedNote.getText());
	}

	@Test
	public void testEditNote() throws InterruptedException {

		AddNote();
		EditNote();

		WebElement editedNote = driver.findElement(By.id("note-title-view"));
		Assertions.assertEquals("Test Note!", editedNote.getText());

	}

	@Test
	public void testDeleteNote() throws InterruptedException {
		AddNote();
		DeleteNote();

		boolean isDeletedNoteDisplayed = driver.findElements(By.id("note-title-view")).isEmpty();
		Assertions.assertTrue(isDeletedNoteDisplayed);
	}

	@Test
	public void testAddCredentials() throws InterruptedException {

		AddCredentials();

		WebElement addedCredentials = driver.findElement(By.id("credential-username-view"));
		Assertions.assertEquals("adev", addedCredentials.getText());
	}

	@Test
	public void testEditCredentials() throws InterruptedException {

		AddCredentials();
		EditCredentials();

		WebElement editedCredentials = driver.findElement(By.id("credential-username-view"));
		Assertions.assertEquals("adev123", editedCredentials.getText());

	}

	@Test
	public void testDeleteCredentials() throws InterruptedException {
		AddCredentials();
		DeleteCredentials();

		boolean isDeletedCredentialDisplayed = driver.findElements(By.id("credential-url-view")).isEmpty();
		Assertions.assertTrue(isDeletedCredentialDisplayed);
	}

	private void SignupUser() throws InterruptedException {
		driver.get(String.format("http://localhost:%d/signup", this.port));
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("inputFirstName"));
		element.sendKeys(FIRST_NAME);

		element = driver.findElement(By.id("inputLastName"));
		element.sendKeys(LAST_NAME);

		element = driver.findElement(By.id("inputUsername"));
		element.sendKeys(USERNAME);

		element = driver.findElement(By.id("inputPassword"));
		element.sendKeys(PASSWORD);

		element = driver.findElement(By.id("signup"));
		element.click();
		Thread.sleep(1000);
	}

	private void LoginUser() throws InterruptedException {
		driver.get(String.format("http://localhost:%d/login", this.port));
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("inputUsername"));
		element.sendKeys(USERNAME);

		element = driver.findElement(By.id("inputPassword"));
		element.sendKeys(PASSWORD);

		element = driver.findElement(By.id("login"));
		element.click();
		Thread.sleep(1000);
	}

	private void AddNote() throws InterruptedException {
		SignupUser();
		LoginUser();

		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(500);

		WebElement showNoteModalButton = driver.findElement(By.id("show-note-modal"));
		showNoteModalButton.click();
		Thread.sleep(500);

		WebElement noteTitleTextBox = driver.findElement(By.id("note-title"));
		noteTitleTextBox.sendKeys("Test Note");

		WebElement noteDescriptionTextArea = driver.findElement(By.id("note-description"));
		noteDescriptionTextArea.sendKeys("Note functionality test by Selenium");

		WebElement saveNoteButton = driver.findElement(By.id("save-note"));
		saveNoteButton.click();
		Thread.sleep(500);

		WebElement navigateHomeLink = driver.findElement(By.id("nav-home"));
		navigateHomeLink.click();
		Thread.sleep(500);

		notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(500);
	}

	private void EditNote() throws InterruptedException {

		WebElement editNoteButton = driver.findElement(By.id("edit-note"));
		editNoteButton.click();
		Thread.sleep(500);

		WebElement noteTitleTextBox = driver.findElement(By.id("note-title"));
		noteTitleTextBox.clear();
		noteTitleTextBox.sendKeys("Test Note!");

		WebElement saveNoteButton = driver.findElement(By.id("save-note"));
		saveNoteButton.click();
		Thread.sleep(500);

		WebElement navigateHomeLink = driver.findElement(By.id("nav-home"));
		navigateHomeLink.click();
		Thread.sleep(500);

		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(500);
	}

	private void DeleteNote() throws InterruptedException {

		WebElement deleteNoteLink = driver.findElement(By.id("delete-note"));
		deleteNoteLink.click();
		Thread.sleep(500);

		WebElement navigateHomeLink = driver.findElement(By.id("nav-home"));
		navigateHomeLink.click();
		Thread.sleep(500);
	}

	private void AddCredentials() throws InterruptedException {
		SignupUser();
		LoginUser();

		WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialsTab.click();
		Thread.sleep(500);

		WebElement showCredentialsModalButton = driver.findElement(By.id("show-credential-modal"));
		showCredentialsModalButton.click();
		Thread.sleep(500);

		WebElement credentialUrlTextbox = driver.findElement(By.id("credential-url"));
		credentialUrlTextbox.sendKeys("http://localhost:8080");

		WebElement credentialUsernameTextbox = driver.findElement(By.id("credential-username"));
		credentialUsernameTextbox.sendKeys("adev");

		WebElement credentialPaswordTextbox = driver.findElement(By.id("credential-password"));
		credentialPaswordTextbox.sendKeys("P@$$word!1234");

		WebElement saveNoteButton = driver.findElement(By.id("save-credentials"));
		saveNoteButton.click();
		Thread.sleep(500);

		WebElement navigateHomeLink = driver.findElement(By.id("nav-home"));
		navigateHomeLink.click();
		Thread.sleep(500);

		credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialsTab.click();
		Thread.sleep(500);
	}

	private void EditCredentials() throws InterruptedException {

		WebElement editCredentialsButton = driver.findElement(By.id("edit-credentials"));
		editCredentialsButton.click();
		Thread.sleep(500);

		WebElement credentialUsernameTextBox = driver.findElement(By.id("credential-username"));
		credentialUsernameTextBox.clear();
		credentialUsernameTextBox.sendKeys("adev123");

		WebElement saveCredentialsButton = driver.findElement(By.id("save-credentials"));
		saveCredentialsButton.click();
		Thread.sleep(500);

		WebElement navigateHomeLink = driver.findElement(By.id("nav-home"));
		navigateHomeLink.click();
		Thread.sleep(500);

		WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialsTab.click();
		Thread.sleep(500);
	}

	private void DeleteCredentials() throws InterruptedException {

		WebElement deleteCredentialsLink = driver.findElement(By.id("delete-credentials"));
		deleteCredentialsLink.click();
		Thread.sleep(500);

		WebElement navigateHomeLink = driver.findElement(By.id("nav-home"));
		navigateHomeLink.click();
		Thread.sleep(500);
	}
}
