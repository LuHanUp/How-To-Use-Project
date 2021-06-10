package top.luhancc.hmily.server2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.luhancc.hmily.server2.service.AccountInfoService;


@RestController
public class Bank2Controller {
    @Autowired
    AccountInfoService accountInfoService;

    /**
     * 接收bank1的转账请求，添加金额
     *
     * @param amount
     * @return
     */
    @RequestMapping("/transfer")
    public Boolean transfer(@RequestParam("amount") Double amount) {
        this.accountInfoService.updateAccountBalance("2", amount);
        return true;
    }


    /**
     * bank2向bank1发起转账
     *
     * @param amount
     * @return
     */
    @RequestMapping("/transfer2")
    public String transfer2(@RequestParam("amount") Double amount) {
        this.accountInfoService.updateAccountBalance2("2", amount);
        return "bank2向bank1转账:" + amount;
    }

}
