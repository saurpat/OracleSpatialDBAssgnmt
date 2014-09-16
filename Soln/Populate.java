import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Author-Saurabh Patwardhan
 * USC ID-2797 7717 17
 * Some parts of this code have been taken from sample examples
 */
public class Populate {

	Connection mainConnection = null;
    Statement mainStatement = null;
    ResultSet mainResultSet = null;

    public static void main(String args[])
    {
        Populate p = new Populate(args);
    }

   
    public Populate(String[] args)
    {
        ConnectToDB();			// connect to DB
        parseAndInsertData(args);
        CloseConnection();
    }


	public void CloseConnection() {
		try{
			mainConnection.close();
		}
		catch (SQLException e){
					System.out.println("Error in close connection: " + e.toString());
		}
	}


	public void parseAndInsertData(String[] fileNames) {
		try {
			if(fileNames==null || fileNames.length!=3)
			{
				System.out.println("Input filenames as command line arguements");
				System.exit(-1);
			}
			// delete previous data from the DB
            System.out.print( "\n ** Deleting previous tuples ..." );
            mainStatement.executeUpdate( "delete from STUDENTS" );
            mainStatement.executeUpdate( "delete from ANNOUNCEMENT_SYSTEMS" );
            mainStatement.executeUpdate( "delete from BUILDINGS" );
            System.out.println( ", Deleted. **" );

            
            for(int i=0;i<fileNames.length;i++){
				BufferedReader br = new BufferedReader(new FileReader(fileNames[i]));
				try {
					String s="";
					String sql="";
					String cols[];
					// publish new data
		            System.out.println( " ** Inserting Data ... from "+fileNames[i]);
					while( (s=br.readLine())!=null){
							cols=s.split(",");
							switch(i){
								case 0://buildings.xy
									int numberOFVertices=Integer.parseInt(cols[2].trim());
									int firstX=Integer.parseInt(cols[3].trim());
									int firstY=Integer.parseInt(cols[4].trim());
									int nextX,nextY;
									sql="insert into BUILDINGS values('"+cols[0]+//building_id	
									"','"+cols[1]+//building name
									"',MDSYS.SDO_GEOMETRY(2003, NULL, NULL," +
									"MDSYS.SDO_ELEM_INFO_ARRAY(1,1003,1),"+//one polygon (exterior polygon ring)
									"MDSYS.SDO_ORDINATE_ARRAY("
										+firstX+","+firstY +","; //insert first vertex
									for(int j=5;j<((numberOFVertices-1)*2)+5;j+=2){//loop from all remaining vertices
										nextX=Integer.parseInt(cols[j].trim());
										nextY=Integer.parseInt(cols[j+1].trim());
										sql=sql+nextX+","+nextY +",";
									}
									sql=sql+firstX+","+firstY+"))) ";//to complete the polygon as per the syntax, enter the first vertex again.
									break;
								
								case 1://students.xy
								//insert into STUDENTS values('p68',MDSYS.SDO_GEOMETRY(2001, NULL, MDSYS.SDO_POINT_TYPE(524,150, NULL), NULL, NULL) ) 
									sql="insert into STUDENTS values('"+cols[0]+"',MDSYS.SDO_GEOMETRY(2001, NULL, MDSYS.SDO_POINT_TYPE("
										+Integer.parseInt(cols[1].trim())+","+ Integer.parseInt(cols[2].trim())+", NULL), NULL, NULL) ) ";
								break;
								
								case 2://announcementSystems.x
									int x=Integer.parseInt(cols[1].trim());		  
									int y=Integer.parseInt(cols[2].trim());
									int rad=Integer.parseInt(cols[3].trim());
									//3 pts on circumference
									int pt1x=x;
									int pt1y=y-rad;
									int pt2x=x+rad;
									int pt2y=y;
									int pt3x=x;
									int pt3y=y+rad;
									sql="insert into ANNOUNCEMENT_SYSTEMS values('"+cols[0]+//as_id
									"',"+x+ // x coordinate
									","+y+ // y coordinate
									","+rad+ // radius
									",MDSYS.SDO_GEOMETRY(2003, NULL, NULL," +
									"MDSYS.SDO_ELEM_INFO_ARRAY(1,1003,4),"+//circle
									"MDSYS.SDO_ORDINATE_ARRAY("
										+pt1x+","+pt1y +","
										+pt2x+","+pt2y +","
										+pt3x+","+pt3y+"))) ";
								break;
								
							}
							System.out.println(sql);
							mainStatement.executeUpdate(sql);
					}
					System.out.println( " ** Done. **" );
				} catch (IOException e) {
					System.out.println("Error in parsing/inserting students:"+e.toString());
					System.exit(-1);
				}
            }
		} catch (FileNotFoundException | SQLException e) {
			System.out.println("Error in parsing/inserting data:"+e.toString());
			System.exit(-1);
		}			
	}
	
	


	public void ConnectToDB() {
		try
		{
			// loading Oracle Driver
    		System.out.print("Looking for Oracle's jdbc-odbc driver ... ");
	    	DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
	    	System.out.println(", Loaded.");

			String URL = "jdbc:oracle:thin:@localhost:1522:orcl2";
	    	String userName = "SYS as SYSDBA";
	    	String password = "**********";//edited

	    	System.out.print("Connecting to DB...");
	    	mainConnection = DriverManager.getConnection(URL, userName, password);
	    	System.out.println(", Connected!");

    		mainStatement = mainConnection.createStatement();

   		}
   		catch (Exception e)
   		{
     		System.out.println( "Error while connecting to DB: "+ e.toString() );
     		System.exit(-1);
   		}
		
	}

}
