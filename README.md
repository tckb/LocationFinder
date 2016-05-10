# Location Finder

A small command tool for querying the location information of a city  using GoEuro REST Webservice. The corresponding result locations are saved on disk as a comma-seperated-value file.

The project uses [RetroFit](http://square.github.io/retrofit/) to make REST calls to the webservice.

Compiling code & Usage
===========
The project was developed  and compiled using Apache Maven 3.2.2 and Oracle Java 8
neverthless, the project is expected to be backward compatible until Oracle/Open Java JDK 6

Using Apache Maven:

```
$git clone ...
$cd GoEuroLocFinder
.../GoEuroLocFinder$ mvn package

```

The above instruction would create an executable "fat" jar in `target` folder containing all of its dependencies.

Usage:

```
java -jar <thisJarFile> <city> [ outputFile [-v] ]
Optional args::
	 	outputFile: /path/to/Ouputfile.csv (default: /path/to/home/<city>_locations.csv)
		-v : Verbous output, displays raw json file
```


Example
======
```
$ java -jar GoEuroLocFinder-fatjar.jar Berlin
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
---

```
$ java -jar GoEuroLocFinder-fatjar.jar invalidCity
Locations founds for : invalidCity
********************************
(None)
```
---

```
$ java -jar GoEuroLocFinder-fatjar.jar abc ./out.csv -v
---> HTTP GET http://api.goeuro.com/api/v2/position/suggest/en/abc
---> END HTTP (no body)
<--- HTTP 200 http://api.goeuro.com/api/v2/position/suggest/en/abc (241ms)
Transfer-Encoding: chunked
: HTTP/1.1 200 OK
Real-Client: 131.220.196.151
Server: nginx/1.4.6 (Ubuntu)
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT
Connection: keep-alive
Pragma: no-cache
Date: Wed, 18 Feb 2015 21:59:57 GMT
LB-Backend: 10.200.0.71
Cache-Control: no-cache, no-store, must-revalidate
X-Backend-Server: search-be-07
Vary: Accept-Encoding
Expires: 0
Content-Type: application/json;charset=UTF-8

[{"_id":314974,"key":null,"name":"Albacete","fullName":"Albacete (ABC), Spain","iata_airport_code":"ABC","type":"airport","country":"Spain",
"geo_position":{"latitude":38.95325,"longitude":-1.862009},"locationId":null,
"inEurope":true,"countryCode":"ES","coreCountry":true,"distance":null}]
<--- END HTTP (288-byte body)
Locations founds for : abc
********************************
Albacete (ABC), Spain [ airport ]

CSV File saved at /Users/TCKB/Documents/Projects/GoEuroLocFinder/target/./out.csv
```
----
Unless specified, the entire code is licensed under GNU GPLV2.
