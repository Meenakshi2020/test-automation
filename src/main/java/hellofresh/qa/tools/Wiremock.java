package hellofresh.qa.tools;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Class to start and stop the Wiremock server in order to mock the POST request
 */
public class Wiremock {

    private Logger log = LoggerFactory.getLogger(Wiremock.class);
    private WireMockServer wireMockServer = new WireMockServer();

    public void wiremockStart()
    {
        log.info("Starting Wiremock server");
        wireMockServer.start();
        configureFor("localhost", 8080);

        // Stub for POST request
        stubFor(post(urlEqualTo("/post"))
                .willReturn(aResponse()
                        .withStatus(200).withHeader("Content-Type", "application/json")
                        .withBodyFile("postResponse.json")));
    }

    public void wiremockStop()
    {
        log.info("Stopping Wiremock server");
        wireMockServer.stop();
    }
}