package top.luhancc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import top.luhancc.springmvc.controller.param.QueryVo;
import top.luhancc.springmvc.controller.param.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * SpringMvc 请求参数绑定
 *
 * @author luHan
 * @create 2021/5/11 14:25
 * @since 1.0.0
 */
@Controller
@RequestMapping("parameter/bind")
public class RequestParameterBindController {
    @RequestMapping()
    public String index() {
        return "parameter";
    }

    /**
     * SpringMvc对原生Servlet api的支持
     * <p>
     * 如果要在SpringMvc中使用Servlet原生对象，比如HttpServletRequest，直接在handler方法形参中声明即可
     */
    @RequestMapping("primevalServletApi")
    public void primevalServletApi(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String id = request.getParameter("id");
        System.out.println("从请求中获取的参数id:" + id);
        System.out.println("response:" + response);
        System.out.println("request:" + request);
        System.out.println("session:" + session);
    }

    /**
     * SpringMvc接收简单类型的参数
     *
     * @param id
     * @param name
     */
    @RequestMapping("simpleParameter")
    public void simpleParameter(Integer id, String name) {
        System.out.println("id:" + id + ",name:" + name);
    }

    /**
     * SpringMvc接收Pojo类型的参数
     *
     * @param user
     */
    @RequestMapping("pojoParameter")
    public void pojoParameter(User user) {
        System.out.println("user:" + user);
    }

    /**
     * SpringMvc接收Pojo包装类型的参数
     *
     * @param query
     */
    @RequestMapping("pojoBackParameter")
    public void pojoBackParameter(QueryVo query) {
        System.out.println("query:" + query);
    }

    /**
     * SpringMvc接收日志类型的参数
     * <p>
     * 需要定义一个类型转换器：参考{@link top.luhancc.springmvc.controller.param.DateParameterConverter}
     *
     * @param birthday
     */
    @RequestMapping("receiveDateParameter")
    public void receiveDateParameter(Date birthday) {
        System.out.println("birthday:" + birthday);
    }

    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile file, HttpSession session) {
        String originalFilename = file.getOriginalFilename();// 文件原始名称
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String newName = UUID.randomUUID() + ext;
        // 获取项目/uploads目录的绝对路径
        String realPath = session.getServletContext().getRealPath("/uploads");
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File folder = new File(realPath + "/" + datePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            // 存储文件到目录
            file.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
