package com.blogspot.aptgetmoo.dhjclient.item;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.blogspot.aptgetmoo.dhjclient.parser.Webpage;

/**
 * HTML page returned by Jakim's Direktori Halal web search
 *
 * @author Dzul Nizam
 */
public class ResultPage extends Webpage {

    private final static String DEFAULT_BASE_URL =
            "http://www.halal.gov.my/ehalal/directory_standalone.php";

    private URI mBaseUrl;

    private URI mUrl;

    /**
     * This assigns the default Jakim's web URL. In such a case the URL is invalid, put a new URL
     * in #ResultPage(String pBaseUrl), or the Web is no longer in service :(
     *
     * @throws	URISyntaxException
     * @see #ResultPage(String)
     * @see #getBaseUrl()
     */
    public ResultPage() throws URISyntaxException {
        mBaseUrl = new URI(DEFAULT_BASE_URL);
    }

    /**
     * @param 	pBaseUrl Base URL for Jakim's search result page
     * @throws 	MalformedURLException If given URL is invalid
     * @throws 	URISyntaxException
     */
    public ResultPage(String pBaseUrl) throws URISyntaxException {
        mBaseUrl = new URI(pBaseUrl);
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
    public void setFetchParameters(String pKeyword, String pType, int pPage) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(mBaseUrl);
        builder.addParameter("cari", pKeyword);
        builder.addParameter("type", pType);
        builder.addParameter("page", String.valueOf(pPage));

        mUrl = builder.build();
    }

}
