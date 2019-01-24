# Solr Unicode Quote Component

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


##Test

``` bash
solr create_core -c testCore1 -d sample_techproducts_configs

post -c testCore1 /usr/local/opt/solr-7.3.1/example/exampledocs/*
```

## Build

```
ant compile
ant jar
ant run
```