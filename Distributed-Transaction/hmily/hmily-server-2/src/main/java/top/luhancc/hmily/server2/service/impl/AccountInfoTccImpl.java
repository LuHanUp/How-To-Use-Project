package top.luhancc.hmily.server2.service.impl;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luhancc.hmily.server2.dao.AccountInfoDao;
import top.luhancc.hmily.server2.feign.Bank1Client;
import top.luhancc.hmily.server2.service.AccountInfoTcc;

@Service
public class AccountInfoTccImpl implements AccountInfoTcc {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private Bank1Client bank1Client;

    @Override
    @Hmily(confirmMethod = "commit", cancelMethod = "rollback")
    public void prepare(String accountNo, double amount) {
        System.out.println("******** Bank2 Service  prepare...  ");
        if (!bank1Client.transfer1(amount)) {
            throw new RuntimeException("bank1 exception");
        }
    }

    @Override
    public void commit(String accountNo, double amount) {
        System.out.println("******** Bank2 Service commit...");
    }

    @Override
    public void rollback(String accountNo, double amount) {
        accountInfoDao.updateAccountBalance(accountNo, amount);
        System.out.println("******** Bank2 Service rollback...  ");
    }
}
