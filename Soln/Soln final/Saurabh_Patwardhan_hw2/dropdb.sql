drop index as_circle_spatial_idx;
drop table ANNOUNCEMENT_SYSTEMS;
delete from USER_SDO_GEOM_METADATA where table_name='ANNOUNCEMENT_SYSTEMS';
drop index s_point_spatial_idx;
drop table STUDENTS;
delete from USER_SDO_GEOM_METADATA where table_name='STUDENTS';
drop index b_polygon_spatial_idx;
drop table BUILDINGS;
delete from USER_SDO_GEOM_METADATA where table_name='BUILDINGS';
