package com.lls.sms.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.utils
 * @ClassName: ConnectonUtil
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/5/29 18:23
 */

public class JdbcUtil {

    public static final Logger log = Logger.getLogger(String.valueOf(JdbcUtil.class));

    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    private static DruidDataSource druidDataSource;


    private JdbcUtil() {
    }

    static {

        try {
            Properties properties = new Properties();
            //使用输入流读取jdbc.properties文件
            InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取数据库连接池中的链接
     *
     * @return 返回null为连接数据池失败
     */
    public static Connection getConnection() {

        Connection conn = THREAD_LOCAL.get();
        if (conn == null) {
            try {
                conn = druidDataSource.getConnection();
                log.info("创建得到Connection");
                //保存到ThreadLocal中
                THREAD_LOCAL.set(conn);
                //设置为手动管理事务
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭连接
     */
    public static void commitAndClose() {
        Connection connection = THREAD_LOCAL.get();
        if (connection != null) {
            try {
                //提交事务
                connection.commit();
                log.info("事务提交");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //关闭连接
                    connection.close();
                    log.info("关闭连接");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        THREAD_LOCAL.remove();
    }

    /**
     * 回滚事务，并关闭连接
     */
    public static void rollbackAndClose() {
        Connection connection = THREAD_LOCAL.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
                log.info("回滚成功");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                    log.info("关闭连接");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        THREAD_LOCAL.remove();
    }


}
