def solution(storey):
    answer = 0
    plus = False
    arr = list(str(storey))[::-1]  # 1의 자리를 맨 앞으로 reverse
    for i in range(0, len(arr)-1):
        num = int(arr[i])
        if plus:
            num += 1
            if num == 10:
                continue
            plus = False

        if num == 5:
            answer += 5
            next_num = int(arr[i+1])
            if next_num >= 5:  # next_num >= 6에서 TC 1, 3오답 -> 5로 수정
                plus = True
        elif 0 <= num <= 4:
            answer += num
        elif 6 <= num <= 9:
            answer += (10-num)
            plus = True

    last = int(arr[-1])  # 마지막 하나 따로 검사
    if plus:
        last += 1

    if last == 1 or last == 10:
        return answer + 1
    elif 2 <= last <= 5:
        return answer + last
    elif 6 <= last <= 9:
        return answer + (10-last) + 1