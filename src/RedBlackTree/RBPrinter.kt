package RedBlackTree
import Printer
import Tree
/**
 * Created by Holden Caulfield on 09.04.2017.
 */
class RBPrinter<T: Comparable<T>,E>: Printer<T,E> {
    override fun print(tree: Tree<T, E>)  {
        tree as rbbst
        for (node in tree) {
            for (i in 1..tree.getheight(node.key) * 5) {
                print(' ')
            }
            if (node.color == Color.BLACK) {
                println(node.value)
            }
            else {
                println(27.toChar() + "[31m"+ node.value +27.toChar() + "[0m")
            }
        }
        println("\n")

    }
}