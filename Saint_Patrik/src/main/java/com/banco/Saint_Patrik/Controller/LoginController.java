package com.banco.Saint_Patrik.Controller;

import com.banco.Saint_Patrik.Entities.Card;
import com.banco.Saint_Patrik.Entities.Transaction;
import com.banco.Saint_Patrik.Errors.ServiceError;
import com.banco.Saint_Patrik.Services.CardService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

   
    @Autowired
    private CardService cardService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/main")
    public String main(HttpSession session, ModelMap model, @RequestParam(required = false) String cardId) throws ServiceError {
        Card login = (Card) session.getAttribute("cardSession"); //con esto si el id de login logueado viene nulo, no ejecuta el metodo
        if (login == null) {
            return "redirect:/login";
        }
        List<Transaction> allTransactions = cardService.searchAllTransactions(login.getId());
        model.addAttribute("transactions", allTransactions);

        return "index.html";
       
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, 
            @RequestParam(required = false) String logout, ModelMap model) {
        
        if (error != null) {
            model.put("error", "INCORRECT USERNAME OR PASSWORD"); //hay que mostrarlo en la vista login.html
        }
        if (logout != null) {
            model.put("logout", "SUCCESSFULLY EXITED THE PLATFORM"); //hay que mostrarlo en la vista login.html
        }
        return "login.html";
    }
}
