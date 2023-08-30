import sys
from collections import deque
input = sys.stdin.readline

def solve():
    # stack에 현재 계산값을 누적하고 닫힌 괄호가 나오면 pop하면서 갱신

    stack = deque() # deque를 stack으로 사용
    tmp = 1

    for i in range(len(l)):
        if l[i] in ['(','[']:
            stack.append(l[i])
            tmp = 1
            continue

        if len(stack)==0: # )() 와 같은 경우 예외처리
            return 0

        top = stack.pop() # 현재 스택의 최상위 값

        if top not in ['(','[']: # 현재까지 계산된 값 있으면
            tmp = top # 계산값을 tmp에 저장
            if len(stack) == 0: # ()) 와 같은 경우 예외처리
                return 0
            top = stack.pop() # 스택 최상위 값 ( 혹은 [

        if l[i]==')' and top=='(':
            if len(stack)>0 and stack[-1] not in ['(','[']: # 스택에 계산된 결과값(숫자)가 있으면 + 해줘야 함, ()[] 와 같은 경우
                a = stack.pop()
                stack.append(a + tmp*2)
            else:
                stack.append(tmp*2)
        elif l[i]==']' and top=='[':
            if len(stack)>0 and stack[-1] not in ['(','[']: # 스택에 계산된 결과값(숫자)가 있으면 + 해줘야 함, ()[] 와 같은 경우
                a = stack.pop()
                stack.append(a + tmp*3)
            else:
                stack.append(tmp*3)
        else: # 잘못된 경우
            return 0


    if len(stack) > 1 or stack[-1] in ['(','[',')',']']: # ( 와 같은 경우
        return 0
    
    return stack[-1]


l = list(str(input().strip()))

ans = solve()

print(ans)