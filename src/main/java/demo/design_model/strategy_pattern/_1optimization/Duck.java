package demo.design_model.strategy_pattern._1optimization;

/**
 * User: yu.liuyly
 * Date: 14-1-4
 * Time: 下午11:09
 */
public abstract class Duck {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

                 public void swim(){
                     System.out.println("I can swim");
                 }
    public void performFly(){
        flyBehavior.fly();
    }

    public  void performQuack(){
        quackBehavior.quack();
    }
}
