# Capstone Project - Korean Trip

### Korean Trip app is a Korea travel app. You can use the Korea Travel API to display nearby Korean tourist destinations and click on a tourist destination to check detailed information.

### location Permission is requested.

You can save the places you want by using the database.
You can search and check the travel places stored in the database even without Internet access.

[login page]<br>
<img src="https://user-images.githubusercontent.com/11826959/223388571-e34d1047-b938-4775-a9a8-2c52c9f220e6.jpg" width="200">

[mainFragment page]<br>
<img src="https://user-images.githubusercontent.com/11826959/223388493-3e7924b7-9979-4855-a1c6-0aca252ae626.jpg" width="200">

[settingFragment page]<br>
<img src="https://user-images.githubusercontent.com/11826959/223388528-3f056a7a-73f3-44d3-9932-892952d75acd.jpg" width="200">

[mylistFragment page]<br>
<img src="https://user-images.githubusercontent.com/11826959/223388532-614b3289-beb8-4275-86eb-2c7e6e32e591.jpg" width="200">

[mainFragment scrolling]<br>
<img src="https://user-images.githubusercontent.com/11826959/223388544-681abd8f-4b48-46b3-8f39-0001397817bf.jpg" width="200">

[detailFragment page]<br>
<img src="https://user-images.githubusercontent.com/11826959/223388561-98e9e5f9-0b40-4ea5-a667-b129df63f0c2.jpg" width="200">




| rubric | file |
| --- | --- |
| Application includes at least three screens with distinct features using either the Android Navigation Controller or Explicit Intents | app/src/main/res/navigation/nav_main.xml |
| The Navigation Controller is used for Fragment-based navigation and intents are utilized for Activity-based navigation. | app/src/main/java/com/rollcake/tripPhoto/base/BaseFragment.kt |
| An application bundle is built to store data passed between Fragments and Activities. | app/src/main/res/navigation/nav_main.xml |
| Application UI effectively utilizes ConstraintLayout to arrange UI elements effectively and efficiently across application features, avoiding nesting layouts and maintaining a flat UI structure where possible. | app/src/main/res/layout |
| Data collections are displayed effectively, taking advantage of visual hierarchy and arrangement to display data in an easily consumable format. | app/src/main/res/layout |
| Resources are stored appropriately using the internal res directory to store data in appropriate locations including string * values, drawables, colors, dimensions, and more. | app/src/main/res/values app/src/main/res/values-night |
| Every element within ConstraintLayout should include the id field and at least 1 vertical constraint. | app/src/main/res/layout |
| Data collections should be loaded into the application using ViewHolder pattern and appropriate View, such as RecyclerView. | app/src/main/java/com/rollcake/tripPhoto/ui/mylist/MyListFragment.kt |
| Application contains at least 1 feature utilizing MotionLayout to adapt UI elements to a given function. This could include animating control elements onto and off screen, displaying and hiding a form, or animation of complex UI transitions. | app/src/main/res/layout/fragment_trips.xml |
| MotionLayoutbehaviors are defined in a MotionScene using one or more Transition nodes and ConstraintSet blocks. | app/src/main/res/layout/fragment_trips.xml |
| Constraints are defined within the scenes and house all layout params for the animation. | app/src/main/res/xml/scene_10_header.xml |
| The Application connects to at least 1 external data source using Retrofit or other appropriate library/component and retrieves data for use within the application. | app/src/main/java/com/rollcake/tripPhoto/network/TripsApiService.kt |
| Data retrieved from the remote source is held in local models with appropriate data types that are readily handled and manipulated within the application source. Helper libraries such as Moshi may be used to assist with this requirement. | app/src/main/java/com/rollcake/tripPhoto/network/TripsApiService.kt |
| The application performs work and handles network requests on the appropriate threads to avoid stalling the UI. | app/src/main/java/com/rollcake/tripPhoto/network/TripsApiService.kt |
| The Application loads remote resources asynchronously using an appropriate library such as Glide or other library/component when needed. | app/src/main/java/com/rollcake/tripPhoto/ui/detail/DetailFragment.kt |
| Images display placeholder images while being loaded and handle failed network requests gracefully. | app/src/main/java/com/rollcake/tripPhoto/ui/detail/DetailFragment.kt |
| All requests are performed asynchronously and handled on the appropriate threads. | app/src/main/java/com/rollcake/tripPhoto/ui/detail/DetailFragment.kt |
| The application utilizes storage mechanisms that best fit the data stored to store data locally on the device. Example: SharedPreferences for user settings or an internal database for data persistence for application data. Libraries such as https://developer.android.com/topic/libraries/architecture/room may be utilized to achieve this functionality. | app/src/main/java/com/rollcake/tripPhoto/data/local |
| Data stored is accessible across user sessions. | app/src/main/java/com/rollcake/tripPhoto/data/local |
| Data storage operations are performed on the appropriate threads as to not stall the UI thread. | app/src/main/java/com/rollcake/tripPhoto/data/local |
| Data is structured with appropriate data types and scope as required by application functionality. | app/src/main/java/com/rollcake/tripPhoto/data/local app/src/main/java/com/rollcake/tripPhoto/network/TripProperty.kt |
| Application separates responsibilities amongst classes and structures using the MVVM Pattern:Fragments/Activities control the ViewsModels houses the data structures,ViewModel controls business logic.Application adheres to architecture best practices, such as the observer pattern, to prevent leaking components, such as Activity Contexts, and efficiently utilize system resources. | app/src/main/java/com/rollcake/tripPhoto/ui |
| Beyond MVVM, the application handles system events, such as orientation changes, application switching, notifications, and similar events gracefully including, but not limited to: | app/src/main/java/com/rollcake/tripPhoto/ui |
| Storing and restoring state and informationProperly handling lifecycle events in regards to behavior and functionalityImplement bundles to restore and save dataHandling interaction to and from the application via IntentsHandling Android Permissions | app/src/main/java/com/rollcake/tripPhoto/ui |
| Application utilizes at least 1 hardware component to provide meaningful functionality to the application as a whole. Suggestion options include: | app/src/main/java/com/rollcake/tripPhoto/ui/main/MainFragment.kt |
