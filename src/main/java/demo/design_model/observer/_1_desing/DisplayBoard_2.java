package demo.design_model.observer._1_desing;

/**
 * User: yu.liuyly
 * Date: 14-1-5
 * Time: 下午5:32
 */
public class DisplayBoard_2 implements DisplayBoard{
    @Override
    public void update(float temperature, float humidity, float pressure){
        System.out.println("DisplayBorad_2 has updated");
    }
}
