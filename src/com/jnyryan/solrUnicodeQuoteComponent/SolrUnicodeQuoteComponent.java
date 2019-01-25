package com.jnyryan.solrUnicodeQuoteComponent;

class SolrUnicodeQuoteComponent {
    public static void main(String[] args) {
        System.out.println("You're running SolrUnicodeQuoteComponent!");
        FoldUnicodeQuotes fuq = new FoldUnicodeQuotes();
        System.out.println("You're running " + fuq.getDescription() + "!");
    }
}