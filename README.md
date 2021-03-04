# SongSearchApp
SongSearchApp is a simple Android application that allows users to search and retrieve track listings from the Last.fm platform. The user is then able to click on a listing to find out more detailed information about the track and other tracks tagged as similar. Additionally, the user is able to navigate to the Last.fm track's site where further track information is available and can be sampled.

![Alt text](app/docs/images/search_screenshot.png?raw=true "Search Screen Screenshot") ![Alt text](app/docs/images/details_screenshot.png?raw=true "Details Screen Screenshot")

# Software Development Approach
This software was developed using a Kanban approach

# Architecture Design
The Project follows a MVVM with Repository pattern architecture. This architecture was chosen for:
- Separation of Concerns that provides a way to testing the architecture components in isolation and allows for the View classes to be updated without modifying the ViewModel classes.
- Resilience to configuration changes allows the ViewModel classes to store UI data that would otherwise be lost on screen rotation or activity lifecycle changes.
- Communication between fragments using a ViewModel class removes the need for fragments to communicate via an Activity using callbacks.

The View classes use data binding to communicate updates to their respective ViewModel classes. The ViewModel classes communicate with a Repository class using LiveData. This is then passed back to the View classes observing this LiveData. The Repository class communicates with a RESTful API using Retrofit and caches the response to a local Room database.

![Alt text](app/docs/images/mvvm_architecture.png?raw=true "MVVM Architecture")

# Libraries Used
- Hilt to provide constructor dependency injection to classes in the application
- Retrofit to provide access to the backend API endpoints
- AndroidX to provide Lifecycle and LiveData functionality to the app
- Room to store the Song responses from Retrofit.
- Data binding to bind the inflated layout files to instances running in the application code.
- Custom tabs to provide access to the track's webpage

# Further Improvements
- Refactor project to leverage the Paging library.
- Abstract the Similar Searches logic out of the Details Fragment

