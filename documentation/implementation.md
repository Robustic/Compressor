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

[Lempel-Ziv-Welch](http://www.ajer.org/papers/v3(2)/C0322226.pdf)

## Saavutetut aika- ja tilavaativuudet

## Suorituskyky- ja O-analyysivertailu

Kunkin pakkausalgoritmin toteutuksessa saavutettua tehokkuutta mitataan pakkauksen tilasäästöllä verrattuna alkuperäisen tiedoston kokoon sekä pakkauksen ja purkamisen viemään aikaan.

Kun pakkausohjelma suoritetaan, ilmoitaan saaavutettu pakkaussuhde (pakatun tiedoston koko prosentteina verrattuna alkuperäisen tiedoston kokoon). Lisäksi ilmoitaan pakkaamiseen tai purkamiseen kulunut aika.

Testeihin on sisällytetty myös testejä, joilla mitataan pakkamiseen ja purkamiseen kuluvaa aikaa. Näin eri pakkausalgoritmeja voidaan verrata keskenään.

## Työn mahdolliset puutteet ja parannusehdotukset

## Lähteet


