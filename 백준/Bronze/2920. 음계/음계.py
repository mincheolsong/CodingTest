import sys


input = sys.stdin.readline

def solve():
    flag = 0
    
    for i in range(0,8):
        if l[i]!=a[i]:
            flag = 1
            break
            
    if flag==0:
        print("ascending")
        return
    
    for i in range(0,8):
        if l[i]!=d[i]:
            flag = 2
            break
    
    if flag==1:
        print("descending")
        return
    
    print("mixed")

l = list(map(int,input().split()))

a = [1,2,3,4,5,6,7,8]
d = [8,7,6,5,4,3,2,1]

solve()





