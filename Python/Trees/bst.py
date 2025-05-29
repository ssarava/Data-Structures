from typing import List, Optional

class Node():
    
    def __init__(self,
                 data: int = None):
        self.data = data
        self.left: Node = None
        self.right: Node = None
        
    def __repr__(self):
        return f"{self.data}"

class BinarySearchTree:
    
    def __init__(self):
        self.root: Node = None
        self.size = 0
        
    def contains(self, value: int) -> bool:
        if self.root is None:
            return False
        
        curr = self.root
        while curr is not None:
            if value < curr.data:
                curr = curr.left
            elif value > curr.data:
                curr = curr.right
            else:
                return True
        return False
    
    def insert(self, value: int):
        self.root = self.insert_helper(value, self.root)
        self.size += 1
        
    def insert_helper(self, value: int, root: Node):
        if root is None:
           return Node(value)

        if root.data == value:
            return root

        if value < root.data:
            root.left = self.insert_helper(value, root.left)
        else:
            root.right = self.insert_helper(value, root.right)
            
        return root
        
    def preorder(self):
        lst = []
        self.preorder_helper(self.root, lst)
        print(lst)
            
    def preorder_helper(self, root: Node, acc: list):
        if root is not None:
            self.preorder_helper(root.left, acc)
            acc.append(root.data)
            self.preorder_helper(root.right, acc)
            
    def isEmpty(self):
        return self.size == 0
            
def main():
    tree1 = BinarySearchTree()
    tree1.insert(2)
    tree1.insert(1)
    tree1.insert(3)
    
main()