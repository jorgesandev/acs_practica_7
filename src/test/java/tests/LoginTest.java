package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginExitoso() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Acciones usando Page Object
        loginPage.login("john", "demo");

        // Assertions en el TEST (NO en el Page Object)
        Assert.assertTrue(homePage.isHomePageDisplayed(), "El dashboard no se mostró correctamente después del login");
        Assert.assertTrue(homePage.isAccountsOverviewVisible(), "El título 'Accounts Overview' no es visible");
        Assert.assertEquals(homePage.getAccountsOverviewTitleText(), "Accounts Overview", "El título del dashboard no coincide");
        Assert.assertTrue(homePage.isAccountsTableVisible(), "La tabla de cuentas no está visible");
    }

    @Test
    public void testLoginFallido() {
        LoginPage loginPage = new LoginPage(driver);

        // Acciones usando Page Object
        loginPage.login("usuarioInvalido", "claveInvalida");

        // Assertions en el TEST
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "No se mostró el mensaje de error para credenciales inválidas");
        Assert.assertFalse(loginPage.getErrorMessageText().isEmpty(), "El mensaje de error está vacío");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "La página de login debería seguir visible después de un fallo");
    }

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Login
        loginPage.login("john", "demo");
        Assert.assertTrue(homePage.isHomePageDisplayed(), "El dashboard no se mostró correctamente");

        // Logout
        homePage.clickLogout();

        // Validar que se regresó a la página de login
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "La página de login no se mostró después del logout");
    }
}
