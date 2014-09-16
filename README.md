OracleSpatialDBAssgnmt
======================
Write your README as if it was for a production service, and include the following items:

Project chosen- Oracle spatial features graduate assignment similar to Food trucks project.
Technical Track- full stack (Java Swing, Oracle Spatial features)
Reasoning behind your technical choices- Interested in working on complete end-to-end application

Files:
1-surronding_food_trucks.png: Image representing output from the project
2-Populate.java: To parse input files and insert into DB
3-Hw2.java: File containing Swing GUI and logic
4-students.xy, buildings.xy, announcement_systems.xy: input files containing locations

Note: 

This assignment will require a database connection with Oracle Spatial features, hence I have included a screenshot of output which is similar to surronding food trucks from a location.


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



commands:

java -classpath classes111.jar;sdoapi.zip; Populate buildings.xy students.xy announcementSystems.xy

java -classpath classes111.jar;sdoapi.zip; HW2
 
