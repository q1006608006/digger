package top.ivan.uav;

import java.io.IOException;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/12/7
 */
public interface Wormhole {
    Information takeInformation() throws InterruptedException,IOException;
    List<Information> takeInformations() throws InterruptedException,IOException;
    boolean sendInformation(Information info) throws IOException;
}
