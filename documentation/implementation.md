# Toteutusdokumentti

## Ohjelman koodin rakenne

Ohjelman koodi on jaettu paketteihin siten, että kukin paketti hoitaa sille kuuluvan tason tehtäviä.

Paketti `project` käytetään ohjelman käynnistämiseen, eikä se sisällä mitään muuta logiikkaa. Se sisältää main-funktion, josta ohjelman suoritus alkaa.

Lähimpänä käyttäjää on `ui` paketti, joka sisältää tekstikäyttöliittymän toteuttamiseen käytetyn koodin.

Paketti `algorithms` sisältää pakkausalgoritmien korkean tason toteutuksen.

Paketti `datastructures` sisältää toteutetut tietorakenteet ja niiden käsittelyyn tehdyn yksityiskohtaisen koodin.

Paketti `fileio` sisältää tiedostojen lukemiseen ja kirjoittamiseen käytetyt toiminnot.

### Huffman

Huffmanin algoritmin toimninta on kuvattu useissa lähteissä, kuten lähteessä [Huffman](https://en.wikipedia.org/wiki/Huffman_coding), joten itse algoritmia ei kuvata tässä dokumentaatiossa. Sen sijaan kerrotaan hieman algoritmin toteutuksesta tässä harjoitustyössä.

Huffmanin koodissa lasketaan aluksi kunkin merkin lukumäärä syötteessä ja järjestämällä merkit tämän perusteella oikeaan järjestykseen. Tämä toteutettiin luokassa *ByteDataLinkedList* lisäämällä kukin alkio oikealle paikaleen linkitettyyn listaan aina alkiota lisättäessä.

Kunkin merkin koodi saatiin sijoittamalla alkiot puurakenteeseen *ByteDataBinaryTree* Huffmanin algoritmin edellyttämällä tavalla.

Tämän jälkeen koodaus onnistui helposti koodaamalla syötteen kukin merkki edellä määritellyllä koodilla.

Merkkien koodit talletettiin koodatun tiedoston alkuun, joita käyttämällä purkaminen onnistui. Purkamisessa hyödynnettiin myöskin *ByteDataBinaryTree* tietorakennetta, jolla saatiin purkamiseen tehokkuutta.

### Lempel-Ziv-Welch

Lempel-Ziv-Welchin algoritmin toimninta on kuvattu useissa lähteissä, kuten lähteissä [Lempel-Ziv-Welch](http://web.mit.edu/6.02/www/s2012/handouts/3.pdf) ja [Lempel-Ziv-Welch](http://www.ajer.org/papers/v3\(2\)/C0322226.pdf), joten itse algoritmia ei kuvata tässä dokumentaatiossa. Sen sijaan kerrotaan hieman algoritmin toteutuksesta tässä harjoitustyössä.

Tässä harjoitustyössä Lempel-Ziv-Welchin algoritmin koodauksen tarvitsema sanakirja on toteutettu puurakenteena, jonka alkiot ovat *Letter* luokan olioita. Sanakirjan juuressa on 256 *Letter* oliota. Kun sanakirjaa täydennetään, lisätään uusi sana aina sen sanan lapseksi, mistä uusi sana polveutuu. Esim. sana *aut* olisi sanan *au* lapsi.

Lempel-Ziv-Welchin purkualgoritmille sanakirja on toteutettu *ByteList* taulukkona, jonka alkioihin viittaaminen on nopeaa.

## Saavutetut aika- ja tilavaativuudet

Ohjelman tietorakenteissa syötteen koko *n* vaikuttaa ainoastaan *ByteList*:ien suuruuteen. *ByteList*:it ovat pohjimmiltaan taulukkoja, joiden alkioihin voidaan viitata suoraan indeksillä. Ohjelman suorituksen aikana *ByteList*:ejä luetaan muutaman rajatun määrän kertoja läpi alusta loppuun. Syötteen koko *n* ei vaikuta siihen, kuinka monta kertaa kukin alkio *ByteList* taulukosta luetaan tai kirjoitetaan, vaan lukemisia on aina vakio määrä. Alkioita *ByteList*:eistä ei myöskään sijoiteta muihin tietorakenteisiin. *ByteList*ien käsittelyn aikavaativuus on siten O(n).

Kaikki muut tietorakenteet on kooltaan rajattuja. Tällöin niiden käsittelyn aikavaativuus on O(1) yksittäiselle tapahtumalle ja O(n), kun niitä käsitellään *n* kertaa.

Kokonaisaikavaativuus ohjelman algoritmien suoriukselle on siten O(n).

## Suorituskyky- ja O-analyysivertailu

Kunkin pakkausalgoritmin toteutuksessa saavutettua tehokkuutta mitataan pakkauksen tilasäästöllä verrattuna alkuperäisen tiedoston kokoon sekä pakkauksen ja purkamisen viemään aikaan.

Kun pakkausohjelma suoritetaan, ilmoitaan saavutettu pakkaussuhde (pakatun tiedoston koko prosentteina verrattuna alkuperäisen tiedoston kokoon). Lisäksi ilmoitetaan pakkaamiseen tai purkamiseen kulunut aika.

JUnit testeihin on sisällytetty myös testejä, joilla mitataan pakkaamiseen ja purkamiseen kuluvaa aikaa. Näin eri pakkausalgoritmeja voidaan verrata keskenään helposti.

### Suorituskykytestien tulokset

Testiaineistot on esitetty [testausdokumentissa](https://github.com/Robustic/Compressor/blob/master/documentation/testing.md).

#### Skaalautuvuustesti

Alla suorituskykytestien antama tulostus. Kuten nähdään, kasvaa ohjelman algoritmien tiivistykseen käyttämä suoritusaika lineaarisesti syötteen koon kasvaessa. Ilmoitetut ajat eivät sisällä tiedostojen lukemista tai kirjoittamista, joten ne kuvastavat pelkästään syötteen käsittelyyn kuluvaa aikaa.

Huffmanin algoritmille:
        
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

        
        Huffman: 40000000 bytes
        ==============================
        Compressing with Huffman.
        Input file name: 'testFileToPerformanceTest_40000000.txt'
        Compression took 621 ms elapsed time.
        Compressing rate 56.18 %.
        ==============================
        Uncompressing with Huffman.
        Uncompressing took 2167 ms elapsed time.

        
        Huffman: 100000000 bytes
        ==============================
        Compressing with Huffman.
        Input file name: 'testFileToPerformanceTest_100000000.txt'
        Compression took 828 ms elapsed time.
        Compressing rate 56.17 %.
        ==============================
        Uncompressing with Huffman.
        Uncompressing took 3628 ms elapsed time.

Lempel-Ziv-Welch:in algoritmille: 
        
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
        
        
        Lempel-Ziv-Welch: 40000000 bytes
        ==============================
        Compressing with Lempel-Ziv-Welch.
        Input file name: 'testFileToPerformanceTest_40000000.txt'
        Compression took 4585 ms elapsed time.
        Compressing rate 37.73 %.
        ==============================
        Uncompressing with Lempel-Ziv-Welch.
        Uncompressing took 887 ms elapsed time.

        
        Lempel-Ziv-Welch: 100000000 bytes
        ==============================
        Compressing with Lempel-Ziv-Welch.
        Input file name: 'testFileToPerformanceTest_100000000.txt'
        Compression took 9802 ms elapsed time.
        Compressing rate 37.70 %.
        ==============================
        Uncompressing with Lempel-Ziv-Welch.
        Uncompressing took 3416 ms elapsed time.

Tulokset on esitetty graafisessa muodossa alla olevassa kuvaajassa. Kuvaajan muoto oli aina likimain vastaava toistamalla testiajot muutaman kerran.

<img src="https://github.com/Robustic/Compressor/blob/master/documentation/running_time.png" width="740">

**Kuva 1.** *Suoritusaika eri kokoisilla samasta lähdeaineistoista generoidulla tiedostolla.*

Kuten kuvaajasta nähdään, ovat suoritusajat lineaarisia suhteessa syötteen kokoon. Syötteen koon kymmenkertaistuessa suoritusaika likimain kymmnekertaistuu. Tämä on nähtävissä Hoffmanin ja Lempel-Ziv-Welchin algoritmeilla sekä pakkaamisessa että purkamisessa. Havainto tukee arviota, että harjoitustyössä käytetyillä pakkausalgoritmeilla päästään aikavaativuuteen O(n).

#### Testit erilaisilla testiaineistoilla

Yleisesti käytettyyn testiaineistoon [The Canterbury Corpus](http://corpus.canterbury.ac.nz/descriptions/#cantrbry) tehtyjen testiajojen tulokset on esitetty taulukossa 1. Kuten nähdään, päästään Lempel-Ziv-Welchillä selvästi tiiviimpään pakkaukseen. Teksti- ja numeromuotoisella syötteellä tiedostojen koko pakattuna on usein alle 50 % alkuperäisestä. Tulokset on ilmoitettu taulukossa viiden ajon keskiarvona.

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

Esimerkiksi yleiseen `zip` formaattiin pakatessa tiedoston `kennedy.xls` koko on vain noin 18 % alkuperäisestä, joten tässä harjoitustyössä tehty ohjelma ei pääse aivan yleisesti käytössä olevien ohjelmien algoritmien tasolle.

## Työn mahdolliset puutteet ja parannusehdotukset

Ohjelma ei tarkista, onko syötteenä annettu purettava tiedosto sisällöltään validi, muuten kuin päättelemällä tiedostopäätteestä. Toisaalta ohjelmassa on vältetty yleisimpiä tiedostopäätteitä valitsemalla käytettäviksi pakatun tiedoston tiedostopäätteiksi *.huffman* ja *.lzw*. Tahallisen virheellisen syötteen havaitsemislogiikan käyttöönotto vaatisi ohjelmaan useaan kohtaan tarkistuksia, jotka antaisivat virhetilanteen huomatessaan poikkeuksen, jonka jälkeen ohjelman suoritus lopetettaisiin.

## Lähteet

Compression Algorithms: Huffman and Lempel-Ziv-Welch (LZW). Haettu 9.4.2020: http://web.mit.edu/6.02/www/s2012/handouts/3.pdf

Dheemanth, H. (2014). LZW Data Compression. *American Journal of Engineering Research (AJER), 3*(2), 22-26. Haettu 9.4.2020: http://www.ajer.org/papers/v3\(2\)/C0322226.pdf

Huffman coding. (n.d.). Wikipedia. Haettu 9.4.2020: https://en.wikipedia.org/wiki/Huffman_coding
