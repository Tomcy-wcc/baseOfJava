package java8;

/**
 * @Description 接口内允许添加默认实现的方法
 *
 * 通过 default 关键字对接口中定义的抽象方法提供一个默认的实现。
 *
 * @auther wcc
 * @create 2019-10-14 9:46
 */


public class DefaultOfInterface{
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };
        double calculate = formula.calculate(100);
        System.out.println(calculate);
    }
}


interface Formula  {

    //计算
    double calculate(int a);

    //求平方根
    default double sqrt(int a){
        return Math.sqrt(a);
    }

}
