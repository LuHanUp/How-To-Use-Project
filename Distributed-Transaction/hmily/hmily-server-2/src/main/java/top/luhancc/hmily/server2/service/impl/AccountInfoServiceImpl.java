package top.luhancc.hmily.server2.service.impl;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luhancc.hmily.server2.dao.AccountInfoDao;
import top.luhancc.hmily.server2.feign.Bank1Client;
import top.luhancc.hmily.server2.service.AccountInfoService;
import top.luhancc.hmily.server2.service.AccountInfoTcc;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
    private AccountInfoTcc accountInfoTcc;

    @Override
    @Hmily(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public Boolean updateAccountBalance(String accountNo, Double amount) {
        System.out.println("******** Bank2 Service Begin ...");
        try {
            if (amount > 100) {
                int i = 1 / 0;// 让转账金额大于100时出现异常，以便演示分布式事务是否生效
            }
            accountInfoDao.updateAccountBalance(accountNo, amount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    public Boolean confirmMethod(String accountNo, Double amount) {
        System.out.println("******** Bank2 Service commit...  ");
        return true;
    }

    public Boolean cancelMethod(String accountNo, Double amount) {
        accountInfoDao.updateAccountBalance(accountNo, amount * -1);
        System.out.println("******** Bank2 Service rollback...  ");
        return true;
    }

    @Override
    public void updateAccountBalance2(String accountNo, Double amount) {
        System.out.println("******** Bank2 Service Begin ... ");
        accountInfoDao.updateAccountBalance(accountNo, amount * -1);//转出
        accountInfoTcc.prepare(accountNo, amount);
    }
}
