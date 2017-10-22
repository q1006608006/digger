import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import top.ivan.digger.util.CrawlerUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/16
 */
public class TimeTest {
    private static Map<String, String[]> arTimeMap = null;
    private static final String[] number = new String[]{"٠", "١", "٢", "٣", "٤", "٥", "٦", "٧", "٨", "٩"};
    private static Pattern facebookVideoIdPattern = Pattern.compile("/videos/(\\d+)");

    // FIXME: (wangyixin,2017/9/16,15:29)
    // 用于反向映射arTimeMap(用于翻译阿语："星期"、"月份"、"上/下午")
    private static Map<String, String> tempMap = new HashMap<>();

    static {

        arTimeMap = new HashMap<>();
        final String[] January = new String[]{"كانون الثاني", "طوبة", "طوبا", "محرم", "يناير"};
        final String[] February = new String[]{"فبراير", "شباط", "أمشير", "امشير", "آمشير", "إمشير", "صفر"};
        final String[] March = new String[]{"مارس", "آذار", "أذار", "اذار", "إذار", "برمهات", "برامهات", "برماهات", "ربيع الأول", "ربيع الاول", "ربيع الآول"};
        final String[] April = new String[]{"أبريل", "ابريل", "إبريل", "آبريل", "نيسان", "برمودة", "بارمودة", "برامودة", "برمودا", "ربيع الثانى", "ربيع الثاني"};
        final String[] May = new String[]{"مايو", "أيار", "ايار", "آيار", "إيار", "بشنس", "بشانس", "جمادى الأول", "جمادى الاول", "جمادى الآول", "جمادى", "ماي"};
        final String[] June = new String[]{"يونيو", "يونية", "يونيه", "حزيران", "حريزان", "بؤونة", "بؤنة", "بؤونه", "بؤنه", "جمادى الثانى", "جمادى الثاني"};
        final String[] July = new String[]{"يوليو", "يولية", "يوليه", "تموز", "أبيب", "ابيب", "آبيب", "إبيب", "رجب"};
        final String[] August = new String[]{"أغسطس", "اغسطس", "إغسطس", "آغسطس", "آب", "أب", "اب", "إب", "مسرى", "مسري", "مسرا", "شعبان"};
        final String[] September = new String[]{"سبتمبر", "أيلول", "ايلول", "آيلول", "إيلول", "توت", "رمضان"};
        final String[] October = new String[]{"أكتوبر", "اكتوبر", "إكتوبر", "آكتوبر", "تشرين الأول", "تشرين الاول", "تشرين الآول", "بابة", "بابا", "بابه", "شوال"};
        final String[] November = new String[]{"نوفمبر", "تشرين الثاني", "هاتور", "ذو القعدة", "ذى القعدة", "ذى القعده", "ذو القعده"};
        final String[] December = new String[]{"ديسمبر", "كانون الأول", "كانون الاول", "كانون الآول", "كياهك", "كيهك", "ذو الحجة", "ذي الحجة", "ذى الحجه", "ذو الحجه"};
        final String[] Monday = new String[]{"الاثنين", "الإثنين", "الآثنين"};
        final String[] Tuesday = new String[]{"الثلاثاء"};
        final String[] Wednesday = new String[]{"الأربعاء", "الاربعاء", "الآربعاء"};
        final String[] Thursday = new String[]{"الخميس"};
        final String[] Friday = new String[]{"الجمعة", "الجمعه"};
        final String[] Saturday = new String[]{"السبت"};
        final String[] Sunday = new String[]{"الاحد", "الأحد", "الآحد"};
//        final String[] number = new String[]{"٠", "١", "٢", "٣", "٤", "٥", "٦", "٧", "٨", "٩"};
        final String[] AM = new String[]{"ص"};
        final String[] PM = new String[]{"م"};

        //٠ ١ ٢ ٣ ٤ ٥ ٦ ٧ ٨ ٩
        arTimeMap.put("01", January);
        arTimeMap.put("02", February);
        arTimeMap.put("03", March);
        arTimeMap.put("04", April);
        arTimeMap.put("05", May);
        arTimeMap.put("06", June);
        arTimeMap.put("07", July);
        arTimeMap.put("08", August);
        arTimeMap.put("09", September);
        arTimeMap.put("10", October);
        arTimeMap.put("11", November);
        arTimeMap.put("12", December);
        arTimeMap.put("Monday", Monday);
        arTimeMap.put("Tuesday", Tuesday);
        arTimeMap.put("Wednesday", Wednesday);
        arTimeMap.put("Thursday", Thursday);
        arTimeMap.put("Friday", Friday);
        arTimeMap.put("Saturday", Saturday);
        arTimeMap.put("Sunday", Sunday);
//        arTimeMap.put("number", number);
        arTimeMap.put("AM", AM);
        arTimeMap.put("PM", PM);

        // FIXME: (wangyixin,2017/9/16,15:32)
        // 初始化tempMap
        for (Map.Entry<String, String[]> ar : arTimeMap.entrySet()) {
            String[] ars = ar.getValue();
            for (String arStr : ars) {
                tempMap.put(arStr, ar.getKey());
            }
        }

    }

    public static class TimeNode {
        private Map<String, TimeNode> childNodes;

        private String value;
        private int length = 0;

        public TimeNode getParent() {
            return parent;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private void setParent(TimeNode parent) {
            this.parent = parent;
        }

        private TimeNode parent;

        public TimeNode getNode(String key) {
            if (null == childNodes) {
                return null;
            }
            return childNodes.get(key);
        }

        public int getLength() {
            return length;
        }

        private void setLength(int length) {
            this.length = length;
        }

        public void putNode(String key, TimeNode child) {
            if (null == childNodes) {
                childNodes = new HashMap<>();
            }
            if (childNodes.get(key) == null) {
                childNodes.put(key, child);
            }
            child.setLength(length + 1);
            child.setParent(this);
        }

        public static String filterByDictionary(String src) {
            String[] parts = src.split("\\s+");
            TimeNode tempTimeNode;
            Stack<String> resultStack = new Stack<>();
            for (int i = 0; i < parts.length; ) {
                tempTimeNode = getNode(parts, i);
                if (tempTimeNode != null) {
                    resultStack.push(tempTimeNode.getValue());
                    i += tempTimeNode.length;
                } else {
                    resultStack.push(parts[i]);
                    i++;
                }
            }
            StringBuilder resultBuilder = new StringBuilder(resultStack.pop());
            int size = resultStack.size();
            for (int i = 0; i < size; i++) {
                resultBuilder.append(' ').append(resultStack.pop());
            }
            return resultBuilder.toString();
        }

        private static TimeNode getNode(String[] src, int offset) {
            TimeNode current = timeRoot;
            for (int i = offset; i < src.length; i++) {
                if (null != current.getNode(src[i])) {
                    current = current.getNode(src[i]);
                } else {
                    break;
                }
            }
            if (current.equals(timeRoot)) {
                return null;
            }
            return current;
        }
    }

    private static TimeNode timeRoot;

    static {
        TimeNode root = new TimeNode();
        TimeNode currentTimeNode = null;
        for (Map.Entry<String, String> entry : tempMap.entrySet()) {
            String phrase = entry.getKey();
            String meaning = entry.getValue();
            String[] words = phrase.split("\\s");

            currentTimeNode = root;

            for (String word : words) {
                TimeNode cTimeNode = new TimeNode();
                TimeNode parent = currentTimeNode;
                currentTimeNode = currentTimeNode.getNode(word);
                if (null == currentTimeNode) {
                    parent.putNode(word, cTimeNode);
                    currentTimeNode = cTimeNode;
                }
            }
            currentTimeNode.setValue(meaning);
        }

        timeRoot = root;
    }


    public static void main(String[] args) throws ParseException {
//        String test = "    25  ذو الحجه  1438   ";
//        test = test.trim();
////        System.out.println(test.split("\\s+").length);
//        System.out.println(TimeNode.filterByDictionary(test));


/*        for (String key : tempMap.keySet()) {
            String test = key;
            System.out.println(filterByDictionary(test));
        }*/
        String s = null + "a";

        Locale locale = Locale.ENGLISH;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a", locale);

        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0300"));

        Date date = sdf.parse("26-12-1438 07:18 pm");
        System.out.println(date.getTime() / 1000);

    }

    @Test
    public void newTest() throws IOException {
        Connection connect = Jsoup.connect("http://www.baidu.com");
        System.out.println(connect.get().outerHtml());
    }

    @Test
    public void newTest1() throws IOException {
        CrawlerUtil.HttpResponse response = CrawlerUtil.request("http://www.baidu.com").response();
//        response.setCharset("gbk");
        System.out.println(response.getBody());
    }

    @Test
    public void testSA() throws Exception {

    }


}
