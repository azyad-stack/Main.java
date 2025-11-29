
The goal of this assignment is to implement different hash tables in Java.

You are given the skeletons of four classes to complete :

	- HashTable: this is an abstract class (the superclass of the other three classes). 
	DO NOT MODIFY THIS CLASS.

	- StaticHashTable: a class used to implement static hash tables using the multiply-shift method discussed in the course. 
	You should complete this class.

	- RandomHashTable: a class that implements random hash tables using the multiply-shift method to define the family of hash functions. 
	You should complete this class.

	- OpenAddressingHashTable: a class that implements a hash table using open addressing for conflict resolution. We use double probing. 
	You should complete this class.


How to proceed :
================ 

1 - read carefully the content of the class HashTable. 
All attributes and methodes are commented.
You shlould not modify this class.


2 - start the implementation of StaticHashTable : 

a - Read and understand the class attributs and constructors.

b - The hash function is already implemented (the multiply-shift method).

c - Complete the code of insert method :  to support size update and resizing when load factor > MAX_LOAD

d - The search function is already impmemented

e - You can now test your StaticHashTable implementation in the Main class. 

f - Implement the delete method. take care of updating the size. but do not resize.

g- Complete the three remaining functions (keys, values, clear) and test in Main class.




3 - Give you implementation for RandomHashTable, and test it in the Main class


4 - Give you implementation for OpenAddressingHashTable, and test it in the Main class.



5 - Using One of your hash table class solve these problems in the Main class :

a - Problem 1. Two-Sum Problem
------------------------------
You are given an array of integers T and an integer N.
Your task is to write a function that returns the indices of two numbers in the array whose sum equals N.


b - Problem 2. Anagram Detection
--------------------------------
Two strings are said to be anagrams if they contain exactly the same characters, but possibly in a different order.
For example:
	"listen" and "silent" are anagrams
	"triangle" and "integral" are anagrams
	"apple" and "pale" are not anagrams
Your task is :
Write a function that takes two strings s1 and s2 and returns:
	true if they are anagrams
	false otherwise
You must ignore:
	Capitalization (e.g., "Dormitory" and "dirty room" should be treated as anagrams)
	Spaces and punctuation


-------------------------------------------------------------------------
What to return (upload):
========================

When you finish your homework you should upload 5 java files into Teams : 
- Main.java 
- HashTable.java 
- StaticHashTable.java 
- RandomHashTable.java
- OpenAddressingHashTable.java

Only these 5 files :)  


