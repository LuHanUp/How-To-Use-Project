package top.luhancc.hmily.server2.feign;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hmily-server-1")
public interface Bank1Client {

    @RequestMapping("/hmily/server1/transfer1")
    @Hmily
    Boolean transfer1(@RequestParam("amount") Double amount);
}
