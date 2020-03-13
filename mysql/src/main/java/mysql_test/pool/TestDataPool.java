package mysql_test.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDataPool {
    // 根据配置文件里的名称创建连接池
    public static ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0-config.xml");
    
    /**
     * 主程序
     */
    public static void main(String[] args) {
        // 模拟多次对数据库的查询操作
        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                public void run() {
                    select();
                }
            }, "线程" + i).start();
        }
    }
    
    /**
     * 查询程序
     */
    public static void select() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // 获取连接并执行SQL
        try {
            conn = cpds.getConnection();
            pstmt = conn.prepareStatement("select * from runoob_transcation_test ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(Thread.currentThread().getName() + "\t" + rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}