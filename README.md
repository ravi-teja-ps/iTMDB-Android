iTMDB Android App  - Ravi Teja
=============================

Integrating the [TMDB 3 Open source Api](https://developers.themoviedb.org/3) into Android using

## Key Integrations
1. Clean Architecture with Multi module approach
2. MVVM Architecture
3. Kotlin first approach
4. Hilt Dependency Injection
5. Retrofit & GSON - Http Networking
6. Coroutines for Concurrency
7. Flow(StateFlow for subscribing to local and networking Stream data)
8. Mockito & Turbine with JUnit for Unit testing of various Clean Code layers.
9. Paging3 for Lazy loading paged data of PopularShows list screen.
10.RoomDB for Offline caching of Shows, and Account info. 
   


### Android App Architecture & Modules 

1. Used Clean architecture layered approach segregating modules into 
  { domain  , data , presentation Ui layers}.

2. Implemented the Repository, UseCases with interfaces

3. Used Domain Mappers for Model to DB and vice versa.

4. Used StateFlows for Communicating between various layers.

5. Unit test for ViewModels, StateFlow, Repository and usecases.
6. Used Hilt dependency injection to inject Repository Interfaces & Implementations, UseCase Interfaces and its Implementation and all major classos in the app.



###  Screens  APi's integrated

 * Integrated Popular Shows list screen using Paging3  & RoomDB caching lazy loading
 * Integrated  TMDB user authentication and login via api services
 * Integrated accounts section and Popular Shows details screen.
 

## Screenshots



 
 
