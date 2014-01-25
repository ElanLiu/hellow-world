package demo.design_model.strategy_pattern._0original;

/**
 * User: yu.liuyly
 * Date: 14-1-4
 * Time: 下午11:27
 *
 * 原始需求：
 * 一个duck父类，提供fly，quack，swim方法，其中fly和quack方法可能会因为duck的子类不同而有区别
 *
 * 子类redduck覆盖了fly的方法。
 */
public class Test {
    public static void main(String[] args){
        demo.design_model.strategy_pattern._0original.RedDuck redDuck = new RedDuck();
    }
}
