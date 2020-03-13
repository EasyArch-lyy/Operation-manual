package mysql_test.engine;

import mysql_test.util.JdbcUtil;

import java.sql.*;

/**
 * 控制mysql脚本
 * @author lyy
 */
public class Linksql {

    private static Connection con= JdbcUtil.initConnection();

    /**
    * @param cource_name
    */
    public String test(String cource_name) {
        String creatsql = "CREATE TABLE " + cource_name + "("
                + "name varchar(20) not null"
                + ")charset=utf8;";
        return creatsql;
    }

   /**
    * 根据表名创建表
    * @param tableName
    */
    public static void createTable(String tableName){
        Statement stmt = null;
        try {
            System.out.println("连接数据库");
            Linksql linksql = new Linksql();
            //执行创建表
            System.out.println("创建表");
            stmt = con.createStatement();
            String creatsql = linksql.test(tableName);
            if (0 == stmt.executeLargeUpdate(creatsql)) {
                System.out.println("成功创建表！");
            } else {
                System.out.println("创建表失败！");
            }
            stmt.close();
            con.close();
            System.out.println("关闭资源");
        } catch (Exception e) {
            System.out.println("创建表失败！");
            e.printStackTrace();
        }
    }
}