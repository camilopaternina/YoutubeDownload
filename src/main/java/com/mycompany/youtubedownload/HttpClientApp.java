
package com.mycompany.youtubedownload;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

public class HttpClientApp {
    
    HttpClient client;
    HttpRequest requestGET;

    public HttpClientApp() {
        client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();
    }
   
    
    public File getImage(String url, File imagen) throws URISyntaxException, InterruptedException, ExecutionException{ 
        requestGET = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
        
        client.sendAsync(requestGET, BodyHandlers.ofFile(imagen.toPath())).get();
        return imagen;
    }
    
    public String getString(String url) throws URISyntaxException, InterruptedException, ExecutionException{
        requestGET = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
        
        HttpResponse response = (HttpResponse) client.sendAsync(requestGET, BodyHandlers.ofString()).get();
        return response.body().toString();
    }
}
