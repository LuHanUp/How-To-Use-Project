package top.luhancc.use.tair.test;

import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.impl.DefaultTairManager;

import java.util.Collections;

/**
 * @author luHan
 * @create 2021/4/27 17:54
 * @since 1.0.0
 */
public class TairTest {

    public static void main(String[] args) {
        DefaultTairManager defaultTairManager = new DefaultTairManager();
        defaultTairManager.setConfigServerList(Collections.singletonList("192.168.2.141:5189"));// 设置Tair配置中心地址
        defaultTairManager.setTimeout(5000);// 设置超时时间,单位s,默认是2s
        defaultTairManager.setCharset("UTF-8");
        defaultTairManager.setGroupName("group_test");
        defaultTairManager.init();

        // 然后使用defaultTairManager对Tair进行操作即可
        /*
            namespace:命名空间,类似于redis中的database
            ResultCode:操作返回结果
         */
        ResultCode resultCode = defaultTairManager.put(1, "name", "this is value");

        /*
        Result<DataEntry> 获取的返回结果
        DataEntry:存储的数据
         */
        Result<DataEntry> dataEntryResult = defaultTairManager.get(1, "name");
    }
}
