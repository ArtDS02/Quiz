/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Question;
import context.DBContext;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quoct
 */
public class QuestionDAO extends DBContext {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;
    
        public Question getQuestionById(int id) {
        String sql = "Select * From Question Where QuestionId = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Question question = new Question(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                return question;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static ArrayList<Question> getListQuestions() {
        try {
            String query = "select * from Question";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Question> list = new ArrayList<>();
            while (rs.next()) {
                Question a = new Question(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(a);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
////////////////////////////////////////////////////////

    public static void CreateQuestion(String Detail, String AnswerA, String AnswerB, String AnswerC, String AnswerD, String TrueAnswer) {
        try {
            String query = "INSERT INTO Question (Detail, AnswerA, AnswerB, AnswerC, AnswerD, TrueAnswer)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?);";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Detail);
            ps.setString(2, AnswerA);
            ps.setString(3, AnswerB);
            ps.setString(4, AnswerC);
            ps.setString(5, AnswerD);
            ps.setString(6, TrueAnswer);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static Question getQuestion(String id) {
        String query = "select * from Question where QuestionID=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Question(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void UpdateQuestion(String Detail, String AnswerA, String AnswerB, String AnswerC, String AnswerD, String TrueAnswer, int Id) {
        try {
            String query = "UPDATE Question SET Detail = ?, AnswerA = ?, AnswerB = ?, AnswerC = ?, AnswerD = ?, TrueAnswer = ? "
                    + "     WHERE QuestionID = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Detail);
            ps.setString(2, AnswerA);
            ps.setString(3, AnswerB);
            ps.setString(4, AnswerC);
            ps.setString(5, AnswerD);
            ps.setString(6, TrueAnswer);
            ps.setInt(7, Id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public static void deleteQuestion(String id) {
        String query = "DELETE  FROM Question  WHERE QuestionID=?";
        try {
            conn = new DBContext().getConnection();
            System.out.println(conn);
            ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Question getQuestionById2(int id) {
        String sql = "Select * From Question Where QuestionId = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Question question = new Question(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                return question;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Question> getQuestionByID(int id) {
        String sql = "Select * From CollectionConnect Join Question On CollectionConnect.QuestionID = Question.QuestionID Where CollectionID = ?";
        List<Question> list = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question(rs.getInt("QuestionID"),
                        rs.getString("Detail"),
                        rs.getString("AnswerA"),
                        rs.getString("AnswerB"),
                        rs.getString("AnswerC"),
                        rs.getString("AnswerD"),
                        rs.getString("TrueAnswer"));
                list.add(question);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int lastPagesP(int size, int id) {
        int lastPages = 0;
        String createQuery = "Select count (*) From CollectionConnect Where CollectionID = ?" ;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(createQuery);
            rs = ps.executeQuery();
            ps.setInt(1, id);
            while (rs.next()) {
                int total = rs.getInt(1);
                lastPages = total / size;

                if (total % size != 0) {
                    lastPages++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return lastPages;
    }

}



