package demo.design_model.strategy_pattern._1optimization;

/**
 * User: yu.liuyly
 * Date: 14-1-4
 * Time: 下午11:27
 *
 * 由于fly和quack方法会因为子类不同而改变，所以该优化中将改变的和不改变的进行了分离。
 *
 * 1  duck父类改为abstract
 * 2 fly和quack委托给其他接口来做。
 */
public class Test {
    public static void main(String[] args){
        Duck redDuck = new RedDuck();
    }
}
