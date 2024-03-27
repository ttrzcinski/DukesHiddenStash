package org.ttrzcinski.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Obtains HTTP response of URL call.<br/>
 * <br/>
 * Created 31.08.19 as a part of Project DukesHiddenStash.
 *
 * @author Tomasz Trzciński <trzcinski.tomasz.1988@gmail.com>
 * @version 1.0
 * @since 1.12
 */
public class HTTPObtainer {

  /**
   * Obtains content of HTTP web page.
   *
   * @param uri given uri
   * @return list of String content of the page
   */
  public static List<String> obtainAsList(String uri) {
    List<String> response = new ArrayList<>();
    BufferedReader in = null;
    try {
      // TODO UPDATE as it is deprecated
      URL test = new URL(uri);
      URLConnection uc = test.openConnection();
      uc.addRequestProperty("User-Agent", "Mozilla/4.0");
      in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
      response = in.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      SafeClose.close(in);
    }
    return response;
  }

  /**
   * Obtains content of HTTP web page.
   *
   * @param uri given uri
   * @return String content of the page
   */
  public static String obtainAsString(String uri) {
    return String.join("", obtainAsList(uri));
  }

  /**
   * Obtains content of HTTP web page.
   *
   * @param uri given uri
   * @return list of String content of the page
   */
  public static List<String> obtainAsListInFuture(String uri) {
    List<String> response = new ArrayList<>();
    // Building URL request
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .GET()
        .build();
    // Creating response body handler
    HttpResponse.BodyHandler<String> bodyHandler =
        HttpResponse.BodyHandlers.ofString();
    // Receiving response via HttpClient
    CompletableFuture<HttpResponse<String>> future =
        HttpClient.newHttpClient()
            .sendAsync(request, bodyHandler);
    future.thenApply(HttpResponse::body)
        .thenAccept(response::add)
        .join();
    return response;
  }

  /**
   * Obtains content of HTTP web page.
   *
   * @param uri given uri
   * @return String content of the page
   */
  public static String obtainAsStringInFuture(String uri) {
    return String.join("", obtainAsListInFuture(uri));
  }

}
