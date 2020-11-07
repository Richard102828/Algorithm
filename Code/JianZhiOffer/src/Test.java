import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Test {
    static PriorityQueue<Integer> queue = new PriorityQueue<>(12, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public static void main(String[] args) {
//        System.out.println(new int[0]);
//        int[] res = maxSlidingWindow(new int[] {1, 3, 1, 2, 0, 5}, 3);
//        for (int val: res) {
//            System.out.println(val);
//        }
//        queue.add(2);
//        queue.add(5);
//        queue.add(1);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
        final Apple apple = new Apple();
        final Fruit apple1 = (Fruit)Proxy.newProxyInstance(apple.getClass().getClassLoader(),
                apple.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if("run".equals(method.getName()))
                    System.out.println("苹果热身一下");
                method.invoke(apple);
                if("eat".equals(method.getName()))
                    System.out.println("洗一下手");
                method.invoke(apple);
                return null;
            }
        });
        System.out.println("apple1的方法：");
        apple1.run();
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k > nums.length || k <= 0)
            return new int[0];
        Deque<Integer> queue = new LinkedList<>();
        //这里没有使用数组，是因为数组的长度是不能确定的，不能使用k，因为k是未知的，而数组的长度是在编译时确定的
        ArrayList<Integer> results = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            while(!queue.isEmpty() && nums[i] >= nums[queue.peekLast()])
                queue.pollLast();
            if(!queue.isEmpty() && i - queue.peekFirst() >= k)
                queue.pollFirst();
            queue.addLast(i);
            if(i + 1 >= k)
                results.add(nums[queue.peekFirst()]);
        }
        int[] res = new int[results.size()];
        int p = 0;
        for(int val: results) {
            res[p] = val;
            p++;
        }
        return res;
    }
}

interface Fruit {
    public void eat();
    public void run();
}

class Apple implements Fruit {
    @Override
    public void eat() {
        System.out.println("我吃了一个苹果");
    }
    public void run(){
        System.out.println("苹果跑起来了！！！");
        eat();
    }
}


