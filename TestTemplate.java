package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Modelo genérico de teste automatizado com Selenium WebDriver + JUnit.
 *
 * Pré-requisitos:
 *  - JDK instalado
 *  - Projeto Maven com as dependências "junit" e "selenium-java" no pom.xml
 *  - ChromeDriver baixado, na versão compatível com o Chrome instalado
 *
 * Estrutura do teste:
 *  1. setUp()    -> abre o navegador antes de cada teste (roda automaticamente)
 *  2. test...()  -> o cenário de teste em si
 *  3. tearDown() -> fecha o navegador depois de cada teste (roda automaticamente)
 */
public class TestTemplate {

    private WebDriver navegador;
    private WebDriverWait espera;

    // URL da aplicação que será testada
    private static final String URL_BASE = "https://exemplo.com/login";

    @Before
    public void setUp() {
        // Caminho para o executável do ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\caminho\\para\\chromedriver.exe");

        navegador = new ChromeDriver();
        navegador.manage().window().maximize();

        // Espera explícita: aguarda até 10 segundos por um elemento antes de falhar
        espera = new WebDriverWait(navegador, Duration.ofSeconds(10));

        navegador.get(URL_BASE);
    }

    @Test
    public void testFazerLogin() {
        // Localizando os campos por ID (opção preferencial, quando disponível)
        WebElement campoUsuario = espera.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement campoSenha = navegador.findElement(By.id("password"));
        WebElement botaoLogin = navegador.findElement(By.id("login-button"));

        campoUsuario.sendKeys("usuario_teste");
        campoSenha.sendKeys("senha_teste");
        botaoLogin.click();

        // Espera até que o elemento que confirma o login apareça na tela
        WebElement mensagemBoasVindas = espera.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("welcome-message")));

        // Validação (assert) do resultado esperado
        assertTrue(mensagemBoasVindas.isDisplayed());
        assertEquals("Bem-vindo, usuario_teste!", mensagemBoasVindas.getText());
    }

    @Test
    public void testValidarTitulo() {
        String tituloEsperado = "Página de Login";
        String tituloAtual = navegador.getTitle();

        assertEquals(tituloEsperado, tituloAtual);
    }

    @After
    public void tearDown() {
        // Fecha o navegador e encerra a sessão do WebDriver
        if (navegador != null) {
            navegador.quit();
        }
    }
}
