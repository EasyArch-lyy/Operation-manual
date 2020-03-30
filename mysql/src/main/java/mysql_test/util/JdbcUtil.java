package mysql_test.util;
 
import java.sql.*;
import java.util.*;
import java.io.*;
 
/**
 * @author lyy
 */
public class JdbcUtil {

    public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://39.97.119.183:3306/RUNOOB";
    public static final String DBUSER = "root";
    public static final String DBPASS = "409421";
    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
 
    /**
     * 初始化连接
     */
    public static Connection initConnection() {
        try {
            Class.forName(DBDRIVER);
            con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

 
    /**
     * 关闭ps连接
     */
    public static void closePs(){
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 关闭rs连接
     */
    public static void closeRs(){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭所有连接
     */
    public static void closeConnction() {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
//    public static void main(String[] args) {
//        JdbcUtil tj = new JdbcUtil();
//        /**
//         String sql = "insert into company values(?,?,?)";
//         Object[] obj = {3, "baidu", "liyanhong"};
//         int i = tj.updateAndDeleteData(sql, obj);
//         System.out.println(i);
//         **/
//        String sql = "select * from company ";
//        Object[] obj = {};
//        ResultSet rs = tj.queryData(sql, obj);
//        try {
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String str = rs.getString(2);
//                String st = rs.getString(3);
//                System.out.println(id+"=="+str+"=="+st);
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            tj.closeConnction();
//        }
//
//    }
}