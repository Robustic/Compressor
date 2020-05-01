# Ohjelman toteutus

## Ohjelman koodin rakenne

Ohjelman koodi on jaettu paketteihin siten, että kukin paketti hoitaa sille kuuluvan tason tehtäviä.

Paketti `project` käytetään ohjelman käynnistämiseen, eikä se sisällä mitään muuta logiikkaa. Se sisältää main-funktion, josta ohjelman suoritus alkaa.

Lähimpänä käyttäjää on `ui` paketti, joka sisältää tekstikäyttöliittymän toteuttamiseen käytetyn koodin.

Paketti `algorithms` sisältää pakkausalgoritmien korkean tason toteutuksen.

Paketti `datastructures` sisältää toteutetut tietorakenteet ja niiden käsittelyyn tehdyn yksityiskohtaisen koodin.

Paketti `fileio` sisältää tiedostojen lukemiseen ja kirjoittamiseen käytetyt toiminnot.

## Huffman

[Huffman](https://en.wikipedia.org/wiki/Huffman_coding)

## Lempel-Ziv-Welch

[Lempel-Ziv-Welch](http://web.mit.edu/6.02/www/s2012/handouts/3.pdf)

[Lempel-Ziv-Welch](http://www.ajer.org/papers/v3\(2\)/C0322226.pdf)

## Saavutetut aika- ja tilavaativuudet

## Suorituskyky- ja O-analyysivertailu

Kunkin pakkausalgoritmin toteutuksessa saavutettua tehokkuutta mitataan pakkauksen tilasäästöllä verrattuna alkuperäisen tiedoston kokoon sekä pakkauksen ja purkamisen viemään aikaan.

Kun pakkausohjelma suoritetaan, ilmoitaan saaavutettu pakkaussuhde (pakatun tiedoston koko prosentteina verrattuna alkuperäisen tiedoston kokoon). Lisäksi ilmoitaan pakkaamiseen tai purkamiseen kulunut aika.

Testeihin on sisällytetty myös testejä, joilla mitataan pakkamiseen ja purkamiseen kuluvaa aikaa. Näin eri pakkausalgoritmeja voidaan verrata keskenään.

### Testisyötteet

Testattavat syötteet ovat generoituja tekstitiedostoja, joihin sanat on arvottu sattumanvaraisesti teoksesta [The Project Gutenberg EBook of The King James Bible](https://www.gutenberg.org/cache/epub/10/pg10.txt). Tekstitiedostot ovat suuruudeltaan 10 000, 100 000, 1000 000, 10 000 000 ja 100 000 000 tavua.

Lisäksi suoritettiin testauksia seuraavilla tiedostoilla, joita käytetään yleisesti pakaustehokkuuden arviointiin. Pakattavat tiedostot ovat lähteestä [The Canterbury Corpus](http://corpus.canterbury.ac.nz/descriptions/#cantrbry). Pakkaamisen tehokkuutta testattiin viidellä ajolla kullekkin operaatiolle. 


### Suorituskykytestien tulokset

Alla suorituskykytestien antama tulostus
        
        Huffman: 10000 bytes
        ==============================
        Compressing with Huffman.
        Input file name: 'testFileToPerformanceTest_10000.txt'
        Compression took 0 ms elapsed time.
        Compressing rate 63.51 %.
        ==============================
        Uncompressing with Huffman.
        Uncompressing took 2 ms elapsed time.

        
        Huffman: 100000 bytes
        ==============================
        Compressing with Huffman.
        Input file name: 'testFileToPerformanceTest_100000.txt'
        Compression took 1 ms elapsed time.
        Compressing rate 56.90 %.
        ==============================
        Uncompressing with Huffman.
        Uncompressing took 5 ms elapsed time.

        
        Huffman: 1000000 bytes
        ==============================
        Compressing with Huffman.
        Input file name: 'testFileToPerformanceTest_1000000.txt'
        Compression took 28 ms elapsed time.
        Compressing rate 56.25 %.
        ==============================
        Uncompressing with Huffman.
        Uncompressing took 47 ms elapsed time.

        
        Huffman: 10000000 bytes
        ==============================
        Compressing with Huffman.
        Input file name: 'testFileToPerformanceTest_10000000.txt'
        Compression took 94 ms elapsed time.
        Compressing rate 56.17 %.
        ==============================
        Uncompressing with Huffman.
        Uncompressing took 407 ms elapsed time.

        
        Huffman: 100000000 bytes
        ==============================
        Compressing with Huffman.
        Input file name: 'testFileToPerformanceTest_100000000.txt'
        Compression took 828 ms elapsed time.
        Compressing rate 56.17 %.
        ==============================
        Uncompressing with Huffman.
        Uncompressing took 3628 ms elapsed time.

        
        
        
        
        
        Lempel-Ziv-Welch: 10000 bytes
        ==============================
        Compressing with Lempel-Ziv-Welch.
        Input file name: 'testFileToPerformanceTest_10000.txt'
        Compression took 0 ms elapsed time.
        Compressing rate 75.92 %.
        ==============================
        Uncompressing with Lempel-Ziv-Welch.
        Uncompressing took 0 ms elapsed time.


        Lempel-Ziv-Welch: 100000 bytes
        ==============================
        Compressing with Lempel-Ziv-Welch.
        Input file name: 'testFileToPerformanceTest_100000.txt'
        Compression took 11 ms elapsed time.
        Compressing rate 51.59 %.
        ==============================
        Uncompressing with Lempel-Ziv-Welch.
        Uncompressing took 9 ms elapsed time.


        Lempel-Ziv-Welch: 1000000 bytes
        ==============================
        Compressing with Lempel-Ziv-Welch.
        Input file name: 'testFileToPerformanceTest_1000000.txt'
        Compression took 126 ms elapsed time.
        Compressing rate 39.60 %.
        ==============================
        Uncompressing with Lempel-Ziv-Welch.
        Uncompressing took 43 ms elapsed time.


        Lempel-Ziv-Welch: 10000000 bytes
        ==============================
        Compressing with Lempel-Ziv-Welch.
        Input file name: 'testFileToPerformanceTest_10000000.txt'
        Compression took 1074 ms elapsed time.
        Compressing rate 37.90 %.
        ==============================
        Uncompressing with Lempel-Ziv-Welch.
        Uncompressing took 324 ms elapsed time.


        Lempel-Ziv-Welch: 100000000 bytes
        ==============================
        Compressing with Lempel-Ziv-Welch.
        Input file name: 'testFileToPerformanceTest_100000000.txt'
        Compression took 9802 ms elapsed time.
        Compressing rate 37.70 %.
        ==============================
        Uncompressing with Lempel-Ziv-Welch.
        Uncompressing took 3416 ms elapsed time.



Tulokset on ilmoitettu viiden ajon keskiarvona.

| Tiedosto       | Tyyppi | Kuvaus             | Koko (tavua) | Huffman: Compressing rate | LZW: Compressing rate | Huffman: Comp. speed | LZW: Comp. speed | Huffman: Uncomp. speed | LZW: Uncomp. speed |
| :------------- | :----- | :----------------- | :----------- | :------------------------ | :-------------------- | :------------------- | :--------------- | :--------------------- | :----------------- |
| alice29.txt    | text   | English text       | 152089       |                           |                       |                      |                  |                        |                    |
| asyoulik.txt   | play   | Shakespeare        | 125179       |                           |                       |                      |                  |                        |                    |
| cp.html        | html   | HTML source        | 24603        |                           |                       |                      |                  |                        |                    |
| fields.c       | Csrc   | C source           | 11150        |                           |                       |                      |                  |                        |                    |
| grammar.lsp    | list   | LISP source        | 3721         |                           |                       |                      |                  |                        |                    |
| kennedy.xls    | Excl   | Excel Spreadsheet  | 1029744      | 45.17 %                   | 34.14 %               | 38 ms                | 134 ms           | 37 ms                  | 44 ms              |
| lcet10.txt     | tech   | Technical writing  | 426754       |                           |                       |                      |                  |                        |                    |
| plrabn12.txt   | poem   | Poetry             | 481861       |                           |                       |                      |                  |                        |                    |
| ptt5           | fax    | CCITT test set     | 513216       |                           |                       |                      |                  |                        |                    |
| sum            | SPRC   | SPARC Executable   | 38240        |                           |                       |                      |                  |                        |                    |
| xargs.1        | man    | GNU manual page    | 4227         |                           |                       |                      |                  |                        |                    |

## Työn mahdolliset puutteet ja parannusehdotukset

Ohjelma ei tarkista, onko syötteenä annettu purettava tiedosto sisällöltään validi, muuten kuin päättelemällä tiedostopäätteestä. Toisaalta ohjelmassa on vältetty yleisimpiä tiedostopäätteitä valitsemalla käytettäviksi pakatun tiedoston tiedostopäätteiksi *.huffman* ja *.lzw*. Tahallisen virheellisen syötteen havaitsemislogiikan käyttöönotto vaatisi ohjelmaan useaan kohtaan tarkistuksia, jotka antaisivat virhetilanteen huomatessaan poikkeuksen, jonka jälkeen ohjelman suoritus lopetettaisiin.

## Lähteet

Compression Algorithms: Huffman and Lempel-Ziv-Welch (LZW). Haettu 9.4.2020: http://web.mit.edu/6.02/www/s2012/handouts/3.pdf

Dheemanth, H. (2014). LZW Data Compression. *American Journal of Engineering Research (AJER), 3*(2), 22-26. Haettu 9.4.2020: http://www.ajer.org/papers/v3\(2\)/C0322226.pdf

Huffman coding. (n.d.). Wikipedia. Haettu 9.4.2020: https://en.wikipedia.org/wiki/Huffman_coding
