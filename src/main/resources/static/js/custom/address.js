window.onload = function () {
    const sigunguInputs = document.querySelectorAll('.sigunguInputs');
    const sidoInputs = document.querySelectorAll('.sidoInputs');
    sigunguInputs.forEach((input, idx) => {
        input.addEventListener('click', () => {
            new daum.Postcode({
                oncomplete: function (data) {
                    sidoInputs[idx].value = data.sido;
                    sigunguInputs[idx].value = data.sigungu;
                }
            }).open();
        });
    });
}