# Testausdokumentti

## Testauksen kohteet

Ohjelmalle tehdyt testit jakautuivat kahteen osaan. JUnit testeillä haluttiin varmistaa ohjelman vakaa ja oikea toiminta erilaisilla syötteillä. Suorituskykytesteillä varmistettiin ohjelman suoriutuminen kokonaisuutena tehokkaasti järkevässä ajassa myös suurilla syötteillä.

## Testisyötteet

### JUnit testien testisyötteet

Yksikkötestauksessa on testattu koko ohjelman toiminta, eli kaikki ohjelma varten kirjoitetut luokat metodeineen. 

Tietorakenteiden käsittelyyn ja tiedostosta lukemiseen ja kirjoittamisen toteuttaville luokille on tehty kattavat ja perusasioita testaavat testit pienillä ja helposti hallittavilla syötteillä. Näin on varmistettu, että muiden, korkeammalla ohjelman hierarkiassa olevien luokkien usein käyttämät luokat toimivat oikein.

Algoritmien testaamisessa on testattu kokonaisten tiedostojen käsittelyä. Tässä on hyödynnetty sitä, että pakatun tiedoston täytyy purkamisen jälkeen olla täysin samanlainen alkuperäisen tiedoston kanssa. Näin useammalla tiedostolla testaten voidaan varmistua, että ohjelma sekä pakkaa että purkaa oikein.

### Suorituskykytestien testisyötteet

#### Skaalautuvuustesti

Testattavat syötteet ovat generoituja tekstitiedostoja, joihin sanat on arvottu sattumanvaraisesti teoksesta [The Project Gutenberg EBook of The King James Bible](https://www.gutenberg.org/cache/epub/10/pg10.txt). Tekstitiedostot ovat suuruudeltaan 10 000, 100 000, 1000 000, 10 000 000, 40 000 000 ja 100 000 000 tavua. Generoiduilla tiedostoilla tehtävät testit on toteutettu JUnit testeinä. Näin saatiin ohjelman kehityksen aikana varmistettua, että ohjelman parannukset tekevät ohjelmasta tehokkaamman, eli nopeamman suorittaa.

#### Testit erilaisilla testiaineistoilla

Lisäksi suoritettiin testauksia tiedostoilla, joita käytetään yleisesti pakaustehokkuuden arviointiin. Pakattavat tiedostot olivat lähteestä [The Canterbury Corpus](http://corpus.canterbury.ac.nz/descriptions/#cantrbry). Pakkaamisen tehokkuutta testattiin viidellä ajolla kullekkin operaatiolle, joista otettiin keskiarvo, joka on ilmoitettu tuloksissa. Nämä testit suoritettiin valmiille ohjelman jar-paketille komentoriviltä.

## Testien toistaminen

### JUnit testien toistaminen

Yksikkötestit on toteutettu JUnit testeillä. Testit voidaan suorittaa automaattisesti, joten niiden suorittaminen on sujuvaa. Testien ajaminen ei myöskään vie paljoa aikaa, joten ne pystytään ajamaan aina kun ohjelmakoodia on muutettu. Näin voidaan varmistaa, että ohjelma pysyy suortuskelpoisena eikä ohjelmaa kehitettäessä hajoteta jo toimivaa koodia, vaan ohjelmistokehitys veisi kehitystyötä koko ajan eteenpäin.

JUnit-testeistä saatiin hyvin kattavat. Tätä edesauttoi se, että ohjelma on komentorivipohjainen ja käytetyt algoritmit ovat lopulta suhteellisen yksinkertaisia. Testikattavuusraportti on esitetty alla.

<img src="https://github.com/Robustic/Compressor/blob/master/documentation/jacoco_test_coverage.png" width="969">

**Kuva 1.** *Jacoco testikattavuusraportti.*

### Suorituskykytestien toistaminen

Suorituskykytestit voidaan toistaa ohjelman valmiin jar-paketin avulla kullekin testattavalle tiedostolle erikseen suoraan komentoriviltä.
