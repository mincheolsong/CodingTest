import sys
from collections import deque
input = sys.stdin.readline

# bfs
dr = [0,1,0,-1,0,0]
dc = [1,0,-1,0,0,0]
dh = [0,0,0,0,1,-1]

def bfs(height,row,col):
    global zero_cnt

    q = deque()

    q.append([height,row,col])

    while q:
        h,r,c = q.popleft()

        for d in range(6):
            nh = h + dh[d]
            nr = r + dr[d]
            nc = c + dc[d]
            if nh < 0 or nh>=H: continue
            if nr < 0 or nr>=N: continue
            if nc < 0 or nc>=M: continue

            if box[nh][nr][nc]==0 or box[nh][nr][nc] > box[h][r][c]+1:
                if box[nh][nr][nc]==0:
                    zero_cnt-=1
                box[nh][nr][nc]=box[h][r][c]+1
                q.append([nh,nr,nc])


M,N,H = map(int,input().split())

box = [] # [0][1][2] : 0층의 1행 2열, [1][1][2] : 1층의 1행 2열
zero_cnt = 0 # 0 즉 익지않은 토마토 갯수
ans = -1

for _ in range(H):
    layer = []
    for _ in range(N):
        tmp = list(map(int,input().split()))
        zero_cnt += tmp.count(0)
        layer.append(tmp)
    box.append(layer)

if zero_cnt == 0:
    ans = 0
else:
    for k in range(H):
        for r in range(N):
            for c in range(M):
                if box[k][r][c]==1: # 처음 주어진 익은 토마토 이면 bfs
                    bfs(k,r,c)

    if zero_cnt == 0:
        for k in range(H):
            for r in range(N):
                tmp = max(box[k][r])-1
                ans = tmp if tmp > ans else ans

print(ans)
