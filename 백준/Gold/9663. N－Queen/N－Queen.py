import sys
input = sys.stdin.readline

def check(r,c):
    for i in range(0,r):
        if chk[i]==c: return False
        if (r-i)==abs(c-chk[i]): return False 
    return True


def backTracking(i):
    global ans

    if i==N:
        ans+=1
        return

    for c in range(N):
        if not check(i,c): continue
        chk[i]=c
        backTracking(i+1)


N = int(input())
chk = [0]*N
ans = 0

backTracking(0)

print(ans)