# input = sys.stdin.readline

n = int(input())

for _ in range(n):
	l = list(input().strip())
	print(l[0],end="")
	print(l[-1])
