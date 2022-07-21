package MySql;

import java.sql.*;

public class MySQLAccess {
    private final static String url = "jdbc:mysql://localhost:3307/ludogamedb";
    private final static String userName = "root";
    private final static String password = "123456";
    static ResultSet resultSet;
    static Connection connection;
    static Statement statement;

    static String query = "select * from score";

    public void readDataBase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void writeToDataBase(String rn,int rs,String gn,int gs,String yn,int ys,String bn,int bs)
    {
        String id ="0";
        int nextId=0;
        try
        {
        while(resultSet.next())
        {
            id=resultSet.getString("idScore");
        }
        nextId=Integer.parseInt(id)+1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String queryInsert = "insert into score values (" + nextId + ",'" + rn + "'," + rs + ",'" +
                gn + "'," + gs + ",'" + yn + "'," + ys + ",'" +
                bn + "'," + bs + ");";
        try {
            statement.executeUpdate(queryInsert);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}