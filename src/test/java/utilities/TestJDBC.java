package utilities;

import java.sql.*;

public class TestJDBC {


    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://duotech-db.cc652zs7kmja.us-east-2.rds.amazonaws.com/employees";
        String username= "duotech";
        String password ="duotech2021";

        Connection connection = DriverManager.getConnection(url, username, password);// establishes connection

        System.out.println("Connection established");

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultSet = statement.executeQuery("select * from employees limit 10"); // executes read operation

//        resultSet.next(); // moves the cussor to the next row


//Moves the cursor forward one row from its current position.
// A ResultSet cursor is initially positioned before the first row;
// the first call to the method next makes the first row the current row;
// the second call makes the second row the current row, and so on.

//        System.out.println(resultSet.getObject(3));// JDBC indexes start from 1
//
//        System.out.println(resultSet.getObject("birth_date"));
//
//        while(resultSet.next()){
//            System.out.println(resultSet.getString("first_name"));
//        }

// to get the number of rows
        resultSet.last();  // Moves the cursor to the last row in this ResultSet object
        int noOfrows = resultSet.getRow(); //Retrieves the current row number. The first row is number 1, the second number 2, and so on.

        // to get the number of columns
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

//        resultSet.previous();  // moves the cursor to previous
//        resultSet.absolute(5); // moves the cursor to indicated row
//        System.out.println(resultSet.getString(3));

        resultSet.beforeFirst();

        for (int i = 1; i <= noOfrows; i++) {
           resultSet.next();
            for (int j = 1; j <= columnCount; j++) {
                System.out.print(resultSet.getObject(j) + "\t");
            }
            System.out.println();

        }


        String updateQuery = "UPDATE employees SET first_name='Parviz', last_name='Hatamov' WHERE emp_no='10002'";

        int i1 = statement.executeUpdate(updateQuery);










    }
}
