import sys
from collections import deque
input = sys.stdin.readline

def solve():

    stack = deque() # deque를 stack으로 사용
    tmp = 1

    for i in range(len(l)):
        if l[i] in ['(','[']:
            stack.append(l[i])
            tmp = 1
            continue

        if len(stack)==0:
            return 0
        top = stack.pop()

        if top not in ['(','[']:
            tmp = top
            if len(stack) == 0:
                return 0
            top = stack.pop()

        if l[i]==')' and top=='(':
            if len(stack)>0 and stack[-1] not in ['(','[']:
                a = stack.pop()
                stack.append(a + tmp*2)
            else:
                stack.append(tmp*2)
        elif l[i]==']' and top=='[':
            if len(stack)>0 and stack[-1] not in ['(','[']:
                a = stack.pop()
                stack.append(a + tmp*3)
            else:
                stack.append(tmp*3)
        else:
            return 0

    if len(stack) > 1 or stack[-1] in ['(','[',')',']']:
        return 0
    return stack[-1]


l = list(str(input().strip()))

ans = solve()

print(ans)