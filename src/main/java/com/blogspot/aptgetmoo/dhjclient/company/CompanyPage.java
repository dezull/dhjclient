package com.blogspot.aptgetmoo.dhjclient.company;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.blogspot.aptgetmoo.dhjclient.parser.Webpage;

/**
 * HTML page representing Jakim's Direktori Halal - Company page
 *
 * @author Dzul Nizam
 */
public class CompanyPage extends Webpage {

    private final static String DEFAULT_BASE_URL =
            "http://www.halal.gov.my/ehalal/directory/slm_viewdetail.php";

    private URL mBaseUrl;

    private URL mUrl;

    private String mCompanyCode;

    /**
     * This assigns the default Jakim's web URL. In such a case the URL is invalid, put a new URL
     * in #CompanyPage(String pBaseUrl), or the Web is no longer in service :(
     *
     * @see #CompanyPage(String)
     * @see #getBaseUrl()
     */
    public CompanyPage() {
        try {
            mBaseUrl = new URL(DEFAULT_BASE_URL);
        } catch (MalformedURLException e) {
            // default URL should not throw MalformedURLException
            e.printStackTrace();
        }
    }

    /**
     * @param 	pBaseUrl				Base URL for Jakim's search result page
     * @throws 	MalformedURLException 	If given URL is invalid
     */
    public CompanyPage(String pBaseUrl) throws MalformedURLException {
        mBaseUrl = new URL(pBaseUrl);
    }

    @Override
    public String getBaseUrl() {
        return mBaseUrl.toString();
    }

    @Override
    public String getUrl() {
        return mUrl.toString();
    }

    @Override
    public String getParseable() throws IOException {
        final String companyCode = getCompanyCode();

        if (!companyCode.matches("[a-zA-Z0-9-]{4,}")) {
            throw new IOException("Invalid company code");
        }

        if (companyCode == null || companyCode.trim().isEmpty()) {
            throw new IOException("getCompanyCode() returns null or is empty");
        }

        return super.getParseable();
    }

    /**
     * @return	Company code
     */
    public String getCompanyCode() {
        return mCompanyCode;
    }

    /**
     * @param 	pCompanyCode Company code
     * @throws MalformedURLException
     */
    public void setCompanyCode(String pCompanyCode) throws MalformedURLException {
        mCompanyCode = pCompanyCode;

        try {
            mUrl = new URL(mBaseUrl.toString() + "?comp_code="
                    + URLEncoder.encode(mCompanyCode, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
