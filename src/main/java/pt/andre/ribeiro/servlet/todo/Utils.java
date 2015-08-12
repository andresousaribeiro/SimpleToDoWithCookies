/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.andre.ribeiro.servlet.todo;

import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AndreRibeiro
 */
public class Utils {
    
    
    public static void saveToCookies(HttpServletResponse response) {
        
        String output = ""; 
        for (Map.Entry<Integer, Task> entry : TaskDB.instance().getTasks().entrySet()) {
                Integer key = entry.getKey();
                Task value = entry.getValue();
                output+= key + "#" + value+"&";
            }
        Cookie cookie = new Cookie("DB",output);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
    }
}
