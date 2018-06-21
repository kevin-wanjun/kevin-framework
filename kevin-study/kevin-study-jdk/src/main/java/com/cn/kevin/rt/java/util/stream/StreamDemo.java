package com.cn.kevin.rt.java.util.stream;

import com.alibaba.fastjson.JSON;
import com.cn.kevin.rt.java.util.stream.bean.IdentityCard;
import com.cn.kevin.rt.java.util.stream.bean.InitData;
import com.cn.kevin.rt.java.util.stream.bean.Student;
import com.cn.kevin.rt.java.util.stream.bean.StudentClass;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 聚合操作是Java 8针对集合类，使编程更为便利的方式，可以与Lambda表达式一起使用，达到更加简洁的目的。
 * 前面例子中，对聚合操作的使用可以归结为3个部分：
 * 1.创建Stream:通过stream()方法，取得集合对象的数据集。
 * 2.Intermediate:通过一系列中间（Intermediate）方法，对数据集进行过滤、检索等数据集的再次处理。
 * 如上例中，使用filter()方法来对数据集进行过滤。
 * 3.Terminal通过最终（terminal）方法完成对数据集中元素的处理。如上例中，使用forEach()完成对过滤后元素的打印。
 *
 * 在一次聚合操作中，可以有多个Intermediate，但是有且只有一个Terminal。
 * 也就是说，在对一个Stream可以进行多次转换操作，并不是每次都对Stream的每个元素执行转换。
 * 并不像for循环中，循环N次，其时间复杂度就是N。
 * 转换操作是lazy(惰性求值)的，只有在Terminal操作执行时，才会一次性执行。
 * 可以这么认为，Stream 里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，
 * 在 Terminal 操作的时候循环 Stream 对应的集合，然后对每个元素执行所有的函数。
 *
 * 刚才提到的Stream的操作有Intermediate、Terminal和Short-circuiting：
 * Intermediate：map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered
 * Terminal：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator
 * Short-circuiting：anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
 *
 * 惰性求值和及早求值方法:
 * 如何判断一个操作是惰性求值还是及早求值，其实很简单，只需要看其返回值即可：如果返回值是Stream，那么就是惰性求值；
 * 如果返回值不是Stream或者是void，那么就是及早求值。上面的示例中，只是包含两步：一个惰性求值-filter和一个及早求值。
 *
 * @author wj
 * @date 2018-04-27
 *
 */
public class StreamDemo {

    static List<Student> data = InitData.DATA;

    public static void main(String[] args) {
        System.out.println("=======groupingBy==========");
        Stream<Person> stream = Stream.of(new Person("1", "aa", "12"), new Person("1", "bb", "13"), new Person("3", "cc", "14"));

        Function<Person,String> f1 = person -> person.id;
        Map<String,List<Person>> map = stream.collect(Collectors.groupingBy(f1));

        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry);
        }

        System.out.println("--------------------------------------------------------");

        //groupingBy
        Map<String, List<Person>> tempMap = Stream.of(new Person("1", "aa", "12"), new Person("1", "bb", "13"), new Person("3", "cc", "14"))
                .collect(Collectors.groupingBy(x -> x.id));
        System.out.println(tempMap);
        for (String s : tempMap.keySet()) {
            tempMap.get(s).stream().forEach(x -> System.out.println(x));
        }

        Optional<Person> optional = null;

        Map<Boolean, List<Integer>> collectGroup = Stream.of(1, 2, 3, 4)
                .collect(Collectors.groupingBy(it -> it > 3));
        System.out.println("collectGroup : " + collectGroup);
    }

    //**************************************************静态工厂方法*************************************************
    /**
     * of方法，其生成的Stream是有限长度的，
     * Stream的长度为其内的元素个数。
     * public static<T> Stream<T> of(T... values)
     */
    public static void of(){
        //of(T... values)：返回含有多个T元素的Stream
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        integerStream.forEach(e -> System.out.println(e));
        //of(T t)：返回含有一个T元素的Stream
        Stream<String> stringStream = Stream.of("A");
        stringStream.forEach(e -> System.out.println(e));
    }

    /**
     * generator方法，返回一个无限长度的Stream,其元素由Supplier接口的提供。
     * 在Supplier是一个函数接口，只封装了一个get()方法，其用来返回任何泛型的值，
     * 该结果在不同的时间内，返回的可能相同也可能不相同，没有特殊的要求。
     * public static<T> Stream<T> generate(Supplier<T> s)
     */
    public static void generator(){
        /*
         * generate(Supplier<T> s)：返回一个无限长度的Stream
         * 1.这种情形通常用于随机数、常量的 Stream，或者需要前后元素间维持着某种状态信息的 Stream。
         * 2.把 Supplier 实例传递给 Stream.generate() 生成的 Stream，
         * 默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。
         */
        Stream<Double> generateB = Stream.generate(()-> Math.random());
        Stream<Double> generateC = Stream.generate(Math::random);
        generateB.forEach(e -> System.out.println(e));
        generateC.forEach(e -> System.out.println(e));
    }

    /**
     * iterate方法，其返回的也是一个无限长度的Stream，
     * 与generate方法不同的是，其是通过函数f迭代对给指定的元素种子而产生无限连续有序Stream，
     * 其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环。
     * public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
     */
    public static void iterate(){
        /*
         * 种子为1，也可认为该Stream的第一个元素，通过f函数来产生第二个元素。
         * 接着，第二个元素，作为产生第三个元素的种子，从而产生了第三个元素，以此类推下去。
         * 需要主要的是，该Stream也是无限长度的，应该使用filter、limit等来截取Stream，否则会一直循环下去。
         */
        int initialValue = 1;
        Stream.iterate(initialValue, item -> item + 1)
                .limit(11)
                .forEach(System.out::println);
    }

    /**
     * empty方法返回一个空的顺序Stream，该Stream里面不包含元素项。
     * public static<T> Stream<T> empty()
     */
    public static void empty(){
        System.out.println(Stream.empty().collect(Collectors.toList()).size());
    }
    //**************************************************静态工厂方法*************************************************


    /**
     * Intermediate主要是用来对Stream做出相应转换及限制流，实际上是将源Stream转换为一个新的Stream，以达到需求效果。
     */
    //**************************************************Intermediate*************************************************

    /**
     * concat方法将两个Stream连接在一起，合成一个Stream。
     * 若两个输入的Stream都时排序的，则新Stream也是排序的；
     * 若输入的Stream中任何一个是并行的，则新的Stream也是并行的；若关闭新的Stream时，原两个输入的Stream都将执行关闭处理。
     * public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
     */
    public static void concat(){
        Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5))
                .forEach(integer -> System.out.print(integer + "  "));
    }

    /**
     * 创建了一个Stream（命名为A），其含有重复的1，2，3等六个元素，而实际上打印结果只有“1，2，3”等3个元素。
     * 因为A经过distinct去掉了重复的元素，生成了新的Stream（命名为B），而B
     * 中只有“1，2，3”这三个元素，所以也就呈现了刚才所说的打印结果。
     * Stream<T> distinct();
     */
    public static void distinct(){
        Stream.of(1,2,3,1,2,3)
                .distinct()
                .forEach(System.out::println);

        /*
         * distinct 去重实际上调用的对象的equals方法
         */
        List<IdentityCard> list = new ArrayList(){
            {
                add(new IdentityCard("1","张三"));
                add(new IdentityCard("2","李四"));
                add(new IdentityCard("3","网二"));
                add(new IdentityCard("1","砂布"));
            }
        };
        list.stream().distinct().forEach(System.out::println);

    }

    /**
     * filter方法对原Stream按照指定条件过滤，在新建的Stream中，只包含满足条件的元素，将不满足条件的元素过滤掉。
     * Stream<T> filter(Predicate<? super T> predicate);
     */
    public static void filter(){
        data.stream().filter(e -> e.getStudentName().length() ==3).forEach(System.out::println);
        //过滤掉学生名字长度不为3的学生
        System.out.println("--------------------------------------------------------------");
        //filter传入的Lambda表达式必须是Predicate实例，参数可以为任意类型，而其返回值必须是boolean类型。
        Predicate<Student> predicate = student -> student.getStudentName().length() != 3;
        data.stream().filter(predicate).forEach(System.out::println);

    }

    /**
     * map方法将对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
     * 为了提高处理效率，官方已封装好了，三种变形：mapToDouble，mapToInt，mapToLong。
     * 其实很好理解，如果想将原Stream中的数据类型，转换为double,int或者是long是可以调用相对应的方法。
     * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
     */
    public static void map(){
        Stream.of("a", "b", "hello")
                .map(item-> item.toUpperCase())
                .forEach(System.out::println);

        System.out.println("------------------------------------------------------");
        //map传入的Lambda表达式必须是Function实例，参数可以为任意类型，而其返回值也是任性类型，javac会根据实际情景自行推断。
        Function<Student,String> function = student -> student.getStudentName();
        data.stream().map(function).forEach(System.out::println);
    }

    /**
     * flatMap方法与map方法类似，都是将原Stream中的每一个元素通过转换函数转换，
     * 不同的是，该换转函数的对象是一个Stream，也不会再创建一个新的Stream，
     * 而是将原Stream的元素取代为转换的Stream。如果转换函数生产的Stream为null，应由空Stream取代。
     * flatMap有三个对于原始类型的变种方法，分别是：flatMapToInt，flatMapToLong和flatMapToDouble。
     * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
     */
    public static void flatMap(){
        List<Student> studentList1 = new ArrayList() {
            {
                add(new Student("0001", "田虎是子", "100001", "一年一班"));
                add(new Student("0002", "蛟九", "100001", "一年一班"));
                add(new Student("0003", "甘久珍", "100001", "一年一班"));
                add(new Student("0004", "田园", "100001", "一年一班"));
                add(new Student("0005", "大胡子", "100001", "一年一班"));
            }
        };

        List<Student> studentList2 = new ArrayList() {
            {
                add(new Student("0006", "甄姬", "100002", "一年二班"));
                add(new Student("0007", "苏安全", "100002", "一年二班"));
                add(new Student("0008", "关飞", "100002", "一年二班"));
                add(new Student("0009", "校田径俺三", "100002", "一年二班"));
                add(new Student("0010", "餐刀锋", "100002", "一年二班"));
            }
        };


        List<StudentClass> studentClassList = new ArrayList() {
            {
                add(new StudentClass("100002", "一年二班", studentList1));
                add(new StudentClass("0007", "100002", studentList2));
            }
        };

        Stream<Student> ss = studentClassList.stream().flatMap(c -> c.getStudents().stream());
        Map<String,Student> map = studentClassList.stream().flatMap(c -> c.getStudents().stream()).collect(Collectors.toMap(e -> e.getId(),e->e));
        System.out.println(JSON.toJSONString(map));

        Stream<Stream<Student>> s = studentClassList.stream().map(c -> c.getStudents().stream());
        System.out.println(JSON.toJSONString(s));




        //获取单词，并且去重
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world",
                "hello world welcome");

        /*
         *  1：
         *     flatMap 方法实现为: <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
         *     Function返回的泛型参数（第二个泛型参数）必须是继承Stream的
         *     map 方法实现为:<R> Stream<R> map(Function<? super T, ? extends R> mapper);
         *     Function返回的泛型参数（第二个泛型参数）可以是任意的
         */
        //
        Function<String,Stream<String>> functionFlatMap1 = e -> Arrays.stream(e.split(" "));
        list.stream().flatMap(functionFlatMap1);
        /*
         *  这种情况报错
         *  Function<String,Integer> functionFlatMap2 = e -> e.split(" ").length;
         *  list.stream().flatMap(functionFlatMap2)
         */
        Function<String,Stream<String>> functionMap1 = e -> Arrays.stream(e.split(" "));
        list.stream().map(functionMap1);
        Function<String,Integer> functionMap2 = e -> e.split(" ").length;
        list.stream().map(functionMap2);

        /*
         * 2:返回类型不同
         *  map方法将对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
         *
         *  flatMap 该换转函数的对象是一个Stream，也不会再创建一个新的Stream，
         *  而是将原Stream的元素取代为转换的Stream。如果转换函数生产的Stream为null，应由空Stream取代。
         */
        Function<String,Stream<String>> f = e -> Arrays.stream(e.split(" "));
        Stream<Stream<String>> stream = list.stream().map(f);
        Stream<String> stream2 = list.stream().flatMap(f);


        //也可以这样
        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);

        //相互组合
        List<String> list2 = Arrays.asList("hello", "hi", "你好");
        List<String> list3 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("------------------------------------------------------");
        list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * peek方法生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），
     * 新Stream每个元素被消费的时候都会执行给定的消费函数，并且消费函数优先执行
     * Stream<T> peek(Consumer<? super T> action);
     */
    public static void peek(){
        Consumer<Student> consumer = student -> student.setClassName(student.getClassId()+"_"+student.getClassName());
        data.stream().peek(consumer).forEach(System.out::println);
    }

    /**
     * skip方法将过滤掉原Stream中的前N个元素，返回剩下的元素所组成的新Stream。
     * 如果原Stream的元素个数大于N，将返回原Stream的后（原Stream长度-N）个元素所组成的新Stream；
     * 如果原Stream的元素个数小于或等于N，将返回一个空Stream。
     * Stream<T> skip(long n);
     */
    public static void skip(){
        Stream<Integer> s = Stream.of(1, 2, 3,4,5);
        s.skip(2).forEach(System.out::println);
    }

    /**
     * sorted方法将对原Stream进行排序，返回一个有序列的新Stream。sorterd有两种变体sorted()，sorted(Comparator)，
     * 前者将默认使用Object.equals(Object)进行排序，而后者接受一个自定义排序规则函数(Comparator)，可按照意愿排序。
     * Stream<T> sorted();
     */
    public static void sorted(){
        Stream.of(5, 4, 3, 2, 1)
                .sorted()
                .forEach(System.out::print);
        System.out.println();


        Stream.of(23, 12, 3, 4, 5)
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        //Comparator
        //Comparator<Integer> comparator = (Integer i1 ,Integer i2) -> i1 - i2;
        Comparator<Integer> comparator = Comparator.comparingInt(i -> i);

        Stream.of(11, 22, 33, 44, 55)
                .sorted(comparator)
                .forEach(System.out::println);
        System.out.println();
    }
    //**************************************************Intermediate*************************************************


    //**************************************************Terminal*************************************************
    /**
     * count方法将返回Stream中元素的个数。
     * long count();
     */
    public static void count(){
        long count = Stream.of(1, 2, 3, 4, 5)
                .count();
        // 打印结果：count:5
        System.out.println("count:" + count);
    }

    /**
     * forEach方法前面已经用了好多次，其用于遍历Stream中的所元素，避免了使用for循环，让代码更简洁，逻辑更清晰。
     * void forEach(Consumer<? super T> action);
     */
    public static void forEach(){
        Stream.of(5, 4, 3, 2, 1)
                .forEach(System.out::println);

        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        map.forEach((key,value) ->
            System.out.println("key:"+key+",value:"+value)
        );
    }

    /**
     * forEachOrdered方法与forEach类似，都是遍历Stream中的所有元素，不同的是，
     * 如果该Stream预先设定了顺序，会按照预先设定的顺序执行（Stream是无序的），默认为元素插入的顺序。
     */
    public static void forEachOrdered(){
        data.stream().forEach(System.out::println);
        System.out.println("-------------------------");
        data.stream().forEachOrdered(System.out::println);
    }

    /**
     * max方法根据指定的Comparator，返回一个Optional，该Optional中的value值就是Stream中最大的元素。至于Optional是啥，后续再做介绍吧。
     * 原Stream根据比较器Comparator，进行排序(升序或者是降序)，所谓的最大值就是从新进行排序的，max就是取重新排序后的最后一个值，而min取排序后的第一个值。
     * Optional<T> max(Comparator<? super T> comparator);
     */
    public static void max(){
        Comparator<Integer> comparator = (Integer i1,Integer i2) ->
        {
          if(i1 < i2){
              return 1;
          } else if(i1 > i2){
              return -1;
          }
          return 0;
        };
        Stream.of(1, 22, 333, 4444, 55555).sorted(comparator).forEach(System.out::println);
        //此处是取出排序之后的最后一个元素，输出：1
        Optional<Integer> max = Stream.of(1, 22, 333, 4444, 55555)
                .max(comparator);
        // 打印结果：max:1
        System.out.println("max:" + max.get());
    }

    /**
     * max方法根据指定的Comparator，返回一个Optional，该Optional中的value值就是Stream中最大的元素。至于Optional是啥，后续再做介绍吧。
     * 原Stream根据比较器Comparator，进行排序(升序或者是降序)，所谓的最大值就是从新进行排序的，max就是取重新排序后的最后一个值，而min取排序后的第一个值。
     * Optional<T> min(Comparator<? super T> comparator);
     */
    public static void min(){
        Comparator<Integer> c = (Integer i1,Integer i2) -> {
            if(i1 < i2){
                return 1;
            } else if(i1 > i2){
                return -1;
            }
            return 0;
        };

        Stream.of(1, 22, 333, 4444, 55555).sorted(c).forEach(System.out::println);
        //此处是取出排序之后的第一个元素，输出：55555
        Optional<Integer> max = Stream.of(1, 22, 333, 4444, 55555)
                .min(c);
        // 打印结果：max:1
        System.out.println("max:" + max.get());
    }


    //************reduce *************
    /**
     * reduce 操作可以实现从Stream中生成一个值，其生成的值不是随意的，而是根据指定的计算模型。比如，之前提到count、min和max方
     * 法，因为常用而被纳入标准库中。事实上，这些方法都是reduce操作。
     *
     * reduce方法有三个override的方法：
     *  Optional<T> reduce(BinaryOperator<T> accumulator);
     *  T reduce(T identity, BinaryOperator<T> accumulator);
     *  <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner);
     */


    //************reduce *************

    //**************************************************Terminal*************************************************



    //**************************************************Short-circuiting*************************************************

    /**
     * allMatch操作用于判断Stream中的元素是否全部满足指定条件。如果全部满足条件返回true，否则返回false。
     * boolean allMatch(Predicate<? super T> predicate);
     */
    public static void allMatch(){
        boolean allMatch = Stream.of(1, 2, 3, 4)
                .allMatch(integer -> integer == 1);
        // 打印结果：allMatch: false
        System.out.println("allMatch: " + allMatch);
    }


    /**
     * anyMatch操作用于判断Stream中的是否有满足指定条件的元素。如果最少有一个满足条件返回true，否则返回false。
     * boolean anyMatch(Predicate<? super T> predicate);
     */
    public static void anyMatch(){
        boolean allMatch = Stream.of(1, 2, 3, 4)
                .anyMatch(integer -> integer == 1);
        // 打印结果：allMatch: true
        System.out.println("allMatch: " + allMatch);
    }

    /**
     * noneMatch方法将判断Stream中的所有元素是否满足指定的条件，如果所有元素都不满足条件，返回true；否则，返回false.
     * boolean noneMatch(Predicate<? super T> predicate);
     */
    public static void noneMatch(){
        System.out.println(Stream.of(1,2,3,4,5).noneMatch(i -> i < 0));
        System.out.println(Stream.of(1,2,3,4,5).allMatch(i -> i > 0));
    }

    /**
     * findAny操作用于获取含有Stream中的某个元素的Optional，如果Stream为空，则返回一个空的Optional。
     * 由于此操作的行动是不确定的，其会自由的选择Stream中的任何元素。
     * 在并行操作中，在同一个Stram中多次调用，可能会不同的结果。
     * 在串行调用时，Debug了几次，发现每次都是获取的第一个元素，个人感觉在串行调用时，应该默认的是获取第一个元素。
     * Optional<T> findAny();
     */
    public static void findAny(){
        Supplier<Integer> supplier = () -> 512;

        Optional<Integer> any = Stream.of(1, 2, 3, 4).findAny();
        System.out.println(any.orElseGet(supplier));

        List<Integer> list = Arrays.asList(1,22,333,4444,55555);
        Optional<Integer> any2 = list.stream().filter(i -> i > 9999999).findAny();
        System.out.println(any2.orElseGet(supplier));
    }

    /**
     * findFirst操作用于获取含有Stream中的第一个元素的Optional，如果Stream为空，则返回一个空的Optional。
     * 若Stream并未排序，可能返回含有Stream中任意元素的Optional。
     * Optional<T> findFirst();
     */
    public static void findFirst(){
        Supplier<Integer> supplier = () -> 512;

        List<Integer> list = Arrays.asList(1,22,333,4444,55555);
        Optional<Integer> any1 = list.stream().findFirst();
        System.out.println(any1.orElseGet(supplier));

        List<Integer> list2 = new ArrayList<>();
        Optional<Integer> any2 = list2.stream().findFirst();
        System.out.println(any2.orElseGet(supplier));
    }

    /**
     * limit方法将截取原Stream，截取后Stream的最大长度不能超过指定值N。如果原Stream的元素个数大于N，
     * 将截取原Stream的前N个元素；如果原Stream的元素个数小于或等于N，将截取原Stream中的所有元素。
     * 和 skip 函数相反
     * Stream<T> limit(long maxSize);
     */
    public static void limit(){
        Stream.of(1,2,3,4,5).limit(4).forEach(System.out::println);
    }
    //**************************************************Short-circuiting*************************************************




    static class Person {
        String id;
        String name;
        String age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Person() {
        }

        public Person(String id, String name, String age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }



}

