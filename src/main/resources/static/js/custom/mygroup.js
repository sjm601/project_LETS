// fetch를 이용해 서버와 통신 후 스터디 그룹 회원 관리하는 javascript (희영)


const closeBtn = document.querySelector('#settingModalCloseBtn');
const accordionBtns = document.querySelectorAll('.settingAccordionBtn');
const collapses = document.querySelectorAll('.accordion-collapse');
const memberListBtn = document.querySelector('#memberListBtn');
const tbodyMemberList = document.querySelector('#tbodyMemberList');
const contactListBtn = document.querySelector('#contactListBtn');
const tbodyContactList = document.querySelector('#tbodyContactList');

// 모달 닫기 버튼 클릭 시 이벤트 처리
closeBtn.addEventListener('click', e => {
    accordionBtns.forEach(btn => {
        btn.classList.add("collapsed");
        btn.setAttribute("aria-expanded", false);
    });
    collapses.forEach(collapse => {
        collapse.classList.remove("show");
    });
});

// 스터기 그룹 멤버 관리 버튼 클릭 시 이벤트 처리
memberListBtn.addEventListener('click', e => {
    const url = `/group/groupSetting/${studyGroupId}`;
    viewMemberList(url);
});

// 스터기 그룹 가입 신천 내역 버튼 클릭 시 이벤트 처리
contactListBtn.addEventListener('click', e => {
    const url = `/group/contactSetting/${studyGroupId}`;
    viewContactList(url);
})

// 스터디 그룹 멤버 관리 화면
//  fetch를 이용해 서버에서 스터디 그룹 멤버 리스트 호출 후 테이블 완성
function viewMemberList(url) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            tbodyMemberList.innerHTML = "";
            for (let i = 0; i < data.length; i++) {
                groupMemberAddRow(data, i);
            }
        }).catch(error => alert(error));
}

// 스터디 그룹 멤버 탈퇴
// fetch를 이용해 서버에서 스터디 그룹 멤버 탈퇴 처리
function deleteMember(id) {
    const url = `/group/${studyGroupId}/` + id;

    if (!confirm("회원을 정말 탈퇴 시킬까요?")) return;

    fetch(url, {
        method: 'DELETE'
    }).then(response => response.text())
        .then(msg => {
            if (msg === 'delete-success') {
                alert("해당 회원이 탈퇴 되었습니다.");
                viewMemberList(`/group/groupSetting/${studyGroupId}`);

            } else {
                alert("탈퇴가 실패 됐습니다.");
            }
        });
}

// 스터디 그룹 멤버 관리 테이블 행 추가
function groupMemberAddRow(data, i) {
    const tr = document.createElement('tr')
    tbodyMemberList.appendChild(tr);
    if (data[i].POSITION === '팀장') {
        tr.innerHTML +=
            `<td>${(i + 1)}</td>
             <td>${data[i].NAME}</td>
             <td>${data[i].GENDER}</td>
             <td>${data[i].AGE}</td>
             <td><span class="btn">${data[i].POSITION}</span></td>`
    } else {
        tr.innerHTML +=
            `<td>${(i + 1)}</td>
             <td>${data[i].NAME}</td>
             <td>${data[i].GENDER}</td>
             <td>${data[i].AGE}</td>
             <td><button type="button" class="btn btn-danger memberRemoveBtn" onclick="deleteMember(${data[i].MEMBERID})" style="font-size: 14px;">탈퇴</button></td>`
    }
}

// 스터디 그룹 가입 신청 내역 화면
// fetch를 이용해 서버에서 스터디 그룹 가입 신청 내역 리스트 호출 후 테이블 완성
function viewContactList(url) {
    fetch(url).then(response => response.json()).then(data => {
        tbodyContactList.innerHTML = "";
        for (let i = 0; i < data.length; i++) {
            groupContactAddRow(data, i);
        }
    }).catch(error => alert(error));
}

// 스터디 그룹 가입 신청 내역 테이블 행 추가
function groupContactAddRow(data, i) {
    const tr = document.createElement('tr')
    tbodyContactList.appendChild(tr);
    tr.innerHTML +=
        `<td>${(i + 1)}</td>
         <td>${data[i].NAME}</td>
         <td>${data[i].GENDER}</td>
         <td>${data[i].AGE}</td>
         <td>${data[i].REGDATE}</td>
         <td>
            <button class="btn btn-success me-2" onclick="contactMember(${data[i].MEMBERID}, 'approve')" style="font-size: 14px;">승인</button>
            <button class="btn btn-danger" onclick="contactMember(${data[i].MEMBERID}, 'refuse')" style="font-size: 14px;">거절</button>
         </td>`
}

// 스터디 그룹 가입 승인/거절 
// fetch를 이용해 서버에서 스터디 그룹 멤버 가입 승인 또는 거절
function contactMember(id, type) {
    const url = `/group/memberContact/${studyGroupId}/` + id;

    if (type === 'approve') {
        if (!confirm("가입을 승인 하시겠습니까?")) return;

        fetch(url, {
            method: 'PUT'
        }).then(response => response.text())
            .then(msg => {
                if (msg === 'approve-success') {
                    alert("가입이 승인되었습니다.");
                    viewContactList(`/group/contactSetting/${studyGroupId}`);

                } else {
                    alert("가입 승인이 실패했습니다.");
                }
            });
    } else {
        if (!confirm("가입을 거절 하시겠습니까?")) return;

        fetch(url, {
            method: 'DELETE'
        }).then(response => response.text())
            .then(msg => {
                if (msg === 'refuse-success') {
                    alert("가입이 거절되었습니다.");
                    viewContactList(`/group/contactSetting/${studyGroupId}`);

                } else {
                    alert("가입 거절이 실패했습니다.");
                }
            });
    }
}