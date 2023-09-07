import sys
from collections import deque
input = sys.stdin.readline
INF = float('inf')

# 1 -> v1 -> v2 -> N
# 1 -> v2 -> v1 -> N

def get_smallest_node():
    min_val = INF
    idx = 0

    for i in range(1,N+1):
        if dist[i] < min_val and not visited[i]:
            min_val=dist[i]
            idx = i

    return idx


def dijkstra(i,j):

    dist[i]=0
    visited[i]=True
    for g in graph[i]:
        dist[g[0]]=g[1]

    # 방문 하지 않은 노드 중 최솟값 dist의 노드 찾기
    # 해당 노드 방문처리
    # dist 갱신
    for _ in range(N-1):
        now = get_smallest_node()
        visited[now]=True
        if now==j:
            break
        for g in graph[now]:
            cost = dist[now] + g[1]
            if cost < dist[g[0]]:
                dist[g[0]]=cost

    return dist


N,E = map(int,input().split())
graph = [[] for _ in range(N+1)]

for _ in range(E):
    a,b,c = map(int,input().split())
    graph[a].append((b,c))
    graph[b].append((a,c))

v1, v2 = map(int,input().split())

dist = [INF] * (N + 1)
visited = [False] * (N + 1)
one_to_v1 = dijkstra(1,v1)

dist = [INF] * (N + 1)
visited = [False] * (N + 1)
one_to_v2 = dijkstra(1,v2)

dist = [INF] * (N + 1)
visited = [False] * (N + 1)
v1_to_v2 = dijkstra(v1,v2)

dist = [INF] * (N + 1)
visited = [False] * (N + 1)
v1_to_N = dijkstra(v1,N)

dist = [INF] * (N + 1)
visited = [False] * (N + 1)
v2_to_N = dijkstra(v2,N)

result1 = one_to_v1[v1] + v1_to_v2[v2] + v2_to_N[N]
result2 = one_to_v2[v2] + v1_to_v2[v2] + v1_to_N[N]

ans = min(result1,result2)

print(-1 if ans==INF else ans)
