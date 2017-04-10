/**
 * Created by Holden Caulfield on 12.03.2017.
 */
interface Tree<K: Comparable<K>, V>{
    //fun getroot(): Node<K,V>?
    //fun setroot(node: Node<T,E>?)
    fun insert(key: K, value: V)
    fun search(key : K): V?
    fun delete(key: K)
    /**fun getheight(key: T): Int {
        var currentNode: Node<T,E>? = getroot()
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
    fun maxNode(node : Node<T,E>? = getroot() ):Node<T,E>?{
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
    fun minNode(node : Node<T,E>? = getroot() ):Node<T,E>?{
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
    }**/

}