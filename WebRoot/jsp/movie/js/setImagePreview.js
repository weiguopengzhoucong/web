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
	 alert("对不起，只能上传以下格式的文件:  "
	 + (extArray.join("  ")) + "\n请重新选择符合条件的文件"
	 + "再上传.");

	var docObj = document.getElementById("upload");

	var imgObjPreview = document.getElementById("preview");
	if (docObj.files && docObj.files[0]) {
		//火狐下，直接设img属性
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '90px';
		imgObjPreview.style.height = '70px';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();

		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

	} else {
		if( document.getElementById("localImag").style.display == 'none'){
			 document.getElementById("localImag").style.display = 'block';
		}
		//IE下，使用滤镜
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag");
		//必须设置初始大小
		localImagId.style.width = "90px";
		localImagId.style.height = "70px";
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters
			.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
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
	 alert("对不起，只能上传以下格式的文件:  "
	 + (extArray.join("  ")) + "\n请重新选择符合条件的文件"
	 + "再上传.");

	var docObj = document.getElementById("upload2");

	var imgObjPreview = document.getElementById("preview2");
	if (docObj.files && docObj.files[0]) {
		//火狐下，直接设img属性
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '90px';
		imgObjPreview.style.height = '70px';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();

		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

	} else {
		if( document.getElementById("localImag2").style.display == 'none'){
			 document.getElementById("localImag2").style.display = 'block';
		}
		//IE下，使用滤镜
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag2");
		//必须设置初始大小
		localImagId.style.width = "90px";
		localImagId.style.height = "70px";
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters
			.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
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
	 alert("对不起，只能上传以下格式的文件:  "
	 + (extArray.join("  ")) + "\n请重新选择符合条件的文件"
	 + "再上传.");

}