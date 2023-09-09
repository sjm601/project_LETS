DROP SEQUENCE article_id_seq;

DROP SEQUENCE article_history_id_seq;

DROP SEQUENCE article_comment_id_seq;

DROP SEQUENCE article_comment_history_id_seq;

DROP SEQUENCE cafe_id_seq;

DROP SEQUENCE cafe_history_id_seq;

DROP SEQUENCE cafe_option_id_seq;

DROP SEQUENCE cafe_option_list_id_seq;

DROP SEQUENCE contact_id_seq;

DROP SEQUENCE faq_id_seq;

DROP SEQUENCE study_group_id_seq;

DROP SEQUENCE group_contact_id_seq;

DROP SEQUENCE group_history_id_seq;

DROP SEQUENCE group_member_list_id_seq;

DROP SEQUENCE participation_list_id_seq;

DROP SEQUENCE study_plan_id_seq;

DROP SEQUENCE member_id_seq;

DROP SEQUENCE member_history_id_seq;

DROP SEQUENCE reservation_id_seq;

DROP SEQUENCE review_id_seq;

DROP SEQUENCE review_history_id_seq;

DROP SEQUENCE review_comment_id_seq;

DROP SEQUENCE review_comment_history_id_seq;

DROP SEQUENCE room_id_seq;

DROP SEQUENCE room_history_id_seq;

DROP SEQUENCE si_do_id_seq;

DROP SEQUENCE si_gun_gu_id_seq;

commit;

CREATE SEQUENCE article_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE article_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE article_comment_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE article_comment_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE cafe_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE cafe_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE cafe_option_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE cafe_option_list_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE contact_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE faq_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE study_group_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE group_contact_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE group_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE group_member_list_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE participation_list_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE study_plan_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE member_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE member_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE reservation_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE review_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE review_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE review_comment_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE review_comment_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE room_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE room_history_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE si_do_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE si_gun_gu_id_seq
    START WITH 1
    INCREMENT BY 1;

commit;