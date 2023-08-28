import sys
input = sys.stdin.readline



def comb(n,r,start):
    global ans

    if n==M:
        result = 0
        for l in list(zip(*dist)):
            result += min(l)
        ans = result
        return


    if r==0:
        result = 0
        for l in list(zip(*select)):
            result += min(l)
        ans = result if ans > result else ans
        return

    for i in range(start,n):
        select.append(dist[i])
        comb(n,r-1,i+1)
        select.pop()



def solve():

    for i in range(len(chicken)):
        cr,cc = chicken[i]
        for j in range(len(house)):
            hr,hc = house[j]
            dist[i][j] = abs(cr-hr) + abs(cc-hc)


N,M = map(int,input().split())

city = []
house=[]
chicken=[]
ans = 1e9
for _ in range(N):
    city.append(list(map(int,input().split())))

for i in range(N):
    for j in range(N):
        if city[i][j]==1: house.append([i,j])
        elif city[i][j]==2: chicken.append([i,j])

dist=[[0]*len(house) for _ in range(len(chicken))]

solve()

select=[]
comb(len(chicken),M,0)

print(ans)
