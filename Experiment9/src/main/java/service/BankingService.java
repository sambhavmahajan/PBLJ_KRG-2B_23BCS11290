package service;

import com.example.app.dao.AccountDAO;
import com.example.app.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankingService {
    @Autowired
    private AccountDAO accountDAO;

    @Transactional
    public void transfer(int fromId, int toId, double amount){
        Account from = accountDAO.get(fromId);
        Account to = accountDAO.get(toId);

        if(from.getBalance() < amount){
            throw new RuntimeException("Insufficient balance!");
        }

        from.setBalance(from.getBalance()-amount);
        to.setBalance(to.getBalance()+amount);

        accountDAO.update(from);
        accountDAO.update(to);
    }
}
