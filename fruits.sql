// 과일 기본정보 테이블
CREATE TABLE fruits (
    fruit_name VARCHAR(20) NOT NULL PRIMARY KEY,
    file_name VARCHAR(20) NOT NULL,
    calories DOUBLE NOT NULL,
    carbohydrate DOUBLE NOT NULL,
    protein DOUBLE NOT NULL,
    fat DOUBLE NOT NULL,
    sugar DOUBLE NOT NULL,
    expiration INT
);

// 과일 영양소 관계 테이블
CREATE TABLE fn_table(
    fruit_name VARCHAR(20) NOT NULL,
    nutrition VARCHAR(50) NOT NULL,
    unit VARCHAR(5) NOT NULL,
    amount DOUBLE NOT NULL,
    PRIMARY KEY(fruit_name, nutrition)
);

CREATE TABLE user(
    id VARCHAR(20) NOT NULL,
    pw VARCHAR(200) NOT NULL,
    name VARCHAR(10) NOT NULL,
    phone_num VARCHAR(15) NOT NULL,
    age INT NOT NULL,
    PRIMARY KEY(id));



// 영양소별 효능 테이블
// N_TYPE은 비타민, 그 외로 나뉨
CREATE TABLE effective(
    nutrition VARCHAR(20) NOT NULL,
    n_type VARCHAR(20) NOT NULL,
    effective TEXT NOT NULL,
    PRIMARY KEY(nutrition)
);

// 과일 제철시기 테이블
CREATE TABLE period(
    fruit_name VARCHAR(20) NOT NULL,
    start INT NOT NULL,
    end INT NOT NULL,
    PRIMARY KEY(fruit_name)
);

// 커뮤니티 게시글 테이블
CREATE TABLE review(
    review_id INT NOT NULL AUTO_INCREMENT,
    fruit_name VARCHAR(20) NOT NULL,
    review_time DATETIME NOT NULL,
    id VARCHAR(20) NOT NULL,
    content TEXT NOT NULL,
    flavor TEXT,
    PRIMARY KEY(review_id)
);

// 댓글 테이블
CREATE TABLE comment(
    review_id INT NOT NULL,
    post_time DATETIME NOT NULL,
    id VARCHAR(20) NOT NULL,
    comment TEXT NOT NULL,
    PRIMARY KEY(review_id, post_time, id)
);

// user 및 review 좋아요 mapping테이블
CREATE TABLE good(
    review_id INT NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    PRIMARY KEY(review_id, user_id)
);

// 게시물 이미지 저장 테이블
CREATE TABLE review_image(
    review_id INT NOT NULL,
    image_name VARCHAR(30) NOT NULL,
    PRIMARY KEY(review_id, image_name)
);

INSERT INTO effective VALUES('비타민A', 'vitamin', '시력 및 피부 골격유지 면역력 향상 항산화 효과 어두운 곳에서 시각 적응에 도움이 됩니다');
INSERT INTO effective VALUES('베타카로틴', 'vitamin', '노화의 원인인 활성산소를 중화시키는 강력한 항산화 물질의 역할을 하여 노화 방지에 효과가 있습니다');
INSERT INTO effective VALUES('비타민B1(티아민)', 'vitamin', '탄수화물을 에너지로 사용하는 것에 도움을 주고 심장 및 근육, 뇌기능을 정상적으로 유지합니다');
INSERT INTO effective VALUES('비타민B2(리보플라빈)', 'vitamin', '탄수화물, 지방, 단백질을 에너지로 사용하도록 도움을 주고 피부, 모발 손톱, 눈 건강에 도움을 줍니다');
INSERT INTO effective VALUES('비타민B3(나이아신, 니코틴산)', 'vitamin', '체내 에너지 생성, 뇌기능 유지, 피부건강, 고지혈증, 혈당유지, 염증개선에 도움이 됩니다');
INSERT INTO effective VALUES('비타민B6', 'vitamin', '신진대사, 두외발달, 헤모글로빈의 생성, 정신건강에 도움이 되며 호르몬 조절 및 생리전 증후군, 입덧 증상에 도움이 됩니다');
INSERT INTO effective VALUES('엽산', 'vitamin', '세포와 혈액 형성에 도움이 되며 태아 신경관의 정상적인 발달에 필요합니다');
INSERT INTO effective VALUES('비오틴', 'vitamin', '지방, 탄수화물, 단백질 대사와 에너지 생성에 도움이 됩니다');
INSERT INTO effective VALUES('비타민C', 'vitamin', '면역기능 강화, 콜라겐 형성을 통한 뼈, 머리카락, 관절 건강 유지, 항산화 및 해독작용, 스트레스 해소작용, 철의 흡수에 도움이 되며 세포를 보호나는데 도움이 됩니다');
INSERT INTO effective VALUES('비타민E', 'vitamin', '노화작용을 가속화하는 활성산소의 작용을 억제하는 항산화 효과 및 면역체계에 도움을 주며 유해산소로부터 보호하는데 도움을 줍니다');
INSERT INTO effective VALUES('비타민K', 'vitamin', '혈액의 응고에 도움을 주며 뼈와 신장조직을 만드는데 도움이 됩니다');

INSERT INTO effective VALUES('칼륨', 'etc', '나트륨 이온과 함께 세포의 삼투압, 수분 평형을 유지, 신경 및 근육세포의 흥분과 자극전달을 조절하여 근육의 수축과 이완을 조절하며 다량 섭취시 나트륨의 배설을 증가시켜 혈압을 강하시키는데 효과가 있습니다');
INSERT INTO effective VALUES('아미노산', 'etc', '항염증 작용, 운동능력 향상, 근육성장 촉진, 피로회복, 면역력 강화, 체중갑량, 머리카락의 성장, 노화를 예방하는데 도움이 됩니다');
INSERT INTO effective VALUES('칼슘', 'etc', '뼈의 골밀도 유지 등 뼈 건강에 도움을 주며, 근육 수축 및 혈액의 응고에 도움이 됩니다');
INSERT INTO effective VALUES('마그네슘', 'etc', '뼈 건강에 도움이 되며, 근육의 이완과 수축에 관여합니다. 세포의 에너지 생산과 단백질 대사 도움을 줍니다');
INSERT INTO effective VALUES('인', 'etc', '뼈 건강에 도움이 되며 근육, 뇌, 신경의 기능에 도움이 되며 몸의 대사를 촉진시켜줍니다');
INSERT INTO effective VALUES('식이섬유소', 'etc', '콜레스테롤과 중성지방을 낮추어 심혈관질환 예방, 당뇨병 예방 및 치료, 포만감을 느끼게 하여 체중조절에 도움, 장운동을 촉진하여 변비를 예방하며 대장암을 예방하는데 도움이 됩니다.');

// period테이블 insert문
 

// fn_table 테이블 insert문
INSERT INTO fn_table VALUES ('감', '식이섬유', 'g', 6.4);	
INSERT INTO fn_table VALUES ('감', '칼슘', 'mg', 6);	
INSERT INTO fn_table VALUES ('감', '마그네슘', 'mg', 5);	
INSERT INTO fn_table VALUES ('감', '인', 'mg', 15);	
INSERT INTO fn_table VALUES ('감', '칼륨', 'mg', 132);	
INSERT INTO fn_table VALUES ('감', '나트륨', 'mg', 0);	
INSERT INTO fn_table VALUES ('감', '비타민A', 'ug', 7);	
INSERT INTO fn_table VALUES ('감', '비타민B1(티아민)', 'mg', 0.057);	
INSERT INTO fn_table VALUES ('감', '비타민B2(리보플라빈)', 'mg', 0.051);	
INSERT INTO fn_table VALUES ('감', '비타민B3(나이아신,니코틴산)', 'mg', 0.305);	
INSERT INTO fn_table VALUES ('감', '비타민B6', 'mg', 0.026);	
INSERT INTO fn_table VALUES ('감', '비오틴', 'ug', 1.85);	
INSERT INTO fn_table VALUES ('감', '엽산', 'ug', 26);	
INSERT INTO fn_table VALUES ('감', '비타민C', 'mg', 13.95);	
INSERT INTO fn_table VALUES ('감', '비타민E', 'mg', 0.07);	
INSERT INTO fn_table VALUES ('감', '비타민K', 'ug', 0);	
INSERT INTO fn_table VALUES ('감', '총아미노산', 'mg', 332);	
INSERT INTO fn_table VALUES ('감', '베타카로틴', 'ug', 81);

