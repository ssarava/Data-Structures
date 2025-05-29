// This header contains the struct for a node and macros for easy operations
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define EMPTY_LINKED_LIST   0       // size of an empty linked ist
#define HEAD_ONLY           1       // size of a linked list with only one element/node

typedef struct node_struct {
    int data;                       // every node contains data
    struct node_struct *next;       // every node points to a "next" node
} node_t;

typedef struct {
    int size;           // size of the singly-linked list
    node_t *head;       // pointer to the head of the singly-linked list
} singly_linked_list_t;

// functions defined in MySinglyLinkedList.c
node_t *node_init(int data);                               // creates a node
void list_init(singly_linked_list_t *list);             // initializes fields of the list; similar to constructor in java
void clear_list(singly_linked_list_t *list);
void print_list(singly_linked_list_t *list);

int getSize(singly_linked_list_t *list);              // returns the size of the linked list
void addToEnd(singly_linked_list_t *list, int data);    // adds to the end of the linked list
void addToFront(singly_linked_list_t *list, int data);  // adds to the front of the linked list

int getFirst(singly_linked_list_t *list);             // returns the first element
int getLast(singly_linked_list_t *list);              // returns the last element

int retrieveFirstElement(singly_linked_list_t *list);     // removes and returns the first element
int retrieveLastElement(singly_linked_list_t *list);      // removes and returns the last element

void removeAllInstances(singly_linked_list_t *list, int targetData);    // removes all instances of 'targetData'
// char isEmpty(singly_linked_list_t *list);                 // returns 1 if linked list is empty; returns 0 otherwise
char contains(singly_linked_list_t *list, int data);        // returns 1 if the linked list contains 'data; returns 0 otherwise

int indexOf(singly_linked_list_t *list, int data);          // returns the index at which 'data is located in the linked list; returns -1 otherwise
int lastIndexOf(singly_linked_list_t *list, int data);      // returns the last index at which 'data is located in the linked list; returns -1 otherwise

int set(singly_linked_list_t *list, int index, int element);    // sets the data of the node at index 'index' to 'element'
int remove_node(singly_linked_list_t *list, int index);              // removes the node at 'index'; pulls nodes to the left
void add(singly_linked_list_t *list, int index, int element);   // adds at 'index'; pushes nodes to the right
