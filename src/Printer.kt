/**
 * Created by Holden Caulfield on 21.05.2017.
 */
internal interface Printer<T: Comparable<T>,K>{
    fun print(tree: Tree<T,K>)
}