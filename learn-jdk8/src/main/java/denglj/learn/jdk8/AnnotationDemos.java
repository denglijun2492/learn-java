package denglj.learn.jdk8;

import org.junit.Test;

import java.lang.annotation.*;

/**
 * @Title 支持重复注解的特性
 * @Description: TODO
 *
 * @Author denglj
 * @Date 2019/11/20 17:06
 **/
public class AnnotationDemos {
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @interface Filters {
        Filter[] value();
    }

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )                //Filters是存放Filter注解的容器
    @interface Filter {
        String value();
    };

    @Filter( "filter1" )
    @Filter( "filter2" )
    interface Filterable {
    }

    @Filters({
            @Filter("aaaa"),
            @Filter("bbbb")
    })

    interface Filterable2 {
    }

    @Test
    public void demo1(){
        //jdk8专门提供了getAnnotationsByType，用于获取重复注解
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() );
        }

        for (Filters filters : Filterable2.class.getAnnotationsByType(Filters.class)) {
            for (Filter filter : filters.value()) {
                System.out.println(filter.value());
            }
        }
    }
}
