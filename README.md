# Location Finder

A small command tool for querying the location information of a city  using GoEuro REST Webservice. The corresponding result locations are saved on disk as a comma-seperated-value file.

The project uses [RetroFit](http://square.github.io/retrofit/) to make REST calls to the webservice.

Compiling code & Usage
===========

The project was developed  and compiled using Apache Maven 3.2.2 and Oracle Java 8

The project contains two branches corresponding to the flavours :
* simple & minimalistic, [command line tool](#Commandline-tool).
* _production_-grade, [interactive shell](#Interactive-shell) i.e.,   [REPL](https://en.wikipedia.org/wiki/Read%E2%80%93eval%E2%80%93print_loop) version

regardless of the flavours, both achieve the same objective.

Building:
```
$git clone ...
$git checkout [master|simple]
$mvn clean package
$java -jar target/<_jar_file>.jar [city_name]
```



#Commandline-tool
[branch](https://github.com/tckb/LocationFinder/tree/simple)
```
java -jar  target/<_jar_file>.jar Berlin
Locations founds for : Berlin
********************************
Berlin, Germany [ location ]
Berlingo, Italy [ location ]
Berlingerode, Germany [ location ]
Bernau bei Berlin, Germany [ location ]
Berlin Tegel (TXL), Germany [ airport ]
Berlin Schönefeld (SXF), Germany [ airport ]

CSV File saved at /Users/TCKB/Berlin_locations.csv
```


#Interactive-shell
[branch](https://github.com/tckb/LocationFinder/tree/master)


###As a simple command line tool


```
$java -jar target/<_jar_file>.jar berlin

...
2016-05-10 18:59:42.261 DEBUG 13936 --- [           main] com.goeuro.service.impl.GoEuroService    : Using Api BaseUrl: http://api.goeuro.com/api/v2
2016-05-10 18:59:42.331 DEBUG 13936 --- [           main] c.g.service.impl.CSVLocationSerializer   : Serializing date to directory: /Users/user123/
2016-05-10 18:59:42.977 DEBUG 13936 --- [           main] com.goeuro.view.CLView                   : Search location: berlin
2016-05-10 18:59:44.467  INFO 13936 --- [           main] com.goeuro.view.CLView                   : Found results:
Id		Name[Type]
====================================================
376217		Berlin, Germany[location]
448103		Berlingo, Italy[location]
425332		Berlingerode, Germany[location]
425326		Bernau bei Berlin, Germany[location]
314826		Berlin Tegel (TXL), Germany[airport]
314827		Berlin Schönefeld (SXF), Germany[airport]
334196		Berlin Hbf, Germany[station]
333977		Berlin Ostbahnhof, Germany[station]

 [ Saving result to file: out.csv ]
```

###For Opening interactive shell

```
$java -jar target/<_jar_file>.jar
...
2016-05-10 16:01:30.905 DEBUG 12847 --- [           main] com.goeuro.service.impl.GoEuroService    : Using Api BaseUrl: http://api.goeuro.com/api/v2
2016-05-10 16:01:30.962 DEBUG 12847 --- [           main] c.g.service.impl.CSVLocationSerializer   : Serializing date to directory: /Users/user123/
2016-05-10 16:01:31.320  INFO 12847 --- [           main] com.goeuro.view.CLView                   : Started CLView in 2.351 seconds (JVM running for 2.959)
=======================================
 _____       ____
 / ___/___   / __/__ __ ____ ___
/ (_ // _ \ / _/ / // // __// _ \
\___/ \___//___/ \_,_//_/   \___/

=======================================
Version:1.0.0
Welcome to GoEuro's CL Utility
goeuro-cli>[TAB]
cls       exit      greet     help      search
goeuro-cli>greet
 Hello stranger!
goeuro-cli>help search
Keyword:                   search
Description:               Search for the location using the location service
 Keyword:                  location
   Help:                   city  or location name
   Mandatory:              true
   Default if specified:   '__NULL__'
   Default if unspecified: '__NULL__'

 Keyword:                  save
   Help:                   save the result to disk
   Mandatory:              false
   Default if specified:   'out.csv'
   Default if unspecified: '__NULL__'

* search - Search for the location using the location service
goeuro-cli>search --location delhi

Id        Name[Type]
====================================================
431070        Cueva del Hierro, Spain[location]
385208        Delhi, India[location]
462342        Delhi, USA[location]
460670        Delhi Hills, USA[location]
372325        Delhi, Canada[location]
386467        New Delhi, India[location]
315341        Delhi (DEL), India[airport]

goeuro-cli>search --location berlin

Id        Name[Type]
====================================================
376217        Berlin, Germany[location]
448103        Berlingo, Italy[location]
425332        Berlingerode, Germany[location]
425326        Bernau bei Berlin, Germany[location]
314826        Berlin Tegel (TXL), Germany[airport]
314827        Berlin Schönefeld (SXF), Germany[airport]
334196        Berlin Hbf, Germany[station]
333977        Berlin Ostbahnhof, Germany[station]

goeuro-cli>search --location berlin --save

Id        Name[Type]
====================================================
376217        Berlin, Germany[location]
448103        Berlingo, Italy[location]
425332        Berlingerode, Germany[location]
425326        Bernau bei Berlin, Germany[location]
314826        Berlin Tegel (TXL), Germany[airport]
314827        Berlin Schönefeld (SXF), Germany[airport]
334196        Berlin Hbf, Germany[station]
333977        Berlin Ostbahnhof, Germany[station]

 [ Saving result to file: out.csv ]
goeuro-cli>search --location singapire --save singapore.csv
(No results found for the location)
goeuro-cli>search --location singapore --save singapore.csv

Id        Name[Type]
====================================================
406081        Singapore, Singapore[location]
315254        Singapore (SIN), Singapore[airport]

 [ Saving result to file: singapore.csv ]
goeuro-cli>help
* clear - Clears the console
* cls - Clears the console
* exit - Exits the shell
* greet - Print a simple hello world message
* help - List all commands usage
* quit - Exits the shell
* search - Search for the location using the location service

goeuro-cli>exit
```
----
Unless specified, the entire code is licensed under GNU GPLV2.
