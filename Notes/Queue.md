1. 队列，先进先出

2. 两个栈可以实现一个队列
   
   1. 将一个栈中的元素全部弹出，并入栈到另一个栈中
   
3. 题目需要用到队列来辅助的，可以使用`java`中的`LinkedList`

   ```
   Queue<TreeNode> queue = new LinkedList<>();	//使用向上转型
   ```

4. 双端队列（`Deque`）支持再两端插入数据，而普通的队列`Queue`只能再尾部插入

   1. ```
      Deque<Integer> deque = new LinkedList<Integer>();
      ```

