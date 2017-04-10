/**
 * Created by Holden Caulfield on 06.03.2017.
 */
class BinIterator<T: Comparable<T>, E>(val tree: bst<T, E>): Iterator<Node<T,E>> {

    var next = tree.maxNode()
    override fun hasNext():Boolean {
        return next != null
    }
    override fun next() : Node<T,E>{
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