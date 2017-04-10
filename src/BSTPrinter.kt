/**
 * Created by Holden Caulfield on 12.03.2017.
 */
class BSTPrinter<T: Comparable<T>,E> {
    fun print(tree: bst<T, E>) {
            for (node in tree) {
                for (i in 1..tree.getheight(node.key) * 5) {
                    print(' ')
                }
                println(node.value)
                }
            }
}