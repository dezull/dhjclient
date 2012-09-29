package com.blogspot.aptgetmoo.dhjclient.test.item;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.blogspot.aptgetmoo.dhjclient.item.ResultPage;

public class MockResultPage extends ResultPage {

    public MockResultPage() throws URISyntaxException {
        super();
    }

    /**
     * @param pUrl URL will be ignored.
     * @throws URISyntaxException
     * @throws MalformedURLException
     * @see #fetchHtml()
     */
    public MockResultPage(String pUrl) throws URISyntaxException {
        super(pUrl);
    }

    /**
     * Should return the first page of 4-page result, containing 20 items (of 69).
     * keyword, page & type of HTTP GET parameters are not handled (ignored).
     *
     * @return Mocked HTML String, similar to what Jakim's web should to return
     */
    @Override
    public String getParseable() {
        InputStream is = getClass().getResourceAsStream("/kfc_1.html");

        try {
            return new Scanner(is).useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

}
