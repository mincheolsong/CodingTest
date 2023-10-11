import sys
from collections import deque
input = sys.stdin.readline
INF = float('inf')

def f(cnt):

    if cnt==len(bracket):
        tmp = []
        for i in range(len(isSelected)):
            if isSelected[i]:
                start,end = bracket[i]
                tmp.append(start)
                tmp.append(end)

        if len(tmp)==0:
            return

        tmp_ans = []

        for i in range(len(l)):
            if i in tmp:
                continue
            tmp_ans.append(l[i])
        ans.append(''.join(tmp_ans))
        return

    isSelected[cnt]=True
    f(cnt+1)
    isSelected[cnt]=False
    f(cnt+1)

st = input().strip()
l = list(st)

stack = [] # 괄호 위치를 찾기위한 스택
bracket = [] # 괄호들의 인덱스를 저장하고 있는 리스트
ans = []

for i in range(len(l)):
    if l[i]=='(':
        stack.append(i)
    elif l[i]==')':
        tmp = stack.pop()
        bracket.append([tmp,i])

isSelected = [False]*len(bracket)
f(0)
ans.sort()
prev_a = ''
for a in ans:
    if(prev_a==a):
        continue
    print(a)
    prev_a=a
