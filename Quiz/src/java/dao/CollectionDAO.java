/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Collection;

/**
 *
 * @author quoct
 */
public class CollectionDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    //Dang dung
    public static List<Collection> getAllCollection() {
        List<Collection> list = new ArrayList<>();

        String sql = "Select * From Collection";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AccountDAO accDAO = new AccountDAO();
                Account acc = accDAO.getAccountById2(rs.getInt(4));
                Collection cd = new Collection(rs.getInt(1), rs.getString(2), rs.getInt(3), acc);

                list.add(cd);
            }
        } catch (Exception e) {
        }
        return list;
    }


    public Collection getCollectionDetailById(int id) {

        String sql = "Select * From CollectionDetail Where CollectionDetailID = " + id;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                AccountDAO accDAO = new AccountDAO();
                Account acc = accDAO.getAccountById2(rs.getInt(4));
                Collection cd = new Collection(rs.getInt(1), rs.getString(2), rs.getInt(3), acc);

                return cd;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Collection> getCollectionDetailByOwner(Account account) {

        String sql = "Select * From CollectionDetail Where Owner = " + account.getId();
        List<Collection> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AccountDAO accDAO = new AccountDAO();
                Account acc = accDAO.getAccountById2(rs.getInt(4));

                Collection cd = new Collection(rs.getInt(1), rs.getString(2), rs.getInt(3), acc);
                list.add(cd);
            }
        } catch (Exception e) {
        }
        return list;
    }

}
