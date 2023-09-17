function updateQtyTotal() {
	var input = document.getElementById("headCount");
	var cardQty = document.querySelector(".qtyTotal");

	var newVal = parseInt(input.value) || 0; // 입력값을 정수로 변환, 값이 없을 경우 0으로 처리

	// 최대값을 가져오기
	var maxVal = parseInt(input.max) || 0;

	if (newVal > maxVal) {
		newVal = maxVal; // 현재 값이 최대값을 초과하면 최대값으로 설정
	}

	cardQty.innerHTML = newVal;

	// 입력값을 최대값으로 설정
	input.value = newVal;
}

// 이하의 코드는 변경하지 않고 그대로 유지합니다.
$(function() {
	// 버튼 이벤트 핸들러는 여기에 그대로 유지합니다.
	$(".qtyButtons input").after('<div class="qtyInc"></div>');
	$(".qtyButtons input").before('<div class="qtyDec"></div>');
	$(".qtyDec, .qtyInc").on("click", function() {
		var $button = $(this);
		var oldValue = $button.parent().find("input").val();

		if ($button.hasClass('qtyInc')) {
			var newVal = parseInt(oldValue) + 1;
		} else {
			// don't allow decrementing below zero
			if (oldValue > 1) { // 변경: oldValue가 1 미만으로 가지 않도록 수정
				var newVal = parseInt(oldValue) - 1;
			} else {
				newVal = 1; // 최소값을 1로 설정
			}
		}

		$button.parent().find("input").val(newVal);
		updateQtyTotal();
		$(".qtyTotal").addClass("rotate-x");
	});

	function removeAnimation() { $(".qtyTotal").removeClass("rotate-x"); }
	const counter = document.querySelector(".qtyTotal");
	counter.addEventListener("animationend", removeAnimation);
});