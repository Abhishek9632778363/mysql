package my;
import java.sql.*;
import java.util.Scanner;
 
public class Mysql {

	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
	   static final String DB_URL = "jdbc:mysql://localhost:3306/new_schema1?autoReconnect=true&useSSL=false";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abhiabartha";
	   static ResultSet rs = null;  
	   static Connection conn = null;
	   Statement stmt = null;
	   static PreparedStatement statement = null;
	   static String sql;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
	   
	   try{
	      //STEP 1: Register JDBC driver
	      Class.forName("com.mysql.cj.jdbc.Driver");

	      //STEP 2: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	     
	      //STEP 3: Insert into table	    
	      sql = "INSERT INTO assignementdb (id, name, email, mobile,city,user_status) VALUES (?, ?, ?, ?, ?, ?)";
	      
	      statement = conn.prepareStatement(sql);
	      statement.setInt(1, 4);
	      statement.setString(2, "Soumya");
	      statement.setString(3, "soumya123@gmail.com");
	      statement.setInt(4, 1313142888);
	      statement.setString(5, "Bangalore");
	      statement.setBoolean(6, true);
	      
	      try {
	    	  int rowsInserted = statement.executeUpdate();
	    	  if (rowsInserted > 0) {
	    		    System.out.println("A new user inserted successfully!");
	    	  }
	      }catch(SQLIntegrityConstraintViolationException si) {
	    	  System.out.println("Failed to Insert user,Duplicate Found!");
	      }
	      display();
	    
	      //STEP 4: inserting the data
	      insertassignementdb();
	      
	      //STEP 5: Update
	      updateassignementdb();  
	      
	      //STEP 6: Delete
	      deleteassignementdb();
	      //STEP 7 : Clean-up environment 
	      rs.close();
	      statement.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(statement!=null)
	            statement.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Thank you!");
	}//end main
	
	
	private static void insertassignementdb() {
		sql = "INSERT INTO assignementdb (id, name, email, mobile,city,user_status) VALUES (?, ?, ?, ?, ?, ?)";
	     
	
		while(true) {
		System.out.println("do u like to add one more details type 1 else 0:");
		Scanner in0 = new Scanner(System.in);
		int Opt  = in0.nextInt();
		 
		if(Opt > 0 ) {
		 
		System.out.println("Enter Id to insert:");
		 Scanner in1 = new Scanner(System.in);
		 int id = in1.nextInt();
		 
		 System.out.println("Enter name to insert:");
		 Scanner in2 = new Scanner(System.in);
		 String Name  = in2.nextLine();
		 
		 System.out.println("Enter email to insert:");
		 Scanner in3 = new Scanner(System.in);
		 String email  = in3.nextLine();
		 
		 System.out.println("Enter mobile to insert:");
		 Scanner in4 = new Scanner(System.in);
		 int mobile = in4.nextInt();
		 
		 System.out.println("Enter city to insert:");
		 Scanner in5 = new Scanner(System.in);
		 String city  = in5.nextLine();
		 
		 System.out.println("Enter user_status to insert:");
		 Scanner in6 = new Scanner(System.in);
		 String user_status = in6.nextLine();
		 
		 try {
	    	 statement = conn.prepareStatement(sql);
	    	  
	    	  statement.setInt(1, id);
	    	  statement.setString(2, Name);
	    	  statement.setString(3, email);
	    	  statement.setInt(4, mobile);
	    	  statement.setString(5, city);
	    	  statement.setString(6, user_status);
		      int rowsUpdated = statement.executeUpdate();
		      if (rowsUpdated > 0) {
		          System.out.println("An existing user was inserted successfully!");
		      }
	      }catch(SQLException se) {
	    	  System.out.println("Failed to insert Data!");
	    	  se.printStackTrace();
	      }
	      display();}
		 else {
			 break;
		 }
		}
		}
	

	private static void deleteassignementdb() {
		
		System.out.println("do u like to delete details type 1 else 0:");
		Scanner in0 = new Scanner(System.in);
		int Opt  = in0.nextInt();
		 
		if(Opt > 0 ) {
		
		sql = "DELETE FROM assignementdb WHERE id=? OR user_status = false";
		
		System.out.println("Enter Id to delete:");
		Scanner in = new Scanner(System.in);
		int id = in.nextInt();
	      
	      try {
	    	  statement = conn.prepareStatement(sql);
	    	  statement.setInt(1, id);
	       
		      int rowsDeleted = statement.executeUpdate();
		      if (rowsDeleted >= 0) {
		          System.out.println("A user deleted successfully!");
		      }
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
	      display();	}	
	}


	private static void updateassignementdb() {
		System.out.println("do u like to make any changes type 1 else 0:");
		Scanner in0 = new Scanner(System.in);
		int Opt  = in0.nextInt();
		 
		if(Opt > 0 ) {
		 sql = "UPDATE assignementdb SET city = ? WHERE id=?";
		 System.out.println("Enter Id to update:");
		 Scanner in1 = new Scanner(System.in);
		 int id = in1.nextInt();
			
		 System.out.println("Enter CityName:");
		 Scanner in = new Scanner(System.in);
		 String cityName = in.nextLine();
		 
	     try {
	    	 statement = conn.prepareStatement(sql);
	    	  statement.setString(1, cityName);
	    	  statement.setInt(2, id);
		      int rowsUpdated = statement.executeUpdate();
		      if (rowsUpdated > 0) {
		          System.out.println("An existing user was updated successfully!");
		      }
	      }catch(SQLException se) {
	    	  System.out.println("Failed to Update Data!");
	    	  se.printStackTrace();
	      }
	      display();
		
	}}

	private static void display() {
		  sql = "SELECT * FROM assignementdb";
	      try {
			rs = statement.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      if(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         String name = rs.getString("name");
	         String email = rs.getString("email");
	         int mobile = rs.getInt("mobile");
	         String city = rs.getString("city");
	         Boolean user_status = rs.getBoolean("user_status");
	         
	         //Display values
	         System.out.print("ID: " + id + ", ");
	         System.out.print("NAME: " + name + ", ");
	         System.out.print("EMAIL: " + email + ", ");
	         System.out.print("MOBILE: " + mobile + ", ");
	         System.out.print("CITY: " + city + ", ");
	         System.out.println("USER_STATUS: " + user_status);

	      }
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}


}//end FirstExample
