
/**
 * Module dependencies.
 */

var express = require('express');
var routes = require('./routes');
var user = require('./routes/user');
var http = require('http');
var path = require('path');

var app = express();

// all environments
app.set('port', process.env.PORT || 8000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use(express.cookieParser());
app.use(express.session({ secret: "keyboard cat" }));
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.json());
app.use(express.urlencoded());
app.use(express.methodOverride());
app.use(express.multipart());	//업로드가능
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.static(path.join(__dirname, 'uploads')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', routes.index);


//유저 개인정보
app.post('/fbLogin', routes.fbLogin); //페이스북 회원가입, 로그인
app.post('/emJoin', routes.emJoin); //일반 회원가입
app.post('/emLogin', routes.emLogin); //일반 로그인
app.post('/idCheck', routes.idCheck); //중복확인
app.post('/checkin', routes.checkin); //체크인
app.post('/marker', routes.marker); //주변 marker(badge)불러오기
app.post('/location', routes.location); //장소 상세설명
app.post('/record', routes.record); //장소 상세설명
app.post('/zzimReg', routes.zzimReg); //찜 추가하기
app.post('/zzimDel', routes.zzimDel); //찜 제거하기
app.post('/zzim', routes.zzim); //찜 불러오기
app.post('/user', routes.user); //유저정보 불러오기
app.post('/userUpd', routes.userUpd); //유저정보 변경
app.post('/commentLike', routes.commentLike); //라이크올리기




http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});