package com.blogspot.aptgetmoo.dhjclient.test.finder;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

import com.blogspot.aptgetmoo.dhjclient.finder.Finder;
import com.blogspot.aptgetmoo.dhjclient.finder.IFinder;
import com.blogspot.aptgetmoo.dhjclient.finder.Item;
import com.blogspot.aptgetmoo.dhjclient.finder.ItemType;

public class DHJClientTest extends TestCase {

	private static final int ROWS = 20;

	private IFinder client;

	protected void setUp() throws Exception {
		super.setUp();

		client = new Finder(new MockResultPage(), ROWS);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDHJClientDefaultItemsPerPage() {
		client = new Finder(new MockResultPage());

		assertNotNull(client);
		assertEquals(20, client.getItemsPerPage());
		assertEquals(0, client.getResultCount());
		assertEquals(0, client.getTotalPage());
	}

	public void testDHJClient() {
		assertNotNull(client);
		assertEquals(20, client.getItemsPerPage());
		assertEquals(0, client.getResultCount());
		assertEquals(0, client.getTotalPage());
	}

	/**
	 * TODO: Should the parsed result be unit-tested? since it has no well defined structure :( ,
	 * it might be a total waste of time
	 */
	public void testFind() {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			items = client.find("kfc", ItemType.PRODUCT, 1);
		} catch (IOException e) {
			fail("Unexpected IOException");
		}

		assertEquals(20,items.size());
	}

	public void testGetTotalPage() {
		assertEquals(0, client.getTotalPage());

		try {
			client.find("kfc", ItemType.PRODUCT, 1);
		} catch (IOException e) {
			fail("Unexpected IOException");
		}

		assertEquals(4, client.getTotalPage());
	}

	public void testGetResultCount() {
		assertEquals(0, client.getResultCount());

		try {
			client.find("kfc", ItemType.PRODUCT, 1);
		} catch (IOException e) {
			fail("Unexpected IOException");
		}

		assertEquals(69, client.getResultCount());
	}

}
