import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginDefinitions {
    static FirefoxDriver driver;
    private static void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\joeye\\Documents\\Automation Test\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        driver = new FirefoxDriver();
    }
/*
    @Given("^I am a? (.*)$")
    public void i_am_a_User(String user) throws Exception {
            System.out.println(user + "----------------------------");
            //Thread.sleep(20000);
            //WebDriverWait wait = new WebDriverWait(driver,100);
           // driver.close();

    }

    @Given("^I'm on (.*)$")
    public void i_m_on_SDL_SIT(String site) throws Exception {
        LoginDefinitions.setUp();
        driver.get("https://sdlsit.appiancloud.com");
        System.out.println(site + "----------------------------");
    }

    @When("^I enter my username (.*)$")
    public void i_enter_my_username(String un) throws Exception {
       WebElement username =  driver.findElementByName("un");
       username.sendKeys(un);
    }

    @When("^my password (.*)$")
    public void my_password(String pw) throws Exception {
        WebElement password = driver.findElementByName("pw");
        password.sendKeys(pw);
    }

    @When("^Press the login button$")
    public void press_the_login_button() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        WebElement loginButton = driver.findElementByXPath("//*[@Value = 'Log in']");
        loginButton.click();
    }

    @Then("^I can login into my home page = SDL main site$")
    public void i_can_login_into_my_home_page_SDL_main_site() throws Exception {
        String mainSite = "https://sdlsit.appiancloud.com/suite/tempo/tasks/l_BqtQ";
        assertEquals(mainSite,driver.getCurrentUrl());
      //  assertEquals("Tasks",driver.getTitle());
        WebDriverWait wait = new WebDriverWait(driver,1000);
        LoginDefinitions.findAllWebElements2();
        //driver.quit();
    }
    public static void findAllWebElements2(){
       // List<WebElement> elements = driver.findElementsByXPath("//*");
       // List<WebElement> elements = driver.findElementsById("__gwt_historyFrame" );
        // iframe id = tempo
//        WebElement iframe1 = driver.findElementById("__gwt_historyFrame" );
//        Variable frame2 = iframe1.getTagName();+
        //WebElement iframeContents = iframe1.findElementByXPath("//*");

       driver.switchTo().defaultContent();
       // driver.switchTo().frame(0);
        //driver.switchTo().frame("__gwt_historyFrame");
       // driver.switchTo().frame("tempo");

        //WebElement thisdriver = driver.findElementsByXPath("/html/body/div[6]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div/tempo-report/div[3]/div/main/div/div/div/div/div/div[4]/div/div/div[1]/div[1]/div[2]/div/div");

        List<WebElement> elements = driver.findElementsByCssSelector("#dddd242167bf7b4900e47550aaad465c");



//        List<WebElement> elements = driver.findElementsByTagName("class");
        for(WebElement el:elements)
        {
            System.out.println(
                    "tag="+ el.getTagName() +
                    " :Text=" + el.getText() +
                    " :Value=" +el.getAttribute("value") +
                    " :name=" +el.getAttribute("name") +
                    " :id=" + el.getAttribute("id") +
                    " :href=" + el.getAttribute("href")+
                    " :xmlns=" +el.getAttribute("xmlns")+
                            " :lang=" +el.getAttribute("lang")+
                            " :class=" +el.getAttribute("class")+
                            " :div=" +el.getAttribute("div")

            );
        }
    }
    */

}
