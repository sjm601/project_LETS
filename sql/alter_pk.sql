ALTER TABLE article
    ADD (
    CONSTRAINT article_id_pk PRIMARY KEY (id)
 );

ALTER TABLE article_history
    ADD (
    CONSTRAINT article_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE article_comment
    ADD (
    CONSTRAINT article_comment_id_pk PRIMARY KEY (id)
 );

ALTER TABLE article_comment_history
    ADD (
    CONSTRAINT article_comment_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE cafe
    ADD (
    CONSTRAINT cafe_id_pk PRIMARY KEY (id)
 );

ALTER TABLE cafe_history
    ADD (
    CONSTRAINT cafe_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE cafe_option
    ADD (
    CONSTRAINT cafe_option_id_pk PRIMARY KEY (id)
 );

ALTER TABLE cafe_option_list
    ADD (
    CONSTRAINT cafe_option_list_id_pk PRIMARY KEY (id)
 );

ALTER TABLE contact
    ADD (
    CONSTRAINT contact_id_pk PRIMARY KEY (id)
 );

ALTER TABLE faq
    ADD (
    CONSTRAINT faq_id_pk PRIMARY KEY (id)
 );

ALTER TABLE study_group
    ADD (
    CONSTRAINT study_group_id_pk PRIMARY KEY (id)
 );

ALTER TABLE group_contact
    ADD (
    CONSTRAINT group_contact_id_pk PRIMARY KEY (id)
 );

ALTER TABLE group_history
    ADD (
    CONSTRAINT group_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE group_member_list
    ADD (
    CONSTRAINT group_member_list_id_pk PRIMARY KEY (id)
 );

ALTER TABLE participation_list
    ADD (
    CONSTRAINT participation_list_id_pk PRIMARY KEY (id)
 );

ALTER TABLE study_plan
    ADD (
    CONSTRAINT study_plan_id_pk PRIMARY KEY (id)
 );

ALTER TABLE member
    ADD (
    CONSTRAINT member_id_pk PRIMARY KEY (id)
 );

ALTER TABLE member_history
    ADD (
    CONSTRAINT member_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE reservation
    ADD (
    CONSTRAINT reservation_id_pk PRIMARY KEY (id)
 );

ALTER TABLE review
    ADD (
    CONSTRAINT review_id_pk PRIMARY KEY (id)
 );

ALTER TABLE review_history
    ADD (
    CONSTRAINT review_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE review_comment
    ADD (
    CONSTRAINT review_comment_id_pk PRIMARY KEY (id)
 );

ALTER TABLE review_comment_history
    ADD (
    CONSTRAINT review_comment_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE room
    ADD (
    CONSTRAINT room_id_pk PRIMARY KEY (id)
 );

ALTER TABLE room_history
    ADD (
    CONSTRAINT room_history_id_pk PRIMARY KEY (id)
 );

ALTER TABLE si_do
    ADD (
    CONSTRAINT si_do_id_pk PRIMARY KEY (id)
 );

ALTER TABLE si_gun_gu
    ADD (
    CONSTRAINT si_gun_gu_id_pk PRIMARY KEY (id)
 );

commit;