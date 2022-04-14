INSERT INTO article(id, title, content) VALUES(1, 'Dazed & Confused', '글렌체크');
INSERT INTO article(id, title, content) VALUES(2, 'Day1', 'HONNE');
INSERT INTO article(id, title, content) VALUES(3, 'Butter', 'BTS');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES(4, '당신의 인생 영화는?', '댓글ㄱ');
INSERT INTO article(id, title, content) VALUES(5, '당신의 소울 푸드는?', '댓글ㄱㄱ');
INSERT INTO article(id, title, content) VALUES(6, '당신의 취미는?', '댓글ㄱㄱㄱ');

-- comment 더미 데이터
---- 4번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES(1, 4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(id, article_id, nickname, body) VALUES(2, 4, 'Kim', '다크 나이트');
INSERT INTO comment(id, article_id, nickname, body) VALUES(3, 4, 'Lee', '노팅힐');

---- 5번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES(4, 5, 'Park', '불고기');
INSERT INTO comment(id, article_id, nickname, body) VALUES(5, 5, 'Kim', '파스타');
INSERT INTO comment(id, article_id, nickname, body) VALUES(6, 5, 'Lee', '피자');

---- 6번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES(7, 6, 'Park', '런닝');
INSERT INTO comment(id, article_id, nickname, body) VALUES(8, 6, 'Kim', '바디 웨이트');
INSERT INTO comment(id, article_id, nickname, body) VALUES(9, 6, 'Lee', '게임');