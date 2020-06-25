package org.clxmm.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author clx
 * @date 2020-06-25 22:08
 */
public class MockServer {


    public static void main(String[] args) throws IOException {
        configureFor("127.0.0.1", 8060);
        //清除以前的配置
        removeAllMappings();
   /*     //language=JSON
        String s = "{\"id\":1}";
        stubFor(get(urlEqualTo("/order/1"))
                .willReturn(aResponse().
                        withBody(s).
                        withStatus(200)));*/

        mock("/order/1","1");
        mock("/order/2","2");


    }


    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mooc/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }


}
