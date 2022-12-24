# Bored

Application that helps in choosing activities.
With it, you can start a suggested activity, filter by type of activity and view what has already been done and stop an activity.

Project structure

![bored](https://user-images.githubusercontent.com/2738131/209447608-e1814eee-48cd-470e-b9ec-b44ec92b8453.png)

MVVM Architecture - Chosen because it is one of the most used architectures in the market today and also because it removes the strong coupling between each component. More importantly, in this architecture the children have no direct reference to the parent, they are only referenced by observables.

Hilt - Provides a standard way to do in-app DI, providing containers for each Android component in the project and managing the container lifecycle automatically. For use the Dagger was also used.


Retrofit - In addition to allowing a simple implementation and being one of the most used frameworks on the market, with OkHttp it is easy, for example, to intercept the request and change it the way you need it.

Compose - Was used because it has a great reduction of code, the Compose library is not coupled to the operating system, as with current components, it is compatible with legacy components (xml) and compose was designed in a way that we can build our interfaces with a system of small, reusable, self-contained code blocks.

Room - To persist the data, the annotation system facilitates the implementation and the reading of the code.


Technical debts:
- unit tests
- Logs Exception Handling
- Improved interfaces
- Generic ResourceStace

The project build can be done directly in Android Studio after the project is cloned.
below the apk for installation

[https://drive.google.com/file/d/1QPIuiFpa1Sw6WyNdpc2odjh9dnl4gba4/view?usp=share_link](https://drive.google.com/file/d/1O5kM6T1kTtpSsj73p0-DZS5y6MJHMkmh/view?usp=sharing)
