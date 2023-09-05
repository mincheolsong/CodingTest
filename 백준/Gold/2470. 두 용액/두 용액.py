import sys
input = sys.stdin.readline

N = int(input())

l = list(map(int,input().split()))
l.sort()
start=0
end=N-1
ans=float('inf')
ans_list=[0,0]

while start < end:
    tmp = l[start]+l[end]
    tmp_abs = abs(tmp)
    if tmp_abs < ans:
        ans = tmp_abs
        ans_list[0] = l[start]
        ans_list[1] = l[end]

    if tmp_abs == 0:
        break

    if tmp < 0:
        start+=1
    else:
        end-=1

print(ans_list[0],ans_list[1])