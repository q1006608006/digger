package top.ivan.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ivan.service.TestUserService;

@Controller
@RequestMapping("/user")
public class HelloController {
    private Logger logger = LogManager.getLogger(HelloController.class);

    @Autowired
    private TestUserService userService;

    @RequestMapping("/hello")
    public @ResponseBody
    String hello() {
        logger.debug("hehe");
        logger.error("hehe");
        logger.trace("hehe");
        logger.info("hehe");
        logger.warn("hehe");
        System.out.println("heie2hie");
        String msg = userService.getUserById(1).getName() + " say hello to you";
        return msg;
    }


/*    public static final String wrURL = "http://Nisfeldunia.ahram.org.eg/../../Media/NewsMedia/2017/8/12/2017-636381449719265225-926_608x403.jpg";
    public static final String clURL = "http://Nisfeldunia.ahram.org.eg/Media/NewsMedia/a/../2017/8/12/2017-636381449719265225-926_608x403.jpg";

    public static void main(String[] args) throws IOException {

      // System.out.println(wrURL.replaceAll("(\\.\\./)+",""));

       URL base = new URL("http://Nisfeldunia.ahram.org.eg");
       URL url1 = new URL(base,"../../Media/NewsMedia/2017/8/12/2017-636381449719265225-926_608x403.jpg");
       System.out.println(url1.toString().replaceAll("(\\.\\./)+",""));

       // System.out.println(getUrl(clURL));
    }




    public static String getUrl(String cUrl) throws Exception{
        int pos = cUrl.indexOf("://");
        String pro = cUrl.substring(0, pos);
        String body = cUrl.substring(pos+3);
        String[] cs = body.split("/");
        Stack<String> stack = new Stack<>();
        for(String str : cs) {
            if(str.equals("..")) {
                if(stack.size() > 1) {
                    stack.pop();
                }
            } else if(str.equals(".")) {
                continue;
            } else {
                stack.push(str);
            }
        }

        StringBuilder sb = new StringBuilder(pro).append(":/");
        for(int i = 0;i < stack.size();i++) {
            sb.append('/').append(stack.get(i));
        }
        return sb.toString();
    }*/

}
