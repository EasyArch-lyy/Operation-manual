package mysql_test.affair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 事务的基本用法
 */
public class Test {

    public void arrair(){

    }

    private Connection con= null;

  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;


    try {
      //加载驱动类
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
      conn.setAutoCommit(false); //JDBC中默认是true，自动提交事务
      ps1 = conn.prepareStatement("insert into t_user (username,pwd) values (?,?)");//?是占位符
      ps1.setObject(1, "张三");
      ps1.setObject(2, "666666");
      ps1.execute();
      System.out.println("插入一个用户张三");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      ps2 = conn.prepareStatement("insert into t_user (username,pwd) values (?,?)");
      ps2.setObject(1, "李四");
      ps2.setObject(2, "123456");
      ps2.execute();
      System.out.println("插入一个用户李四");
      conn.commit();//提交事务
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      try {
        conn.rollback();//某一条数据添加失败时，回滚
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps1 != null) {
          ps1.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}