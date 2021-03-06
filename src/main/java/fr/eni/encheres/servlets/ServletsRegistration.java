package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "registration", value = "/registration")

public class ServletsRegistration extends HttpServlet {
    private UserManager userManager;

    public void init() throws ServletException {
        userManager = UserManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            String password = request.getParameter("password");
            String passwordCheck = request.getParameter("passwordCheck");

            if (request.getParameter("username").equals("") ||
                    request.getParameter("surname").equals("") ||
                    request.getParameter("firstName").equals("") ||
                    request.getParameter("email").equals("") ||
                    request.getParameter("phone").equals("") ||
                    request.getParameter("street").equals("") ||
                    request.getParameter("postalCode").equals("") ||
                    request.getParameter("city").equals("") ||
                    request.getParameter("password").equals("") ||
                    request.getParameter("passwordCheck").equals("")
            ) {
                request.setAttribute("error", "Veuillez remplir tous les champs");
                doGet(request, response);
            } else {

                if (Objects.equals(password, passwordCheck)) {
                    User user = new User(
                            request.getParameter("username"),
                            request.getParameter("surname"),
                            request.getParameter("firstName"),
                            request.getParameter("email"),
                            Integer.parseInt(request.getParameter("phone")),
                            request.getParameter("street"),
                            Integer.parseInt(request.getParameter("postalCode")),
                            request.getParameter("city"),
                            request.getParameter("password"),
                            100, false
                    );

                    String canCreate = userManager.checkIfUserCanBeCreate(user);

                    if (canCreate == null) {
                        User userRegistration = userManager.registration(user);
                        request.getSession().setAttribute("user", userRegistration);
                        getServletContext().getRequestDispatcher("/home").forward(request, response);
                    } else {
                        request.setAttribute("error", canCreate + " d??j?? utilis?? sur le site, veuillez en utiliser un autre.");
                        request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
                    }

                }
            }
        } catch (BLLException e) {
            try {
                throw new BLLException("BLL " + e);
            } catch (BLLException ex) {
                ex.printStackTrace();
            }
        } catch (DALException e) {
            e.printStackTrace();
        }
    }
}


