/**
 * Created by Holden Caulfield on 15.05.2017.
 */
import org.junit.jupiter.api.Test
import RedBlackTree.rbbst
import RedBlackTree.RBIterator
import RedBlackTree.RBNode
import RedBlackTree.Color
import BinarySearchTree.*
import B_Tree.*

import org.junit.jupiter.api.Assertions.*
import java.util.*
internal class timetest {
    @Test
    fun check(){
        var binsearchtree = bst<Int,Int>()
        var b_tree = BTree<Int,Int>(50)

        var random = Random()
        var j = random.nextInt(10000000)
        for(i in 0 .. 10000000 - 1 ){
            b_tree.insert(j,j)
            j = random.nextInt(10000000)
        }
        var c = System.nanoTime()
        binsearchtree.delete(j)
        var d = System.nanoTime()
        println((d-c))

    }

}