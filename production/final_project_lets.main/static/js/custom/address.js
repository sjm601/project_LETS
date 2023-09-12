window.onload = function () {
  const addressInputs = document.querySelectorAll('.address');
  addressInputs.forEach((input, idx) => {
    input.addEventListener('click', () => {
      new daum.Postcode({
      oncomplete: function (data) {
        addressInputs[idx].value = data.sigungu;
      }
    }).open();
    });
  });
}