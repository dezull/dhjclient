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
    SLAUGHTER ("slaughter", "S");

    private final String mFileName;
    private final String mQueryStr;

    ItemType(String fileName, String queryStr) {
        mFileName = fileName;
        mQueryStr = queryStr;
    }

    public String getFileName() {
        return mFileName;
    }

    public String getQueryStr() {
        return mQueryStr;
    }

    public String toString() {
        return mFileName + ": " + mQueryStr;
    }
}
