import java.sql.*;
import java.util.*;
import oracle.sdoapi.OraSpatialManager;
import oracle.sdoapi.geom.*;
import oracle.sdoapi.adapter.*;
import oracle.sdoapi.sref.*;
import oracle.sql.STRUCT;


public class Ex6
{
    Connection mainConnection = null;
    Statement mainStatement = null;
    ResultSet mainResultSet = null;

    /*****************************/
    public static void main(String args[])
    {
        System.out.println();
		Ex6 e = new Ex6();
        System.out.println();
    }

    /*****************************/
    public Ex6()
    {
        ConnectToDB();			// connect to DB

    	PublishData();			// publish some data

		SearchAllTuples();		// search for all tuples

		ShowMetaData();			// show meta data

		ShowAllTuples();		// show results of the search

    }


    /*****************************/
    public void PublishData()
    {
        try
        {

				// delete previous data from the DB
            System.out.print( "\n ** Deleting previous tuples ..." );
            mainStatement.executeUpdate( "delete from spatial_test" );
            System.out.println( ", Deleted. **" );


				// publish new data
            System.out.print( " ** Inserting Data ..." );
            mainStatement.executeUpdate( "insert into spatial_test values ( 1, MDSYS.SDO_GEOMETRY" +
            							 "( 2001, NULL, MDSYS.SDO_POINT_TYPE(12, 14, NULL), NULL, NULL) ) " );
            mainStatement.executeUpdate( "insert into spatial_test values ( 2, MDSYS.SDO_GEOMETRY" +
            							 "( 2001, NULL, MDSYS.SDO_POINT_TYPE(3, 5, NULL), NULL, NULL) ) " );
            mainStatement.executeUpdate( "insert into spatial_test values ( 3, MDSYS.SDO_GEOMETRY" +
            							 "( 2001, NULL, MDSYS.SDO_POINT_TYPE(-1, -5, NULL), NULL, NULL) ) " );
            System.out.println( ", Done.\n **" );

        }
        catch( Exception e )
        { System.out.println( " Error 2: " + e.toString() ); }
    }

    /*****************************/
    public void SearchAllTuples()
    {
		try
    	{
                                // searches for all tuples
	        System.out.println("\n ** Selecting all tuples in the table **" );
	        mainResultSet = mainStatement.executeQuery( "select * from spatial_test " );
		}
    	catch( Exception e )
	    { System.out.println( " Error : " + e.toString() ); }
    }

    /*****************************/
    public void ShowMetaData()
    {
    	try
		{
                                // shows meta data
    	    System.out.println("\n ** Obtaining Meta Data ** " );
	        ResultSetMetaData meta = mainResultSet.getMetaData();
	        for( int col=1; col<=meta.getColumnCount(); col++ )
		        System.out.println( "Column: " + meta.getColumnName(col) + "\t, Type: " + meta.getColumnTypeName(col) );
    	}
		catch( Exception e )
    	{ System.out.println( " Error : " + e.toString() ); }
    }

    /*****************************/
    public void ShowAllTuples()
    {
		STRUCT point;		//Structure to handle Geometry Objects
		Geometry geom;     	//Structure to handle Geometry Objects

		try
		{
                                // shows result of the query
	        ResultSetMetaData meta = mainResultSet.getMetaData();

    	    System.out.println("\n ** Showing all Tuples ** " );
	        int tupleCount=1;

	  		GeometryAdapter sdoAdapter = OraSpatialManager.getGeometryAdapter("SDO", "9",STRUCT.class, null, null, mainConnection);

 	        while( mainResultSet.next() )
    	    {
	    	    System.out.print( "Tuple " + tupleCount++ + " : " );

	    	    int Point_ID = mainResultSet.getInt( 1 );
	    	    System.out.print( "\"" + Point_ID + "\"," );

	    	    point = (STRUCT)mainResultSet.getObject(2);
				geom = sdoAdapter.importGeometry( point );
      			if ( (geom instanceof oracle.sdoapi.geom.Point) )
      			{
					oracle.sdoapi.geom.Point point0 = (oracle.sdoapi.geom.Point) geom;
					double X = point0.getX();
					double Y = point0.getY();
					System.out.print( "\"(X = " + X + ", Y = " + Y + ")\"" );
				}

		        System.out.println();
       	    }
        }
		catch( Exception e )
	    { System.out.println(" Error : " + e.toString() ); }

		System.out.println();
    }


    /*****************************/
    public void ConnectToDB()
    {
		try
		{
			// loading Oracle Driver
    		System.out.print("Looking for Oracle's jdbc-odbc driver ... ");
	    	DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
	    	System.out.println(", Loaded.");

			String URL = "jdbc:oracle:thin:@gilaan.usc.edu:1521:csci585";
	    	String userName = "temp585";
	    	String password = "temp585";

	    	System.out.print("Connecting to DB...");
	    	mainConnection = DriverManager.getConnection(URL, userName, password);
	    	System.out.println(", Connected!");

    		mainStatement = mainConnection.createStatement();

   		}
   		catch (Exception e)
   		{
     		System.out.println( "Error while connecting to DB: "+ e.toString() );
     		e.printStackTrace();
     		System.exit(-1);
   		}
    }

}


