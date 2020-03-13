package mysql_test.engine;

import java.sql.*;

/**
 * 控制mysql脚本
 */
public class Linksql {

    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //指定连接数据库的url
    final static String DB_URL = "jdbc:mysql://39.97.119.183/RUNOOB";
    //mysql用户名
    final static String name = "root";
    //mysql密码
    final static String pwd = "409421";

    public static void createTable() {

    }


    public String test(String cource_name) {
        String creatsql = "CREATE TABLE " + cource_name + "("
                + "name varchar(20) not null"
                + ")charset=utf8;";
        return creatsql;
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            System.out.println("连接数据库");
            conn = DriverManager.getConnection(DB_URL, name, pwd);
            Linksql linksql = new Linksql();
            //执行创建表
            System.out.println("创建表");
            stmt = conn.createStatement();
            String creatsql = linksql.test("python");
            if (0 == stmt.executeLargeUpdate(creatsql)) {
                System.out.println("成功创建表！");
            } else {
                System.out.println("创建表失败！");
            }
            stmt.close();
            conn.close();
            System.out.println("关闭资源");
        } catch (Exception e) {
            System.out.println("创建表失败！");
            e.printStackTrace();

        }
    }
}