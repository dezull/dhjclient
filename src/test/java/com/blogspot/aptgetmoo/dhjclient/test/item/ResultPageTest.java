package com.blogspot.aptgetmoo.dhjclient.test.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.blogspot.aptgetmoo.dhjclient.item.ResultPage;

public class ResultPageTest {

    private static final String SERVER_URL = "http://does.not.matter/halal.php";

    @Test public void constructorWithDefaultUrl() throws URISyntaxException {
        ResultPage page = new ResultPage();
        assertNotNull(page);
    }

    @Test public void constructor() throws URISyntaxException {
        assertNotNull(new ResultPage(SERVER_URL));
    }

    @Test public void constructorExpectURISyntaxException() {
        final String invalidUrl = ":invalidUrl";
        try {
            new ResultPage(invalidUrl);
            fail("Expected URISyntaxException");
        } catch (URISyntaxException e) {}
    }

    @Test public void getBaseUrl() throws URISyntaxException {
        ResultPage page = new ResultPage(SERVER_URL);
        assertEquals(SERVER_URL, page.getBaseUrl());

        ResultPage pageDefaultUrl = new ResultPage();
        assertNotNull(pageDefaultUrl.getBaseUrl());
    }

    /**
     * Test setFetchParameters() and getUrl()
     * @throws URISyntaxException
     */
    @Test public void setFetchParametersAndGetUrl() throws URISyntaxException {
        String keyword = "kfc";
        String type = "P";
        int page = 1;
        String url = "";

        ResultPage result = new ResultPage(SERVER_URL);

        try {
            result.setFetchParameters(keyword, type, page);
        } catch (URISyntaxException e) {
            fail("Unexpected URISyntaxException");
        }

        url = SERVER_URL + "?cari=" + keyword + "&type=" + type + "&page=" + page;
        assertEquals(url, result.getUrl());

        // Reset parameters
        keyword = "ayam";
        type = "P";
        page = 2;

        try {
            result.setFetchParameters(keyword, type, page);
        } catch (URISyntaxException e) {
            fail("Unexpected URISyntaxException");
        }

        url = SERVER_URL + "?cari=" + keyword + "&type=" + type + "&page=" + page;
        assertEquals("Failed to reset parameters (#setFetchParameters)", url, result.getUrl());
    }

    @Test public void getParseable() throws URISyntaxException {
        String keyword = "kfc";
        String type = "P";
        int page = 1;

        // Test with valid URL, but wherever it points to doesn't matter
        final String validUrl = "http://www.google.com";
        ResultPage mockResult = new MockResultPage(validUrl);

        try {
            mockResult.setFetchParameters(keyword, type, page);
            try {
                assertNotNull(mockResult.getParseable());
            } catch (IOException e) {
                fail("Unexpected IOException");
            }
        } catch (URISyntaxException e) {
            fail("Unexpected URISyntaxException");
        }
    }

}
