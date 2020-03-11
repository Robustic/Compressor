# Määrittelydokumentti

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

## Käytettävät tietorakenteet

Käytettävien tietorakenteiden valinnassa huomioitiin, että ne riittävät valittujen algoritmien toteuttamiseen.

### Taulukkolista (ArrayList)

Taulukkolista kapseloi muuttuvapituisen taulukon käsittelyyn tarvittavat toiminnot. Taulukkolistan toteutus on helppo eri tietotyypeille. Tarvittaville taulukkolistan käsittelyfunktioille aikavaativuustavoitteena on:

*O(1)*

* alkion haku annetusta indeksistä

*O(n)*

* alkion lisääminen listaan

* kahden listan yhdistäminen

### Binääripuu (binary tree)

Huffman:in pakkausalgoritmi hyödyntää binääripuurakennetta binäärikoodien muodostamiseen. Tarvittaville binääripuun käsittelyfunktioille aikavaativuustavoitteena on:

*O(log n)*

* alkion haku listasta


