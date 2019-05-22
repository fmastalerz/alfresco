package automation.tests;

import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.GroupPropLoader;
import automation.utils.loaders.UserConfigLoader;
import automation.pages.GroupManagementPage;
import automation.pages.LoginPage;
import automation.pages.NewGroupPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TS01 - Managing groups")
public class ManagingGroupTest {

    private static LoginPage loginPage;
    private static WebDriver driver;
    private static UserConfigLoader userConfLoader = new UserConfigLoader("user");
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
    private NewGroupPage newGroupPage;
    private GroupManagementPage groupManagementPage;

    @DisplayName("TC01 - New group can be created")
    @ParameterizedTest
    @MethodSource("groupCredentialsProvider")
    void checkIfNewGroupCanBeCreated(final String identifier, final String displayName) {
        //given:
        driver.get(envConfLoader.getNewGroupPanel());

        //when:
        newGroupPage = new NewGroupPage(driver);
        newGroupPage.typeIdentifier(identifier);
        newGroupPage.typeDisplayName(displayName);
        newGroupPage.submitCreateGroupButton();

        groupManagementPage = new GroupManagementPage(driver);
        groupManagementPage.groupNameXPath(displayName, identifier);

        driver.get(envConfLoader.getGroupManagementPage());
        driver.navigate().refresh();

        //then
        assertEquals(String.format("%s (%s)", displayName, identifier), groupManagementPage.getGroupName(),
                "Group 'display name' and/or 'identifier' are not the same");
    }

    @DisplayName("TC02 - Existing group can be edited")
    @Test
    void checkIfGroupCanBeEdited() {
        //todo: implement this
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
        driver.get(envConfLoader.getGroupManagementPage());
    }

    @BeforeAll
    public static void beforeAll() {

        driver = envConfLoader.getDriver();
        driver.get(envConfLoader.urlBeginning());

        loginPage = new LoginPage(driver);
        loginPage.typeUsername(userConfLoader.getUserLogin());
        loginPage.typePassword(userConfLoader.getUserPassword());

        loginPage.submitLogin();
    }

    @AfterAll
    static void afterAll() {
       driver.quit();
    }

    private static Stream<Arguments> groupCredentialsProvider() {
        return Stream.of(
                Arguments.of("Group", "NewGroup"),
                Arguments.of("DC", "MARVEL")
        );
    }
}
