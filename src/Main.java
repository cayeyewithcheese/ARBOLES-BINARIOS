import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ejemplo: arbol del enunciado
        //            A
        //          /   \
        //         B     C
        //        / \   / \
        //       D   E F   G
        //      / \
        //     H   I
        TreeNode a = new TreeNode("A");
        BinaryTree tree = new BinaryTree(a);

        a.left = new TreeNode("B");
        a.right = new TreeNode("C");
        a.left.left = new TreeNode("D");
        a.left.right = new TreeNode("E");
        a.right.left = new TreeNode("F");
        a.right.right = new TreeNode("G");
        a.left.left.left = new TreeNode("H");
        a.left.left.right = new TreeNode("I");

        int opcion = 0;

        do {
                    System.out.println("\n========== MENU ==========");
                    System.out.println("1. Construir arbol");
                    System.out.println("2. Mostrar arbol");
                    System.out.println("3. Recorrido Preorden");
                    System.out.println("4. Recorrido Inorden");
                    System.out.println("5. Recorrido Posorden");
                    System.out.println("6. Mostrar factores de equilibrio");
                    System.out.println("7. Balancear arbol (AVL)");
                    System.out.println("8. Salir");
                    System.out.println("==========================");
                    System.out.print("Seleccione una opcion: ");

                    String entrada = scanner.nextLine();

                    try {
                        opcion = Integer.parseInt(entrada);
                    } catch (NumberFormatException e) {
                        opcion = 0;
                    }

                    switch (opcion) {
                        case 1:
                            tree.buildTree(scanner);
                            break;

                        case 2:
                            tree.printTree();
                            break;

                        case 3:
                            System.out.println("Preorden: " + tree.preorder());
                            break;

                        case 4:
                            System.out.println("Inorden: " + tree.inorder());
                            break;

                        case 5:
                            System.out.println("Posorden: " + tree.postorder());
                            break;

                        case 6:
                            tree.printBalanceFactors();
                            System.out.println("Nodos desbalanceados: "
                                    + tree.unbalancedNodes());
                            System.out.println("Arbol balanceado: "
                                    + tree.isBalanced());
                            break;

                        case 7:
                            tree.balance();
                            break;

                        case 8:
                            System.out.println("Programa finalizado.");
                            break;

                        default:
                            System.out.println("Opcion no valida.");
                    }

                } while (opcion != 8);

                scanner.close();
            }
        }


        /*
        Implemente aqui el menu (cada opcion llama al metodo de BinaryTree
        indicado entre parentesis):
        ========== MENU ==========
        1. Construir arbol                  (buildTree(new Scanner(System.in)))
        2. Mostrar arbol                    (printTree())
        3. Recorrido Preorden               (preorder())
        4. Recorrido Inorden                (inorder())
        5. Recorrido Posorden               (postorder())
        6. Mostrar factores de equilibrio   (printBalanceFactors())
        7. Balancear arbol (AVL)            (balance())
        8. Salir
        ==========================
        Seleccione una opcion:
        */

