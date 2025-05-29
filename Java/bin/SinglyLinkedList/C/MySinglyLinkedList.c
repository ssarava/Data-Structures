// MySinglyLinkedList.c: utility functions for operating on a singly linked list. 
// Most functions are used in SLL_main.c which provides an application to
// work with the functions.
#include "SLL.h"

// creates a node
node_t *node_init(int data) {
    node_t *node;
    node->data = data;
    node->next = NULL;
    return node;
}

// initializes fields of the list; similar to constructor in java
void list_init(singly_linked_list_t *list) {
    list->head = NULL;
    list->size = 0;
}

// clears the list of all elements
void clear_list(singly_linked_list_t *list) {
    node_t *curr = list->head;

    // frees nodes as such: points to 2nd, wipes out 1st; re-points 2nd to 3rd, wipes out 2nd; re-points 3rd to 4th, wipes out 3rd; etc.
    while (curr != NULL) {
        node_t *to_free = curr;
        to_free = to_free->next;
        free(to_free);
    }
    list->head = NULL;
    list->size = 0;
    return;
}

void print_list(singly_linked_list_t *list) {
    node_t *curr = list->head;
    
    while (curr != NULL) {
        printf("[%d] --> ", curr->data);
    }
    printf("NULL\n");
}

// returns the size of the linked list
int getSize(singly_linked_list_t *list) {
    return list->size;
}

// returns 1 if linked list is empty; returns 0 otherwise
// char isEmpty(singly_linked_list_t *list) {
//     return list->size == 0 ? 1 : 0;
// }

void addToEnd(singly_linked_list_t *list, int data) {
    node_t *node = node_init(data);
    if (list->size == EMPTY_LINKED_LIST) {
        list->head = node;
    } else {
        node_t *curr = list->head;
        while (curr->next != NULL) {
            curr = curr->next;
        }
        curr->next = node;
    }
    list->size ++;
    
    return;
}

void addToFront(singly_linked_list_t *list, int data) {
    if (list->size == EMPTY_LINKED_LIST) {
        list->head->data = data;
    } else {
        node_t *node = node_init(data);
        node->next = list->head;
        list->head = node;
    }
    list->size ++;

    return;
}

int getFirst(singly_linked_list_t *list) {
    return list->head == NULL ? -1 : list->head->data;
}

int getLast(singly_linked_list_t *list) {
    if (list->size == EMPTY_LINKED_LIST) {
        return -1;
    } else if (list->size == HEAD_ONLY) {
        return list->head->data;
    } else {
        node_t *curr = list->head;
        while (curr->next != NULL) {
            curr = curr->next;
        }
        return curr->data;
    }
}

int retrieveFirstElement(singly_linked_list_t *list) {
    if (list->size == EMPTY_LINKED_LIST) {
        return -1;
    }
    int data;
    data = list->head->data;
    node_t *pointer = list->head;
    if (list->size == HEAD_ONLY) {
        free(pointer);
        list->head = NULL;
    } else {
        list->head = list->head->next;
        free(pointer);
    }
    list->size --;
    return data;
}

int retrieveLastElement(singly_linked_list_t *list) {
    if (list->size == EMPTY_LINKED_LIST) {
        return -1;
    }
    int data;
    node_t *pointer;
    if (list->size == HEAD_ONLY) {
        pointer = list->head;
        data = list->head->data;
        list->head = NULL;
        free(pointer);
    } else {
        node_t *curr = list->head;
        while (curr->next->next != NULL) {
            curr = curr->next;
        }
        pointer = curr->next;
        data = curr->next->data;
        curr->next = NULL;
        free(pointer);
    }
    list->size --;
    return data;
}

void removeAllInstances(singly_linked_list_t *list, int target_data) {
    node_t *curr = list->head;
    node_t *temp_head = list->head;
    char non_target_node = 0;

    while (curr != NULL) {
        if (curr->data == target_data) {
            if (!non_target_node) {
                if (list->head == curr) {
                    list->head = curr->next;
                    curr = list->head;
                    free(temp_head);
                    temp_head = curr;
                } else {
                    curr = curr->next;
                    temp_head = temp_head->next->next;
                }
            } else {
                if (temp_head == curr) {
                    temp_head = curr->next;
                    curr = temp_head;
                    temp_head = curr;
                } else {
                    curr = curr->next;
                    temp_head->next = temp_head->next->next;
                }
            }
            list->size --;
        } else {
            curr = curr->next;
            if (temp_head->next != curr) {
                temp_head = temp_head->next;
            }
            if (!non_target_node) {
                non_target_node = 1;
            }
        }
    }
    
    return;
}

char contains(singly_linked_list_t *list, int data) {
    node_t *curr = list->head;
    while (curr != NULL) {
        if (curr->data == data) {
            return 1;
        }
        curr = curr->next;
    }
    return 0;
}

int indexOf(singly_linked_list_t *list, int data) {
    node_t *curr = list->head;
    int index = 0;
    while (curr != NULL) {
        if (curr->data == data) {
            return index;
        }
        curr = curr->next;
        index ++;
    }
    return -1;
}

int lastIndexOf(singly_linked_list_t *list, int data) {
    node_t *curr = list->head;
    int index = 0, last_index = -1;
    while (curr != NULL) {
        if (curr->data == data) {
            last_index = index;
        }
        curr = curr->next;
        index ++;
    }
    return last_index;
}

int set(singly_linked_list_t *list, int index, int element) {
    if (index < 0 || index >= list->size) {
        return -1;
    }
    int curr_index = 0;
    node_t *curr = list->head;
    while (curr != NULL && curr_index < index) {
        curr = curr->next;
        curr_index ++;
    }
    int prev_data = curr->data;
    curr->data = element;
    return prev_data;
}

int remove_node(singly_linked_list_t *list, int index) {
    if (index < 0 || index >= list->size) {
        return -1;
    }
    int data;
    node_t *pointer;
    if (index == 0) {
        data = list->head->data;
        pointer = list->head;
        list->head = NULL;
        free(pointer);
    } else {
        int curr_index = 0;
        node_t *curr = list->head;
        while (curr != NULL && curr_index < index - 1) {
            curr = curr->next;
            curr_index ++;
        }
        pointer = curr->next;
        data = pointer->data;
        curr->next = curr->next->next;
        free(pointer);
    }
    list->size --;
    return data;
}

void add(singly_linked_list_t *list, int index, int element) {
    if (index < 0 || index >= list->size) {
        return;
    }
    node_t *node = node_init(element);
    if (index == 0) {
        node->next = list->head;
        list->head = node;
    } else {
        node_t *curr = list->head;
        int curr_index = 0;
        while (curr != NULL && curr_index < list->size - 1) {
            curr = curr->next;
            curr_index ++;
        }
        node->next = curr->next;
        curr->next = node;
    }
    list->size ++;
}