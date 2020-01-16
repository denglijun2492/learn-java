package denglj.demo.aop.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 16:05
 **/
public class CarFlowChain {

    private final List<BaseProduce> steps;

    private int index;

    public CarFlowChain(){
        steps = new ArrayList<>();
        index = 0;
    }

    public void addStep(BaseProduce step){
        steps.add(step);
    }

    public void proceed(){
        if(index < steps.size()){
            BaseProduce step = steps.get(index++);
            step.execute(this);
        }
    }
}
