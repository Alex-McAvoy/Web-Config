import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description: 使用db.properties连接数据库并进行CRUD的工具类
 * @Author: Alex McAvoy
 * @Date: 2021/1/11 10:33
 * @Version: 1.0
 **/
public class JDBCUtils {

    // 数据库驱动
    @SuppressWarnings("unused")
    private static String driver = "";
    // 数据库url
    @SuppressWarnings("unused")
    private static String url = "";
    // 数据库名称
    @SuppressWarnings("unused")
    private static String database = "";
    // 数据库url参数
    @SuppressWarnings("unused")
    private static String urlParameter = "";
    // 数据库用户名
    @SuppressWarnings("unused")
    private static String username = "";
    // 数据库密码
    @SuppressWarnings("unused")
    private static String password = "";

    //静态块加载数据库连接信息
    static {
        //属性文件
        Properties file = new Properties();
        try {
            //相对路径加载文件
            file.load(new FileInputStream(new File("src/config/db.properties")));
            //获取数据库信息
            driver = file.getProperty("datasource.connection.driver");
            url = file.getProperty("datasource.connection.url");
            database = file.getProperty("datasource.connection.database");
            urlParameter = file.getProperty("datasource.connection.urlParameter");
            username = file.getProperty("datasource.connection.username");
            password = file.getProperty("datasource.connection.password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Connection conn = null;
    static PreparedStatement prestate = null;
    static ResultSet rs = null;

    /**
     * @Description: 连接数据库
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2021/1/11 10:47
     **/
    public void getConn() {
        //加载数据库驱动
        try {
            Class.forName(driver);
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //连接数据库
        try {
            conn = DriverManager.getConnection(url + database + urlParameter, username, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 关闭数据库
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2021/1/11 10:53
     **/
    public void closeDB() {
        /*
         * 分别按以下顺序关闭数据库
         * 1）ResultSet结果集资源
         * 2）PreparedStatement对象资源
         * 3）Connection连接数据库对象
         */
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (prestate != null) {
            try {
                prestate.close();
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

    /**
     * @Description: 通用增删改操作
     * @Param: [sql, args]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2021/1/11 11:13
     **/
    public void update(String sql, Object... args) throws SQLException {
        //预编译SQL语句
        prestate = conn.prepareStatement(sql);
        //填充占位符
        System.out.println(args.length);
        for (int i = 0; i < args.length; i++)
            prestate.setObject(i + 1, args[i]);
        //上传数据库执行SQL语句
        prestate.execute();
    }

    /**
     * @Description: 通用查询操作
     * @Param: [sql, args]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2021/1/11 11:52
     **/
    public List<Object> query(Object javaBean, String sql, Object... args) throws SQLException {
        //预编译SQL语句
        prestate = conn.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < args.length; i++)
            prestate.setObject(i + 1, args[i]);
        //上传数据库执行SQL语句，并返回字符结果集
        rs = prestate.executeQuery();
        //获取结果集的元数据
        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的列数
        int columnCount = rsmd.getColumnCount();

        List<Object> list = new ArrayList();
        while (rs.next()) {
            try {
                Class clazz = javaBean.getClass(); //通过反射获取javaBean的类
                Object obj = clazz.getConstructor().newInstance(); //创建一个clazz类型的实例

                //处理结果集中一行数据中的每一列
                for (int i = 0; i < columnCount; i++) {
                    //获取一行中的列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取表中每列的列名
                    String columnName = rsmd.getColumnName(i + 1);

                    //通过反射为表对应的JavaBean对象的属性赋值
                    Field field = clazz.getDeclaredField(columnName); //获取javaBean当前列属性
                    String type = field.getGenericType().toString(); //获取javaBean当前列属性类型
                    field.setAccessible(true); //关闭安全检查

                    if (type.equals("int") && columnValue == null) //若当前列属性类型为int类型，且当前列列值为null
                        columnValue = 0;

                    field.set(obj, columnValue); //为javaBean设置值
                }
                list.add(obj);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Test
    public void test() {
        getConn();

        //增
//        try {
//            String sql = "INSERT INTO name_age(name,age) VALUES(?, ?);";
//            update(sql,"job","15");
//            System.out.println("插入成功");
//        } catch (SQLException e) {
//            System.out.println("插入失败");
//            e.printStackTrace();
//        }

        //删
//        try {
//            String sql = "DELETE FROM name_age WHERE id = ?;";
//            update(sql,4);
//            System.out.println("删除成功");
//        } catch (SQLException e) {
//            System.out.println("删除失败");
//            e.printStackTrace();
//        }

        //改
//        try {
//            String sql = "UPDATE name_age SET age = ? WHERE id = ?;";
//            update(sql, 15, 3);
//            System.out.println("修改成功");
//        } catch (SQLException e) {
//            System.out.println("修改失败");
//            e.printStackTrace();
//        }

        //查
        try {
            String sql = "SELECT * FROM name_age WHERE age > ?;";
            List<Object> list = query(new Person(), sql, 10);
            System.out.println(list);
            System.out.println("查询成功");
        } catch (SQLException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
        closeDB();
    }

}
