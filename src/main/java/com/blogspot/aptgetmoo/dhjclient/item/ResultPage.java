package com.blogspot.aptgetmoo.dhjclient.item;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import com.blogspot.aptgetmoo.dhjclient.parser.Webpage;

/**
 * HTML page returned by Jakim's Direktori Halal web search
 *
 * @author Dzul Nizam
 */
public class ResultPage extends Webpage {

    private final static String DEFAULT_BASE_URL =
            "http://www.halal.gov.my/ehalal/directory_standalone.php";

    private URL mBaseUrl;

    private URL mUrl;

    /**
     * This assigns the default Jakim's web URL. In such a case the URL is invalid, put a new URL
     * in #ResultPage(String pBaseUrl), or the Web is no longer in service :(
     *
     * @throws	URISyntaxException
     * @see #ResultPage(String)
     * @see #getBaseUrl()
     */
    public ResultPage() {
        try {
            mBaseUrl = new URL(DEFAULT_BASE_URL);
        } catch (MalformedURLException e) {
            // default URL should not throw MalformedURLException
            e.printStackTrace();
        }
    }

    /**
     * @param 	pBaseUrl Base URL for Jakim's search result page
     * @throws 	MalformedURLException If given URL is invalid
     * @throws 	URISyntaxException
     */
    public ResultPage(String pBaseUrl) throws MalformedURLException {
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

    /**
     * @param 	pKeyword
     * @param 	pType
     * @param 	pPage
     * @throws 	URISyntaxException
     */
    public void setFetchParameters(String pKeyword, String pType, int pPage) throws MalformedURLException {
        try {
            mUrl = new URL(mBaseUrl.toString()
                    + "?cari=" + URLEncoder.encode(pKeyword, "UTF-8")
                    + "&type=" + URLEncoder.encode(pType, "UTF-8")
                    + "&page=" + URLEncoder.encode(String.valueOf(pPage), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
