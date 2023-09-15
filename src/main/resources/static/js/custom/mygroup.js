const closeBtn = document.querySelector('#settingModalCloseBtn');
const accordionBtns= document.querySelectorAll('.settingAccordionBtn');
const collapses= document.querySelectorAll('.accordion-collapse');
closeBtn.addEventListener('click', e => {
    accordionBtns.forEach(btn => {
        btn.classList.add("collapsed");
        btn.setAttribute("aria-expanded", false);
    });
    collapses.forEach(collapse => {
        collapse.classList.remove("show");
    });
});