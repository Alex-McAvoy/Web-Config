import org.junit.Test;

import java.sql.*;

public class JDBC {
    static Connection conn = null;
    static Statement state = null;
    static PreparedStatement prestate = null;
    static ResultSet rs = null;

    //连接数据库
    public void getConn() {
        //加载数据库驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //连接数据库
        try {
            //连接基本信息
            String url = "jdbc:mysql://localhost:3306/";
            String database = "test";
            String urlParameter = "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
            String username = "root";
            String password = "123456";
            //数据库连接
            conn = DriverManager.getConnection(url + database + urlParameter, username, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //关闭数据库
    public void closeDB() {
        /*
         * 分别按以下顺序关闭数据库
         * 1）ResultSet结果集资源
         * 2）Statement对象资源
         * 3）Connection连接数据库对象
         */
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                System.out.println("数据库关闭成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    CRUD crud = new CRUD(); //内部类

    //测试数据库
    public void test() {
//        crud.create();
//        crud.delete();
//        crud.update();
        crud.retrieve();
    }

    //内部类
    public class CRUD {
        // 插入数据
        public void create() {
            String sql = "INSERT INTO name_age SET name='alex',age='50';";// 不设置id属性，默认递增
//		String sql="INSERT INTO name_age(name,age) values('tom','15');";//不设置id属性，默认递增
//		String sql="INSERT INTO name_age values('132','alex','55');";//设置id属性，不可递增
            try {
                state = conn.createStatement(); //容器
                state.executeUpdate(sql); //将sql语句上传至数据库执行
                System.out.println("插入成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 查询数据
        public void retrieve() {
            String sql = "select * from name_age;";
            try {
                state = conn.createStatement(); //容器
                rs = state.executeQuery(sql); //将sql语句传至数据库，返回的值为字符集
                while (rs.next()) { //next()获取内容
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String age = rs.getString(3);
                    System.out.println("id:" + id + ",name:" + name + ",age:" + age);
                }
                System.out.println("查询完毕");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 删除数据
        public void delete() {
            String sql = "DELETE FROM name_age WHERE id='132';";
            try {
                state = conn.createStatement(); //容器
                state.executeUpdate(sql); //将sql语句上传至数据库执行
                System.out.println("删除成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 修改数据
        public void update() {
            String sql = "UPDATE name_age SET age='22' WHERE name='tom';";
            try {
                state = conn.createStatement(); //容器
                state.executeUpdate(sql); //将sql语句上传至数据库执行
                System.out.println("修改成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 通过姓名查询年龄
        public void queryAgeByName(String name) {
            String sql = "SELECT * FROM name_age WHERE name=?";
//		String sql = "SELECT * FROM name_age WHERE name=? AND age=?"; //并列条件
            try {
                prestate = conn.prepareStatement(sql); //容器
                prestate.setString(1, name); //将sql中第1个?的值替换成name值
                rs = prestate.executeQuery(); //上传数据库返回结果集
                if (rs.next()) { //如果取到了值，那么输出
                    String age = rs.getString(3);
                    System.out.println("用户:" + name + "的年龄为:" + age);
                } else {
                    System.out.println("不存在名为:" + name + "的用户");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        jdbc.getConn();
        jdbc.test();
        jdbc.closeDB();
    }
}
