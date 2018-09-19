package cn.mariojd.java8.example.widely;

import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jared
 * @date 2018/9/19 16:45
 */
public class TenExample {

    private void map() {
        Set<Integer> collect = Stream.of(10, 3, 5, 5, 23).map(n -> n * 2).collect(Collectors.toSet());
        System.out.println("collect = " + collect); // [20, 6, 10, 46]
    }

    private void filter() {
        Object[] array = Stream.of(10, 3, 15, 9, 23).filter(n -> n >= 10).toArray();
        System.out.println("array = " + Arrays.toString(array));// [10, 15, 23]
    }

    private void sort() {
        // naturalOrder
        List<Integer> collect = Stream.of(10, 3, 15, 9, 23).sorted().collect(Collectors.toList());
        System.out.println("collect = " + collect); // [3, 9, 10, 15, 23]
        collect = Stream.of(10, 3, 15, 9, 23).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("collect = " + collect); // [23, 15, 10, 9, 3]
    }

    private void skip() {
        TreeSet<Integer> collect = Stream.of(10, 3, 15, 9, 23).skip(3).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("collect = " + collect); // [9, 23]
    }

    private void distinct() {
        LinkedList<Integer> collect = Stream.of(9, 3, 3, 9, 23).distinct().collect(Collectors.toCollection(LinkedList::new));
        System.out.println("collect = " + collect); // [9, 3, 23]
    }

    private void count() {
        long count = Stream.of(9, 3, 3, 9, 23).count();
        System.out.println("count = " + count); // 5
    }

    private void limit() {
        List<Integer> collect = Stream.of(9, 3, 3, 9, 23).limit(2).collect(Collectors.toList());
        System.out.println("collect = " + collect); // [9, 3]
    }

    private void match() {
        boolean allMatch = Stream.of(9, 3, 3, 9, 23).allMatch(n -> n > 5);
        System.out.println("allMatch = " + allMatch); // false
        boolean anyMatch = Stream.of(9, 3, 3, 9, 23).anyMatch(n -> n > 5);
        System.out.println("anyMatch = " + anyMatch); // true
        boolean noneMatch = Stream.of(9, 3, 3, 9, 23).noneMatch(n -> n > 5);
        System.out.println("noneMatch = " + noneMatch); // false
    }

    private void find() {
        Optional<Integer> any = Stream.of(9, 3, 3, 9, 23).filter(n -> n * 2 > 10).findAny();
        any.ifPresent(n -> System.out.println("any = " + n)); // 9 or 23
        any = Stream.of(9, 3, 3, 9, 23).filter(n -> n * 2 > 10).findFirst();
        any.ifPresent(n -> System.out.println("any = " + n)); // 9
    }

    private void peek() {
    }

    private void minOrMax() {
        Stream.of(9, 3, 3, 8, 23).min(Comparator.naturalOrder()).ifPresent(n -> System.out.println("n = " + n)); // 3
        Stream.of(9, 3, 3, 9, 23).max(Comparator.reverseOrder()).ifPresent(n -> System.out.println("n = " + n)); // 23
    }

    public static void main(String[] args) {
        TenExample tenExample = new TenExample();
        tenExample.map();
        tenExample.filter();
        tenExample.sort();
        tenExample.skip();
        tenExample.distinct();
        tenExample.count();
        tenExample.limit();
        tenExample.match();
        tenExample.find();
        tenExample.peek();
        tenExample.minOrMax();
    }

}
