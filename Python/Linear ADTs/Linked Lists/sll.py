
class Node:
    
    def __init__(self,
                 val: int = None,
                 next = None):
        self.val = val
        self.next = next
        
class SinglyLinkedList:
    """My implementation of a Singly Linked List. I implemented whichever methods from JavaDocs's LinkedList page I found relevant"""
    
    def __init__(self):
        self.size = 0
        self.head: Node = None
    
    def __repr__(self):
        elements = []
        curr = self.head
        while curr:
            elements.append(curr.val)
            curr = curr.next
        return f"{elements}"
    
    def is_empty(self):
        """Returns whether the list is empty"""
        return self.size == 0
    
    def clear(self):
        """Removes all elements from the list"""
        self.head = None
        self.size = 0
    
    def add_to_head(self, val):
        """Adds 'val' to the beginning of the list"""
        if self.is_empty():
            self.head = Node(val)
        else:
            new_node = Node(val, self.head)
            self.head = new_node
        self.size += 1
    
    def add_to_tail(self, val):
        """Appends 'val' to the end of the list"""
        if self.is_empty():
            self.head = Node(val)
        else:
            new_node = Node(val)
            curr = self.head
            while curr.next is not None:
                curr = curr.next
            curr.next = new_node
        self.size += 1
        
    def insert_at_index(self, val, ind: int):
        """Inserts 'val' at index 'ind' if it's valid"""
        if ind < 0:
            raise ValueError(f"Can't insert at a negative index!")
        elif ind >= self.size:
            raise ValueError(f"SLL doesn't contain enough elements to insert at index {ind}")
        elif ind == 0:
            self.add_to_head(val)
        else:
            new_node = Node(val)
            curr = self.head
            for _ in range(ind - 1):
                curr = curr.next
                
            new_node.next = curr.next
            curr.next = new_node
            self.size += 1
                
    def contains(self, value):
        """Returns whether the list contains 'value'"""
        curr = self.head
        while curr:
            if curr.val == value:
                return True
            curr = curr.next
        return False
    
    def remove(self, ind: int):
        """Removes the element at index 'ind' if it's valid"""
        if ind < 0:
            raise ValueError(f"Can't remove at a negative index!")
        elif ind >= self.size:
            raise ValueError(f"SLL doesn't contain enough elements to remove at index {ind}")
        else:
            removed = None
            curr = self.head
            for _ in range(ind - 1):
                curr = curr.next
            removed = curr.next.val
            curr.next = curr.next.next
        self.size -= 1
        return removed
    
    def remove_first(self):
        """Removes and returns the first element in the list"""
        return self.remove(0)
    
    def remove_last(self):
        """Removes and returns the last element in the list"""
        return self.remove(self.size - 1)
        
    def remove_all(self, value):
        """Removes all occurrences of 'value' in the list"""
        prev, temp_head, curr = self.head, self.head, self.head
        while curr:
            if curr.val == value:
                self.size -= 1
                if curr is self.head:
                    temp_head = curr.next
                    prev = temp_head
                    self.head = temp_head
                else:
                    prev.next = curr.next
            else:
                prev = curr
            curr = curr.next
        self.head = temp_head
    
    def get(self, ind: int):
        """Retrieves, but doesn't remove, the value at index 'ind' if it's valid"""
        if ind < 0:
            raise ValueError(f"Can't insert at a negative index!")
        elif ind >= self.size:
            raise ValueError(f"SLL doesn't contain enough elements to insert at index {ind}")
        curr = self.head
        for _ in range(ind):
            curr = curr.next
        return curr.val
    
    def getFirst(self):
        """Retrieves, but doesn't remove, the first value in the list"""
        if self.size == 0:
            raise ValueError(f"List is empty! Add values.")
        return self.head.val
    
    def get_last(self):
        """Retrieves, but doesn't remove, the last value in the list"""
        if self.size == 0:
            raise ValueError(f"List is empty! Add values.")
        return self.get(self.size - 1)
    
    def index_of(self, value):
        """Returns the index of the first occurrence of 'value', or -1 if the value doesn't exist in the list"""
        curr = self.head
        for index in range(self.size):
            if curr.val == value:
                return index
            curr = curr.next
        return -1
    
    def replace(self, ind: int, element):
        """Sets the value at index 'ind' to 'element', and returns the replaced element"""
        curr = self.head
        for _ in range(ind):
            curr = curr.next
        replaced = curr.val
        curr.val = element
        return replaced
    
    def set(self) -> set:
        uniques = set()
        curr = self.head
        while curr:
            uniques.add(curr.val)
            curr = curr.next
        return uniques
    
    def to_array(self) -> list:
        curr, arr = self.head, []
        while curr:
            arr.append(curr.val)
            curr = curr.next
        return arr
    
    def reverse(self):
        headset, curr, ahead, temp = False, self.head, self.head.next, self.head
        while ahead:
            if not headset:
                temp = self.head
                self.head = self.head.next
                temp.next = None
                ahead = ahead.next
                self.head.next = temp
                headset = True
            else:
                print("hi")
        
        raise ValueError("Unimplemented!")