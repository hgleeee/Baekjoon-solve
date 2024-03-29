-- 코드를 입력하세요
SELECT C.CAR_ID, C.CAR_TYPE, C.DAILY_FEE * (100 - P.DISCOUNT_RATE) DIV 100 * 30 AS FEE
FROM CAR_RENTAL_COMPANY_CAR C
INNER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P ON P.CAR_TYPE = C.CAR_TYPE AND DURATION_TYPE LIKE '30%'
WHERE CAR_ID NOT IN (
   SELECT CAR_ID
   FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
   WHERE DATEDIFF(START_DATE, '2022-11-30') <= 0 AND DATEDIFF(END_DATE, '2022-11-01') >= 0
) 
AND C.DAILY_FEE * (100 - P.DISCOUNT_RATE) DIV 100 * 30 >= 500000
AND C.DAILY_FEE * (100 - P.DISCOUNT_RATE) DIV 100 * 30 < 2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC;