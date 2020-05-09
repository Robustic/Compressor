# Tutorial

## Maven komentorivi komennot

### Luo JavaDoc dokumentaatio

`mvn javadoc:javadoc`

JavaDoc dokumentaatio generoituu hakemistoon:

`/Compressor/target/site/apidocs/overview-summary.html`

### Aja JUnit testit ja generoi Jacoco testiraportti

`mvn test`

Jacoco testiraportti generoituu hakemistoon:

`/Compressor/target/site/jacoco/index.html`

### Generate Checkstyle raportti

`mvn checkstyle:checkstyle`

Checkstyle raportti generoituu hakemistoon:

`/Compressor/target/site/checkstyle.html`

### Suoritettavan jar tiedoston generointi

`mvn package`

Ajettavan jar-paketin voi kopioida hakemistosta `/Compressor/target/` haluamaansa hakemistoon.

Jar ohjelma voidaan suorittaa komennolla 

`java -jar Compressor-1.00.jar`

siinä kansiossa, missä jar-paketti sijaitsee.

## Ohjelman toiminnot

### Help

Ohjelma antaa lyhyet ohjeet komentoriville komennolla 

`java -jar Compressor-1.00.jar help`

### Pakkaaminen Huffmanin algoritmilla

`java -jar Compressor-1.00.jar huff comp <filename>`

missä `<filename>` on tiedostonimi hakemistopolkuineen. Pakatun tiedoston tiedostopäätteeksi annetaan `.huffman`.

### Purkaminen Huffmanin algoritmilla

`java -jar Compressor-1.00.jar huff uncomp <filename>`

missä `<filename>` on tiedostonimi hakemistopolkuineen. Purettavat tiedoston tulee olla pakattu samalla ohjelmalla ja tiedostopäätteenä on oltava `.huffman`.

### Pakkaaminen Lempel-Ziv-Welchin algoritmilla

`java -jar Compressor-1.00.jar lzw comp <filename>`

missä `<filename>` on tiedostonimi hakemistopolkuineen.  Pakatun tiedoston tiedostopäätteeksi annetaan `.lzw`.

### Purkaminen Lempel-Ziv-Welchin algoritmilla

`java -jar Compressor-1.00.jar lzw uncomp <filename>`

missä `<filename>` on tiedostonimi hakemistopolkuineen. Purettavat tiedoston tulee olla pakattu samalla ohjelmalla ja tiedostopäätteenä on oltava `.lzw`.

## Ohjelman sallimat syötteet

Ohjelma sallii pakattaviksi tiedostoiksi teksti- ja binääritiedostoja . Ohjelma hyväksyy pakattavaksi kaikki tiedostot, joiden koko on alle 512 megatavua. Purettavat tiedoston tulee olla pakattu samalla ohjelmalla ja tiedostopäätteen on oltava oikea.


