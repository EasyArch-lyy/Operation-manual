package mysql_test.sqlTemplete;

import mysql_test.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lyy
 */
public class JDBC01 {

    private static Connection con = null;

    public JDBC01() {
        Connection con = JdbcUtil.initConnection();
    }

   /**
    * 查询表中所有数据
    * @param table
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
                System.out.println(rs.getString("id"));
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
//            if(con!=null) {
//                con.close();
//            }
        }
    }

   /**
    * 验证帐号密码
    * Statement版
    * @param username
    * @param password
    * @return boolean 返回布尔结果
    */
    public static boolean selectByUernamePassword(String username,String password) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
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

   /**
    * @param username
    * @param password
    * PreparedStatement版
    */
    public static boolean selectByUP2(String username,String password) throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        try {
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
    public void selectUserByPage(int pageNumber, int pageCount) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println(con);
            stmt = con.prepareStatement("select * from runoob_transcation_test limit ?,?");
            stmt.setInt(1, (pageNumber - 1) * pageCount);
            stmt.setInt(2, pageCount);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
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

    public static void main(String[] args) throws SQLException  {
        //查询第四页，每页显示八行数据
        JDBC01 jdbc01=new JDBC01();
        jdbc01.selectUserByPage(1,1);
    }

}