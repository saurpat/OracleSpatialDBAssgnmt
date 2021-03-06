CREATE TABLE ANNOUNCEMENT_SYSTEMS (
 as_id VARCHAR2(32) PRIMARY KEY,
 x number,
 y number,
 r number,
 as_circle SDO_GEOMETRY);
    
INSERT INTO USER_SDO_GEOM_METADATA 
  VALUES (
  'ANNOUNCEMENT_SYSTEMS',
  'as_circle',
  SDO_DIM_ARRAY(   
    SDO_DIM_ELEMENT('X', 0, 900, 0.005),
    SDO_DIM_ELEMENT('Y', 0, 600, 0.005)
     ),
  NULL   
);  

  CREATE INDEX as_circle_spatial_idx
  ON ANNOUNCEMENT_SYSTEMS(as_circle)
  INDEXTYPE IS MDSYS.SPATIAL_INDEX;
 
   CREATE TABLE STUDENTS (
 person_id VARCHAR2(32) PRIMARY KEY,
 s_point SDO_GEOMETRY);
  
INSERT INTO USER_SDO_GEOM_METADATA 
  VALUES (
  'STUDENTS',
  's_point',
  SDO_DIM_ARRAY(   
    SDO_DIM_ELEMENT('X', 0, 900, 0.005),
    SDO_DIM_ELEMENT('Y', 0, 600, 0.005)
     ),
  NULL   
); 
  
  CREATE INDEX s_point_spatial_idx
  ON STUDENTS(s_point)
  INDEXTYPE IS MDSYS.SPATIAL_INDEX;
   
 CREATE TABLE BUILDINGS (
 building_id VARCHAR2(32) PRIMARY KEY,
 building_name VARCHAR2(32),
 b_polygon SDO_GEOMETRY);
  
INSERT INTO USER_SDO_GEOM_METADATA 
  VALUES (
  'BUILDINGS',
  'b_polygon',
  SDO_DIM_ARRAY(   
    SDO_DIM_ELEMENT('X', 0, 900, 0.005),
    SDO_DIM_ELEMENT('Y', 0, 600, 0.005)
     ),
  NULL   
); 
  
  CREATE INDEX b_polygon_spatial_idx
  ON BUILDINGS(b_polygon)
  INDEXTYPE IS MDSYS.SPATIAL_INDEX;
   

 

