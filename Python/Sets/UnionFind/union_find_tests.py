'''
Modeled after Justin's CMSC420 coding project testing scripts 
'''

import argparse
import csv
import union_find

if __name__ == "__main__":

    parser = argparse.ArgumentParser()
    parser.add_argument('-tf', '--tracefile')
    args = parser.parse_args()
    tracefile = args.tracefile

    uf: union_find.UnionFind = None
    with open(tracefile, "r") as file:
        reader = csv.reader(file, delimiter=' ')
        lines = [l for l in reader]
        
        for line in lines:
            if line[0] == 'UnionFind':
                uf = union_find.UnionFind(int(line[1]))
                
            elif line[0] == 'add':
                uf.add(int(line[1]))
                
            elif line[0] == 'union1':
                # line[1] is new root, line[2] is element
                uf.union1(int(line[2]), int(line[1]))
                
            elif line[0] == 'union2':
                # line[1] is new root, line[2] is element
                uf.union2(int(line[2]), int(line[1]))
                
            elif line[0] == 'find':
                uf.find_rep2(int(line[1]))
                
            elif line[0] == 'dump':
                print(uf.dump())