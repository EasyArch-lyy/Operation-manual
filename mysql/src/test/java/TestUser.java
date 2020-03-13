import mysql_test.util.JdbcUtil;

import static mysql_test.engine.Linksql.createTable;

public class TestUser {

    private int s=0;

    public TestUser(){
        s=TestUtil.CCC(s);
    }
    private int get(){
        if (s==0){
            s=TestUtil.CCC(s);
        }
        return s;
    }

    public static void main(String[] args) {
//        TestUser testUser=new TestUser();
//        System.out.println(testUser.get());
        TestUtil.createT();
    }
}
