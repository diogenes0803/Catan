package server.handler;


public class Cookie {
    private String name;
    private String value;

    public Cookie(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public Cookie(String cookie) {
        int equalsIndex = cookie.indexOf('=');

        if (equalsIndex <= 0 || equalsIndex >= cookie.length()) {
            throw new IllegalArgumentException("Improperly formatted cookie: \"" + cookie + "\"");
        }

        name = cookie.substring(0, equalsIndex);
        value = cookie.substring(equalsIndex + 1);

        // remove surrounding quotation marks on the value, if any
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length()-1);
        }
    }


    public boolean nameIs(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return name + '=' + value;
    }
}
