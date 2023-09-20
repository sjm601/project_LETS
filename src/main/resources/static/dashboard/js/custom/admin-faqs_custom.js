const url = '/admin/faq';

const faqs = document.querySelectorAll('li.faqs');
faqs.forEach((e, i) => {
    const editBtn = e.querySelector('button.edit_btn');
    const removeBtn = e.querySelector('button.remove_btn');
    const category = e.querySelector('select#category');
    const title = e.querySelector('input#title');
    const content = e.querySelector('textarea#content');
    const faqId = faqList[i].id;

    if (editBtn) {
        editBtn.addEventListener('click', event => {
            event.preventDefault();
            const faqForm = {
                faqId: faqId,
                title: title.value,
                content: content.value,
                category: category.value,
            }

            fetch(url, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(faqForm),
            }).then(response => {
                return response.text();
            }).then(message => {
                if (message === 'success') {
                    alert('FAQ 수정이 완료되었습니다.');
                    location.href = url;
                } else if (message === 'fail') {
                    alert('FAQ 수정이 정상적으로 진행되지 않았습니다. 다시 시도해주세요.');
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
                body: JSON.stringify(faqId),
            }).then(response => {
                return response.text();
            }).then(message => {
                if (message === 'success') {
                    alert('FAQ 삭제가 완료되었습니다.');
                    location.href = url;
                } else if (message === 'fail') {
                    alert('FAQ 삭제가 정상적으로 진행되지 않았습니다. 다시 시도해주세요.');
                }
            }).catch(error => {
                alert(error);
            })
        })
    }

})