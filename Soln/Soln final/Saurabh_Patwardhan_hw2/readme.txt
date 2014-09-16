-------------------Readme---------------------------


Name-Saurabh Patwardhan

Student id-2797 7717 50

User name-patwards



*****************************************The list of the submitted files:*****************************************

1-HW2.java

2-Populate.java

3-createdb.sql

4-dropdb.sql

5-readme.txt




*****************************************Resolution of homework:*****************************************

Note: 

1-I have considered radius/within circumference of announcement system's while comparing it with other spatial objects, and not the distance from its center

2-I have considered center of announcement systems and students as point and not rectangles while inserting into database. 
I have shown them as rectangles/squares only for illustration purposes



Info:

1- The populate.java file will populate the respective tables

2-The items checked on the active features panel will only be shown when queries are executed.

3-The bottom text area contains list of all the queries that were executed since the application was started.

3-The whole region query will display all the students, announcement systems and buildings on the map.

4-In the point query, once you click on the map, a circle is shown, when submit button is clicked, following things happen:
	
	a)Count of each feature within the circle is calculated
	
	b)Using this count, we get the distances of all these features and then we show the nearest one in yellow color and others in green color
	
	c)Note: I have kept the selected point and the cirlce around it for visualisation purposes. 

5-In the Range query, following things happen:
	
	a)Select a polygon using left clicks and use right click to complete it.
	
	b)After clicking submit, any shapes interating with this polygon is shown
	
	c)Note: I have kept the selected polygon after submit click in different color for visualisation purposes.

6-In Surrounding students query: after clicking on map, the nearest announcemnet system is shown and after submitting, the students within it are shown

7-In Emergency query: we select a point, shown nearest announcement system, after submitting, 
	the students within the original Announcement system as color coded with
 their second nearest announcement system.






*****************************************how to compile:*****************************************

Note: please include classes111.jar and sdoapi.zip in the same folder as the source files to compile/run successfully


javac -classpath classes111.jar;sdoapi.zip HW2.java

javac -classpath classes111.jar;sdoapi.zip Populate.java



*****************************************Run:*****************************************

Note: 

1-please include classes111.jar and sdoapi.zip in the same folder as the source files to compile/run successfully

2-Also put the map.jpg, buildings.xy,students.xy, announcementSystems.xy in the same folder as source files

3-couple of queries might be slow, please give 2-3 seconds to execute.



commands:

java -classpath classes111.jar;sdoapi.zip; Populate buildings.xy students.xy announcementSystems.xy

java -classpath classes111.jar;sdoapi.zip; HW2
