/**
 * Created by Holden Caulfield on 12.03.2017.
 */
interface Tree<K: Comparable<K>, V>{
    //fun getroot(): Node<K,V>?
    //fun setroot(node: Node<T,E>?)
    fun insert(key: K?, value: V?)
    fun search(key : K?): V?
    fun delete(key: K?)

}