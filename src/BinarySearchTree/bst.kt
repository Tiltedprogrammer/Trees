package BinarySearchTree
import Tree
/**
 * Created by Holden Caulfield on 27.02.2017.
 */



class bst< T: Comparable<T>, E>( var root: Node<T,E>? = null):Tree<T,E>, Iterable<Node<T,E>>{
    override fun iterator():Iterator<Node<T,E>> = BinIterator(this)
    override fun search(key: T?): E? {
        if(key == null) return null
        var curNode: Node<T, E>? = root
         while (curNode != null) {
            when {
                key < curNode.key -> curNode = curNode.left
                key > curNode.key -> curNode = curNode.right
                key == curNode.key -> return  curNode.value
            }
        }
        return null
    }

    override fun insert(key: T, value: E) {
        var curNode: Node<T, E>? = root
        var prevNode: Node<T, E>? = null
        var newNode: Node<T, E> = Node(key, value)
        while (curNode != null) {
            prevNode = curNode
            if (key < curNode.key) {
                curNode = curNode.left
            }
            else {
                curNode = curNode.right
            }
        }
        newNode.parent = prevNode
        if(prevNode == null){
            root = newNode
        }
        else if(newNode.key < prevNode.key){
            prevNode.left = newNode
        }
        else{
            prevNode.right = newNode
        }

    }
    private fun transplant(u: Node<T,E>, v: Node<T,E>?){
        if(u.parent == null){
            root = v
        }
        else if(u == u.parent!!.left){
            u.parent!!.left = v
        }
        else{
            u.parent!!.right = v
        }
        if(v != null){
            v.parent = u.parent
        }

    }
    override fun delete(key :T){
        if(key == null) return
        var curNode: Node<T, E>? = root
        loop@while (curNode != null) {
            when {
                key < curNode.key -> curNode = curNode.left
                key > curNode.key -> curNode = curNode.right
                key == curNode.key -> break@loop
            }
        }
        if(curNode == null) return
        if(curNode.left == null){
            transplant(curNode, curNode.right)
        }
        else if(curNode.right == null){
            transplant(curNode, curNode.left)
        }
        else{
            var tmp = minNode(curNode.right)
            if(tmp!!.parent != curNode) {
                transplant(tmp,tmp.right)
                tmp.right = curNode.right
                tmp.right!!.parent = tmp
            }
            transplant(curNode,tmp)
            tmp.left = curNode.left
            tmp.left!!.parent = tmp
        }
        return



    }
    fun maxNode(node : Node<T,E>? = root):Node<T,E>?{
        if (node == null){
            return null
        }
        else{
            var prevNode = node
            var curNode = node
            while(curNode != null){
                prevNode = curNode
                curNode = curNode.right
            }
            return prevNode

        }
    }
    fun minNode(node : Node<T,E>? = root ):Node<T,E>?{
        if (node == null){
            return null
        }
        else{
            var prevNode = node
            var curNode = node
            while(curNode != null){
                prevNode = curNode
                curNode = curNode.left
            }
            return prevNode

        }
    }
    fun getheight(key: T): Int {
        var currentNode: Node<T,E>? = root
        var height: Int = 0

        loop@ while (currentNode != null) {
            when {
                key < currentNode.key -> currentNode = currentNode.left
                key > currentNode.key -> currentNode = currentNode.right
                key == currentNode.key -> break@loop
            }
            height++
        }

        return height
    }
}


