package com.blogspot.aptgetmoo.dhjclient.item;

import java.io.Serializable;

/**
 * Item type for Jakim's Direktori Halal search
 *
 * @author Dzul Nizam
 */
public enum ItemType implements Serializable {
    COMPANY ("company", "A"),
    PRODUCT ("product", "P"),
    PREMISES ("premises", "M"),
    MENU ("menu", "Z"),
    SLAUGHTERHOUSE ("slaughterhouse", "S");

    private final String mName;
    private final String mQueryString;

    ItemType(String pName, String pQueryString) {
        mName = pName;
        mQueryString = pQueryString;
    }

    public String getName() {
        return mName;
    }

    public String getQueryString() {
        return mQueryString;
    }

    public String toString() {
        return mName + ": " + mQueryString;
    }
}
