/**
 * Created by Holden Caulfield on 03.03.2017.
 */
class RBNode<K: Comparable<K>, V>(var key: K, var value: V, var color: Color = Color.BLACK){
    var left: RBNode<K,V>? = null
    var right: RBNode<K,V>? = null
    var parent: RBNode<K,V>? = null
    fun recoloring() {
        if(this.color == Color.BLACK)  this.color = Color.RED
        else{
            this.color = Color.BLACK
        }
    }

    fun brother(): RBNode<K, V>? {
        if (this == this.parent?.left) return this.parent!!.right
        return this.parent?.left
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as RBNode<*, *>

        if (key != other.key) return false
        if (value != other.value) return false
        if (color != other.color) return false

        return true
    }
}