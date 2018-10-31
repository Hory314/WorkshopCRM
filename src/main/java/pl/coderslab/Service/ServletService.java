package pl.coderslab.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ServletService
{
    public static Cookie getCookie(HttpServletRequest request, String cookieName)
    {
        Cookie[] cookies = request.getCookies();

        try // jakby cookies[] bylo puste (bo nie ma ciasteczek w ogole)
        {
            for (Cookie c : cookies)
            {
                if (c.getName().equals(cookieName))
                {
                    return c;
                }
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Nie ma żadnych ciasteczek");
        }
        return null; // dojdzie tu jesli nie znajdzie ciasteczka
    }
}
