package top.ivan.crawler.griddle;

import top.ivan.crawler.FocusManager;
import top.ivan.crawler.focus.RegexFocus;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class DefaultGriddleAisle extends AbstractGriddleAisle {

    public DefaultGriddleAisle(AisleBean aisle, FocusManager manager) {
        super(aisle, manager);
    }

    @Override
    public String pass(String src, Map<String, String> peeMap) throws Exception {
        ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();
        List<FocusWallet> focusWallets = getFocusWallets();
        String target,key,ret = src;
        FocusWallet wallet;
        for(int i=0;i < focusWallets.size();i ++) {
            wallet = focusWallets.get(i);
            target = wallet.getTarget();
            key = wallet.getKey();
            key = RegexFocus.peeKey(key,peeMap);
            ret = wallet.getFocus().peek(ret,target,key);
        }
        return ret;
    }

}
