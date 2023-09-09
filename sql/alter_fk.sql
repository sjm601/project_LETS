ALTER TABLE article
    ADD (
    CONSTRAINT art_member_id_fk FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT art_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE article_history
    ADD (
    CONSTRAINT arh_article_id_fk FOREIGN KEY (article_id) REFERENCES article (id)
 );

ALTER TABLE article_comment
    ADD (
    CONSTRAINT arc_article_id_fk FOREIGN KEY (article_id)   REFERENCES article (id),
    CONSTRAINT arc_member_id_fk FOREIGN KEY (member_id)  REFERENCES member (id),
    CONSTRAINT arc_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE article_comment_history
    ADD (
    CONSTRAINT ach_comment_id_fk FOREIGN KEY (article_comment_id)  REFERENCES article_comment (id)
 );

ALTER TABLE cafe
    ADD (
    CONSTRAINT caf_si_gun_gu_id_fk FOREIGN KEY(si_gun_gu_id) REFERENCES si_gun_gu(id),
    CONSTRAINT caf_member_id_fk FOREIGN KEY(member_id) REFERENCES member(id),
    CONSTRAINT caf_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE cafe_history
    ADD (
    CONSTRAINT cah_cafe_id_fk FOREIGN KEY(cafe_id) REFERENCES cafe(id)
 );

ALTER TABLE cafe_option_list
    ADD (
    CONSTRAINT col_cafe_id_fk FOREIGN KEY(cafe_id) REFERENCES cafe(id),
    CONSTRAINT col_option_id_fk FOREIGN KEY(option_id) REFERENCES cafe_option(id)
 );

ALTER TABLE contact
    ADD (
    CONSTRAINT con_status_ck CHECK (status IN ('hold','approve','refuse'))
 );


ALTER TABLE study_group
    ADD (
    CONSTRAINT stg_si_gun_gu_id_fk FOREIGN KEY (si_gun_gu_id) REFERENCES SI_GUN_GU(id),
    CONSTRAINT stg_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE group_contact
    ADD (
    CONSTRAINT grc_status_ck CHECK (status IN ('hold','approve','refuse'))
 );

ALTER TABLE group_history
    ADD (
    CONSTRAINT grh_study_group_id_fk FOREIGN KEY (study_group_id) REFERENCES STUDY_GROUP(id)
 );

ALTER TABLE group_member_list
    ADD (
 	CONSTRAINT gml_study_group_id_fk FOREIGN KEY(study_group_id) REFERENCES STUDY_GROUP(id),
    CONSTRAINT gml_member_id_fk FOREIGN KEY(member_id) REFERENCES MEMBER(id)
 );


ALTER TABLE participation_list
    ADD (
 	CONSTRAINT pal_study_group_id_fk FOREIGN KEY(study_group_id) REFERENCES STUDY_GROUP(id),
    CONSTRAINT pal_member_id_fk FOREIGN KEY(member_id) REFERENCES MEMBER(id),
    CONSTRAINT pal_study_plan_id_fk FOREIGN KEY(study_plan_id) REFERENCES STUDY_PLAN(id)
 );

ALTER TABLE member
    ADD (
    CONSTRAINT mem_gender_ck CHECK (gender IN ('male', 'female', 'others')),
    CONSTRAINT mem_type_ck CHECK (type IN ('admin', 'guest', 'host')),
    CONSTRAINT mem_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE member_history
    ADD (
    CONSTRAINT meh_member_id_fk FOREIGN KEY(member_id) REFERENCES member(id)
 );

ALTER TABLE reservation
    ADD (
    CONSTRAINT res_member_id_fk FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT res_room_id_fk FOREIGN KEY (room_id) REFERENCES room (id),
    CONSTRAINT res_pay_status_ck CHECK (pay_status IN ('success', 'fail')),
    CONSTRAINT res_payment_type_ck CHECK (payment_type IN ('card', 'transfer', 'noBankbook'))
 );

ALTER TABLE review
    ADD (
    CONSTRAINT rev_member_id_fk FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT rev_room_id_fk FOREIGN KEY (room_id) REFERENCES room (id),
    CONSTRAINT rev_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE review_history
    ADD (
    CONSTRAINT reh_review_id_fk FOREIGN KEY (review_id) REFERENCES review (id)
 );

ALTER TABLE review_comment
    ADD (
    CONSTRAINT rec_review_id_fk FOREIGN KEY (review_id) REFERENCES review (id),
    CONSTRAINT rec_member_id_fk FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT rec_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE review_comment_history
    ADD (
    CONSTRAINT rch_comment_id_fk FOREIGN KEY ( review_comment_id )  REFERENCES review_comment (id)
 );

ALTER TABLE room
    ADD (
    CONSTRAINT roo_cafe_id_fk FOREIGN KEY(cafe_id) REFERENCES cafe(id),
    CONSTRAINT roo_status_ck CHECK (status IN ('enabled', 'disabled'))
 );

ALTER TABLE room_history
    ADD (
    CONSTRAINT roh_room_id_fk FOREIGN KEY(room_id) REFERENCES room(id)
 );

ALTER TABLE si_gun_gu
    ADD (
    CONSTRAINT sgg_si_do_id_fk FOREIGN KEY(si_do_id) REFERENCES si_do(id)
 );

commit;