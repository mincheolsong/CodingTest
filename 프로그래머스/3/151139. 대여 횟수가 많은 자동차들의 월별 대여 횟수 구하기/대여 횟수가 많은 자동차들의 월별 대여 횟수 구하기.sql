-- 코드를 입력하세요
# SELECT CAST(DATE_FORMAT(START_DATE,'%c') AS unsigned) AS MONTH, CAR_ID, COUNT(*) RECORDS FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# WHERE YEAR(START_DATE)=2022 AND MONTH(START_DATE) IN ('08','09','10')
# GROUP BY MONTH, CAR_ID
# ORDER BY MONTH ASC, CAR_ID DESC;
SELECT CAST(DATE_FORMAT(X.START_DATE,'%c') AS unsigned) AS MONTH, X.CAR_ID, COUNT(*) RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS X
JOIN (SELECT CAR_ID, COUNT(*) RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE YEAR(START_DATE)=2022 AND MONTH(START_DATE) IN ('08','09','10')
GROUP BY CAR_ID
HAVING RECORDS >= 5) AS Y
ON X.CAR_ID = Y.CAR_ID
WHERE YEAR(X.START_DATE)=2022 AND MONTH(X.START_DATE) IN ('08','09','10')
GROUP BY MONTH, X.CAR_ID
ORDER BY MONTH ASC, X.CAR_ID DESC;

