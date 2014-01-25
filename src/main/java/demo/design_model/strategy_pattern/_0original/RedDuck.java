package demo.design_model.strategy_pattern._0original;

/**
 * User: yu.liuyly
 * Date: 14-1-4
 * Time: 下午11:21
 */
public class RedDuck extends Duck {
    public RedDuck() {
        fly();
        super.quack();


    }
    public void fly(){
        System.out.println("no fly !");
    }

}
