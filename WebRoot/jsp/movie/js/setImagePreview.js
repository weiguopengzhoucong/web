function setImagePreview(form, file) {
	 extArray = new Array(".gif", ".jpg", ".png", ".jpeg","JPG","GIF","JPEG","PNG");
	 allowSubmit = false;
	 if (!file) return;
	 while (file.indexOf("\\") != -1)
	 file = file.slice(file.indexOf("\\") + 1);
	 ext = file.slice(file.indexOf(".")).toLowerCase();
	 for (var i = 0; i < extArray.length; i++) {
	 if (extArray[i] == ext) { allowSubmit = true; break; }
	 }
	 if (allowSubmit) ;
	 else
	 alert("�Բ���ֻ���ϴ����¸�ʽ���ļ�:  "
	 + (extArray.join("  ")) + "\n������ѡ������������ļ�"
	 + "���ϴ�.");

	var docObj = document.getElementById("upload");

	var imgObjPreview = document.getElementById("preview");
	if (docObj.files && docObj.files[0]) {
		//����£�ֱ����img����
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '90px';
		imgObjPreview.style.height = '70px';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();

		//���7���ϰ汾�����������getAsDataURL()��ʽ��ȡ����Ҫһ�·�ʽ  
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

	} else {
		if( document.getElementById("localImag").style.display == 'none'){
			 document.getElementById("localImag").style.display = 'block';
		}
		//IE�£�ʹ���˾�
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag");
		//�������ó�ʼ��С
		localImagId.style.width = "90px";
		localImagId.style.height = "70px";
		//ͼƬ�쳣�Ĳ�׽����ֹ�û��޸ĺ�׺��α��ͼƬ
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters
			.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("���ϴ���ͼƬ��ʽ����ȷ��������ѡ��!");
			return false;
		}
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
	return true;
}

function setImagePreview2(form, file) {
	 extArray = new Array(".gif", ".jpg", ".png", ".jpeg","JPG","GIF","JPEG","PNG");
	 allowSubmit = false;
	 if (!file) return;
	 while (file.indexOf("\\") != -1)
	 file = file.slice(file.indexOf("\\") + 1);
	 ext = file.slice(file.indexOf(".")).toLowerCase();
	 for (var i = 0; i < extArray.length; i++) {
	 if (extArray[i] == ext) { allowSubmit = true; break; }
	 }
	 if (allowSubmit) ;
	 else
	 alert("�Բ���ֻ���ϴ����¸�ʽ���ļ�:  "
	 + (extArray.join("  ")) + "\n������ѡ������������ļ�"
	 + "���ϴ�.");

	var docObj = document.getElementById("upload2");

	var imgObjPreview = document.getElementById("preview2");
	if (docObj.files && docObj.files[0]) {
		//����£�ֱ����img����
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '90px';
		imgObjPreview.style.height = '70px';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();

		//���7���ϰ汾�����������getAsDataURL()��ʽ��ȡ����Ҫһ�·�ʽ  
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

	} else {
		if( document.getElementById("localImag2").style.display == 'none'){
			 document.getElementById("localImag2").style.display = 'block';
		}
		//IE�£�ʹ���˾�
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag2");
		//�������ó�ʼ��С
		localImagId.style.width = "90px";
		localImagId.style.height = "70px";
		//ͼƬ�쳣�Ĳ�׽����ֹ�û��޸ĺ�׺��α��ͼƬ
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters
			.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("���ϴ���ͼƬ��ʽ����ȷ��������ѡ��!");
			return false;
		}
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
	return true;
}

function checkImg(form, file) {

	  extArray = new Array(".gif", ".jpg", ".png", ".jpeg","JPG","GIF","JPEG","PNG");
	 allowSubmit = false;
	 if (!file) return;
	 while (file.indexOf("\\") != -1)
	 file = file.slice(file.indexOf("\\") + 1);
	 ext = file.slice(file.indexOf(".")).toLowerCase();
	 for (var i = 0; i < extArray.length; i++) {
	 if (extArray[i] == ext) { allowSubmit = true; break; }
	 }
	 if (allowSubmit) ;
	 else
	 alert("�Բ���ֻ���ϴ����¸�ʽ���ļ�:  "
	 + (extArray.join("  ")) + "\n������ѡ������������ļ�"
	 + "���ϴ�.");

}