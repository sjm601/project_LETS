DROP TABLE article CASCADE CONSTRAINTS;

DROP TABLE article_history CASCADE CONSTRAINTS;

DROP TABLE article_comment CASCADE CONSTRAINTS;

DROP TABLE article_comment_history CASCADE CONSTRAINTS;

DROP TABLE cafe CASCADE CONSTRAINTS;

DROP TABLE cafe_history CASCADE CONSTRAINTS;

DROP TABLE cafe_option CASCADE CONSTRAINTS;

DROP TABLE cafe_option_list CASCADE CONSTRAINTS;

DROP TABLE contact CASCADE CONSTRAINTS;

DROP TABLE faq CASCADE CONSTRAINTS;

DROP TABLE study_group CASCADE CONSTRAINTS;

DROP TABLE group_contact CASCADE CONSTRAINTS;

DROP TABLE group_history CASCADE CONSTRAINTS;

DROP TABLE group_member_list CASCADE CONSTRAINTS;

DROP TABLE participation_list CASCADE CONSTRAINTS;

DROP TABLE study_plan CASCADE CONSTRAINTS;

DROP TABLE member CASCADE CONSTRAINTS;

DROP TABLE member_history CASCADE CONSTRAINTS;

DROP TABLE reservation CASCADE CONSTRAINTS;

DROP TABLE review CASCADE CONSTRAINTS;

DROP TABLE review_history CASCADE CONSTRAINTS;

DROP TABLE review_comment CASCADE CONSTRAINTS;

DROP TABLE review_comment_history CASCADE CONSTRAINTS;

DROP TABLE room CASCADE CONSTRAINTS;

DROP TABLE room_history CASCADE CONSTRAINTS;

DROP TABLE si_do CASCADE CONSTRAINTS;

DROP TABLE si_gun_gu CASCADE CONSTRAINTS;

commit;

CREATE TABLE article (
                         id	NUMBER(8)		NOT NULL,
                         title	VARCHAR2(255)		NOT NULL,
                         content	VARCHAR2(1024)		NOT NULL,
                         regdate	DATE	DEFAULT SYSDATE	NULL,
                         image_path	VARCHAR2(255)		NULL,
                         status VARCHAR2(16) DEFAULT 'enabled' NULL,
                         member_id	NUMBER(8)		NOT NULL
);

CREATE TABLE article_history (
                                 id	NUMBER(8)		NOT NULL,
                                 modify_comment	VARCHAR2(16)    DEFAULT 'create' NULL,
                                 modify_date	DATE	DEFAULT SYSDATE	NULL,
                                 article_id	NUMBER(8)		NOT NULL
);

CREATE TABLE article_comment (
                                 id	NUMBER(8)		NOT NULL,
                                 content	VARCHAR2(255)		NOT NULL,
                                 regdate	DATE	DEFAULT SYSDATE	NULL,
                                 status VARCHAR2(16) DEFAULT 'enabled' NULL,
                                 article_id	NUMBER(8)		NOT NULL,
                                 member_id	NUMBER(8)		NOT NULL
);

CREATE TABLE article_comment_history (
                                         id	NUMBER(8)    NOT NULL,
                                         modify_comment	VARCHAR2(16)    DEFAULT 'create' NULL,
                                         modify_date	DATE	DEFAULT SYSDATE	NULL,
                                         article_comment_id	NUMBER(8)		NOT NULL
);

CREATE TABLE cafe (
                      id	NUMBER(8)		NOT NULL,
                      email	VARCHAR2(255)		NOT NULL,
                      name	VARCHAR2(255)		NOT NULL,
                      phone_number	VARCHAR2(16)		NOT NULL,
                      road_address	VARCHAR2(255)		NOT NULL,
                      detail_address	VARCHAR2(255)		NOT NULL,
                      latitude	NUMBER(16,8)		NULL,
                      longitude	NUMBER(16,8)		NULL,
                      room_count	NUMBER(2)		NULL,
                      start_time	NUMBER(2)		NULL,
                      end_time	NUMBER(2)		NULL,
                      description	VARCHAR2(512)		NULL,
                      image_path	VARCHAR2(255)		NULL,
                      regdate	DATE	DEFAULT SYSDATE	NULL,
                      business_number	NUMBER(10)		NOT NULL,
                      status VARCHAR2(16) DEFAULT 'enabled' NULL,
                      si_gun_gu_id	NUMBER(8)		NOT NULL,
                      member_id	NUMBER(8)		NOT NULL
);

CREATE TABLE cafe_history (
                              id	NUMBER(8)		NOT NULL,
                              modify_comment	VARCHAR2(16)    DEFAULT 'create' NULL,
                              modify_date	DATE	DEFAULT SYSDATE	NULL,
                              cafe_id	NUMBER(8)		NOT NULL
);

CREATE TABLE cafe_option (
                             id	NUMBER(8)		NOT NULL,
                             name	VARCHAR2(255)		NOT NULL,
                             image_path	VARCHAR2(255)		NULL,
                             description	VARCHAR2(255)		NULL
);

CREATE TABLE cafe_option_list (
                                  id	NUMBER(8)		NOT NULL,
                                  cafe_id	NUMBER(8)		NOT NULL,
                                  option_id	NUMBER(8)		NOT NULL
);

CREATE TABLE contact (
                         id	NUMBER(8)		NOT NULL,
                         email	VARCHAR2(255)		NOT NULL,
                         name	VARCHAR2(255)		NOT NULL,
                         phone_number	VARCHAR2(16)		NOT NULL,
                         address	VARCHAR2(255)		NULL,
                         message	VARCHAR2(255)		NOT NULL,
                         business_number	NUMBER(10)		NULL,
                         contact_date	DATE	DEFAULT SYSDATE	NULL,
                         status	VARCHAR2(16)	DEFAULT 'hold'	NULL
);

CREATE TABLE faq (
                     id	NUMBER(8)		NOT NULL,
                     title	VARCHAR2(255)		NOT NULL,
                     content	VARCHAR2(1024)		NOT NULL
);

CREATE TABLE study_group (
                             id	NUMBER(8)		NOT NULL,
                             name	VARCHAR2(255)		NOT NULL,
                             head_count	NUMBER(2)		NOT NULL,
                             regdate	DATE	DEFAULT SYSDATE	NULL,
                             image_path	VARCHAR2(255)		NULL,
                             subject	VARCHAR2(16)		NOT NULL,
                             status VARCHAR2(16) DEFAULT 'enabled' NULL,
                             si_gun_gu_id	NUMBER(8)		NULL
);

CREATE TABLE group_contact (
                               id	NUMBER(8)		NOT NULL,
                               status	VARCHAR2(16)	DEFAULT 'hold' NULL,
                               regdate DATE DEFAULT SYSDATE	NULL,
                               member_id	NUMBER(8)		NOT NULL,
                               study_group_id	NUMBER(8)		NOT NULL
);

CREATE TABLE group_history (
                               id	NUMBER(8)		NOT NULL,
                               modify_comment	VARCHAR2(16)    DEFAULT 'create' NULL,
                               modify_date	DATE	DEFAULT SYSDATE	NULL,
                               study_group_id	NUMBER(8)		NOT NULL
);

CREATE TABLE group_member_list (
                                   id	NUMBER(8)		NOT NULL,
                                   position	VARCHAR2(16)		NULL,
                                   member_id	NUMBER(8)		NOT NULL,
                                   study_group_id	NUMBER(8)		NOT NULL
);

CREATE TABLE participation_list (
                                    id	NUMBER(8)		NOT NULL,
                                    study_group_id	NUMBER(8)		NOT NULL,
                                    member_id	NUMBER(8)		NOT NULL,
                                    study_plan_id	NUMBER(8)		NOT NULL
);

CREATE TABLE study_plan (
                            id	NUMBER(8)		NOT NULL,
                            head_count	NUMBER(2)		NOT NULL,
                            regdate	DATE	DEFAULT SYSDATE	NULL
);

CREATE TABLE member (
                        id	NUMBER(8)		NOT NULL,
                        email	VARCHAR2(255)		NOT NULL,
                        name	VARCHAR2(255)		NOT NULL,
                        password	VARCHAR2(64)		NOT NULL,
                        phone_number	VARCHAR2(16)		NULL,
                        age  NUMBER(2)		NULL,
                        gender	VARCHAR2(8)		NULL,
                        regdate	DATE	DEFAULT SYSDATE	NULL,
                        type	VARCHAR2(16)		NOT NULL,
                        image_path	VARCHAR2(255)		NULL,
                        status VARCHAR2(16) DEFAULT 'enabled' NULL
);

CREATE TABLE member_history (
                                id	NUMBER(8)		NOT NULL,
                                modify_comment	VARCHAR2(16)    DEFAULT 'create' NULL,
                                modify_date	DATE	DEFAULT SYSDATE	NULL,
                                member_id	NUMBER(8)		NOT NULL
);

CREATE TABLE reservation (
                             id	NUMBER(8)		NOT NULL,
                             booking_date	DATE		NOT NULL,
                             start_time	NUMBER(2)		NOT NULL,
                             end_time	NUMBER(2)		NOT NULL,
                             head_count	NUMBER(2)		NOT NULL,
                             payment_date	DATE	DEFAULT SYSDATE	NULL,
                             pay_status	VARCHAR2(16)		NOT NULL,
                             pay_name	VARCHAR2(255)		NOT NULL,
                             pay_email	VARCHAR2(255)		NOT NULL,
                             pay_phone_number	VARCHAR2(16)		NOT NULL,
                             payment_type	VARCHAR2(16)	DEFAULT 'card'	NULL,
                             member_id	NUMBER(8)		NOT NULL,
                             room_id	NUMBER(8)		NOT NULL
);

CREATE TABLE review (
                        id	NUMBER(8)		NOT NULL,
                        title	VARCHAR2(255)		NOT NULL,
                        content	VARCHAR2(1024)		NOT NULL,
                        star_rating	NUMBER(1)		NOT NULL,
                        write_date	DATE	DEFAULT SYSDATE	NULL,
                        status VARCHAR2(16) DEFAULT 'enabled' NULL,
                        room_id	NUMBER(8)		NOT NULL,
                        member_id	NUMBER(8)		NOT NULL
);

CREATE TABLE review_history (
                                id	NUMBER(8)		NOT NULL,
                                modify_comment	VARCHAR2(16)    DEFAULT 'create' NULL,
                                modify_date	DATE	DEFAULT SYSDATE	NULL,
                                review_id	NUMBER(8)		NOT NULL
);

CREATE TABLE review_comment (
                                id	NUMBER(8)		NOT NULL,
                                content	VARCHAR2(255)		NOT NULL,
                                regdate	DATE	DEFAULT SYSDATE	NULL,
                                status VARCHAR2(16) DEFAULT 'enabled' NULL,
                                review_id	NUMBER(8)		NOT NULL,
                                member_id	NUMBER(8)		NOT NULL
);

CREATE TABLE review_comment_history (
                                        id	NUMBER(8)		NOT NULL,
                                        modify_comment	VARCHAR2(16)    DEFAULT 'create' NULL,
                                        modify_date	DATE	DEFAULT SYSDATE	NULL,
                                        review_comment_id	NUMBER(8)		NOT NULL
);

CREATE TABLE room (
                      id	NUMBER(8)		NOT NULL,
                      name	VARCHAR2(255)		NOT NULL,
                      description	VARCHAR2(512)		NULL,
                      image_path	VARCHAR2(255)		NULL,
                      head_count	NUMBER(2)		NOT NULL,
                      price	NUMBER(8)		NOT NULL,
                      status VARCHAR2(16) DEFAULT 'enabled' NULL,
                      cafe_id	NUMBER(8)		NOT NULL
);

CREATE TABLE room_history (
                              id	NUMBER(8)		NOT NULL,
                              modify_comment	 VARCHAR2(16)    DEFAULT 'create' NULL,
                              modify_date	DATE	DEFAULT SYSDATE	NULL,
                              room_id	NUMBER(8)		NOT NULL
);

CREATE TABLE si_do (
                       id	NUMBER(8)		NOT NULL,
                       name	VARCHAR2(255)		NOT NULL
);

CREATE TABLE si_gun_gu (
                           id	NUMBER(8)		NOT NULL,
                           name	VARCHAR2(255)		NOT NULL,
                           si_do_id	NUMBER(8)		NOT NULL
);

commit;