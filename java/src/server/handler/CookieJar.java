package server.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


public class CookieJar implements Iterable<Cookie> {
    private static final URLDecoder decoder = new URLDecoder();
    private static final String ENCODING = "UTF-8";

    private Collection<Cookie> local_cookies;

    public CookieJar() {
        local_cookies = new ArrayList<>();
    }


    public CookieJar(Collection<String> listOfCookies) {
        this();

        if (listOfCookies != null) {
            addCookies(listOfCookies);
        }
    }

    public CookieJar(String[] cookies) {
        this(Arrays.asList(cookies));
    }


    public void addCookies(Collection<String> cookieList) {
        for (String listOfCookies : cookieList) {
            try {
                for (String cookie : decoder.decode(listOfCookies, ENCODING).split(";")) {
                    addCookie(cookie.trim());
                }
            }
            catch (UnsupportedEncodingException e) {
                assert false : "Bad encoding.";
                e.printStackTrace();
            }
        }
    }


    public void addCookie(String cookie) {
        local_cookies.add(new Cookie(cookie));
    }


    public void addCookie(String name, String value) {
        local_cookies.add(new Cookie(name, value));
    }


    @Override
    public Iterator<Cookie> iterator() {
        return Collections.unmodifiableCollection(local_cookies).iterator();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Cookie cookie : local_cookies) {
            sb.append(cookie.toString());
            sb.append(';');
        }

        return sb.toString();
    }
}
