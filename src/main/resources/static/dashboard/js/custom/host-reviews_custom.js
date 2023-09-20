const url = "/host/reviews";

const reviews = document.querySelectorAll("li.reviews");
reviews.forEach((e, i) => {
    const reviewBtn = e.querySelector("button.review_btn");
    const content = e.querySelector("textarea#content");

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

})