package test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tests {

	private WebDriver driver;
	private static final String TIPO_DRIVER = "webdriver.gecko.driver";
	// private static final String TIPO_DRIVER = "webdriver.chrome.driver";
	private static final String PATH_DRIVER = "./src/test/resources/webDriver/geckodriver.exe";
	// private static final String PATH_DRIVER =
	// "./src/test/resources/webDriver/chromedriver.exe";
	private String URL = "https://aulavirtual.instituto.ort.edu.ar/";
	private static final String LOGIN_LINK = "Acceder";
	private static final String LOGIN_URL = "https://aulavirtual.instituto.ort.edu.ar/login/index.php";
	private static final String LOGIN_USER = "username";
	private static final String LOGIN_PASS = "password";
	private static final String LOGIN_REMEMBER_USER = "rememberusername";
	private static final String LOGIN_ACCESS_BUTTON = "loginbtn";
	private static final String PERSONAL_AREA = "Área";
	private static final String EXPECTED_INVALID_LOGIN = "Acceso inválido. Por favor, inténtelo otra vez.";
	private static final String ASO = "ASO";
	private static final String WELCOME = "Bienvenidos";
	private static final String ASISTENCIA = "Enviar asistencia";
	private static final String ENVIAR_ASISTENCIA = "Asistencia";
	private static final String ASO_PRESENT = "id_status_5085";
	private static final String SUBMIT_BUTTON = "id_submitbutton";

	String user = ""; // poner tu nombre de usuario
	String pass = ""; // poner tu password

	private void waitSeconds(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	private void checkURL(String expectedURL) {
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(expectedURL, actualURL);
	}

	private void checkByLinkText(String linkText) {
		Boolean isPresent = driver.findElements(By.linkText(linkText)).size() > 0;
		Assert.assertTrue(isPresent);
	}

	private void clickByLinkText(String linkText) {
		checkByLinkText(linkText);
		WebElement link = driver.findElement(By.linkText(linkText));
		link.click();
	}

	private void checkByPartialLinkText(String linkText) {
		Boolean isPresent = driver.findElements(By.linkText(linkText)).size() > 0;
		Assert.assertTrue(isPresent);
	}

	private void clickByPartialLinkText(String linkText) {
		checkByPartialLinkText(linkText);
		WebElement link = driver.findElement(By.partialLinkText(linkText));
		link.click();
	}

	private void checkById(String id) {
		Boolean isPresent = driver.findElements(By.id(id)).size() > 0;
		Assert.assertTrue(isPresent);

	}

	private WebElement clickById(String id) {
		checkById(id);
		WebElement link = driver.findElement(By.id(id));
		link.click();
		return link;
	}

	private void escribirById(String id, String msg) {
		clickById(id).sendKeys(msg);
	}

	private void waitByPartialLinkText(String partialLinkText) {
		WebElement element = (new WebDriverWait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(partialLinkText))));

	}

	private void login(String linkText, String msg1, String msg2) {
		clickByLinkText(linkText);
		waitSeconds(5);
		checkURL(LOGIN_URL);
		escribirById(LOGIN_USER, msg1);// Ingresar nombre de usuario
		escribirById(LOGIN_PASS, msg2);// Ingresar password
		Assert.assertTrue(clickById(LOGIN_REMEMBER_USER).isSelected());
		clickById(LOGIN_ACCESS_BUTTON);
	}

	private void darAsistencia(String partialLinkText, String id) {
		if (!driver.getCurrentUrl().equals(URL)) {
			driver.get(URL);
		}
		clickByPartialLinkText(partialLinkText);
		clickByPartialLinkText(WELCOME);
		clickByLinkText(ASISTENCIA);
		clickByPartialLinkText(ENVIAR_ASISTENCIA);
		clickById(id);
		//clickById(SUBMIT_BUTTON);
		Boolean isPresent = driver.findElements(By.linkText(ASISTENCIA)).size() > 0;
		Assert.assertFalse(isPresent);

		
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("BeforeClass");
		System.setProperty(TIPO_DRIVER, PATH_DRIVER);
	}

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		System.out.println("Apertura del navegador");
		driver.manage().window().maximize();
		System.out.println("Maximización de la pantalla");
		driver.get(URL);
		System.out.println("Entro a la URL: " + URL);
		waitSeconds(3);
		System.out.println("Espera 3 segundos");
	}

	@Test
	public void testLoginCorrect() {
		System.out.println("Test. tesLoginRightOk");
		user = "34924571"; // poner tu nombre de usuario
		pass = "asopass1"; // poner tu password
		login(LOGIN_LINK, user, pass);
		waitSeconds(3);
		if (driver.findElements(By.partialLinkText(PERSONAL_AREA)).size() > 0) {
			waitByPartialLinkText(PERSONAL_AREA);
			checkURL(URL);
			System.out.println("Login correcto");

		} else {
			String currentInvalidLogin = driver.findElement(By.className("accesshide")).getText();
			Assert.assertEquals(EXPECTED_INVALID_LOGIN, currentInvalidLogin);
			System.out.println("Login incorrecto");
		}
	}

	@Test
	public void testLoginIncorrect() {
		System.out.println("Test. tesLoginRightOk");
		user = "34924571"; // poner tu nombre de usuario
		pass = ""; // poner tu password
		login(LOGIN_LINK, user, pass);
		waitSeconds(3);
		if (driver.findElements(By.partialLinkText(PERSONAL_AREA)).size() > 0) {
			waitByPartialLinkText(PERSONAL_AREA);
			checkURL(URL);
			System.out.println("Login correcto");

		} else {
			String currentInvalidLogin = driver.findElement(By.className("accesshide")).getText();
			Assert.assertEquals("Contraseña vacia", currentInvalidLogin);
			System.out.println("Login incorrecto");
		}
	}

	@Test
	public void testAssistanceASO() {
		testLoginCorrect();
		darAsistencia(ASO, ASO_PRESENT);
		System.out.println("Asistencia satisfactoria");
		
	}

	@After
	public void tearDown() {
		System.out.println("After");
		// driver.quit();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("AfterClass");
	}

}
