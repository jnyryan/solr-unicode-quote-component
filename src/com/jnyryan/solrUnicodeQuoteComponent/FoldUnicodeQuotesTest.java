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

import org.junit.Test;


import static org.junit.Assert.*;

public class FoldUnicodeQuotesTest {

    @Test
    public void emptyString() {
        assertEquals("","");
    }

    @Test
    public void noQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        assertEquals("",fuq.replaceUnicodeDoubleQuotes(""));
    }
    
    @Test
    public void standardQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the \"music makers\" "));
    }

    @Test
    public void allTheQuotesQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        assertEquals(" \" \" \" \" \" \" \" \" \" \" \" \" ",fuq.replaceUnicodeDoubleQuotes(" “ ” 〝 〞 „ « » ‟ ❝ ❞ ⹂ ＂ "));
    }
    
    @Test
    public void doubleQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the “music makers” "));
    }
    
    @Test
    public void curlyQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the 〝music makers〞 "));
    }
    
    @Test
    public void theRestOfTheQuotes() {
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the „music makers„ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the «music makers» "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ‟music makers‟ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ❝music makers❞ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ⹂music makers⹂ "));
        assertEquals("we are the \"music makers\" ",fuq.replaceUnicodeDoubleQuotes("we are the ＂music makers＂ "));
    }
}
