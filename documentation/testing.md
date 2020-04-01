# Ohjelman testaus

## Testauksen kohteet


## Testisyötteet

Tietorakenteiden käsittelyyn ja tiedostosta lukemiseen ja kirjoittamisen toteuttaville luokille on tehty kattavat ja perusasioita testaavat testit pienillä ja helposti hallittavilla syötteillä. Näin on varmistettu, että muiden, korkeammalla ohjelman hierarkiassa olevien luokkien usein käyttämät luokat toimivat oikein.

Algoritmien testaamisessa on testattu kokonaisten tiedostojen käsittelyä. Tässä on hyödynnetty sitä, että pakatun tiedoston täytyy purkamisen jälkeen olla täysin samanlainen alkuperäisen tiedoston kanssa. Näin useammalla tiedostolla testaten voidaan varmistua, että ohjelma sekä pakkaa että purkaa oikein.

## Testien toistaminen

Testit on toteutettu JUnit testeillä. Testit suoritetaan automaattisesti aina kun halutaan, joten niiden suorittaminen on sujuvaa. Testien ajaminen ei myöskään vie paljoa aikaa, joten ne pystytään ajamaan aina kun ohjelmakoodia on muutettu. Näin voidaan varmistaa, että ohjelma pysyy suortuskelpoisena eikä ohjelmaa kehitettäessä hajoteta jo toimivaa koodia, vaan ohjelmistokehitys veisi kehitystyötä koko ajan eteenpäin.
