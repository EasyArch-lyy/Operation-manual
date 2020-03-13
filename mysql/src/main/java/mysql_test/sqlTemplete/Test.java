package mysql_test.sqlTemplete;

import java.sql.*;
import static mysql_test.util.JdbcUtil.*;

public class Test {

    private Connection con= null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    private Statement stmt=null;

   /**
    * 初始化加载
    */
    public Test(){
        con=initConnection();
    }

    /**
     * 查询数据
     * @param sql
     * @param obj
     * @return ResultSet
     */
    public ResultSet queryData(String sql, Object[] obj) {
        String str = "";
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     *添加，修改和删除
     * @param sql
     * @param obj
     * @return int
     */
    public int updateAndDeleteData(String sql, Object[] obj) {
        int i = 0;
        try {
            ps = con.prepareStatement(sql);
            for (int j = 0; j < obj.length; j++) {
                ps.setObject(j + 1, obj[j]);
            }
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePs();
            closeRs();
        }
        return i;
    }

   /**
    * 查询有多少页
    * @param pageSize
    * @return pageCount
    */
   @org.junit.Test
    public void getPageCount(/*int pageSize*/) throws Exception {
        try {
            con=initConnection();
            String sql = "select count(*) from runoob_transcation_test";
            stmt = con.prepareStatement(sql);
            rs=stmt.executeQuery(sql);
//            rs.next();
//            while (rs.next()) {
                int rowsCount = rs.getInt(0);
                int pageCount = (int) Math.ceil(1.0 * rowsCount / 10);//算出总共需要多少页
                System.out.println(pageCount);
//            }
//            return pageCount;
        } finally {
//            con.close();
        }
    }

   /**
    * 分页查询机制
    * @param pageNumber 页数
    * @param pageCount 每页显示多少个数据
    */
    public void selectUserByPage(int pageNumber,int pageCount,String table) throws SQLException {

        try {
            stmt = con.prepareStatement("select * from "+table);
//            stmt.setInt(1, (pageNumber-1)*pageCount );
//            stmt.setInt(2, pageCount);
//            rs = stmt.executeQuery();

            while(rs.next()) {
                //System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
                System.out.println(rs.getString("id")+","+rs.getString("username")+","+rs.getString("password"));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeConnction();
        }
    }
}
