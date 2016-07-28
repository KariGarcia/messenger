package com.karen.messenger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
   static String bd = "trainingibmmessenger";
   static String login = "root";
   static String password = "DBKari";
   static String url = "jdbc:mysql://localhost/"+bd;
   
   private static Connection connection = null;
 
   /** Constructor de DbConnection */
   public static Connection getConnection() {
      try{
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         connection = DriverManager.getConnection(url,login,password);
 
         if (connection!=null){
            System.out.println("Conexión a base de datos "+bd+" OK\n");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
      return connection;
   }

   public static void desconectar(){
      connection = null;
   }
}
