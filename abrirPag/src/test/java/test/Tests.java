package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeOptions;

public class Tests {

	private WebDriver driver;
	// private static final String TIPO_DRIVER = "webdriver.gecko.driver";
	private static final String TIPO_DRIVER = "webdriver.chrome.driver";
	// private static final String PATH_DRIVER =
	// "./src/test/resources/webDriver/geckodriver.exe";
	private static final String PATH_DRIVER = "./src/test/resources/webDriver/chromedriver.exe";
	private String URL = "https://aulavirtual.instituto.ort.edu.ar/";
	Boolean isPresent;
	ArrayList<String> logError = new ArrayList();
	//

	private void waitt() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	private String dondeEstoy() {
		String dondeEstoy = driver.getCurrentUrl();
		return dondeEstoy;
	}

	private Boolean checkByLinkText(String linkText) {
		Boolean isPresent = driver.findElements(By.linkText(linkText)).size() > 0;
		return isPresent;
	}

	private void clickByLinkText(String linkText) {
		WebElement acceder = driver.findElement(By.linkText(linkText));
		acceder.click();
	}

	private Boolean checkById(String id) {
		isPresent = driver.findElements(By.id(id)).size() > 0;
		return isPresent;
	}

	private WebElement clickById(String id) {
		WebElement acceder = driver.findElement(By.id(id));
		acceder.click();
		return acceder;
	}

	private Boolean checkByXpath(String xpath) {
		isPresent = driver.findElements(By.xpath(xpath)).size() > 0;
		return isPresent;
	}

	private void clickByXpath(String xpath) {
		WebElement acceder = driver.findElement(By.xpath(xpath));
		acceder.click();
	}

	private Boolean checkByPartialLinkText(String PartialLinkText) {
		isPresent = driver.findElements(By.partialLinkText(PartialLinkText)).size() > 0;
		return isPresent;
	}

	private Boolean checkByPartialLinkTexth(String partialLinkText) {
		isPresent = driver.findElements(By.partialLinkText(partialLinkText)).size() > 0;
		return isPresent;
	}

	private void clickByPartialLinkText(String partialLinkText) {
		WebElement acceder = driver.findElement(By.partialLinkText(partialLinkText));
		acceder.click();
	}

	private Boolean checkByClassName(String className) {
		isPresent = driver.findElements(By.className(className)).size() > 0;
		return isPresent;
	}

	private void clickByClassName(String className) {
		WebElement acceder = driver.findElement(By.className(className));
		acceder.click();
	}

	private Boolean checkByCssSelector(String cssSelector) {
		isPresent = driver.findElements(By.cssSelector(cssSelector)).size() > 0;
		return isPresent;
	}

	private void clickByCssSelector(String cssSelector) {
		WebElement acceder = driver.findElement(By.cssSelector(cssSelector));
		acceder.click();
	}

	private Boolean checkByName(String name) {
		isPresent = driver.findElements(By.name(name)).size() > 0;
		return isPresent;
	}

	private void clickByName(String name) {
		WebElement acceder = driver.findElement(By.name(name));
		acceder.click();
	}

	private void escribirById(String id, String user) {
		clickById(id).sendKeys(user);
	}

	private String getName(String name) {
		String nam = name;
		return nam;
	}

	private void login(String linkText, String id1, String user, String id2, String pass, String xpath) {
		if (checkByLinkText(linkText)) {
			clickByLinkText(linkText);// Entrar a la pagina del login
			waitt();
			if (checkById(id1) && checkById(id2)) {
				escribirById(id1, user);// Ingresar nombre de usuario
				escribirById(id2, pass);// Ingresar password
				clickById(id2).submit();
				waitt();
			} else {
				logError.add(id1 + " o" + id2 + "no pudo ser encontrado");
			}
		} else {
			logError.add(linkText + "no pudo ser encontrado");
		}
		if (dondeEstoy() != URL && !checkByXpath(xpath)) {

			logError.add("Login erroneo");
		}

	}

	private void darAsistencia(String partialLinkText1, String partialLinkText2, String linkText,
			String partialLinkText3, String id1, String id2) {
		if (!dondeEstoy().equals(URL)) {
			driver.get(URL);
		}
		if (checkByPartialLinkText(partialLinkText1)) {
			clickByPartialLinkText(partialLinkText1);
			if (checkByPartialLinkText(partialLinkText2)) {
				clickByPartialLinkText(partialLinkText2);
				if (checkByLinkText(linkText)) {
					clickByLinkText(linkText);
					if (checkByPartialLinkText(partialLinkText3)) {
						clickByPartialLinkText(partialLinkText3);
						if (checkById(id1)) {
							clickById(id1);
							if (checkById(id2)) {
								clickById(id2);
								waitt();
							}
						}
					}
				}
			}
		}
	}

	private void zoomIn(String partialLinkText1, String linkText, String partialLinkText2, String xpath) { //
		if (!dondeEstoy().equals(URL)) {
			driver.get(URL);
			waitt();
		}
		if (checkByPartialLinkText(partialLinkText1)) {
			clickByPartialLinkText(partialLinkText1);
			if (checkByLinkText(linkText)) {
				clickByLinkText(linkText);
				if (checkByPartialLinkText(partialLinkText2)) {
					clickByPartialLinkText(partialLinkText2);
					if (checkByXpath(xpath)) {
						clickByXpath(xpath);
						waitt();
					}
				}
			}
		}
	}

	private void temp() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// driver.switchTo().window(tabs2.get(0));
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		waitt();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"page-mod-zoom-view\"]")));

	}

	// *********************************************************************

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Inicio de test");
		System.setProperty(TIPO_DRIVER, PATH_DRIVER);
	}

	@Before
	public void setUp() {
		// driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
	}

	@Test
	public void testOpen() {

		String user = ""; // poner tu nombre de usuario
		String pass = ""; // poner tu password
		String personalArea = "//*[@id=\"nav-drawer\"]/nav/a[2]/div/div/span[2]";
		String aso = "ASO";// /html/body/div[1]/div[5]/nav/a[5]/div/div/span[2]
		String bd1 = "BD1";/// html/body/div[1]/div[5]/nav/a[6]/div/div/span[2]
		String fpr = "FPR"; // /html/body/div[1]/div[5]/nav/a[9]/div/div/span[2]
		String tp1 = "TP1"; // /html/body/div[1]/div[5]/nav/a[10]/div/div/span[2]
		String sad = "SAD"; // /html/body/div[1]/div[5]/nav/a[11]/div/div/span[2]
		String asistencia = "Asistencia";
		String enviarAsistencia = "Enviar asistencia";
		String asoPresent = "id_status_5085";
		String bd1Present = "id_status_5117";// Cambiar numero de id una vez se conozca
		String fprPresent = "id_status_5085";// Cambiar numero de id una vez se conozca
		String tp1Present = "id_status_5181";
		String sadPresent = "id_status_5109";// Cambiar numero de id una vez se conozca
		String submitButton = "id_submitbutton";
		String exchangeSection = "Sección de Intercambio";
		String welcome = "Bienvenidos";
		String asoZoom = "Arquitectura";
		String zoomButton = "/html/body/div[1]/div[4]/div[2]/div/section/div[1]/table/tbody/tr[1]/th/form/button";
		String bd1Zoom = "Base";
		String fprZoom = "Fundamentos";
		String tp1Zoom = "Taller";
		String sadZoom = "Administrativos";

		login("Acceder", "username", user, "password", pass, personalArea);

		if (checkByPartialLinkText("Área")) {

			System.out.println(driver.findElements(By.partialLinkText("ASO")).size());

			darAsistencia(aso, welcome, asistencia, enviarAsistencia, asoPresent, submitButton);
			driver.get(URL);
			darAsistencia(bd1, welcome, asistencia, enviarAsistencia, bd1Present, submitButton);
			driver.get(URL);
			darAsistencia(fpr, welcome, asistencia, enviarAsistencia, fprPresent, submitButton);
			driver.get(URL);
			darAsistencia(tp1, welcome, asistencia, enviarAsistencia, tp1Present, submitButton);
			driver.get(URL);
			darAsistencia(sad, welcome, asistencia, enviarAsistencia, sadPresent, submitButton);
			driver.get(URL);
			zoomIn(aso, exchangeSection, asoZoom, zoomButton);
			temp();
			driver.get(URL);
			zoomIn(bd1, exchangeSection, bd1Zoom, zoomButton);
			temp();
			driver.get(URL);
			zoomIn(fpr, exchangeSection, fprZoom, zoomButton);
			temp();
			driver.get(URL);
			zoomIn(tp1, exchangeSection, tp1Zoom, zoomButton);
			temp();
			driver.get(URL);
			zoomIn(sad, exchangeSection, sadZoom, zoomButton);
			temp();

		}
	}
	/*
	 * @After public void tearDown() { driver.quit(); }
	 * 
	 * @AfterClass public static void tearDownAfterClass() {
	 * System.out.println("Fin de los tests"); }
	 */
}
