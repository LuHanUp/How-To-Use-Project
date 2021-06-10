package top.luhancc.hmily.server1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.luhancc.hmily.server1.service.AccountInfoService;


@RestController
public class Bank1Controller {
    @Autowired
    AccountInfoService accountInfoService;

    /**
     * bank1向bank2发起转账
     *
     * @param amount
     * @return
     */
    @RequestMapping("/transfer")
    public String transfer(@RequestParam("amount") Double amount) {
        this.accountInfoService.updateAccountBalance("1", amount);
        return "bank1向bank2转账:" + amount;
    }

    /**
     * 接收bank2的转账请求，添加金额
     *
     * @param amount
     * @return
     */
    @RequestMapping("/transfer1")
    public Boolean transfer1(@RequestParam("amount") Double amount) {
        this.accountInfoService.updateAccountBalance1("1", amount);
        return true;
    }

}
