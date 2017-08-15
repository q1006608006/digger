package top.ivan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ivan.domain.Page;

@Controller
@RequestMapping("/Hello")
public class HelloController {
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @RequestMapping("hello")
    public @ResponseBody
    String hello() {
        return page.getContent();
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
