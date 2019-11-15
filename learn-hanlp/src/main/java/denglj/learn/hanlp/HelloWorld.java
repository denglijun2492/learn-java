package denglj.learn.hanlp;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {

        //System.out.println(HanLP.segment("我的马上不去..."));

        //标准分词
//        List<Term> termList = StandardTokenizer.segment("商品和服务");
//        System.out.println(termList);

        //NLP分词
        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
    }
}
