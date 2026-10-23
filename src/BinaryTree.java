
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree {

    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    // 1. Construccion del arbol

    public void insertRoot(Object data) {
        if (root == null) {
            root = new TreeNode(data);
        }
    }

    public TreeNode search(Object data) {
        return search(root, data);
    }

    private TreeNode search(TreeNode node, Object data) {
        if (node == null) {
            return null;
        }

        if (node.data.equals(data)) {
            return node;
        }

        TreeNode found = search(node.left, data);

        if (found != null) {
            return found;
        }

        return search(node.right, data);
    }

    public boolean insertLeft(Object parent, Object data) {
        TreeNode node = search(parent);

        if (node == null || node.left != null) {
            return false;
        }

        node.left = new TreeNode(data);
        return true;
    }

    public boolean insertRight(Object parent, Object data) {
        TreeNode node = search(parent);

        if (node == null || node.right != null) {
            return false;
        }

        node.right = new TreeNode(data);
        return true;
    }

    public void buildTree(Scanner scanner) {
        System.out.print("Dato de la raiz: ");
        String dato = scanner.nextLine().trim();

        if (dato.isEmpty() || dato.equals("-")) {
            root = null;
            System.out.println("Arbol vacio.");
            return;
        }

        root = new TreeNode(dato);

        Queue<TreeNode> cola = new LinkedList<>();
        cola.add(root);

        while (!cola.isEmpty()) {
            TreeNode actual = cola.remove();

            System.out.print("Hijo izquierdo de "
                    + actual.data + ": ");
            String izquierdo = scanner.nextLine().trim();

            if (!izquierdo.isEmpty() && !izquierdo.equals("-")) {
                actual.left = new TreeNode(izquierdo);
                cola.add(actual.left);
            }

            System.out.print("Hijo derecho de "
                    + actual.data + ": ");
            String derecho = scanner.nextLine().trim();

            if (!derecho.isEmpty() && !derecho.equals("-")) {
                actual.right = new TreeNode(derecho);
                cola.add(actual.right);
            }
        }

        System.out.println("Arbol construido correctamente.");
    }

    // 2. Visualizacion del arbol

    public void printTree() {
        if (root == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }

        printTree(root, "", true);
    }

    private void printTree(TreeNode node, String espacios,
                           boolean ultimo) {
        if (node == null) {
            return;
        }

        System.out.println(espacios
                + (ultimo ? "+-- " : "|-- ")
                + node.data);

        String nuevosEspacios = espacios
                + (ultimo ? "    " : "|   ");

        if (node.left != null && node.right != null) {
            printTree(node.left, nuevosEspacios, false);
            printTree(node.right, nuevosEspacios, true);
        } else if (node.left != null) {
            printTree(node.left, nuevosEspacios, true);
        } else if (node.right != null) {
            printTree(node.right, nuevosEspacios, true);
        }
    }

    // 3. Recorridos recursivos

    public String preorder() {
        List<String> resultado = new ArrayList<>();
        preorder(root, resultado);
        return String.join(" ", resultado);
    }

    private void preorder(TreeNode node, List<String> resultado) {
        if (node == null) {
            return;
        }

        resultado.add(String.valueOf(node.data));
        preorder(node.left, resultado);
        preorder(node.right, resultado);
    }

    public String inorder() {
        List<String> resultado = new ArrayList<>();
        inorder(root, resultado);
        return String.join(" ", resultado);
    }

    private void inorder(TreeNode node, List<String> resultado) {
        if (node == null) {
            return;
        }

        inorder(node.left, resultado);
        resultado.add(String.valueOf(node.data));
        inorder(node.right, resultado);
    }

    public String postorder() {
        List<String> resultado = new ArrayList<>();
        postorder(root, resultado);
        return String.join(" ", resultado);
    }

    private void postorder(TreeNode node, List<String> resultado) {
        if (node == null) {
            return;
        }

        postorder(node.left, resultado);
        postorder(node.right, resultado);
        resultado.add(String.valueOf(node.data));
    }

    // 4. Factores de equilibrio

    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int izquierda = height(node.left);
        int derecha = height(node.right);

        return 1 + Math.max(izquierda, derecha);
    }

    public int balanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return height(node.right) - height(node.left);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }

        int factor = balanceFactor(node);

        if (factor < -1 || factor > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    public List<Object> unbalancedNodes() {
        List<Object> resultado = new ArrayList<>();
        unbalancedNodes(root, resultado);
        return resultado;
    }

    private void unbalancedNodes(TreeNode node,
                                 List<Object> resultado) {
        if (node == null) {
            return;
        }

        int factor = balanceFactor(node);

        if (factor < -1 || factor > 1) {
            resultado.add(node.data);
        }

        unbalancedNodes(node.left, resultado);
        unbalancedNodes(node.right, resultado);
    }

    public void printBalanceFactors() {
        if (root == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }

        printBalanceFactors(root, "", true);
    }

    private void printBalanceFactors(TreeNode node,
                                     String espacios,
                                     boolean ultimo) {
        if (node == null) {
            return;
        }

        System.out.println(espacios
                + (ultimo ? "+-- " : "|-- ")
                + node.data + " (FE: "
                + balanceFactor(node) + ")");

        String nuevosEspacios = espacios
                + (ultimo ? "    " : "|   ");

        if (node.left != null && node.right != null) {
            printBalanceFactors(node.left, nuevosEspacios, false);
            printBalanceFactors(node.right, nuevosEspacios, true);
        } else if (node.left != null) {
            printBalanceFactors(node.left, nuevosEspacios, true);
        } else if (node.right != null) {
            printBalanceFactors(node.right, nuevosEspacios, true);
        }
    }

    // 5. Rotaciones AVL

    public TreeNode rotateRight(TreeNode node) {
        if (node == null || node.left == null) {
            return node;
        }

        TreeNode nuevaRaiz = node.left;
        TreeNode medio = nuevaRaiz.right;

        nuevaRaiz.right = node;
        node.left = medio;

        return nuevaRaiz;
    }

    public TreeNode rotateLeft(TreeNode node) {
        if (node == null || node.right == null) {
            return node;
        }

        TreeNode nuevaRaiz = node.right;
        TreeNode medio = nuevaRaiz.left;

        nuevaRaiz.left = node;
        node.right = medio;

        return nuevaRaiz;
    }

    public TreeNode rotateLeftRight(TreeNode node) {
        if (node == null) {
            return null;
        }

        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    public TreeNode rotateRightLeft(TreeNode node) {
        if (node == null) {
            return null;
        }

        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    public void balance() {
        System.out.println("\nArbol antes de balancear:");
        printBalanceFactors();

        root = balanceSubtree(root);

        System.out.println("\nArbol despues de balancear:");
        printBalanceFactors();

        if (isBalanced()) {
            System.out.println("El arbol quedo balanceado.");
        } else {
            System.out.println(
                    "El arbol aun tiene nodos desbalanceados.");
        }
    }

    private TreeNode balanceSubtree(TreeNode node) {
        if (node == null) {
            return null;
        }

        node.left = balanceSubtree(node.left);
        node.right = balanceSubtree(node.right);

        int factor = balanceFactor(node);

        if (factor < -1) {
            if (balanceFactor(node.left) > 0) {
                node = rotateLeftRight(node);
            } else {
                node = rotateRight(node);
            }
        } else if (factor > 1) {
            if (balanceFactor(node.right) < 0) {
                node = rotateRightLeft(node);
            } else {
                node = rotateLeft(node);
            }
        }

        // Revisa nuevamente el subarbol despues de la rotacion.
        if (node != null
                && (balanceFactor(node) < -1
                || balanceFactor(node) > 1)) {
            return balanceSubtree(node);
        }

        return node;
    }
}


