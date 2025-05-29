'''
Modeled after Justin's CMSC420 coding project testing scripts 
'''


import argparse
import csv
import sll

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('-tf', '--tracefile')
    args = parser.parse_args()
    tracefile = args.tracefile

    lst: sll.SinglyLinkedList = None
    with open(tracefile, 'r') as file:
        reader = csv.reader(file, delimiter=",")
        lines = [line for line in reader]
        for line in lines:
            if line[0] == 'initialize':
                lst = sll.SinglyLinkedList()
            elif line[0] == 'clear':
                lst.clear()
            elif line[0] == 'isEmpty':
                print("List is empty") if lst.is_empty() else print("List is not empty")
            elif line[0] == 'add':
                if line[1] == 'head':
                    lst.add_to_head(int(line[2]))
                elif line[1] == 'tail':
                    lst.add_to_tail(int(line[2]))
            elif line[0] == 'insert':
                lst.insert_at_index(int(line[1]), int(line[2]))
            elif line[0] == 'search':
                print(f"List contains {int(line[1])}.") if lst.contains(int(line[1])) else print(f"List does not contain {int(line[1])}")
            elif line[0] == 'remove':
                if line[1] == 'first':
                    lst.remove_first()
                elif line[1] == 'last':
                    lst.remove_last()
                elif line[1] == 'all':
                    lst.remove_all(int(lst[2]))
                else:
                    lst.remove(int(lst[2]))
            elif line[0] == 'get':
                print(f"list[{int(line[1])}] = {lst.get(int(lst[1]))}")
            elif line[0] == 'replace':
                lst.replace(int(line[1]), int(line[2]))
            elif line[0] == 'set':
                print(lst.set())
            elif line[0] == 'array':
                print(lst.to_array())
            elif line[0] == 'dump':
                print(lst)









# import argparse
# import csv
# import sll

# if __name__ == "__main__":

#     parser = argparse.ArgumentParser()
#     parser.add_argument('-tf', '--tracefile')
#     args = parser.parse_args()
#     tracefile = args.tracefile

#     lst: sll.SinglyLinkedList = None
#     with open(tracefile, "r") as file:
#         reader = csv.reader(file, delimiter=' ')
#         lines = [l for l in reader]
        
#         for line in lines:
#             if line[0] == 'UnionFind':
#                 lst = sll.SinglyLinkedList()
                
#             elif line[0] == 'add':
#                 lst.add(int(line[1]))
                
#             elif line[0] == 'union':
#                 # line[1] is new root, line[2] is element
#                 lst.union(int(line[1]), int(line[2]))
                
#             elif line[0] == 'find':
#                 lst.find(int(line[1]))
                
#             elif line[0] == 'dump':
#                 print(lst.dump())