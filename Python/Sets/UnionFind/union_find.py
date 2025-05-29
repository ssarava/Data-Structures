class UnionFind:
    '''
    Also called the "Disjoint-Set Data Structure". Used to manage disjoint sets.
    Good for quickly determining if two elements belong to the same set.
    Good for quickly merging two sets

    Use cases:
        - detecting cycles in graphs
        - checking network connectivityy
        - MST (Kruskal's Algorithm)


    To handle non-numerical elements, map them to integers
    
    To test this script from the command line, enter the following into the terminal:
    
        python3 union_find_tests.py -tf tests.txt
        
    where "tests.txt" consists of commands to create/manipulate UnionFind instances
    '''
    
    def __init__(self,
                 n: int):
        self.parents = [index for index in range(n)]
        self.sizes = [1] * n
    
    def dump(self) -> str:
        return f"Sizes:\t\t{self.sizes}\nParents:\t{self.parents}"
        
    def union1(self, x, y):
        """
        Makes 'y' the new root of 'x'. Order doesn't matter.
        """
        root_x = self.find_rep1(x)
        root_y = self.find_rep1(y)
        self.parents[root_y] = root_x   # make 'x' the new root of 'y'
        
    def union2(self, x, y):
        """
        Makes 'y' the new root of 'x'. Order doesn't matter.
        Uses 'find_rep2' which uses path compression.
        """
        
        root_x = self.find_rep2(x)
        root_y = self.find_rep2(y)
        # print(f"{x}'s parent is {root_x}\t\t{y}'s parent is {root_y}")
        print(f"size of {root_x} = {self.sizes[root_x]}\t\tsize of {root_y} = {self.sizes[root_y]}")
        
        new_root = root_x if self.sizes[root_x] > self.sizes[root_y] else root_y
        child = root_y if self.sizes[root_x] > self.sizes[root_y] else root_x
        self.parents[child] = new_root   # make 'x' the new root of 'y'
        self.sizes[new_root] += 1
    
    def find_rep1(self, x):
        """
        Returns the highest root of this element  
        """
        if self.parents[x] == x:
            return x
        else:
            temp = self.find_rep1(self.parents[x])
            self.sizes[temp] += 1
            return temp
        
    def find_rep2(self, x):
        """
        Uses path compression; every node on the path to the root has its parent set to the root.
        Returns the highest root of this element.
        """
        if self.parents[x] == x:
            return x
        else:
            self.parents[x] = self.find_rep2(self.parents[x])
            return self.parents[x]
    
    def add(self):
        """
        Add a new element
        """
        self.parents.append(len(self.parents))
        self.sizes.append(1)
