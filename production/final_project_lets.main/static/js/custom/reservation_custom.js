// tbody 삭제
$(document).ready(function(){
			
	$('#deleteList').click(function(){
		$('tbody tr').eq(0).empty();
	});
});

//정규 표현식
const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const namePattern = /^[가-힣]{2,10}$/;
const phoneNumberPattern = /^\d{3}\d{3,4}\d{4}$/;

const form = document.querySelector('.needs-validation');
const inputs = form.querySelectorAll('.form-control');

function validateInput(inputElement, regexPattern) {
    const inputValue = inputElement.value;
    return regexPattern.test(inputValue);
}

inputs.forEach((input) => {
    input.addEventListener('blur', function () {
        const pattern =
            input.getAttribute('id') === 'name_booking'
                ? namePattern
                : input.getAttribute('id') === 'email_booking'
                ? emailPattern
                : input.getAttribute('id') === 'telephone_booking'
                ? phoneNumberPattern
                : null;

        if (pattern) {
            const invalidFeedback = input.nextElementSibling;
            const errorMessage =
                input.getAttribute('id') === 'name_booking'
                    ? '이름을 정확히 입력하여 주세요...'
                    : input.getAttribute('id') === 'email_booking'
                    ? '이메일을 정확히 입력하여 주세요...'
                    : input.getAttribute('id') === 'telephone_booking'
                    ? '전화번호를 정확히 입력하여 주세요... (ex. 01012345678)'
                    : null;

            if (!validateInput(input, pattern)) {
                input.parentElement.classList.add('was-validated');
                invalidFeedback.textContent = errorMessage;
                invalidFeedback.style.display = 'block';
            } else {
                input.parentElement.classList.remove('was-validated');
                invalidFeedback.style.display = 'none';
            }
        }
    });
});

form.addEventListener('submit', function (event) {
    event.preventDefault();

    let isValid = true;

    inputs.forEach((input) => {
        const pattern =
            input.getAttribute('id') === 'name_booking'
                ? namePattern
                : input.getAttribute('id') === 'email_booking'
                ? emailPattern
                : input.getAttribute('id') === 'telephone_booking'
                ? phoneNumberPattern
                : null;

        if (pattern) {
            const invalidFeedback = input.nextElementSibling;
            const errorMessage =
                input.getAttribute('id') === 'name_booking'
                    ? '이름을 정확히 입력하여 주세요...'
                    : input.getAttribute('id') === 'email_booking'
                    ? '이메일을 정확히 입력하여 주세요...'
                    : input.getAttribute('id') === 'telephone_booking'
                    ? '전화번호를 정확히 입력하여 주세요... (ex. 01012345678)'
                    : null;

            if (!validateInput(input, pattern)) {
                input.parentElement.classList.add('was-validated');
                invalidFeedback.textContent = errorMessage;
                invalidFeedback.style.display = 'block';
                isValid = false;
            }
        }
    });

    if (!isValid) {
        return;
    } else {
        // 아임포트 실행 코드를 이곳에 두세요
        var IMP = window.IMP; // 생략가능
        IMP.init('imp71428807'); // <-- 본인 가맹점 식별코드 삽입

        var today = new Date();
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes(); // 분
        var seconds = today.getSeconds(); // 초
        var milliseconds = today.getMilliseconds();
        var merchantUid = hours + minutes + seconds + milliseconds;

        var name_booking = document.getElementById('name_booking').value;
        var email_booking = document.getElementById('email_booking').value;
        var telephone_booking = document.getElementById('telephone_booking').value;

        

        IMP.request_pay({
          pg: 'html5_inicis', // 여기에 사용할 PG사 정보를 넣어주세요
          pay_method: 'card',
          merchant_uid: 'IMP' + merchantUid,
          name: '당근 10kg',
          amount: 100,
          buyer_email: email_booking,
          buyer_name: name_booking,
          buyer_tel: telephone_booking
        }, function (rsp) {
          if (rsp.success) {
            let msg = '결제가 완료되었습니다.';
            alert(msg);
            location.href = "payment_success.html"
          } else {
            let msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            alert(msg);
          }
        });
    }
});
