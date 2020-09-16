CREATE TABLE t_user_favorite(
	i_rest INT UNSIGNED,
	i_user INT UNSIGNED,
	r_dt DATETIME DEFAULT NOW(),
	PRIMARY KEY(i_rest, i_user),
	FOREIGN KEY(i_rest) REFERENCES t_restaurant(i_rest),
	FOREIGN KEY(i_user) REFERENCES t_user(i_user)
);

CREATE TABLE t_user(
	i_user INT unsigned PRIMARY KEY AUTO_INCREMENT,
	user_id VARCHAR(30) NOT NULL UNIQUE,
	user_pw VARCHAR(70) NOT NULL,
	salt VARCHAR(30) NOT NULL,
	nm       VARCHAR(5) NOT NULL,
	profile_img VARCHAR(50),
	r_dt DATETIME DEFAULT NOW(),
	m_dt DATETIME DEFAULT NOW()
);

CREATE TABLE t_restaurant(
	i_rest INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	nm VARCHAR(20) NOT NULL,
	addr VARCHAR(100) NOT NULL,
	lat DOUBLE UNSIGNED NOT NULL,
	lng DOUBLE UNSIGNED NOT NULL,
	cd_category INT UNSIGNED NOT NULL,
	i_user INT UNSIGNED NOT NULL,
	r_dt DATETIME DEFAULT NOW(),
	m_dt DATETIME DEFAULT NOW(),
	FOREIGN KEY (i_user) REFERENCES t_user(i_user)
);

CREATE TABLE c_code_m(
	i_m INT UNSIGNED PRIMARY KEY,
	`desc` VARCHAR(30) DEFAULT '',
	cd VARCHAR(20) DEFAULT ''
);

CREATE TABLE c_code_d(
	i_m INT UNSIGNED,
	cd INT UNSIGNED,
	val VARCHAR(15) NOT NULL,
	PRIMARY KEY(i_m, cd),
	FOREIGN KEY(i_m) REFERENCES c_code_m(i_m)
);

CREATE TABLE t_restraurant_recommend_menu (
	i_rest INT UNSIGNED,
	seq INT UNSIGNED,
	menu_nm VARCHAR(20) NOT NULL,
	menu_price INT UNSIGNED NOT NULL,
	menu_pic VARCHAR(50),
	PRIMARY KEY(i_rest, seq),
	FOREIGN KEY(i_rest) REFERENCES t_restaurant(i_rest)
);

CREATE TABLE t_restaurant_menu(
	i_rest INT UNSIGNED,
	seq INT UNSIGNED,
	menu_pic VARCHAR(50),
	PRIMARY KEY(i_rest, seq),
	FOREIGN KEY(i_rest) REFERENCES t_restaurant(i_rest)
);

INSERT INTO c_code_m(i_m, `desc`, cd) VALUES (1, '음식점 카테고리', 'cd_restaurant');
SELECT * FROM c_code_m;

SELECT A.i_rest, A.nm, A.addr, A.i_user, A.hits,
B.val AS cd_category_nm, IFNULL(C.cnt, 0) AS cnt
FROM t_restaurant A
LEFT JOIN c_code_d B
ON A.cd_category = B.cd
AND B.i_m = 1
LEFT JOIN (
	SELECT i_rest, COUNT(i_rest) AS cnt
	FROM t_user_favorite
	GROUP BY i_rest
) C
ON A.i_rest = C.i_rest
WHERE A.i_rest = 1;

DELETE A FROM t_restaurant_recommend_menu A
INNER JOIN t_restaurant B
ON A.i_rest = B.i_rest
WHERE A.i_rest = ?
AND A.seq = ?
AND B.i_user = ?;