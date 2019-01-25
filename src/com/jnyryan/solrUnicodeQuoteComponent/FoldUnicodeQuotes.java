/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jnyryan.solrUnicodeQuoteComponent;

import java.io.IOException;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.handler.component.SearchComponent;
import org.apache.solr.request.SolrQueryRequest;

public class FoldUnicodeQuotes extends SearchComponent {

  @Override
  public void prepare(ResponseBuilder rb) throws IOException {
    updateSolrRequest(rb.req);
  }

  @Override
  public void process(ResponseBuilder rb) throws IOException {
  }

  @Override
  public String getDescription() {
    return "Solr Unicode Quote Seqarch Component";
  }

  /**
   * Grab the Q and Q.ALT parameters and remove UNICODE quotes from them.
   *
   * @param req the request to the SOLR handler
   */
  public void updateSolrRequest(SolrQueryRequest req) {	  
    SolrParams params = req.getParams();
    String newQuery = replaceUnicodeDoubleQuotes(params.get(CommonParams.Q));
    ModifiableSolrParams newParams = new ModifiableSolrParams(params);
    newParams.remove(CommonParams.Q);
    newParams.add(CommonParams.Q, newQuery);
    req.setParams(newParams);
  }

  /**
   * Replace UNICODE double quotes with basic Latin standard quote mark (") - &#34; &quot;
   * Replaces:
   *  “ left double quotation mark (\u201C)
   *  ” right double quotation mark (\u201D)
   * 〝 left curly quote (\u301D)
   *  〞right curly quote (\u301E)
   *  „ index quote german scandanavian (\u201E)
   *  « left-pointing double angle quotation mark (\AB)
   *  » right-pointing double angle quotation mark (\BB)
   *  ‟ double high-reversed-9 quotation mark (\u201F)
   *  ❝ heavy double turned comma quotation mark ornament (\u275D)
   *  ❞ heavy double comma quotation mark ornament (\u275E)
   *  ⹂ double low-reversed-9 quotation mark - (\u2E42)
   *  ＂fullwidth quotation mark - (\uFF02)
   *
   * @param s The query string
   * @return Returns {@code s} with UNICODE double quotes replaced as standard double quote
   */
  public String replaceUnicodeDoubleQuotes(String s) {
    return s.replaceAll("[“”〝〞„«»‟❝❞⹂＂]","\"");
  }

}
