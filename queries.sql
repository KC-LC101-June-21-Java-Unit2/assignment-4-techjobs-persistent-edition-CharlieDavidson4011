-- Part 1: Test it with SQL
 SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'techjobs' AND TABLE_NAME='job';

-- Part 2: Test it with SQL

SELECT NAME FROM EMPLOYER WHERE LOCATION = "Kansas";
-- Part 3: Test it with SQL

DROP TABLE JOB;

-- Part 4: Test it with SQL

SELECT * FROM skill
 LEFT JOIN job_skills ON skill.id = job_skills.skills_id
 WHERE job_skills.jobs_id IS NOT NULL
 ORDER BY name ASC;Part 4: Test it with SQL