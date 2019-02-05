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

## Usage

Download latest JAR at time of writing [here](https://github.com/jnyryan/solr-unicode-quote-component/releases/download/v1.0.2/solr-unicode-quote-component-7.5.jar)

Copy the `unicode-quote-component-X.X.jar` file from the to the location of your components

Add the following the `solrconfig.xml` for the core you want to use the component

``` xml
  <!--adding the classpath of the unicode-quote-component-->
  <lib dir="${solr.install.dir:../../../..}/dist/" regex="unicode-quote-component-\d.*\.jar" />
```


``` xml
  <!--Register the custom component-->
  <searchComponent name="unicodeQuoteComponent" class="com.jnyryan.solrUnicodeQuoteComponent.FoldUnicodeQuotes"/>
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

## Test it

After adding the plugin to the core (like the techproducts example), you can see these three requests will behave the same

[no Quotes](http://localhost:8983/solr/techproducts/select?q=A Song of Ice and Fire)
[standard Quotes](http://localhost:8983/solr/techproducts/select?q="A Song of Ice and Fire")
[Curly Quotes](http://localhost:8983/solr/techproducts/select?q=“A Song of Ice and Fire”)

## Development

#### Build

``` bash
  ant compile
  ant jar
```

The jar is placed in the ./dist folder. use it wisely

#### Development Test

``` bash
  ant test
```

#### Setting up Eclipse Dev Environment

If you have built lucene-solr locally and are running in debug in Eclipse, create test cores as follows.

``` bash
touch ./eclipse-build/solr-server/solr/techproducts/core.properties
mkdir ./eclipse-build/solr-server/solr/techproducts
cp -r ./eclipse-build/solr-server/solr/configsets/sample_techproducts_configs/ ./eclipse-build/solr-server/solr/techproducts
solr/bin/post -p 8983 -c techproducts solr/example/exampledocs/*
```

## Troubleshooting the Build

Things to note:

- Make sure the component folder is correct, a WARNING may be issued by SOLR if the files cannot be located
- Check the Java Target Version
  - unzip the jar manifest to see the target version
  `unzip -q -c dist/jar/solr-unicode-quote-component-7.5.jar META-INF/MANIFEST.MF`

  todo
   1. check classpath with clean build
   2.

   cp dist/jar/solr-unicode-quote-component-7.5.jar ~/git/lucene-solr/solr/dist