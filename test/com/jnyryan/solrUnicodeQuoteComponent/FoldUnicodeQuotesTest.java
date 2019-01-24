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
package test.com.jnyryan.solrUnicodeQuoteComponent;

import org.junit.Test;
import com.jnyryan.solrUnicodeQuoteComponents;

import static org.junit.Assert.*;

public class SolrUnicodeQuoteComponentsTest {

    @Test
    public void EmptyString() {
        assertEquals("","");
    }

    @Test
    public void NoQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuote()));
        assertEquals("",fuq.replaceUnicodeDoubleQuotes(""));
    }

    @Test
    public void NormalQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        assertEquals("",fuq.replaceUnicodeDoubleQuotes(""));
    	assertEquals("we are the music makers", "we are the music makers");
        assertEquals("we are the music makers",fuq.replaceUnicodeDoubleQuotes("we are the music makers"));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the \"music makers\" "));
        assertEquals(" \" \" \" \" \" \" \" \" \" \" \" \" ",fuq.replaceUnicodeDoubleQuotes(" “ ” 〝 〞 „ « » ‟ ❝ ❞ ⹂ ＂ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the “music makers” "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the 〝music makers〞 "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the „music makers„ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the «music makers» "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ‟music makers‟ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ❝music makers❞ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ⹂music makers⹂ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ＂music makers＂ "));
    }
}
