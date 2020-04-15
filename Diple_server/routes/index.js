require("date-utils");

var path = require("path");
var fs = require("fs");
var easyimage = require("easyimage");
var express = require('express');
var FB = require('fb');
var generic_pool = require("generic-pool");
var gcm = require('node-gcm');
var async = require("async");

var mysql = require("mysql");
var pool = generic_pool.Pool({
	name : 'mysql',
	create : function(callback) {
		var config = {
			host : "localhost",
			port : "3306",
			user : "root",
			password : "1234",
			database : "DIPLE_DB"
		};
		var client = mysql.createConnection(config);
		client.connect(function(err){
			if(err) console.error("err", err);
			callback(err, client);
		});
	},
	destroy: function(client){
		client.end();
	},
	min: 2,
	max: 10,
	idleTimeoutMillis: 300000,	//사용 안할시 자동으로 끊어주는 시간 5분
	log: true
});

process.on("exit", function(){
	pool.drain(function(){
		pool.destroyAllNow();
	});
});

var session = express.session({
	userNo: "",
	otherNo: ""
});


exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};


//페이스북 회원가입, 로그인
exports.fbLogin = function(req, res){
	var userId = req.body.userId;
	var accessToken = req.body.accessToken;
	var myPhoneNumber = req.body.myPhoneNumber;
	var otherPhoneNumber = req.body.otherPhoneNumber;
	var regId=[];
	regId.push(req.body.regId);
	var message = new gcm.Message();
	var sender = new gcm.Sender('AIzaSyDKICpMR4mSdpRmU0x4hC9YNaOWwierd6w');
	var nowdate = new Date();
	var regdate = nowdate.toFormat('YYYY-MM-DD');

	var FB = require('fb');
	console.log(userId, accessToken, myPhoneNumber, otherPhoneNumber,regId);
	FB.setAccessToken(accessToken);
	FB.api('fql', {q : {id: 'SELECT uid FROM user WHERE uid=me()'} }, function(results) {
		if(results.data[0].fql_result_set[0].uid == userId){
			pool.acquire(function(err, conn){
				conn.query("select userNo,otherNo from DIPLE_USER_INFO where userId=?", [userId], function(err, re){
					if(err) {res.json({"result":"fail"});}
					if(re[0]){			// 이미회원
						conn.query("update DIPLE_USER_INFO set regId=? where userId=?", [regId, userId], function(err, r){
							if(err) {res.json({"result":"fail"});}
							req.session.userNo = re[0].userNo;
							req.session.otherNo = re[0].otherNo;
							console.log("req.session.otherNo", req.session.otherNo);
							res.json({"result":"123"});
						});

					} else {				//회원가입
						console.log(userId, accessToken, myPhoneNumber, otherPhoneNumber,regId);
						conn.query("insert into DIPLE_USER_INFO(userId, password, myPhoneNumber, otherPhoneNumber, regId, regdate) values(?,?,?,?,?,?);", [userId, accessToken, myPhoneNumber, otherPhoneNumber,regId,regdate], function(err, re){
							console.log("otherPhoneNumber", otherPhoneNumber, "myPhoneNumber", myPhoneNumber);
							conn.query("select * from DIPLE_USER_INFO where myPhoneNumber=? and otherPhoneNumber=?", [otherPhoneNumber, myPhoneNumber], function(err, r){ // 번호가 같으면 가져옴
								console.log("r[0]", r[0]);
								if(r[0]){	//번호가 존재할경우
									conn.query("update DIPLE_USER_INFO set otherNo=? where userId=?", [r[0].userNo, userId], function(err, resu){	//otherNo등록
										//푸쉬 메세지 보내기
										console.log("r[0].regId!!!!!!!!!!", r[0].regId);
										regId.push(r[0].regId);
										message.collapseKey = "who123";
										message.addData('key1', "1");
										message.delayWhileIdle = true;
										message.timeToLive = 3;
										console.log(message, regId);
										sender.sendNoRetry(message, regId, function(err, result) {
											console.log('result', result);
											res.json({"result":"success"});
										});
										conn.query("update DIPLE_USER_INFO set otherNo=? where userId=?", [userId, r[0].userNo], function(err, a){
											if(err) {res.json({"result":"fail"});}
										});
										conn.query("select userNo,otherNo from DIPLE_USER_INFO where userId=?", [userId], function(err, a){
											if(err) {res.json({"result":"fail"});}
											req.session.userNo = a[0].userNo;
											req.session.otherNo = a[0].otherNo;
											console.log("req.session.otherNo", req.session.otherNo);
										});
									});
								} else{
									console.log("userId", userId);
									conn.query("select userNo,otherNo from DIPLE_USER_INFO where userId=?", [userId], function(err, a){
										if(err) {res.json({"result":"fail"});}
										req.session.userNo = a[0].userNo;
										req.session.otherNo = a[0].otherNo;
										console.log("req.session.otherNo", req.session.otherNo);
										res.json({"result":"success"});
									});
								}
							});
						});

					}
				});
				pool.release(conn);
			});
		} else {	
			res.json({"result":"fail facebook login","resultCode":-1});
		}	
	});
}

//일반 회원가입
exports.emJoin = function(req, res){
	var userId = req.body.id;
	var password = res.body.password;

	pool.acquire(function(err, conn){
		conn.query("insert into DIPLE_USER_INFO(userId, password) values(?,?)", [userId, password], function(err, result){
			if(err) {res.json({"result":"fail facebook login","resultCode":-1});}
			conn.query("select userNo,otherNo from DIPLE_USER_INFO where userId=?", [userId], function(err, result){
				if(err) {res.json({"result":"fail facebook login","resultCode":-1});}
				req.session.userNo = result[0].userNo;
				req.session.otherNo = result[0].otherNo;
				console.log("req.session.otherNo", req.session.otherNo);
				res.json({"result":"success Join","resultCode":0});
			});
		});
		pool.release(conn);
	});
}

//일반 로그인
exports.emLogin = function(req, res){
	var userId = req.body.id;
	var password = res.body.password;

	pool.acquire(function(err, conn){
		conn.query("insert into DIPLE_USER_INFO(userId, password) values(?,?)", [userId, password], function(err, result){
			if(err) {res.json({"result":"fail facebook login","resultCode":-1});}
			req.session.userNo = result[0].userNo;
			res.json({"result":"success login","resultCode":0});
		});
		pool.release(conn);
	});
}


//중복확인
exports.idCheck = function(req, res){
	var userId = req.body.id;

	pool.acquire(function(err, conn){
		conn.query("select userId from DIPLE_USER_INFO where userId=?", [userId], function(err, result){
			if(err) {res.json({"result":"fail facebook login","resultCode":-1});}
			if(result[0]){
				res.json({"result":"success","resultCode":0});
			}else{
				res.json({"result":"fail","resultCode":-1});
			}
		});
		pool.release(conn);
	});
}

//체크인
exports.checkin = function(req, res){
	

	console.log(req.body);
		async.waterfall([
			function(callback){	//placeId 구해오기
				console.log("호출1");
				var userNo = req.session.userNo;
				var latitude = req.body.latitude;
				var longitude = req.body.longitude;
				var placeName = req.body.placeName;
				var address = req.body.address;
				var storePhone = req.body.storePhone;
				var placeId = req.body.placeId;
				var comment  = req.body.comment;
				var badgeType = req.body.badgeType;
				var stringPlaceId = String(placeId);
				var date = new Date();
				var regdate = date.toFormat('YYYY-MM-DD HH:mm:ss');
				

				pool.acquire(function(err, conn){
					conn.query("select placeId from DIPLE_LOCATION_INFO where placeId=?", [placeId], function(err, result){
						if(result[0]){	//placeId 존재할 경우
							placeId = result[0].placeId;
							conn.query("update DIPLE_LOCATION_INFO set checkinNum=checkinNum+1 where placeId=?", [placeId], function(err, result){
								if(err) {res.json({"result":"fail"});}
								callback(null, placeId, stringPlaceId);
							});
						}else{	//placeId 존재하지 않을 경우
							conn.query("insert into DIPLE_LOCATION_INFO(placeName, address, latitude, longitude, storePhone, badgeId1,regdate) values(?,?,?,?,?,?,?)", [placeName,address,latitude,longitude,storePhone,badgeType,regdate], function(err, result){						
								if(err) {res.json({"result":"fail"});}
								conn.query("select placeId from DIPLE_LOCATION_INFO order by placeId desc limit 0,1", [], function(err, place){
									placeId = place[0].placeId;
									stringPlaceId = String(placeId);
									callback(null, placeId, stringPlaceId);
								});
							});
						}
					});
					pool.release(conn);
				});	
			},
			function(arg1, arg2, callback){	//어싱크 이치
				var placeId = arg1;
				var stringPlaceId = String(arg2);
				console.log("stringPlaceId", stringPlaceId);
				var originalImg;
				var thumbnailImg;
				var file = req.files.myFile;
				var myFile = [];
				myFile.push(file);
				console.log("호출2");
				console.log("placeId2", arg1);
				console.log("myFile", myFile);
				async.each(myFile, function(item, callback){	//asyc시작!
					console.log(item);
					console.log("호출되었습니다.!!!!!!!!!!!!!!!!");
					if(item.originalFilename == ""){
						res.json({"result":"no file"});
					} else {
						var userfolder = path.resolve(__dirname, '..', 'uploads/location_img/', stringPlaceId);
						if(! fs.existsSync(userfolder)){
							fs.mkdirSync(userfolder);
						}
						var name = item.name+".jpg";
						var srcpath = item.path;
						var destpath = path.resolve(userfolder, name);
						originalImg = "http://54.187.154.204:8000/location_img/"+stringPlaceId+"/"+name;
						thumbnailImg = "http://54.187.154.204:8000/location_img/"+stringPlaceId+"/"+name.substring(0,name.lastIndexOf('.'))+"-thumbnail"+name.substring(name.lastIndexOf('.'));
						console.log("destpath", destpath);
						var is = fs.createReadStream(srcpath);
						var os = fs.createWriteStream(destpath);
						is.pipe(os);
						is.on("end", function(){
							fs.unlinkSync(srcpath);
							var srcimg = destpath;
							var idx = destpath.lastIndexOf('.');
							var filename = destpath.substring(0, idx);
							var ext = destpath.substring(idx);
							var destimg = filename + '-thumbnail' + ext;
							easyimage.resize({src:srcimg, dst:destimg, width:500, height:500}, function(err, image) {
								if(err) console.error('err', err);
								console.log("image", image);
							});
						});
					}
				});
				console.log("호출3");
				var userNo = req.session.userNo;
				var latitude = req.body.latitude;
				var longitude = req.body.longitude;
				var placeName = req.body.placeName;
				var address = req.body.address;
				var storePhone = req.body.storePhone;
				var comment  = req.body.comment;
				var badgeType = req.body.badgeType;
				var date = new Date();
				var regdate = date.toFormat('YYYY-MM-DD HH:mm:ss');

				console.log("placeId3", placeId);
				pool.acquire(function(err, conn){
					console.log("placeId, userNo, originalImg, thumbnailImg", placeId, userNo, originalImg, thumbnailImg);
					conn.query("insert into DIPLE_LOCATION_IMG(placeId, userNo, originalImg, thumbnailImg, regdate) values(?,?,?,?,?)", [placeId,userNo,originalImg,thumbnailImg,regdate], function(err, result){	
						if(err) {res.json({"result":"fail"});}
						console.log("호출은 됨?")
					});
					conn.query("insert into DIPLE_BADGE_INFO(placeId, badgeType, regdate) values(?,?,?)", [placeId,badgeType,regdate], function(err, result){		
						if(err) {res.json({"result":"fail"});}
					});
					conn.query("insert into DIPLE_CHECK_IN(userNo, badgeType, placeId, regdate) values(?,?,?,?)", [userNo,badgeType,placeId,regdate], function(err, result){	
						if(err) {res.json({"result":"fail"});}
					});
					conn.query("insert into DIPLE_RECORD_INFO(placeId, userNo, comment,regdate) values(?,?,?,?)", [placeId,userNo,comment,regdate], function(err, result){	
						if(err) {res.json({"result":"fail"});}
					});
					conn.query("insert into DIPLE_LOCATION_COMMENT(placeId, userNo, comment, regdate) values(?,?,?,?)", [placeId,userNo,comment,regdate], function(err, result){
						if(err) {res.json({"result":"fail"});}
					});
					pool.release(conn);
				});	
				callback(null);
			}//어싱크 이치
			
		],
		function(err){
			if(err) {res.json({"result":"err"});}
			else {res.json({"result":"success"})}
		});
};

exports.marker = function(req, res){
	var latitude = req.body.latitude;
	var longitude = req.body.longitude;
	console.log(req.body);
	pool.acquire(function(err, conn){
		conn.query("select bi.badgeType, li.placeId, li.latitude, li.longitude, li.placeName, li.address, li.checkinNum from DIPLE_LOCATION_INFO li, DIPLE_BADGE_INFO bi where li.latitude<?+0.1 and li.latitude>?-0.1 and li.longitude<?+0.1 and li.longitude>?-0.1 and li.placeId=bi.placeId group by li.placeId", [latitude,latitude,longitude,longitude], function(err, result){
			console.log(result);
			if(err) {res.json({"result":"fail"});}
			else {res.json({"result":result});}
		});
		pool.release(conn);
	});
}

exports.location = function(req, res){
	var placeId = req.body.placeId;
	pool.acquire(function(err, conn){
		conn.query("select originalImg, thumbnailImg from DIPLE_LOCATION_IMG where placeId=?", [placeId], function(err, photo){
			if(err) {res.json({"result":"fail1"});}
			conn.query("select lc.commentId, lc.comment, ui.imgUrl, ui.coupleName, lc.likeNum from DIPLE_LOCATION_COMMENT lc, DIPLE_USER_INFO ui where lc.placeId=? and lc.userNo=ui.userNo", [placeId], function(err, comment){
				if(err) {res.json({"result":"fail2"});}
				conn.query("select * from DIPLE_LOCATION_INFO where placeId=?", [placeId], function(err, locationInfo){
					if(err) {res.json({"result":"fail3"})}
					else {res.json({"photo":photo,"comment":comment,"locationInfo":locationInfo});}
				});	
			});	
		});		
		pool.release(conn);
	});
}

//방문기록(연인)
exports.record = function(req, res){
	var userNo = req.session.userNo;
	var otherNo = req.session.otherNo;
	console.log(userNo);
	pool.acquire(function(err, conn){
		conn.query("select ri.comment,ri.regdate, li.placeName, li.placeId, lm.originalImg, lm.thumbnailImg, li.badgeId1 from DIPLE_RECORD_INFO ri, DIPLE_LOCATION_INFO li, DIPLE_LOCATION_IMG lm where ri.placeId=li.placeId and lm.placeId=ri.placeId and (ri.userNo=? or ?) group by lm.originalImg", [userNo,otherNo], function(err, result){
			if(err) {res.json({"result":"fail"});}
			
			//console.log(result);
			var arr = [];
			var photo = [];
			var curr = '';
			var prev = '';
			for(var i =0, j = 0; i<result.length; i++){
				console.log(result[i]);
				
				curr = result[i];

				if(curr.placeId != prev.placeId){
					console.log("X", curr + ":" + result[i].originalImg + "," + result[i].thumbnailImg);
					prev = curr;
					photo = [];
					arr[j] = {"info":curr, "photo":photo};
					var unsync = { "originalImg":result[i].originalImg, "thumbnailImg":result[i].thumbnailImg };
					photo.push(unsync);
					j++;	// photo 다를때
				} else {
					console.log("O", curr + ":" + result[i].originalImg + "," + result[i].thumbnailImg);
					var sync = { "originalImg":result[i].originalImg, "thumbnailImg":result[i].thumbnailImg };
					photo.push(sync);
				}
			}
			console.log(arr);
			res.json({"result":arr});		
		});
		pool.release(conn);
	});
}

//찜 등록하기
exports.zzimReg = function(req, res){
	var placeId = req.body.placeId;
	var userNo = req.session.userNo;
	var otherNo = req.session.otherNo;
	var date = new Date();

	console.log('otherNo', otherNo);
	pool.acquire(function(err, conn){
		conn.query("select * from DIPLE_ZZIM_INFO where placeId=? and userNo=?", [placeId,userNo], function(err, result1){
			if(err) {res.json({"result":"fail"});}
			if(result1[0]) {res.json({"result":"fail"});}
			else {
				console.log("placeId,userNo,otherNo,regdate", placeId,userNo,otherNo);
				conn.query("insert into DIPLE_ZZIM_INFO(placeId,userNo,otherNo,regdate) values(?,?,?,now())", [placeId,userNo,otherNo], function(err, result2){
					if(err) {res.json({"result":"fail"});}
					else {res.json({"result":"success"});}
				});
			}
		});
		pool.release(conn);
	});
}

//찜 지우기
exports.zzimDel = function(req, res){
	console.log(req.body);
	var placeId = req.body.placeId;
	var userNo = req.session.userNo;
	var otherNo = req.session.otherNo;
	var date = new Date();
	var regdate = date.toFormat('YYYY-MM-DD HH:mm:ss');

	pool.acquire(function(err, conn){
		console.log(placeId, userNo);
			conn.query("delete from DIPLE_ZZIM_INFO where placeId=? and ((userNo=? or otherNo = ?) or (userNo=? or otherNo = ?))", [placeId, userNo, otherNo, otherNo, userNo], function(err, result){
				if(err) {res.json({"result":"fail3"});}
				else {res.json({"result":"success"});}
			});
		pool.release(conn);
	});
}

//찜 조회
exports.zzim = function(req, res){
	var userNo = req.session.userNo;
	var otherNo = req.session.otherNo;
	var date = new Date();
	var regdate = date.toFormat('YYYY-MM-DD HH:mm:ss');
	
	console.log(userNo, otherNo);
	pool.acquire(function(err, conn){
		conn.query("select zi.placeId, li.placeName, li.address, li.badgeId1, li.badgeId2, li.badgeId3 from DIPLE_ZZIM_INFO zi, DIPLE_LOCATION_INFO li where zi.placeId=li.placeId and (userNo=? or ?) group by placeId;", [userNo, otherNo], function(err, result){
			console.log(result);
			if(err) {res.json({"result":"fail"});}
			else {res.json({"result":result});}
		});
		pool.release(conn);
	});
}

//유저정보 조회
exports.user = function(req, res){
	var userNo = req.session.userNo;
	pool.acquire(function(err, conn){
		conn.query("select * from DIPLE_USER_INFO where userNo=?", [userNo], function(err, result){
			console.log(result);
			if(err) {res.json({"result":"fail"});}
			else {res.json({"result":result[0]});}
		});
		pool.release(conn);
	});
}

//유저정보 변경
exports.userUpd = function(req, res){	//상대것도 같이
	var userNo = req.session.userNo;
	var strUserNo = String(userNo);
	var otherNo = req.session.otherNo;
	var myFile;
	var originalImg;
	var thumbnailImg;
	var coupleName = req.body.coupleName;
	var message = req.body.message;
	console.log('myFile', myFile);
	console.log(typeof (req.files));
	if(req.files){
		console.log(typeof req.files.myFile)
		myFile = req.files.myFile;
		if(myFile.originalFilename == ""){
			res.json({result:"no file"});
		} else {
			var userfolder = path.resolve(__dirname, '..', 'uploads/user_img/', strUserNo);
			console.log(userfolder);
			if(! fs.existsSync(userfolder)){
				fs.mkdirSync(userfolder);
			}
			var name = myFile.name+".jpg";
			var srcpath = myFile.path;
			var destpath = path.resolve(userfolder, name);
			originalImg = "http://54.187.154.204:8000/user_img/"+strUserNo+"/"+name;
			thumbnailImg = "http://54.187.154.204:8000/user_img/"+strUserNo+"/"+name.substring(0,name.lastIndexOf('.'))+"-thumbnail"+name.substring(name.lastIndexOf('.'));
			console.log("thumbnailImg");
			console.log("destpath", destpath);
			var is = fs.createReadStream(srcpath);
			var os = fs.createWriteStream(destpath);
			is.pipe(os);
			is.on("end", function(){
				fs.unlinkSync(srcpath);
				var srcimg = destpath;
				var idx = destpath.lastIndexOf('.');
				var filename = destpath.substring(0, idx);
				var ext = destpath.substring(idx);
				var destimg = filename + '-thumbnail' + ext;
				easyimage.resize({src:srcimg, dst:destimg, width:500, height:500}, function(err, image) {
					if(err) console.error('err', err);
					console.log("image", image);
				});
			});
		}
	}
	
	console.log("userNo", userNo, "otherNo", otherNo, "thumbnailImg", thumbnailImg)
	pool.acquire(function(err, conn){
		console.log("myFile", myFile);
		if(myFile){
			console.log(coupleName, message, thumbnailImg, userNo);
			conn.query("update DIPLE_USER_INFO set imgUrl=? where userNo=?", [thumbnailImg, userNo], function(err, result){
				if(err) {res.json({"result":"fail1"});}
				conn.query("update DIPLE_USER_INFO set imgUrl=?  where userNo=?", [thumbnailImg, otherNo], function(err, result){
					if(err) {res.json({"result":"fail2"});}
				});
			});
		}
		conn.query("select imgUrl from DIPLE_USER_INFO where userNo=?", [userNo], function(err, img){
			var img = img[0].imgUrl;
			console.log("img", img);
			conn.query("update DIPLE_USER_INFO set coupleName=?, imgUrl=?, comment=? where userNo=?", [coupleName, img, message, userNo], function(err, result){
				if(err) {res.json({"result":"fail1"});}
				conn.query("update DIPLE_USER_INFO set coupleName=?, imgUrl=?, comment=? where userNo=?", [coupleName, img, message, otherNo], function(err, result){
					if(err) {res.json({"result":"fail2"});}
					conn.query("select * from DIPLE_USER_INFO where userNo=?", [userNo], function(err, user){
						if(err) {res.json({"result":"fail3"});}
						else {res.json({"result":user[0]});}
					});
				});
			});
		});
		pool.release(conn);
	});
}

//유저정보 조회
exports.commentLike = function(req, res){
	var commentId = req.body.commentId;
	console.log(req.body);
	pool.acquire(function(err, conn){
		conn.query("update DIPLE_LOCATION_COMMENT set likeNum=likeNum+1 where commentId=?", [commentId], function(err, result){
			console.log(result);
			if(err) {res.json({"result":"fail"});}
			conn.query("select likeNum from DIPLE_LOCATION_COMMENT where commentId=?", [commentId], function(err, likeNum){
				res.json({"result":likeNum[0]});
			});
		});
		pool.release(conn);
	});
}