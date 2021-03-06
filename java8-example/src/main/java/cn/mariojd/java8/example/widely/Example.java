package cn.mariojd.java8.example.widely;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jared
 * @date 2018/9/19 16:45
 */
public class Example {

    private Integer[] array = {10, 3, 3, 15, 9, 23};

    private Example() {
        Arrays.stream(array).forEach(n -> System.out.print(n + " ")); // 10 3 3 15 9 23
        System.out.println();
        Arrays.stream(array).parallel().forEach(n -> System.out.print(n + " ")); // output random
        System.out.println();
        Arrays.asList(array).parallelStream().forEachOrdered(n -> System.out.print(n + " ")); // 10 3 3 15 9 23
        System.out.println();
    }

    private void map() {
        List<Integer> collect = Stream.of(array).map(n -> n * 2).collect(Collectors.toList());
        System.out.println("collect = " + collect); // [20, 6, 6, 30, 18, 46]

        collect = Stream.of(array).mapToInt(n -> n * 2).boxed().collect(Collectors.toList());
        System.out.println("collect = " + collect); // [20, 6, 6, 30, 18, 46]

        ArrayList<Long> collect1 = Stream.of(array).mapToLong(Integer::longValue).boxed().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("collect1 = " + collect1); // [10, 3, 3, 15, 9, 23]

        TreeSet<Object> collect2 = Stream.of(array).mapToDouble(Integer::doubleValue).collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println("collect2 = " + collect2); // [3.0, 9.0, 10.0, 15.0, 23.0]
    }

    private void filter() {
        Object[] objects = Stream.of(array).filter(n -> n >= 10).toArray();
        System.out.println("objects = " + Arrays.toString(objects));// [10, 15, 23]
    }

    private void sort() {
        // naturalOrder
        List<Integer> collect = Stream.of(array).sorted().collect(Collectors.toList());
        System.out.println("collect = " + collect); // [3, 3, 9, 10, 15, 23]

        // reverseOrder
        collect = Stream.of(array).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("collect = " + collect); // [23, 15, 10, 9, 3, 3]

        collect = Stream.of(array).sorted(Comparator.comparingInt(Integer::intValue).reversed()).collect(Collectors.toList());
        System.out.println("collect = " + collect); // [23, 15, 10, 9, 3, 3]
    }

    private void skip() {
        TreeSet<Integer> collect = Stream.of(array).skip(3).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("collect = " + collect); // [9, 15, 23]
    }

    private void distinct() {
        LinkedList<Integer> collect = Stream.of(array).distinct().collect(Collectors.toCollection(LinkedList::new));
        System.out.println("collect = " + collect); // [10, 3, 15, 9, 23]
    }

    private void sumAndCount() {
        int sum = Stream.of(array).mapToInt(Integer::intValue).sum();
        System.out.println("sum = " + sum); // 63

        long sum1 = Stream.of(array).mapToLong(Integer::intValue).sum();
        System.out.println("sum1 = " + sum1); // 63

        double sum2 = Stream.of(array).mapToDouble(Integer::intValue).sum();
        System.out.println("sum2 = " + sum2); // 63.0

        // array.length is equal 6
        long count = Stream.of(array).count();
        System.out.println("sumAndCount = " + count); // 6
    }

    private void limit() {
        Set<Integer> collect = Stream.of(array).limit(3).collect(Collectors.toSet());
        System.out.println("collect = " + collect); // [3, 10]
    }

    private void match() {
        boolean allMatch = Stream.of(array).allMatch(n -> n > 5);
        System.out.println("allMatch = " + allMatch); // false

        boolean anyMatch = Stream.of(array).anyMatch(n -> n > 5);
        System.out.println("anyMatch = " + anyMatch); // true

        boolean noneMatch = Stream.of(array).noneMatch(n -> n > 5);
        System.out.println("noneMatch = " + noneMatch); // false
    }

    private void find() {
        Optional<Integer> any = Stream.of(array).filter(n -> n * 2 > 10 && n * 2 < 20).findAny();
        any.ifPresent(n -> System.out.println("any = " + n)); // 9

        any = Stream.of(array).filter(n -> n < 5).findFirst();
        any.ifPresent(n -> System.out.println("any = " + n)); // 3
    }

    private void minAndMax() {
        Stream.of(array).min(Comparator.naturalOrder()).ifPresent(n -> System.out.println("n = " + n)); // 3

        Stream.of(array).max(Comparator.comparingInt(Integer::intValue)).ifPresent(n -> System.out.println("n = " + n)); // 23
    }

    private void peek() {
        LinkedHashSet<Integer> collect = Stream.of(array).peek(n -> System.out.print(n + " ")).collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println();
        System.out.println("collect = " + collect); // [10, 3, 15, 9, 23]
    }

    private void reduce() {
        Integer reduce = Stream.of(array).reduce(100, Integer::sum);
        System.out.println("reduce = " + reduce); // 163
    }

    private void flatMap() {
        List<Integer> collect = Stream.of(array, array).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println("collect = " + collect); // [10, 3, 3, 15, 9, 23, 10, 3, 3, 15, 9, 23]
    }

    private void present() {
        boolean isPresent = Stream.of(array).findAny().isPresent();
        System.out.println("isPresent = " + isPresent);
        Stream.of(array).filter(n -> n == 20).findFirst().ifPresent(n -> System.out.println("n = " + n));
    }

    private void summaryStatistics() {
        IntSummaryStatistics collect = Stream.of(array).collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(collect.getCount() + " " + collect.getSum() + " " + collect.getMin() + " " + collect.getAverage() + " " + collect.getMax());
        System.out.println("collect = " + collect); // IntSummaryStatistics{sumAndCount=6, sum=63, min=3, average=10.500000, max=23}

        IntSummaryStatistics intSummaryStatistics = Stream.of(array).mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("intSummaryStatistics = " + intSummaryStatistics); // IntSummaryStatistics{sumAndCount=6, sum=63, min=3, average=10.500000, max=23}
    }

    private void groupBy() {
        Map<Boolean, List<Integer>> collect = Stream.of(array).collect(Collectors.groupingBy(n -> n >= 10));
        System.out.println("collect = " + collect); // {false=[3, 3, 9], true=[10, 15, 23]}
    }

    public static void main(String[] args) {
        Example example = new Example();
        example.map();
        example.filter();
        example.sort();
        example.skip();
        example.distinct();
        example.sumAndCount();
        example.limit();
        example.match();
        example.find();
        example.minAndMax();
        example.peek();
        example.reduce();
        example.flatMap();
        example.present();
        example.summaryStatistics();
        example.groupBy();
    }

}
