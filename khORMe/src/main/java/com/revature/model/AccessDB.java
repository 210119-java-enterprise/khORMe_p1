package com.revature.model;

import com.revature.forum.util.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDB {

    public void getClassTable(Class cls){

    }


    public void selectStar(Class<?> cls){

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select*from "+cls.getSimpleName()+";";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Field[] fields = cls.getFields();
            System.out.println(fields.length);
            while(rs.next()) {
                for (Field field : fields) {

                    switch(field.getType().toString()){
                        case "int":
                            System.out.print(rs.getInt(field.getName())+" ");
                            break;
                        case "class java.lang.String":
                            System.out.println(rs.getString(field.getName())+" ");
                            break;
                        default:
                            System.out.println("ERROR DEFAULT -- field.getType()");
                            break;
                    }
                }
                System.out.print("\n");

            }

        } catch (SQLException e) { e.printStackTrace(); }
    }
}
