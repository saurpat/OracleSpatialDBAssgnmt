import java.sql.*;
import java.util.*;

public class Ex2
{
    Connection mainConnection = null;
    Statement mainStatement = null;

    ResultSet mainResultSet = null;

    /*****************************/
    public static void main(String args[])
    {
		System.out.println();
        Ex2 e = new Ex2();
		System.out.println();
    }

    /*****************************/
    public Ex2()
    {
        ConnectToDB();		// connect to DB

    	SearchAllTuples();	// Search for all tuples

		ShowMetaData();		// show meta data

    	ShowAllTuples();	// show results of the previous search
    }

    /*****************************/
    public void SearchAllTuples()
    {
    	try
		{
				// searches for all tuples
    	    System.out.println("\n ** Selecting all tuples in the table **\n ** SQL: select * from info;" );
	    	mainResultSet = mainStatement.executeQuery( "select * from info " );
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
    	try
		{
				// shows result of the query
    	    ResultSetMetaData meta = mainResultSet.getMetaData();

	    	System.out.println("\n ** Showing all Tuples ** " );
	    	int tupleCount=1;
     		while( mainResultSet.next() )
	    	{
				System.out.print( "Tuple " + tupleCount++ + " : " );
	        	for( int col=1; col<=meta.getColumnCount(); col++)
		   			System.out.print( "\""+mainResultSet.getString( col )+"\"," );
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


