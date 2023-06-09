/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;
import model.Question;

/**
 *
 * @author Thinh
 */
@WebServlet(name = "Update", urlPatterns = {"/Update"})
public class Update extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        switch (id.charAt(0)) {
            case 'a': {
                AccountDAO dao = new AccountDAO();
                Account s = dao.getAccount(id.substring(1));
                if (!dao.getAccount(id.substring(1)).getUsername().trim().equals("admin")) {
                    request.setAttribute("st", s);
                    request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
                }
                response.sendRedirect("manageUser.jsp");

                break;
            }
            case 'q': {
                QuestionDAO dao = new QuestionDAO();
                Question s = dao.getQuestion(id.substring(1));
                request.setAttribute("st", s);
                request.getRequestDispatcher("UpdateQuestion.jsp").forward(request, response);
                break;
            }
            default:
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        switch (id.charAt(0)) {
            case 'a': {
                String UserName = request.getParameter("UserName");
                AccountDAO dao = new AccountDAO();
                if (dao.checkName(UserName.trim()) == 0) {
                    String FirstName = request.getParameter("FirstName");
                    String LastName = request.getParameter("LastName");
                    String Password = request.getParameter("Password");
                    String repass = request.getParameter("repass");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String DOB = request.getParameter("DOB");
                    String Role = request.getParameter("Role");
                    if (FirstName.equals("") || LastName.equals("") || Password.equals("") || repass.equals("") || phone.equals("") || email.equals("") || DOB.equals("") || Role.equals("")) {
                        request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
                    } else {
                        dao.updateAccount(Integer.parseInt(id.substring(1)), FirstName, LastName, UserName, Password, phone, email, DOB, Integer.parseInt(Role));
                        response.sendRedirect("manageUser.jsp");
                        break;
                    }
                } else {
                    request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
                }
            }
            case 'q': {
                QuestionDAO dao = new QuestionDAO();
                String Detail = request.getParameter("Detail");
                String AnswerA = request.getParameter("AnswerA");
                String AnswerB = request.getParameter("AnswerB");
                String AnswerC = request.getParameter("AnswerC");
                String AnswerD = request.getParameter("AnswerD");
                String TrueAnswer = request.getParameter("TrueAnswer");
                if (Detail.equals("") || TrueAnswer.equals("") || AnswerA.equals("") || AnswerB.equals("") || AnswerC.equals("") || AnswerD.equals("")) {
                    response.sendRedirect("UpdateQuestion.jsp");
//                    response.sendRedirect("manageQuestion.jsp");
                } else {
                    dao.UpdateQuestion(Detail, AnswerA, AnswerB, AnswerC, AnswerD, TrueAnswer, Integer.parseInt(id.substring(1)));
                    response.sendRedirect("manageQuestion.jsp");
                }
            }
            default:
                break;
        }
    }

}
