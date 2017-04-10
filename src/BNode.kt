/**
 * Created by Holden Caulfield on 04.04.2017.
 */
class BNode<K: Comparable<K>,V> {
    var leaf: Boolean = true
    var keys = mutableListOf<Pair<K,V>>()
    var Nodes = mutableListOf<BNode<K,V>>()
    override fun toString(): String {
        var s = "["
        for (i in 0..keys.size-2){
            s+=keys[i].first.toString()+","
        }
        s += keys[keys.size-1].first.toString()
        s+="]"
        return s
    }


}