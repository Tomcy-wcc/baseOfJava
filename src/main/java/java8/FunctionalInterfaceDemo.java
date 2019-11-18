package java8;

import java.util.function.Supplier;

/**
 * @Description FunctionalInterface的使用
 *
 * 只要接口中仅仅包含一个抽象方法，我们就可以将其改写为 Lambda 表达式。
 * 为了保证一个接口明确的被定义为一个函数式接口（Functional Interface）
 * 我们需要为该接口添加注解：@FunctionalInterface。
 * 这样，一旦你添加了第二个抽象方法，编译器会立刻抛出错误提示。
 *
 * @auther wcc
 * @create 2019-10-14 10:26
 */
public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
        //1、Lambda 表示式
        //Converter<String, Integer> converter = (from) -> Integer.valueOf(from);

        //2、通过 :: 关键字来引用类的方法或构造器
        //2.1、如何引用静态方法
        Converter<String, Integer> converter = Integer::valueOf;
        Integer integer = converter.convert("123");
        System.out.println(integer);

        //生产者
        Supplier<Object> objectSupplier = Object::new;
        Object o = objectSupplier.get();
        System.out.println(o);

        //消费者
    }

}


@FunctionalInterface
interface Converter<F, T>{
    T convert(F from);
}
