const url = "/mypage/reservation";

const reservations = document.querySelectorAll("li.reservations");
reservations.forEach((e, i) => {
    const cancelBtn = e.querySelector("button.cancel_btn");
    const reviewBtn = e.querySelector("button.review_btn");
    const rating = e.querySelector("select#rating");
    const content = e.querySelector("textarea#content");

    cancelBtn.addEventListener('click', event => {
        event.preventDefault();
        let reservationId = reservationList[i].id;

        fetch(url, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(reservationId),
        }).then(response => {
            return response.text();
        }).then(message => {
            if (message === "success") {
                alert("예약 취소가 완료되었습니다.");
                location.href = url;
            }
        }).catch(error => {
            alert(error);
        })
    })

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
})