package cs1302;
import java.util.Comparator;
import java.util.function.BinaryOperator;

import cs1302.LinkedGenList;
import cs1302.genlistadt.GenList;

//interface Test {
//    static <R, T>GenList<R> demoMap(LinkedGenList<T> t,Function<T,R> f) {
//        return t.map(f);
//    }
//    static <T> T demoReduce(LinkedGenList<T> t,T start, BinaryOperator<T> f) {
//        return t.reduce(start,f);
//    }
//    static <T> GenList<T> demoFilter(LinkedGenList<T> t,Predicate<T> p) {
//        return t.filter(p);
//    }
//    static <T> T demoMin(LinkedGenList<T> t,Comparator<T> c) {
//        return t.min(c);
//    }
//    static <T> boolean demoAllMatch(LinkedGenList<T> t,Predicate<T> p) {
//        return t.allMatch(p);
//    }
//}

public class LinkedGenListTest implements Test{
    public static void main(String []args) {

        LinkedGenList<Double> first = new LinkedGenList<>();
        first.add(1.4);
        first.add(2.7);
        first.add(3.6);
        first.add(1.3);
        
        LinkedGenList<Integer> two = new LinkedGenList<>();
        two.add(1);
        two.add(2);
        two.add(3);
        two.add(0);
        
        
        GenList<Integer> t  = Test.demoMap(two, f -> {
        	return f + 5;
        });
        GenList<Double> g  = Test.demoMap(first, f -> {
        	return f * 1.3;
        });
        BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;
        Integer tm = 5;
        Integer rm = Test.demoReduce(two, tm, adder);
        Double tr = 2.4;
        BinaryOperator<Double> red = (n3, n4) -> n3 + n4;
        Double rr = Test.demoReduce(first, tr, red);
        GenList<Integer> tg =  Test.demoFilter(two, p -> (p > 1));
        GenList<Double> rg =  Test.demoFilter(first, p -> (p > 1.3));
        Comparator<Integer> cmp = (i1, i2) -> Integer.compare(i1,i2);
        Integer res = Test.demoMin(two, cmp);
        Comparator<Double> cmp2 = (i3, i4) -> Double.compare(i3,i4);
        Double re2 = Test.demoMin(first, cmp2);
        boolean doub = Test.demoAllMatch(first, p -> (p < 3.7));
        boolean integ = Test.demoAllMatch(first, p -> (p > 2));
        System.out.println(doub + " " + integ);
        System.out.println(re2 + " " + res);
        System.out.println(rg.get(0) + " " + tg.get(0));
        System.out.println(rr + " " + rm);
        System.out.println(g.get(0) + " " + t.get(0));
        
        LinkedGenList<Double> thr = new LinkedGenList<>();
        thr.add(3.3);
        thr.add(first);
        
        System.out.println(thr.size());
        
        
        
  

    }

}
