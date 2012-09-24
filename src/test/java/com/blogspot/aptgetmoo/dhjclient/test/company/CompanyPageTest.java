package com.blogspot.aptgetmoo.dhjclient.test.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.junit.Test;

import com.blogspot.aptgetmoo.dhjclient.company.CompanyPage;
import com.blogspot.aptgetmoo.dhjclient.parser.Webpage;

public class CompanyPageTest {

    private static final String COMP_CODE_1 = "COMP-CODE-1234";

    @Test public void testConstructor() {
        Webpage page = new CompanyPage(COMP_CODE_1);

        assertNotNull(page);
    }

    @Test public void testConstructorWithValidUrl() {
        final String validUrl = "http://www.google.com";
        Webpage page;

        try {
            page = new CompanyPage(validUrl, COMP_CODE_1);
            assertNotNull(page);
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

    @Test public void testConstructorWithInvalidUrl() {
        final String invalidUrl = "invalidUrl";

        try {
            new CompanyPage(invalidUrl, COMP_CODE_1);
            fail("Expected MalformedURLException");
        } catch (MalformedURLException e) {}
    }

    @Test public void testGetBaseUrlDefault() {
        Webpage page = new CompanyPage(COMP_CODE_1);

        assertNotNull(page.getBaseUrl());
    }

    @Test public void testGetBaseUrlCustom() {
        final String validUrl = "http://www.google.com";
        Webpage page;

        try {
            page = new CompanyPage(validUrl, COMP_CODE_1);
            assertEquals(validUrl, page.getBaseUrl());
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

    @Test public void testGetUrlDefault() {
        Webpage page = new CompanyPage(COMP_CODE_1);
        final String baseUrl = page.getBaseUrl();
        final String url = page.getUrl();

        assertFalse(baseUrl.compareTo(url) == 0);
        assertTrue(url.startsWith(baseUrl));
    }

    @Test public void testGetUrlDefaultCustom() {
        final String validUrl = "http://www.google.com";
        Webpage page;

        try {
            page = new CompanyPage(validUrl, COMP_CODE_1);
            final String baseUrl = page.getBaseUrl();
            final String url = page.getUrl();

            assertEquals(validUrl, baseUrl);
            assertFalse(baseUrl.compareTo(url) == 0);
            assertTrue(url.startsWith(baseUrl));
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

    @Test public void testGetCompanyCode() {
        Webpage page = new CompanyPage(COMP_CODE_1);

        assertEquals(COMP_CODE_1, ((CompanyPage) page).getCompanyCode());
    }

    @Test public void testGetCompanyCodeCustome() {
        final String validUrl = "http://www.google.com";

        Webpage page;
        try {
            page = new CompanyPage(validUrl, COMP_CODE_1);
            assertEquals(COMP_CODE_1, ((CompanyPage) page).getCompanyCode());
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

}
