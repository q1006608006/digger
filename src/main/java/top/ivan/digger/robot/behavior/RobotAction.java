package top.ivan.digger.robot.behavior;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/9
 */
public interface RobotAction {
    void doAction(ActionPoint point,ActionChain chain) throws Exception;
}
