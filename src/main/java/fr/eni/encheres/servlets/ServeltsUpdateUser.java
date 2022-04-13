package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/updateUser")
public class ServeltsUpdateUser extends HttpServlet {
    private UserManager userManager;

    public void init() throws ServletException {
        userManager = UserManager.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher("WEB-INF/updateUser.jsp").forward(request, response);

        } else {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userSession = (User) request.getSession().getAttribute("user");

        try {
            String password = request.getParameter("password");
            String passwordCheck = request.getParameter("passwordCheck");
            if (Objects.equals(password, passwordCheck)) {
                User user = new User(
                        userSession.getId(),
                        request.getParameter("username"),
                        request.getParameter("surname"),
                        request.getParameter("firstName"),
                        request.getParameter("email"),
                        Integer.parseInt(request.getParameter("phone")),
                        request.getParameter("street"),
                        Integer.parseInt(request.getParameter("postalCode")),
                        request.getParameter("city"),
                        request.getParameter("password"),
                        userSession.getCredit(), userSession.isAdmin()
                );
                String canCreate=userManager.checkIfUserCanBeCreate(user);

                boolean usernameCheck=userManager.checkUsernameIsCorrect(user.getUsername());
                if(canCreate==null && usernameCheck) {
                    userManager.updateUser(user);
                    request.getSession().setAttribute("user", user);
                }else if(!usernameCheck){
                    request.setAttribute("error", " Veuillez utiliser un pseudo seulement avec des caractères alphanumérique");
                }else{
                    request.setAttribute("error", canCreate+" déjà utilisé sur le site, veuillez en utiliser un autre.");
                }
            }else{
                request.setAttribute("error", "Confimation de mot de passe non identique");
            }
            request.getRequestDispatcher("WEB-INF/updateUser.jsp").forward(request, response);

        } catch (NumberFormatException | DALException e) {
            e.printStackTrace();
        }
    }
}
