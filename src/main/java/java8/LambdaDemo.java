package java8;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description Lambda表达式的使用
 * @auther wcc
 * @create 2019-10-14 10:11
 */
public class LambdaDemo {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        //1、传统采用匿名内部类实现
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        //2、Lambda表达式
        /*Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });*/

        //3、可以省略参数类型，Java 编译器能够根据类型推断机制判断出参数类型
        /*Collections.sort(names, (a, b) -> a.compareTo(b));*/

        names.sort((a, b) -> a.compareTo(b));

        System.out.println(names);

        //parallelStream：并行流
        //stream：串行流
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);

        //lists.parallelStream().forEach((i)->System.out.print(i));
        lists.stream().forEach(i->System.out.print(i));
        System.out.println();
        lists.stream().filter(i -> i > 3).forEach(i -> System.out.print(i));


        String substring = "aaaaaaa".substring(1, 10);
        System.out.println(substring);
    }
}


