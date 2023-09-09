select smh.MEMBER_ID, STATUS
from MEMBER_HISTORY mh
         join (select MEMBER_ID, max(MODIFY_DATE) lmd
               from MEMBER_HISTORY
               group by MEMBER_ID) smh ON smh.MEMBER_ID = mh.MEMBER_ID
WHERE mh.MODIFY_DATE = smh.lmd;

select MEMBER_ID, max(MODIFY_DATE)
from MEMBER_HISTORY
group by MEMBER_ID
order by MEMBER_ID;

-- 회원 가입 쿼리
INSERT INTO member(id, email, name, password, type)
VALUES( member_id_seq.NEXTVAL, #{email}, #{name}, #{password}, 'guest');

INSERT INTO member_history(id, status, member_id)
VALUES(member_history_id_seq.NEXTVAL, 'create', member_id_seq.CURRVAL);

-- 회원 수정
UPDATE MEMBER
SET name = #{name},
    password = #{password},
    phone_number = #{phoneNumber},
    gender = #{gender},
    age = #{age},
    image_path = #{imagePath}
WHERE id = #{id};

INSERT INTO member_history(id, status, member_id)
VALUES(member_history_id_seq.NEXTVAL, 'update', #{memberId});

-- 회원 탈퇴
INSERT INTO member_history(id, status, member_id)
VALUES(member_history_id_seq.NEXTVAL, 'delete', #{memberId});

-- 현재 활성화 된 회원 조회
select id, email, name, phone_number, gender, mbti, regdate, type, image_path, status
from MEMBER m
         join (select mhs.MEMBER_ID, STATUS
               from MEMBER_HISTORY mh
                        join (select MEMBER_ID, max(MODIFY_DATE) lmd
                              from MEMBER_HISTORY
                              group by MEMBER_ID) mhs ON mhs.MEMBER_ID = mh.MEMBER_ID
               WHERE mh.MODIFY_DATE = mhs.lmd) ms ON ms.MEMBER_ID = m.ID
where STATUS = 'create' or STATUS = 'update'
order by ID;

-- 회원 가입 시 이메일 중복 조회
select id, EMAIL
from MEMBER m
         join (select mhs.MEMBER_ID, STATUS
               from MEMBER_HISTORY mh
                        join (select MEMBER_ID, max(MODIFY_DATE) lmd
                              from MEMBER_HISTORY
                              group by MEMBER_ID) mhs ON mhs.MEMBER_ID = mh.MEMBER_ID
               WHERE mh.MODIFY_DATE = mhs.lmd) ms ON ms.MEMBER_ID = m.ID
where ( STATUS = 'create' or STATUS = 'update' ) and EMAIL = #{email};

-- 스터디 가입 시 휴대폰 번호 중복 조회
select id, phone_number
from MEMBER m
         join (select mhs.MEMBER_ID, STATUS
               from MEMBER_HISTORY mh
                        join (select MEMBER_ID, max(MODIFY_DATE) lmd
                              from MEMBER_HISTORY
                              group by MEMBER_ID) mhs ON mhs.MEMBER_ID = mh.MEMBER_ID
               WHERE mh.MODIFY_DATE = mhs.lmd) ms ON ms.MEMBER_ID = m.ID
where ( STATUS = 'create' or STATUS = 'update' ) and PHONE_NUMBER = #{phoneNumber};