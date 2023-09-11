import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int idx;
        Node leftNode, rightNode;

        public Node(int idx) {
            this.idx = idx;
        }
    }

    static int[] inOrder, postOrder;
    static Node[] tree;
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine());
        inOrder = new int[n+1];
        postOrder = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            inOrder[i] = stoi(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            postOrder[i] = stoi(st.nextToken());
        }

        tree = new Node[n+1];
        for (int i = 1; i <= n; ++i) {
            tree[i] = new Node(i);
        }
        idx = n;

        solve(1, n, -1, false);
        print(tree[postOrder[n]]);
        br.close();
    }

    static void print(Node node) {
        System.out.print(node.idx + " ");
        if (node.leftNode != null) {
            print(node.leftNode);
        }
        if (node.rightNode != null) {
            print(node.rightNode);
        }
    }

    static void solve(int lo, int hi, int parentIdx, boolean isLeft) {
        if (lo > hi) return;
        int nodeIdx = -1;
        for (int i = lo; i <= hi; ++i) {
            if (postOrder[idx] == inOrder[i]) {
                nodeIdx = i;
                idx--;
                break;
            }
        }
        if (nodeIdx == -1) return;
        if (parentIdx != -1) {
            if (isLeft) {
                tree[inOrder[parentIdx]].leftNode = tree[inOrder[nodeIdx]];
            } else {
                tree[inOrder[parentIdx]].rightNode = tree[inOrder[nodeIdx]];
            }
        }
        solve(nodeIdx+1, hi, nodeIdx, false);
        solve(lo, nodeIdx-1, nodeIdx, true);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}