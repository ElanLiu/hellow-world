package demo.design_model.strategy_pattern._2optimization;

/**
 * User: yu.liuyly
 * Date: 14-1-4
 * Time: 下午11:17
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly with wings!");
    }
}
