import sys
from collections import deque
input = sys.stdin.readline

def dfs(start,cnt):
    global end,ans

    if start==end:
        ans = cnt
        return

    for n,d in graph[start]:
        if visited[n]: continue
        visited[n]=True
        dfs(n,cnt+d)


N,M = map(int,input().split())
graph = [[] for _ in range(N+1)]
start=end=0
ans=0

for _ in range(N-1):
    a,b,c = map(int,input().split())
    graph[a].append([b,c])
    graph[b].append([a,c])

for _ in range(M):
    visited = [False] * (N + 1)
    start,end = map(int,input().split())
    visited[start]=True
    dfs(start,0)
    print(ans)
