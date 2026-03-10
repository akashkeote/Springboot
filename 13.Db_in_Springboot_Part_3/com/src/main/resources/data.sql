-- Insert sample categories
INSERT INTO category (id, title, description) VALUES 
(1, 'Programming', 'Programming courses'),
(2, 'Database', 'Database courses'),
(3, 'Web Development', 'Web dev courses');

-- Insert sample courses
INSERT INTO course (courseId, courseTitle, courseDescription, coursePrice, categoryId) VALUES 
(1, 'Java Basics', 'Learn Java programming', 1000, 1),
(2, 'Spring Boot', 'Master Spring Boot framework', 2000, 1),
(3, 'MySQL', 'Learn MySQL database', 1500, 2),
(4, 'HTML & CSS', 'Web design fundamentals', 800, 3),
(5, 'React JS', 'Modern frontend development', 1800, 3);
