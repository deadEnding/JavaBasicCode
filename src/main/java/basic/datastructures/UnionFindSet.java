package basic.datastructures;

/**
 * @author: deadend
 * @date: 10:47 AM 3/13/17
 * @version: 1.0
 * @description: 并查集
 */


public class UnionFindSet {

    /* 节点id的直接父结点 */
    private int[] parent;
    /* 以节点id为根的节点个数 */
    private int[] size;
    /* 最大集合的大小 */
    private int maxSize;
    /* 集合的个数 */
    private int count;

    public UnionFindSet(int n) {
        this(n, n);
    }

    /**
     * 构造函数初始化
     * @param n: 数组大小
     * @param count: 集合数
     */
    public UnionFindSet(int n, int count) {
        if (n <= 0) {          // 不合理参数
            return;
        }

        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;     // 各节点初始父结点为自身
            size[i] = 1;       // 各集合初始大小为1
        }
        maxSize = 1;           // 初始化最大集合大小为1
        this.count = count;
    }

    /**
     * 查找根节点，即集合的代表元
     * @param id
     * @return 根节点的id
     */
    public int findRoot(int id) {
        while (id != parent[id]) {   // parent[id]值为id的是根节点
            parent[id] = parent[parent[id]];   // 路径压缩
            id = parent[id];         // 向上
        }
        return id;
    }

    /**
     * 判断两个节点是否属于同一集合，即判断根节点是否相同
     * @param pid
     * @param qid
     * @return
     */
    public boolean isConnected(int pid, int qid) {
        return findRoot(pid) == findRoot(qid);
    }

    /**
     * 合并两个集合，实际上合并两个结合的根节点
     * @param pid
     * @param qid
     * @return 是否合并成功
     */
    public boolean union(int pid, int qid) {
        int pRoot = findRoot(pid);
        int qRoot = findRoot(qid);
        if (pRoot == qRoot) {
            return false;
        } else {
            parent[pRoot] = qRoot;      // 合并
            size[qRoot] += size[pRoot]; // 修改集合大小
            maxSize = Math.max(maxSize, size[qRoot]);   // 更新最大集合大小
            count--;    // 集合数减1
            return true;
        }
    }
}
