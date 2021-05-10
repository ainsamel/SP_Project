package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import domain.UserService;
import util.Status;

public final class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher view = null;
        UserService UserService = null;
        Status status = new Status();
        request.setAttribute("status", status);

        String usertype = request.getParameter(request.getParameter("usertype"));
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        if (usertype.equals("unknown")) {
            status.addException(new Exception(
                    "Please select a login type"));
        }
        if ((userid == null) || (userid.length() == 0)) {
            status.addException(new Exception(
                    "Please enter your userid"));
        }
        if ((password == null) || (password.length() == 0)) {
            status.addException(new Exception(
                    "Please enter your password"));
        }

        User user = null;

        try {

            UserService = new UserService();

            user = UserService.getUser(usertype, userid, password);

            if (user == null) {
                status.addException(new Exception(
                        "Please enter your user information in the right way"));
            }

            if (!status.isSuccessful()) {
                view = request.getRequestDispatcher("main.jsp");
                view.forward(request, response);
                return;
            }

            request.setAttribute("user", user);

        } catch (Exception e) {
            status.addException(e);
            view = request.getRequestDispatcher("main.jsp");
            view.forward(request, response);
        }

        if (usertype.equals("A")) {
            view = request.getRequestDispatcher("admin/login.jsp");
            view.forward(request, response);
        }

        if (usertype.equals("C")) {
            view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }
    }
}