# Miner0
MY app on github
Miner0, aplikacja z przeznaczeniem dla przemysłu górniczego. Opiera swoje działanie o wzorzec projektowy MVC,
który jest dostarczany dzięki składowemu modułowi Spring Web MVC.
Strukturze aplikacji możemy wyodrębnić takie moduły jak :
a)	Commands - moduł z plikami DTO klas podstawowych
b)	Controllers - moduł zawierający Controllery poszczególnych endpointów wystawionych w sieci
c)	Converters - moduł zawierający konwertery z DTO na klase obiektu
d)	Models - moduł zawierający poszczególne modele klas 
e)	Repositories - moduł zawierający repozytoria odpowiedzialne za komunikację z baża danych, wykorzystanie CrudRepository z Spring Data
f) Views - Moduł widoków znajdujący się w folderze resources
Widok aplikacji został zaimplementowany z wykorzystaniem html, css, thymaleaf oraz paroma skryptami javascript.
Główne funkcjonalności udostępniane dzięki aplikacji to:
Tworzenie obiektów takich jak :
- Chodnik przodkowy
- Kombajn przodkowy
- Szafa manewrowa
- Bezpiecznik
- Pracownik
- Prace zaplanowane
Praca na kopalni przez swoje specyficzne warunki pracy zmaga się z problemem prowadzenia, przekazywania 
informacji odnośnie stanu poszczególnych chodników przodkowych oraz elementów znajdujących się w rejonie.
Aplikacja dzięki stworzeniu odpowiedniej struktury oraz wprowadzeniu zależności pozwala na wygodne zapisywanie
elementów znajdujących się w danym rejonie. Ponaddto odpowiedzialna jest za przestrzeganie terminów legalizacji zabezpieczeń.
