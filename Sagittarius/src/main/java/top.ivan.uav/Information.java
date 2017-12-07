package top.ivan.uav;

import java.net.Socket;

/**
 * description
 *
 * @author Administrator
 * @date 2017/12/7
 */
public class Information {
    private String msg;
    private Socket socket;

    private Information(String msg,Socket socket) {
        this.msg = msg;
        this.socket = socket;
    }

    public String getMsg() {
        return msg;
    }

    public Socket getSocket() {
        return socket;
    }
}
