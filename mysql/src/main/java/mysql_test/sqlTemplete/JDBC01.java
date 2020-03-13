package mysql_test.sqlTemplete;

import mysql_test.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC01 {

    private static Connection con = null;

    public JDBC01() {
        Connection con = JdbcUtil.initConnection();

    }


    public static void main(String[] args) throws SQLException  {
        //查询第四页，每页显示八行数据
//        selectUserByPage(4,8);
    }

   /**
    * 查询表中所有数据
    * @param table
    *
    */
    public static void selectAll(String table) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //数据库的增删改查
            stmt = con.createStatement();
            //返回一个结果集
            rs =stmt.executeQuery("select * from"+table);
            while(rs.next()) {
                System.out.println(rs.getString("id")+","+rs.getString("username")+","+rs.getString("password"));
            }
        } catch (Exception e) {
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
            //System.out.println(sql);
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

    public static boolean selectByUP2(String username,String password) throws SQLException{
        Connection con=null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String url ="jdbc:mysql://localhost:3306/garysql?useUnicode=true&characterEncoding=UTF8&useSSL=false";
            con = DriverManager.getConnection(url,"root","123456");
            
            String sql = "select * from garytb where username = ? and password = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //添加参数
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            //进行查询
            rs = pstmt.executeQuery();
                
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

   /**
    * @param pageNumber 第几页
    * @param pageCount  每页显示多少个数据
    */
    public void selectUserByPage(int pageNumber,int pageCount) throws SQLException {

        //注册驱动使用驱动连接数据库
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //String url ="jdbc:mysql://localhost:3306/garysql";
            //指定编码查询数据库
            String url = "jdbc:mysql://39.97.119.183:3306/RUNOOB?useUnicode=true&characterEncoding=UTF8&useSSL=false";
            String user = "root";
            String password = "409421";
            //建立和数据库的连接
            con = DriverManager.getConnection(url, user, password);

            stmt = con.prepareStatement("select * from runoob_transcation_test limit ?,?");
            stmt.setInt(1, (pageNumber - 1) * pageCount);
            stmt.setInt(2, pageCount);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
                System.out.println(rs.getString("id") /*+*/ /*"," + rs.getString("username") + "," + rs.getString("password")*/);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}