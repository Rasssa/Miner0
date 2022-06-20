# Miner0
<p>
Miner0, aplikacja z przeznaczeniem dla przemysłu górniczego. Opiera swoje działanie o wzorzec projektowy MVC,
który jest dostarczany dzięki składowemu modułowi Spring Web MVC.
Strukturze aplikacji możemy wyodrębnić takie moduły jak :
<li>	Commands - moduł z plikami DTO klas podstawowych
<li>	Controllers - moduł zawierający Controllery poszczególnych endpointów wystawionych w sieci
<li>	Converters - moduł zawierający konwertery z DTO na klase obiektu
<li>	Models - moduł zawierający poszczególne modele klas 
<li>	Repositories - moduł zawierający repozytoria odpowiedzialne za komunikację z baża danych, wykorzystanie CrudRepository z Spring Data
<li> Views - moduł widoków znajdujący się w folderze resources.
Widok aplikacji został zaimplementowany z wykorzystaniem html, css, thymaleaf oraz paroma skryptami javascript.
    <p>
Aplikacja pozwala na tworzenie wirtualnych obiektów takich elementów chodników przodkowych jak:
<li> Chodnik przodkowy
<li> Kombajn przodkowy
<li> Szafa manewrowa
<li> Bezpiecznik
<li> Pracownik
<li> Prace zaplanowane
  <p>
Praca na kopalni przez swoje specyficzne warunki pracy zmaga się z problemem prowadzenia, przekazywania 
informacji odnośnie stanu poszczególnych chodników przodkowych oraz elementów znajdujących się w rejonie.
Aplikacja dzięki stworzeniu odpowiedniej struktury oraz wprowadzeniu zależności pozwala na wygodne zapisywanie
elementów znajdujących się w danym rejonie. Ponad to odpowiedzialna jest za sprawdzanie terminów legalizacji zabezpieczeń
oraz informowanie użytkownika o ich upływie.
<p><b>
    login: user1 <p>
    password: user1Pass
