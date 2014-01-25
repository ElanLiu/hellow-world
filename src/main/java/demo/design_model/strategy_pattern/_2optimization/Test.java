package demo.design_model.strategy_pattern._2optimization;

/**
 * User: yu.liuyly
 * Date: 14-1-5
 * Time: 上午10:13
 *
 * 本次优化：因为子类每次都要实例化父类中的引用。而不能改变，所以这里优化这个点
 *
 * 有了父类中的set方法，就可以在子类一次实例化后，多次修改父类的引用的实例
 */
public class Test {
    public static void main(String[] args) {
Duck duck = new RedDuck();
        duck.setFlyBehavior(null);
        duck.performFly();

    }
}
