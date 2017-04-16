package RedBlackTree
/**
 * Created by Holden Caulfield on 09.04.2017.
 */
class RBIterator<T:Comparable<T>,E>(val tree: rbbst<T,E>):Iterator<RBNode<T,E>>{
    var next = tree.maxNode()
    override fun hasNext():Boolean {
        return next != null
    }
    override fun next() : RBNode<T,E>{
        if(next == null){
            throw NullPointerException()
        }
        val prevNode = next
        if (next!!.left != null){
            next = tree.maxNode(next!!.left)
        }
        else{
            while (next != tree.root && next == next!!.parent!!.left){
                next = next!!.parent
            }
            next = next!!.parent
        }
        return prevNode!!
    }
}