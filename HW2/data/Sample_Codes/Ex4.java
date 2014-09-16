import java.sql.*;
import java.util.*;

public class Ex4
{
    Connection mainConnection = null;
    Statement mainStatement = null;
    PreparedStatement SecondStatement = null;
    ResultSet mainResultSet = null;

    /*****************************/
    public static void main(String args[])
    {
        System.out.println();
    	Ex4 e = new Ex4();
        System.out.println();
    }

    /*****************************/
    public Ex4()
    {
        ConnectToDB();			// connect to DB

    	PublishData();			// publish some data

    	SearchAllTuples();		// search for all tuples

		ShowAllTuples();		// search for all tuples

        InsertPrepared();		// insert data using prepared statement

    	SearchAllTuples();		// search again

		ShowMetaData();			// show meta data again

    	ShowAllTuples();		// show results of the search again
    }

    /*****************************/
    public void InsertPrepared()
    {
    	try
		{
			// use prepared statement to insert data

	        System.out.println("\n ** Using prepared statement, faster than regular statement  ** " );
    	    SecondStatement.setString( 1, "Name 1" );
	        SecondStatement.setString( 2, "00000000" );
	        SecondStatement.setString( 3, "250000" );
	        SecondStatement.executeUpdate();
	        System.out.println("Prepared: new tuple inserted " );
    		SecondStatement.setString( 1, "Name 2" );
	        SecondStatement.setString( 2, "111111" );
	        SecondStatement.setString( 3, "887700" );
	        SecondStatement.executeUpdate();
	        System.out.println("Prepared: new tuple inserted \n\n " );
		}
		catch( Exception e )
	    { System.out.println( " Error 1: " + e.toString() ); }
    }

    /*****************************/
    public void PublishData()
    {
        try
        {

				// delete previous data from the DB
            System.out.print( "\n ** Deleting previous tuples ..." );
            mainStatement.executeUpdate( "delete from info" );
            System.out.println( ", Deleted. **" );


				// publish new data
            System.out.print( " ** Inserting Data ..." );
            mainStatement.executeUpdate( "insert into info values ('Clinton, Bill', '987-654-3210', 400000)" );
            mainStatement.executeUpdate( "insert into info values ('Doll, Bob', '123-456-7890', 100000)" );
            mainStatement.executeUpdate( "insert into info values ('Bush, George', '654-321-0987', 450000)" );
            mainStatement.executeUpdate( "insert into info values ('Gore, Al', '321-098-7654', 200000)" );
            mainStatement.executeUpdate( "insert into info values ('Bing, Chandler', '432-105-6789', 750000)" );
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
	    	System.out.println(" ** Selecting all tuples in the table **" );
    	    mainResultSet = mainStatement.executeQuery( "select * from info	 " );
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
	    	SecondStatement = mainConnection.prepareStatement( "insert into info values( ?, ?, ?)" );

   		}
   		catch (Exception e)
   		{
     		System.out.println( "Error while connecting to DB: "+ e.toString() );
     		e.printStackTrace();
     		System.exit(-1);
   		}
     }


}


