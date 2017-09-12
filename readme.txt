

APK Link : https://goo.gl/msi7dg
Later this path can be modified to build devops where builds will be auto uploaded using continuous integration.


Code Building Guidelines :  
This project use gradle based structure and can be imported directly in Android Studio. 


Architecture Details:

**MVP : This project uses MVP based architecture (Mindorks open Source base)  based on current project requirement which can be
further extended to MVP-Clean architecture which is generally used for large project with several functionality and multiple data providers.
**App uses modern enhanced Gradle based Structure to manage modules and keeping configurations and library dependencies out of main file for 
re-usability of version.

**Project Package Structure : App uses modern package structures based on features. 

**Singleton pattern is used for DataManager access.

User Interface : 

**ConstraintLayout and RelativeLayout is used for better performance.
**Vector Drawables are used for better performance and multi device support. 
**Material Design : Cardview is used for better look and feel.

Algorithm Used : 
Major consideration : User has to pay for every station including source station. 
Refer Statement in Problem statement -  "INR 2.00 for every station other than interchange station."
For Fare calculation logic , I am using enhanced "Dijkstra's Algorithm" using adjacencyncy matrix. 
Algorithm will stop once destination is reached. I compared this algorithm with adjacency list implementation 
using heap ( refer FareCalculatorWithList.java for adjacency list implementation)
and found it has smaller running time for provided input.

Code Description : 

FareCalculator.java : Responsible for fare calculation logic 
base package : It contains basic framework for MVP used from open sourced Mindorks framework.
ui package : Refer ui package for All View (Activity and Fragment)
data package : For data management and business logic.

Testing : 
 Android v 5.0







