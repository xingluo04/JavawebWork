## 员工信息练习题

1.查询所有员工的姓名、邮箱和工作岗位。

```
SELECT first_name, last_name, email, job_title 
FROM employees;
```


2.查询所有部门的名称和位置。

```
SELECT dept_name, location 
FROM departments;
```


3.查询工资超过70000的员工姓名和工资。

```
SELECT first_name, last_name, salary 
FROM employees 
WHERE salary > 70000;
```


4.查询IT部门的所有员工。

```
SELECT * 
FROM employees 
WHERE dept_id = (SELECT dept_id FROM departments WHERE dept_name = 'IT');
```


5.查询入职日期在2020年之后的员工信息。

```
SELECT * 
FROM employees 
WHERE hire_date > '2020-01-01';
```


6.计算每个部门的平均工资。

```
SELECT dept_id, AVG(salary) AS average_salary 
FROM employees 
GROUP BY dept_id;
```


7.查询工资最高的前3名员工信息。

```
SELECT * 
FROM employees 
ORDER BY salary DESC LIMIT 3;
```


8.查询每个部门员工数量。

```
SELECT dept_id, COUNT(*) AS employee_count 
FROM employees 
GROUP BY dept_id;
```


9.查询没有分配部门的员工。

```
SELECT * 
FROM employees 
WHERE dept_id IS NULL;
```


10.查询参与项目数量最多的员工。

```
SELECT emp_id, COUNT(project_id) AS project_count 
FROM employee_projects 
GROUP BY emp_id 
ORDER BY project_count DESC LIMIT 1;
```


11. 计算所有员工的工资总和。

```
SELECT SUM(salary) AS total_salary 
FROM employees;
```


12. 查询姓"Smith"的员工信息。

```
SELECT * 
FROM employees 
WHERE last_name = 'Smith';
```


13. 查询即将在半年内到期的项目。

```
SELECT * 
FROM projects 
WHERE end_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 6 MONTH);
```


14. 查询至少参与了两个项目的员工。

```
SELECT emp_id 
FROM employee_projects 
GROUP BY emp_id HAVING COUNT(project_id) >= 2;
```


15. 查询没有参与任何项目的员工。

```
SELECT * 
FROM employees 
WHERE emp_id NOT IN (SELECT DISTINCT emp_id FROM employee_projects);
```


16. 计算每个项目参与的员工数量。

```
SELECT project_id, COUNT(emp_id) AS employee_count 
FROM employee_projects 
GROUP BY project_id;
```


17. 查询工资第二高的员工信息。

```
SELECT * 
FROM employees 
ORDER BY salary DESC LIMIT 1, 1;
```


18. 查询每个部门工资最高的员工。

```
SELECT * 
FROM employees e1 
WHERE salary = (SELECT MAX(salary) FROM employees e2 WHERE e1.dept_id = e2.dept_id);
```


19. 计算每个部门的工资总和,并按照工资总和降序排列。

```
SELECT dept_id, SUM(salary) AS total_salary 
FROM employees 
GROUP BY dept_id 
ORDER BY total_salary DESC;
```


20. 查询员工姓名、部门名称和工资。

```
SELECT CONCAT(e.first_name, ' ', e.last_name) AS full_name, d.dept_name, e.salary 
FROM employees e 
JOIN departments d ON e.dept_id = d.dept_id;
```


21. 查询每个员工的上级主管(假设emp_id小的是上级)。

```
SELECT e1.emp_id, CONCAT(e2.first_name, ' ', e2.last_name) AS manager_name 
FROM employees e1 
LEFT JOIN employees e2 ON e1.emp_id > e2.emp_id 
ORDER BY e1.emp_id;
```


22. 查询所有员工的工作岗位,不要重复。

```
SELECT DISTINCT job_title 
FROM employees;
```


23. 查询平均工资最高的部门。

```
SELECT dept_id, AVG(salary) AS average_salary 
FROM employees 
GROUP BY dept_id 
ORDER BY average_salary DESC LIMIT 1;
```


24. 查询工资高于其所在部门平均工资的员工。

```
SELECT e1.* 
FROM employees e1 
INNER JOIN (SELECT dept_id, AVG(salary) AS avg_salary 
			FROM employees 
            GROUP BY dept_id) e2 
ON e1.dept_id = e2.dept_id 
WHERE e1.salary > e2.avg_salary;
```


25. 查询每个部门工资前两名的员工。

```
SELECT * FROM employees e1 
WHERE (SELECT COUNT(*) 
		FROM employees e2 
		WHERE e2.dept_id = e1.dept_id AND e2.salary > e1.salary) < 2 
ORDER BY e1.dept_id, e1.salary DESC;
```

## 学生选课题

1. 查询所有学生的信息。

```
SELECT * FROM student;
```

2. 查询所有课程的信息。

```
SELECT * FROM course;
```


3. 查询所有学生的姓名、学号和班级。

```
SELECT name, student_id, my_class FROM student;
```


4. 查询所有教师的姓名和职称。

```
SELECT name, title FROM teacher;
```


5. 查询不同课程的平均分数。

```
SELECT course_id, AVG(score) AS average_score FROM score GROUP BY course_id;
```


6. 查询每个学生的平均分数。

```
SELECT student_id, AVG(score) AS average_score FROM score GROUP BY student_id;
```


7. 查询分数大于85分的学生学号和课程号。

```
SELECT student_id, course_id FROM score WHERE score > 85;
```


8. 查询每门课程的选课人数。

```
SELECT course_id, COUNT(student_id) AS student_count FROM score GROUP BY course_id;
```


9. 查询选修了"高等数学"课程的学生姓名和分数。

```
SELECT s.name, sc.score FROM student s
JOIN score sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id
WHERE c.course_name = '高等数学';
```


10. 查询没有选修"大学物理"课程的学生姓名。

```
SELECT name FROM student
WHERE student_id NOT IN (
    SELECT DISTINCT student_id FROM score
    JOIN course ON score.course_id = course.course_id
    WHERE course.course_name = '大学物理'
);
```


11. 查询C001比C002课程成绩高的学生信息及课程分数。

```
SELECT a.student_id, a.score AS C001_score, b.score AS C002_score
FROM score a, score b
WHERE a.student_id = b.student_id
AND a.course_id = 'C001'
AND b.course_id = 'C002'
AND a.score > b.score;
```


12. 统计各科成绩各分数段人数：课程编号，课程名称，[100-85]，[85-70]，[70-60]，[60-0] 及所占百分比

```
SELECT 
    c.course_id, 
    c.course_name, 
    SUM(CASE WHEN sc.score BETWEEN 85 AND 100 THEN 1 ELSE 0 END) AS '100-85', 
    SUM(CASE WHEN sc.score BETWEEN 70 AND 85 THEN 1 ELSE 0 END) AS '85-70', 
    SUM(CASE WHEN sc.score BETWEEN 60 AND 70 THEN 1 ELSE 0 END) AS '70-60', 
    SUM(CASE WHEN sc.score BETWEEN 0 AND 60 THEN 1 ELSE 0 END) AS '60-0',
    ROUND((SUM(CASE WHEN sc.score BETWEEN 85 AND 100 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS '100-85_percentage',
    ROUND((SUM(CASE WHEN sc.score BETWEEN 70 AND 85 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS '85-70_percentage',
    ROUND((SUM(CASE WHEN sc.score BETWEEN 60 AND 70 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS '70-60_percentage',
    ROUND((SUM(CASE WHEN sc.score BETWEEN 0 AND 60 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS '60-0_percentage'
FROM course c
JOIN score sc ON c.course_id = sc.course_id
GROUP BY c.course_id, c.course_name;
```


13. 查询选择C002课程但没选择C004课程的成绩情况(不存在时显示为 null )。

```
SELECT sc1.student_id, sc1.score
FROM score sc1
LEFT JOIN score sc2 ON sc1.student_id = sc2.student_id AND sc2.course_id = 'C004'
WHERE sc1.course_id = 'C002' AND sc2.student_id IS NULL;
```


14. 查询平均分数最高的学生姓名和平均分数。

```
SELECT s.name, AVG(sc.score) AS average_score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.student_id
ORDER BY average_score DESC
LIMIT 1;
```


15. 查询总分最高的前三名学生的姓名和总分。

```
SELECT s.name, SUM(sc.score) AS total_score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.student_id
ORDER BY total_score DESC
LIMIT 3;
```


16. 查询各科成绩最高分、最低分和平均分。要求如下：
以如下形式显示：课程 ID，课程 name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率
及格为>=60，中等为：70-80，优良为：80-90，优秀为：>=90
要求输出课程号和选修人数，查询结果按人数降序排列，若人数相同，按课程号升序排列

```
SELECT 
    c.course_id, 
    c.course_name, 
    MAX(sc.score) AS highest_score, 
    MIN(sc.score) AS lowest_score, 
    AVG(sc.score) AS average_score,
    ROUND((SUM(CASE WHEN sc.score >= 60 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS pass_rate,
    ROUND((SUM(CASE WHEN sc.score BETWEEN 70 AND 80 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS average_rate,
    ROUND((SUM(CASE WHEN sc.score BETWEEN 80 AND 90 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS good_rate,
    ROUND((SUM(CASE WHEN sc.score >= 90 THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS excellent_rate,
    COUNT(sc.student_id) AS student_count
FROM course c
JOIN score sc ON c.course_id = sc.course_id
GROUP BY c.course_id, c.course_name
ORDER BY student_count DESC, c.course_id ASC;
```


17. 查询男生和女生的人数。

```
SELECT 
    gender, 
    COUNT(student_id) AS student_count
FROM student
GROUP BY gender;
```


18. 查询年龄最大的学生姓名。

```
SELECT name, birth_date
FROM student
ORDER BY birth_date DESC
LIMIT 1;
```


19. 查询年龄最小的教师姓名。

```
SELECT name, birth_date
FROM teacher
ORDER BY birth_date ASC
LIMIT 1;
```


20. 查询学过「张教授」授课的同学的信息。

```
SELECT s.name, sc.score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id
JOIN teacher t ON c.teacher_id = t.teacher_id
WHERE t.name = '张教授';
```


21. 查询查询至少有一门课与学号为"2021001"的同学所学相同的同学的信息 。

```
SELECT DISTINCT s2.student_id, s2.name
FROM student s1
JOIN score sc1 ON s1.student_id = sc1.student_id
JOIN course c ON sc1.course_id = c.course_id
JOIN score sc2 ON c.course_id = sc2.course_id
JOIN student s2 ON sc2.student_id = s2.student_id
WHERE s1.student_id '2021001'
AND sc1.student_id != sc2.student_id;
```


22. 查询每门课程的平均分数，并按平均分数降序排列。

```
SELECT c.course_id, c.course_name, AVG(sc.score) AS average_score
FROM course c
JOIN score sc ON c.course_id = sc.course_id
GROUP BY c.course_id, c.course_name
ORDER BY average_score DESC;
```


23. 查询学号为"2021001"的学生所有课程的分数。

```
SELECT c.course_name, sc.score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id
WHERE s.student_id '2021001';
```


24. 查询所有学生的姓名、选修的课程名称和分数。

```
SELECT s.name, c.course_name, sc.score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id;
```


25. 查询每个教师所教授课程的平均分数。

```
SELECT t.name, AVG(sc.score) AS average_score
FROM teacher t
JOIN course c ON t.teacher_id = c.teacher_id
JOIN score sc ON c.course_id = sc.course_id
GROUP BY t.name;
```


26. 查询分数在80到90之间的学生姓名和课程名称。

```
SELECT s.name, c.course_name
FROM student s
JOIN score sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id
WHERE sc.score BETWEEN 80 AND 90;
```


27. 查询每个班级的平均分数。

```
SELECT s.my_class, AVG(sc.score) AS average_score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.my_class;
```


28. 查询没学过"王讲师"老师讲授的任一门课程的学生姓名。

```
SELECT s.name
FROM student s
LEFT JOIN score sc ON s.student_id = sc.student_id
LEFT JOIN course c ON sc.course_id = c.course_id
LEFT JOIN teacher t ON c.teacher_id = t.teacher_id
WHERE t.name <> '王讲师' AND sc.student_id IS NULL;
```


29. 查询两门及其以上小于85分的同学的学号，姓名及其平均成绩 。

```
SELECT s.student_id, s.name, AVG(sc.score) AS average_score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.student_id, s.name
HAVING COUNT(sc.score) >= 2 AND AVG(sc.score) < 85;
```


30. 查询所有学生的总分并按降序排列。

```
SELECT s.student_id, s.name, SUM(sc.score) AS total_score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.student_id, s.name
ORDER BY total_score DESC;
```


31. 查询平均分数超过85分的课程名称。

```
SELECT c.course_name
FROM course c
JOIN score sc ON c.course_id = sc.course_id
GROUP BY c.course_id, c.course_name
HAVING AVG(sc.score) > 85;
```


32. 查询每个学生的平均成绩排名。

```
SELECT s.student_id, s.name, AVG(sc.score) AS average_score, RANK() OVER (ORDER BY AVG(sc.score) DESC) AS rank
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.student_id, s.name;
```


33. 查询每门课程分数最高的学生姓名和分数。

```
SELECT c.course_name, sc.student_id, s.name, sc.score
FROM course c
JOIN score sc ON c.course_id = sc.course_id
JOIN student s ON sc.student_id = s.student_id
WHERE sc.score = (SELECT MAX(sc2.score) FROM score sc2 WHERE sc2.course_id = c.course_id)
ORDER BY c.course_name, sc.score DESC;
```


34. 查询选修了"高等数学"和"大学物理"的学生姓名。

```
SELECT s.name
FROM student s
JOIN score sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id
WHERE c.course_name IN ('高等数学', '大学物理');
```


35. 按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩（没有选课则为空）。

```
SELECT s.student_id, s.name, c.course_name, sc.score, AVG(sc.score) OVER (PARTITION BY s.student_id) AS average_score
FROM student s
LEFT JOIN score sc ON s.student_id = sc.student_id
LEFT JOIN course c ON sc.course_id = c.course_id
ORDER BY s.student_id, average_score DESC, c.course_name, sc.score DESC;
```


36. 查询分数最高和最低的学生姓名及其分数。

```
SELECT s.name, sc.score AS highest_score, sc2.score AS lowest_score
FROM student s
JOIN (SELECT student_id, MAX(score) AS score FROM score GROUP BY student_id) sc ON s.student_id = sc.student_id
JOIN (SELECT student_id, MIN(score) AS score FROM score GROUP BY student_id) sc2 ON s.student_id = sc2.student_id;
```


37. 查询每个班级的最高分和最低分。

```
SELECT s.my_class, MAX(sc.score) AS highest_score, MIN(sc.score) AS lowest_score
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.my_class;
```


38. 查询每门课程的优秀率（优秀为90分）。

```
SELECT c.course_name, (COUNT(CASE WHEN sc.score >= 90 THEN 1 END) / COUNT(sc.student_id)) * 100 AS excellent_rate
FROM course c
JOIN score sc ON c.course_id = sc.course_id
GROUP BY c.course_id, c.course_name;
```


39. 查询平均分数超过班级平均分数的学生。

```
SELECT s.name, AVG(sc.score) AS avg_score, s.my_class
FROM student s
JOIN score sc ON s.student_id = sc.student_id
GROUP BY s.student_id, s.name, s.my_class
HAVING AVG(sc.score) > (SELECT AVG(sc2.score) FROM score sc2 WHERE sc2.student_id = s.student_id AND sc2.student_id IN (SELECT student_id FROM student WHERE my_class = s.my_class));
```


40. 查询每个学生的分数及其与课程平均分的差值。

```
SELECT s.name, sc.score, c.course_name, (sc.score - AVG(sc2.score)) AS score_difference
FROM student s
JOIN score sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id
JOIN score sc2 ON c.course_id = sc2.course_id
GROUP BY s.name, sc.score, c.course_name;
```

