# Solr Unicode Quote Component

A search component that can be added to request handlers to manage quotes before the tokeniser and analyser stage.

The following are folded to a staight quote " (\u0022)
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
   
## Usage

Edit the `solrconfig.xml` of the core you want to enable the component for

Add the following to that file

The classpath to your component folder

	``` xml
	<lib dir="${solr.install.dir:../../../..}/dist/" regex="unicode-quote-component-\d.*\.jar" />
	```

Register the custom component
	``` xml
	<searchComponent name="unicodeQuoteComponent" class="com.jnyryan.solr.components.FoldUnicodeQuotes"/>
	```

In the request handler add the component as a first-component
``` xml
    <arr name="first-components">
      <str>unicodeQuoteComponent</str>
    </arr>
```

e.g.

``` xml
  <requestHandler name="/select" class="solr.SearchHandler">
    <lst name="defaults">
      <str name="echoParams">explicit</str>
      <int name="rows">10</int>
    </lst>
    <arr name="first-components">
      <str>unicodeQuoteComponent</str>
      <!-- <str>unicodeQuoteComponentJar</str> -->
    </arr>
  </requestHandler>
```

## Build

	```
	ant compile
	ant jar
	ant run
	```

## Test


	``` bash
	ant test
	```