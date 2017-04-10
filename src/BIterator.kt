import java.util.*

/**
 * Created by Holden Caulfield on 09.04.2017.
 */
class BIterator<K:Comparable<K>,V>(var Node: BNode<K,V>?): Iterator<BNode<K,V>>{
    var queue = LinkedList<BNode<K, V>>()

    init {
        if (Node != null){
            queue.add(Node!!)
        }

    }

    override fun hasNext(): Boolean {
        return  queue.isNotEmpty()
    }

    override fun next(): BNode<K, V> {
        var tnode = queue.poll()
        for (i in 0..tnode.Nodes.size-1){
            queue.add(tnode.Nodes[i])
        }
        return tnode
    }

}