package automation.utils.loaders;

public enum Pages {
    LOGIN_PAGE(new EnvironmentConfigLoader("environment").getLoginPage()),
    ADMIN_TOOLS_GROUPS_PAGE(new EnvironmentConfigLoader("environment").getAdminToolsGroupPage()),
    BROWSE_GROUPS_PANEL(new EnvironmentConfigLoader("environment").getBrowseGroupsPanel()),
    NEW_GROUP_PAGE(new EnvironmentConfigLoader("environment").getNewGroupPage()),
    SOME_GROUP_EDIT_PAGE(new EnvironmentConfigLoader("environment").getSomeGroupEditPage());

    private final String url;

    Pages(String otherUrl) {
        url = otherUrl;
    }

    public String getUrl() {
        return url;
    }
}
