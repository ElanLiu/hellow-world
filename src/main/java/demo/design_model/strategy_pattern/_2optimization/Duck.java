package demo.design_model.strategy_pattern._2optimization;

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

    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior = quackBehavior;
    }
}
