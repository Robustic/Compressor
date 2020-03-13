# Määrittelydokumentti

## Ohjelman tarkoitus

Kirjoitettavan ohjelman tarkoituksena on pakata syötteenä annettu tiedosto kooltaan pienempään tilaan, jotta se veisi vähemmän tilaa pitkäaikaismuistissa.

Ohjelma ei ole yhteensopiva muiden pakkausohjelmien kanssa, joten sillä pakattuja tiedostoja ei voi purkaa muilla ohjelmilla, eikä muilla ohjelmilla pakattuja tiedostoja ole mahdollista purkaa kehitettävällä ohjelmalla.

## Ohjelman syötteet

Ohjelma ajetaan komentoriviltä. Syötteenä ohjelma saa joko pakattavan tai purettavan tiedoston tiedostonimen ja mahdollisesti myös hakemistosijainnin. Syötteenä voi antaa myös käytettävän pakkausalgoritmin valinnan. Ohjelma lukee annetun tiedoston tavut, koodaa tai purkaa ne koodausalgoritmin ohjaamalla tavalla ja kirjoittaa tulostiedoston samaan hakemistoon lähtötiedoston kanssa.

## Käytettävät algoritmit

Käytettävät Huffman ja Lempel–Ziv–Welch -algoritmit valittiin, koska ne ovat:

* riittävän tehokkaita pakkausongelman ratkaisemiseksi

* sopivan monimutkaisia harjoitustyön laajuuteen nähden

* opettavaisia

* mielenkiintoisia toteuttaa

### Huffman

Huffmanin algoritmi on kuvattu useissa lähteissä. Yleiskuvaus on esitetty mm. Wikipedian artikkelissa [Huffman_coding](https://en.wikipedia.org/wiki/Huffman_coding).

### Lempel–Ziv–Welch

Lempel–Ziv–Welch:in algoritmi on kuvattu useissa lähteissä. Yleiskuvaus on esitetty mm. Wikipedian artikkelissa [Lempel–Ziv–Welch](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch).

## Käytettäviä tietorakenteita

Käytettävien tietorakenteiden valinnassa huomioitiin, että ne riittävät valittujen algoritmien toteuttamiseen riittävän tehokkaasti. Lempel–Ziv–Welch:in algoritmin osalta ei vielä tarkkaan tiedetä ennen varsinaisen toteutuksen aloitusta, riittävätkö alla kuvatut tietorakenteet. 

Tilavaativuus kaikille listoille ja linkitetyille listoille on *O(n)*. Binääripuulle tilavaativuus on *O(n \* log n)*, mutta toisaalta binääripuun osalta _n_ on suurimmillaan 256. 

Kokonaisuutena ohjelmalle aika- ja tilavaativuustavoite on *O(n)*, koska tiedoston koko vaikuttaa lineaarisesti pakkauksen ja purkamisen kestoon. Suuren tiedoston aika- ja tilavaativuuden kannalta ohjelmassa käytännössä lisätään arvoja useissa vaiheissa taulukkolistan loppuun. Aikavaativuus tälle on *O(n)*.

Järjestämistä ja binääripuun käyttämistä tarvitaan suurimmillaan vain _n_ kooltaan 256 olevien osioiden osalta, joten kokonaisuuden kannalta ne eivät kasvata kokonaisuuden aika- ja tilavaativuutta suuremmaksi.

### Taulukkolista (Array list)

Taulukkolista kapseloi muuttuvapituisen taulukon käsittelyyn tarvittavat toiminnot. Taulukkolistan toteutus on helppo eri tietotyypeille. Tarvittaville taulukkolistan käsittelyfunktioille aikavaativuustavoitteena on:

*O(1)*

* alkion haku annetusta indeksistä

* alkion lisääminen listan loppuun

*O(n)*

* kahden listan yhdistäminen peräkkäin

### Linkitettu lista (Linked list)

Linkitetyn listan avulla voidaan pitää yllä järjestettyä listaa. Pienillä n:än arvoilla linkitetyn listan tehokkuus on riittävä, mutta n:n kasvaessa tarvittaisiin tehokkaampi tietorakenne. Koska tässä tapauksessa _n_ on enimmillään 256 (yhden tavun mahdolliset vaihtoehdot), arvioidaan linkitetyn listan olevan riittävän tehokas tarkoitettuun käyttöön. Tarvittaville linkitetyn listan käsittelyfunktioille aikavaativuustavoitteena on:

*O(1)*

* alkion haku linkitetyn listan alusta tai lopusta

* alkion lisääminen linkitetyn listan alkuun tai loppuun

*O(n)*

* alkion haku mistä tahansa kohtaa linkitettyä listaa

* alkion lisääminen mihin tahansa kohtaa linkitettyä listaa

*O(n^2)*

* alkioiltaan järjestyksessä olevan linkitetyn listan muodostaminen

### Binääripuu (Binary tree)

Huffman:in pakkausalgoritmi hyödyntää binääripuurakennetta binäärikoodien muodostamiseen. Tarvittaville binääripuun käsittelyfunktioille aikavaativuustavoitteena on:

*O(log n)*

* alkion haku puusta

* alkion lisääminen puuhun

*O(n \* log n)*

* binääripuun muodostaminen

## Lähteet

[Tietorakenteet ja algoritmit -kirja](https://github.com/pllk/tirakirja/raw/master/tirakirja.pdf)

[Wikipedia: Huffman_coding](https://en.wikipedia.org/wiki/Huffman_coding)

[Wikipedia: Lempel–Ziv–Welch](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch)
