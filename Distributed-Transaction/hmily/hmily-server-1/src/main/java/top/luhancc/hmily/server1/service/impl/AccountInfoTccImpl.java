package top.luhancc.hmily.server1.service.impl;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luhancc.hmily.server1.dao.AccountInfoDao;
import top.luhancc.hmily.server1.feign.Bank2Client;
import top.luhancc.hmily.server1.service.AccountInfoTcc;

@Service
public class AccountInfoTccImpl implements AccountInfoTcc {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private Bank2Client bank2Client;

    @Override
    @Hmily(confirmMethod = "commit", cancelMethod = "rollback")
    public void prepare(String accountNo, double amount) {
        System.out.println("******** Bank1 Service  prepare...  ");
        if (!bank2Client.transfer(amount)) {
            throw new RuntimeException("bank2 exception");
        }
    }

    @Override
    public void commit(String accountNo, double amount) {
        System.out.println("******** Bank1 Service commit...");
    }

    @Override
    public void rollback(String accountNo, double amount) {
        accountInfoDao.updateAccountBalance(accountNo, amount);
        System.out.println("******** Bank1 Service rollback...  ");
    }
}
