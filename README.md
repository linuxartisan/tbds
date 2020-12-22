# Tree-based Data Structure

## About
Java implementation of a tree-based data structure (TBDS), to store a set of key-value pairs, where both keys and values are strings. Every node (except the root) of TBDS stores a key-value pair. In addition, every node in TBDS (including the root) stores a hash map of ```<character,TBDSNode>```, where keys are characters and TBDSNodes (other nodes in TBDS) are the values stored in that hash map.

**How TBDS stores key-value pairs:**

Every node in TBDS can have zero or more children. This tree structure allows you to search for a string key in the structure by repeatedly looking up the node associated with the next character in the key, if it exists. Consider the following example of TBDS that contains some keys and values as follows (```null``` indicates no value has been stored for that key).

|Keys |Values |
|:-----|:----|
|a | a|
|an | an|
|and | and|
|ant | ant|
|m | null|
|ma | null|
|mam | mam|
|man | man|
|many | many|
|z | null|
|zo | null|
|zoo | zoo|
|zoom | zoom|

The tree representation of this data is present in the file ```sample-tbds.png```.

The ```words.txt``` file contains sample words that one may use to build the tree.

## Usage
Compile the code:
```
javac *.java
```

Create your own ```main()``` method. A sample is present in the ```TBDS.java``` file.
