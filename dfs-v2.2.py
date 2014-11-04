#Author: Nicholas Kuan, nkua002, 1201891
import sys
import time
keepSearching = True

#Read input string, and convert the adj matrix to a list of lists for each node to be returned
def graph(f):
    numberOfNodes = int(f.readline())
    #numberOfNodes = int(sys.stdin.readline())
    adjList = []
    for i in range(numberOfNodes):
        adjList += [f.readline().split(" ")]
        #adjList += [sys.stdin.readline().split(" ")]
    return adjList

#DFS to appended to d, each node that is reachable from starting point, v
def dfs(G, v, d, e):
    #Only search if destination not found yet
    global keepSearching
    d.append(int(v))
    if int(v) == e:
        keepSearching = False
    if keepSearching == True:
        for w in G[int(v)]:
            try:
                if int(w) not in d:
                    #Recursive call to dfs to find all w reachable from v 
                    dfs(G, w, d, e)
            #If w is an empty line, return
            except ValueError:
                return
    else:
        return
    
def runAssignment4(filename):
    startTime = time.time()
    sys.setrecursionlimit(100000)
    global keepSearching
    f = open(filename)
    discovered = []
    myGraph = graph(f)

    destination = f.readline().split(" ")
    #destination = sys.stdin.readline().split(" ")
    startPoint = int(destination[0])
    endPoint = int(destination[1])

    if startPoint == endPoint:
        path = "yes"
    else:
        #Complete a dfs starting from startPoint, stops after destination reached
        keepSearching = True
        dfs(myGraph, startPoint, discovered, endPoint)  
        if keepSearching == True:
            path = "no"
        else:
            path = "yes"

    #Print output
    print("s = " + str(startPoint) + "; t = " + str(endPoint) + "; " + str(path))
    endTime = time.time()
    print("Total time: " + str(endTime-startTime))
    f.close()
runAssignment4("input.txt")
