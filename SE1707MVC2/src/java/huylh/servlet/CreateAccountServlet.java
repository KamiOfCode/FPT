/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylh.servlet;

import huylh.registration.RegistrationCreateError;
import huylh.registration.RegistrationDAO;
import huylh.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LE HUU HUY
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private final String USER_LENGTH_ERROR = "Username is required typing from 6 to 30 character";
    private final String PASSWORD_LENGTH_ERROR = "Password is required typing from 6 to 20 character";
    private final String FULLNAME_LENGTH_ERROR = "Full name is required typing from 2 to 50 character";
    private final String CONFIRM_ERROR = "Confirm must match password";
    private final String USER_EXISTED_ERROR = " is existed in this system";
    private final String CREATE_ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get all parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        String url = CREATE_ERROR_PAGE;

        try {
            //2. Verify all user's error
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameIsExisted(USER_LENGTH_ERROR);
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr(PASSWORD_LENGTH_ERROR);
            } else if (confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched(CONFIRM_ERROR);
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthErr(FULLNAME_LENGTH_ERROR);
            }
            if (foundErr) {//error occur
                //3. catch errors into an atrribute, then fw errors to display page
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//no error
                //3. call DAO
                //3.1 new DAO obj
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call method of DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                //4. process result
                if (result) {
                    url = LOGIN_PAGE;
                }
            }//no error
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateAccountServlet _ SQL: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
