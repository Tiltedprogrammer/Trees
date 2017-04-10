/**
 * Created by Holden Caulfield on 04.04.2017.
 */
class BPrinter<K: Comparable<K>,V> {
    fun printTree(tree : BTree<K,V>){
        var printuntil = 1
        var nextline = 0
        for (bnode in tree){
            if (printuntil == 0){
                printuntil = nextline
                nextline = 0
                println()
            }
            print(bnode.toString() + " ")
            nextline += bnode.Nodes.size
            printuntil --
        }
    }
}