package top.luhancc.hmily.server1.feign;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hmily-server-2")
public interface Bank2Client {

    @GetMapping("/hmily/server2/transfer")
    @Hmily
    Boolean transfer(@RequestParam("amount") Double amount);
}
