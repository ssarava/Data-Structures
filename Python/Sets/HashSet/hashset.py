import numpy as np

class Node:
    
    def __init__(self,
                 valIn: int = None):
        self.val = valIn

class Hashset:
    
    def __init__(self):
        self.table = np.zeros(1)