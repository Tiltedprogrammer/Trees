/**
 * Created by Holden Caulfield on 04.04.2017.
 */
import java.util.*
class BTree<K: Comparable<K>, V>(val t: Int): Tree<K,V>,Iterable<BNode<K,V>>{
    var root: BNode<K,V>? = null
    override fun iterator(): Iterator<BNode<K, V>> = BIterator(root)
    override fun search(key: K):V?{
        var pair = InternalSearch(key)
        if(pair == null) return null
        return pair.first.keys[pair.second].second
    }
    private fun InternalSearch(key: K, Node :BNode<K,V>? = root): Pair<BNode<K,V>,Int>?{
        if(Node == null) return null
        var i = 0
        var n = Node.keys.size
        while((i < n) &&(key > Node.keys[i].first)){
            i += 1
        }
        if((i < n) && key == Node.keys[i].first)
            return Pair(Node,i)
        else if(Node.leaf) return null
        else return InternalSearch(key, Node.Nodes[i])
    }
    private fun split(Node: BNode<K,V>, i: Int){
        var z = BNode<K,V>()
        var y = Node.Nodes[i]
        z.leaf = y.leaf
        for(j in 0 .. t - 2){
            z.keys.add(y.keys[t])
            y.keys.removeAt(t)
        }
        if(!y.leaf){
            for(j in 0 .. t-1){
                z.Nodes.add(y.Nodes[t])
                y.Nodes.removeAt(t)
            }
        }
        Node.Nodes.add(i+1,z)
        Node.keys.add(i,y.keys[t-1])
        y.keys.removeAt(t-1)
    }
    private fun insertNonFull(Node : BNode<K,V>, key: K, value: V){
        var size = Node.keys.size
        var i = 0
        if(Node.leaf){
            while((i < size) && (key > Node.keys[i].first)) i += 1
            Node.keys.add(i,Pair(key,value))

        }
        else{
            while( i < size && key > Node.keys[i].first) i += 1
            if(Node.Nodes[i].keys.size == 2*t - 1){
                split(Node,i)
                if(key > Node.keys[i].first) i += 1
            }
            insertNonFull(Node.Nodes[i],key,value)
        }
    }
    override fun insert(key : K, value: V): Boolean{
        if(root == null){
            this.root = BNode<K,V>()
            root!!.keys.add(Pair(key,value))
            return true
        }
        var r = this.root
        if(key in r!!.keys.map{it.first}) return false
        if(r.keys.size == 2*t-1){
            var s = BNode<K,V>()
            this.root = s
            s.leaf = false
            s.Nodes.add(r)
            split(s,0)
            insertNonFull(s, key,value)
        }
        else insertNonFull(r,key, value)
        return true



    }
    override fun delete(key: K){
        //if(key == null) return
        //if(search(key) == null) return false
        InternalDelete(key)
    }
    private fun InternalDelete(key: K, startNode: BNode<K,V>? = root):Boolean{
        var delNode: BNode<K,V>? = startNode?: return false
        if(key in delNode!!.keys.map{it.first} && delNode.leaf ){
            var i = 0
            while(i < delNode.keys.size && key > delNode.keys[i].first){
                i++
            }
            delNode.keys.removeAt(i)

        }
        else if(key in delNode.keys.map{it.first} && !delNode.leaf){
            var i = 0
            while(i < delNode.keys.size && key > delNode.keys[i].first){
                i++
            }
            if(delNode.Nodes[i].keys.size > t - 1){
                val tmpkey = maxByNode(delNode.Nodes[i])
                InternalDelete(tmpkey.first, delNode.Nodes[i])
                delNode.keys[i] = tmpkey

            }
            else if(delNode.Nodes[i+1].keys.size > t - 1){
                val tmpkey = minByNode(delNode.Nodes[i+1])
                InternalDelete(tmpkey.first, delNode.Nodes[i+1])
                delNode.keys[i] = tmpkey
            }
            else{
                val leftNode = delNode.Nodes[i]
                val rightNode = delNode.Nodes[i+1]
                leftNode.keys.add(delNode.keys[i])
                delNode.keys.removeAt(i)
                delNode.Nodes.removeAt(i+1)
                for (j in 0 ..t - 2) {
                    leftNode.keys.add(rightNode.keys.first())
                    rightNode.keys.removeAt(0)
                    //leftNode.Nodes.add(rightNode.Nodes[0])
                    //rightNode.Nodes.removeAt(0)
                }
                if(!leftNode.leaf){
                    for(j in 0 .. t-1){
                        leftNode.Nodes.add(rightNode.Nodes[0])
                        rightNode.Nodes.removeAt(0)
                    }
                }
                if (delNode == root && delNode.keys.size == 0) {
                    root = delNode.Nodes[i]
                    delNode.Nodes.removeAt(i)
                }
                InternalDelete(key, leftNode)

            }
        }
        else if(key !in delNode.keys.map { it.first }){
            if(delNode.leaf){
                return false
            }
            else{
                var i = 0
                while(i < delNode.keys.size && key > delNode.keys[i].first){
                    i++
                }
                var parent = delNode
                delNode = delNode.Nodes[i]
                if(delNode.keys.size > t-1){
                    InternalDelete(key, delNode)
                }
                else{
                    if(i > 0 && parent.Nodes[i-1].keys.size > t-1){
                            val leftneighbour = parent.Nodes[i-1]
                            delNode.keys.add(0,parent.keys[i-1])
                            parent.keys[i-1] = leftneighbour.keys.last()
                            if(!delNode.leaf){
                                delNode.Nodes.add(0,leftneighbour.Nodes.last())
                                leftneighbour.Nodes.removeAt(leftneighbour.Nodes.size - 1)
                            }
                            leftneighbour.keys.removeAt(leftneighbour.keys.size - 1)
                            InternalDelete(key, delNode)
                        }
                    else if(i < parent.keys.size && parent.Nodes[i+1].keys.size > t - 1 ){
                            val rightneighbour = parent.Nodes[i+1]
                            delNode.keys.add(parent.keys[i])
                            parent.keys[i] = rightneighbour.keys[0]
                            if(!delNode.leaf){
                                delNode.Nodes.add(rightneighbour.Nodes[0])
                                rightneighbour.Nodes.removeAt(0)
                            }
                            rightneighbour.keys.removeAt(0)
                            InternalDelete(key,delNode)
                        }
                    else if(i > 0) {
                        val leftneighbour = parent.Nodes[i-1]
                        leftneighbour.keys.add(parent.keys[i-1])
                        parent.keys.removeAt(i-1)
                        parent.Nodes.removeAt(i)
                        for(j in 0 .. t - 2){
                            leftneighbour.keys.add(delNode.keys[0])
                            delNode.keys.removeAt(0)
                        }
                        if(!leftneighbour.leaf){
                            for(j in 0 .. t-1){
                                leftneighbour.Nodes.add(delNode.Nodes[0])
                                delNode.Nodes.removeAt(0)
                            }
                        }
                        if(parent.keys.size == 0){
                            if(parent == root){
                                root = parent.Nodes[i-1]
                                parent.Nodes.removeAt(i-1)
                            }
                            else{
                                println("Exception")
                            }
                        }
                        InternalDelete(key, leftneighbour)


                    }
                    else if(i < parent.Nodes.size){
                        val rightneighbour = parent.Nodes[i+1]
                        delNode.keys.add(parent.keys[i])
                        parent.keys.removeAt(i)
                        parent.Nodes.removeAt(i+1)
                        for(j in 0 .. t - 2){
                            delNode.keys.add(rightneighbour.keys[0])
                            rightneighbour.keys.removeAt(0)
                        }
                        if(!delNode.leaf){
                            for(j in 0 .. t-1){
                                delNode.Nodes.add(rightneighbour.Nodes[0])
                                rightneighbour.Nodes.removeAt(0)
                            }
                        }
                        if(parent.keys.size == 0){
                            if(parent == root){
                                root = parent.Nodes[i]
                                parent.Nodes.removeAt(i)
                            }
                            else{
                                println("Exception")
                            }
                        }
                        InternalDelete(key, delNode)

                    }

                }
            }
        }
        return true
    }

    /**override fun iterator(): Iterator<BNode<K, V>> {
        return (object : Iterator<BNode<K, V>>{
            var queue = LinkedList<BNode<K, V>>()

            init {
                if (root != null){
                    queue.add(root!!)
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

        })
    }**/
    private fun maxByNode(node: BNode<K, V>): Pair<K,V> {
        var currentNode = node
        while (!currentNode.leaf) {
            currentNode = currentNode.Nodes.last()
        }
        return currentNode.keys.last()
    }

    private fun minByNode(node: BNode<K, V>): Pair<K,V> {
        var currentNode = node
        while (!currentNode.leaf) {
            currentNode = currentNode.Nodes[0]
        }
        return currentNode.keys[0]
    }

}