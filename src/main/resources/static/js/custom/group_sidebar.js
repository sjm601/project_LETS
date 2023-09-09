const subjectList = document.querySelectorAll('.subject');
subjectList.forEach(a => {
  a.addEventListener('click', () => {
    subjectList.forEach(a => a.classList.remove('active'));
    a.classList.add('active');
  });
});