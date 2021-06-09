package top.luhancc.hmily.server1.service.impl;

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


}
