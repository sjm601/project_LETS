const url = '/mypage/reservation';

const reservations = document.querySelectorAll('li.reservations');
reservations.forEach((e, i) => {
    const cancelBtn = e.querySelector('button.cancel_btn');
    const reviewBtn = e.querySelector('button.review_btn');
    const rating = e.querySelector('select#rating');
    const content = e.querySelector('textarea#content');

    if (cancelBtn) {
        cancelBtn.addEventListener('click', event => {
            event.preventDefault();
            let reservationId = reservationList[i].id;

            fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reservationId),
            }).then(response => {
                return response.text();
            }).then(message => {
                if (message === 'success') {
                    alert('예약 취소가 완료되었습니다.');
                    location.href = url;
                } else if (message === 'fail') {
                    alert('예약 취소가 정상적으로 진행되지 않았습니다. 다시 시도해주세요.');
                }
            }).catch(error => {
                alert(error);
            })
        })
    }

    if (reviewBtn) {
        reviewBtn.addEventListener('click', event => {
            event.preventDefault();
            const reviewForm = {
                reservationId: reservationList[i].id,
                rating: rating.value,
                content: content.value
            }

            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reviewForm),
            }).then(response => {
                return response.text();
            }).then(message => {
                if (message === 'success') {
                    alert('리뷰 작성이 완료되었습니다.');
                    location.href = url;
                } else if (message === 'fail') {
                    alert('리뷰 작성이 정상적으로 진행되지 않았습니다. 다시 시도해주세요.');
                }
            }).catch(error => {
                alert(error);
            })
        })
    }

})