const url = "/mypage/review";

const reviews = document.querySelectorAll("li.reviews");
reviews.forEach((e, i) => {
    const editBtn = e.querySelector("button.edit_btn");
    const removeBtn = e.querySelector("button.remove_btn");
    const rating = e.querySelector("select#rating");
    const content = e.querySelector("textarea#content");

    editBtn.addEventListener('click', event => {
        event.preventDefault();
        const reviewForm = {
            reviewId: reviewList[i].id,
            rating: rating.value,
            content: content.value
        }

        fetch(url, {
            method: 'PATCH',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(reviewForm),
        }).then(response => {
            return response.text();
        }).then(message => {
            if (message === "success") {
                alert("리뷰 수정이 완료되었습니다.");
                location.href = url;
            }
        }).catch(error => {
            alert(error);
        })
    })

    removeBtn.addEventListener('click', event => {
        event.preventDefault();
        const reviewId = reviewList[i].id;

        fetch(url, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(reviewId),
        }).then(response => {
            return response.text();
        }).then(message => {
            if (message === "success") {
                alert("리뷰 삭제가 완료되었습니다.");
                location.href = url;
            }
        }).catch(error => {
            alert(error);
        })
    })

})