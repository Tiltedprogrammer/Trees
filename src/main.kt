import javafx.scene.chart.Chart

/**
 * Created by Holden Caulfield on 05.03.2017.
 */

fun main(args: Array<String>) {
    val tree = rbbst<Int,Int>()
    tree.insert(7,7)
    tree.insert(1,1)
    tree.insert(4,4)
    tree.insert(8,8)
    tree.insert(3,3)
    tree.insert(5,5)
    tree.insert(6,6)
    tree.insert(12,12)
    tree.delete(7)
    tree.delete(6)
    tree.delete(4)
    //tree.delete(5)
   //tree.delete(8)
    RBPrinter<Int,Int>().print(tree)
}
