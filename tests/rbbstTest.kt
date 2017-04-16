import org.junit.jupiter.api.Test
import RedBlackTree.rbbst
import RedBlackTree.RBIterator
import RedBlackTree.RBNode
import RedBlackTree.Color

import org.junit.jupiter.api.Assertions.*
import sun.invoke.empty.Empty
import java.util.*

/**
 * Created by Holden Caulfield on 15.03.2017.
 */
internal class rbbstTest {
    var EmptyTree = rbbst<Int,Int>()
    var tree = rbbst<Int,Int>()
    init{
        for(i in 2 .. 66){
            tree.insert(i,i)
        }
        tree.insert(3,3)

    }
    @Test
    fun SearchInEmptyTree() {
        assertNull(EmptyTree.search(1))

    }
    //@Test
    //fun DeleteEmpty(){
      //  assertFalse(EmptyTree.delete(1))
    //}

    @Test
    fun InsertInEmptyTree() {
        var tree = rbbst<Int,Int>()
        EmptyTree.root = RBNode(1,1)
        tree.insert(1,1)
        assertEquals(EmptyTree, tree)
        EmptyTree.root = null

    }
    @Test
    fun RootColor(){
        var Tree  : rbbst<Int,Int> = rbbst()
        var random = Random()
        for (i in 1..1000) {
            Tree.insert(random.nextInt(), random.nextInt())
            assertEquals(Tree.root!!.color, Color.BLACK)
        }
        /**var prev:RbTree<Int,String>
        val list = mutableListOf<Node<Int,String>>()
        for (i in Tree) list.add(i)
        for (i in 1..list.size-2) assertEquals (true,list[i+1].key > list[i].key)
        list.clear()
        for (i in 1..1000000) Tree.insertNode (random.nextInt(),random.nextFloat().toString())
        for (i in Tree) list.add(i)
        for (i in 1..list.size-2) assertEquals (true,list[i+1].key > list[i].key)**/
    }
    @Test
    fun testinsertcase1(){//tree is empty
        var expectedTree: rbbst<Int,Int> = rbbst()
        var actualTree: rbbst<Int,Int> = rbbst()
        expectedTree.root = RBNode(1,1)
        actualTree.insert(1,1)
        assertEquals(expectedTree,actualTree)
    }
    @Test
    fun testinsertcase2(){//Nodes father is black
        var expectedTree: rbbst<Int,Int> = rbbst()
        var actualTree: rbbst<Int,Int> = rbbst()
        expectedTree.root = RBNode(1,1)
        expectedTree.root!!.right = RBNode(2,2)
        expectedTree.root!!.right!!.color = Color.RED
        expectedTree.root!!.right!!.parent = expectedTree.root
        actualTree.insert(1,1)
        actualTree.insert(2,2)
        assertEquals(expectedTree,actualTree)
    }
    @Test
    fun testinsertcase3() {//Nodes father is red && uncle is red
        var expectedTree: rbbst<Int, Int> = rbbst()
        var actualTree: rbbst<Int, Int> = rbbst()
        expectedTree.root = RBNode(2,2)
        expectedTree.root!!.right = RBNode(3,3)
        expectedTree.root!!.right!!.color = Color.BLACK
        expectedTree.root!!.right!!.parent = expectedTree.root
        expectedTree.root!!.left = RBNode(1,1)
        expectedTree.root!!.left!!.color = Color.BLACK
        expectedTree.root!!.left!!.parent = expectedTree.root
        expectedTree.root!!.right!!.right = RBNode(4,4)
        expectedTree.root!!.right!!.right!!.color = Color.RED
        expectedTree.root!!.right!!.right!!.parent = expectedTree.root!!.right
        actualTree.insert(2,2)
        actualTree.insert(1,1)
        actualTree.insert(3,3)
        actualTree.insert(4,4)
        assertEquals(expectedTree,actualTree)

    }
    @Test
    fun testinsertcase4(){//Node's father is red uncle is black
        var expectedTree: rbbst<Int, Int> = rbbst()
        var actualTree: rbbst<Int, Int> = rbbst()
        expectedTree.root = RBNode(6,6)
        expectedTree.root!!.right = RBNode(7,7)
        expectedTree.root!!.right!!.color = Color.BLACK
        expectedTree.root!!.right!!.parent = expectedTree.root
        expectedTree.root!!.left = RBNode(4,4)
        expectedTree.root!!.left!!.color = Color.BLACK
        expectedTree.root!!.left!!.parent = expectedTree.root
        expectedTree.root!!.left!!.left = RBNode(2,2)
        expectedTree.root!!.left!!.left!!.color = Color.RED
        expectedTree.root!!.left!!.left!!.parent = expectedTree.root!!.left
        expectedTree.root!!.left!!.right = RBNode(5,5)
        expectedTree.root!!.left!!.right!!.color = Color.RED
        expectedTree.root!!.left!!.right!!.parent = expectedTree.root!!.left
        actualTree.insert(6,6)
        actualTree.insert(7,7)
        actualTree.insert(5,5)
        actualTree.insert(4,4)
        actualTree.insert(2,2)
        assertEquals(expectedTree,actualTree)

    }
    @Test
    fun deleteEmpty(){
        var expectedTree: rbbst<Int,Int> = rbbst()
        var actualTree: rbbst<Int,Int> = rbbst()
        actualTree.delete(12)
        assertEquals(expectedTree, actualTree)
    }
    @Test
    fun deletecase1(){//X is red not leaf
        var expectedTree: rbbst<Int,Int> = rbbst()
        var actualTree: rbbst<Int,Int> = rbbst()
        expectedTree.root = RBNode(4,4)
        expectedTree.root!!.left = RBNode(1,1)
        expectedTree.root!!.left!!.parent = expectedTree.root
        expectedTree.root!!.right = RBNode(6,6)
        expectedTree.root!!.right!!.color = Color.RED
        expectedTree.root!!.right!!.parent = expectedTree.root
        expectedTree.root!!.left!!.right = RBNode(3,3)
        expectedTree.root!!.left!!.right!!.color = Color.RED
        expectedTree.root!!.left!!.right!!.parent = expectedTree.root!!.left
        expectedTree.root!!.right!!.left = RBNode(5,5)
        expectedTree.root!!.right!!.left!!.parent = expectedTree.root!!.right
        expectedTree.root!!.right!!.right = RBNode(8,8)
        expectedTree.root!!.right!!.right!!.parent = expectedTree.root!!.right
        actualTree.insert(7,7)
        actualTree.insert(1,1)
        actualTree.insert(4,4)
        actualTree.insert(8,8)
        actualTree.insert(3,3)
        actualTree.insert(6,6)
        actualTree.insert(5,5)
        actualTree.delete(7)
        assertEquals(expectedTree,actualTree)
    }

    @Test
    fun deletecase2(){//N is new root
        var expectedTree: rbbst<Int,Int> = rbbst()
        var actualTree: rbbst<Int,Int> = rbbst()
        expectedTree.root = RBNode(6,6)
        actualTree.insert(3,3)
        actualTree.insert(6,6)
        actualTree.delete(3)
        assertEquals(expectedTree,actualTree)
    }
    @Test
    fun deletecase3(){//Brother is red
        var expectedTree: rbbst<Int,Int> = rbbst()
        var actualTree: rbbst<Int,Int> = rbbst()

        }
    @Test
    fun maxNodeTest(){
        assertEquals(66, tree.maxNode(tree.root)!!.value)
    }
    @Test
    fun minNodeTest(){
        assertEquals(2,tree.minNode(tree.root)!!.value)
    }
    @Test
    fun brotherTest(){
        assertNull(tree.root!!.brother())
        assertEquals(tree.root!!.right,tree.root!!.left!!.brother())
    }

    @Test
    fun stressTest(){
        for (i in 1..20000000){
            EmptyTree.insert(i,i)
            if (i%100000 == 0) println(i)
        }
    }

    @Test
    fun getheight() {
        assertEquals(0, tree.getheight(tree.root!!.key))

    }

    @Test
    fun iterator() {
        val tree = rbbst<Int, Int>()
        var random = Random()
        for (i in 1..2000)
        {
            val j = random.nextInt()
            tree.insert(j,j)
        }
        var previousKey = tree.maxNode()!!.key + 1
        for (i in tree) {
            assertTrue(i.key < previousKey)
            previousKey = i.key
        }
    }

}