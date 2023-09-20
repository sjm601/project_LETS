const mappingUrl = '/member/delete';
const redirectUrl = '/';

const deleteBtn = document.querySelector('button.delete_btn');

if (deleteBtn) {
    deleteBtn.addEventListener('click', event => {
        event.preventDefault();
        const password = document.querySelector('input.deletePassword').value;
        console.log(password);

        fetch(mappingUrl, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: password,
        }).then(response => {
            return response.text();
        }).then(message => {
            if (message === 'success') {
                location.href = redirectUrl;
                alert('회원 탈퇴가 완료되었습니다.');
            } else if(message === 'fail') {
                alert('비밀번호가 올바르게 입력되지 않았습니다.');
            }
        }).catch(error => {
            alert(error);
        })
    })
}

