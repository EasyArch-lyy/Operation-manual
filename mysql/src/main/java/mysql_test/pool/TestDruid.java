package mysql_test.pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author lyy
 */
public class TestDruid {

   /**
    * 根据配置文件里的名称创建连接池
    */
    private static DataSource source = null;

    static {
        Properties pros = new Properties();
        InputStream is = TestDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 主程序
     */
    public static void main(String[] args) {
        // 模拟多次对数据库的查询操作
        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
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
            conn = source.getConnection();
            pstmt = conn.prepareStatement("select * from runoob_transcation_test ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(Thread.currentThread().getName() + "\t" +  rs.getInt("id"));
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