package cs1302;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import cs1302.LinkedGenList;
import cs1302.genlistadt.GenList;

interface Test {
    static <R, T>GenList<R> demoMap(LinkedGenList<T> t,Function<T,R> f) {
        return t.map(f);
    }
    static <T> T demoReduce(LinkedGenList<T> t,T start, BinaryOperator<T> f) {
        return t.reduce(start,f);
    }
    static <T> GenList<T> demoFilter(LinkedGenList<T> t,Predicate<T> p) {
        return t.filter(p);
    }
    static <T> T demoMin(LinkedGenList<T> t,Comparator<T> c) {
        return t.min(c);
    }
    static <T> boolean demoAllMatch(LinkedGenList<T> t,Predicate<T> p) {
        return t.allMatch(p);
    }
}