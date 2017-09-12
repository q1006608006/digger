import org.junit.Test;
import top.ivan.digger.util.CrawlerUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/12
 */
public class UtilTest {

    @Test
    public void getPage() throws IOException {

        System.out.println(CrawlerUtil.request("https://www.baidu.com").header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
//                .header("Cookie","BAIDUID=F980647C3292B38526CD5D996FBB3DB5:FG=1; BIDUPSID=F980647C3292B38526CD5D996FBB3DB5; PSTM=1501203074; BDUSS=GtCd3RqS1Z-OH5sSlJ0WU1LaEpWWVcwaFhwZTRIQzlsWXdKTkZFRm9NYzVDYjVaTVFBQUFBJCQAAAAAAAAAAAEAAAC3GmkqtPPYvPf2utrYvMzsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADl8llk5fJZZZ; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; BD_CK_SAM=1; PSINO=7; H_PS_645EC=f642szV8egUgir%2Bj3J%2FXsz3ElN%2BmyFi%2BeeAeFAwxfplzXFNKA7k6%2B%2FZAcqYvwA5MLRvK; _gat=1; BD_HOME=1; H_PS_PSSID=1452_21110_17001_24329_24393; BD_UPN=12314353; hibext_instdsigdip=1; _ga=GA1.2.398254850.1505197573; _gid=GA1.2.516595316.1505197573")
                .response().getBody());
    }


    @Test
    public void testHtmlUnit() {

    }
}
