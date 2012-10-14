package com.example;

import java.util.ArrayList;
import java.io.IOException;

import com.blogspot.aptgetmoo.dhjclient.item.ItemFinder;
import com.blogspot.aptgetmoo.dhjclient.item.ItemType;
import com.blogspot.aptgetmoo.dhjclient.item.Item;
import com.blogspot.aptgetmoo.dhjclient.item.ResultPage;
import com.blogspot.aptgetmoo.dhjclient.company.CompanyFinder;
import com.blogspot.aptgetmoo.dhjclient.company.CompanyPage;
import com.blogspot.aptgetmoo.dhjclient.company.Company;

class Dhj {

    public static void main(String[] args) throws IOException {
        Dhj dhj = new Dhj();
        dhj.printSearchResult(args[0]);
    }

    public void printSearchResult(final String keyword) throws IOException {
        ItemFinder itemFinder = new ItemFinder(new ResultPage());
        ItemType[] types = ItemType.values();

        for (ItemType type : types) {
            ArrayList<Item> items = itemFinder.find(keyword, type, 1);

            System.out.println(type.getName());
            System.out.println("----------------\n");

            for (Item item : items) {
                printItem(item);
                System.out.println("\tcompany:-");
                printCompany(item.itemCompanyCode);
                System.out.println();
            }

            System.out.println();
        }
    }

    public void printItem(Item item) {
        System.out.println("\t" + item.itemSequence + ") name: " + item.itemName);
        System.out.println("\tcomp-code: " + item.itemCompanyCode);
        System.out.println("\tcomp-name: " + item.itemCompanyName);
        System.out.println("\titem brand: " + item.itemBrand);
        System.out.println("\titem expiry: " + item.itemExpiryDate);
        System.out.println("\titem address: " + item.itemAddress);
        System.out.println("\tseq: " + item.itemSequence);
    }

    public void printCompany(String compCode) throws IOException {
        CompanyFinder comFinder = new CompanyFinder(new CompanyPage());
        Company comp = comFinder.find(compCode);

        System.out.println("\tcode: " + comp.code);
        System.out.println("\tname: " + comp.name);
        System.out.println("\tweb: " + comp.web);
        System.out.println("\taddress: " + comp.address.replace("\n", " "));
        System.out.println("\tphone: " + comp.phone);
        System.out.println("\tfax: " + comp.fax);
        System.out.println("\temail: " + comp.email);
        System.out.println("\tjakimOfficer: " + comp.jakimOfficer);
        System.out.println("\tjakimRefNo: " + comp.jakimRefNo);
    }

}
