-- SELECT YEAR(JOINED) AS YEAR, MONTH(JOINED) AS MONTH, COUNT(*) AS TOTAL
-- FROM USER_INFO
-- GROUP BY YEAR, MONTH


SELECT YEAR(SALES_DATE) AS YEAR, MONTH(SALES_DATE) AS MONTH, 
    COUNT(DISTINCT S.USER_ID) AS PURCHASED_USERS, ROUND(COUNT(DISTINCT S.USER_ID) / (
        SELECT COUNT(*)
        FROM USER_INFO
        WHERE YEAR(JOINED) = '2021'
    ), 1) AS PURCHASED_RATIO
FROM ONLINE_SALE S
    INNER JOIN USER_INFO I ON I.USER_ID = S.USER_ID
WHERE YEAR(I.JOINED) = '2021'
GROUP BY YEAR, MONTH
ORDER BY YEAR ASC, MONTH ASC;