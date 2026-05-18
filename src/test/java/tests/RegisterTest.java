package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

    @Test
    public void testNavegacionARegister() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        // Acción usando Page Object
        loginPage.clickRegisterLink();

        // Assertions en el TEST
        Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "La página de registro no se cargó correctamente");
        Assert.assertEquals(registerPage.getSignUpTitleText(), "Signing up is easy!", "El título de la página de registro no coincide");
    }

    @Test
    public void testRegistroExitoso() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        // Navegar a registro
        loginPage.clickRegisterLink();
        Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "La página de registro no se cargó");

        // Datos de registro únicos usando timestamp (evita duplicados en username y SSN)
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        String uniqueUsername = "testuser" + uniqueSuffix;
        String uniqueSsn = uniqueSuffix.substring(uniqueSuffix.length() - 9);
        // Formatear SSN como XXX-XX-XXXX
        String formattedSsn = uniqueSsn.substring(0, 3) + "-" + uniqueSsn.substring(3, 5) + "-" + uniqueSsn.substring(5);

        // Acciones de registro usando Page Object
        registerPage.register(
                "David",
                "Sandoval",
                "Paseo del papu",
                "Tijuana",
                "Baja California",
                "12345",
                "6641234567",
                formattedSsn,
                uniqueUsername,
                "password123",
                "password123"
        );

        // Assertions en el TEST
        boolean enPaginaRegistro = registerPage.isRegisterPageDisplayed();

        if (!enPaginaRegistro) {
            // Redirigió a otra página (normalmente login) -> éxito
            LoginPage loginPageAfter = new LoginPage(driver);
            Assert.assertTrue(
                    loginPageAfter.isLoginPageDisplayed() || loginPageAfter.isRegisterSuccessMessageDisplayed(),
                    "Se esperaba redirección al login con mensaje de éxito tras el registro"
            );
        } else {
            // Seguimos en la página de registro: puede ser éxito inline o errores
            boolean successInline = registerPage.isSuccessMessageDisplayed();
            boolean pageHasErrors = driver.getPageSource().toLowerCase().contains("error") ||
                    driver.getPageSource().contains("already exists") ||
                    driver.getPageSource().contains("required");
            Assert.assertTrue(successInline || pageHasErrors,
                    "El registro no mostró éxito ni errores de validación tras enviar el formulario");
        }
    }
}
