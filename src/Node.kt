/**
 * Created by Holden Caulfield on 09.04.2017.
 */
class Node<K:Comparable<K>,V>(val key:K, val value: V):TreeNode<K,V> {
    var left: Node<K,V>? = null
    var right: Node<K,V>? = null
    var parent: Node<K,V>? = null
}