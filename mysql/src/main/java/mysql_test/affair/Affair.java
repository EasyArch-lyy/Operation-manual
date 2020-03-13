package mysql_test.affair;

import mysql_test.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * mysql事务类
 * @author lyy
 */
public class Affair {

    Connection con=null;

    public Affair(){
        con= JdbcUtil.initConnection();
    }
    /**
     * 插入数据使用事务
     */
    public void arrair() {

        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            //加载驱动类
            ps1 = con.prepareStatement("insert into t_user (username,pwd) values (?,?)");
            ps1.setObject(1, "张三");
            ps1.setObject(2, "666666");
            ps1.execute();
            System.out.println("插入一个用户张三");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ps2 = con.prepareStatement("insert into t_user (username,pwd) values (?,?)");
            ps2.setObject(1, "李四");
            ps2.setObject(2, "123456");
            ps2.execute();
            System.out.println("插入一个用户李四");
            con.commit();//提交事务
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
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
