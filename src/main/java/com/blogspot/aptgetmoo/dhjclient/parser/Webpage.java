package com.blogspot.aptgetmoo.dhjclient.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parseable Webpage
 *
 * @author Dzul Nizam
 */
public abstract class Webpage implements IParseable {

    @Override
    public String getParseable() throws IOException {
        URL url = new URL(getUrl());

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();

            try {
                return new Scanner(is).useDelimiter("\\A").next();
            } catch (NoSuchElementException e) {
                return "";
            }
        } catch (ProtocolException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * @return Search page URL
     */
    abstract public String getBaseUrl();

    /**
     * @return URL with parameters
     */
    abstract public String getUrl();

}
