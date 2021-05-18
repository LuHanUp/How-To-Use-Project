import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * @author luHan
 * @create 2021/5/18 12:02
 * @since 1.0.0
 */
public class TestMd5 {

    @Test
    public void createMd5Password() {
        String password = "123456";
        String username = "lisi";
        Md5Hash md5Hash = new Md5Hash(password, username + "abcd", 3);
        System.out.println(md5Hash);
    }
}
