package automation.tests;

import automation.pages.GroupManagementPage;
import automation.pages.HomePage;
import automation.pages.LoginPage;
import automation.pages.NewGroupPage;
import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import automation.utils.loaders.UserConfigLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TS01 - Managing groups")
public class ManagingGroupTest {

    private static Go go;
    private static WebDriver driver;
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
    private NewGroupPage newGroupPage;
    private GroupManagementPage groupManagementPage;

    @DisplayName("TC01 - New group can be created")
    @ParameterizedTest
    @MethodSource("groupCredentialsProvider")
    void checkIfNewGroupCanBeCreated(final String identifier, final String displayName) {
        //given:
        go.to(Pages.NEW_GROUP_PAGE);

        //when:
        newGroupPage = new NewGroupPage(driver);
        newGroupPage.typeIdentifier(identifier);
        newGroupPage.typeDisplayName(displayName);
        newGroupPage.submitCreateGroupButton();

        groupManagementPage = new GroupManagementPage(driver);
        groupManagementPage.setGroupIdentifier(displayName, identifier);

        go.to(Pages.BROWSE_GROUPS_PAGE);

        waitForXpath(groupManagementPage.getGroupIdentifier());

        //then
        assertEquals(String.format("%s (%s)", displayName, identifier), groupManagementPage.getGroupName(),
                "Group 'display name' and/or 'identifier' are not the same");
    }

    @DisplayName("TC02 - Existing group can be edited")
    @Test
    void checkIfGroupCanBeEdited() {
        //todo: re-write this

        driver.get("http://127.0.0.1:8080/share/page/console/admin-console/groups#state=panel%3Dupdate%26group%3DSome");
        WebElement inputField = driver.findElement(By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-displayname"));
        inputField.sendKeys(" with updated name");
        WebElement editButton = driver.findElement(By.id("page_x002e_ctool_x002e_admin-console_x0023_default-updategroup-save-button-button"));
        editButton.click();
        WebElement nameSpan = driver.findElement(By.xpath("//span[contains(text(),'SomeGroup with updated name')]"));

        //waitForXpath();
        String nameFromNameSpan = nameSpan.getText();
        System.out.println(nameFromNameSpan);

    }

    @DisplayName("TC03 - Existing group can be removed")
    @Test
    void checkIfGroupCanBeRemoved() {
        // todo: implement this
    }

    @DisplayName("TC04 - Existing group can be removed permanently")
    @Test
    void checkIfGroupCanBeRemovedPermanently() {
        // todo: implement this
    }

    @BeforeEach
    void beforeEach() {
        go.to(Pages.GROUP_MANAGEMENT_PAGE);
    }

    @BeforeAll
    public static void beforeAll() {
        driver = envConfLoader.getDriver();

        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);

        UserConfigLoader userConfLoader = new UserConfigLoader("user");
        new LoginPage(driver).logUser(driver, userConfLoader.getUserLogin(), userConfLoader.getUserPassword());
    }

    @AfterAll
    static void afterAll() {
       driver.quit();
    }

    private static Stream<Arguments> groupCredentialsProvider() {
        return Stream.of(
                Arguments.of("Jay&SilentBob", "Jay & Silent Bob")
        );
    }

    private void waitForXpath(By pathToElement) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pathToElement));
    }
}
