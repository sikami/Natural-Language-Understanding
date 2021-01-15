public class KeyPhrase {
    private String keywords;
    private String[] keys;

    public KeyPhrase(String keywords) {
        this.keywords = keywords;
        this.keys = null;
    }

    public String getKeyphrase() {
        return this.keywords;
    }
}
