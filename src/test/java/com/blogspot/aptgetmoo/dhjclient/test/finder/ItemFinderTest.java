package com.blogspot.aptgetmoo.dhjclient.test.finder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.aptgetmoo.dhjclient.finder.ItemFinder;
import com.blogspot.aptgetmoo.dhjclient.finder.IItemFinder;
import com.blogspot.aptgetmoo.dhjclient.finder.Item;
import com.blogspot.aptgetmoo.dhjclient.finder.ItemType;

public class ItemFinderTest {

    private static final int ROWS = 20;

    private IItemFinder client;

    @Before public void setUp() throws Exception {
        client = new ItemFinder(new MockResultPage(), ROWS);
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void testDHJClientDefaultItemsPerPage() {
        client = new ItemFinder(new MockResultPage());

        assertNotNull(client);
        assertEquals(20, client.getItemsPerPage());
        assertEquals(0, client.getResultCount());
        assertEquals(0, client.getTotalPage());
    }

    @Test public void testDHJClient() {
        assertNotNull(client);
        assertEquals(20, client.getItemsPerPage());
        assertEquals(0, client.getResultCount());
        assertEquals(0, client.getTotalPage());
    }

    /**
     * TODO: Should the parsed result be unit-tested? since it has no well defined structure :( ,
     * it might be a total waste of time
     */
    @Test public void testFind() {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            items = client.find("kfc", ItemType.PRODUCT, 1);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }

        assertEquals(20,items.size());
    }

    @Test public void testGetTotalPage() {
        assertEquals(0, client.getTotalPage());

        try {
            client.find("kfc", ItemType.PRODUCT, 1);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }

        assertEquals(4, client.getTotalPage());
    }

    @Test public void testGetResultCount() {
        assertEquals(0, client.getResultCount());

        try {
            client.find("kfc", ItemType.PRODUCT, 1);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }

        assertEquals(69, client.getResultCount());
    }

}
