package top.ivan.crawler;

import top.ivan.crawler.core.GriddleBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/19
 */
public class Examiner {
    private String focusPoint;
    private Collapsar collapsar;

    public String getFocusPoint() {
        return focusPoint;
    }

    public void setFocusPoint(String focusPoint) {
        this.focusPoint = focusPoint;
    }

    Object survey(Object src) {
//        return focus.peek(src);
        return null;
    }

    public static String exceptionMessage(Class<?> clazz,Throwable cause) {
        return String.valueOf(-1);
    }

    public static void main(String[] args) throws Exception {
        Griddle griddle = GriddleBuilder.builder().json(
                "[{\"peek\":\"id\",\"defaultValue\":\"akb\",\"focus\":{\"type\":\"regex\",\"target\":\"[\\\\s\\\\S]*\\\\[id=\\\"([\\\\s\\\\S]*?)\\\"\\\\][\\\\s\\\\S]*\",\"key\":\"[$1]\",\"interceptor\":{\"type\":\"list\",\"target\":\"@-\",\"interceptor\":{\"type\":\"list\",\"target\":\"regex[a]\",\"key\":\"fire!!!\",\"interceptor\":{\"type\":\"list\",\"target\":\"regex[b]\",\"key\":\"more fire!!!\"}}}}},{\"peek\":\"value\",\"defaultValue\":\"NaN\",\"focus\":{\"type\":\"test\",\"target\":\"regex[[\\\\S]* (\\\\S+)fuck(\\\\S+) [\\\\s\\\\S]*]\",\"key\":\"$matches_[\\\\s\\\\S]*fuck[\\\\s\\\\S]*>>[$1 fuck $2 >{$id}<]\"}}]"
        ).build();
        String src = "a ifuckyou en[id=\"a,b, ,\"]" +
                "<!DOCTYPE HTML>\n" + "<html lang=\"en-US\">\n" + "<head>\n" + "  <meta charset=\"UTF-8\" />\n" + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge,chrome=1\" />\n" + "  <meta name=\"robots\" content=\"noindex, nofollow\" />\n" + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\" />\n" + "  <title>Just a moment...</title>\n" + "  <style type=\"text/css\">\n" + "    html, body {width: 100%; height: 100%; margin: 0; padding: 0;}\n" + "    body {background-color: #ffffff; font-family: Helvetica, Arial, sans-serif; font-size: 100%;}\n" + "    h1 {font-size: 1.5em; color: #404040; text-align: center;}\n" + "    p {font-size: 1em; color: #404040; text-align: center; margin: 10px 0 0 0;}\n" + "    #spinner {margin: 0 auto 30px auto; display: block;}\n" + "    .attribution {margin-top: 20px;}\n" + "    @-webkit-keyframes bubbles { 33%: { -webkit-transform: translateY(10px); transform: translateY(10px); } 66% { -webkit-transform: translateY(-10px); transform: translateY(-10px); } 100% { -webkit-transform: translateY(0); transform: translateY(0); } }\n" + "    @keyframes bubbles { 33%: { -webkit-transform: translateY(10px); transform: translateY(10px); } 66% { -webkit-transform: translateY(-10px); transform: translateY(-10px); } 100% { -webkit-transform: translateY(0); transform: translateY(0); } }\n" + "    .bubbles { background-color: #404040; width:15px; height: 15px; margin:2px; border-radius:100%; -webkit-animation:bubbles 0.6s 0.07s infinite ease-in-out; animation:bubbles 0.6s 0.07s infinite ease-in-out; -webkit-animation-fill-mode:both; animation-fill-mode:both; display:inline-block; }\n" + "  </style>\n" + "\n" + "    <script type=\"text/javascript\">\n" + "  //<![CDATA[\n" + "  (function(){\n" + "    var a = function() {try{return !!window.addEventListener} catch(e) {return !1} },\n" + "    b = function(b, c) {a() ? document.addEventListener(\"DOMContentLoaded\", b, c) : document.attachEvent(\"onreadystatechange\", b)};\n" + "    b(function(){\n" + "      var a = document.getElementById('cf-content');a.style.display = 'block';\n" + "      setTimeout(function(){\n" + "        var s,t,o,p,b,r,e,a,k,i,n,g,f, YWHRCNR={\"DpndptOAJDp\":+((+!![]+[])+(+[]))};\n" + "        t = document.createElement('div');\n" + "        t.innerHTML=\"<a href='/'>x</a>\";\n" + "        t = t.firstChild.href;r = t.match(/https?:\\/\\//)[0];\n" + "        t = t.substr(r.length); t = t.substr(0,t.length-1);\n" + "        a = document.getElementById('jschl-answer');\n" + "        f = document.getElementById('challenge-form');\n" + "        ;YWHRCNR.DpndptOAJDp*=+((!+[]+!![]+!![]+[])+(+[]));YWHRCNR.DpndptOAJDp+=+((!+[]+!![]+!![]+[])+(!+[]+!![]+!![]+!![]+!![]));YWHRCNR.DpndptOAJDp-=+((!+[]+!![]+!![]+[])+(!+[]+!![]+!![]+!![]+!![]+!![]+!![]));YWHRCNR.DpndptOAJDp-=+((+!![]+[])+(!+[]+!![]+!![]+!![]+!![]+!![]+!![]+!![]));YWHRCNR.DpndptOAJDp+=+((+!![]+[])+(!+[]+!![]+!![]));a.value = parseInt(YWHRCNR.DpndptOAJDp, 10) + t.length; '; 121'\n" + "        f.action += location.hash;\n" + "        f.submit();\n" + "      }, 4000);\n" + "    }, false);\n" + "  })();\n" + "  //]]>\n" + "</script>\n" + "\n" + "\n" + "</head>\n" + "<body>\n" + "  <table width=\"100%\" height=\"100%\" cellpadding=\"20\">\n" + "    <tr>\n" + "      <td align=\"center\" valign=\"middle\">\n" + "          <div class=\"cf-browser-verification cf-im-under-attack\">\n" + "  <noscript><h1 data-translate=\"turn_on_js\" style=\"color:#bd2426;\">Please turn JavaScript on and reload the page.</h1></noscript>\n" + "  <div id=\"cf-content\" style=\"display:none\">\n" + "    \n" + "    <div>\n" + "      <div class=\"bubbles\"></div>\n" + "      <div class=\"bubbles\"></div>\n" + "      <div class=\"bubbles\"></div>\n" + "    </div>\n" + "    <h1><span data-translate=\"checking_browser\">Checking your browser before accessing</span> elfagr.org.</h1>\n" + "    \n" + "    <p data-translate=\"process_is_automatic\">This process is automatic. Your browser will redirect to your requested content shortly.</p>\n" + "    <p data-translate=\"allow_5_secs\">Please allow up to 5 seconds&hellip;</p>\n" + "  </div>\n" + "   \n" + "  <form id=\"challenge-form\" action=\"/cdn-cgi/l/chk_jschl\" method=\"get\">\n" + "    <input type=\"hidden\" name=\"jschl_vc\" value=\"aadfda3a663bba0a2704d1856fca2072\"/>\n" + "    <input type=\"hidden\" name=\"pass\" value=\"1509503402.073-khqLNa+Z/F\"/>\n" + "    <input type=\"hidden\" id=\"jschl-answer\" name=\"jschl_answer\"/>\n" + "  </form>\n" + "</div>\n" + "\n" + "          \n" + "          <div class=\"attribution\">\n" + "            <a href=\"https://www.cloudflare.com/5xx-error-landing?utm_source=iuam\" target=\"_blank\" style=\"font-size: 12px;\">DDoS protection by Cloudflare</a>\n" + "            <br>\n" + "            Ray ID: 3b6b6dedf62d2330\n" + "          </div>\n" + "      </td>\n" + "     \n" + "    </tr>\n" + "  </table>\n" + "</body>\n" + "</html>\n";
        Map<String,String> ret = griddle.doFilter(src,new HashMap<>());
        System.out.println(ret);

/*        long et = System.currentTimeMillis();
        System.out.println((et - st));
        System.out.println(ret);*/
    }
}
