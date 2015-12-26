# EarthPorn
Basic android application which downloads beautiful image of earth from the sub reddit - EarthPorn
This app uses a free API which is provided by the Steam community. To import the project in the workspace, first import the build.grade file into the work space and rest of the project will be automatically imported.

The application is divided into following structure:

beans
interfaces
services
ui

Beans: The beans package contains the POJO classes that are used to parse the json that is sent in the response by the api.

Interfaces: Interfaces class contains the interfaces that are used to hit the api and the details of type of query parameters that are passed to the api call.

Services: The Services package contains the service class which implements the interfaces for that particular api.

UI: The UI package contains the UI/Activity that uses the service class to work on the api.


Currently the application shows the image which is currently present at the top position, which keeps on changing from time to time depending on the votes that it gets from the community.

External libraries used for simplifying the API calls are universal image downloader, Retrofit, okhttp, gson, rxjava, rxandroid.
