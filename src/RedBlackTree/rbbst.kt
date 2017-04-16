package RedBlackTree
import Tree
/**
 * Created by Holden Caulfield on 05.03.2017.
 */
class rbbst< T: Comparable<T>, E>:Iterable<RBNode<T,E>>,Tree<T,E>{
    override fun iterator() = RBIterator(this)
    internal var root: RBNode<T,E>? = null
    override fun search(key: T?): E? {
        if(key == null) return null
        val currentNode = searchByKey(key, root)

        if (currentNode == null) {
            return null
        }
        else {
            return currentNode.value
        }
    }

    private fun searchByKey(key: T, nodeStart: RBNode<T, E>?): RBNode<T, E>? {
        var currentNode: RBNode<T, E>? = nodeStart

        loop@ while (currentNode != null) {
            when {
                key < currentNode.key -> currentNode = currentNode.left
                key > currentNode.key -> currentNode = currentNode.right
                key == currentNode.key -> break@loop
            }
        }

        return currentNode
    }
    private fun leftrotate(Node: RBNode<T,E>) {
        val tmp = Node.right
        Node.right = tmp!!.left
        tmp.left?.parent = Node
        tmp.left = Node
        tmp.parent = Node!!.parent

        if (Node.parent == null)
            root = tmp
        Node.parent = tmp
        if (Node == tmp.parent?.left) {
            tmp.parent?.left = tmp
        } else tmp.parent?.right = tmp
    }

    private fun rightrotate(Node: RBNode<T,E>) {
        val tmp = Node.left
        Node.left = tmp!!.right

        tmp.right?.parent = Node
        tmp.right = Node
        tmp.parent = Node.parent

        if (Node.parent == null)
            root = tmp
        Node.parent = tmp
        if (Node == tmp.parent?.left) {
            tmp.parent?.left = tmp
        } else tmp.parent?.right = tmp
    }
    private fun insertFixup(node: RBNode<T,E>){
        var curNode: RBNode<T,E>? = node
        var tmp :RBNode<T,E>?
        while (curNode?.parent?.color == Color.RED){
            if(curNode?.parent == curNode?.parent?.parent?.left){
                tmp = curNode?.parent?.parent?.right
                if(tmp?.color == Color.RED && tmp != null){
                    curNode?.parent?.color = Color.BLACK
                    tmp.color = Color.BLACK
                    curNode?.parent?.parent?.color = Color.RED
                    curNode = curNode?.parent?.parent
                }
                else if(curNode == curNode?.parent?.right){
                    curNode = curNode?.parent
                    leftrotate(curNode!!)
                }
                else if (curNode == curNode?.parent?.left) {
                    curNode?.parent?.color = Color.BLACK
                    curNode?.parent?.parent?.color = Color.RED
                    rightrotate(curNode?.parent?.parent!!)
                }


            }
            else{
                tmp = curNode?.parent?.parent?.left
                if(tmp?.color == Color.RED && tmp != null){
                    curNode?.parent?.color = Color.BLACK
                    tmp?.color = Color.BLACK
                    curNode?.parent?.parent?.color = Color.RED
                    curNode = curNode?.parent?.parent
                }
                else if(curNode == curNode?.parent?.left){
                    curNode = curNode?.parent
                    rightrotate(curNode!!)
                }
                else if (curNode == curNode?.parent?.right) {
                    curNode?.parent?.color = Color.BLACK
                    curNode?.parent?.parent?.color = Color.RED
                    leftrotate(curNode?.parent?.parent!!)
                }
            }
        }
        root?.color = Color.BLACK

    }


    override fun insert(key: T, value: E) {
        var curNode: RBNode<T,E> = RBNode(key, value)
        var y: RBNode<T,E>? = null
        var x: RBNode<T,E>? = root
        while(x != null) {
            y = x
            if(key < x!!.key ){
                x = x.left
            }
            else{
                x = x.right
            }
        }
        curNode.parent = y
        if(y == null){
            root = curNode
            curNode.color = Color.BLACK
            return
        }
        if(key < y!!.key ){
            y.left = curNode
        }
        else{
            y.right = curNode
        }
        curNode.left = null
        curNode.right = null
        curNode.color = Color.RED
        insertFixup(curNode)

    }
    private fun transplant(v :RBNode<T,E>, u: RBNode<T,E>?){
        if(v.parent == null){
            root = u
        }
        else if(v == v.parent!!.left){
            v.parent!!.left = u
        }
        else{
            v.parent!!.right = u
        }
        u?.parent = v.parent
    }
    override fun delete(key: T) {
        //if (key == null) return

        val node = searchByKey(key, root) ?: return
        val min = minNode(node.right)

        when {
            ((node.right != null) && (node.left != null))
            -> {
                val nextKey = min!!.key
                val nextValue = min.value
                delete(min.key)
                node.key = nextKey
                node.value = nextValue
            }
            ((node == root) && (node.right == null && node.left == null))
            -> {
                root = null
                return
            }
            (node.color == Color.RED && node.right == null && node.left == null)
            -> {
                if (node.key < node.parent!!.key) {
                    node.parent!!.left = null
                } else {
                    node.parent!!.right = null
                }
                return
            }
            (node.color == Color.BLACK && ((node.left != null) && (node.left!!.color == Color.RED)))
            -> {
                node.key = node.left!!.key
                node.value = node.left!!.value
                node.left = null
                return
            }
            (node.color == Color.BLACK && (node.right != null) && (node.right!!.color == Color.RED))
            -> {
                node.key = node.right!!.key
                node.value = node.right!!.value
                node.right = null
                return
            }
            else
            -> {
                case1(node)
            }
        }

        if (node.key == key) {
            if (node.key < node.parent!!.key) {
                node.parent!!.left = null
            } else {
                node.parent!!.right = null
            }
        }
        return
    }

    private fun case1(node: RBNode<T, E>) {
        if (node == root) {
            node.color = Color.BLACK
            return
        }

        if (node == node.parent!!.left) {
            case2Left(node)
        } else {
            case2Right(node)
        }
    }

    private fun case2Left(node: RBNode<T, E>) {
        val brother = node.brother()

        if (brother!!.color == Color.RED) {
            node.parent!!.recoloring()
            brother.recoloring()
            leftrotate(node.parent!!)
            case1(node)
            return
        }

        case3(node)
    }

    private fun case2Right(node: RBNode<T, E>) {
        val brother = node.brother()

        if (brother!!.color == Color.RED) {
            node.parent!!.recoloring()
            brother.recoloring()
            rightrotate(node.parent!!)
            case1(node)
            return
        }

        case3(node)
    }

    private fun case3(node: RBNode<T, E>) {
        val brother = node.brother()

        if (((brother!!.left == null) || brother.left!!.color == Color.BLACK)
                &&
                ((brother.right == null) || brother.right!!.color == Color.BLACK))
        {
            node.color = Color.BLACK
            brother.recoloring()
            if (node.parent!!.color == Color.RED) {
                node.parent!!.recoloring()
                return
            }
            case1(node.parent!!)
            return
        }

        if (node == node.parent!!.left) {
            case4Left(node)
        } else {
            case4Right(node)
        }
    }

    private fun case4Left(node: RBNode<T, E>) {
        val brother = node.brother()

        if ((brother!!.right == null) || brother.right!!.color == Color.BLACK) {
            brother.recoloring()
            brother.left!!.recoloring()
            rightrotate(brother)
            case1(node)
            return
        }

        case5Left(node)
    }

    private fun case4Right(node: RBNode<T, E>) {
        val brother = node.brother()

        if ((brother!!.left == null) || brother.left!!.color == Color.BLACK) {
            brother.recoloring()
            brother.right!!.recoloring()
            leftrotate(brother)
            case1(node)
            return
        }

        case5Right(node)
    }

    private fun case5Left(node: RBNode<T, E>) {
        val brother = node.brother()

        if ((brother!!.right != null) && brother.right!!.color == Color.RED) {
            brother.color = node.parent!!.color
            node.color = Color.BLACK
            node.parent!!.color = Color.BLACK
            brother.right!!.color = Color.BLACK
            leftrotate(node.parent!!)
            return
        }
    }

    private fun case5Right(node: RBNode<T, E>) {
        val brother = node.brother()

        if ((brother!!.left != null) && brother.left!!.color == Color.RED) {
            brother.color = node.parent!!.color
            node.color = Color.BLACK
            node.parent!!.color = Color.BLACK
            brother.left!!.color = Color.BLACK
            rightrotate(node.parent!!)
            return
        }
    }
    fun maxNode(node : RBNode<T,E>? = root):RBNode<T,E>?{
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
    fun minNode(node : RBNode<T,E>? = root):RBNode<T,E>?{
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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is rbbst<*, *>) return false
        var iterator = this.iterator()

        for (node in other){
            if (!iterator.hasNext()) return false
            if (node != iterator.next()) return false
        }
        if (iterator.hasNext()) return false
        return true
    }
    fun getheight(key: T): Int {
        var currentNode: RBNode<T,E>? = root
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


