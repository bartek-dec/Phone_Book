# Phone_Book
### This is a console program to present difference in time efficiency between different sorting and searching algorithms.
### Program reads in the content of two files:
#### - directory.txt - it plays a role as a phone book (it contains phone number and name - 1014300 contacts in total) 
#### - find.txt - it represents the list of 500 names of which the program will try to find in the aforementioned phone book.
### Program displays results (time required to complete the task) for the following cases:
#### - linear search in the unsorted list
#### - bubble sort and jump search - if bubble sort takes more than 20 s, the sorting process is being stopped and the program completes task using linear search
#### - quick sort and jump search
#### - quick sort and binary search
#### - creating hash table and searching in it.