function item(itemId, itemName, blobId) {
	this.itemId = itemId;
	this.itemName = itemName;
	this.blobId = blobId;
};

function item_tmp(itemId, itemName, src) {
	this.itemId = itemId;
	this.itemName = itemName;
	this.src = src;
};

function Reverse(canvasID) {
	this.cv = Cevent(canvasID);
	this.ctx = this.cv.ctx;
	this.nx = 0;
	this.ny = 0;
	this.imgW = 78;
	this.imgH = 78;
	this.imgWW = 100;
	this.imgHH = 100;
	this.space = 0;
	this.imgCount = 10;
	this.imgArray = new Array();
	this.time = 120;
	this.imgNumber = 0;
	this.resultCount = 0;
	this.itemsResult = new Array();
	this.itemsResultCount = 0;
	this.control = false;
	this.drawing = false;
}

Reverse.prototype.start = function(flag, controller) {
	this.control = controller;
	this.intervalId = window.setInterval(function(objRef) {
		return function() {
			if (flag) {
				objRef.reRecycleImg.call(objRef);
			} else {
				objRef.recycleImg.call(objRef);
			}
		};
	}(this), this.time);
	this.drawing = true;
};

Reverse.prototype.recycleImg = function() {
	if (!this.control) {
		this.imgNumber--;
	}
	if (this.imgNumber == this.imgCount) {
		this.drawResult();
		if (this.resultCount == this.itemsResultCount) {
			this.cut();
		}
	} else {
		this.draw();
	}
};

Reverse.prototype.reRecycleImg = function() {
	if (!this.control) {
		this.imgNumber--;
	}
	if (this.imgNumber == this.imgCount) {
		this.drawResult();
		if (this.resultCount == this.itemsResultCount) {
			this.cut();
		}
	} else {
		this.reDraw();
	}
};

Reverse.prototype.draw = function() {
	this.nx = Math.round(Math.random() * (this.cv.width - 100));
	this.ny = Math.round(Math.random() * (this.cv.height - 100));

	this.cv.itemimgrect(0, 0, 0, 0, this.newImgSrc()).draw();
	// this.cv.itemrect(0, 0, 0, 0, this.newItemImg()).draw();
	this.cv.loop(50);
	/*
	var x = 0, y;
	y = this.accounty(this.nx, this.ny, x);
	if (y >= 0 && y <= this.cv.height) {
		if (this.nx > this.cv.width / 2) {
			x = this.cv.width;
			y = this.accounty(this.nx, this.ny, this.cv.width);
		}
	} else {
		if (this.ny > this.cv.height / 2) {
			y = this.cv.height;
			x = this.accountx(this.nx, this.ny, y);
		} else {
			y = 0;
			x = this.accountx(this.nx, this.ny, y);
		}
	}
	*/
	var tx, ty;
	if (this.nx > this.cv.width / 2) {
		tx = this.nx * 2;
	} else {
		tx = this.nx - this.cv.width;
	}
	if (this.ny > ((this.cv.height - 100)) / 2) {
		ty = this.ny * 2;
	} else {
		ty = this.ny - (this.cv.height - 100);
	}
	$(this.cv.get(this.imgNumber)).animate( {
		x : tx,
		y : ty,
		w : this.imgW,
		h : this.imgH
	}, 0).animate( {
		x : this.nx,
		y : this.ny,
		w : this.imgW / 2,
		h : this.imgH / 2
	}, 1500).animate( {
		x : this.nx,
		y : this.ny,
		w : 0,
		h : 0
	}, 0);
	this.imgNumber++;
};

Reverse.prototype.reDraw = function() {
	var shape = this;
	this.nx = Math.round(Math.random() * (this.cv.width - 100));
	this.ny = Math.round(Math.random() * (this.cv.height - 100));

	this.cv.itemimgrect(0, 0, 0, 0, this.newImgSrc()).draw();
	// this.cv.itemrect(0, 0, 0, 0, this.newItemImg()).draw();
	this.cv.loop(50);
	/*
	var x = 0, y;
	y = this.accounty(this.nx, this.ny, x);
	if (y >= 0 && y <= this.cv.height) {
		if (this.nx > this.cv.width / 2) {
			x = this.cv.width;
			y = this.accounty(this.nx, this.ny, this.cv.width);
		}
	} else {
		if (this.ny > this.cv.height / 2) {
			y = this.cv.height;
			x = this.accountx(this.nx, this.ny, y);
		} else {
			y = 0;
			x = this.accountx(this.nx, this.ny, y);
		}
	}
	*/
	var tx, ty;
	if (this.nx > this.cv.width / 2) {
		tx = this.nx * 2;
	} else {
		tx = this.nx - this.cv.width;
	}
	if (this.ny > ((this.cv.height - 100)) / 2) {
		ty = this.ny * 2;
	} else {
		ty = this.ny - (this.cv.height - 100);
	}
	$(this.cv.get(this.imgNumber)).animate( {
		x : this.nx,
		y : this.ny,
		w : this.imgW / 2,
		h : this.imgH / 2
	}, 0).animate( {
		x : tx,
		y : ty,
		w : this.imgW,
		h : this.imgH
	}, 5000);
	this.imgNumber++;
};

Reverse.prototype.remove = function() {
	var flag = false;
	var shapes = this.cv.get();
	for ( var i = 0; i < shapes.length; i++) {
		this.cv.remove(shapes[i]);
		i--;
		flag = true;
	}
	return flag;
};

Reverse.prototype.drawResult = function() {
	var self = this;
	this.nx = Math.round(Math.random() * (this.cv.width - this.imgW));
	this.ny = Math.round(Math.random() * (this.cv.height - this.imgH * 2));
	var tmp;
	if (self.imgArray.length > 0) {
		if (this.computeRandom(this.nx, this.ny, self.imgArray)) {
			self.cv.comitemrect(this.nx, this.ny, this.imgW, this.imgH,
					self.itemsResult[self.resultCount]).draw();
			tmp = self.cv.get(self.cv._shapes.length - 1);
			self.imgArray.push(tmp);
		}
	} else {
		self.cv.comitemrect(this.nx, this.ny, this.imgW, this.imgH,
				self.itemsResult[self.resultCount]).draw();
		tmp = self.cv.get(self.cv._shapes.length - 1);
		self.imgArray.push(tmp);
	}
	self.cv._last.item = self.itemsResult[this.resultCount];
	self.cv.click(function() {
		var shape = self.cv._curHover;
		showOneApp(shape.item.itemId);
	});
	this.resultCount++;
};

Reverse.prototype.stop = function() {
	window.clearInterval(this.intervalId);
	this.resultCount = 0;
	this.imgNumber = 0;
	this.remove();
	this.imgArray = new Array();
};

Reverse.prototype.cut = function() {
	window.clearInterval(this.intervalId);
	this.resultCount = 0;
	this.imgNumber = 0;
};

Reverse.prototype.random = function(lower, upper) {
	return Math.floor(Math.random() * (upper - lower) + lower);
};

Reverse.prototype.checkRandom = function(newLefttopX, newLefttopY, lefttopX,
		lefttopY) {
	var flag = false;
	var dx = Math.abs(newLefttopX - lefttopX), dy = Math.abs(newLefttopY
			- lefttopY);
	if (dx > this.imgWW || dy > this.imgHH) {
		flag = true;
	}
	return flag;
};

Reverse.prototype.computeRandom = function() {
	var flag = false;
	var ox = 0, oy = 0;
	var suffix = 0;
	for ( var i = 0; i < this.imgArray.length; i++) {
		ox = this.imgArray[i].x;
		oy = this.imgArray[i].y;
		if (this.checkRandom(this.nx, this.ny, ox, oy)) {
			suffix++;
		}
	}
	if (suffix == this.imgArray.length) {
		flag = true;
	} else {
		this.nx = Math.round(Math.random() * (this.cv.width - this.imgW));
		this.ny = Math.round(Math.random() * (this.cv.height - this.imgH * 2));
		flag = this.computeRandom(this.nx, this.ny, this.imgArray);
	}
	return flag;
};

Reverse.prototype.newImgSrc = function() {
	var num = this.random(1, 10);
	var src;
	src = "/AppMall/images/canvas/square/square"+num+".png";
	return src;
};

Reverse.prototype.newItemImg = function() {
	var num = this.random(1, 10);
	var sitem = new item_tmp();
	if (num == 1) {
		sitem = new item_tmp("1", "时钟", "/AppMall/images/appIcons/icon1.png");
	}
	if (num == 2) {
		sitem = new item_tmp("2", "网络电视", "/AppMall/images/appIcons/icon2.png");
	}
	if (num == 3) {
		sitem = new item_tmp("3", "图片浏览", "/AppMall/images/appIcons/icon3.png");
	}
	if (num == 4) {
		sitem = new item_tmp("4", "音乐播放器", "/AppMall/images/appIcons/icon4.png");
	}
	if (num == 5) {
		sitem = new item_tmp("5", "便签", "/AppMall/images/appIcons/icon5.png");
	}
	if (num == 6) {
		sitem = new item_tmp("6", "日文词典", "/AppMall/images/appIcons/icon6.png");
	}
	if (num == 7) {
		sitem = new item_tmp("7", "GAMEBOX",
				"/AppMall/images/appIcons/icon7.png");
	}
	if (num == 8) {
		sitem = new item_tmp("8", "视频录制", "/AppMall/images/appIcons/icon8.png");
	}
	if (num == 9) {
		sitem = new item_tmp("9", "记事本", "/AppMall/images/appIcons/icon9.png");
	}
	if (num == 10) {
		sitem = new item_tmp("10", "计算器", "/AppMall/images/appIcons/icon10.png");
	}
	return sitem;
};

Reverse.prototype.accountx = function(x, y, ty) {
	return (ty - y) * (this.cv.width / 2 - x) / (this.cv.height / 2 - y) + x;
};

Reverse.prototype.accounty = function(x, y, tx) {
	return (tx - x) * (this.cv.height / 2 - y) / (this.cv.width / 2 - x) + y;
};