WITH NH AS (
    SELECT CAR_ID, (
        CASE
            WHEN DATEDIFF(END_DATE, '2022-10-16') >= 0 AND DATEDIFF('2022-10-16', START_DATE) >= 0
            THEN '대여중'
            ELSE '대여 가능'
        END
    ) AS AVAILABILITY
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
)

SELECT CAR_ID, (
    CASE
        WHEN (SELECT COUNT(*) FROM NH 
              WHERE AVAILABILITY = '대여중' 
              GROUP BY CAR_ID 
              HAVING CAR_ID = H.CAR_ID) > 0
        THEN '대여중'
        ELSE '대여 가능'
    END
) AS AVAILABILITY
FROM NH H
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;