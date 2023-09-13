// 이미지 업로드 script (희영)
// 사용 방법
// 업로드 할 input 태그 => type="file" class="real-upload form-control" accept="image/*"
// 이미지 프리뷰 할 태그 추가 ul 태그 -> class="image-preview"

const realUploads = document.querySelectorAll('.real-upload');
realUploads.forEach((realUpload, idx) => {
  realUpload.addEventListener('change', (e) => {
    const files = e.currentTarget.files;
    const imagePreview = document.querySelectorAll('.image-preview')[idx];
    const docFrag = new DocumentFragment();

    // 파일 타입 검사
    [...files].forEach(file => {
      if (!file.type.match("image/.*")) {
        alert('이미지 파일만 업로드가 가능합니다.');
        return
      }
      const reader = new FileReader();
      reader.onload = (e) => {
        const preview = createElement(e, file);
        imagePreview.appendChild(preview);
      };
      reader.readAsDataURL(file);
    });
  });

  function createElement(e, file) {
    if (document.querySelectorAll('.imgLi')[idx] == null) {
      const li = document.createElement('li');
      li.classList.add("imgLi");
      const img = document.createElement('img');
      img.classList.add("createImg");
      img.setAttribute('src', e.target.result);
      img.setAttribute('data-file', file.name);
      img.setAttribute('style', 'width: 200px; height: 200px;');
      li.appendChild(img);

      return li;
    } else {
      const li = document.querySelectorAll('.imgLi')[idx];
      let img = document.querySelectorAll('.createImg')[idx];
      img.setAttribute('src', e.target.result);
      img.setAttribute('data-file', file.name);
      img.setAttribute('style', 'width: 200px; height: 200px;');
      li.appendChild(img);

      return li;
    }
  }
});