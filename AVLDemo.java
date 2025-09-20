import java.util.Scanner;

class AVLTree {
    // Nodo interno del árbol AVL
    class Node {
        int value, height;    // valor guardado y altura del nodo
        Node left, right;     // hijos izquierdo y derecho

        Node(int value) {
            this.value = value;
            this.height = 1;  // un nodo nuevo inicia con altura 1
        }
    }

    private Node root; // raíz del árbol

    // Retorna la altura de un nodo, 0 si es nulo
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Calcula el factor de balance de un nodo: altura izquierda - altura derecha
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Rotación derecha para balanceo (LL case)
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza rotación
        x.right = y;
        y.left = T2;

        // Actualiza alturas tras rotación
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // devuelve nueva raíz tras rotación
    }

    // Rotación izquierda para balanceo (RR case)
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza rotación
        y.left = x;
        x.right = T2;

        // Actualiza alturas tras rotación
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // devuelve nueva raíz tras rotación
    }

    // Inserta un valor en el árbol y balancea el árbol si es necesario
    private Node insert(Node node, int value) {
        // Inserción estándar en árbol binario de búsqueda
        if (node == null)
            return new Node(value);

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else
            return node;  // No se permiten duplicados

        // Actualiza altura del nodo insertado
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Calcula factor de balance para detectar desequilibrio
        int balance = getBalance(node);

        // Condiciones de balanceo y rotaciones

        // Caso LL
        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        // Caso RR
        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        // Caso LR
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso RL
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;  // Nodo balanceado o actualizado
    }

    // Encuentra el nodo con el valor mínimo (usado en eliminación)
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Elimina un valor del árbol y balancea según necesidad
    private Node delete(Node node, int value) {
        if (node == null)
            return node;

        if (value < node.value)
            node.left = delete(node.left, value);
        else if (value > node.value)
            node.right = delete(node.right, value);
        else {
            // Nodo con uno o sin hijos
            if ((node.left == null) || (node.right == null)) {
                Node temp = (node.left != null) ? node.left : node.right;

                // Sin hijo
                if (temp == null) {
                    temp = node;
                    node = null;
                } else // Un hijo
                    node = temp;
            } else {
                // Nodo con dos hijos: consigue sucesor en subárbol derecho
                Node temp = minValueNode(node.right);
                node.value = temp.value;
                node.right = delete(node.right, temp.value);
            }
        }

        // Si sólo tenía un nodo
        if (node == null)
            return node;

        // Actualiza altura y balancea
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Balanceos post-eliminación

        // Caso LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // Caso LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // Caso RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Busca si un valor existe en el árbol
    private boolean search(Node node, int value) {
        if (node == null)
            return false;
        if (value == node.value)
            return true;
        return value < node.value ? search(node.left, value) : search(node.right, value);
    }

    // Métodos para insertar, eliminar, buscar
    public void insert(int value) {
        if (!search(root, value)) {
            root = insert(root, value);
            System.out.println("Inserción exitosa.");
        } else {
            System.out.println("Clave duplicada. No insertada.");
        }
    }

    public void delete(int value) {
        if (search(root, value)) {
            root = delete(root, value);
            System.out.println("Eliminación exitosa.");
        } else {
            System.out.println("Valor no encontrado.");
        }
    }

    public boolean search(int value) {
        return search(root, value);
    }

    // Recorridos con impresión
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.value + " ");
        }
    }

    // Obtener raíz para recorridos externos
    public Node getRoot() {
        return root;
    }
}

// Clase principal
public class AVLDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AVLTree tree = new AVLTree();

        while (true) {
            System.out.println("\n1. Insertar\n2. Eliminar\n3. Buscar\n4. Recorrido Inorden\n5. Recorrido Preorden\n6. Recorrido Postorden\n7. Salir");
            int choice = scan.nextInt();
            if (choice == 7) break;

            switch (choice) {
                case 1:
                    System.out.println("Ingrese elementos a agregar (finalice con -999):");
                    while (true) {
                        int val = scan.nextInt();
                        if (val == -999) break;
                        tree.insert(val);
                    }
                    break;

                case 2:
                    System.out.println("Ingrese elemento a eliminar:");
                    int del = scan.nextInt();
                    tree.delete(del);
                    break;

                case 3:
                    System.out.println("Ingrese elemento a buscar:");
                    int buscar = scan.nextInt();
                    System.out.println(tree.search(buscar) ? "Encontrado." : "No encontrado.");
                    break;

                case 4:
                    System.out.print("Recorrido Inorden: ");
                    tree.inOrder(tree.getRoot());
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Recorrido Preorden: ");
                    tree.preOrder(tree.getRoot());
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Recorrido Postorden: ");
                    tree.postOrder(tree.getRoot());
                    System.out.println();
                    break;

                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }   
        }
        scan.close();
    }
}

