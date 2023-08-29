import sys
input = sys.stdin.readline

def solve():
    global ans

    pizza_idx=0

    for i in range(D-1,-1,-1): # 오븐의 밑에서 부터 피자를 차곡차곡 쌓기

        if pizza[pizza_idx] > oven[i]: # 다음 위치의 오븐 탐색
            continue

        pizza_idx+=1

        if pizza_idx==N: # 모든 피자가 다 배치됨
            ans = i+1
            return

    # 모든 피자가 배치될 수 없는 경우 ans는 0
    return

D,N = map(int,input().split())

oven = list(map(int,input().split())) # D 개
pizza = list(map(int,input().split())) # N 개
ans=0

# oven 모양 재정의
for i in range(D-1):
    if oven[i] < oven[i+1]:
        oven[i+1] = oven[i]


solve()

print(ans)

