import sys
from collections import deque
input = sys.stdin.readline

def delete(n):

    q = deque()
    q.append(n)

    while q:
        tmp = q.popleft()
        graph[tmp]=tmp # 제거처리 (자기 자신을 부모로 가지는걸 제거로 판단)

        for i in range(N):
            if i != tmp and graph[i]==tmp:
                q.append(i)



N = int(input())

graph = list(map(int,input().split()))

node = int(input())

delete(node)
ans = 0
for i in range(N):
    if i not in graph:
        ans+=1

print(ans)

