-- 코드를 입력하세요
SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_OUTS O
INNER JOIN ANIMAL_INS I ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE TIMEDIFF(O.DATETIME, I.DATETIME) < 0
ORDER BY I.DATETIME;