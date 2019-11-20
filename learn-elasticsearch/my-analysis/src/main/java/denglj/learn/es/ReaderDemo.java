package denglj.learn.es;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/19 17:38
 **/
public class ReaderDemo {

    public static void main(String[] args) throws IOException {
        MyAnalyzer analyzer = new MyAnalyzer();
        TokenStream ts = analyzer.tokenStream("text", "我爱 北京 天安门");
        CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while (ts.incrementToken()) {
            System.out.println(term.toString());
        }
        ts.end();
        ts.close();
    }

    public static void main2(String[] args) throws IOException {
        String s = "等的速度快的事务SDKIE杀跌aaa";
        StringReader reader = new StringReader(s);
        reader.mark(1);
        System.out.println(reader.read());
        System.out.println(reader.read());
        System.out.println(reader.read());
        System.out.println(reader.read());
        System.out.println(reader.read());
        System.out.println(reader.read());
        System.out.println(reader.read());
        System.out.println(reader.read());
        reader.reset();
        System.out.println("----------------回来重复读取-----------------");
        System.out.println(reader.read());
        System.out.println(reader.read());
    }

    public static void main1(String[] args) throws IOException {
        String s = "等的速度快的事务SDKIE杀跌aaa";
        StringReader reader = new StringReader(s);
        char[] arr = new char[5];
        int ch = 0;

        while ((reader.read(arr, 0, 5))!=-1){
            System.out.println(arr);
        };

        System.out.println(arr);

        while ((ch = reader.read()) != -1){
            System.out.println((char)ch);
        }
    }
}
