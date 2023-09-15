const closeBtn = document.querySelector('#settingModalCloseBtn');
const accordionBtns= document.querySelectorAll('.settingAccordionBtn');
const collapses= document.querySelectorAll('.accordion-collapse');
const settingBtn= document.querySelector('#setting');
// const tableMemberList= document.querySelector('#memberList');

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

// settingBtn.addEventListener('click', e => {
//     request();
// });

// function request() {
//     const url = `/group/groupSetting/${studyGroupId}`;
//     let promise = fetch(url);
//     promise
//         .then(response => {
//             console.log(response.json());
//         })
//         .then(memberList => {
//             tableMemberList.replaceWith(memberList);
//             console.log(memberList);
//         })
//         .catch(error => alert(error));
// }