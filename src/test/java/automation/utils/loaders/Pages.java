package automation.utils.loaders;

public enum Pages {
    ADMIN("http://adminUrl.com"),
    USER("http://userUrl.com"),
    BROWSE("http://browseUrl.com");

    private final String url;

    Pages(String otherUrl) {
        url = otherUrl;
    }

    public String getUrl() {
        return url;
    }
}
