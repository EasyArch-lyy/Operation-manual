package mysql_test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC02 {

    public static void main(String[] args) throws SQLException  {
        //selectAll();
        System.out.println(selectByUernamePassword("Gary","123"));
    }

    public static void selectAll() throws SQLException {
        //注册驱动    使用驱动连接数据库
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //String url ="jdbc:mysql://localhost:3306/garysql";
            //指定编码查询数据库
            String url ="jdbc:mysql://localhost:3306/garysql?useUnicode=true&characterEncoding=UTF8&useSSL=false";
            String user = "root";
            String password = "409421";
            //建立和数据库的连接
            con = DriverManager.getConnection(url,user,password);
            
            //数据库的增删改查
            stmt = con.createStatement();
            //返回一个结果集
            rs =stmt.executeQuery("select * from garytb");
            
            while(rs.next()) {
                //System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
                System.out.println(rs.getString("id")+","+rs.getString("username")+","+rs.getString("password"));
            }
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(rs!=null) {
                rs.close();
            }
            if(stmt!=null) {
                stmt.close();
            }
            if(con!=null) {
                con.close();
            }
        }
    }

    public static boolean  selectByUernamePassword(String username,String password) throws SQLException {
        Connection con=null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String url ="jdbc:mysql://localhost:3306/garysql?useUnicode=true&characterEncoding=UTF8&useSSL=false";
            con = DriverManager.getConnection(url,"root","123456");
            stmt =con.createStatement();
            String sql = "select * from garytb where username = '"+username+"' and password = '"+password+"'";
            rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
                return true;
            }else {
                return false;
            }
                
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(rs!=null) {
                rs.close();
            }
            if(stmt!=null) {
                stmt.close();
            }
            if(con!=null) {
                con.close();
            }
        }
        
        return false;
    }
    
}