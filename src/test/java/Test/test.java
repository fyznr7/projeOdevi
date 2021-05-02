package Test;

import Selenium.Basket;
import Selenium.LoginPage;
import Selenium.Product;
import Selenium.homePage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {


        WebDriver driver;

        @Before
        public void setup(){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\feyza\\Desktop\\selenium\\chromedriver.exe");
            this.driver = new ChromeDriver();
        }

        @Test
        public void start() {
            homePage gittigidiyor = new homePage(driver);
            /* www.gittigidiyor.com sitesi açılır. */
            gittigidiyor.connect();

            Assert.assertEquals(driver.getCurrentUrl(),"https://www.gittigidiyor.com/");
            /* Ana sayfanın açıldığı kontrol edilir. Siteye login olunur. */
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail("********");
            loginPage.setPass("*********");
            loginPage.loginGittiGidiyor();
            /* kullanıcı ismi ile Login işlemi kontrol edilir. */
            Assert.assertEquals(loginPage.loginControl(),"*******");
            /* Arama kutucuğuna 'bilgisayar' kelimesi girilir. */
            Product product = new Product(driver);
            product.setSearchWord("bilgisayar");
            product.sendAndClickSearch();
            /* Arama sonuçları sayfasından 2.sayfa açılır. */
            product.secondPage();
            /* 2.sayfanın açıldığı kontrol edilir. */
            Assert.assertEquals(driver.getCurrentUrl(),"https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
            /*Favorilerim linkine tıklanır.*/
            product.setSearchWord("favorilerim");
            product.sendAndClickSearch();

            /* Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir. */
            product.productSelect();

            Basket basket = new Basket(driver);
            /* Rastgele Seçilen ürün sepete eklenir.*/
            basket.addBasked();
            /* Sepet görüntülenir */
            basket.goToBasked();


            /* Ürün sepetten silinerek sepetin boş olduğu kontrol edilir. */
            basket.baskedProductRemove();
            Assert.assertEquals(basket.productRemoveControl(),"Sepetiniz Boş");

            System.out.println("Successful");
        }
}
