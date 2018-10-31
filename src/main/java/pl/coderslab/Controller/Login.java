package pl.coderslab.Controller;

import pl.coderslab.Service.ServletService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"/login", "/login/"})
public class Login extends HttpServlet
{
    private final String adminName = "admin";
    private final String adminPass = "coderslab";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals(this.adminName) && password.equals(this.adminPass))
        {
            // jak sie zgadza to uniewaznij stara sesje
            HttpSession oldSession = request.getSession(false); // false - jak nie ma zadnej sesji to nie tworz tylko zwroc null
            if (oldSession != null) // jak byla jakas stara sesja
            {
                oldSession.invalidate(); // to ją unieważnij
            }

            HttpSession newSession = request.getSession(true); // true - stworz jak nie ma (a nie ma)
            newSession.setAttribute("admin_name", this.adminName);
            newSession.setAttribute("admin_pass", this.adminPass);
            if (request.getParameter("remember") == null)
            {
                System.out.println("jest null - krotka sesja");
                newSession.setMaxInactiveInterval(/*60 * 15*/5); // 15 minut lub do zamkniecia przegladarki // 5 sekund do testow
                //ustawiam tylko czas "nieaktywnosci" sesji - ciasteczko zyje do zamkniecia przegladarki czyli sesja tez
            } else
            {
                /* można wychodzic z przglądarki i sesja bedzie zapamietana, ale bez względu na wszystko
                 * raz na 3 dni trzeba sie logować (bo po 3 dniach ciasteczko idzie papa (+sesja) i nie odświeżam go
                 * nawet jak admin porusza się po stronie)
                 */
                System.out.println("nie jest null - dluga sesja");

                Cookie oldSessionCookie = ServletService.getCookie(request, "session_id");
                if (oldSessionCookie != null)
                {
                    oldSessionCookie.setMaxAge(0);
                    response.addCookie(oldSessionCookie);
                }

                int sessMaxAge = 60 * 60 * 24 * 3; // 3 dni
                Cookie sessionCookie = new Cookie("session_id", newSession.getId()); // sam se stworze ciastko dla sesji...
                sessionCookie.setMaxAge(sessMaxAge); // bo chce ustawic max age
                sessionCookie.setPath("/");
                response.addCookie(sessionCookie);

                newSession.setMaxInactiveInterval(sessMaxAge);
            }

            // response.sendRedirect("/adminpanel/manage/groups"); // logowanie ok, wiec przekierowujemy na cos
            response.sendRedirect(response.encodeRedirectURL("/")); // logowanie ok, wiec przekierowujemy na cos
        } else
        {
            request.setAttribute("login_info", "<p style='color: red;'>Zły login/hasło.</p>"); // mozna potem zmienic na template

            doGet(request, response); // jak haslo sie nie zgadza to wyswietl logowanie
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String loginInfo = (String) request.getAttribute("login_info");
        System.out.println("loginInfo: " + loginInfo + ".");
        HttpSession session = request.getSession(false); // false - nie twórz nowej jak nie istniteje sesja (zwroci null jak nie instnieje)

        if (session != null) // jak niepusta sesja...
        {
            String sessionAdminName = (String) session.getAttribute("admin_name");
            String sessionAdminPass = (String) session.getAttribute("admin_pass");
            if (sessionAdminName != null && sessionAdminPass != null) // i user/haslo ustawione...
            {
                if (sessionAdminName.equals(this.adminName) && sessionAdminPass.equals(this.adminPass)) // i sie zgadza...
                { // jak hasla sa w sesji
                    System.err.println("OK zapraszamy");
                    response.sendRedirect(response.encodeRedirectURL("/")); // to user zalogowany juz - przekieruj
                    return; // return zeby sie forward() nie wykonal bo bedzie error
                }
            }
        } // musze tak zrobic, bo filter nie obejmuje tego servletu (wykluczylem go)

        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}