package top.ivan.crawler;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/25
 */
public interface ExportFocusHandle {
    void setManager(FocusManager manager);

    String EXPORT_MODULE = "^([\\s\\S]*?)\\[([\\s\\S]*)\\]$";

    static boolean isExportTarget(String target) {
        return target.matches(EXPORT_MODULE);
    }

    static Focus getExportFocus(String target,FocusManager manager) {
        if(null != manager) {
            return manager.getFocus(target.replaceAll(EXPORT_MODULE,"$1"));
        }
        return null;
    }

    static String getExportTarget(String srcTarget) {
        return srcTarget.replaceAll(EXPORT_MODULE,"$2");
    }
}
