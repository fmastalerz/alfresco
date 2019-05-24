package automation.tests;

import automation.pages.BrowseGroupsPanel;
import automation.pages.LoginPage;
import automation.pages.NewGroupPage;
import automation.pages.UpdateGroupPage;
import automation.utils.WaitForElement;
import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import automation.utils.loaders.UserConfigLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("TS01 - Managing groups")
public class ManagingGroupTest {

    private static Go go;
    private static WebDriver driver;
    private BrowseGroupsPanel browseGroupsPanel;

    @DisplayName("TC01 - New group can be created")
    @ParameterizedTest
    @MethodSource("groupCredentialsProvider")
    void checkIfNewGroupCanBeCreated(final String identifier, final String displayName) {
        //given:
        go.to(Pages.NEW_GROUP_PAGE);

        //when:
        NewGroupPage newGroupPage = new NewGroupPage(driver);
        newGroupPage.typeIdentifier(identifier);
        newGroupPage.typeDisplayName(displayName);
        newGroupPage.submitCreateGroupButton();

        browseGroupsPanel = new BrowseGroupsPanel(driver);
        browseGroupsPanel.setPathToGroupCredentials(displayName, identifier);

        WaitForElement.wait(driver, browseGroupsPanel.getGroupCredentials());

        //then
        assertEquals(String.format("%s (%s)", displayName, identifier), browseGroupsPanel.getGroupName(),
                "Group 'display name' and/or 'identifier' are not the same");
    }

    @DisplayName("TC02 - Existing group can be edited")
    @ParameterizedTest
    @MethodSource("groupDisplayNameEditText")
    void checkIfGroupCanBeEdited(final String displayName, final String identifier, final String editText) {
        //given:
        // todo: do it in the way which allow to put group name dynamically
        go.to(Pages.SOME_GROUP_EDIT_PAGE);

        //when:
        UpdateGroupPage updateGroupPage = new UpdateGroupPage(driver);

        WaitForElement.wait(driver, updateGroupPage.getEditGroupInputField());

        updateGroupPage.editGroupName(editText);
        updateGroupPage.clickUpdateButton();

        browseGroupsPanel = new BrowseGroupsPanel(driver);
        browseGroupsPanel.setPathToGroupCredentials(String.format("%s%s", displayName, editText), identifier);

        WaitForElement.wait(driver,browseGroupsPanel.getGroupCredentials());

        //then:
        assertEquals(String.format("%s%s (%s)", displayName, editText, identifier), browseGroupsPanel.getGroupName(),
                "Group names don't match");

    }

    @DisplayName("TC03 - Existing group can be removed")
    @Test
    void checkIfGroupCanBeRemoved() {
        JavascriptExecutor jsExecutor = ((JavascriptExecutor)driver);
        //wait for elements
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //find span
        go.to(Pages.BROWSE_GROUPS_PANEL);
        WebElement groupSpan = driver.findElement(By.xpath("//*[contains(text(), 'Removable')]/parent::*"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", groupSpan);

        //points on remove button in span
        WebElement removeButton =
                driver.findElement(By.xpath("//a[@class='yui-columnbrowser-item groups-item-group']//span[contains(text(),'RemovableGroup')]/following-sibling::span[1]/span[@class='groups-delete-button']"));
        jsExecutor.executeScript("arguments[0].click();", removeButton);

        // delete button in popup
        WebElement deleteButton =
                driver.findElement(By.id("page_x002e_ctool_x002e_admin-console_x0023_default-remove-button-button"));
        deleteButton.click();

        go.to(Pages.BROWSE_GROUPS_PANEL);

        WaitForElement.wait(driver, By.xpath("//div[@class='yui-columnbrowser-column-body']"));
        List<WebElement> groups =
                driver.findElements(By.xpath("//a[@class='yui-columnbrowser-item groups-item-group']"));

        assertFalse(groups.stream().anyMatch(element -> element.getText().equals("RemovableGroup (Removable)")),
                "Group was found on a list containing all of groups");

    }

    @DisplayName("TC04 - Existing group can be removed permanently")
    @Test
    void checkIfGroupCanBeRemovedPermanently() {
        // todo: implement this
        //given:
        //when:
        //then:
    }

    @BeforeEach
    void beforeEach() {
        go.to(Pages.GROUP_MANAGEMENT_PAGE);
    }

    @BeforeAll
    public static void beforeAll() {
        EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
        driver = envConfLoader.getDriver();

        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);

        UserConfigLoader userConfLoader = new UserConfigLoader("user");
        new LoginPage(driver).logUser(driver, userConfLoader.getUserLogin(), userConfLoader.getUserPassword());
    }

    @AfterAll
    static void afterAll() {
       //driver.quit();
    }

    private static Stream<Arguments> groupCredentialsProvider() {
        return Stream.of(
                Arguments.of("Jay&SilentBob", "Jay & Silent Bob")
        );
    }

    private static Stream<Arguments> groupDisplayNameEditText() {
        return Stream.of(
                Arguments.of("SomeGroup", "Some", " with updated name")
        );
    }
}
