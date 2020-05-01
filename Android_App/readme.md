# SporTeam Mobile Application
### SporTeam is a mobile application that has the goal to collect participants for games and training, as well as other events.

The application is done in Android and the backend is written in python.


## Laboratory 3
#### 1. The API is private, backend is made with flask in python -main.py and for database SQLite file - sportify.db.
In order to start the server you have to run the main.py file.

#### 2. These are the API requests available from backend:
```
GET /api/v1/events/all - get all events as a list in json format
POST /api/v1/authenticate - authenticates user, input paramater body in json format for username and password 
{
	"username" : <username>,
	"password" : <password>
}
GET /api/v1/event - get event based on id
GET /api/v1/user_info - get user info 
POST /api/v1/logout - log out the user based on auth token from header
```
Backend contains 3 GET and 2 POST request. The authentication is receiving body in JSON format and not URL queries.

#### 3. Android is using Retrofit libraries that was added in gradle file: 
    implementation 'com.squareup.retrofit2:retrofit:2.8.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'

Retrofit is initialized in class RetrofitClientInstance and in ServerRequestService is the service responsible to make request.
#### 4. Two more screens were added: 
ItemInfoActivity - shows details about selected event from list
UserFragment - information bout logged user

There are 6 screens: Splash Screen, Login Screen, Event lists, Event info, User info and Settings page

#### 5. Bottom bar with tabs was added as navigation component, it shows events page and user info.
#### 6. Demo: https://drive.google.com/open?id=140mHHrsRuQpvkgunY7pjSpOtuLp_sQ_q

## Laboratory 4
#### 1. Project was refactored for MVP(Model-View-Presenter) architecture. 

Technologies used to achieve this MVP are:
1) Dagger - dependencies injection library, useful to separate project into modules and inject dependencies into activity
2) RxJava - a library that uses observable design pattern to make API asynchronous requests

#### Project architecture

Screens package - contains all views with MVP structure. As an example lets analyse Login Screen: 

LoginModel - Defines what screens to be opened and displayed;

LoginPresenter - contains the business logic to validate the username and password also formats the data for LoginView;
 
LoginView - outputs login screen and error messages;

LoginBuilder - is a Dagger class builder to combine LoginModel, LoginPresenter and LoginView classes. 

Dagger automatically generates the code to inject LoginPresenter and LoginView into LoginActivity, this is done by using annotation @Inject. All other views have the same structure as Login screen. 



