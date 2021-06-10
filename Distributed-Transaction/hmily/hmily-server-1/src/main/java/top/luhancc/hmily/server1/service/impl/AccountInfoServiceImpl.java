package top.luhancc.hmily.server1.service.impl;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luhancc.hmily.server1.dao.AccountInfoDao;
import top.luhancc.hmily.server1.service.AccountInfoService;
import top.luhancc.hmily.server1.service.AccountInfoTcc;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {


    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private AccountInfoTcc accountInfoTcc;


    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        System.out.println("******** Bank1 Service Begin ... ");
        accountInfoDao.updateAccountBalance(accountNo, amount * -1);//转出
        accountInfoTcc.prepare(accountNo, amount);
    }

    @Override
    @Hmily(confirmMethod = "updateAccountBalance1ConfirmMethod", cancelMethod = "updateAccountBalance1CancelMethod")
    public void updateAccountBalance1(String accountNo, Double amount) {
        System.out.println("******** Bank1 Service Begin ...");
        try {
            if (amount > 100) {
                int i = 1 / 0;// 让转账金额大于100时出现异常，以便演示分布式事务是否生效
            }
            accountInfoDao.updateAccountBalance(accountNo, amount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateAccountBalance1ConfirmMethod(String accountNo, Double amount) {
        System.out.println("******** Bank1 Service Commit ...");
    }

    public void updateAccountBalance1CancelMethod(String accountNo, Double amount) {
        accountInfoDao.updateAccountBalance(accountNo, amount * -1);
        System.out.println("******** Bank1 Service rollback ...");
    }


}
