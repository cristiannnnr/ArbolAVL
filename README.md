## ¿Qué es un árbol AVL?

Un árbol AVL (del nombre de sus creadores Adelson-Velsky y Landis) es un **árbol binario de búsqueda auto-balanceado** que mantiene el equilibrio para asegurar que las operaciones de inserción, eliminación y búsqueda se ejecuten en tiempo $$O(\log n)$$.

Cada nodo tiene un **factor de equilibrio** que es la diferencia entre la altura de su subárbol izquierdo y derecho. Para mantener el árbol balanceado, este factor debe estar entre -1 y 1.

***

## Funcionalidad del código

- **Nodo (`Node`)**: Cada nodo contiene un valor entero, sus referencias a hijos izquierdo y derecho, y su altura en el árbol.

- **Inserción**: Inserta un valor respetando la propiedad binaria del árbol. Luego actualiza alturas y realiza rotaciones (simples o dobles) para mantener el balance AVL.

- **Eliminación**: Borra un nodo por valor. Si tiene dos hijos, intercambia con el sucesor inorden (mínimo del subárbol derecho) y luego elimina el nodo sucesor. Posteriormente, actualiza alturas y balancea el árbol con rotaciones si es necesario.

- **Búsqueda**: Busca un valor determinado recursivamente en el árbol, usando la propiedad de árbol binario.

- **Rotaciones**: Aplicadas para corregir desequilibrios:
  - Rotación derecha (caso LL)
  - Rotación izquierda (caso RR)
  - Rotación doble izquierda-derecha (caso LR)
  - Rotación doble derecha-izquierda (caso RL)

- **Recorridos**: Implementa tres formas de recorrer el árbol:
  - **Inorden**: izquierda → raíz → derecha (produce valores ordenados)
  - **Preorden**: raíz → izquierda → derecha
  - **Postorden**: izquierda → derecha → raíz
  
- **Interfaz por consola**: Permite al usuario insertar, eliminar, buscar valores y mostrar recorridos en modo interactivo.

***

## Detalles de métodos clave

| Método               | Descripción                                                                                  |
|----------------------|----------------------------------------------------------------------------------------------|
| `insert(int value)`  | Inserta un nodo con el valor especificado, actualizando y balanceando el árbol AVL.          |
| `delete(int value)`  | Elimina el nodo con el valor dado, actualizando y balanceando el árbol.                      |
| `search(int value)`  | Verifica si el valor existe en el árbol.                                                    |
| `inOrder(Node root)` | Muestra los valores ordenados con recorrido inorden.                                        |
| `preOrder(Node root)`| Muestra los valores en recorrido preorden.                                                  |
| `postOrder(Node root)`| Muestra los valores en recorrido postorden.                                                |
| `rightRotate(Node y)`| Realiza rotación simple a la derecha para corregir desequilibrios LL.                        |
| `leftRotate(Node x)` | Realiza rotación simple a la izquierda para corregir desequilibrios RR.                      |

***

## Beneficios del árbol AVL

- Garantiza operaciones logarítmicas en el peor caso.
- Mantiene el árbol balanceado automáticamente tras cada inserción o eliminación.
- Evita degeneración en listas enlazadas que ocurre en árboles binarios de búsqueda simples.

***

## Referencias y más información

[1](https://runestone.academy/ns/books/published/pythoned/Trees/ImplementacionDeUnArbolAVL.html)
[2](https://translate.google.com/translate?u=https%3A%2F%2Fwww.happycoders.eu%2Falgorithms%2Favl-tree-java%2F&hl=es&sl=en&tl=es&client=srp)
[3](https://translate.google.com/translate?u=https%3A%2F%2Fwww.datacamp.com%2Ftutorial%2Favl-tree&hl=es&sl=en&tl=es&client=srp)
[4](https://translate.google.com/translate?u=https%3A%2F%2Fwww.w3schools.com%2Fdsa%2Fdsa_data_avltrees.php&hl=es&sl=en&tl=es&client=srp)
