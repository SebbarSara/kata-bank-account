package kata.bank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kata.bank.model.Account;

@Service
public class AccountService {
	
    private Map<Integer, Account> DB = new HashMap<>();

	
   public void save(Account account) {
	   account.setId(DB.keySet().size()+1);
	   DB.put(account.getId(), account);
   }
   
   public Account getAccountsById(int id){
   	Account account = DB.get(id) ;
       
       return account;
   }
    
    public List<Account> getAccountsList(){
    	List<Account> list = new ArrayList<>();
        if(list.isEmpty()) {
            list.addAll(DB.values());
        }
        return list;
    }


}
