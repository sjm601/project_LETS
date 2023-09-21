package com.vj.lets.web.member.validator;

import com.vj.lets.domain.member.dto.RegisterForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 회원 관련 유효성 검사 컴포넌트
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-21 (목)
 */
@Component
public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegisterForm registerMember = (RegisterForm) target;
        // 폼 필드 검증
        if (!StringUtils.hasText(registerMember.getEmail())) {
            errors.rejectValue("email", "required");
        }

        if (!StringUtils.hasText(registerMember.getPassword())) {
            errors.rejectValue("password", "required");
        }

        if (!StringUtils.hasText(registerMember.getName())) {
            errors.rejectValue("name", "required");
        }

    }
}
