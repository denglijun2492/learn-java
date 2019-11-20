package denglj.learn.es;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
public class MyTokenizer extends Tokenizer {
    private final static String PUNCTION = " -()/";
    private final StringBuilder buffer = new StringBuilder();
    private int suffixOffset;
    private int tokenStart = 0, tokenEnd = 0;
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);


    public boolean incrementToken() throws IOException {
        clearAttributes();
        buffer.setLength(0);
        int ci;
        char ch;
        tokenStart = tokenEnd;

        ci = input.read();
        if(ci>64&&ci<91){
            ci=ci+32;
        }
        ch = (char) ci;
        while (true) {
            if (ci == -1){
                if (buffer.length() == 0)
                    return false;
                else {
                    termAtt.setEmpty().append(buffer);
                    offsetAtt.setOffset(correctOffset(tokenStart),
                            correctOffset(tokenEnd));
                    return true;
                }
            }
            else if (PUNCTION.indexOf(ch) != -1) {
                //buffer.append(ch);
                tokenEnd++;
                if(buffer.length()>0){
                    termAtt.setEmpty().append(buffer);
                    offsetAtt.setOffset(correctOffset(tokenStart),
                            correctOffset(tokenEnd));
                    return true;
                }else
                {
                    ci = input.read();
                    if(ci>64&&ci<91){
                        ci=ci+32;
                    }
                    ch = (char) ci;
                }
            } else {
                buffer.append(ch);
                tokenEnd++;
                ci = input.read();
                if(ci>64&&ci<91){
                    ci=ci+32;
                }
                ch = (char) ci;
            }
        }
    }

    @Override
    public final void end() {
        final int finalOffset = correctOffset(suffixOffset);
        this.offsetAtt.setOffset(finalOffset, finalOffset);
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        tokenStart = tokenEnd = 0;
    }
}
