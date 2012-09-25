package com.blogspot.aptgetmoo.dhjclient.test.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.blogspot.aptgetmoo.dhjclient.item.ResultPage;

public class ResultPageTest {

    private static final String SERVER_URL = "http://does.not.matter/halal.php";

    @Test public void constructorWithDefaultUrl() {
        ResultPage page = new ResultPage();
        assertNotNull(page);
    }

    @Test public void constructor() {
        try {
            assertNotNull(new ResultPage(SERVER_URL));
        } catch (MalformedURLException e) {
            fail("Unexpected exception");
        }
    }

    @Test public void constructorExpectMalformedURLException() {
        final String invalidUrl = "invalidUrl";
        try {
            new ResultPage(invalidUrl);
            fail("Expected MalformedURLException");
        } catch (MalformedURLException e) {}
    }

    @Test public void getBaseUrl() {
        ResultPage page;
        try {
            page = new ResultPage(SERVER_URL);
            assertEquals(SERVER_URL, page.getBaseUrl());
        } catch (MalformedURLException e) {
            fail("Unexpected exception");
        }

        ResultPage pageDefaultUrl = new ResultPage();
        assertNotNull(pageDefaultUrl.getBaseUrl());
    }

    /**
     * Test setFetchParameters() and getUrl()
     */
    @Test public void setFetchParametersAndGetUrl() {
        String keyword = "kfc";
        String type = "P";
        int page = 1;
        String url = "";

        ResultPage result;
        try {
            result = new ResultPage(SERVER_URL);
        } catch (MalformedURLException e) {
            fail("Unexpected exception");
            return;
        }
        result.setFetchParameters(keyword, type, page);

        url = SERVER_URL + "?cari=" + keyword + "&type=" + type + "&page=" + page;
        assertEquals(url, result.getUrl());

        // Reset parameters
        keyword = "ayam";
        type = "P";
        page = 2;

        result.setFetchParameters(keyword, type, page);
        url = SERVER_URL + "?cari=" + keyword + "&type=" + type + "&page=" + page;
        assertEquals("Failed to reset parameters (#setFetchParameters)", url, result.getUrl());
    }

    @Test public void getParseable() {
        String keyword = "kfc";
        String type = "P";
        int page = 1;

        // Test with valid URL, but wherever it points to doesn't matter
        final String validUrl = "http://www.google.com";
        ResultPage mockResult;

        try {
            mockResult = new MockResultPage(validUrl);
            mockResult.setFetchParameters(keyword, type, page);
            try {
                assertNotNull(mockResult.getParseable());
            } catch (IOException e) {
                fail("Unexpected IOException");
            }
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

}
