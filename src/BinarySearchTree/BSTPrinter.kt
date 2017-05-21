
package BinarySearchTree
import Tree
import Printer
/**
 * Created by Holden Caulfield on 12.03.2017.
 */
class BSTPrinter<T: Comparable<T>,E>: Printer<T,E> {
    override fun print(tree: Tree<T, E>) {
        tree as bst
            for (node in tree) {
                for (i in 1..tree.getheight(node.key) * 5) {
                    print(' ')
                }
                println(node.value)
                }
        println("\n")
            }
}