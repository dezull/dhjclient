package com.blogspot.aptgetmoo.dhjclient.item;

/**
 * Holds a row of result from Jakim's Halal Web
 *
 * @author Dzul Nizam
 */
public class Item {

    public String itemName = "";

    public String itemCompanyCode = "";

    public String itemCompanyName = "";

    public String itemBrand = "";

    public String itemAddress = "";

    public String itemExpiryDate = "";

    public int itemSequence = -1;

    public ItemType itemType;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("itemName: ").append(itemName).append(";")
                .append("itemCompanyCode: ").append(itemCompanyCode).append(";")
                .append("itemCompanyName: ").append(itemCompanyName).append(";")
                .append("itemBrand: ").append(itemBrand).append(";")
                .append("itemAddress: ").append(itemAddress).append(";")
                .append("itemExpiryDate: ").append(itemExpiryDate).append(";")
                .append("itemSequence: ").append(itemSequence).append(";")
                .append("itemType: ").append(itemType)
                .toString();
    }
}
