package com.endava.pages;

import java.sql.*;

/**
 * Created by andpopescu on 11/24/2016.
 */
public class DatabaseConnection {

    static final String DB_URL = "jdbc:mysql://192.168.100.125:3306/drupal";
    static final String USER = "root";
    static final String PASS = "root";

    public void checkDataSet(String username,String email){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users WHERE name = '"+username+"'"+"and mail = '"+email+"'";
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                System.out.println("Record exists in db");
            }else{
                System.out.println("Record does not exists in db");
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}


