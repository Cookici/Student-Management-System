package com.lls.sms.dao;


import com.lls.sms.utils.JdbcUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.dao
 * @ClassName: BaseDao
 * @Author: 63283
 * @Description: 抽象类    DML
 * @Date: 2023/5/29 18:51
 */

public abstract class BaseDao<T> {

    private Class<T> clazz = null;

    {
        //获取T的真正类型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] type = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) type[0];
    }

    public int update(String sql, Object... args) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public <T> T queryForOne(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            T t = (T) clazz.newInstance();
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = rsMetaData.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public <T> List<T> queryForList(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = (T) clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = rsMetaData.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}