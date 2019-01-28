# Solr Unicode Quote Component

A search component that can be added to request handlers to manage quotes before the tokeniser and analyser stage.

In other word - curly quotes - a way to remap them to standard quotes.

The following requests will behave the same:

- http://localhost:8983/solr/temp1/select?q="A Song of Ice and Fire"
- http://localhost:8983/solr/temp1/select?q=“A Song of Ice and Fire”
- http://localhost:8983/solr/temp1/select?q=«A Song of Ice and Fire»

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

## Development Build

``` bash
  ant compile
  ant jar
```

## Development Test


``` bash
  ant test
```

## Usage

Copy the `unicode-quote-component-X.X.jar` file to the location of your components

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
    </arr>
  </requestHandler>
```

## Test it yourself

Download and install [SOLR](https://lucene.apache.org/solr/guide/7_6/installing-solr.html) or build it yourself from [source](https://github.com/apache/lucene-solr)

``` bash
solr create_core -c techproducts -d sample_techproducts_configs
post -c techproducts /usr/local/opt/solr-7.3.1/example/exampledocs/*
```

## Development Build

Things to note:

- Make sure the component folder is correct, a WARNING may be issued by SOLR if the files cannot be located
- Check the Java Target Version
  - unzip the jar manifest to see the target version
  `unzip -q -c dist/jar/solr-unicode-quote-component-7.5.jar META-INF/MANIFEST.MF`

  todo
   1. check classpath with clean build
   2.

   cp dist/jar/solr-unicode-quote-component-7.5.jar ~/git/lucene-solr/solr/dist