package com.banco.Saint_Patrik.Controller;

import com.banco.Saint_Patrik.Entities.Card;
import com.banco.Saint_Patrik.Entities.Transaction;
import com.banco.Saint_Patrik.Entities.User;
import com.banco.Saint_Patrik.Enum.Role;
import com.banco.Saint_Patrik.Enum.Type_Transaction;
import com.banco.Saint_Patrik.Errors.ServiceError;
import com.banco.Saint_Patrik.Services.SaveService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/database")
public class SaveController {

    @Autowired
    private SaveService saveService;

    @GetMapping("/save")
    public String putData(ModelMap model) throws ServiceError {

        List<Card> cards = new ArrayList();
        List<Transaction> transactions = new ArrayList();

        User user = new User();
        user.setDocument("88855533");
        user.setEnabled(true);
        user.setMail("mailtres@mail.com");
        user.setName("Maria");
        user.setSurname("Sanchez");
        user.setTransaction(transactions);
        user.setTypeRole(Role.USER);

        Card card = new Card();
        card.setNumberCard("988822321");
        card.setCredit(384000.0);
        card.setEnabled(true);
        String encrypted = new BCryptPasswordEncoder().encode("0123");
        card.setPin(encrypted);
        card.setTransaction(transactions);
        card.setUser(user);

        cards.add(card);
        user.setCard(cards);

        Transaction transaction = new Transaction();
        transaction.setAmount(5000.0);
        transaction.setCard(card);
        Date dateTransaction = new Date();
        transaction.setDateTransaction(dateTransaction);
        transaction.setEnabled(true);
        transaction.setType(Type_Transaction.SEND);
        transaction.setUser(user);

        transactions.add(transaction);

        saveService.registrar(user, card, transaction);

        return "dataInDB.html";
    }
}
