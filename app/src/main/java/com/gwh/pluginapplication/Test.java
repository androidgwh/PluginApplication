package com.gwh.pluginapplication;

import com.gwh.pluginapplication.kotlin.One;
import com.gwh.pluginapplication.kotlin.Two;
import com.gwh.pluginapplication.kotlin.TwoSingle;

/**
 * Created by Guo Wenhui 2020/10/21
 **/
public class Test {
    public static void main(String[] args) {
        testKotlin();
    }

    /**
     * 测试kotlin
     */
    private static void testKotlin() {
        One instance = One.INSTANCE;
        One instance1 = One.INSTANCE;
        System.out.println("is " + (instance == instance1));
        One.INSTANCE.print();

        String name = Two.name;
        System.out.println("获取到伴生对象的属性 "+name);
        Two.changeMarks("B");
        String marks=Two.printMarks();
        System.out.println(marks);

        //伴生对象创建单例
        TwoSingle.Companion companion = TwoSingle.Companion;
        TwoSingle.Companion companion1 = TwoSingle.Companion;
        System.out.println(companion==companion1);
    }
}
