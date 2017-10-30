package top.ivan.crawler.core.focus;

import com.google.gson.JsonElement;
import top.ivan.crawler.ExportFocusHandle;
import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.UnSupportFocusException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/25
 */
public class TestFocus implements Focus,ExportFocusHandle {


    private FocusManager manager;
    @Override
    public String peek(String src, String target, String key) throws Exception {

        KeyOrder keyOrder = KeyOrder.getKeyOrder(key);
        String srcType = keyOrder.getOriginal();
        if(keyOrder.pass(peekKey(src,srcType),keyOrder.getOrder())) {
            Focus focus = ExportFocusHandle.getExportFocus(target,manager);
            String exportTarget = ExportFocusHandle.getExportTarget(target);
            return focus.peek(src,exportTarget,keyOrder.getRealKey());
        }
        return src;
    }

    @Override
    public void setManager(FocusManager manager) {
        this.manager = manager;
    }

    public static String nullValue(Object src) {
        if (null == src || "".equals(src.toString().trim())) {
            return null;
        }
        return src.toString();
    }
    public static String notNullValue(Object src) {

        if(null == src) {
            return "";
        } else {
            return src.toString();
        }
    }

    private String peekKey(String src,String srcType) throws Exception {
        if(srcType.matches("^#[\\s\\S]*#$")) {
            return JsonFocus.takeJsonValue(src,srcType.replace("#",""));
        } else if(srcType.matches("^\\$[\\s\\S]*\\$$")){
            return srcType.replace("$","");
        } else {
            return src;
        }
    }

    private static class KeyOrder {
        static final String REGEX = "^(#\\S+?#|\\$\\S+?\\$|\\$|)(\\S*?)\\[([\\s\\S]*)\\]$";
        private static Lexicon<OrderInvoker> lexicon = new Lexicon<>(null,null);

        private String original;
        private String order;
        private String key;

        KeyOrder(String key) throws UnSupportFocusException {
            if(!isKeyOrder(key)) {
                throw new UnSupportFocusException(String.format("can not parse key order with:\"%s\"",key));
            }
            this.original = key.replaceAll(REGEX,"$1");
            this.order = key.replaceAll(REGEX,"$2");
            this.key = key.replaceAll(REGEX,"$3");
        }

        String getRealKey() {
            return key;
        }

        String getOrder() {
            return order;
        }

        String getOriginal() {
            return original;
        }

        static KeyOrder getKeyOrder(String keyOrder) throws UnSupportFocusException {
            return new KeyOrder(keyOrder);
        }

        static boolean isKeyOrder(String keyOrder) {
            return keyOrder.matches(REGEX);
        }

        boolean pass(String original,String order) throws UnSupportFocusException {
            Lexicon<OrderInvoker> node = lexicon.get(order);
            if(null == node) {
                throw new UnSupportFocusException(String.format("no suitable test order with:\"%s\"" ,order));
            }
            OrderInvoker invoker = node.getValue();
            String key = order.replaceFirst(order,node.getPath());
            return invoker.test(original,key);
        }

        static void registerOrder(String order,OrderInvoker invoker) {
            lexicon.put(order,invoker);
        }

        private interface OrderInvoker{
            boolean test(String src,String key);
        }

        private static class Lexicon<T> {
            Character c;
            Lexicon parent;
            T value;
            Map<Character,Lexicon<T>> next;

            Lexicon(Lexicon parent,Character c) {
                this.c = c;
                this.parent = parent;
            }

            void put(String cs,T obj) {
                if(next == null) {
                    next = new LinkedHashMap<>();
                }
                if(cs.length() > 0) {
                    Lexicon<T> lex = next.get(cs.charAt(0));
                    if(lex == null) {
                        lex = new Lexicon<T>(this,cs.charAt(0));
                        next.put(cs.charAt(0),lex);
                    }
                    lex.put(cs.substring(1),obj);
                } else {
                    value = obj;
                }
            }

            Lexicon<T> get(String cs) {
                if(cs != null && cs.length() > 0 && next != null) {
                    Lexicon<T> lex = next.get(cs.charAt(0));
                    if(lex != null) {
                        return lex.get(cs.substring(1));
                    }
                }
                return null == value ? null : this;
            }

            String getPath() {
                if(parent != null) {
                    return parent.getPath() + notNullValue(c);
                }
                return notNullValue(c);
            }

            T getValue() {
                return value;
            }

        }


    }

    public static void main(String[] args) throws Exception {
//        System.out.println("#1#=1[a[][]".replaceAll(KeyOrder.REGEX,"$3"));
//        KeyOrder.Lexicon<String> lexicon = new KeyOrder.Lexicon<>(null,null);
//        lexicon.put("abc","heiehie");
//        lexicon.put("bbc","cnm");
//        System.out.println(lexicon.get("abcasfsdf").getPath());
//        System.out.println(lexicon.get("bbc").getValue());
        Focus focus = new TestFocus();
        focus.peek("[1,2,3]","regex[1]","#[2]#=b[123]");
    }
}
