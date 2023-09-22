const url = "/host/reviews";

const reviews = document.querySelectorAll("li.reviews");
reviews.forEach((e, i) => {
    const reviewBtn = e.querySelector("button.review_btn");
    const content = e.querySelector("textarea#content");
    const editBtn = e.querySelector('button.edit_btn');
    const commentContent = e.querySelector("textarea#commentContent");
    const reviewId = reviewList[i].commentRevId;
    console.log(reviewId);

    if (reviewBtn) {
        reviewBtn.addEventListener('click', event => {
            event.preventDefault();
            const reviewForm = {
                reservationId: reviewList[i].reservationId,
                content: content.value
            }

            fetch(url, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(reviewForm),
            }).then(response => {
                return response.text();
            }).then(message => {
                if (message === "success") {
                    alert("리뷰 작성이 완료되었습니다.");
                    location.href = url;
                }
            }).catch(error => {
                alert(error);
            })
        })
    }

    if (editBtn) {
        editBtn.addEventListener('click', event => {
            event.preventDefault();
            const reviewForm = {
                reviewId: reviewId,
                content: commentContent.value
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
})