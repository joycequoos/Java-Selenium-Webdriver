# Modelo Genérico de Teste com Selenium WebDriver

[← Voltar](https://github.com/joycequoos/Test_QA/blob/main/README.md)

Modelo genérico e comentado de teste automatizado com **Selenium WebDriver** e **JUnit**, em Java, pensado para ser reaproveitado como ponto de partida em qualquer projeto de automação de testes web.

**→ Veja o código: [https://github.com/joycequoos/Java-Selenium-Webdriver/blob/master/TestTemplate.java)**



## Sobre o Modelo

Este modelo (`TestTemplate.java`) reúne, em um único arquivo, a estrutura básica que qualquer classe de teste com Selenium WebDriver costuma ter: abertura do navegador, execução do cenário de teste e fechamento do navegador ao final — tudo isso já organizado com boas práticas, como espera explícita e localização de elementos por ID.

## Pré-requisitos

| Item | Descrição |
|---|---|
| **JDK** | Java Development Kit instalado na máquina. |
| **Projeto Maven** | Com as dependências `junit` e `selenium-java` configuradas no `pom.xml`. |
| **ChromeDriver** | Executável baixado, na versão compatível com o Chrome instalado. |

## Estrutura do Teste

### 1. `setUp()` — Preparação do Ambiente

Roda automaticamente **antes de cada teste**, graças à anotação `@Before`. É responsável por:

- Configurar o caminho do ChromeDriver (`System.setProperty`).
- Abrir o navegador (`new ChromeDriver()`) e maximizar a janela.
- Criar o objeto de espera explícita (`WebDriverWait`), usado para aguardar elementos aparecerem na tela antes de interagir com eles.
- Acessar a URL da aplicação que será testada.

### 2. `testFazerLogin()` — Cenário de Teste: Login

Demonstra um fluxo completo de teste:

1. Localiza os campos de usuário e senha, e o botão de login, preferencialmente por **ID** — a forma mais confiável de localizar um elemento, por ter menor chance de se repetir na página.
2. Preenche os campos com `sendKeys(...)` e clica no botão com `click()`.
3. Espera até que a mensagem de boas-vindas apareça na tela, usando `WebDriverWait` combinado com `ExpectedConditions`.
4. Valida o resultado esperado com `assertTrue` (o elemento está visível) e `assertEquals` (o texto é exatamente o esperado).

### 3. `testValidarTitulo()` — Cenário de Teste: Validação Simples

Um segundo exemplo, mais simples, que apenas compara o título da página (`getTitle()`) com o valor esperado — útil para mostrar que um teste não precisa ser complexo para agregar valor.

### 4. `tearDown()` — Encerramento do Ambiente

Roda automaticamente **depois de cada teste**, graças à anotação `@After`. Fecha o navegador com `navegador.quit()`, liberando os recursos utilizados, independentemente de o teste ter passado ou falhado.

## Por que Usar Espera Explícita (`WebDriverWait`)

Um erro comum em automação de testes é tentar interagir com um elemento antes que a página termine de carregá-lo, o que gera falhas intermitentes (os famosos testes "flaky"). O `WebDriverWait` resolve isso aguardando, por um tempo limite definido (no modelo, 10 segundos), até que uma condição seja satisfeita — como o elemento estar visível na tela — antes de seguir com o teste.

## Como Adaptar o Modelo

Para reaproveitar este modelo em um cenário próprio, é necessário ajustar:

- **`URL_BASE`** — para a URL da aplicação que será testada.
- **Os seletores** (`By.id(...)`, `By.className(...)`, `By.xpath(...)` etc.) — para os elementos reais da página testada.
- **As asserções** (`assertEquals`, `assertTrue`) — para os resultados esperados do próprio cenário.






