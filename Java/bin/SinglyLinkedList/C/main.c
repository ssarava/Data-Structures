// main function to initialize SLL and perform operations

#include <stdio.h>
#include <string.h>
#include "SLL.h"

int main(int argc, char* argv[]) {
    // int echo = 0;                                // controls echoing, 0: echo off, 1: echo on
    // if(argc > 1 && strcmp("-echo",argv[1])==0) { // turn echoing on via -echo command line option
    //     echo=1;
    // }

    // printf("Singly Linked List Demo\n");
    // printf("Commands:\n");
    // printf("  print:                shows the current contents of the list\n");
    // printf("  clear:                eliminates all elements from the list\n");
    // printf("  exit:                 exit the program\n");
    // printf("  get size:             gets the current size of the list\n");
    // printf("  add to end:           adds the value to the end of the list\n");
    // printf("  add to front:         adds the value to the front of the list\n");
    // printf("  get first:            gets the first element in the list\n");
    // printf("  get last:             gets the last element in the list\n");
    // printf("  retrieve first:       gets and removes the first element in the list\n");
    // printf("  retrieve last:        gets and removes the last element in the list\n");
    // printf("  remove all:           removes all instances of the specified element from the list\n");
    // printf("  is list empty:        prints 'Yes' if the list is empty; prints 'No' otherwise\n");
    // printf("  contains thing:       determine if the given thing is in the list\n");
    // printf("  index of thing:       gets the index of the thing in the list; if doesn't exist, prints 'doesn't exist'\n");
    // printf("  last index of thing:  gets the index of the thing in the list; if doesn't exist, prints 'doesn't exist'\n");
    // printf("  set element at index: sets the element at index to the specified value\n");
    // printf("  remove at index:      removes the element at the index, shifting subsequent nodes 'to the left'\n");
    // printf("  add at index:         adds the element at the index, shifting subsequent nodes 'to the right'\n");
    // printf("                        (NOT YET IMPLEMENTED)\n");

    // char cmd[128];              // buffer that contains the vector of commands
    // singly_linked_list_t list;  // singly linked list struct to initialize
    // int success;                // 'success' variable that contains value after reading in commands
    // list_init(&list);           // 'list' is initialized (remember, the function takes a pointer/memory address as an argument)

    // // the loop always runs (we want the program to continue till we break out of it by quiting)
    // while (1) {
    //     printf("list>");
    //     success = fscanf(stdin, cmd);   // we want to read input from 'stdin' (the console) into our buffer
        
    //     // if the "end of (file) input" is detected
    //     if (success == EOF) {
    //         printf("\n");   // jump to next line
    //         break;
    //     }

    //     if (strcmp("exit", cmd) == 0) { // if user wants to exit
    //         if (echo) {
    //             printf("Exiting the program...");   // 'exit' prints only if echo is turned on
    //         }
    //         break;      // break out of loop, jumps to return statement
    //     } else if (strcmp("print", cmd) == 0) {
    //         if (echo) {
    //             printf("Printing the list...\n");
    //             print_list(&list);
    //         }
    //     } else if (strcmp("clear", cmd) == 0) {
    //         if (echo) {
    //             printf("Clearing the list...\n");
    //             clear_list(&list);
    //         }
    //     } else if (strcmp("get size", 0) == 0) {
    //         printf("Size of the list = %d\n", list.size);
    //     } else if (strcmp("add to end", 0) == 0) {
    //         int data_to_add;
    //         fscanf(stdin, "%d", data_to_add);
    //         if (echo) {
    //             printf("adding %d to end\n", data_to_add);
    //         }
    //         addToEnd(&list, data_to_add);
    //     } else if (strcmp("add to front", cmd) == 0) {
    //         // ...
    //     }
    // }
    printf("Hello world!\n");
    return 0;
}