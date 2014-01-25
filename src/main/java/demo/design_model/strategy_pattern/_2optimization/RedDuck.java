package demo.design_model.strategy_pattern._2optimization;

/**
 * User: yu.liuyly
 * Date: 14-1-4
 * Time: 下午11:21
 */
public class RedDuck extends Duck {
    public RedDuck(){
        flyBehavior = new FlyWithWings();
        quackBehavior = new QuackZIZI();
        flyBehavior.fly();
        quackBehavior.quack();
    }

}
