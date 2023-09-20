const url = '/mypage/review';

const reviews = document.querySelectorAll('li.reviews');
reviews.forEach((e, i) => {
    const editBtn = e.querySelector('button.edit_btn');
    const removeBtn = e.querySelector('button.remove_btn');
    const rating = e.querySelector('select#rating');
    const content = e.querySelector('textarea#content');
    const reviewId = reviewList[i].id;

    if (editBtn) {
        editBtn.addEventListener('click', event => {
            event.preventDefault();
            const reviewForm = {
                reviewId: reviewId,
                rating: rating.value,
                content: content.value
            }

            fetch(url, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reviewForm),
            }).then(response => {
                return response.text();
            }).then(message => {
                if (message === 'success') {
                    alert('리뷰 수정이 완료되었습니다.');
                    location.href = url;
                } else if (message === 'fail') {
                    alert('리뷰 수정이 정상적으로 진행되지 않았습니다. 다시 시도해주세요.');
                }
            }).catch(error => {
                alert(error);
            })
        })
    }

    if (removeBtn) {
        removeBtn.addEventListener('click', event => {
            event.preventDefault();

            fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reviewId),
            }).then(response => {
                return response.text();
            }).then(message => {
                if (message === 'success') {
                    alert('리뷰 삭제가 완료되었습니다.');
                    location.href = url;
                } else if (message === 'fail') {
                    alert('리뷰 삭제가 정상적으로 진행되지 않았습니다. 다시 시도해주세요.');
                }
            }).catch(error => {
                alert(error);
            })
        })
    }

})