
import java.util.*;
import java.sql.*;

public class Ex1
{

	//Connection Variables
  	Connection mainConnection = null;
  	Statement mainStatement = null;
  	ResultSet mainResultset1 = null;


  	public static void main(String[] args)
    {
    	Ex1 e = new Ex1();
    }

	/********************************************/
	/* constructore								*/
	/********************************************/
 	public Ex1()
    {
		System.out.println();
		ConnectToDB();
	}


	/********************************************/
	/* Connecting to DB							*/
	/********************************************/
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

