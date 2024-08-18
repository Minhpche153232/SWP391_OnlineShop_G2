/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.*;
import models.Type;

/**
 *
 * @author ADMIN
 */
public class TypeDAO extends DBContext {

    public Type getTypeById(int typeId) {
        Type type = null;
        try {
            String query = "SELECT * FROM Type WHERE typeId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, typeId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                type = new Type();
                type.setTypeId(rs.getInt("typeId"));
                type.setTypeName(rs.getString("typeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }
     public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<>();
        try {
            String query = "SELECT * FROM Type";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Type type = new Type();
                type.setTypeId(rs.getInt("typeId"));
                type.setTypeName(rs.getString("typeName"));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }
     public static void main(String[] args) {
        TypeDAO dAO = new TypeDAO();
         System.out.println(dAO.getAllTypes());
    }
}
