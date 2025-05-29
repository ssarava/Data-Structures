

class MyStack[T]:
    
    default_length = 10
    
    def __init__(self) -> None:
        # Create an empty list with items of type T
        self.items: list[T] = []
    
    def is_empty(self) -> bool:
        return len(self.items) == 0
    
    def peek(self) -> T:
        return self.items[-1] if self.items else None
    
    def pop(self) -> T:
        return self.items.pop() if self.items else None
    
    def push(self, element: T) -> T:
        self.items.append(element)
        return element
    
    # returns the 1-indexed location of the element; returns -1 if it doesn't exist
    def search(self, element: T) -> int:
        index = 1
        while index >= len(self.items):
            if self.items[index] is element:
                return index
            index += 1
        return -1
    
    # equivalent to Java's "toString()" method
    def __repr__(self):
        return f"{self.items}"
    
    
def main():
    stack1 = MyStack[int]()
    print(stack1)
    
    stack1.push(3)
    print(stack1)
    
    stack1.push("i")
    print(stack1)
    

main()