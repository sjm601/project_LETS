// tbody 삭제
$(document).ready(function () {

	$('#deleteList').click(function () {
		$('tbody').empty();
	});
});

document.addEventListener("DOMContentLoaded", function () {
	// 정규 표현식
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
			// 아임포트 실행 코드
			var IMP = window.IMP;
			IMP.init('imp71428807'); // 가맹점 식별코드 삽입

			var today = new Date();
			var hours = today.getHours();
			var minutes = today.getMinutes();
			var seconds = today.getSeconds();
			var milliseconds = today.getMilliseconds();
			var merchantUid = hours + minutes + seconds + milliseconds;

			var name_booking = document.getElementById('name_booking').value;
			var email_booking = document.getElementById('email_booking').value;
			var telephone_booking = document.getElementById('telephone_booking').value;
			var cafeName = [[${reservation['cafe_name']}]];
			var roomName = [[${reservation['room_name']}]];
			var amount = [[${reservation.totalPrice}]];
			var reservationId = [[${reservation.id}]];

			IMP.request_pay({
				pg: 'html5_inicis',
				pay_method: 'card',
				merchant_uid: 'IMP' + merchantUid,
				name: cafeName + '    ' + '룸' + roomName,
				amount: amount,
				buyer_email: email_booking,
				buyer_name: name_booking,
				buyer_tel: telephone_booking
			}, function (rsp) {
				if (rsp.success) {
					let msg = '결제가 완료되었습니다.';
					console.log(msg);

					// 결제 정보를 서버로 전송
					$.ajax({
						type: "POST",
						url: '/reservation/' + reservationId + '/payment/',
						data: {
							merchant_uid: 'IMP' + merchantUid,
							paymentType: 'card',
							payStatus: 'success',
							payName: name_booking,
							payEmail: email_booking,
							payPhoneNumber: telephone_booking
						},
						success: function (data) {
							// 서버로부터 응답을 받으면 이 부분에서 추가 동작 수행
							console.log(data);
						},
						error: function (xhr, status, error) {
							// 요청이 실패한 경우 이 부분에서 에러 처리
							console.error(error);
						}
					});
				} else {
					let msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
					alert(msg);
				}
			});
		}
	});
});