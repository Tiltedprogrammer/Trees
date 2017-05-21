package B_Tree
import Tree
import Printer
/**
 * Created by Holden Caulfield on 04.04.2017.
 */
class BPrinter<K: Comparable<K>,V> : Printer<K,V>{
    override fun print(tree : Tree<K,V>){
        tree as BTree
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
        println("\n")
    }
}