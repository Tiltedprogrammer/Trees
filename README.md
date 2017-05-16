# Trees
#### Ordered(1..n)

Nodes | Simple BST | B_Tree
----- | ---------- | ------
10k | Insert 376 ms <br> Search 0.529067 ms <br> Delete 0.658987 ms | Insert 118 ms <br> Search 0,0091 ms <br> Delete 0,024 ms
100k | Insert 33231 ms <br> Search 5.712645 ms<br> Delete 7,529233 ms | Insert 398ms <br> Search 0,016 ms <br> Delete 0,033 ms
1 mln | Insert too long <br> Search too long <br> Delete too long| Insert 1581 ms <br> Search 0,0086 ms <br> Delete 0,034 ms
100 mln |  Insert too long <br> Search too long <br> Delete too long| Insert 27458 ms <br> 0.016 ms <br> Delete 0,043 ms
-----------

#### Random
Nodes | Simple BST | B_Tree
----- | ---------- | ------
10k | Insert 13 ms <br> Search 0.047 ms <br> Delete 0.013 ms | Insert 137 ms <br> Search 0,011 ms <br> Delete 0,036 ms
100k | Insert 154 ms <br> Search 0,048 ms<br> Delete 0,035 ms | Insert 557 ms <br> Search  0,019 ms <br> Delete 0,033 ms
1 mln | Insert 1947 ms <br> Search 0,032 ms <br> Delete 0,046 ms| Insert 4530 ms <br> Search 0,019 ms <br> Delete 0,023 ms
100 mln |  Insert 24045 ms <br> Search 0,047 ms <br> Delete 0,117 ms| Insert 27458 ms <br> 0,015 ms <br> Delete 0,021 ms
-----------
     
