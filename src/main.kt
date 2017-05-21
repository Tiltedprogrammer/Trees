import B_Tree.*
import RedBlackTree.*
import BinarySearchTree.*

/**
 * Created by Holden Caulfield on 05.03.2017.
 */

fun main(args: Array<String>) {
    val tree: Tree<Int, Int>
    var line: String?
    val printer: Printer<Int, Int>

    println("""Choose type fo tree (just type a word):
BST - Binary search tree
RBT - Red black tree
B - B tree.""")

    loop1@ while (true) {
        line = readLine()
        when (line) {
            "BST" -> {
                tree = bst<Int, Int>() as Tree<Int, Int>
                printer = BSTPrinter<Int, Int>() as Printer<Int, Int>
                break@loop1
            }
            "RBT" -> {
                tree = rbbst<Int, Int>() as Tree<Int, Int>
                printer = RBPrinter<Int, Int>() as Printer<Int, Int>
                break@loop1
            }
            "B" -> {
                println("Enter tree parameter")
                line = readLine()
                if (line != null && line.matches(Regex("\\d+"))) {
                    if (line.toInt() != 1) {
                        tree = BTree<Int, Int>(line.toInt()) as Tree<Int, Int>
                        printer = BPrinter<Int, Int>() as Printer<Int, Int>
                        break@loop1
                    } else {
                        println("Invalid tree. Try again.")
                        continue@loop1
                    }
                } else {
                    println("Invalid tree. Try again.")
                    continue@loop1
                }
            }
            else -> {
                println("Invalid tree. Try again.")
                continue@loop1
            }

        }
    }
    println("""Type A,S,D,p or Q for further actions(Int as argument):
A %argument% to add
S %argument% to search
D %argument% to delete
P for print
Q to quit""")
    loop2@ while (true) {
        line = readLine()
        when (line) {
            null -> {
                println("Input is null. Try again.")
                continue@loop2
            }
            else -> {
                val splited: List<String> = line.trim().split(" ")
                val key: Int?
                when {
                    splited.size < 2 -> key = null
                    else -> {
                        if (splited[1].matches(Regex("^-?\\d+"))) {
                            key = splited[1].toInt()
                        } else {
                            println("Invalid key, try again.")
                            continue@loop2
                        }
                    }
                }
                when (splited[0]) {
                    "A" -> {
                        if (key == null) {
                            println("Key is null. Try again.")
                            continue@loop2
                        }
                        tree.insert(key, key)
                    }
                    "S" -> {
                        if (key == null) {
                            println("Key is null. Try again.")
                            continue@loop2
                        }
                        println(tree.search(key))
                    }
                    "D" -> {
                        if (key == null) {
                            println("Key is null. Try again.")
                            continue@loop2
                        }
                        tree.delete(key)
                    }
                    "P" -> {
                        printer.print(tree)
                    }
                    "Q" -> break@loop2
                    else -> {
                        println("Invalid operation with tree. Try again.")
                        continue@loop2
                    }
                }
            }
        }
    }
}
