package com.blogspot.aptgetmoo.dhjclient.test.item;

import java.io.IOException;
import java.io.InputStream;
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
     * @see #fetchHtml()
     */
    public MockResultPage(String pUrl) throws URISyntaxException {
        super(pUrl);
    }

    /**
     * @return Mocked HTML String, similar to what Jakim's web should to return
     */
    @Override
    public String getParseable() throws IOException {
        final String url = getUrl();
        final String baseUrl = getBaseUrl();
        String res = null;

        if (url.compareTo(baseUrl + "?cari=kfc&type=P&page=1") == 0) {
            // The first page of 4-page result, containing 20 items (of 69).
            res = "/item-product-kfc-1.html";
        } else if (url.compareTo(baseUrl + "?cari=tak%20ada&type=P&page=1") == 0) {
            res = "/item-product-not-found.html";
        } else {
            res = "/item-product-not-found.html";
        }

        InputStream is = getClass().getResourceAsStream(res);

        try {
            return new Scanner(is).useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

}
